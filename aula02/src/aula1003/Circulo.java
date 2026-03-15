package aula1003;

public class Circulo implements Forma{
	
	double raio;

	@Override
	public double calcularArea() {
		double area = 3.1415 * this.raio * this.raio;
		return area;
	}

	@Override
	public double calcularPerimetro() {
		double perimetro = 2 * 3.1415 * this.raio;
		return perimetro;
	}

}
