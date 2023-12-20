package homebreja.severo.com.br.dominio;

import java.util.HashMap;




public class Condicao {
	private String nome;
	private String time;
	private HashMap< String , Integer> efeitos = new HashMap<String, Integer>();
	private String descricao;
	private int nascimento;
	
	
	public Condicao(String nome, String time, String descricao, HashMap< String , Integer> efeitos, int nascimento) {
		this.nome = nome;
		this.time = time;
		this.efeitos = efeitos;
		this.descricao  = descricao;
		this.nascimento = nascimento;

	}
	
	public void infos(String arg) {
		if(efeitos.containsKey(arg))
			System.out.println(arg + ": "+ efeitos.get(arg));

	}
	
	public int getGet(String local) {
		return efeitos.get(local);
	}
	
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}

	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public HashMap<String, Integer> getEfeitos() {
		return efeitos;
	}


	public void setEfeitos(HashMap<String, Integer> efeitos) {
		this.efeitos = efeitos;
	}

	public int getNascimento() {
		return nascimento;
	}

	public void setNascimento(int nascimento) {
		this.nascimento = nascimento;
	}


	
			
			

}
