

public class Package {

	protected String dstMac;
	protected String srcMac;
	protected String dstIp;
    protected String srcIp;
    protected PackageTypes type;
    private String beginIp;
    private String endIP;
    
    public Package(String srcMac,String dstMac,String srcIp,String dstIp){
        this.srcIp = srcIp;
        this.dstMac = dstMac;
        this.srcMac = srcMac;
        this.dstIp = dstIp;
       
    }

    public Package(String srcMac,String dstMac,String dstIp){
        this.dstIp = dstIp;
        this.dstMac = dstMac;
        this.srcMac = srcMac;
       
    }
    public String getStr(){

    	return "";
    	
    }

	public String getBeginIp() {
		return beginIp;
	}

	public void setBeginIp(String beginIp) {
		this.beginIp = beginIp;
	}

	public String getEndIP() {
		return endIP;
	}

	public void setEndIp(String endIP) {
		
		this.endIP = endIP;
	}




}
