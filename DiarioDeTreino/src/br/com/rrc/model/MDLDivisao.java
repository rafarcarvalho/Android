package br.com.rrc.model;

public class MDLDivisao {
	private int pk_Int_Codigo_Divisao;
	private int fk_Int_Codigo_Treino;
	private char chr_Divisao;
	
	public int getPk_Int_Codigo_Divisao() {
		return pk_Int_Codigo_Divisao;
	}
	
	public void setPk_Int_Codigo_Divisao(int pk_Int_Codigo_Divisao) {
		this.pk_Int_Codigo_Divisao = pk_Int_Codigo_Divisao;
	}
	
	public int getFk_Int_Codigo_Treino() {
		return fk_Int_Codigo_Treino;
	}
	
	public void setFk_Int_Codigo_Treino(int fk_Int_Codigo_Treino) {
		this.fk_Int_Codigo_Treino = fk_Int_Codigo_Treino;
	}
	
	public char getChr_Divisao() {
		return chr_Divisao;
	}
	
	public void setChr_Divisao(char chr_Divisao) {
		this.chr_Divisao = chr_Divisao;
	}
	

	public MDLDivisao(int pk_Int_Codigo_Divisao, 
						int fk_Int_Codigo_Treino,
						char chr_Divisao) {
		super();
		this.pk_Int_Codigo_Divisao = pk_Int_Codigo_Divisao;
		this.fk_Int_Codigo_Treino = fk_Int_Codigo_Treino;
		this.chr_Divisao = chr_Divisao;
	}

	public MDLDivisao() {
		super();
	}

	@Override
	public String toString() {
		return "Divisao: " + chr_Divisao;
	}
	
}
