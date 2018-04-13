package gateway;

public interface IGoogleGateway {
	public String register(String email, String password);
	public String login(String email, String password);
}
