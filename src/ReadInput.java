import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class ReadInput {
		

		public  Topology getTopology(String filename) {
			try{
				File file = new File(filename);
				Topology topology = new Topology();
				Scanner scanner = new Scanner(file);
				int control = 1;
				String data = scanner.next();
				
				while(scanner.hasNext()) {
					if(control == 0) {
						data = scanner.next();
					} else if(control == 2){
						break;
					}else{
						control = 0;
					}
					switch(data) {
						case "#NODE":	
								while(scanner.hasNext()) {
									data = scanner.next();
				
									if(data.isEmpty()) {
										control = 1;
										break;
									} else if(data.charAt(0) == '#') {
										control = 1;
										break;
									}else if(data.charAt(0) == '$'){
										control = 2;
										break;
									}
									String[] values = data.split(",");
									topology.addNode(initNode(values));
								}
							break;
						case "#SWITCH":						
								while(scanner.hasNext()) {
									data = scanner.next();
									if(data.isEmpty()) {
										control = 1;
										break;
									} else if(data.charAt(0) == '#' ) {
										control = 1;
										break;
									}else if(data.charAt(0) == '$'){
										control = 2;
										break;
									}							
									String[] values = data.split(",");
									topology.addNode(initSwitch(values));
								}
							break;
						case "#LINK":		
								while(scanner.hasNext()) {
									data = scanner.next();
									if(data.isEmpty()) {
										control = 1;
										break;
									} else if(data.charAt(0) == '#' ) {
										control = 1;
										break;
									}else if(data.charAt(0) == '$'){
										control = 2;
										break;
									}				
									String[] values = data.split(",");
									initLink(values,topology);							
								}
							break;
						case "#ROUTER":			
								while(scanner.hasNext()) {
									data = scanner.next();
									if(data.isEmpty()) {
										control = 1;
										break;
									} else if(data.charAt(0) == '#') {
										control = 1;
										break;
									}else if(data.charAt(0) == '$'){
										control = 2;
										break;
									}								
									String[] values = data.split(",");
									topology.addNode(initRouter(values));
								}
							break;
						case "#ROUTERTABLE":						
								while(scanner.hasNext()) {
									data = scanner.next();
									if(data.isEmpty()) {
										control = 1;
										break;
									} else if(data.charAt(0) == '#' ) {
										control = 1;
										break;
									}else if(data.charAt(0) == '$'){
										control = 2;
										break;
									}
									String[] values = data.split(",");
									initRouterTable(values,topology);
								}
							break;
					}
					
				}
				scanner.close();
				return topology;
			}catch(FileNotFoundException e){
				System.out.print("Erro ao ler o arquivo");
				return null;
			}
		}
		
		public Node initNode(String[] nodeProperties) {
			String nodeID = nodeProperties[0];
			String mac = nodeProperties[1];
			String ip = nodeProperties[2];
			String getwayIp = nodeProperties[3];	
			
			NetworkInterface eths[] = new NetworkInterface[1];
			NetworkInterface eth0 = new NetworkInterface();
			eth0.setMacAddr(mac);
			eth0.setIpAddr(ip);
			eth0.setGetwayIp(getwayIp);
			eths[0] = eth0;
			Node node = new Node(nodeID);
			node.setEths(eths);
			return node;
			
		}
		
		public  Router initRouter(String[] routerProperties) {
			String routerID = routerProperties[0];
			int numPorts = Integer.parseInt(routerProperties[1]);
			NetworkInterface eths[] = new NetworkInterface[numPorts];
			for(int i=0,j=2;i<numPorts;i++,j=j+2){
				eths[i] = new NetworkInterface();
				String mac = routerProperties[j];
				String ip = routerProperties[j+1];
				eths[i].setMacAddr(mac);
				eths[i].setMacAddr(ip);
			}
			Router r = new Router(routerID);
			r.setEths(eths);

			return r;
		}
		public void initRouterTable(String[] routerTableProperties,Topology topology) {
			String routerID = routerTableProperties[0];
			Router r = (Router)topology.getNode(routerID);
			
			String dest = routerTableProperties[1];
			String nexthop = routerTableProperties[2];
			int port = Integer.parseInt(routerTableProperties[3]);
			r.addTableLine(dest,port,nexthop);
		}
		public  void initLink(String[] linkProperties,Topology topology) {
			
			String nodeIdA = linkProperties[0];
			int nodePortA = Integer.parseInt(linkProperties[1]);
			String nodeIdB = linkProperties[2];
			int nodePortB =  Integer.parseInt(linkProperties[3]);
			
			GenericNode ngA = topology.getNode(nodeIdA);
			GenericNode ngB = topology.getNode(nodeIdB);
			NetworkLink link = new NetworkLink(ngA,nodePortA, ngB, nodePortB);
			ngA.getEth(nodePortA).setLink(link);
			ngB.getEth(nodePortB).setLink(link);
		    
		}
		public Switch initSwitch(String[] switchProperties) {
			String switchId = switchProperties[0];
			int numPorts = Integer.parseInt(switchProperties[1]);
			NetworkInterface eths[] = new NetworkInterface[numPorts];
			for(int i=0;i<numPorts;i++){
				eths[i] = new NetworkInterface();
			}

			Switch s = new Switch(switchId,numPorts);
			s.setEths(eths);
			return s;
		}

}	

