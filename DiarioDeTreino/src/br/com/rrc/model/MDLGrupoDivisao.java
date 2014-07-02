package br.com.rrc.model;

public class MDLGrupoDivisao {
	private int pk_Int_Codigo_Grupo_Divisao;
	private int fk_Int_Codigo_Divisao;
	private int fk_Int_Codigo_Grupo_Muscular;
	
	public int getPk_Int_Codigo_Grupo_Divisao() {
		return pk_Int_Codigo_Grupo_Divisao;
	}
	public void setPk_Int_Codigo_Grupo_Divisao(int pk_Int_Codigo_Grupo_Divisao) {
		this.pk_Int_Codigo_Grupo_Divisao = pk_Int_Codigo_Grupo_Divisao;
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
	
	public MDLGrupoDivisao(int pk_Int_Codigo_Grupo_Divisao,
							int fk_Int_Codigo_Divisao, 
							int fk_Int_Codigo_Grupo_Muscular) {
		super();
		this.pk_Int_Codigo_Grupo_Divisao = pk_Int_Codigo_Grupo_Divisao;
		this.fk_Int_Codigo_Divisao = fk_Int_Codigo_Divisao;
		this.fk_Int_Codigo_Grupo_Muscular = fk_Int_Codigo_Grupo_Muscular;
	}
	
	public MDLGrupoDivisao() {
		super();
	}
}
