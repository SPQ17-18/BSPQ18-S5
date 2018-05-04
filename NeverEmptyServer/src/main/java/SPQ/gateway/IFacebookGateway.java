package SPQ.gateway;

public interface IFacebookGateway {
	public String register(String email, String password);
	public String login(String email, String password);
}
