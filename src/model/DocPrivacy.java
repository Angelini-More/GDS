package model;

import java.util.Date;

public class DocPrivacy {
	private int id;
	private String documento;
	private String data2;
	private Date data;
	private int durata;
	private int flag;
	private int compar;
	
	public DocPrivacy(int id, String documento) {
		this.id=id;
		this.documento=documento;
	}
	
	public DocPrivacy(int id, String documento, int flag, Date data) {
		this.id=id;
		this.documento=documento;
		this.flag=flag;
		this.data=data;
	}

	
	public DocPrivacy(int id, String documento, Date data, int flag, int compar) {
		this.id=id;
		this.documento=documento;
	this.flag=flag;
		this.data=data;
		this.compar=compar;
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
	
	public String getData2(){
		return data2;
	}
	
	public void setData2(String data2){
		this.data2 = data2;
	}
	
	public int getCompar(){
		return compar;
	}
	
	public void setCompar(int compar){
		this.compar = compar;
	}
}



