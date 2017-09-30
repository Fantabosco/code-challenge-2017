package it.reply.challenge.fantabosco;

public class Node {
	
	private String name;
	private NodeState state;
	
	public Node(String name, NodeState state) {
		this.name = name;
		this.state = state;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public NodeState getState() {
		return state;
	}

	public void setState(NodeState state) {
		this.state = state;
	}
	
	@Override
	public String toString() {
		return name;
	}
}
