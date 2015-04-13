package fan.gae.learning.user;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity(name = "UserInfo")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class UserInfo implements UserInterface {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long UserId;
	
	private String AutoId;
	private String UserName;
	
	private String Password;
	private int Role; // //visitor: 0 learner: 1 teacher: 2 administer: 3
	private String Email;
	private Date RegisterTime;
	private int VisitTimes;

	//@Basic
	//private User user;

	public UserInfo(String autoId,String userName, String password, int role,
			String email, Date registerTime, int visitTimes) {
		super();
		//UserId = userId;
		AutoId = autoId;
		UserName = userName;
		Password = password;
		Role = role;
		Email = email;
		RegisterTime = registerTime;
		VisitTimes = visitTimes;
	}

	public UserInfo() {

	}

	public UserInfo(Long UserId) {
		this.UserId = UserId;
	}

	/*
	 * @Override public void setUserId(String UserId) { this.UserId = UserId; }
	 */

	public Long getUserId() {
		return UserId;
	}

	
	
	public String getAutoId() {
		return AutoId;
	}

	public void setAutoId(String autoId) {
		AutoId = autoId;
	}

	@Override
	public void setUserName(String UserName) {
		this.UserName = UserName;
	}

	@Override
	public String getUserName() {
		return UserName;
	}

	@Override
	public void setPassword(String Password) {
		this.Password = Password;
	}

	@Override
	public String getPassword() {
		return Password;
	}

	@Override
	public void setRole(int Role) {
		this.Role = Role;
	}

	@Override
	public int getRole() {
		return Role;
	}

	@Override
	public void setEmail(String Email) {
		this.Email = Email;
	}

	@Override
	public String getEmail() {
		return Email;
	}

	@Override
	public void setRegisterTime(Date RegisterTime) {
		this.RegisterTime = RegisterTime;
	}

	@Override
	public Date getRegisterTime() {
		return RegisterTime;
	}

	@Override
	public void setVisitTimes(int VisitTimes) {
		this.VisitTimes = VisitTimes;
	}

	@Override
	public int getVisitTime() {
		return VisitTimes;
	}

	/*public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public static UserInfo getInfoForUser(User user) {
		UserInfo userInfo = null;

		EntityManager em = EMFService.get().createEntityManager();
		try {
			userInfo = em.find(UserInfo.class, user.getUserId());
			if (userInfo == null) {
				userInfo = new UserInfo(user.getUserId());
				userInfo.setUser(user);
			}
		} finally {
			em.close();
		}
		return userInfo;
	}
	/*
	 * public void save() { EntityManager em =
	 * EMFService.get().createEntityManager(); try { em.persist(this); } finally
	 * { em.close(); } }
	 */

}
