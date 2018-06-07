package SPQ.gateway;

public interface IPayPalGateway {
	public String pay(String email, String password, String price);
}
