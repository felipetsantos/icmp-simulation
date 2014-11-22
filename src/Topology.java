/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.Hashtable;



public  class Topology {
  private Hashtable<String,GenericNode> nodes;

  public Topology(){
	  nodes = new Hashtable<String,GenericNode>();
  }
  public void addNode(GenericNode node){
	  nodes.put(node.getName(), node);
  }
  
  public GenericNode getNode(String nodeID){
	  return nodes.get(nodeID);
  }
  

}
