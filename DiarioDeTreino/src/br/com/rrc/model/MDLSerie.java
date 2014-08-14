package br.com.rrc.model;

import java.util.Date;

public class MDLSerie {
	
	private int pk_Int_Codigo_Serie;
	private int fk_Int_Codigo_Grupo_Divisao;
	private int fk_Int_Codigo_Exercicio;
	private double flt_Carga;
	private int int_Repeticoes;
	private boolean bit_Completou;
	private Date dtt_Inicio;
	private Date dtt_Fim;

	public int getPk_Int_Codigo_Serie() {
		return pk_Int_Codigo_Serie;
	}
	public void setPk_Int_Codigo_Serie(int pk_Int_Codigo_Serie) {
		this.pk_Int_Codigo_Serie = pk_Int_Codigo_Serie;
	}
	public int getFk_Int_Codigo_Grupo_Divisao() {
		return fk_Int_Codigo_Grupo_Divisao;
	}
	public void setFk_Int_Codigo_Grupo_Divisao(int fk_Int_Codigo_Grupo_Divisao) {
		this.fk_Int_Codigo_Grupo_Divisao = fk_Int_Codigo_Grupo_Divisao;
	}
	public int getFk_Int_Codigo_Exercicio() {
		return fk_Int_Codigo_Exercicio;
	}
	public void setFk_Int_Codigo_Exercicio(int fk_Int_Codigo_Exercicio) {
		this.fk_Int_Codigo_Exercicio = fk_Int_Codigo_Exercicio;
	}
	public double getFlt_Carga() {
		return flt_Carga;
	}
	public void setFlt_Carga(double flt_Carga) {
		this.flt_Carga = flt_Carga;
	}
	public int getInt_Repeticoes() {
		return int_Repeticoes;
	}
	public void setInt_Repeticoes(int int_Repeticoes) {
		this.int_Repeticoes = int_Repeticoes;
	}
	public boolean isBit_Completou() {
		return bit_Completou;
	}
	public void setBit_Completou(boolean bit_Completou) {
		this.bit_Completou = bit_Completou;
	}
	public Date getDtt_Inicio() {
		return dtt_Inicio;
	}
	public void setDtt_Inicio(Date dtt_Inicio) {
		this.dtt_Inicio = dtt_Inicio;
	}
	public Date getDtt_Fim() {
		return dtt_Fim;
	}
	public void setDtt_Fim(Date dtt_Fim) {
		this.dtt_Fim = dtt_Fim;
	}
	
	public MDLSerie() {
		super();
	}
	
	public MDLSerie(int pk_Int_Codigo_Serie, int fk_Int_Codigo_Grupo_Divisao,
			int fk_Int_Codigo_Exercicio, double flt_Carga, int int_Repeticoes,
			boolean bit_Completou, Date dtt_Inicio, Date dtt_Fim) {
		super();
		this.pk_Int_Codigo_Serie = pk_Int_Codigo_Serie;
		this.fk_Int_Codigo_Grupo_Divisao = fk_Int_Codigo_Grupo_Divisao;
		this.fk_Int_Codigo_Exercicio = fk_Int_Codigo_Exercicio;
		this.flt_Carga = flt_Carga;
		this.int_Repeticoes = int_Repeticoes;
		this.bit_Completou = bit_Completou;
		this.dtt_Inicio = dtt_Inicio;
		this.dtt_Fim = dtt_Fim;
	}
	
	
}
