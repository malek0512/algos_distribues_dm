package projects.dm.nodes.nodeImplementations;

import java.awt.Color;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import projects.dm.nodes.messages.AskMessage;
import projects.dm.nodes.timers.InitTimer;

import sinalgo.configuration.WrongConfigurationException;
import sinalgo.nodes.Node;
import sinalgo.nodes.edges.Edge;
import sinalgo.nodes.messages.Inbox;
import sinalgo.nodes.messages.Message;

public class AllNode extends sinalgo.nodes.Node {

//	public static List<AllNode> nodeList= new LinkedList<AllNode>();
	private static int counter =0;
	public boolean resultat;
	public int l, n;
	
	@Override
	public void handleMessages(Inbox inbox) {
		// TODO Auto-generated method stub

	}

	@Override
	public void preStep() {
		// TODO Auto-generated method stub

	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		setColor(Color.GREEN);
//		(new InitTimer(this)).startRelative(InitTimer.timerRefresh, this); 	
		this.ID = counter++;
		l = 1;
		n = 0;
		Message d = new AskMessage(this.ID, this.l-1);
		Message g = new AskMessage(this.ID, this.l-1);
		System.out.println(this + " is sending now message " + g + " and " + d);
		send(g, toTheLeft());
		send(d, toTheRight());
	}

	public Node toTheLeft () {
		Iterator<Edge> it = outgoingConnections.iterator();
		if (it.hasNext()) {
			return it.next().endNode;
		}
		return null;
	}
	
	public Node toTheRight () {
		if (outgoingConnections.size()>=2) {
			Iterator<Edge> it = outgoingConnections.iterator();
			for(int i=0; i<2; i++) {
				if (i == 2)
					return it.next().endNode;
			}
		}
		return null;
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
