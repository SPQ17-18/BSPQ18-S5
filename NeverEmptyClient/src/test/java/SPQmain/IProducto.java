package SPQmain;

public interface IProducto {

	/**
	 * Adds a product to this product.
	 */
	public abstract IProducto add(IProducto p);
	
	/**
	 * Adds a simple Product to this product. This is a helper method for
	 * implementing double dispatch
	 */
	public abstract IProducto addMoney(Producto p);
	
	/**
	 * Adds a MoneyBag to this money. This is a helper method for
	 * implementing double dispatch.
	 */
	public abstract IProducto addMoneyBag(MoneyBag s);
}
