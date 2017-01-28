package sg.edu.nus.comp.cs3219.control;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

public class MasterControlTest {

	MasterControl master;
	
	@Before
	public void setUp() {
		master = new MasterControl();
	}

	@Test
	public void testExample1() {
		Set<String> ignoreWords = new HashSet<>();
		ignoreWords.add("is");
		ignoreWords.add("the");
		ignoreWords.add("of");
		ignoreWords.add("and");
		ignoreWords.add("as");
		ignoreWords.add("a");
		ignoreWords.add("after");
		
		Set<String> requiredWords = new HashSet<>();
		requiredWords.add("Star");
		requiredWords.add("Space");

		List<String> input = new ArrayList<>();
		input.add("The Day after Tomorrow");
		input.add("Fast and Furious");
		input.add("Man of Steel");
		input.add("Star Trek");
		input.add("2001: a Space Odyssey");

		List<String> result = master.run(input, ignoreWords, requiredWords);

		assertEquals(2, result.size());
		assertEquals("Space Odyssey 2001: a", result.get(0));
		assertEquals("Star Trek", result.get(1));
	}
	
	@Test
	public void testExample2() {
		Set<String> ignoreWords = new HashSet<>();
		ignoreWords.add("is");
		ignoreWords.add("the");
		ignoreWords.add("of");
		ignoreWords.add("and");
		ignoreWords.add("as");
		ignoreWords.add("a");
		ignoreWords.add("after");
		
		Set<String> requiredWords = new HashSet<>();
		requiredWords.add("Day");
		requiredWords.add("Fast");
		requiredWords.add("Man");

		List<String> input = new ArrayList<>();
		input.add("The Day after Tomorrow");
		input.add("Fast and Furious");
		input.add("Man of Steel");
		input.add("Star Trek");
		input.add("2001: a Space Odyssey");

		List<String> result = master.run(input, ignoreWords, requiredWords);

		assertEquals(3, result.size());
		assertEquals("Day after Tomorrow the", result.get(0));
		assertEquals("Fast and Furious", result.get(1));
		assertEquals("Man of Steel", result.get(2));
	}
}

