package User;

public interface IUserBiz {
	public void login() throws Exception;
	public void register() throws Exception;
	public void logout();
	public User getUserById(String userId);
}
