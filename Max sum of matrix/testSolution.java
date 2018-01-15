import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.After;
import java.util.*;
import java.io.*;
import java.lang.*;

public class testSolution{
	private Solutions sol;

	private InputStream stringToStream(String str){
		return new ByteArrayInputStream(str.getBytes());
	}; 

	private ArrayList<ArrayList<Integer>> stringToMatrix(String str){
		InputStream is 	= stringToStream(str);
		Scanner sc 		= new Scanner(is);
		ArrayList<ArrayList<Integer>> matrix = new ArrayList<ArrayList<Integer>>();
		while(sc.hasNextLine()){
			String[] line 			= sc.nextLine().split("\\s+");
			ArrayList<Integer> tmp 	= new ArrayList<Integer>();
			for(String entry : line){
				tmp.add(Integer.parseInt(entry));
			}
			matrix.add(tmp);
		}
		return matrix;
	}
	
	@Before
	public void init(){
		sol = new Solutions();
	}

	@Test
	public void test1By3Matrix(){
		String matrixString = 	"1 0 1\n";
		ArrayList<ArrayList<Integer>> matrix = stringToMatrix(matrixString);
		Assert.assertEquals(sol.solve(matrix), 2);
	}

	@Test
	public void test3By3Matrix(){
		String matrixString = 	"-1 0 1\n" + 
								"1 0 1\n" + 
								"1 0 1\n";
		ArrayList<ArrayList<Integer>> matrix = stringToMatrix(matrixString);
		Assert.assertEquals(sol.solve(matrix), 4);
	}

	@Test
	public void test1By1Matrix(){
		String matrixString = 	"5\n";
		ArrayList<ArrayList<Integer>> matrix = stringToMatrix(matrixString);
		Assert.assertEquals(sol.solve(matrix), 5);
	}
}