package mobidever.godutch.model;

import java.util.Date;

public class ModelUser {
	//�û�������ID
	private int mUserID;
	//�û�����
	private String mUserName;
	//�������
	private Date mCreateDate = new Date();
	//״̬ 0ʧЧ 1����
	private int mState  = 1;
	
	public int getUserID() {
		return mUserID;
	}
	public void setUserID(int pUserID) {
		this.mUserID = pUserID;
	}
	public String getUserName() {
		return mUserName;
	}
	public void setUserName(String pUserName) {
		this.mUserName = pUserName;
	}
	public Date getCreateDate() {
		return mCreateDate;
	}
	public void setCreateDate(Date pCreateDate) {
		this.mCreateDate = pCreateDate;
	}
	public int getState() {
		return mState;
	}
	public void setState(int pState) {
		this.mState = pState;
	}
}
