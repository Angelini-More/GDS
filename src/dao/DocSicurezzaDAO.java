package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
}
