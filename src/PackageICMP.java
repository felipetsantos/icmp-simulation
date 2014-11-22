

public class PackageICMP extends Package  {
	
	private int ttl;
	private ICMPModes mode;

	public PackageICMP(String srcMac, String dstMac, String srcIp,
			String dstIp,ICMPModes mode) {
		
		super(srcMac, dstMac, srcIp, dstIp);
		
		
		type = PackageTypes.ICMP;
		this.ttl =8;
		this.mode = mode;
	}

	public int getTTL(){
		return ttl;
		
	}
	public void decrementTTL(){
		ttl--;
	}
	
    public String getStr(){
    	
    	switch(mode){
			case ICMP_ECHO_REPLY:
				return  "<"+this.srcMac+">-><"+this.dstMac+">|<"+this.srcIp+">-><"+this.dstIp+">,TTL="+this.ttl+"|ICMP_ECHOREPLY";
			case  ICMP_ECHO_REQUEST:
				 return ""+this.srcMac+">-><"+this.dstMac+">|ARP_REQUEST,<"+this.dstIp+">\n";
			case  ICMP_TIME_EXCEECED:
				return "<"+this.srcMac+">-><"+this.dstMac+">|<"+this.srcIp+">-><"+this.dstIp+">,TTL="+this.ttl+"|ICMP_TIMEEXCEEDED";
    	}
    	return "";
    }
    
    public ICMPModes getMode(){
    	return mode;
    }

}
