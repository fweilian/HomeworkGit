package fan.gae.learning.discuss;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "BBSGroupInfo")
public class BBSGroupInfo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long GroupId;

	private String UserId;
	private String GroupName;
	private String GroupIntroduce;
	private String type;

	private int GroupNumber;
	private String[] GroupMember;
	private Date CreateTime;
	private Date ChangeTime;

	public String getUserId() {
		return UserId;
	}

	public void setUserId(String userId) {
		UserId = userId;
	}

	public String getGroupName() {
		return GroupName;
	}

	public void setGroupName(String groupName) {
		GroupName = groupName;
	}

	public String getGroupIntroduce() {
		return GroupIntroduce;
	}

	public void setGroupIntroduce(String groupIntroduce) {
		GroupIntroduce = groupIntroduce;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getGroupNumber() {
		return GroupNumber;
	}

	public void setGroupNumber(int groupNumber) {
		GroupNumber = groupNumber;
	}

	public String[] getGroupMember() {
		return GroupMember;
	}

	public void setGroupMember(String[] groupMember) {
		GroupMember = groupMember;
	}

	public Date getCreateTime() {
		return CreateTime;
	}

	public void setCreateTime(Date createTime) {
		CreateTime = createTime;
	}

	public Date getChangeTime() {
		return ChangeTime;
	}

	public void setChangeTime(Date changeTime) {
		ChangeTime = changeTime;
	}

	public Long getGroupId() {
		return GroupId;
	}

}
