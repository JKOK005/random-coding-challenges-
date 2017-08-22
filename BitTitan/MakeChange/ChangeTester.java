import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ChangeTester{
	MakeChange changer;

	@Before
	public void setup(){
		changer 	= new MakeChange();
	}

	@Test
	public void emptySet(){
		// Sample with sorted coins
		int[] coins 	= {};
		int total 		= 4;
		Assert.assertEquals(0, changer.getMaxPerm(total, coins));
	}

	@Test
	public void unfeasibleSet_A(){
		// Case where total is less than min of set
		int[] coins 	= {30,20,10};
		int total 		= 5;
		Assert.assertEquals(0, changer.getMaxPerm(total, coins));
	}

	@Test
	public void unfeasibleSet_B(){
		// No way to make change at all
		int[] coins 	= {5,7,11};
		int total 		= 13;
		Assert.assertEquals(0, changer.getMaxPerm(total, coins));
	}

	@Test
	public void test_A(){
		// Sample with sorted coins
		int[] coins 	= {1,2,3};
		int total 		= 4;
		Assert.assertEquals(4, changer.getMaxPerm(total, coins));
	}

	@Test
	public void test_B(){
		// Sample with unsorted coins
		int[] coins 	= {3,2,1};
		int total 		= 4;
		Assert.assertEquals(4, changer.getMaxPerm(total, coins));
	}

	@Test
	public void test_C(){
		// Sample with sorted coins
		int[] coins 	= {2,4,5};
		int total 		= 10;
		Assert.assertEquals(4, changer.getMaxPerm(total, coins));
	}
}

