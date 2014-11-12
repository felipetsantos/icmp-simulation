import org.jcsp.lang.Alternative;
import org.jcsp.lang.AltingChannelInput;
import org.jcsp.lang.CSProcess;
import org.jcsp.lang.ChannelOutput;
import org.jcsp.lang.Guard;

/**
 * 
 * 
 */
public class Switch implements CSProcess {
        
   
	// Canais de entrada do switch
	private AltingChannelInput[] in;

	//  Canais de entrada do switch
	private ChannelOutput[] out;

	/**
	 * 
	 * @param in Array de portas de saída do switch.
	 * @param out Array de portas de saida do switch.
	 */
	public Switch(AltingChannelInput[] in, ChannelOutput[] out) {
		super();
		this.in = in;
		this.out = out;
	}

	/**
	 * @param srcPort Porta origem, de onde o pacote foi recebido;
	 * @param pkt Pacote a ser encaminhado;
	 */
	private void forwardPkts(int srcPort, Package pkt) {
		for (int i = 0; i < in.length; i++) {
			if (i != srcPort)
				out[i].write(pkt);
		}
	}

	@Override
	public void run() {
		System.out.println("HUB Online.");
		int port;
		Package pkt;
		final Guard[] altChans = (Guard[]) in;
		final Alternative alt = new Alternative(altChans);

		// Loop para o recebimento e encaminhamento dos pacotes pelo Hub.
		while (true) {
			// "fairselect" realiza uma selecao dos canais com niveis de
			// prioridade, favorecendo canais prontos mas que ainda nao foram
			// atendidos. Mesma ideia utilizada num RoundRobin.
			port = alt.fairSelect();
			pkt = (Package) in[port].read();
			forwardPkts(port, pkt);
		}
	}
}
