package br.com.rrc.model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import android.text.format.DateFormat;



public class MDLTreino {
	
	private int pk_Int_Codigo_Treino;
	private String vch_Nome_Atleta;
	private Date dtt_Criacao;
	private String vch_Nome_Treino;
	private String vch_Projeto;
	private int int_Minutos_Duracao;
	private int int_segundos_pausa_series;
	
	public int getPk_Int_Codigo_Treino() {
		return pk_Int_Codigo_Treino;
	}
	public void setPk_Int_Codigo_Treino(int pk_Int_Codigo_Treino) {
		this.pk_Int_Codigo_Treino = pk_Int_Codigo_Treino;
	}
	public String getVch_Nome_Atleta() {
		return vch_Nome_Atleta;
	}
	public void setVch_Nome_Atleta(String vch_Nome_Atleta) {
		this.vch_Nome_Atleta = vch_Nome_Atleta;
	}
	public Date getDtt_Criacao() {
		return dtt_Criacao;
	}
	public void setDtt_Criacao(Date dtt_Criacao) {
		this.dtt_Criacao = dtt_Criacao;
	}
	public String getVch_Nome_Treino() {
		return vch_Nome_Treino;
	}
	public void setVch_Nome_Treino(String vch_Nome_Treino) {
		this.vch_Nome_Treino = vch_Nome_Treino;
	}
	public String getVch_Projeto() {
		return vch_Projeto;
	}
	public void setVch_Projeto(String vch_Projeto) {
		this.vch_Projeto = vch_Projeto;
	}
	public int getInt_Minutos_Duracao() {
		return int_Minutos_Duracao;
	}
	public void setInt_Minutos_Duracao(int int_Minutos_Duracao) {
		this.int_Minutos_Duracao = int_Minutos_Duracao;
	}
	public int getInt_segundos_pausa_series() {
		return int_segundos_pausa_series;
	}
	public void setInt_segundos_pausa_series(int int_segundos_pausa_series) {
		this.int_segundos_pausa_series = int_segundos_pausa_series;
	}
	
	public MDLTreino(int pk_Int_Codigo_Treino, 
						String vch_Nome_Atleta,
						Date dtt_Criacao, 
						String vch_Nome_Treino, 
						String vch_Projeto,
						int int_Minutos_Duracao, 
						int int_segundos_pausa_series) {
		super();
		this.pk_Int_Codigo_Treino = pk_Int_Codigo_Treino;
		this.vch_Nome_Atleta = vch_Nome_Atleta;
		this.dtt_Criacao = dtt_Criacao;
		this.vch_Nome_Treino = vch_Nome_Treino;
		this.vch_Projeto = vch_Projeto;
		this.int_Minutos_Duracao = int_Minutos_Duracao;
		this.int_segundos_pausa_series = int_segundos_pausa_series;
	}
	
	@Override
	public String toString() {
		/*return "MDLTreino ["
				+ ", vch_Nome_Atleta=" + vch_Nome_Atleta + ", dtt_Criacao="
				+ dtt_Criacao + ", vch_Nome_Treino=" + vch_Nome_Treino
				+ ", vch_Projeto=" + vch_Projeto + ", int_Minutos_Duracao="
				+ int_Minutos_Duracao + ", int_segundos_pausa_series="
				+ int_segundos_pausa_series + "]";*/
		
		return vch_Nome_Atleta + " Treino: " + vch_Nome_Treino == null ? "-" : vch_Nome_Treino + " Criado em: " + new SimpleDateFormat("dd/MM/yyyy").format(dtt_Criacao);
	}
	public MDLTreino() {
		super();
	}

}
