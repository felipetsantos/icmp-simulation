
/**
 *
 * 
 */
public class NetworkLink {
      private  GenericNode nodeA;
      private  GenericNode nodeB;
      private int portA;
      private int portB;
        
      public NetworkLink(GenericNode a,int portA,GenericNode b,int portB){
    	  this.setNodeA(a);
    	  this.setNodeB(b);
    	  this.setPortA(portA);
    	  this.setPortB(portB);
      }

	public int getPortB() {
		return portB;
	}

	private void setPortB(int portB) {
		this.portB = portB;
	}

	public int getPortA() {
		return portA;
	}

	private void setPortA(int portA) {
		this.portA = portA;
	}

	public GenericNode getNodeA() {
		return nodeA;
	}

	private void setNodeA(GenericNode nodeA) {
		this.nodeA = nodeA;
	}

	public GenericNode getNodeB() {
		return nodeB;
	}

	public void setNodeB(GenericNode nodeB) {
		this.nodeB = nodeB;
	}
}
