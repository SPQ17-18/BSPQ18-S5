package SPQ.gateway;

import SPQ.dto.PaymentDTO;

public interface IPayPalGateway {
	public boolean pay(PaymentDTO paymentDTO);
}
