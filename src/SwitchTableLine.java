

/**
 *
 * @author Felipe
 */
public class SwitchTableLine {
    
    private String macAddress;
    private int port;
    
    public SwitchTableLine(String macAddress,int port){
        this.macAddress  = macAddress;
        this.port  = port;
    }
    
    public String getMacAddress(){
        return this.macAddress;
    }

    public int getPort(){
        return this.port;
    }


}
