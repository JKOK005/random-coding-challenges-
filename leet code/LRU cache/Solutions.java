import java.io.*;
import java.lang.*;
import java.util.*;

public class Solutions{
	public static void main(String[] args){
		LRUCache<Integer, Integer> lru = new LRUCache<Integer, Integer>(4);
		lru.put(10, 20);
		lru.put(11, 21);
		lru.put(12, 22);
		lru.put(13, 23);
		lru.put(14, 24);
		lru.put(15, 25);
		System.out.println(lru.get(10));
		System.out.println(lru.get(11));
		System.out.println(lru.get(12));
		System.out.println(lru.get(13));
		System.out.println(lru.get(14));
		System.out.println(lru.get(15));
	}
}