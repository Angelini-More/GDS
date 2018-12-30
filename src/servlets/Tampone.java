package servlets;

import java.io.IOException;
import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
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

/**
 * Servlet implementation class Tampone
 */
@WebServlet("/Tampone")
public class Tampone extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Map data= new HashMap<String, Object>();   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Tampone() {
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
			data.put("lista2", AziendaDAO.lista5((int) s.getAttribute("idarea")));
		data.put("ciao", s.getAttribute("idarea"));
		FreeMarker.process("tampone.html", data, response, getServletContext());
		}
		else {
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

if(premuto.equals("aggtampo")){
	int id=Integer.parseInt(request.getParameter("id"));
	String datascadaudt="";
	Map<String,Object> a=new HashMap<String,Object>();
	
		try {
			Database.connect();
		
		ResultSet azienda=Database.selectRecord("azienda","id=" +id);
		while(azienda.next()){
			SimpleDateFormat caio = new SimpleDateFormat("yyyy-MM-dd");
			Date datac=azienda.getDate("tampone");
			Calendar datan= Calendar.getInstance(TimeZone.getTimeZone("Europe/Rome"),Locale.ITALY);
			datan.setTime(datac);
			datan.add(Calendar.YEAR, 1);
			datac=datan.getTime();
			a.put("tampone", caio.format(datac));
			Database.updateRecord("azienda", a, "id=" + id);
		}
	
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
}

if(premuto.equals("si")) {
	Map<String,Object> f=new HashMap<String,Object>();
	int id=Integer.parseInt(request.getParameter("id"));
	System.out.println(id + "idddddddddddd");
	String auditzero="2001-01-01";
	SimpleDateFormat caio = new SimpleDateFormat("yyyy-MM-dd");
	try {
		Date dataauditt= caio.parse(auditzero);
		f.put("tampone", caio.format(dataauditt));
		Database.connect();
		Database.updateRecord("azienda",f,"azienda.id="+id);
					Database.close();
	} catch (ParseException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		//AziendaDAO.azzeradata(id,1,area);
	
	
		
		}




if(premuto.equals("cerca")){
	String nomeaz=request.getParameter("nomeaz");
	String mese=request.getParameter("mese");
	String anno=request.getParameter("anno");

if(nomeaz!=""){
	try {
		
		if(area==2) {
			data.put("lista2", DataUtil.searchazprtam(nomeaz,area));
		}
		data.put("titolo", 1);
		data.put("mess", nomeaz);
		FreeMarker.process("tampone.html", data, response, getServletContext());
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}

if(mese!="00" && anno!=""){
	try {
	
		if(area==2) {
			data.put("lista2", AziendaDAO.cercam1tamp(mese,anno,area));
			}
		data.put("titolo", 1);
		data.put("mess", mese);
		FreeMarker.process("tampone.html", data, response, getServletContext());
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
}
		}


else {
			response.sendRedirect("Log");
		}
		doGet(request, response);
	}

}
