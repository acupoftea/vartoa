package com.srsoft.model;

public class ContentEx extends Content {
	private String catalogName4SmallType;
	private Integer catalogParentId;
	String catalogName4BigType;

	public String getCatalogName4SmallType() {
		return catalogName4SmallType;
	}

	public void setCatalogName4SmallType(String catalogName4SmallType) {
		this.catalogName4SmallType = catalogName4SmallType;
	}

	public Integer getCatalogParentId() {
		return catalogParentId;
	}

	public void setCatalogParentId(Integer catalogParentId) {
		this.catalogParentId = catalogParentId;
	}

	public String getCatalogName4BigType() {
		return catalogName4BigType;
	}

	public void setCatalogName4BigType(String catalogName4BigType) {
		this.catalogName4BigType = catalogName4BigType;
	}
	
}
