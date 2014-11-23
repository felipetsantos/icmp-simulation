public class GenericNode {
	private String name;
	private NodeTypes type;
	private NetworkInterface[] eths;
	private String waitCommand;
	private GenericNode  dst;
	private GenericNode src;
	
	public GenericNode(String nodeName) {
		
		this.setName(nodeName);
	} 

	public void setWaitCommand(String cmd){
		this.waitCommand = cmd;
	}
	public NetworkInterface getEth(int port){
		return this.eths[port];
	}
        
    public void setEths(NetworkInterface[] eths){
            this.eths = eths;
    }
    
	public void setName(String name){
		this.name = name;
	}
	public String getName(){
		return name;
	}

	
	public void setType(NodeTypes type){
		this.type = type;
	}
	public NodeTypes getType(){
		return type;
	}

	public NetworkInterface[] getEths(){
		return this.eths;
	}
	
	
	
	public void sendPkt(Package pkt,int port){
		switch(pkt.type){
			case ICMP:
				PackageICMP icmp = (PackageICMP)pkt;
				this.sendICMPPkt(icmp, port);
				break;
			case ARP:
				PackageARP arp = (PackageARP) pkt;
				this.sendARPPkt(arp,port);
				break;
		}
	}
	
	public void reciveData(Package pkt,int port){
		switch(pkt.type){
		case ICMP:
			PackageICMP icmp = (PackageICMP) pkt;
			reciveICMPPkt(icmp,port);
			break;
		case ARP:
			PackageARP arp = (PackageARP) pkt;
			this.reciveARPPkt(arp,port);
			break;
		}
	}
	
	// Métodos auxiliares ICMP --------------------------------
	private void sendICMPPkt(PackageICMP icmp,int port){
		Object[] objs = this.getConnectNode(port);
		switch(icmp.getMode()){
			case ICMP_ECHO_REQUEST:
					
					System.out.print(icmp.getStr());
					
					((GenericNode)(objs[0])).reciveData(icmp,((int)objs[1]));
				break;
			case ICMP_ECHO_REPLY:
				((GenericNode)(objs[0])).reciveData(icmp,((int)objs[1]));
				break;
			default:
				break;
		}
	}
	private void reciveICMPPkt(PackageICMP pkt,int port){
		switch (pkt.getMode()) {
		case ICMP_ECHO_REQUEST:
			this.reciveICMPRequest(pkt,port);
			break;
		case ICMP_ECHO_REPLY:
			reciveICMPReply(pkt,port);
		default:
			break;
		}
	}
	private void reciveICMPRequest(PackageICMP pkt,int port){
		if(this.type == NodeTypes.NODE){
			// Testa se o pacote está endereçado para esse Node
			if(getEth(0).getMacAddr().equals(pkt.dstMac)){
				// PACOTE esta endereçado para esse node
				PackageICMP newpkt = new PackageICMP(getEth(0).getMacAddr(),pkt.srcMac,pkt.getBeginIp(),pkt.getEndIP(),ICMPModes.ICMP_ECHO_REPLY);
				newpkt.setBeginIp(pkt.getBeginIp());
				newpkt.setEndIp(pkt.getEndIP());
				sendICMPPkt(newpkt,0);
			}
		}else if(this.type == NodeTypes.SWITCH){
			// Envia o pacote para todas as portas do switch
			Switch sw = (Switch)this;
			SwitchTableLine tbln = sw.getTableLine(pkt.dstMac);
			if(tbln != null){
				this.sendICMPPkt(pkt, tbln.getPort());
			}else{
				System.out.print("O ICMP_ECHO_REPLY não pode ser entregue");
			}
		}else if(this.type == NodeTypes.ROUTER){
			if(getEth(port).getMacAddr().equals(pkt.dstMac)){
				//Envia um ARP Request para descobrir o MAC da maquina destino
				//Pega a porta do IP de destino
				Router r = (Router)this;
				String netIp = getNetworkAddress(pkt.dstIp);
				RouterTableLine tbln = r.getTableLine(netIp);
				NetworkInterface srcEth = r.getEth(port);
				NetworkInterface dstEth = r.getEth(tbln.getPort());
				String dstNetIp = getNetworkAddress(dstEth.getIpAddr());
				// Testa se o Ip de destino é da mesma rede
				PackageARP arp;
				if(netIp.equals(dstNetIp)){
					arp = new PackageARP(dstEth.getMacAddr(),"FF:FF:FF:FF:FF:FF", pkt.dstIp);
				}else{
					arp = new PackageARP(dstEth.getMacAddr(),"FF:FF:FF:FF:FF:FF", tbln.getNextHop());
				}
				arp.setTTL(pkt.getTTL());
				arp.setBeginIp(pkt.getBeginIp());
				arp.setEndIp(pkt.getEndIP());
				this.sendPkt(arp,tbln.getPort());
			}
		}
	}
	private void reciveICMPReply(PackageICMP pkt,int port){
		if(this.type == NodeTypes.NODE){
			// Testa se o pacote está endereçado para esse Node
			if(getEth(0).getIpAddr().equals(pkt.dstIp)){
				// PACOTE esta endereçado para esse node
				//IMPRIME ICMP_REPLY
				System.out.print(pkt.getStr());
				//
				//PackageICMP icmp = new PackageICMP(pkt.dstMac,pkt.srcMac,pkt.dstIp,pkt.srcIp,ICMPModes.ICMP_ECHO_REQUEST);
				//this.sendPkt((Package)icmp, 0);
			}
		}else if(this.type == NodeTypes.SWITCH){
			// Envia o pacote para todas as portas do switch
			Switch sw = (Switch)this;
			SwitchTableLine tbln = sw.getTableLine(pkt.dstMac);
			if(tbln != null){
				this.sendICMPPkt(pkt, tbln.getPort());
			}else{
				System.out.print("O ICMP_ECHO_REPLY não pode ser entregue dstMac não encontrado");
			}
		}else if(this.type == NodeTypes.ROUTER){
			System.out.print(pkt.getStr());
			//Envia um ARP Request para descobrir o MAC da maquina destino
			//Pega a porta do IP de destino
			Router r = (Router)this;
			String netIp = getNetworkAddress(pkt.getBeginIp());
			RouterTableLine tbln = r.getTableLine(netIp);
			NetworkInterface dstEth = r.getEth(tbln.getPort());
			Object[] objs = getConnectNode(tbln.getPort());
			String dstMac = ((GenericNode)objs[0]).getEth(((int)objs[1])).getMacAddr();
			
			
			PackageICMP newpkt = new PackageICMP(dstEth.getMacAddr(),dstMac,pkt.getEndIP(),pkt.getBeginIp(),ICMPModes.ICMP_ECHO_REPLY);
			newpkt.setTTL(pkt.getTTL());
			newpkt.setBeginIp(pkt.getBeginIp());
			newpkt.setEndIp(pkt.getEndIP());
			newpkt.decrementTTL();
			sendICMPPkt(newpkt,tbln.getPort());
		}
	}
	
	
	//
	// Métodos auxilares ARP ---------------------------------
	private void sendARPPkt(PackageARP arp,int port){
		
		Object[] objs = this.getConnectNode(port);
		
		switch(arp.getMode()){
			case REQUEST:
					if(arp.getMode() == ARPModes.REQUEST){
						System.out.print(arp.getStr());
					}
					((GenericNode)(objs[0])).reciveData(arp,((int)objs[1]));
				break;
			case REPLY:

				((GenericNode)(objs[0])).reciveData(arp,((int)objs[1]));

				break;
		}
	}

	private void reciveARPPkt(PackageARP pkt,int port){
		switch(pkt.getMode()) {
			case REQUEST:
				this.reciveARPRequest(pkt,port);
				break;
			case REPLY:
				this.reciveARPReply(pkt,port);
				break;
			default:
				break;
		}
	}
	
	private void reciveARPRequest(PackageARP pkt,int port){
		if(this.type == NodeTypes.NODE){
			// Testa se o pacote está endereçado para esse Node
			if(getEth(0).getIpAddr().equals(pkt.dstIp)){
				// PACOTE esta endereçado para esse node
				PackageARP newpkt = new PackageARP(getEth(0).getMacAddr(),pkt.srcMac,pkt.dstIp,pkt.srcIp);
				newpkt.setTTL(pkt.getTTL());
				newpkt.setBeginIp(pkt.getBeginIp());
				newpkt.setEndIp(pkt.getEndIP());
				this.sendARPPkt(newpkt,0);
			}
		}else if(this.type == NodeTypes.SWITCH){
			// Envia o pacote para todas as portas do switch
			Switch sw = (Switch)this;
			sw.addTableLine(pkt.srcMac, port);
			for(int i=0; i<this.eths.length;i++){
				this.sendARPPkt(pkt,i);
			}
		}else if(this.type == NodeTypes.ROUTER){
			if(getEth(port).getIpAddr().equals(pkt.dstIp)){
				PackageARP newpkt = new PackageARP(getEth(port).getMacAddr(),pkt.srcMac,pkt.dstIp,pkt.srcIp);
				newpkt.setTTL(pkt.getTTL());
				newpkt.setBeginIp(pkt.getBeginIp());
				newpkt.setEndIp(pkt.getEndIP());
				this.sendARPPkt(newpkt,port);
			}
		}
	}

	private void reciveARPReply(PackageARP pkt,int port){
		PackageICMP icmp;
		if(this.type == NodeTypes.NODE){
			// Testa se o pacote está endereçado para esse Node
			if(getEth(0).getMacAddr().equals(pkt.dstMac)){
				// PACOTE esta endereçado para esse node
				//IMPRIME ARP_REPLY
				System.out.print(pkt.getStr());
				//
				
				icmp = new PackageICMP(pkt.dstMac,pkt.srcMac,pkt.getBeginIp(),pkt.getEndIP(),ICMPModes.ICMP_ECHO_REQUEST);
				icmp.setBeginIp(pkt.getBeginIp());
				icmp.setEndIp(pkt.getEndIP());
				this.sendPkt((Package)icmp, 0);
			}
		}else if(this.type == NodeTypes.SWITCH){
			// Envia o pacote para todas as portas do switch
			Switch sw = (Switch)this;
			sw.addTableLine(pkt.srcMac, port);
			SwitchTableLine tbln = sw.getTableLine(pkt.dstMac);
			if(tbln != null){
				this.sendARPPkt(pkt, tbln.getPort());
			}else{
				System.out.print("O ARP_REPLY não pode ser entregue dstMac não encontrado");
			}
		}else if(this.type == NodeTypes.ROUTER){
			if(getEth(port).getMacAddr().equals(pkt.dstMac)){
				// PACOTE esta endereçado para esse node
				//IMPRIME ARP_REPLY
				System.out.print(pkt.getStr());
				//
				
				icmp = new PackageICMP(pkt.dstMac,pkt.srcMac,pkt.getBeginIp(),pkt.getEndIP(),ICMPModes.ICMP_ECHO_REQUEST);
				icmp.setTTL(pkt.getTTL());
				icmp.setBeginIp(pkt.getBeginIp());
				icmp.setEndIp(pkt.getEndIP());
				icmp.decrementTTL();
				this.sendPkt((Package)icmp, port);
			}	
		}
	}
	
	
	// Métodos auxiliares 
	private Object[] getConnectNode(int port){
		
		Object[] objects = new Object[2];
		NetworkLink link = this.getEth(port).getLink();
		GenericNode nodeA = link.getNodeA();
		GenericNode nodeB = link.getNodeB();
		if(nodeA.getName().equals(this.getName())){
			objects[0] = nodeB;
			objects[1] =link.getPortB();
		}else{
			objects[0] = nodeA;
			objects[1] =link.getPortA();
			
		}
		return objects;
	}

	public GenericNode getSrc() {
		return src;
	}

	public void setSrc(GenericNode  src) {
		this.src = src;
	}

	public GenericNode  getDst() {
		return dst;
	}

	public void setDst(GenericNode  dst) {
		this.dst = dst;
	}
	
	public String getNetworkAddress(String ip){
		String oct[] = ip.split("\\.");
		String firstOct = Integer.toBinaryString(Integer.parseInt(oct[0]));
		if(firstOct.charAt(0) == '0'){
			return oct[0]+".0.0.0";
		}else if(firstOct.charAt(0) == '1' && firstOct.charAt(1) == '0'){
			return oct[0]+"."+oct[1]+".0.0";
		}else if(firstOct.charAt(0) == '1' && firstOct.charAt(1) == '1'){
			return oct[0]+"."+oct[1]+"."+oct[2]+".0";
		}
		return "";
		
		
	}

}

