package br.ce.wcaquino;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CalculadoraTest {
	
	private Calculadora calc = new Calculadora();
	
	@Test
	public void testSomar() {

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

		// Assertions.fail();

	}

	@Test
	public void deveRetornarNumeroInteiroNaDivisao() {
		float resultado = calc.dividir(6, 2);
		Assertions.assertEquals(3, resultado);
	}

	@Test
	public void deveRetornarNumeroNegativoNaDivisao() {
		float resultado = calc.dividir(6, -2);
		Assertions.assertEquals(-3, resultado);
	}

	@Test
	public void deveRetornarNumeroDecimalNaDivisao() {
		float resultado = calc.dividir(10, 3);
		Assertions.assertEquals(3.3333332538604736, resultado);
		Assertions.assertEquals(3.33, resultado, 0.01);
	}

	@Test
	public void deveRetornarZeroComNumeradorZeroNaDivisao() {
		float resultado = calc.dividir(0, 2);
		Assertions.assertEquals(0, resultado);
	}

	@Test
	public void deveLancarExcecaoQuandoDIvidirPorZero_Junit4() {

		try {
			float resultado = 10 / 0;
			Assertions.fail("Deveria ter sido lançado um exceção na execução");
		} catch (ArithmeticException ae) {
			Assertions.assertEquals("/ by zero", ae.getMessage());
		}

	}

	@Test
	public void deveLancarExcecaoQuandoDIvidirPorZero_Junit5() {
		
		ArithmeticException exception = Assertions.assertThrows(ArithmeticException.class, () -> {
			float resultado = 10 / 0;
		});
		
		Assertions.assertEquals("/ by zero", exception.getMessage());

	}

}
