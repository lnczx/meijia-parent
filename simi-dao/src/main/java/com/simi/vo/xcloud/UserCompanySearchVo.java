package com.simi.vo.xcloud;

public class UserCompanySearchVo{
	
	private Long companyId;
	
	private Long deptId;
	
	private Long userId;
	
	private Long staffId;
	
	private Long benzId;
	
	private Short status;
	
	private String jobNumber;
	
	public Long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

	public Long getDeptId() {
		return deptId;
	}

	public void setDeptId(Long deptId) {
		this.deptId = deptId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Short getStatus() {
		return status;
	}

	public void setStatus(Short status) {
		this.status = status;
	}

	public String getJobNumber() {
		return jobNumber;
	}

	public void setJobNumber(String jobNumber) {
		this.jobNumber = jobNumber;
	}

	public Long getBenzId() {
		return benzId;
	}

	public void setBenzId(Long benzId) {
		this.benzId = benzId;
	}

	public Long getStaffId() {
		return staffId;
	}

	public void setStaffId(Long staffId) {
		this.staffId = staffId;
	}
}