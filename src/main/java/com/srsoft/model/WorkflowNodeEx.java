package com.srsoft.model;

public class WorkflowNodeEx extends WorkflowNode {
	String receiverName;
	String ccName;
	String ccReceiverId;

	public String getReceiverName() {
		return receiverName;
	}

	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}

	public String getCcName() {
		return ccName;
	}

	public void setCcName(String ccName) {
		this.ccName = ccName;
	}

	public String getCcReceiverId() {
		return ccReceiverId;
	}

	public void setCcReceiverId(String ccReceiverId) {
		this.ccReceiverId = ccReceiverId;
	}
	
}
