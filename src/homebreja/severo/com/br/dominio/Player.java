package homebreja.severo.com.br.dominio;

import java.util.ArrayList;
import java.util.HashMap;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class Player {
	private String nome;
	private ArrayList<Condicao> condicoes = new ArrayList<Condicao>();
	private ArrayList<String> locais = new ArrayList<String>();
	private HashMap< String , Integer> mapaC = new HashMap<String, Integer>();
	
	public Player(String nome) {
		this.nome = nome;
		
		mapaC.put("ataque", 0);
		mapaC.put("defesa", 0);
		mapaC.put("fortitude", 0);
		mapaC.put("vontade", 0);
		mapaC.put("reflexos", 0);
		mapaC.put("dano", 0);
		mapaC.put("pericias", 0);
		mapaC.put("pericias_FOR", 0);
		mapaC.put("pericias_DES", 0);
		mapaC.put("pericias_CON", 0);
		mapaC.put("pericias_INT", 0);
		mapaC.put("pericias_SAB", 0);
		mapaC.put("pericias_CAR", 0);
		mapaC.put("FOR", 0);
		mapaC.put("DES", 0);
		mapaC.put("CON", 0);
		mapaC.put("INT", 0);
		mapaC.put("SAB", 0);
		mapaC.put("CAR", 0);
		
		locais.add("ataque");
		locais.add("defesa");
		locais.add("fortitude");
		locais.add("vontade");
		locais.add("reflexos");
		locais.add("dano");
		locais.add("pericias");
		locais.add("pericias_FOR");
		locais.add("pericias_DES");
		locais.add("pericias_CON");
		locais.add("pericias_INT");
		locais.add("pericias_SAB");
		locais.add("pericias_CAR");
		locais.add("FOR");
		locais.add("DES");
		locais.add("CON");
		locais.add("INT");
		locais.add("SAB");
		locais.add("CAR");

	}
	
	public void retiraCondicao(String nome) {
		int pa = 0;
		for (Condicao condicao : condicoes) {
			if(condicao.getNome().equals(nome)) {
				break;
			}
			pa += 1;
		}
		
		for(int i =0; i < locais.size(); i+=1) {
			if(condicoes.get(pa).getEfeitos().containsKey(locais.get(i))) {
				mapaC.replace(locais.get(i), mapaC.get(locais.get(i)) - condicoes.get(pa).getEfeitos().get(locais.get(i))  );
				condicoes.get(pa).getEfeitos().remove(locais.get(i));
				condicoes.remove(pa);
				return;
			}
		}
		
		System.out.println("condicao não encontrada");
	}
	
	
	public void addCondicao(String condicao, String tempo, int turn) throws IOException  {
		String file = "C:\\workspace\\eclipse-workspace\\TurnBuffer\\src\\homebreja\\severo\\com\\br\\main\\condicoes.txt";
		BufferedReader buffRead = new BufferedReader(new FileReader(file));
		String linha = "";
		Condicao cop;
		while(true) {
			
			HashMap< String , Integer> mapaB = new HashMap<>();
			
			linha = buffRead.readLine();
			if(linha == null) {
				System.out.println("Não encontrado");

				break;
			}
			String[] dados = linha.split(";");
			
			
			if(dados[0].equals(condicao)) {
				
				
				String[] effects = dados[1].split(",");
				if(effects.length >1) {
					for (String string : effects) {
						String[] strs = string.split(" ");
						if(strs[0].equals("->")) {
							this.addCondicao(strs[1], tempo, turn);
						}else
						mapaB.put(strs[0], Integer.parseInt(strs[1]));	
						//System.out.println(dados[1]);
						//System.out.println(strs[0]);
						//System.out.println(strs[1]);
					}
				}
				String desc = dados[2];
				
				String[] type = dados[3].split(" ");
				
				//System.out.println(type[0]);
				if(type[0].equals("Debuff")) {
					 cop = new DeBuff(dados[0],tempo,desc,mapaB,type[1], turn);
					 //System.out.println(mapaB);
				}else if(type[0].equals("Buff")) {
					 cop = new Buff(dados[0],tempo,desc,mapaB,type[1], turn);
				}else {
					 cop = new Condicao(dados[0],tempo,desc,mapaB,turn);
				}
				//System.out.println("check" + cop.getNome() + cop.getEfeitos().get("DES"));
				cop.infos("Fraco");
				insertCondicao(cop);
				buffRead.close();
				break;
				
				
			}
		}
		
		
		
	}
	
	public void insertCondicao(Condicao cop) {
		condicoes.add(cop);
		String[] str;
		for (String local : locais) {
			if(cop.getEfeitos().containsKey(local)) {
				
				
				if(local == "pericias") {
					for (String local1 : locais) {
						str = local1.split("_");
						if(str[0] == "pericias") {
							mapaC.replace(local, mapaC.get(local) + cop.getEfeitos().get(local));
						}
					}
				}else {
					mapaC.replace(local, mapaC.get(local) + cop.getEfeitos().get(local));
				}
			}
		}
	}
	
	public void informeCondicao(int turn) {
		Integer aux = 0;
		ArrayList<Integer> condifall = new ArrayList<>();
		for (Condicao condicao : condicoes) {
			if(condicao.getTime().equals("cena")  || condicao.getTime().equals("Cena"));else
			if((Integer.parseInt(condicao.getTime()) - (turn - condicao.getNascimento())) <0) condifall.add(aux);
				aux += 1;
		}
		
		for (Integer integer : condifall) {
			condicoes.remove(integer.intValue());
		}
		
		for (Condicao condicao : condicoes) {
		if(condicao.getTime().equals("cena")  || condicao.getTime().equals("Cena")) {
			System.out.println("\n"+condicao.getNome()+ "\nDuração: " + condicao.getTime());
		}else
			System.out.println("\n"+condicao.getNome()+ "\nDuração: " + (Integer.parseInt(condicao.getTime()) - (turn - condicao.getNascimento())));
			 
			for (String local : locais) {
					condicao.infos(local);
			}
			
			System.out.println(condicao.getDescricao());
			
			if(condicao instanceof Buff) {
				System.out.println(((Buff)condicao).getOrigem());
			}else if(condicao instanceof DeBuff) {
				System.out.println("condicao de "+((DeBuff)condicao).getTipo());
			}
			
		}
		System.out.println("\n");
	}
	
	

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public ArrayList<Condicao> getCondicoes() {
		return condicoes;
	}

	public void setCondicoes(ArrayList<Condicao> condicoes) {
		this.condicoes = condicoes;
	}

	public HashMap<String, Integer> getMapaC() {
		return mapaC;
	}

	public void setMapaC(HashMap<String, Integer> mapaC) {
		this.mapaC = mapaC;
	}
	
	
}
