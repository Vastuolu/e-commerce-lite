package User;

public interface IUserBiz {
	public static final String EMAIL_REGEX = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
	public void login() throws Exception;
	public void register() throws Exception;
	public void logout();
	public User getUserById(String userId);
}
