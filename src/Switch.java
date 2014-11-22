import java.util.Hashtable;


/**
 * 
 * 
 */
public class Switch extends GenericNode  {
	private Hashtable<String, SwitchTableLine> table;
	
	public Switch(String nodeName,int ports) {
		super(nodeName);
		setType(NodeTypes.SWITCH);
		table = new  Hashtable<String, SwitchTableLine>();
		// TODO Auto-generated constructor stub

	}


	 public void addTableLine(String mac,int port){
	 	SwitchTableLine tbline = new SwitchTableLine(mac, port);
	 	table.put(mac,tbline );
	 }
	 
	 public SwitchTableLine getTableLine(String mac){
		 return table.get(mac);
	 }
}
