package com.marconi.fantacalcio.model;

public class Giocatore {
	
	private int cod_giocatore;
	private String nome;
	private String cognome;
	private int eta;
	private String nazione;
	private String squadraVera;
	private Statistiche statistiche;
	
	
	public Giocatore() {
		super();
	}
	
	public Giocatore(int cod_giocatore, String nome, String cognome, int eta, String nazione, String squadraVera) {
		super();
		this.cod_giocatore=cod_giocatore;
		this.nome = nome;
		this.cognome = cognome;
		this.eta = eta;
		this.nazione = nazione;
		this.squadraVera = squadraVera;
	}


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public String getCognome() {
		return cognome;
	}


	public void setCognome(String cognome) {
		this.cognome = cognome;
	}


	public int getEta() {
		return eta;
	}


	public void setEta(int eta) {
		this.eta = eta;
	}


	public String getNazione() {
		return nazione;
	}


	public void setNazione(String nazione) {
		this.nazione = nazione;
	}


	public String getSquadraVera() {
		return squadraVera;
	}


	public void setSquadraVera(String squadraVera) {
		this.squadraVera = squadraVera;
	}


	public Statistiche getStatistiche() {
		return statistiche;
	}


	public void setStatistiche(Statistiche statistiche) {
		this.statistiche = statistiche;
	}


	public int getCod_giocatore() {
		return cod_giocatore;
	}


	public void setCod_giocatore(int cod_giocatore) {
		this.cod_giocatore = cod_giocatore;
	}
	
	
	
	

}
