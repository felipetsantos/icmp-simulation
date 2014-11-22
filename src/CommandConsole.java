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
				System.out.print("ping");
				break;
			case "traceroute":
				//Executa o traceroute
				System.out.print("traceroute");
				break;
			default:
				System.out.print("Comando inválido");
		}
	}
	
	

}
