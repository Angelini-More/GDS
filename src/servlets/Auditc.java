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
import util.Database;
import util.FreeMarker;
import util.SecurityLayer;
import model.Azienda;

/**
 * Servlet implementation class Audit
 */
@WebServlet("/Auditc")
public class Auditc extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Map data= new HashMap<String, Object>();  
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Auditc() {
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
		int idarea=(int) s.getAttribute("idarea");
		if(idarea==1) {
		data.put("lista1", AziendaDAO.lista1(idarea));
		}
		if(idarea==3) {
			data.put("lista1", AziendaDAO.lista4(idarea));
		}
		FreeMarker.process("auditc.html", data, response, getServletContext());
		} else {
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
			AziendaDAO.azzeradata(id,0,area);
			
			}
		
		if(premuto.equals("confermaaudc")){
			int id=Integer.parseInt(request.getParameter("id"));
			String datascadaudc="";
			Map<String,Object> a=new HashMap<String,Object>();
			try {
				Database.connect();
				ResultSet azienda=Database.selectRecord("azienda","id=" +id);
				while(azienda.next()){
					if(area==1) {
					String dataauc=azienda.getString("auditc");
					int anno=Integer.parseInt(dataauc.substring(6,10));
					System.out.println(anno + "annooooooo");
					int mese=Integer.parseInt(dataauc.substring(3,5));
					System.out.println(mese + "meseeeeeee");
					int giorno=Integer.parseInt(dataauc.substring(0,2));
					System.out.println(giorno + "giornooooo");
					anno=anno+1;
					if(mese<10 && giorno>9){
						datascadaudc=giorno+"/0"+mese+"/"+anno;
						}
						if(giorno<10 && mese>9){
							datascadaudc="0"+giorno+"/"+mese+"/"+anno;
							}
						if(giorno<10 && mese<10){
							datascadaudc="0"+giorno+"/0"+mese+"/"+anno;
							}
						if(giorno>9 && mese>9){
							datascadaudc=giorno+"/"+mese+"/"+anno;
							}
					a.put("auditc", datascadaudc);
					Database.updateRecord("azienda",a,"id=" + id);
					
					
					}if(area==3) {
						SimpleDateFormat caio = new SimpleDateFormat("yyyy-MM-dd");
						Date datac=azienda.getDate("nuovoauditc");
						Calendar datan= Calendar.getInstance(TimeZone.getTimeZone("Europe/Rome"),Locale.ITALY);
						datan.setTime(datac);
						datan.add(Calendar.YEAR, 1);
						datac=datan.getTime();
						a.put("nuovoauditc", caio.format(datac));
						Database.updateRecord("azienda", a, "id=" + id);
					}
					/*LocalDate date = LocalDate.of(anno, mese, giorno);
					a.put("auditc", date.plusYears(1));
					Database.updateRecord("azienda",a,"id=" + id);*/
				}
				
				Database.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		

		
		if(premuto.equals("cerca")){
			String nomeaz=request.getParameter("nomeaz");
			String mese=request.getParameter("mese");
			String anno=request.getParameter("anno");
			System.out.println(mese+"fasaf");
			if(nomeaz!=""){
				try {
					data.put("lista1", AziendaDAO.cercaaz(nomeaz));
					data.put("titolo", 1);
					data.put("mess", nomeaz);
					FreeMarker.process("auditc.html", data, response, getServletContext());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			if(mese!="00" && anno!=""){
				try {
					data.put("lista1", AziendaDAO.cercam(mese,anno));
					data.put("titolo", 1);
					data.put("mess", mese);
					FreeMarker.process("auditc.html", data, response, getServletContext());
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
