package fan.gae.learning.discuss;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "TopicInfo")
public class TopicInfo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long TopicId;

	private String GroupName;
	private String UserId;
	private String TopicTitle;
	private String TopicContent;
	private int ReplyNumber;
	private boolean Owner;
	private Date TopicLeaseTime;

	public String getGroupName() {
		return GroupName;
	}

	public void setGroupName(String groupName) {
		GroupName = groupName;
	}

	public String getUserId() {
		return UserId;
	}

	public void setUserId(String userId) {
		UserId = userId;
	}

	public String getTopicTitle() {
		return TopicTitle;
	}

	public void setTopicTitle(String topicTitle) {
		TopicTitle = topicTitle;
	}

	public String getTopicContent() {
		return TopicContent;
	}

	public void setTopicContent(String topicContent) {
		TopicContent = topicContent;
	}

	public int getReplyNumber() {
		return ReplyNumber;
	}

	public void setReplyNumber(int replyNumber) {
		ReplyNumber = replyNumber;
	}

	public boolean isOwner() {
		return Owner;
	}

	public void setOwner(boolean owner) {
		Owner = owner;
	}

	public Date getTopicLeaseTime() {
		return TopicLeaseTime;
	}

	public void setTopicLeaseTime(Date topicLeaseTime) {
		TopicLeaseTime = topicLeaseTime;
	}

	public Long getTopicId() {
		return TopicId;
	}

}
