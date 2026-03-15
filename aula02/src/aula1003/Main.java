package aula1003;

public class Main {

	public static void main(String[] args) {
		
		Animal a1 = new Gato();
		Animal a2 = new Cachorro();
		
		a1.emitirSom();
		a2.emitirSom();
		
		Retangulo f1 = new Retangulo();
		f1.base = 6.0;
		f1.altura = 4.0;
		System.out.println("Área: " + f1.calcularArea() + " cm2");
		System.out.println("Perímetro: " + f1.calcularPerimetro() 
			+ " cm");
		
		Circulo f2 = new Circulo();
		f2.raio = 6.0;
		System.out.println("Área: " + f2.calcularArea() + " cm2");
		System.out.println("Perímetro: " + f2.calcularPerimetro() 
		+ " cm");
	}

}
