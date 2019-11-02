package main;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Stream;

public class Arquivo {

	private static final String lineSeparator = System.getProperty("line.separator");
	private static final String fileSeparator = System.getProperty("file.separator");

	Arquivo() {
	}

	public void salvar(String diretorio, String nome, Cliente cliente) {
		try {
			salvarTXT(diretorio, nome, cliente);
			salvarObj(cliente);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/* MÉTODO QUE GRAVA O TXT */
	private void salvarTXT(String diretorio, String nome, Cliente cliente) throws IOException {
		FileSystem fs = FileSystems.getDefault();
		Path arq;

		/* VERIFICA SE EXISTE O DIRETÓRIO */
		if (diretorio != null && Files.exists(fs.getPath(diretorio)))
			arq = fs.getPath(diretorio + fileSeparator + nome + ".cli");
		else
			arq = fs.getPath(nome + ".cli");

		/* SE NÃO EXISTIR O ARQUIVO INFORMADO, CRIAMOS ELE */
		if (!Files.exists(arq))
			Files.createFile(arq);

		FileWriter writer = new FileWriter(arq.toFile(), true);
		BufferedWriter bw = new BufferedWriter(writer);

		bw.append(cliente.toString());

		bw.close();
		writer.close();
	}

	/* MÉTODO QUE GRAVA O ARQUIVO SERIALIZADO */
	private void salvarObj(Cliente cliente) throws IOException {
		Path arq = Paths.get(cliente.getNome() + ".ser");

		/* SE NÃO EXISTIR O ARQUIVO INFORMADO, CRIAMOS ELE */
		if (!Files.exists(arq))
			Files.createFile(arq);

		FileOutputStream fos = new FileOutputStream(arq.toFile(), false);
		BufferedOutputStream bos = new BufferedOutputStream(fos);
		ObjectOutputStream oos = new ObjectOutputStream(bos);

		for (Doce doce : cliente.getDocesComprados()) {
			oos.writeObject(doce);
		}
		oos.close();
		bos.close();
		fos.close();
	}

	public Cliente ler(String diretorio, String nome) {
		try {
			Cliente cliente = new Cliente("teste");
			FileReader reader = new FileReader(diretorio + fileSeparator + nome);
			BufferedReader br = new BufferedReader(reader);
			String line;
			/* VERIFICA SE É UM ARQUIVO SEM TEXTO */
			if ((line = br.readLine()) != null)
				/* VERIFICA SE A LINHA É O NOME DO CLIENTE */
				if (line.contains("cli=")) {
					cliente.setNome(line.substring(4));
					/* ENQUANTO EXISTE A PRÓXIMA LINHA */
					while ((line = br.readLine()) != null) {
						/* VERIFICA SE A LINHA É O CÓDIGO DE UM DOCE */
						if (line.contains("cod=")) {
							int codigo = Integer.parseInt(line.substring(4));
							/* VERIFICA SE EXISTE A PRÓXIMA LINHA */
							if ((line = br.readLine()) != null)
								/* VERIFICA SE A LINHA É O NOME DE UM DOCE */
								if (line.contains("nom=")) {
									String nomeDoce = line.substring(4);
									/* VERIFICA SE EXISTE A PRÓXIMA LINHA */
									if ((line = br.readLine()) != null)
										/* VERIFICA SE A LINHA É O PREÇO DE UM DOCE */
										if (line.contains("prc=")) {
											float preco = Float.parseFloat(line.substring(4));
											/* ADICIONA O DOCE AO CLIENTE */
											cliente.addDoce(new Doce(codigo, nomeDoce, preco));
										} else {
											// TODO ERRO
										}
								} else {
									// TODO ERRO
								}
						} else if (line.contains("tot=")) {
							break;
						} else {
							// TODO ERRO
						}
					}
				} else {
					// TODO ERRO
				}
			br.close();
			reader.close();
			return cliente;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public ArrayList<Cliente> lerLista(String diretorio, String nome) {
		try {
			ArrayList<Cliente> clientes = new ArrayList<>();
			FileReader reader = new FileReader(diretorio + fileSeparator + nome);
			BufferedReader br = new BufferedReader(reader);
			String line;
			/* ENQUANTO HÁ LINHA NÃO NULA */
			while ((line = br.readLine()) != null) {
				Cliente cliente = new Cliente("teste");
				/* VERIFICA SE A LINHA É O NOME DO CLIENTE */
				if (line.contains("cli=")) {
					cliente.setNome(line.substring(4));
					/* ENQUANTO EXISTE A PRÓXIMA LINHA */
					while ((line = br.readLine()) != null) {
						/* VERIFICA SE A LINHA É O CÓDIGO DE UM DOCE */
						if (line.contains("cod=")) {
							int codigo = Integer.parseInt(line.substring(4));
							/* VERIFICA SE EXISTE A PRÓXIMA LINHA */
							if ((line = br.readLine()) != null)
								/* VERIFICA SE A LINHA É O NOME DE UM DOCE */
								if (line.contains("nom=")) {
									String nomeDoce = line.substring(4);
									/* VERIFICA SE EXISTE A PRÓXIMA LINHA */
									if ((line = br.readLine()) != null)
										/* VERIFICA SE A LINHA É O PREÇO DE UM DOCE */
										if (line.contains("prc=")) {
											float preco = Float.parseFloat(line.substring(4));
											/* ADICIONA O DOCE AO CLIENTE */
											cliente.addDoce(new Doce(codigo, nomeDoce, preco));
										} else {
											// TODO ERRO
										}
								} else {
									// TODO ERRO
								}
						} else if (line.contains("tot=")) {
							break;
						} else {
							// TODO ERRO
						}
					}
				} else {
					// TODO ERRO
				}

				clientes.add(cliente);
			}
			br.close();
			reader.close();

			return clientes;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public String listarArquivos(String diretorio) {
		try {
			FileSystem fs = FileSystems.getDefault();
			Stream<Path> files = Files.list(fs.getPath(diretorio));
			StringBuilder builder = new StringBuilder();
			files.forEach(file -> builder.append(file.getFileName()+lineSeparator));
			return builder.toString();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void main(String[] args) {
		try {
			Cliente cliente = new Cliente("Caetano");
			cliente.addDoce(new Doce(1, "doce1", 1.24f));
			cliente.addDoce(new Doce(2, "doce2", 4f));
			cliente.addDoce(new Doce(3, "doce3", 5f));
			cliente.addDoce(new Doce(4, "doce4", 2.23f));
			Arquivo a = new Arquivo();
			a.salvar(null, "cli", cliente);
			Cliente cli = a.ler("C:\\Users\\Caetano\\git\\Prog2PreProva2", "cli.cli");
			System.out.println(cli);
			ArrayList<Cliente> clis = a.lerLista("C:\\Users\\Caetano\\git\\Prog2PreProva2", "cli.cli");
			System.out.println(clis.size());
			String dir = a.listarArquivos("C:\\Users\\Caetano\\git\\Prog2PreProva2");
			System.out.println(dir);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
