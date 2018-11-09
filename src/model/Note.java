package model;



public class Note {
	private int id;
	private String testo;
	private String data;
	
	public Note(int id, String testo, String data) {
		this.id=id;
		this.testo=testo;
		this.data=data;
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
	
	public String getData() {
		return data;
	}
	
	public void setData() {
		this.data=data;
	}
}
