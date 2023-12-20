package homebreja.severo.com.br.dominio;

import java.util.HashMap;

public class DeBuff extends Condicao {
	private String tipo;
	
	public DeBuff(String nome, String time, String descricao, HashMap< String , Integer> efeitos, String tipo, int nascimento) {
		super(nome, time, descricao, efeitos, nascimento);
		this.tipo = tipo;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	

}
