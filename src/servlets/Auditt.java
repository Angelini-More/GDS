package servlets;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.AziendaDAO;
import util.DataUtil;
import util.Database;
import util.FreeMarker;
import util.SecurityLayer;
import model.Azienda;

/**
 * Servlet implementation class Audit
 */
@WebServlet("/Auditt")
public class Auditt extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Map data= new HashMap<String, Object>();  
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Auditt() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession s = SecurityLayer.checkSession(request);
		if(s!=null){
		data.clear();
		data.put("ciao", s.getAttribute("idarea"));
		int idarea=(int) s.getAttribute("idarea");
		if(idarea==1) {
		data.put("lista2", AziendaDAO.lista2(idarea));
		
		FreeMarker.process("auditt.html", data, response, getServletContext());}
		if(idarea==3 || idarea==2) {
			data.put("lista2", AziendaDAO.lista3(idarea));
			
			FreeMarker.process("auditt.html", data, response, getServletContext());
		}
		
		
		}else{
			response.sendRedirect("Log");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession s = SecurityLayer.checkSession(request);
		if(s!=null){
String premuto=request.getParameter("tasto");
		
	int area=(int) s.getAttribute("idarea");	
		
		if(premuto.equals("si")){
			int id=Integer.parseInt(request.getParameter("id"));
			System.out.println(id + "idddddddddddd");
			if(area==1) {
			AziendaDAO.azzeradata(id,1,area);
			}
			if(area==3) {
				AziendaDAO.azzeradata(id,1,area);
			}
			}
		

		
		
		if(premuto.equals("confermaaudt")){
			int id=Integer.parseInt(request.getParameter("id"));
			String datascadaudt="";
			Map<String,Object> a=new HashMap<String,Object>();
			try {
				Database.connect();
				ResultSet azienda=Database.selectRecord("azienda","id=" +id);
				while(azienda.next()){
					if(area==1) {
					String dataauc=azienda.getString("auditt");
					int anno=Integer.parseInt(dataauc.substring(6,10));
					System.out.println(anno + "annooooooo");
					int mese=Integer.parseInt(dataauc.substring(3,5));
					System.out.println(mese + "meseeeeeee");
					int giorno=Integer.parseInt(dataauc.substring(0,2));
					System.out.println(giorno + "giornooooo");
					anno=anno+1;
					if(mese<10 && giorno>9){
						datascadaudt=giorno+"/0"+mese+"/"+anno;
						}
						if(giorno<10 && mese>9){
							datascadaudt="0"+giorno+"/"+mese+"/"+anno;
							}
						if(giorno<10 && mese<10){
							datascadaudt="0"+giorno+"/0"+mese+"/"+anno;
							}
						if(giorno>9 && mese>9){
							datascadaudt=giorno+"/"+mese+"/"+anno;
						}
					a.put("auditt", datascadaudt);
					Database.updateRecord("azienda",a,"id=" + id);
				}
				
				if(area==3) {
					SimpleDateFormat caio = new SimpleDateFormat("yyyy-MM-dd");
					Date datac=azienda.getDate("nuovoauditt");
					Calendar datan= Calendar.getInstance(TimeZone.getTimeZone("Europe/Rome"),Locale.ITALY);
					datan.setTime(datac);
					datan.add(Calendar.YEAR, 1);
					datac=datan.getTime();
					a.put("nuovoauditt", caio.format(datac));
					Database.updateRecord("azienda", a, "id=" + id);
				}
				Database.close();
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
		
		if(premuto.equals("cerca")){
			String nomeaz=request.getParameter("nomeaz");
			String mese=request.getParameter("mese");
			String anno=request.getParameter("anno");
		    
			if(nomeaz!=""){
				try {
					if(area==1) {
					data.put("lista2", AziendaDAO.cercaaz(nomeaz,area,2));
					}
					if(area==3 || area==2) {
						data.put("lista2", DataUtil.searchazpr(nomeaz,area,1));
					}
					data.put("titolo", 1);
					data.put("mess", nomeaz);
					FreeMarker.process("auditt.html", data, response, getServletContext());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			if(mese!="00" && anno!=""){
				try {
					if(area==1) {
					data.put("lista2", AziendaDAO.cercam1(mese,anno,area));
					}
					if(area==3 || area==2) {
						data.put("lista2", AziendaDAO.cercam1(mese,anno,area));
						}
					data.put("titolo", 1);
					data.put("mess", mese);
					FreeMarker.process("auditt.html", data, response, getServletContext());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		doGet(request, response);
	}else{
		response.sendRedirect("Log");
	}
	}
}