import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.After;

public class TestLRUCache{
	private LRUCache<Integer, Integer> lruIntToInt;
	private LRUCache<String, Integer> lruStringToInt;
	private LRUCache<Integer, String> lruIntToString;
	private LRUCache<String, String> lruStringToString;
	private LRUCache<Integer, Integer> lruWith2Capacity;

	@Before
	public void init(){
		int capacity 		= 5;
		lruIntToInt 		= new LRUCache<Integer, Integer>(capacity);
		lruStringToInt		= new LRUCache<String, Integer>(capacity);
		lruIntToString	 	= new LRUCache<Integer, String>(capacity);
		lruStringToString	= new LRUCache<String, String>(capacity);
		lruWith2Capacity 	= new LRUCache<Integer, Integer>(2);
	}

	// Integer to integer map storage
	@Test
	public void testStorageIntToInt(){
		lruIntToInt.put(10, 20);
		Assert.assertEquals((int) lruIntToInt.get(10),(int) 20);
	}

	@Test
	public void testOverrideOfKeyToValueForIntToInt(){
		lruIntToInt.put(10, 20);
		Assert.assertNotSame((int) lruIntToInt.get(10), (int) 30);
		lruIntToInt.put(10, 30);
		Assert.assertEquals((int) lruIntToInt.get(10), (int) 30);
	}

	// Integer to String map storage
	@Test
	public void testStorageIntToString(){
		lruIntToString.put(10, "value");
		Assert.assertEquals((String) lruIntToString.get(10), (String) "value");
	}

	@Test
	public void testOverrideOfKeyToValueForIntToString(){
		lruIntToString.put(10, "value");
		Assert.assertNotSame((String) lruIntToString.get(10), (String) "new");
		lruIntToString.put(10, "new");
		Assert.assertEquals((String) lruIntToString.get(10), (String) "new");
	}

	// String to Integer map storage
	@Test
	public void testStorageStringToInt(){
		lruStringToInt.put("key", 20);
		Assert.assertEquals((int) lruStringToInt.get("key"),(int) 20);
	}

	@Test
	public void testOverrideOfKeyToValueForStringToInt(){
		lruStringToInt.put("key", 20);
		Assert.assertNotSame((int) lruStringToInt.get("key"), (int) 30);
		lruStringToInt.put("key", 30);
		Assert.assertEquals((int) lruStringToInt.get("key"), (int) 30);
	}

	// String to Integer map storage
	@Test
	public void testStorageStringToString(){
		lruStringToString.put("key", "value");
		Assert.assertEquals((String) lruStringToString.get("key"),(String) "value");
	}

	@Test
	public void testOverrideOfKeyToValueForStringToString(){
		lruStringToString.put("key", "value");
		Assert.assertNotSame((String) lruStringToString.get("key"), (String) "new");
		lruStringToString.put("key", "new");
		Assert.assertEquals((String) lruStringToString.get("key"), (String) "new");
	}

	@Test
	public void testExceedCapacityOfCache(){
		Assert.assertEquals(lruWith2Capacity.isFull(), false);
		lruWith2Capacity.put(10, 20);
		Assert.assertEquals(lruWith2Capacity.isFull(), false);
		lruWith2Capacity.put(11, 21);
		Assert.assertEquals(lruWith2Capacity.isFull(), true);
		lruWith2Capacity.put(12, 22);
		Assert.assertEquals(lruWith2Capacity.isFull(), true);
	}

	@Test 
	public void testNullifyOldestEntryUponExceedCacheCapacity(){
		int start 	= 10;
		int end 	= 12;
		for(int i =start; i <end; i++){
			lruWith2Capacity.put(i,20);
			Assert.assertNotNull(lruWith2Capacity.get(start));
		}
		lruWith2Capacity.put(end,20);
		Assert.assertNull(lruWith2Capacity.get(start));
	}

	@Test 
	public void testReviveOldestEntryBeforeExceedCacheCapacity(){
		int start 	= 10;
		int end 	= 11;
		for(int i =start; i <end; i++){
			lruWith2Capacity.put(i,20);
		}
		lruWith2Capacity.put(10,20);
		Assert.assertNotNull(lruWith2Capacity.get(start));
	}

	@Test
	public void testOverrideCacheWithIncrementalKeyValue(){
		int end = 10;
		for(int i =0; i <end; i ++){
			lruWith2Capacity.put(i, i);
			for(int j=0; j <=i; j++){
				if(i -j < 2){
					Assert.assertEquals((int) lruWith2Capacity.get(j),(int) j);
				}else{
					Assert.assertNull(lruWith2Capacity.get(j));
				}
			}
		}
	}
}