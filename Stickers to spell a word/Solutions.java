import java.util.*;
import java.io.*;
import java.lang.*;

public class Solutions{
	private HashMap<String, Integer> stringToMinStickersMap;

	public Solutions(){
		stringToMinStickersMap 	= new HashMap<String, Integer>();
	}

	public void setStringToMinStickersMap(String str, int x){
		this.stringToMinStickersMap.put(str, x);
	}

	public boolean hasKey(String key){
		return this.stringToMinStickersMap.containsKey(key);
	}

	public int getValueFromKey(String key){
		return this.stringToMinStickersMap.get(key);
	}

	private String sortString(String str){
		char[] toSort 	= str.toCharArray();
		Arrays.sort(toSort);
		return String.valueOf(toSort);
	}

	private String removeCharFromString(char c, String str){
		String charToString = Character.toString(c);
		return str.replaceFirst(charToString, "");
	}

	private String removeString(String toSubtract, String original){
		String newStr = original;
		for(char eachChar : toSubtract.toCharArray()){
			newStr = this.removeCharFromString(eachChar, newStr);
		}
		return newStr;
	}

	private boolean possibleToFormWord(String[] stickers, String target){
		HashSet<Character> hs 	= new HashSet<Character>();
		for(String sticker : stickers){
			for(char eachChar : sticker.toCharArray()){
				hs.add(eachChar);
			}
		}
		for(char eachChar : target.toCharArray()){
			if(!hs.contains(eachChar)) return false;
		}
		return true;
	}

	private int getMinStickers(String[] stickers, String sortedTarget){
		if(this.hasKey(sortedTarget)){
			return this.getValueFromKey(sortedTarget);
		}else if(sortedTarget.equals("")){
			return 0;
		}

		int minCount = Integer.MAX_VALUE;

		for(String sticker : stickers){
			String subString = this.removeString(sticker, sortedTarget);
			if(!subString.equals(sortedTarget)){
				minCount = Math.min(minCount, 1 + getMinStickers(stickers, subString));
			}
		}
		this.setStringToMinStickersMap(sortedTarget, minCount);
		return minCount;
	}

	public int minStickers(String[] stickers, String target){
		if(!this.possibleToFormWord(stickers, target)) return -1;
		String sortedTarget = this.sortString(target);
		this.getMinStickers(stickers, sortedTarget);
		return this.getValueFromKey(sortedTarget);
	}

	public static void main(String[] args){
		String[] stickers 	= {"letter","gold","give","fish","them","system","four","say","sudden","laugh","dear","still","boat","apple","thought","horse","from","prepare","how","provide","pay","people","three","to","hot","card","above","save","division","notice","complete","rain","band","game","mouth","decide","segment","him","bank","cool","term","life","against","motion","edge","nose","clean","enter","operate","where"};
		String target 		= "lessremember";
		Solutions sol 		= new Solutions();
		long startTime = System.currentTimeMillis();
		System.out.println(sol.minStickers(stickers, target));
		long endTime   = System.currentTimeMillis();
		long totalTime = endTime - startTime;
		System.out.println(totalTime);
	}
}