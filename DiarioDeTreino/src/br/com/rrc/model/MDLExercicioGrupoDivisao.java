package br.com.rrc.model;

public class MDLExercicioGrupoDivisao {
	
	private int pk_Int_Codigo_Exercicio_Grupo_Divisao;
	private int fk_Int_Codigo_Grupo_Divisao;
	private int fk_Int_Codigo_Exercicio;
	
	public int getPk_Int_Codigo_Exercicio_Grupo_Divisao() {
		return pk_Int_Codigo_Exercicio_Grupo_Divisao;
	}
	public void setPk_Int_Codigo_Exercicio_Grupo_Divisao(
			int pk_Int_Codigo_Exercicio_Grupo_Divisao) {
		this.pk_Int_Codigo_Exercicio_Grupo_Divisao = pk_Int_Codigo_Exercicio_Grupo_Divisao;
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
	
	public MDLExercicioGrupoDivisao(int pk_Int_Codigo_Exercicio_Grupo_Divisao,
			int fk_Int_Codigo_Grupo_Divisao, int fk_Int_Codigo_Exercicio) {
		super();
		this.pk_Int_Codigo_Exercicio_Grupo_Divisao = pk_Int_Codigo_Exercicio_Grupo_Divisao;
		this.fk_Int_Codigo_Grupo_Divisao = fk_Int_Codigo_Grupo_Divisao;
		this.fk_Int_Codigo_Exercicio = fk_Int_Codigo_Exercicio;
	}
	
	public MDLExercicioGrupoDivisao() {
		super();
	}
}
