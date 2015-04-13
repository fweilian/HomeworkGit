package fan.gae.learning.factory;

import java.util.Date;
import java.util.List;

import fan.gae.learning.user.UserInfo;

public abstract class UserFactory {
	// operation user

	public abstract boolean AddUser(String autoId, String username,
			String password, int Role, String email, Date date, int visittimes);

	public abstract boolean ChangeUser(UserInfo user,String username,String password,String email);

	public abstract boolean changeRole(UserInfo user,int role);
	
	public abstract List<UserInfo> getUser(String UserId); // userId,...???

	public abstract boolean isCheck(String UserName);

	public abstract boolean DeleteUser(String UserName);

	public abstract List<UserInfo> UserInfo();

	// public abstract User List(String UserId); // return a user info
}
