package aula1003;

public class TesteDeExcecoes {

	public static void main(String[] args) 
			throws LoginIncorretoException {
		
		try {
			int x = 10 / 0;
			System.out.println("Resultado: " + x);
		} catch (ArithmeticException e) {
			System.err.println("Erro: Divisão por 0!");
		}
		
		System.out.println("Continuação da execução...");
		
		Login l = new Login();
		l.autenticar("marlon", "123");
		
	}

}
