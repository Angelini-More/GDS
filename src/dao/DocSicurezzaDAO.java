package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

import model.DocPrivacy;
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
	Locale.setDefault(Locale.ITALIAN);
		
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
			int durata=g.getInt("documentisicurezza.durata");
			int compar=0;
			SimpleDateFormat formdata = new SimpleDateFormat("yyyy-MM-dd");
				 Date datas=g.getDate("sicurezzadoc.data");
				 String u="2001-01-01";
					
					Date date3=formdata.parse(u);
				 
				if(durata!=0 && datas.compareTo(date3)!=0){
				Calendar datan= Calendar.getInstance(TimeZone.getTimeZone("Europe/Rome"),Locale.ITALY);
				Calendar datao= Calendar.getInstance(TimeZone.getTimeZone("Europe/Rome"),Locale.ITALY);
				
				
				
				String r=formdata.format(datao.getTime());
				System.out.println(r+" stringa data odierna");
				Date date1=formdata.parse(r);
				
				System.out.println(datao.getTime()+" data odierna");
				datan.setTime(datas);
				System.out.println(formdata.format(datan.getTime())+" data originaria");
				
				System.out.println(durata+" durataaaaaa");
				datan.add(Calendar.YEAR, durata);
				String h=formdata.format(datan.getTime());
				Date date2=formdata.parse(h);
				System.out.println(formdata.format(datan.getTime())+" data aggiornata");
				compar=date1.compareTo(date2);
				System.out.println(compar+"comparazione date");
				 /*if(datas.equals("NULL")) {}else {
				 SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
				
				 Date datar = simpleDateFormat.parse(datas);
			
				 datas=simpleDateFormat.format(datar);}*/}
				System.out.println(datas+" data formattata");
				int flag=g.getInt("flag");
				DocSicurezza f=new DocSicurezza(idd,documento,datas,flag,compar);
				s.add(f);
			}
			Database.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return s;
	}





public static List<DocPrivacy> documentiprivacy(int id){
	Locale.setDefault(Locale.ITALIAN);
	
	List<DocPrivacy> s=new ArrayList<DocPrivacy>();
	
	try {
		Database.connect();
		ResultSet g=Database.selectRecord("documentiprivacy,privacydoc","privacydoc.idazienda="+ id + " AND privacydoc.iddocpriv=documentiprivacy.id");
		while(g.next()) {
			int idd=g.getInt("privacydoc.id");
			
			String documento=g.getString("documento");
			SimpleDateFormat formdata = new SimpleDateFormat("yyyy-MM-dd");
			 Date datas=g.getDate("privacydoc.data");
			//if(g.getDate("data").equals(0)) {
			//String datap="00/00/00";
			 //DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.SHORT, Locale.ITALY);
		      //datas = dateFormat.parse(datap);
			//}else {
		
			int flag=g.getInt("flag");
			DocPrivacy f=new DocPrivacy(idd,documento,flag,datas);
			s.add(f);
		}
		Database.close();
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return s;
}


public static List<DocPrivacy> documentihaccp(int id){
	Locale.setDefault(Locale.ITALIAN);
	
	List<DocPrivacy> s=new ArrayList<DocPrivacy>();
	
	try {
		Database.connect();
		ResultSet g=Database.selectRecord("documentihaccp,haccpdoc","haccpdoc.idazienda="+ id + " AND haccpdoc.iddochaccp=documentihaccp.id");
		while(g.next()) {
			int idd=g.getInt("haccpdoc.id");
			
			String documento=g.getString("documento");
			SimpleDateFormat formdata = new SimpleDateFormat("yyyy-MM-dd");
			 Date datas=g.getDate("haccpdoc.data");
			//if(g.getDate("data").equals(0)) {
			//String datap="00/00/00";
			 //DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.SHORT, Locale.ITALY);
		      //datas = dateFormat.parse(datap);
			//}else {
		
			int flag=g.getInt("flag");
			DocPrivacy f=new DocPrivacy(idd,documento,flag,datas);
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
	Locale.setDefault(Locale.ITALIAN);
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


public static void aggiornadocpriv(String dat, int idd){
	Locale.setDefault(Locale.ITALIAN);
	try {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		
	      Date datas = simpleDateFormat.parse(dat);
		Map<String,Object> agg=new HashMap<String,Object>();
		Database.connect();
		agg.put("data",simpleDateFormat.format(datas));
		agg.put("flag", 1);
		System.out.println("cambio flaggggg"+ idd);
		
		Database.updateRecord("privacydoc",agg,"privacydoc.id="+idd);
		Database.close();
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}

public static void aggiornadochaccp(String dat, int idd){
	Locale.setDefault(Locale.ITALIAN);
	try {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		
	      Date datas = simpleDateFormat.parse(dat);
		Map<String,Object> agg=new HashMap<String,Object>();
		Database.connect();
		agg.put("data",simpleDateFormat.format(datas));
		agg.put("flag", 1);
		System.out.println("cambio flaggggg"+ idd);
		
		Database.updateRecord("haccpdoc",agg,"haccpdoc.id="+idd);
		Database.close();
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}

}
