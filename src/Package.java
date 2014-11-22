

public class Package {

	protected String dstMac;
	protected String srcMac;
	protected String dstIp;
    protected String srcIp;
    protected PackageTypes type;
    
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




}
