package main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Arquivo {

	private static final String newLine = System.getProperty("line.separator");

	Arquivo() {
	}

	public void salvar(String diretorio, String nome, Cliente cliente) {

	}

	public Cliente ler(String diretorio, String nome) {
		return null;
	}

	public ArrayList<Cliente> lerLista(String diretorio, String nome) {
		
		return null;
	}

	public String listarArquivos(String diretorio) {
		try {
			FileReader reader = new FileReader(diretorio);
			BufferedReader br = new BufferedReader(reader);
			String line;
			StringBuilder builder = new StringBuilder();
			while ((line = br.readLine()) != null) {
				builder.append(line + newLine);
			}
			br.close();
			reader.close();
			return builder.toString();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
