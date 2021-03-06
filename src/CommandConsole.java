import java.io.FileNotFoundException;
import java.util.Scanner;

public class CommandConsole {


	public CommandConsole(String[] str){
		
		if(str.length == 4){
			String fileTopology = str[0];
			String command = str[1];
			String src = str[2];
			String dst = str[3];
			Topology topology = this.getTopology(fileTopology);
			if(topology != null){
				this.executeCommand(topology,command, src, dst);
			}else{
				System.out.print("Arquivo inv�lido");
			}
			
		}
		
	}
	
	public Topology getTopology(String file){
		ReadInput reader = new ReadInput();
		return reader.getTopology(file);
	
	}
	
	public void executeCommand(Topology topology,String command,String src,String dst){
		switch(command){
			case "ping":
				// Executa o ping
				GenericNode node =topology.getNode(src);
				GenericNode dstNode =topology.getNode(dst);
				NetworkInterface eth0 = node.getEth(0);
				String srcNetworkAddress = node.getNetworkAddress(eth0.getIpAddr());
				String dstNetworkAddress = node.getNetworkAddress(dstNode.getEth(0).getIpAddr());
				PackageARP arp; 
				if(dstNetworkAddress.equals(srcNetworkAddress)){
					arp = new PackageARP(eth0.getMacAddr(),"FF:FF:FF:FF:FF:FF", dstNode.getEth(0).getIpAddr());
					
				}else{
					arp = new PackageARP(eth0.getMacAddr(),"FF:FF:FF:FF:FF:FF", eth0.getGetwayIp());
					
				}
				arp.srcIp =  eth0.getIpAddr();
				arp.setBeginIp(node.getEth(0).getIpAddr());
				arp.setEndIp(dstNode.getEth(0).getIpAddr());
				node.setWaitCommand(command);
				node.setSrc(node);
				node.setDst(dstNode);
				node.sendPkt(((Package)arp),0);
				break;
			case "traceroute":
				//Executa o traceroute
				System.out.print("traceroute");
				break;
			default:
				System.out.print("Comando inv�lido");
		}
	}
	
	
}
