

import java.util.Hashtable;



public class Router extends GenericNode {
    

	// tabela de rotas
    private Hashtable<String,RouterTableLine> table;
    public  Hashtable<String,String> arpTable;
    public Router(String nodeName) {
		super(nodeName);
		setType(NodeTypes.ROUTER);
		table = new Hashtable<String, RouterTableLine>();
		arpTable = new  Hashtable<String,String>();
		// TODO Auto-generated constructor stub
	}
    
    
    public void addTableLine(String dst,int port,String nextHop){
    	RouterTableLine tbline = new RouterTableLine(dst, port, nextHop);
    	table.put(dst,tbline );
    }
    
    public RouterTableLine getTableLine(String net){
    	return table.get(net);
    }
    
    





}
