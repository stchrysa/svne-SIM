package model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import tools.LinkComparator;
import tools.NodeComparator;
import model.components.Link;
import model.components.Node;
import model.components.RequestLink;
import model.components.RequestRouter;
import model.components.RequestSwitch;
import model.components.VirtualMachine;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.util.EdgeType;
import edu.uci.ics.jung.graph.util.Pair;

/**
 * Request Class. Subclass of Network.
 */
public class Request extends Network {
	
	private String domain; 
	private int startDate;
	private int endDate;
	private String prov;
	
	/** Not used yet **/
	private float resiliency;
	/** Not used yet **/
	private boolean splittable;
	private ResourceMapping rmap;
	
	
    /** Creates a new instance of Substrate */
    public Request(String id) {
    	super(id);
    	nodeFactory = new RequestNodeFactory();
    	linkFactory = new RequestLinkFactory();
    	/** Setting default values **/
    	resiliency = 0;
    	splittable = false;
    	this.rmap= new ResourceMapping(this);
    }

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public int getStartDate() {
		return startDate;
	}
	

	public void setStartDate(int startDate) {
		this.startDate = startDate;
	}
	
	public String getProv(){
		return prov;
	}
	
	public void setProv(String prov){
		this.prov=prov;
	}

	public int getEndDate() {
		return endDate;
	}

	public void setEndDate(int endDate) {
		this.endDate = endDate;
	}
	
	public void constructJRA23Request() {
		startDate = 0;
	    endDate = 12;
	    // Nodes and links of the request are generated by random factories
	    RequestNodeFactory nodeFactory = new RequestNodeFactory();
	    RequestLinkFactory linkFactory = new RequestLinkFactory();
	    this.setNodeFactory(nodeFactory);
	    this.setLinkFactory(linkFactory);
	     
	    // router0
	    RequestRouter r0 = (RequestRouter) nodeFactory.create("router");
	    // router1
	    RequestRouter r1 = (RequestRouter) nodeFactory.create("router");
	    // sw0
	    RequestSwitch sw0 = (RequestSwitch) nodeFactory.create("switch");
	    // sw1
	    RequestSwitch sw1 = (RequestSwitch) nodeFactory.create("switch");
	    // sw2
	    RequestSwitch sw2 = (RequestSwitch) nodeFactory.create("switch");
	    // vm0
	    VirtualMachine vm0 = (VirtualMachine) nodeFactory.create("virtualMachine");
	    // vm1
	    VirtualMachine vm1 = (VirtualMachine) nodeFactory.create("virtualMachine");
	    // vm2
	    VirtualMachine vm2 = (VirtualMachine) nodeFactory.create("virtualMachine");
	    // vm3
	    VirtualMachine vm3 = (VirtualMachine) nodeFactory.create("virtualMachine");
	    // vm4
	    VirtualMachine vm4 = (VirtualMachine) nodeFactory.create("virtualMachine");
	    // vm5
	    VirtualMachine vm5 = (VirtualMachine) nodeFactory.create("virtualMachine");
	    // vm6
	    VirtualMachine vm6 = (VirtualMachine) nodeFactory.create("virtualMachine");
	    // vm7
	    VirtualMachine vm7 = (VirtualMachine) nodeFactory.create("virtualMachine");

	    // Adding Links with random bw
	    //vm0-sw0
	    RequestLink requestLink = (RequestLink) linkFactory.create();
	    graph.addEdge(requestLink,new Pair<Node>(vm0,sw0), EdgeType.UNDIRECTED);
	    //vm1-sw0
	    requestLink = (RequestLink) linkFactory.create();
	    graph.addEdge(requestLink,new Pair<Node>(vm1,sw0), EdgeType.UNDIRECTED);
	    //vm2-sw1
	    requestLink = (RequestLink) linkFactory.create();
	    graph.addEdge(requestLink,new Pair<Node>(vm2,sw1), EdgeType.UNDIRECTED);
	    //vm3-sw1
	    requestLink = (RequestLink) linkFactory.create();
	    graph.addEdge(requestLink,new Pair<Node>(vm3,sw1), EdgeType.UNDIRECTED);
	    //vm4-sw2
	    requestLink = (RequestLink) linkFactory.create();
	    graph.addEdge(requestLink,new Pair<Node>(vm4,sw2), EdgeType.UNDIRECTED);
	    //vm5-sw2
	    requestLink = (RequestLink) linkFactory.create();
	    graph.addEdge(requestLink,new Pair<Node>(vm5,sw2), EdgeType.UNDIRECTED);
	    //vm6-r1
	    requestLink = (RequestLink) linkFactory.create();
	    graph.addEdge(requestLink,new Pair<Node>(vm6,r1), EdgeType.UNDIRECTED);
	    //vm7-r1
	    requestLink = (RequestLink) linkFactory.create();
	    graph.addEdge(requestLink,new Pair<Node>(vm7,r1), EdgeType.UNDIRECTED);
	    //r0-r1
	    requestLink = (RequestLink) linkFactory.create();
	    graph.addEdge(requestLink,new Pair<Node>(r0,r1), EdgeType.UNDIRECTED);
	    //r0-sw0
	    requestLink = (RequestLink) linkFactory.create();
	    graph.addEdge(requestLink,new Pair<Node>(r0,sw0), EdgeType.UNDIRECTED);
	    //r0-sw1
	    requestLink = (RequestLink) linkFactory.create();
	    graph.addEdge(requestLink,new Pair<Node>(r0,sw1), EdgeType.UNDIRECTED);
	    //r0-sw2
	    requestLink = (RequestLink) linkFactory.create();
	    graph.addEdge(requestLink,new Pair<Node>(r0,sw2), EdgeType.UNDIRECTED);
	}
	
