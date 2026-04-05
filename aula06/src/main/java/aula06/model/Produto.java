package aula06.model;

public class Produto {
	
	private String id; 
    private String nome; 
    private double preco; 

    public Produto(String id, String nome, double preco) { 
        this.id = id; 
        this.nome = nome; 
        this.preco = preco; 
    } 
    
    public Produto() {}

    public String getId() { return id; } 
    public String getNome() { return nome; } 
    public double getPreco() { return preco; }

	public void setId(String uuid) {
		this.id	= uuid;
	} 

}
