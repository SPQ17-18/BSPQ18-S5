package SPQ.gateway;

import SPQ.dto.PaymentDTO;

public interface IVisaGateway {
	public boolean pay(PaymentDTO paymentDTO);
}
