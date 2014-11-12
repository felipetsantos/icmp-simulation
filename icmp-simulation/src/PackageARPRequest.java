

import java.util.UUID;

/**
 * Classe que define um pacote do trabalho de redes, utilizado para a
 * comunicacao entre processos.
 * 
 * @author Tiago Ferreto
 * @author Ramon Costi Fernandes <ramon.fernandes@acad.pucrs.br>
 */
public class PackageARPRequest extends Package {

    public String dstIp;
    
    public PackageARPRequest(String srcMac,String dstMac,String dstIp){
        this.dstIp = dstIp;
        this.dstMac = dstMac;
        this.srcMac = srcMac;
    }
    
    @Override
    public String toString(){
        return "<"+this.srcMac+">-><"+this.dstMac+">|ARP_REQUEST,<"+this.dstIp+">\n";
    }

}
