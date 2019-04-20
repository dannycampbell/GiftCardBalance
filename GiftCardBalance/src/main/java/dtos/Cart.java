package dtos;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


public class Cart {
	public Cart(List<Item> cartItems, BigDecimal total) {
		super();
		this.cartItems = cartItems;
		this.total = total;
	}

	private List<Item> cartItems = new ArrayList();
	private BigDecimal total;

	public String toString() {
		StringBuilder builder = new StringBuilder();

		builder.append(cartItems.get(0)+", "+cartItems.get(1));
		
		return builder.toString();

	}

	public BigDecimal getTotal() {
		return total;
	}
}