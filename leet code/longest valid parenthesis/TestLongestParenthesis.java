import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.After;

public class TestLongestParenthesis{

	private Solutions sol;

	private void checkAnsIsCorrect(String str, int expected){
		int solLength 	= sol.longestValidParentheses(str);
		Assert.assertEquals(solLength, expected);
	}

	@Before	
	public void init(){
		sol 	= new Solutions();
	}

	@Test
	public void testCompleteSeqenceWithoutBreaks(){
		String str 		= "()()()()";
		int maxLength 	= 8;
		checkAnsIsCorrect(str, maxLength);
	}

	@Test
	public void testCompleteNestedSequenceWithoutBreaks(){
		String str 		= "((((()))))";
		int maxLength 	= 10;
		checkAnsIsCorrect(str, maxLength);
	}

	@Test
	public void testCompleteSequenceWith2Breaks(){
		String str 		= "())(()())";
		int maxLength 	= 6;
		checkAnsIsCorrect(str, maxLength);
	}

	@Test
	public void testCompleteSequenceWith3Breaks(){
		String str 		= "()(()((())";
		int maxLength 	= 4;
		checkAnsIsCorrect(str, maxLength);
	}

	@Test
	public void testNoSequence(){
		String str 		= ")))))(((((";
		int maxLength 	= 0;
		checkAnsIsCorrect(str, maxLength);
	}


	@After
	public void end(){
		System.out.println("Ending test");
	}
}