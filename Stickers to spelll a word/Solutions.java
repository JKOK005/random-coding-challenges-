import java.util.*;
import java.io.*;
import java.lang.*;

public class Solutions{
	private HashMap<String, int> stringToMinStickersMap;

	public Solutions(){
		stringToMinStickersMap 	= new HashMap<String, int>();
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

	public String sortString(String str){
		return Arrays.sort(str);
	}

	public String removeString(String toSubtract, String original){
		String newStr = original;
		for(String eachChar : toSubtract){
			newStr = newStr.replaceFirst(eachChar, "");
		}
		return newStr;
	}

	public int getMinStickers(String[] stickers, String sortedTarget){
		if(this.hasKey(sortedTarget)){
			return this.getValueFromKey(sortedTarget);
		}else if(sortedTarget.equals("")){
			return 0;
		}

		int minCount 	= INT_MAX;

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
		// Check if we can get all alphaberts of the target, else return -1

		String sortedTarget = this.sortString(target);
		this.getMinStickers(stickers, sortedTarget);
		return this.getValueFromKey(sortedTarget);
	}

	public static void main(String[] args){

	}
}