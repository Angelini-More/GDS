package servlets;

import java.io.IOException;
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

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.DocSicurezzaDAO;
import util.Database;
import util.FreeMarker;
import util.SecurityLayer;

/**
 * Servlet implementation class DocPrivacy
 */
@WebServlet("/DocSicurezza")
public class DocSicurezza extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Map data= new HashMap<String, Object>();  
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DocSicurezza() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession s = SecurityLayer.checkSession(request);
		int cont=0;
		Map<String,Object> agg=new HashMap<String,Object>();
		if(s!=null){
			
		int ida=(int) s.getAttribute("id");
		ResultSet r;
		try {
			Database.connect();
			r = Database.selectRecord("sicurezzadoc","idazienda="+ ida);
			while(r.next()) {
				cont++;
			}
			Date datas=null;
			if(cont==0) {
				ResultSet p=Database.selectRecord("documentisicurezza");
				while(p.next()) {
					int ids=p.getInt("id");
					agg.put("flag", 0);
					String datap="0000-00-00";
					SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
				
				      datas = simpleDateFormat.parse(datap);
					agg.put("data", simpleDateFormat.format(datas));
					agg.put("iddocsic", ids);
					agg.put("idazienda", ida);
					Database.insertRecord("sicurezzadoc", agg);
				}
			
				
			}
		Database.close();
			data.put("documentis", DocSicurezzaDAO.documentisicurezza(ida));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		FreeMarker.process("docsicurezza.html", data, response, getServletContext());}
		else {
			response.sendRedirect("Log");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		FreeMarker.process("docprivacy.html", data, response, getServletContext());
	}

}
