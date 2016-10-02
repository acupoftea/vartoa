package com.srsoft.model;

public class WorkflowForward {
	int nextNode;
	boolean isEndNode;
	boolean isStartNode;
	int preNode;
	
	public int getNextNode() {
		return nextNode;
	}
	public void setNextNode(int nextNode) {
		this.nextNode = nextNode;
	}
	public boolean isEndNode() {
		return isEndNode;
	}
	public void setEndNode(boolean isEndNode) {
		this.isEndNode = isEndNode;
	}
	public boolean isStartNode() {
		return isStartNode;
	}
	public void setStartNode(boolean isStartNode) {
		this.isStartNode = isStartNode;
	}
	public int getPreNode() {
		return preNode;
	}
	public void setPreNode(int preNode) {
		this.preNode = preNode;
	}
	
}
