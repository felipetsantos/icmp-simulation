import org.jcsp.lang.AltingChannelInput;
import org.jcsp.lang.CSProcess;
import org.jcsp.lang.ChannelOutput;


public class NodeConsoleChannelWrapper {


	public CSProcess node;
        
        
        public AltingChannelInput in;


	public ChannelOutput out;


	public NodeConsoleChannelWrapper(CSProcess node0, ChannelOutput out,AltingChannelInput in) {
		this.node = node0;
		this.out = out;
                this.in  = in;
	}

}
