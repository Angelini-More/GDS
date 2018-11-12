package servlets;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Calendar;
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
import dao.CorsistiDAO;
import dao.CorsoDAO;
import util.Database;
import util.FreeMarker;
import util.SecurityLayer;

/**
 * Servlet implementation class Note
 */
@WebServlet("/Note")
public class Note extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Map data= new HashMap<String, Object>();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Note() {
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
			int idazie= (int) s.getAttribute("id");
			try {
				data.put("note", AziendaDAO.noteaz(idazie));
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		FreeMarker.process("note.html", data, response, getServletContext());
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession s = SecurityLayer.checkSession(request);
		if(s!=null){
		int ida=(int) s.getAttribute("id");
		System.out.println(ida + "id azienda");
		
			try {
				Database.connect();
				Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("Europe/Rome"),Locale.ITALY);
				 Date today = calendar.getTime();
				 SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
				 System.out.println(simpleDateFormat.format(calendar.getTime()));
				 System.out.println(simpleDateFormat.format(today) + "datacorrente");
				 String testo=request.getParameter("testo");
				 Map<String,Object> as=new HashMap<String,Object>();
				 as.put("testo", testo);
				 as.put("data", simpleDateFormat.format(today));
				 as.put("idazienda", ida);
				 Database.insertRecord("note", as);
				 
			Database.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
			response.sendRedirect("Note");
		}else{
			response.sendRedirect("Note");
		}
	}

}
