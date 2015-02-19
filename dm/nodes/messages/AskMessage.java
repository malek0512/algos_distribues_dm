package projects.dm.nodes.messages;

import sinalgo.nodes.messages.Message;

public class AskMessage extends Message {

	private static int msgCounter = 0;
	private int msgId;
	
	public int getMsgId() {
		return msgId;
	}

	public int getMsgL() {
		return msgL;
	}

	private int msgL;
	public AskMessage(int id, int l) {
		super();
		msgId = id;
		msgL = l;
//		msgId = msgCounter;
//		msgCounter++;
	}
	
	@Override
	public Message clone() {
		// TODO Auto-generated method stub
		return this;
	}

	public String toString() {
		return " ask " + msgId;
	}

	public void setMsgL(int i) {
		// TODO Auto-generated method stub
		this.msgL = i;;
	}
}
