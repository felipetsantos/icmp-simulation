

import java.util.UUID;

/**
 * Classe que define um pacote do trabalho de redes, utilizado para a
 * comunicacao entre processos.
 * 
 * @author Tiago Ferreto
 * @author Ramon Costi Fernandes <ramon.fernandes@acad.pucrs.br>
 */
public class PackageICMPTimeExceeced extends Package {

    public String dstIp;
    public String srcIp;
    public int ttl;
    
    public PackageICMPTimeExceeced(String srcMac,String dstMac,String srcIp,String dstIp){
        this.dstIp = dstIp;
        this.dstMac = dstMac;
        this.srcMac = srcMac;
        this.ttl = 8;
    }
    
    @Override
    public String toString(){
        return "Pacotes ICMP Time Exceeded:<"+this.srcMac+">-><"+this.dstMac+">|<"+this.srcIp+">-><"+this.dstIp+">,TTL="+this.ttl+"|ICMP_TIMEEXCEEDED";
    }

}
