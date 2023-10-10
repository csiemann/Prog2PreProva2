package main;

import java.io.Serializable;
import java.util.ArrayList;

public class Cliente implements Serializable{
	private static final long serialVersionUID = 1L;

	private static final String lineSeparator = System.getProperty("line.separator");

	public String nome;
	public ArrayList<Doce> docesComprados = new ArrayList<>();

	public Cliente(String nome) throws Exception {
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
		float result = 0;
		for (int i = 0; i < docesComprados.size(); i++) {
			result += docesComprados.get(i).getPreco();
		}
		return result;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("cli=" + nome + lineSeparator);
		for (int i = 0; i < docesComprados.size(); i++) {
			builder.append(docesComprados.get(i).toString() + lineSeparator);
		}
		builder.append("tot=" + totalCompra()+lineSeparator);
		return builder.toString();
	}
}
