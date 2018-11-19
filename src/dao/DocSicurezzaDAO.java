package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import model.DocSicurezza;
import util.Database;

public class DocSicurezzaDAO {
	
	public static List<DocSicurezza> documentis(){
		
		List<DocSicurezza> s=new ArrayList<DocSicurezza>();
		try {
			Database.connect();
			ResultSet c=Database.selectRecord("documentisicurezza");
			while(c.next()) {
				int id=c.getInt("id");
				String documento=c.getString("documento");
				int durata=c.getInt("durata");
				DocSicurezza f=new DocSicurezza(id,documento,durata);
				s.add(f);
			}
			Database.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return s;
	}
	
	
	
public static List<DocSicurezza> documentisicurezza(int id){
		
		List<DocSicurezza> s=new ArrayList<DocSicurezza>();
		
		try {
			Database.connect();
			ResultSet g=Database.selectRecord("documentisicurezza,sicurezzadoc","sicurezzadoc.idazienda="+ id + " AND sicurezzadoc.iddocsic=documentisicurezza.id");
			while(g.next()) {
				int idd=g.getInt("sicurezzadoc.id");
				
				String documento=g.getString("documento");
				//if(g.getDate("data").equals(0)) {
				//String datap="00/00/00";
				 //DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.SHORT, Locale.ITALY);
			      //datas = dateFormat.parse(datap);
				//}else {
			
				 Date datas=g.getDate("sicurezzadoc.data");
				 /*if(datas.equals("NULL")) {}else {
				 SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
				
				 Date datar = simpleDateFormat.parse(datas);
			
				 datas=simpleDateFormat.format(datar);}*/
				System.out.println(datas+" data formattata");
				int flag=g.getInt("flag");
				DocSicurezza f=new DocSicurezza(idd,documento,datas,flag);
				s.add(f);
			}
			Database.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return s;
	}

public static void aggiornadocsic(String dat, int idd){
	try {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		
	      Date datas = simpleDateFormat.parse(dat);
		Map<String,Object> agg=new HashMap<String,Object>();
		Database.connect();
		agg.put("data",simpleDateFormat.format(datas));
		agg.put("flag", 1);
		System.out.println("cambio flaggggg"+ idd);
		
		Database.updateRecord("sicurezzadoc",agg,"sicurezzadoc.id="+idd);
		Database.close();
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}

}
