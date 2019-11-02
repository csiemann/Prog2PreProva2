package main;
public class Doce {

	public int codigo;
	public String nome;
	public float preco;
	
	public Doce(int codigo, String nome, float preco) throws Exception {
		this.setCodigo(codigo);
		this.setNome(nome);
		this.setPreco(preco);
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) throws Exception {
		if (codigo <= 0) {
			throw new Exception("Código inválido");
		}
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) throws Exception {
		if (nome.isEmpty()) {
			throw new Exception("Nome inválido");
		}
		this.nome = nome;
	}

	public float getPreco() {
		return preco;
	}

	public void setPreco(float preco) throws Exception {
		if (preco < 0) {
			throw new Exception("Preço inválido");
		}
		this.preco = preco;
	}
}