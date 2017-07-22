import java.util.*;

public class WeightComparator implements Comparator<Items>{
	@Override
	public int compare(Items item_a, Items item_b){
		return item_a.getWeight() - item_b.getWeight();
	}
}