
public class NetworkInterface {

    private String in;

    // Canal de saida de dados do nodo;
    private String out;
    
    private int mtu;
    
    private String ipAddr;
    
    private String getwayIp;
    
    private String macAddr;

    private NetworkLink link;
    
    public String getGetwayIp(){
        return getwayIp;
    }

    public void setGetwayIp(String ip){
       this.getwayIp = ip;
    }
    
    public String getIpAddr(){
        return ipAddr;
    }

    public void setIpAddr(String ip){
       this.ipAddr = ip;
    }
    
    public void setMacAddr(String mac){
        this.macAddr = mac;
    }
    
    public String getMacAddr(){
        return this.macAddr;
    }
    
    public int getMtu(){
        return mtu;
    }

    public void setMtu(int mtu){
       this.mtu = mtu;
    }
        
    public void setIn(String in){
        this.in = in;
    }
    public void setOut(String out){
         this.out  = out;
    }
    
    public String getIn(){
        return this.in;
    }
    public String getOut(){
        return this.out;
    }

	public NetworkLink getLink() {
		return link;
	}

	public void setLink(NetworkLink link) {
		this.link = link;
	}
   
    
}
