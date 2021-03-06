package com.simi.vo;

public class MsgSearchVo {
	
	private long userId;
	
	private String title;
	
	private short isSend;
	
	private short userType;
	
	private String isSendStr;

	private Short sendAtOnce;	//是否立即发送
	
	
	public Short getSendAtOnce() {
		return sendAtOnce;
	}

	public void setSendAtOnce(Short sendAtOnce) {
		this.sendAtOnce = sendAtOnce;
	}

	public String getTitle() {
		return title = title !=null ? title.trim():new String();
	}

	public void setTitle(String title) {
		this.title = title;
	}
	public short getUserType() {
		return userType;
	}

	public void setUserType(short userType) {
		this.userType = userType;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public short getIsSend() {
		return isSend;
	}

	public void setIsSend(short isSend) {
		this.isSend = isSend;
	}

	public String getIsSendStr() {
		return isSendStr;
	}

	public void setIsSendStr(String isSendStr) {
		this.isSendStr = isSendStr;
	}
	
}
