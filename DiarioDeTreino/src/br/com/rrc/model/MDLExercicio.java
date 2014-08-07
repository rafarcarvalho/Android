package br.com.rrc.model;

public class MDLExercicio {
	private int pk_Int_Codigo_Exercicio;
	private int fk_Int_Codigo_Grupo_Muscular;
	private String vch_Descricao;
	private String vch_Maquina;
	
	public int getPk_Int_Codigo_Exercicio() {
		return pk_Int_Codigo_Exercicio;
	}
	public void setPk_Int_Codigo_Exercicio(int pk_Int_Codigo_Exercicio) {
		this.pk_Int_Codigo_Exercicio = pk_Int_Codigo_Exercicio;
	}
	public int getFk_Int_Codigo_Grupo_Muscular() {
		return fk_Int_Codigo_Grupo_Muscular;
	}
	public void setFk_Int_Codigo_Grupo_Muscular(int fk_Int_Codigo_Grupo_Muscular) {
		this.fk_Int_Codigo_Grupo_Muscular = fk_Int_Codigo_Grupo_Muscular;
	}
	public String getVch_Descricao() {
		return vch_Descricao;
	}
	public void setVch_Descricao(String vch_Descricao) {
		this.vch_Descricao = vch_Descricao;
	}
	public String getVch_Maquina() {
		return vch_Maquina;
	}
	public void setVch_Maquina(String vch_Maquina) {
		this.vch_Maquina = vch_Maquina;
	}
	
	public MDLExercicio(int pk_Int_Codigo_Exercicio,
						int fk_Int_Codigo_Grupo_Muscular, 
						String vch_Descricao,
						String vch_Maquina) {
		super();
		this.pk_Int_Codigo_Exercicio = pk_Int_Codigo_Exercicio;
		this.fk_Int_Codigo_Grupo_Muscular = fk_Int_Codigo_Grupo_Muscular;
		this.vch_Descricao = vch_Descricao;
		this.vch_Maquina = vch_Maquina;
	}
	
	public MDLExercicio() {
		super();
	}
	@Override
	public String toString() {
		return vch_Descricao;
	}
}