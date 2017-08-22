import java.util.*;
import java.io.*;
import java.lang.*;

public class Permutation{
	public boolean isPerm(String str_A, String str_B){
		if(str_A.length() != str_B.length()) return false; 		// Permutation lengths must be the same

		HashMap<Character, Integer> hs 	= new HashMap<Character, Integer>();

		for(Character i : str_A.toCharArray()){
			if(hs.containsKey(i)){
				hs.put(i, hs.get(i) +1);
			}else{
				hs.put(i, 1);
			}
		}

		for(Character j : str_B.toCharArray()){
			if(hs.containsKey(j) && hs.get(j) > 0){
				hs.put(j, hs.get(j) -1);			
			}else{
				return false;
			}
		}
		return true;
	}

	public static void main(String[] args){
		String perm1 	= "asd";
		String perm2 	= "dsa";
		Permutation p 	= new Permutation();
		System.out.println(p.isPerm(perm1, perm2));
	}
}