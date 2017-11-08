package skeleton;

import java.util.HashMap;

public class Adapter {
	public static final String[] BEVERAGES = new String[] {
			"Caffè Americano", "Caffè Mocha", "Caffè Latte", 
			"Cappuccino", "Caramel Macchiato", "Espresso" }; // You can change these

	/**
	* This function compute the edit distance between a string and every 
	* strings in your selected beverage set. The beverage with the minimum 
	* edit distance <= 3 is returned. The use of Wagner_Fischer algorithm
	* is shown in the main function in WagnerFischer.java
	**/
	public String getBeverage(String s){
		// TODO: find the word with minimum edit distance
		int min = 999;
		int selected_index = -1;
		for(int i = 0; i < BEVERAGES.length; ++i){
			WagnerFischer wf = new WagnerFischer(BEVERAGES[i], s);
			int distance = wf.getDistance();
			if(distance <= 3 && distance < min){
				min = distance;
				selected_index = i;
			}
		}
		if(selected_index == -1)
			return null;
		return BEVERAGES[selected_index];
	}
}
