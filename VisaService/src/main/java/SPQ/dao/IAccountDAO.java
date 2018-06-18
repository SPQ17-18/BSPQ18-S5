package SPQ.dao;

import SPQ.data.Account;

public interface IAccountDAO {
	public boolean pay(Account account, double total);
}
