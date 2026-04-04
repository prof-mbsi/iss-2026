package service;

public class Produto {
	
	private int id;
	private String nome;
	
	public Produto(int id, String nome) {
		this.id = id;
		this.nome = nome;
	}
	
	public int getId() { return id; }
	public String getNome() { return nome; }

}
