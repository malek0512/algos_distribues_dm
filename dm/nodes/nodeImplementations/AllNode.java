package projects.dm.nodes.nodeImplementations;

import java.awt.Color;
import java.util.LinkedList;
import java.util.List;

import projects.dm.nodes.messages.AskMessage;
import projects.dm.nodes.messages.ReplyMessage;

import sinalgo.configuration.WrongConfigurationException;
import sinalgo.nodes.Connections;
import sinalgo.nodes.Node;
import sinalgo.nodes.messages.Inbox;
import sinalgo.nodes.messages.Message;

public class AllNode extends sinalgo.nodes.Node {

	public static List<AllNode> nodeList= new LinkedList<AllNode>();
	private static int counter =0;
	public boolean resultat;
	public int l, n;
	
	@Override
	public void handleMessages(Inbox inbox) {
		while(inbox.hasNext()) {
			sinalgo.nodes.messages.Message msg = inbox.next();
			if (msg instanceof AskMessage) {
				AskMessage walker = (AskMessage) msg;
				int idp = walker.getMsgId();
				int lp	= walker.getMsgL();
				if (idp == this.ID) {
					this.resultat = true;
				} else if (lp > 0) {
					if (idp > this.ID) {
						List<Node> redList = new LinkedList<Node>();
						redList.add(inbox.getSender());
						Node nextNode = nextNodeExcept(outgoingConnections, redList );
						walker.setMsgL(lp-1);
						send(walker, nextNode);
						System.out.println(this + " received message " + walker + " and sends it now (with l-1) to " + nextNode);
					} else {
						resultat = false;
					}
				} else { //(lp == 0)
					if (idp > this.ID) {
						Node nextNode = inbox.getSender();
						send(new ReplyMessage(idp), nextNode);
						System.out.println(this + " received message " + walker + " and sends it now to " + nextNode);
					} else
						resultat = false;
				}
				
				
			} else if (msg instanceof ReplyMessage) {
				ReplyMessage walker = (ReplyMessage) msg;
				int idp = walker.getMsgId();
				
				if (idp != this.ID) {
					List<Node> redList = new LinkedList<Node>();
					redList.add(inbox.getSender());
					Node nextNode = nextNodeExcept(outgoingConnections, redList );
					send(walker, nextNode);
					System.out.println(this + " received message " + walker + " and sends it now to " + nextNode);
				} else if (idp == this.ID) {
					n = n+1;
					if (n==2) {
						n = 0;
						l = l * 2;
						Node g = nextNode(outgoingConnections, 0);
						Node d = nextNode(outgoingConnections, 1);
						
						send(new AskMessage(ID, l-1), d);
						send(new AskMessage(ID, l-1), g);
						System.out.println(this + " received message " + walker + " and sends new AskMsg now to " + g + " and "+d);
					}
				}
			}
		}
	}

	@Override
	public void preStep() {
		// TODO Auto-generated method stub

	}

	@Override
	public void init() {
		setColor(Color.GREEN);
		nodeList.add(this);
//		(new InitTimer(this)).startRelative(InitTimer.timerRefresh, this); 	
		this.ID = counter++;
		l = 1;
		n = 0;
	}

	public void InitiatingMessages () {
		// TODO Auto-generated method stub
		Message d = new AskMessage(this.ID, this.l-1);
		Message g = new AskMessage(this.ID, this.l-1);
		
		Node ng, nd;
		if ((ng = nextNode(outgoingConnections, 0))==null) {
			System.out.println("Node ng est null");
		}
		if ((nd = nextNode(outgoingConnections, 1))==null) {
			System.out.println("Node nd est null");
		}
		System.out.println(this + " is sending now message " + ng + " and " + nd);
		System.out.println(outgoingConnections);
		send(g, ng);
		send(d, nd);
	}
	
	
	public Node nextNode (Connections neighbors, int n) {
		int degree = neighbors.size();
		if (degree == 0) throw new RuntimeException("no neighbor");
		
		sinalgo.tools.storage.ReusableListIterator<sinalgo.nodes.edges.Edge> iter = neighbors.iterator();
		Node node = iter.next().endNode;

		for (int i = 1; i <= degree; i++){
			if (i == n)
				node = iter.next().endNode;
		}
		return node;
	}
	
	public Node nextNodeExcept (Connections neighbors, List<Node> nodeList) {
		int degree = neighbors.size();
		if (degree == 0) throw new RuntimeException("no neighbor");
		
		sinalgo.tools.storage.ReusableListIterator<sinalgo.nodes.edges.Edge> iter = neighbors.iterator();
		
		for (int i = 0; i <= degree; i++){
			Node node = iter.next().endNode;
			if (! nodeList.contains(node))
				return node;
		}

//		return null;
		throw new RuntimeException("no neighbor left");
	}
	
	@Override
	public void neighborhoodChange() {
		// TODO Auto-generated method stub

	}

	@Override
	public void postStep() {
		// TODO Auto-generated method stub

	}

	@Override
	public void checkRequirements() throws WrongConfigurationException {
		// TODO Auto-generated method stub

	}

}
