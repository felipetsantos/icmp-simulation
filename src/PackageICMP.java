

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
	public void setTTL(int ttl){
		this.ttl = ttl;
	}
	public void decrementTTL(){
		ttl--;
	}
	
    public String getStr(){
    	
    	switch(mode){
			case ICMP_ECHO_REPLY:
				return  ""+this.srcMac+"->"+this.dstMac+"|"+this.srcIp+"->"+this.dstIp+",TTL="+this.ttl+"|ICMP_ECHOREPLY\n";
			case  ICMP_ECHO_REQUEST:
				//00:00:00:00:00:01->00:00:00:00:00:02|192.168.0.1->192.168.0.2,TTL=8|ICMP_ECHOREQUEST 
				return ""+this.srcMac+"->"+this.dstMac+"|"+this.srcIp+"->"+this.dstIp+",TTL="+this.ttl+"|ICMP_ECHOREQUEST\n";
			case  ICMP_TIME_EXCEECED:
				return ""+this.srcMac+"->"+this.dstMac+"|"+this.srcIp+"->"+this.dstIp+",TTL="+this.ttl+"|ICMP_TIMEEXCEEDED";
    	}
    	return "";
    }
    
    public ICMPModes getMode(){
    	return mode;
    }

}
