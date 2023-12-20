package homebreja.severo.com.br.main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import homebreja.severo.com.br.dominio.*;

public class TimerTurno {
	
	private static ArrayList<Player> lutadores = new ArrayList<>();
	private static int turn = 0;
	
	public TimerTurno(){
		
	}
	
	public static void main(String args[]) throws IOException {
		
		
		
		
		
		turn = 0;
		int grau = 0; 
		boolean passou = false;
		Scanner sc = new Scanner(System.in);
		String opcao;
		try {
			boolean começou=false;

			while(true) {
				
				if(começou == true) {
					if(passou==true) {
						turn += 1;
					}
				}
				
				System.out.println("Turn Tracker \n===================================================== " + "Turno " + turn);
				
				grau = 0;
				for (Player player : lutadores) {
					System.out.println(player.getNome()+ ":" + grau);
					if(player.getCondicoes().isEmpty() == false) {
						player.informeCondicao(turn);
					}
					grau += 1;
				}
				
				
				passou =false;
				
				
				
				opcao = sc.nextLine();
				
				

				 
				if(opcao.equals("0")) {
					break;
				}else if(opcao.equals("iniciar")) {
					começou = true;
				}else if(opcao.equals("/add lutador")) {
					System.out.println("Informe o nome do lutador");
					opcao = sc.nextLine();
					lutadores.add(new Player(opcao));
				}else if(opcao.equals("/add condicao")) {
					String tempo;
					System.out.println("Informe o numero do lutador");
					grau = Integer.parseInt(sc.nextLine());
					System.out.println("Informe a condicao");
					opcao =sc.nextLine();
					System.out.println("Informe a duracao");
					tempo =sc.nextLine();
					lutadores.get(grau).addCondicao(opcao, tempo, turn);
				}else if(opcao.equals("/remove condicao")) {
					System.out.println("Informe o numero do lutador");
					grau = Integer.parseInt(sc.nextLine());
					System.out.println("Informe a condicao");
					opcao =sc.nextLine();
					lutadores.get(grau).retiraCondicao(opcao);
				}else if(opcao.equals("/next")) {
					passou = true;
				}
			}
			
		}finally{
			sc.close();
		}
	}

	public static ArrayList<Player> getLutadores() throws IOException {
		return lutadores;
	}

	public static void setLutadores(ArrayList<Player> lutadores) {
		TimerTurno.lutadores = lutadores;
	}

	public static int getTurn() {
		return turn;
	}

	public static void setTurn(int turn) {
		TimerTurno.turn = turn;
	}
	
	public static void adicionarCondicao(int a, String nome, String tempo) throws IOException {
		lutadores.get(a).addCondicao(nome, tempo, turn);
	}
	

}
