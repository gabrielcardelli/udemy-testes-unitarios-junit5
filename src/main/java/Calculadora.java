
public class Calculadora {
	
	public int soma(int a, int b) {
		return a+b;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Calculadora calc = new Calculadora();
		System.out.println(calc.soma(2, 3) == 5);
		System.out.println(calc.soma(3, 4) == 7);
		System.out.println(calc.soma(5, 6) == 11);
	
	}

}
