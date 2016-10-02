package com.srsoft.model;

public class UserEx extends User {
	Integer index;
	String companyName;
	String departmentName;
	String roleName;
	String workTimeName;

	public Integer getIndex() {
		return index;
	}

	public void setIndex(Integer index) {
		this.index = index;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getWorkTimeName() {
		return workTimeName;
	}

	public void setWorkTimeName(String workTimeName) {
		this.workTimeName = workTimeName;
	}
	
}
