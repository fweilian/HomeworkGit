package fan.gae.learning.factory;

import java.util.Date;
import java.util.List;

import fan.gae.learning.discuss.BBSGroupInfo;

public abstract class BBSGroupInfoFactory {

	public abstract boolean AddGroup(String UserId,String GroupName,String GroupIntroduce,int GroupNumber,Date CreateTime,Date ChangeTime);
	
	public abstract List<BBSGroupInfo> getBBSGroup(String GroupName);
	
	public abstract List<BBSGroupInfo> getBBSGroup();
}
