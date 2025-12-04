package com.marconi.fantacalcio.model;

public class Statistiche {
	
	private int attacco;
	private int centrocampo;
	private int difesa;
	private int forma;
	private int valore;
	
	public Statistiche(int attacco, int centrocampo, int difesa, int forma, int valore) {
		super();
		this.attacco = attacco;
		this.centrocampo = centrocampo;
		this.difesa = difesa;
		this.forma = forma;
		this.valore = valore;
	}

	public int getAttacco() {
		return attacco;
	}

	public void setAttacco(int attacco) {
		this.attacco = attacco;
	}

	public int getCentrocampo() {
		return centrocampo;
	}

	public void setCentrocampo(int centrocampo) {
		this.centrocampo = centrocampo;
	}

	public int getDifesa() {
		return difesa;
	}

	public void setDifesa(int difesa) {
		this.difesa = difesa;
	}

	public int getForma() {
		return forma;
	}

	public void setForma(int forma) {
		this.forma = forma;
	}

	public int getValore() {
		return valore;
	}

	public void setValore(int valore) {
		this.valore = valore;
	}
	
	
	
	

}
