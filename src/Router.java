

import java.util.Hashtable;



public class Router extends GenericNode {
    

	// tabela de rotas
    private Hashtable<String,RouterTableLine> table;
    
    public Router(String nodeName) {
		super(nodeName);
		setType(NodeTypes.ROUTER);
		table = new Hashtable<String, RouterTableLine>();
		// TODO Auto-generated constructor stub
	}
    
    
    public void addTableLine(String dst,int port,String nextHop){
    	RouterTableLine tbline = new RouterTableLine(dst, port, nextHop);
    	table.put(dst,tbline );
    }





}
