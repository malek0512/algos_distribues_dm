package projects.dm.nodes.messages;

import sinalgo.nodes.messages.Message;

public class ReplyMessage extends Message {

	private static int msgCounter = 0;
	private int msgId;
	public int getMsgId() {
		return msgId;
	}

	public ReplyMessage(int id) {
		super();
//		msgId = msgCounter;
//		msgCounter++;
		msgId = id;
	}
	
	@Override
	public Message clone() {
		// TODO Auto-generated method stub
		return this;
	}

	public String toString() {
		return " reply " + msgId;
	}
}
