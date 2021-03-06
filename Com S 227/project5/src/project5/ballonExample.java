package project5;

import balloon4.Balloon;
import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.assertEquals;

public class ballonExample {
	private Balloon b;
	
	@Before
	public void setup() {
		b = new Balloon(10);
	}
	@Test
	public void initialTest1() {
		assertEquals(0, b.getRadius());
	}
	
	@Test
	public void initialTest2() {
		assertEquals(false, b.isPopped());
	}
	
	@Test
	public void popBlowTest() {
		b.blow(12);
		assertEquals(0, b.getRadius());
		assertEquals(true, b.isPopped());
	}
	
	@Test
	public void blowTest() {
		b.blow(5);
		b.blow(1);
		b.blow(1);
		b.blow(1);
		b.blow(2);
		assertEquals(10, b.getRadius());
	}
	
	@Test
	public void deflateTest() {
		b.blow(10);
		b.deflate();
		assertEquals(0, b.getRadius());
		b.blow(10);
		assertEquals(10, b.getRadius());
	}
	
	@Test 
	public void popTest() {
		b.pop();
		assertEquals(true, b.isPopped());
		b.blow(10);
		assertEquals(0, b.getRadius());
	}
}
