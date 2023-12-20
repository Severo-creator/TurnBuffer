package homebreja.severo.com.br.dominio;

import java.util.HashMap;

public class Buff extends Condicao  {
	private String origem;
	
	public Buff(String nome, String time,  String descricao,  HashMap< String , Integer> efeitos, String origem, int nascimento) {
		super(nome, time, descricao, efeitos, nascimento);
		this.origem = origem;
	}
	

	public String getOrigem() {
		return origem;
	}
	public void setOrigem(String origem) {
		this.origem = origem;
	}
	
	

}
