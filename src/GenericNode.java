
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
	
	public void sendData(){
		
	}
	
	public void reciveData(){
		
	}
}
