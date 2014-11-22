
public class GenericNode {
	private String name;
	private NodeTypes type;
	private NetworkInterface[] eths;
	
	public GenericNode(String nodeName) {
		
		this.setName(nodeName);
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
				break;
			case ARP:
				PackageARP arp = (PackageARP) pkt;
				this.sendARPPkt(arp,port);
				break;
		}
	}
	

	private void sendARPPkt(PackageARP arp,int port){
		switch(arp.getMode()){
			case REQUEST:
					if(this.type == NodeTypes.NODE){
						System.out.print(arp.getStr());
					}
					NetworkLink link = this.getEth(port).getLink();
					GenericNode nodeA = link.getNodeA();
					GenericNode nodeB = link.getNodeB();
					if(nodeA.getName().equals(this.getName())){
						nodeB.reciveData(arp,link.getPortB());
					}else{
						nodeA.reciveData(arp,link.getPortA());
					}

				break;
			case REPLY:

					NetworkLink link1 = this.getEth(port).getLink();
					GenericNode nodeA1 = link1.getNodeA();
					GenericNode nodeB1 = link1.getNodeB();
					if(nodeA1.getName().equals(this.getName())){
						nodeB1.reciveData(arp,link1.getPortB());
					}else{
						nodeA1.reciveData(arp,link1.getPortA());
					}	

				break;
		}
	}
	public void reciveData(Package pkt,int port){
		switch(pkt.type){
		case ICMP:
			break;
		case ARP:
			PackageARP arp = (PackageARP) pkt;
			this.reciveARPPkt(arp,port);
			break;
		}
	}
	
	private void reciveARPPkt(PackageARP pkt,int port){
		switch (pkt.getMode()) {
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
			
		}
	}

	private void reciveARPReply(PackageARP pkt,int port){
		if(this.type == NodeTypes.NODE){
			// Testa se o pacote está endereçado para esse Node
			if(getEth(0).getMacAddr().equals(pkt.dstMac)){
				// PACOTE esta endereçado para esse node
				System.out.print(pkt.getStr());
			}
		}else if(this.type == NodeTypes.SWITCH){
			// Envia o pacote para todas as portas do switch
			Switch sw = (Switch)this;
			SwitchTableLine tbln = sw.getTableLine(pkt.dstMac);
			if(tbln != null){
				this.sendARPPkt(pkt, tbln.getPort());
			}else{
				System.out.print("O ARP_REPLY não pode ser entregue dstMac não encontrado");
			}
		}else if(this.type == NodeTypes.ROUTER){
			
		}
	}
}
