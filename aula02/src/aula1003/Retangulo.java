package aula1003;

public class Retangulo implements Forma{
	
	public double base;
	public double altura;

	@Override
	public double calcularArea() {
		double area = this.base * this.altura;
		return area;
	}

	@Override
	public double calcularPerimetro() {
		double perimetro = (2 * this.base) + (2 * this.altura);
		return perimetro;
	}

}
