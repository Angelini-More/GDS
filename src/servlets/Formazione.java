package servlets;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.AziendaDAO;
import dao.CorsistiDAO;
import dao.CorsoDAO;
import model.Corsista;
import model.Corso;
import util.Database;
import util.FreeMarker;
import util.SecurityLayer;

/**
 * Servlet implementation class Formazione
 */
@WebServlet("/Formazione")
public class Formazione extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Map data= new HashMap<String, Object>(); 
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Formazione() {
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
		int ida=(int) s.getAttribute("id");
		System.out.println(ida + "id azienda");
		data.put("ciao", s.getAttribute("idarea"));
		
			try {
				data.put("nomeaz", AziendaDAO.specifica(ida));
			data.put("corsi", CorsoDAO.corsi());
			data.put("corsisti", CorsistiDAO.corsisti(ida));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		FreeMarker.process("formazione.html", data, response, getServletContext());
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
		String scelta=request.getParameter("scelta");
		System.out.println(scelta + " Cosa scegli?");
		Map<String,Object> agg=new HashMap<String,Object>();
		Map<String,Object> agg2=new HashMap<String,Object>();
		Map<String,Object> agg3=new HashMap<String,Object>();
		int id=0;
		if(scelta.equals("corso")){
			String nomecorso=request.getParameter("nomecorso");
			int durata=Integer.parseInt(request.getParameter("durata"));
			agg.put("nome", nomecorso);
			agg.put("durata", durata);
			try {
				Database.connect();
				Database.insertRecord("corso", agg);
				Database.close();
			}
			catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}}
			if(scelta.equals("corsista")){
				int ida=(int) s.getAttribute("id");
				String nomecorsista=request.getParameter("nomecorsista");
				System.out.println(nomecorsista + " nome corsista aggiunto");
				String cognomecorsista=request.getParameter("cognomecorsista");
				System.out.println(nomecorsista + " nome corsista aggiunto");
				int idcorso=Integer.parseInt(request.getParameter("idcorso"));
				
				String data=request.getParameter("data");
				String gg=data.substring(8,10);
				String mm=data.substring(5,7);
				String aa=data.substring(0,4);
				data=gg + "/" +mm+ "/" +aa;
				agg2.put("nome", nomecorsista);
				agg2.put("cognome", cognomecorsista);
				agg2.put("idazienda", ida);
				
				try {
					int durata=0;
					String datascad="";
					Database.connect();
					ResultSet corsos=Database.selectRecord("corso","id=" + idcorso);
					while(corsos.next()){
					 durata=corsos.getInt("durata");
					}
					int anno=Integer.parseInt(data.substring(6,10));
					System.out.println(anno + "annooooooo");
					int mese=Integer.parseInt(data.substring(3,5));
					System.out.println(mese + "meseeeeeee");
					int giorno=Integer.parseInt(data.substring(0,2));
					System.out.println(giorno + "giornooooo");
					anno=anno+durata;
					if(mese<10 && giorno>9){
						datascad=giorno+"/0"+mese+"/"+anno;
						}
						if(giorno<10 && mese>9){
							datascad="0"+giorno+"/"+mese+"/"+anno;
							}
						if(giorno<10 && mese<10){
							datascad="0"+giorno+"/0"+mese+"/"+anno;
							}
						if(giorno>9 && mese>9){
							datascad=giorno+"/"+mese+"/"+anno;
						}
				
					System.out.println(datascad +" datascadenzaaaaa");
					Database.insertRecord("corsista", agg2);
					Database.selectRecord("corsista");
					ResultSet a =Database.selectUltimoId();
					while(a.next()){
						id=a.getInt("id");
						System.out.println(id + "ultimo id inserito");
					}
					agg3.put("id_corsista", id);
					agg3.put("idcorso", idcorso);
					agg3.put("data_inizio", data);
					agg3.put("datascad", datascad);
					Database.insertRecord("acc", agg3);
					Database.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
			
			
			
	}
			if(scelta.equals("elimina")){
				int idcorso=Integer.parseInt(request.getParameter("idcorso"));
				if(idcorso!=0){
					
				
				try {
					Database.connect();
					Database.deleteRecord("acc","idcorso=" + idcorso);
					Database.deleteRecord("corso","id=" + idcorso);
					Database.close();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}}
			
			if(scelta.equals("eliminasi")){
				int idcorsista=Integer.parseInt(request.getParameter("id"));
				int idcorso=Integer.parseInt(request.getParameter("idcorso"));
				try {
					Database.connect();
					Database.deleteRecord("acc","id_corsista=" + idcorsista + " AND idcorso=" +idcorso);
					Database.deleteRecord("corsista","id=" + idcorsista);
					Database.close();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			response.sendRedirect("Formazione");
}else{
	response.sendRedirect("Log");
}
	}		
}
	
