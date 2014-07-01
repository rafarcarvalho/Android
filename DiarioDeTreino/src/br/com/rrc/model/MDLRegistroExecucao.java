package br.com.rrc.model;

import java.util.Date;

public class MDLRegistroExecucao {
	private int pk_Int_Codigo_Registro_Execucao;
	private int fk_Int_Codigo_Divisao;
	private int fk_Int_Codigo_Grupo_Muscular;
	private int fk_Int_Codigo_Exercicio;
	private int fk_Int_Codigo_Status_Dificuldade;
	private int int_Ordem;
	private String vch_Serie_Repeticao;
	private double flt_Carga;
	private double flt_Peso_Antes_Treino;
	private double flt_Peso_Depois_Treino;
	private boolean bit_Aumento_Carga;
	private boolean bit_Reducao_Carga;
	private String vch_Nota;
	private Date dtt_Execucao;
	private Date dtt_Inicio_Execucao;
	private Date dtt_Fim_Execucao;
	
	public int getPk_Int_Codigo_Registro_Execucao() {
		return pk_Int_Codigo_Registro_Execucao;
	}
	public void setPk_Int_Codigo_Registro_Execucao(
			int pk_Int_Codigo_Registro_Execucao) {
		this.pk_Int_Codigo_Registro_Execucao = pk_Int_Codigo_Registro_Execucao;
	}
	public int getFk_Int_Codigo_Divisao() {
		return fk_Int_Codigo_Divisao;
	}
	public void setFk_Int_Codigo_Divisao(int fk_Int_Codigo_Divisao) {
		this.fk_Int_Codigo_Divisao = fk_Int_Codigo_Divisao;
	}
	public int getFk_Int_Codigo_Grupo_Muscular() {
		return fk_Int_Codigo_Grupo_Muscular;
	}
	public void setFk_Int_Codigo_Grupo_Muscular(int fk_Int_Codigo_Grupo_Muscular) {
		this.fk_Int_Codigo_Grupo_Muscular = fk_Int_Codigo_Grupo_Muscular;
	}
	public int getFk_Int_Codigo_Exercicio() {
		return fk_Int_Codigo_Exercicio;
	}
	public void setFk_Int_Codigo_Exercicio(int fk_Int_Codigo_Exercicio) {
		this.fk_Int_Codigo_Exercicio = fk_Int_Codigo_Exercicio;
	}
	public int getFk_Int_Codigo_Status_Dificuldade() {
		return fk_Int_Codigo_Status_Dificuldade;
	}
	public void setFk_Int_Codigo_Status_Dificuldade(
			int fk_Int_Codigo_Status_Dificuldade) {
		this.fk_Int_Codigo_Status_Dificuldade = fk_Int_Codigo_Status_Dificuldade;
	}
	public int getInt_Ordem() {
		return int_Ordem;
	}
	public void setInt_Ordem(int int_Ordem) {
		this.int_Ordem = int_Ordem;
	}
	public String getVch_Serie_Repeticao() {
		return vch_Serie_Repeticao;
	}
	public void setVch_Serie_Repeticao(String vch_Serie_Repeticao) {
		this.vch_Serie_Repeticao = vch_Serie_Repeticao;
	}
	public double getFlt_Carga() {
		return flt_Carga;
	}
	public void setFlt_Carga(double flt_Carga) {
		this.flt_Carga = flt_Carga;
	}
	public double getFlt_Peso_Antes_Treino() {
		return flt_Peso_Antes_Treino;
	}
	public void setFlt_Peso_Antes_Treino(double flt_Peso_Antes_Treino) {
		this.flt_Peso_Antes_Treino = flt_Peso_Antes_Treino;
	}
	public double getFlt_Peso_Depois_Treino() {
		return flt_Peso_Depois_Treino;
	}
	public void setFlt_Peso_Depois_Treino(double flt_Peso_Depois_Treino) {
		this.flt_Peso_Depois_Treino = flt_Peso_Depois_Treino;
	}
	public boolean isBit_Aumento_Carga() {
		return bit_Aumento_Carga;
	}
	public void setBit_Aumento_Carga(boolean bit_Aumento_Carga) {
		this.bit_Aumento_Carga = bit_Aumento_Carga;
	}
	public boolean isBit_Reducao_Carga() {
		return bit_Reducao_Carga;
	}
	public void setBit_Reducao_Carga(boolean bit_Reducao_Carga) {
		this.bit_Reducao_Carga = bit_Reducao_Carga;
	}
	public String getVch_Nota() {
		return vch_Nota;
	}
	public void setVch_Nota(String vch_Nota) {
		this.vch_Nota = vch_Nota;
	}
	public Date getDtt_Execucao() {
		return dtt_Execucao;
	}
	public void setDtt_Execucao(Date dtt_Execucao) {
		this.dtt_Execucao = dtt_Execucao;
	}
	public Date getDtt_Inicio_Execucao() {
		return dtt_Inicio_Execucao;
	}
	public void setDtt_Inicio_Execucao(Date dtt_Inicio_Execucao) {
		this.dtt_Inicio_Execucao = dtt_Inicio_Execucao;
	}
	public Date getDtt_Fim_Execucao() {
		return dtt_Fim_Execucao;
	}
	public void setDtt_Fim_Execucao(Date dtt_Fim_Execucao) {
		this.dtt_Fim_Execucao = dtt_Fim_Execucao;
	}
	
	public MDLRegistroExecucao(int pk_Int_Codigo_Registro_Execucao,
								int fk_Int_Codigo_Divisao, 
								int fk_Int_Codigo_Grupo_Muscular,
								int fk_Int_Codigo_Exercicio, 
								int fk_Int_Codigo_Status_Dificuldade,
								int int_Ordem, 
								String vch_Serie_Repeticao, 
								double flt_Carga,
								double flt_Peso_Antes_Treino, 
								double flt_Peso_Depois_Treino,
								boolean bit_Aumento_Carga, 
								boolean bit_Reducao_Carga,
								String vch_Nota, 
								Date dtt_Execucao, 
								Date dtt_Inicio_Execucao,
								Date dtt_Fim_Execucao) {
		super();
		this.pk_Int_Codigo_Registro_Execucao = pk_Int_Codigo_Registro_Execucao;
		this.fk_Int_Codigo_Divisao = fk_Int_Codigo_Divisao;
		this.fk_Int_Codigo_Grupo_Muscular = fk_Int_Codigo_Grupo_Muscular;
		this.fk_Int_Codigo_Exercicio = fk_Int_Codigo_Exercicio;
		this.fk_Int_Codigo_Status_Dificuldade = fk_Int_Codigo_Status_Dificuldade;
		this.int_Ordem = int_Ordem;
		this.vch_Serie_Repeticao = vch_Serie_Repeticao;
		this.flt_Carga = flt_Carga;
		this.flt_Peso_Antes_Treino = flt_Peso_Antes_Treino;
		this.flt_Peso_Depois_Treino = flt_Peso_Depois_Treino;
		this.bit_Aumento_Carga = bit_Aumento_Carga;
		this.bit_Reducao_Carga = bit_Reducao_Carga;
		this.vch_Nota = vch_Nota;
		this.dtt_Execucao = dtt_Execucao;
		this.dtt_Inicio_Execucao = dtt_Inicio_Execucao;
		this.dtt_Fim_Execucao = dtt_Fim_Execucao;
	}
	
	public MDLRegistroExecucao() {
		super();
	}
	
}
