

import java.util.UUID;

/**
 * Classe que define um pacote do trabalho de redes, utilizado para a
 * comunicacao entre processos.
 * 
 * @author Tiago Ferreto
 * @author Ramon Costi Fernandes <ramon.fernandes@acad.pucrs.br>
 */
public class PackageARPReply extends Package {

    public String srcIp;
    
    public PackageARPReply(String srcMac,String dstMac,String srcIp){
        this.srcIp = srcIp;
        this.dstMac = dstMac;
        this.srcMac = srcMac;
    }
    
    @Override
    public String toString(){
        return "<"+this.srcMac+">-><"+this.dstMac+">|ARP_REPLY,<"+this.srcIp+">=<"+this.srcMac+">\n";
    }

}
