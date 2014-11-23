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
				System.out.print("Arquivo inválido");
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
				
				PackageARP arp = new PackageARP(eth0.getMacAddr(),"FF:FF:FF:FF:FF:FF", dstNode.getEth(0).getIpAddr());
				node.setWaitCommand(command);
				node.setSrc(src);
				node.setDst(dst);
				node.sendPkt(((Package)arp),0);
				break;
			case "traceroute":
				//Executa o traceroute
				System.out.print("traceroute");
				break;
			default:
				System.out.print("Comando inválido");
		}
	}
	
	
	
	public void executePing(String src,String dst){
		// Verifica se os ips são da mesma rede
		if(true){
			//Envia o ARP Request com os Ip do nodo destino
			//Imprime resposta
			//Envia o ICMP ECHO Request
		}else{
			//Envia o ARP Request com o Ip do getway
			//Imprime resposta
			//Evia o ICMP ECHO REQUEST com o MAC do Getway
			//Imprime resposta
			
		}
	}

}