	public Object getCopy() {
    	Request r = new Request(this.getId());
    	r.state = this.state;
    	r.nodeFactory = (RequestNodeFactory) ((RequestNodeFactory) this.nodeFactory).getCopy();
    	r.linkFactory = (RequestLinkFactory) ((RequestLinkFactory) this.linkFactory).getCopy();
    	r.graph = ((NetworkGraph) this.graph).getCopy();
    	r.graphLayout = this.graphLayout;
    	r.domain = this.domain;
    	r.resiliency = this.resiliency;
    	r.splittable = this.splittable;
    	r.startDate = this.startDate;
    	r.endDate = this.endDate;
    	return r;
    }
	
	
	 public void setRMap (ResourceMapping r){ this.rmap=r; }
	    public ResourceMapping getRMap (){ return  this.rmap; }
	    
	    
	    @SuppressWarnings("unchecked")
		public void print(){
			ArrayList<Node> nodes =(ArrayList<Node>)getNodes(this.getGraph());
			ArrayList<Link> links =(ArrayList<Link>) getLinks(this.getGraph());
			Collections.sort(nodes,new NodeComparator());
			Collections.sort(links,new LinkComparator());
			
			System.out.println("****************************Requested Nodes**************************");
			
			
			for (Node current : nodes){
				System.out.print("["  +  current.getId() + ": ");
			if ((current) instanceof VirtualMachine  )  
				System.out.println(((VirtualMachine)current).getCpu()+" "+ ((VirtualMachine)current).getMemory()+" "+((VirtualMachine)current).getDiskSpace()+"]");	
		    else if (((current) instanceof RequestRouter) )
		    	System.out.println( "[ 1 ]");
			}

			System.out.println("****************************Requested Links**************************");
			for (Link current : links){
				Pair<Node> nodesOfLink = this.getGraph().getEndpoints(current);
				System.out.println("Link: " + current.getId()+ ": " +current.getBandwidth() + " SRC:  " +nodesOfLink.getFirst() + " DEST: "+ nodesOfLink.getSecond());
			}
				
			
		}
	    
	    public ArrayList<Link> getLinks(Graph<Node,Link> t) {
			ArrayList<Link> reqLink =new ArrayList<Link>();
			Collection<Link> edges =  t.getEdges();

			for (Link current : edges)
				reqLink.add(current);
			
			return reqLink;
		}
		
		public ArrayList<Node> getNodes(Graph<Node,Link> t) {
			ArrayList<Node> reqNodes =new ArrayList<Node>();
			Collection<Link> edges =  t.getEdges();

			for (Link current : edges){
				Pair<Node> currentNodes =t.getEndpoints(current);
				if (reqNodes.contains(currentNodes.getFirst())==false)
					reqNodes.add(currentNodes.getFirst());
				if (reqNodes.contains(currentNodes.getSecond())==false)
					reqNodes.add(currentNodes.getSecond());
			}


			return reqNodes;
		}
}
