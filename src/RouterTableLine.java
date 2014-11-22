

/**
 *
 * @author Felipe
 */
public class RouterTableLine {
    
    private String dest;
    private int port;
    private String nextHop;
    
    public RouterTableLine(String dest,int port,String nextHop){
        this.dest  = dest;
        this.port  = port;
        this.nextHop  = nextHop;
    }
    
    public String getNextHop(){
        return this.nextHop;
    }

    public int getPort(){
        return this.port;
    }

    public String ipAddrGetway(){
        return this.dest;
    }
}
