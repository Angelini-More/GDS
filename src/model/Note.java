package model;

import java.util.Date;

public class Note {
	private int id;
	private String testo;
	private Date data;
	
	public Note(int id, String testo, Date data) {
		this.id=id;
		this.testo=testo;
		this.data=data;
	}
	
	public Note(int id, String testo) {
		this.id=id;
		this.testo=testo;
		
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id=id;
	}
	
	public String getTesto() {
		return testo;
	}
	
	public void setTesto(String testo) {
		this.testo=testo;
	}
	
	public Date getData() {
		return data;
	}
	
	public void setData() {
		this.data=data;
	}
}
