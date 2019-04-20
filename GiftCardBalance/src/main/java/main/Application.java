package main;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import dtos.Cart;
import dtos.Item;

public class Application {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		String fileName = args[0];
		String giftCardBalanceString = args[1];
		
		Cart bestCart = new Cart(new ArrayList<Item>(), BigDecimal.ZERO);

		BigDecimal giftcardBalance = new BigDecimal(giftCardBalanceString);
		List<Item> itemList = getPossibleItems(fileName, giftcardBalance);
		BigDecimal bestPrice = BigDecimal.ZERO;
		for (Item product : itemList) {
			for (Item productInner : itemList) {
				if (product.getName().equalsIgnoreCase(productInner.getName())) {
					continue;
				}
				BigDecimal newPrice = product.getPrice().add(productInner.getPrice());
				if (newPrice.compareTo(bestPrice) > 0 && newPrice.compareTo(giftcardBalance) < 0) {
					bestCart = new Cart(Arrays.asList(product, productInner), newPrice);

				}

			}

		}
		System.out.println(bestCart);
	}

	private static List<Item> getPossibleItems(String fileName, BigDecimal giftcardBalance)
			throws FileNotFoundException, IOException {
		FileReader input = new FileReader(fileName);
		BufferedReader bufRead = new BufferedReader(input);
		String myLine = null;
		List<Item> itemList = new ArrayList<Item>();
		while ((myLine = bufRead.readLine()) != null) {
			String[] array = myLine.split(",");
			BigDecimal price = new BigDecimal(array[1]);
			if (!(price.compareTo(giftcardBalance) < 0)) {
				// if the list is always going to be sorted and we need to select multiple items
				// and assuming this store doesn't sell things for free or for a negative price
				// we can ignore any prices equal to or above the giftcard balance to save on
				// performance
				break;
			} else {
				itemList.add(new Item(array[0], price));
			}
		}
		return itemList;
	}

}
