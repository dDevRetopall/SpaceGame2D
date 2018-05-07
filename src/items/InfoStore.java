package items;

import java.io.File;
import java.util.ArrayList;

import proposiciones.ItemId;

public class InfoStore {
	static ArrayList<ItemId>items = new ArrayList<>();
	public static void storeItems(ArrayList<ItemId>items) {
		for(ItemId ii:items) {
			InfoStore.items.add(ii);
		}

	}
	
	public static ArrayList<ItemId> getItems() {
		return items;
	}
	public static void setItems(ArrayList<ItemId> items) {
		InfoStore.items = items;
	}
	public static ItemId getItemByName(String name) {
		System.out.println(items);
		for(ItemId i :items) {
			if(i.getName().equals(name)) {
				return i;
			}
		}
		return null;
		
	}
	
}
