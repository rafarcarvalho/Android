package br.com.rrc.model;

public class MDLStatusDificuldade {
	private int pk_Int_Codigo_Status_Dificuldade;
	private String vch_Descricao;
	private String vch_Imagem;
	
	public int getPk_Int_Codigo_Status_Dificuldade() {
		return pk_Int_Codigo_Status_Dificuldade;
	}
	
	public void setPk_Int_Codigo_Status_Dificuldade(
			int pk_Int_Codigo_Status_Dificuldade) {
		this.pk_Int_Codigo_Status_Dificuldade = pk_Int_Codigo_Status_Dificuldade;
	}
	
	public String getVch_Descricao() {
		return vch_Descricao;
	}
	
	public void setVch_Descricao(String vch_Descricao) {
		this.vch_Descricao = vch_Descricao;
	}
	
	public String getVch_Imagem() {
		return vch_Imagem;
	}
	
	public void setVch_Imagem(String vch_Imagem) {
		this.vch_Imagem = vch_Imagem;
	}
	

	public MDLStatusDificuldade(int pk_Int_Codigo_Status_Dificuldade,
								String vch_Descricao, 
								String vch_Imagem) {
		super();
		this.pk_Int_Codigo_Status_Dificuldade = pk_Int_Codigo_Status_Dificuldade;
		this.vch_Descricao = vch_Descricao;
		this.vch_Imagem = vch_Imagem;
	}

	public MDLStatusDificuldade() {
		super();
	}
	
	
}
