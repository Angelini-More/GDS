package model;

import java.util.Date;

public class DocSicurezza {
	private int id;
	private String documento;
	
	private Date data;
	private int durata;
	private int flag;
	
	public DocSicurezza(int id, String documento, int durata) {
		this.id=id;
		this.documento=documento;
	
		this.durata=durata;
	}
	
	public DocSicurezza(int id, String documento, Date data, int flag) {
		this.id=id;
		this.documento=documento;
	this.flag=flag;
		this.data=data;
	}
	
	public int getId(){
		return id;
	}
	
	public void setId(int id){
		this.id = id;
	}
	
	public int getFlag(){
		return flag;
	}
	
	public void setFlag(int flag){
		this.flag = flag;
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
	
	public Date getData(){
		return data;
	}
	
	public void setData(Date data){
		this.data = data;
	}
}
