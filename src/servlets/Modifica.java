package servlets;

import java.io.IOException;
import java.sql.SQLException;
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
import util.Database;
import util.FreeMarker;
import util.SecurityLayer;

/**
 * Servlet implementation class Modifica
 */
@WebServlet("/Modifica")
public class Modifica extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Map data= new HashMap<String, Object>();   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Modifica() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int id=0;
HttpSession s = SecurityLayer.checkSession(request);
if(s!=null){
	data.put("ciao", s.getAttribute("idarea"));
		
		id=Integer.parseInt(request.getParameter("id"));
		data.put("azienda", AziendaDAO.specifica(id));
		FreeMarker.process("modifica.html", data, response, getServletContext());
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
		String decisione=request.getParameter("decisione");
		if(decisione.equals("salva")){
		int id=Integer.parseInt(request.getParameter("id"));
		String numero=request.getParameter("numero");
		String nome=request.getParameter("nome");
		System.out.println(nome + "nomeeeeeeee");
		String comune=request.getParameter("comune");
		String rappresentante=request.getParameter("rappresentante");
		String sedel=request.getParameter("sedel");
		String sedeo=request.getParameter("sedeo");
		String codicef=request.getParameter("codfis");
		String iva=request.getParameter("iva");
		String email=request.getParameter("email");
		String pec=request.getParameter("pec");
		String cellulare=request.getParameter("cellulare");
		String telefono=request.getParameter("telefono");
		String ateco=request.getParameter("ateco");
		String auditc=request.getParameter("auditc");
		String auditt=request.getParameter("auditt");
		String tamponef="";
		String presentazionef="";
		String cpi="";
		String riferimento="";
		String eventuali=request.getParameter("eventuali");
		
		if((int) s.getAttribute("idarea")==2) {
			tamponef=request.getParameter("tampone");
		}
		if((int) s.getAttribute("idarea")==4) {
			 cpi=request.getParameter("cpi");
			 riferimento=request.getParameter("riferimento");
			presentazionef=request.getParameter("presentazione");
		}
		SimpleDateFormat formdata = new SimpleDateFormat("yyyy-MM-dd");
		String h="0001-01-01";
		Date dataauditt=null;
		Date dataauditc=null;
		Date tampone=null;
		Date presentazione=null;
			



	 
		
		
		Map<String,Object> agg=new HashMap<String,Object>();
		if(numero!=""){
			agg.put("numero", numero);
		}
		
		if(nome!=""){
			agg.put("nome", nome);
		}
		
		if(comune!=""){
			agg.put("comune", comune);
		}
		
		if(rappresentante!=""){
			agg.put("rappresentante", rappresentante);
		}
		
		if(sedel!=""){
			agg.put("sede_legale", sedel);
		}
		
		if(sedeo!=""){
			agg.put("sede_operativa", sedeo);
		}
		
		if(codicef!=""){
			agg.put("codice_fiscale", codicef);
		}
		
		if(iva!=""){
			agg.put("iva", iva);
		}
		
		if(email!=""){
			agg.put("email", email);
		}
		
		if(pec!=""){
			agg.put("pec", pec);
		}
		
		if(cellulare!=""){
			agg.put("cellulare", cellulare);
		}
		
		if(telefono!=""){
			agg.put("telefono", telefono);
		}
		
		if(ateco!=""){
			agg.put("ateco", ateco);
		}
		
		if((int) s.getAttribute("idarea")==1) {
		if(auditc!=""){
			String gg=auditc.substring(8,10);
			String mm=auditc.substring(5,7);
			String aa=auditc.substring(0,4);
			auditc=gg + "/" +mm+ "/" +aa;
			agg.put("auditc", auditc);
		}
		
		if(auditt!=""){
			String gg=auditt.substring(8,10);
			String mm=auditt.substring(5,7);
			String aa=auditt.substring(0,4);
			auditt=gg + "/" +mm+ "/" +aa;
			agg.put("auditt", auditt);
		}}
		
		if((int) s.getAttribute("idarea")==3 || (int) s.getAttribute("idarea")==2) {
			if(auditc!="") {
				


				 try {
					 SimpleDateFormat caio = new SimpleDateFormat("yyyy-MM-dd");
					 dataauditc=formdata.parse(auditc);
						
						
					
					agg.put("nuovoauditc", formdata.format(dataauditc));
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			if(auditt!="") {
				 try {
					 SimpleDateFormat caio = new SimpleDateFormat("yyyy-MM-dd");
					 dataauditt=formdata.parse(auditt);
						
					agg.put("nuovoauditt", formdata.format(dataauditt));
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				 }
					
			
		}
		
		
		if((int) s.getAttribute("idarea")==4) {
			if(presentazionef!="") {
				 try {
					 
					 presentazione=formdata.parse(presentazionef);
					 if(riferimento!=""){
							agg.put("riferimento", riferimento);
						}
					 if(cpi!=""){
							agg.put("cpi", cpi);
						}
						
					
					agg.put("presentazione", formdata.format(presentazione));
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}}
		
		
		if((int) s.getAttribute("idarea")==2) {
			if(tamponef!="") {
				 try {
					 
					 tampone=formdata.parse(tamponef);
						
						
					
					agg.put("tampone", formdata.format(tampone));
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}}
		
		if(eventuali!=""){
			agg.put("eventuali", eventuali);
		}
		
		try {
			Database.connect();
			Database.updateRecord("azienda", agg, "azienda.id=" + id);
			Database.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		response.sendRedirect("Home");
	} else{
		response.sendRedirect("Home");
	}
		}else{
			response.sendRedirect("Log");
		}
		}

}
