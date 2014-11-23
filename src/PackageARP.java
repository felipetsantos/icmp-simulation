

public class PackageARP extends Package {

   
	private ARPModes mode;
	private int TTL;
    public PackageARP(String srcMac, String dstMac, String dstIp) {
		super(srcMac, dstMac, dstIp);
		this.type = PackageTypes.ARP;
		this.TTL = 8;
		this.mode = ARPModes.REQUEST;
	}

    public PackageARP(String srcMac, String dstMac, String srcIp, String dstIp) {
		super(srcMac, dstMac, srcIp, dstIp);
		this.type = PackageTypes.ARP;
		this.mode =  ARPModes.REPLY;
	}
	public String getStr(){
    	switch(mode){
	    	case REPLY:
	    		return ""+this.srcMac+"->"+this.dstMac+"|ARP_REPLY,"+this.srcIp+"="+this.srcMac+"\n";
	    	case REQUEST: 
	    		return ""+this.srcMac+"->"+this.dstMac+"|ARP_REQUEST, "+this.dstIp+"?\n";
	    	default:
	    		return "";
    	}
    	
    }
	
	public ARPModes getMode(){
		return mode;
	}

	public int getTTL() {
		return TTL;
	}

	public void setTTL(int tTL) {
		TTL = tTL;
	}



}
