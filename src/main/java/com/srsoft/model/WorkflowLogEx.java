package com.srsoft.model;

public class WorkflowLogEx extends WorkflowLog {
	Integer index;
	String auditer;
	String nodeName;
	
	public Integer getIndex() {
		return index;
	}

	public void setIndex(Integer index) {
		this.index = index;
	}

	public String getAuditer() {
		return auditer;
	}

	public void setAuditer(String auditer) {
		this.auditer = auditer;
	}

	public String getNodeName() {
		return nodeName;
	}

	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}
	
}
