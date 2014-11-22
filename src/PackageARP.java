

import java.util.UUID;


public class PackageARP {

	public String dstMac;
	public String srcMac;
    public String dstIp;
    public String srcIp;
    
    public PackageARP(String srcMac,String dstMac,String srcIp,String dstIp){
        this.srcIp = srcIp;
        this.dstMac = dstMac;
        this.srcMac = srcMac;
        this.dstIp = dstIp;
    }
    
    public String getAnswer(ARPTypes type){
    	switch(type){
    	case REPLY:
    		return ""+this.srcMac+"->"+this.dstMac+"|ARP_REPLY,"+this.srcIp+"="+this.srcMac+"\n";
    	case REQUEST: 
    		return ""+this.srcMac+"->"+this.dstMac+"|ARP_REQUEST, "+this.dstIp+"?\n";
    	}
    	return "";
    	
    }

}
