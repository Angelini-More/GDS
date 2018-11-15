package model;

import java.util.Date;

public class DocSicurezza {
	private int id;
	private String documento;
	
	private int durata;
	
	public DocSicurezza(int id, String documento, int durata) {
		this.id=id;
		this.documento=documento;
	
		this.durata=durata;
	}
	
	public int getId(){
		return id;
	}
	
	public void setId(int id){
		this.id = id;
	}
	
	public String getDocumento(){
		return documento;
	}
	
	public void setDocumento(String documento){
		this.documento = documento;
	}
	
	public int getDurata(){
		return durata;
	}
	
	public void setDurata(int durata){
		this.durata = durata;
	}
}
