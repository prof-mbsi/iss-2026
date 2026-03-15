package aula1003;

public class Login {
	
	private String usuarioCorreto = "admin";
	private String senhaCorreta = "1234";
	
	public void autenticar(String usuario, String senha) 
			throws LoginIncorretoException  {
		if(!usuario.equals(usuarioCorreto) || 
				senha.equals(senhaCorreta)) {
			throw new LoginIncorretoException(
					"Usuário ou senha incorretos!");
		}
	}

}
