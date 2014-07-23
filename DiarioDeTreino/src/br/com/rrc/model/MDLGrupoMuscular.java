package br.com.rrc.model;

public class MDLGrupoMuscular {
	private int pk_Int_Codigo_Grupo_Muscular;
	private String vch_Nome;
	
	public int getPk_Int_Codigo_Grupo_Muscular() {
		return pk_Int_Codigo_Grupo_Muscular;
	}
	public void setPk_Int_Codigo_Grupo_Muscular(int pk_Int_Codigo_Grupo_Muscular) {
		this.pk_Int_Codigo_Grupo_Muscular = pk_Int_Codigo_Grupo_Muscular;
	}
	public String getVch_Nome() {
		return vch_Nome;
	}
	public void setVch_Nome(String vch_Nome) {
		this.vch_Nome = vch_Nome;
	}
	
	public MDLGrupoMuscular(int pk_Int_Codigo_Grupo_Muscular, String vch_Nome) {
		super();
		this.pk_Int_Codigo_Grupo_Muscular = pk_Int_Codigo_Grupo_Muscular;
		this.vch_Nome = vch_Nome;
	}
	
	public MDLGrupoMuscular() {
		super();
	}
	@Override
	public String toString() {
		return vch_Nome;
	}
}
