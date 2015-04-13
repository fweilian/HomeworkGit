package fan.gae.learning.factory;

import java.util.Date;
import java.util.List;

import fan.gae.learning.discuss.TopicInfo;

public abstract class TopicInfoFactory {

	public abstract boolean AddTopic(String GroupName,String UserId,String TopicTitle,String TopicContent,Date TopicLeastTime);
	public abstract boolean AddReply(String GroupName,String UserId,String TopicTitle,String TopicContent,Date TopicLeastTime);
	
	public abstract List<TopicInfo> getTopic(String GroupName,String UserId,String TopicTitle);
	public abstract List<TopicInfo> getTopic(boolean Owner);
}
