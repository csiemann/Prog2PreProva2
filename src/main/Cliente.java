package main;
import java.util.ArrayList;

public class Cliente {
	
	
	public String nome;
	public ArrayList<Doce> docesComprados = new ArrayList();
	
	public Cliente(String nome) throws Exception{
		this.setNome(nome);
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

	public ArrayList<Doce> getDocesComprados() {
		return docesComprados;
	}

	public void addDoce(Doce doce) {
		docesComprados.add(doce);
	}
	
	public float totalCompra() {
		
		return 0;
	}
		
}
