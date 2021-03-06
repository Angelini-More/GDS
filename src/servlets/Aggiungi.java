package servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import util.Database;
import util.FreeMarker;
import util.SecurityLayer;

/**
 * Servlet implementation class Aggiungi
 */
@WebServlet("/Aggiungi")
public class Aggiungi extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Map data= new HashMap<String, Object>();  
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Aggiungi() {
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
		data.put("ciao", s.getAttribute("idarea"));
		FreeMarker.process("aggiungi_cliente.html", data, response, getServletContext());
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
		SimpleDateFormat caio = new SimpleDateFormat("yyyy-MM-dd");
		Date dataauditc=null;
		Date dataauditt=null;
		Date tampone=null;
		Date presentazione=null;
		if(s!=null){
			try {
			
		String scelta=request.getParameter("inserisci");
		String auditt="";
		String auditc="";
		String tamponef="";
		String presentazionef="";
		if(scelta.equals("yes")){
		Map<String,Object> agg=new HashMap<String,Object>();
		
		 auditc=request.getParameter("auditc");
		 System.out.println(auditc + "visualizza data auditc");
		 auditt=request.getParameter("auditt");
		 tamponef=request.getParameter("tampone");
		 presentazionef=request.getParameter("presentazione");
		
	
		
		if((int) s.getAttribute("idarea")==3 || (int) s.getAttribute("idarea")==2) {
		
			if(!auditc.equals("")) {
			dataauditc= caio.parse(auditc);
	
			} else
			{
				String f="2001-01-01";
				dataauditc= caio.parse(f);
			}
			if(!auditt.equals("")) {
		  dataauditt= caio.parse(auditt);
					}
			else
			{
				String f="2001-01-01";
				 dataauditt= caio.parse(f);
			}
			
		}
		
		if((int) s.getAttribute("idarea")==2) {
			if(!tamponef.equals("")) {
				tampone= caio.parse(tamponef);
		
				} else
				{
					String f="2001-01-01";
					tampone= caio.parse(f);
				}
		}
		
		
		if((int) s.getAttribute("idarea")==4) {
			if(!presentazionef.equals("")) {
				presentazione= caio.parse(presentazionef);
		
				} else
				{
					String f="2001-01-01";
					presentazione= caio.parse(f);
				}
		}
		
		if((int) s.getAttribute("idarea")==1) {
		if(auditc!=""){
		String anno=auditc.substring(0,4);
		System.out.println(anno + "annooooooo");
		String mese=auditc.substring(5,7);
		System.out.println(mese + "meseeeeeee");
		String giorno=auditc.substring(8,10);
		System.out.println(giorno + "giornooooo");
	auditc=giorno+"/"+mese+"/"+anno;
		}
		if(auditt!=""){
	String annot=auditt.substring(0,4);
	System.out.println(annot + "annooooooo");
	String meset=auditt.substring(5,7);
	System.out.println(meset + "meseeeeeee");
	String giornot=auditt.substring(8,10);
	System.out.println(giornot + "giornooooo");
auditt=giornot+"/"+meset+"/"+annot;}
		}
		/*if(auditc.equals(""))
		{
			auditc=null;
		}
		if(auditt.equals(""))
		{
			auditt=null;
		}*/
		agg.put("numero", request.getParameter("numero"));
		agg.put("nome", request.getParameter("nome"));
		agg.put("comune", request.getParameter("comune"));
		agg.put("rappresentante", request.getParameter("rappresentante"));
		agg.put("sede_legale", request.getParameter("sedel"));
		agg.put("sede_operativa", request.getParameter("sedeo"));
		agg.put("codice_fiscale", request.getParameter("codfis"));
		agg.put("iva", request.getParameter("iva"));
		agg.put("email", request.getParameter("email"));
		agg.put("pec", request.getParameter("pec"));
		agg.put("cellulare", request.getParameter("cellulare"));
		agg.put("telefono", request.getParameter("telefono"));
		agg.put("ateco", request.getParameter("ateco"));
		if((int) s.getAttribute("idarea")==1) {
		agg.put("auditc", auditc);
		agg.put("auditt", auditt);
		}
		if((int) s.getAttribute("idarea")==3 || (int) s.getAttribute("idarea")==2) {
			agg.put("auditc", "");
			agg.put("auditt", "");
			agg.put("nuovoauditc", caio.format(dataauditc));
			agg.put("nuovoauditt", caio.format(dataauditt));
			}
		
		if((int) s.getAttribute("idarea")==2) {
			agg.put("tampone", caio.format(tampone));
		}
		
		if((int) s.getAttribute("idarea")==4) {
			agg.put("presentazione", caio.format(presentazione));
			agg.put("cpi", request.getParameter("cpi"));
			agg.put("riferimento", request.getParameter("riferimento"));
		}
		
		agg.put("idarea", s.getAttribute("idarea"));
		agg.put("eventuali", request.getParameter("eventuali"));
		
		try {
			Database.connect();
			Database.insertRecord("azienda", agg);
			Database.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		response.sendRedirect("Home");
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}	
			
	}else{
		response.sendRedirect("Log");
	}
	
		
	}

}
