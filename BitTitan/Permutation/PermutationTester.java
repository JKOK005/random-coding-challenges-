import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class PermutationTester{
	Permutation perm;

	@Before 
	public void setup(){
		perm 	= new Permutation();
	}

	@Test
	public void nullString(){
		// Test for an empty string
		String str_A 	= "";
		String str_B 	= "asd";
		Assert.assertEquals(false, perm.isPerm(str_A, str_B));
	}

	@Test
	public void diffLength(){
		// Test for different length strings
		String str_A 	= "aaa";
		String str_B 	= "aaaa";
		Assert.assertEquals(false, perm.isPerm(str_A, str_B));
	}

	@Test
	public void PermutationFalse(){
		// Test for false permutation 
		String str_A 	= "123";
		String str_B 	= "322";
		Assert.assertEquals(false, perm.isPerm(str_A, str_B));
	}

	@Test
	public void PermutationTrueUnique(){
		// Test for all unique chars
		String str_A 	= "asdqwe";
		String str_B 	= "dqwaes";
		Assert.assertEquals(true, perm.isPerm(str_A, str_B));
	}

	@Test
	public void PermutationTrueDuplicate(){
		// Test for chars which are duplicated
		String str_A 	= "assdddqqqqwwwwweeeeee";
		String str_B 	= "eeeeeewwwwwqqqqdddssa";
		Assert.assertEquals(true, perm.isPerm(str_A, str_B));
	}

	@Test
	public void PermutationTrueSymbols(){
		// Test for non char
		String str_A 	= "!@#$%^";
		String str_B 	= "#$@%!^";
		Assert.assertEquals(true, perm.isPerm(str_A, str_B));
	}
}