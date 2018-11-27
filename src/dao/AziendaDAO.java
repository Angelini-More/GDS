package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.Azienda;
import model.Note;
import util.DataUtil;
import util.Database;


public class AziendaDAO implements AziendaDAO_interface {
	
	public static List<Azienda> lista(int area){
		List<Azienda> aziende=new ArrayList<Azienda>();
		try {
			Database.connect();
			ResultSet listaaz=Database.selectRecordCond2("azienda"," azienda.idarea=" + area, " CAST(numero AS SIGNED) ASC");
			while(listaaz.next()){
				int idarea=listaaz.getInt("idarea");
				
				int id=listaaz.getInt("id");
				String numero=listaaz.getString("numero");
				String nome=listaaz.getString("nome");
				String comune=listaaz.getString("comune");
				
				Azienda c=new Azienda(id,numero, nome,comune);
				aziende.add(c);
				
				
			

			}
			Database.close();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return aziende;
	}
	
	public static List<Azienda> lista1(int idarea){
		List<Azienda> aziende1=new ArrayList<Azienda>();
		try {
			Database.connect();
			ResultSet listaaz1=Database.selectRecordCond2("azienda","azienda.idarea="+idarea, "`azienda`.`auditc` ASC");
			while(listaaz1.next()){
				
				int id=listaaz1.getInt("id");
				String numero=listaaz1.getString("numero");
				String nome=listaaz1.getString("nome");
				String comune=listaaz1.getString("comune");
				String auditc=listaaz1.getString("auditc");
				
				

				
		
				if(!(auditc.equals(""))) {
					System.out.println(auditc + " dataaaaaaa");

				Azienda z=new Azienda(id,numero, nome,comune,auditc,idarea);
				aziende1.add(z);
			} }
			Database.close();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return aziende1;
	}
	
	public static List<Azienda> lista2(int idarea){
		List<Azienda> aziende2=new ArrayList<Azienda>();
		try {
			Database.connect();
			ResultSet listaaz2=Database.selectRecordCond2("azienda","azienda.idarea="+idarea, "`azienda`.`auditt` ASC");
			while(listaaz2.next()){
				int id=listaaz2.getInt("id");
				String numero=listaaz2.getString("numero");
				String nome=listaaz2.getString("nome");
				String comune=listaaz2.getString("comune");
				String auditc=listaaz2.getString("auditc");
				
				String auditt=listaaz2.getString("auditt");

				
		
				if(!auditt.equals("")) {
				

				Azienda x=new Azienda(id,numero, nome,comune,auditc,auditt,idarea);
				aziende2.add(x);
			} }
			Database.close();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return aziende2;
	}
	
	
	
	
	
	public static List<Azienda> lista3(int idarea){
		List<Azienda> aziende2=new ArrayList<Azienda>();
		try {
			Database.connect();
			ResultSet listaaz2=Database.selectRecordCond2("azienda","azienda.idarea="+idarea, "azienda.nuovoauditt ASC");
			while(listaaz2.next()){
				int id=listaaz2.getInt("id");
				String numero=listaaz2.getString("numero");
				String nome=listaaz2.getString("nome");
				String comune=listaaz2.getString("comune");
				
				
				
				Date auditt=listaaz2.getDate("nuovoauditt");

				
				String u="0001-01-01";
				SimpleDateFormat formdata = new SimpleDateFormat("yyyy-MM-dd");
				Date date3=formdata.parse(u);
				if(auditt.compareTo(date3)==1 || auditt.compareTo(date3)==-1) {
				

				Azienda x=new Azienda(id,numero, nome,comune,auditt,idarea);
				aziende2.add(x);
			} }
			Database.close();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return aziende2;
	}
	
	
	
	
	
	public static List<Azienda> lista4(int idarea){
		List<Azienda> aziende2=new ArrayList<Azienda>();
		try {
			Database.connect();
			ResultSet listaaz2=Database.selectRecordCond2("azienda","azienda.idarea="+idarea, "azienda.nuovoauditc ASC");
			while(listaaz2.next()){
				int id=listaaz2.getInt("id");
				String numero=listaaz2.getString("numero");
				String nome=listaaz2.getString("nome");
				String comune=listaaz2.getString("comune");
				
				
				
				Date auditc=listaaz2.getDate("nuovoauditc");

				
				String u="0001-01-01";
				SimpleDateFormat formdata = new SimpleDateFormat("yyyy-MM-dd");
				Date date3=formdata.parse(u);
				if(auditc.compareTo(date3)==1 || auditc.compareTo(date3)==-1) {
				

				Azienda x=new Azienda(id,numero, nome,comune,auditc,idarea);
				aziende2.add(x);
			} }
			Database.close();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return aziende2;
	}
	
	
	
	
	
	
	
	public static Azienda specifica(int id){
		Azienda azi=null;
		try {
			Database.connect();
			ResultSet az=Database.selectRecord("azienda","id=" + id);
			while(az.next()){
				int idarea=az.getInt("idarea");
				
				String numero=az.getString("numero");
				String nome=az.getString("nome");
				System.out.println(nome + "nomeeeeeeee");
				String comune=az.getString("comune");
				String rappresentante=az.getString("rappresentante");
				String sedel=az.getString("sede_legale");
				String sedeo=az.getString("sede_operativa");
				String codicef=az.getString("codice_fiscale");
				String iva=az.getString("iva");
				String email=az.getString("email");
				String pec=az.getString("pec");
				String cellulare=az.getString("cellulare");
				String telefono=az.getString("telefono");
				String ateco=az.getString("ateco");
				
			
				String eventuali=az.getString("eventuali");
				
				System.out.println(idarea+" id area percheeeeeee");
				if(idarea==1) {
					String auditc=az.getString("auditc");
					
					String auditt=az.getString("auditt");
				azi=new Azienda(id,numero,nome,comune,rappresentante,sedel,sedeo,codicef,iva,email,pec,cellulare,telefono,ateco,auditc,auditt,eventuali,idarea);
				}
				if(idarea==3) {
					Date nuovoauditc=az.getDate("nuovoauditc");
					Date nuovoauditt=az.getDate("nuovoauditt");
					azi=new Azienda(id,numero,nome,comune,rappresentante,sedel,sedeo,codicef,iva,email,pec,cellulare,telefono,ateco,nuovoauditc,nuovoauditt,eventuali,idarea);
				}
			}
			Database.close();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return azi;
	}
	
	public static void cancella(int id, int area){
		
		try {
			Database.connect();
			if(area==1) {
			ResultSet i=Database.selectRecord("corsista","corsista.idazienda=" + id);
			while(i.next()){
				int idc=i.getInt("id");
				Database.deleteRecord("acc", "acc.id_corsista=" +idc);
			}
			Database.deleteRecord("corsista", "corsista.idazienda=" + id);
			Database.deleteRecord("sicurezzadoc","sicurezzadoc.idazienda="+id);
			Database.deleteRecord("note","note.idazienda="+id);
			Database.deleteRecord("azienda", "id = " + id);
			}
			if(area==3) {
				Database.deleteRecord("privacydoc","privacydoc.idazienda="+id);
				Database.deleteRecord("note","note.idazienda="+id);
				Database.deleteRecord("azienda", "id = " + id);
			}
			Database.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
public static void azzeradata(int id,int serve, int area){
		Map<String,Object> f=new HashMap<String,Object>();
		try {
			String a="";
			String auditzero="0001-01-01";
			SimpleDateFormat caio = new SimpleDateFormat("yyyy-MM-dd");
			Date dataauditt= caio.parse(auditzero);
			if(serve==1 && area==1){
				f.put("auditt", a);	
			} 
			
			if(serve==1 && area==3){
				f.put("nuovoauditt", caio.format(dataauditt));	
			} 
			
			if(serve!=1 && area==3){
				f.put("nuovoauditc", caio.format(dataauditt));	
			} 
			if(serve!=1 && area==1) {
				f.put("auditc", a);
			}
			
			Database.connect();
Database.updateRecord("azienda",f,"azienda.id="+id);
			Database.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static List<Azienda> cerca(String cercaaz, String cercacom, int area) throws Exception{
		
		List<Azienda> cercate=new ArrayList<Azienda>();
		
				
			
				if(cercaaz!="" && cercacom==""){
				return DataUtil.searchaz(cercaaz,area,1);
				}
				
				if(cercacom!="" && cercaaz==""){
					return DataUtil.searchcom(cercacom,area);
				}
				
				
				if(cercacom!="" && cercaaz!=""){
					return DataUtil.search(cercaaz,cercacom,area);
				}
				
				
				
				
			
		 
			
		
	
		return cercate;}
	
	
	
	public static List<Azienda> cercaaz(String cercaaz, int area, int t) throws Exception{
		
		
		
				return DataUtil.searchaz(cercaaz,area,t);
				
				
			}
	
	public static List<Azienda> cercam(String mese, String anno, int area) throws Exception{
		List<Azienda> lis=new ArrayList<Azienda>();
		Database.connect();
		if(area==1) {
		ResultSet listaz=Database.selectRecord("azienda","idarea="+ area);
		while(listaz.next()){
			String auditc=listaz.getString("auditc");
			if(!auditc.equals("")){
			String meseaudc=auditc.substring(3,5);
			String annoaudc=auditc.substring(6,10);
			if(meseaudc.equals(mese) && annoaudc.equals(anno)){
				int id=listaz.getInt("id");
				
            	String auditt=listaz.getString("auditt");
                String nome = listaz.getString("nome");
                String comune = listaz.getString("comune");
                String numero = listaz.getString("numero");
                
                Azienda n=new Azienda(id,numero,nome,comune,auditc,auditt,area);
              lis.add(n);
			}
			}
		}
		}
		
		if(area==3) {
			ResultSet listaz=Database.selectDate("azienda","MONTH(nuovoauditc)=" + mese +" AND YEAR(nuovoauditc)=" + anno,"nuovoauditc ASC");
			while(listaz.next()) {
				int id=listaz.getInt("id");
				  String nome = listaz.getString("nome");
	                String comune = listaz.getString("comune");
	                String numero = listaz.getString("numero");
	                Date nuovoauditc=listaz.getDate("nuovoauditc");
	                Azienda n=new Azienda(id,numero,nome,comune,nuovoauditc,area);
	                lis.add(n);
			}
		}
		
		Database.close();
		return lis;
		
		
	}
	
	public static List<Azienda> cercam1(String mese,String anno,int area) throws Exception{
		List<Azienda> lis=new ArrayList<Azienda>();
		Database.connect();
		if(area==1) {
		ResultSet listaz=Database.selectRecord("azienda","idarea=" + area);
		while(listaz.next()){
			String auditt=listaz.getString("auditt");
			System.out.println(auditt + "ce l'auditt qui dentro");
			if(!auditt.equals("")){
				System.out.println("entriiiiii quiiiiii");
			String meseaudc=auditt.substring(3,5);
			String annoaudt=auditt.substring(6,10);
			System.out.println(meseaudc + "mese degli audit tecnici");
			if(meseaudc.equals(mese) && annoaudt.equals(anno)){
				int id=listaz.getInt("id");
				
            	String auditc=listaz.getString("auditc");
                String nome = listaz.getString("nome");
                String comune = listaz.getString("comune");
                String numero = listaz.getString("numero");
                
                Azienda n=new Azienda(id,numero,nome,comune,auditc,auditt,area);
              lis.add(n);
			}
			}
		}
		}
		if(area==3) {
			ResultSet listaz=Database.selectDate("azienda","MONTH(nuovoauditt)=" + mese +" AND YEAR(nuovoauditt)=" + anno,"nuovoauditt ASC");
			while(listaz.next()) {
				int id=listaz.getInt("id");
				  String nome = listaz.getString("nome");
	                String comune = listaz.getString("comune");
	                String numero = listaz.getString("numero");
	                Date nuovoauditt=listaz.getDate("nuovoauditt");
	                Azienda n=new Azienda(id,numero,nome,comune,nuovoauditt,area);
	                lis.add(n);
			}
		}
		Database.close();
		return lis;
		
		
	}
	
	public static List<Note> noteaz(int id) throws Exception{
		List<Note> lis=new ArrayList<Note>();
		Database.connect();
		ResultSet listaz=Database.selectRecord("note","idazienda=" + id);
		while(listaz.next()){
			String testo=listaz.getString("testo");
		
				int idn=listaz.getInt("id");
				
            
             Date data=listaz.getDate("data");
           
                Note n=new Note(idn,testo,data);
              lis.add(n);
			
			
		}
		Database.close();
		return lis;
		
		
	}
}
