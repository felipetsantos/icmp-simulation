

import java.util.UUID;

/**
 * Classe que define um pacote do trabalho de redes, utilizado para a
 * comunicacao entre processos.
 * 
 * @author Tiago Ferreto
 * @author Ramon Costi Fernandes <ramon.fernandes@acad.pucrs.br>
 */
public class PackageICMP  {
	
	public String dstMac;
	public String srcMac;
    public String dstIp;
    public String srcIp;
    public int ttl;
    
    public PackageICMP(String srcMac,String dstMac,String srcIp,String dstIp){
        this.dstIp = dstIp;
        this.dstMac = dstMac;
        this.srcMac = srcMac;
        this.ttl = 8;
    }
    
    @Override
    public String toString(){
        return "Pacotes ICMP Time Exceeded:<"+this.srcMac+">-><"+this.dstMac+">|<"+this.srcIp+">-><"+this.dstIp+">,TTL="+this.ttl+"|ICMP_TIMEEXCEEDED";
    }
    
    public String getAnswer(ICMPAnswers type){
    	
    	switch(type){
    		case ECHO_REPLY:
    			return  "<"+this.srcMac+">-><"+this.dstMac+">|<"+this.srcIp+">-><"+this.dstIp+">,TTL="+this.ttl+"|ICMP_ECHOREPLY";
    		case ECHO_REQUEST:
    			 return ""+this.srcMac+">-><"+this.dstMac+">|ARP_REQUEST,<"+this.dstIp+">\n";
    		case TIME_EXCEECED:
    			return "<"+this.srcMac+">-><"+this.dstMac+">|<"+this.srcIp+">-><"+this.dstIp+">,TTL="+this.ttl+"|ICMP_TIMEEXCEEDED";
    	}
    	return "";
    }

}
