package br.ce.wcaquino;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CalculadoraTest {
	
	@Test
	public void testSomar() {
		
		Calculadora calc = new Calculadora();
		
		Assertions.assertEquals(5, calc.soma(2, 3));
		
	}
	
	@Test
	public void assertivas() {
		Assertions.assertEquals("casa", "casa");
		Assertions.assertNotEquals("Casa", "casa");
		Assertions.assertTrue("casa".equalsIgnoreCase("CASA"));
		Assertions.assertTrue("casa".endsWith("sa"));

		List<String> s1 = new ArrayList<String>();
		List<String> s2 = new ArrayList<String>();
		List<String> s3 = null;
		
		Assertions.assertEquals(s1, s2);
		Assertions.assertNotSame(s1, s2);
		Assertions.assertNull(s3);
		Assertions.assertNotNull(s1);
		
		//Assertions.fail();
		
	}

}
