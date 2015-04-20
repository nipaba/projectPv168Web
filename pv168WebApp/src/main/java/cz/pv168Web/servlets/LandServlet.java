package cz.pv168Web.servlets;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cz.pv168Web.manager.LandManager;
import cz.pv168Web.model.Land;
import cz.pv168Web.utils.DatabaseException;

/**
 * Servlet implementation class LandServlet
 */
@WebServlet("/Land")
public class LandServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private static Logger LOGGER= LoggerFactory.getLogger(LandServlet.class);
	

    public LandServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getPathInfo() == null ? "NotSet" : request.getPathInfo();

        LOGGER.info(" Servlet accessed : LandServlet  GET " + action);

        List<Land> landList = getLandList();
        request.setAttribute("landList", landList);

        request.getRequestDispatcher("/land.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getPathInfo() == null ? "NotSet" : request.getPathInfo();
        LOGGER.info(" Servlet accessed : LandServlet  POST " + action);

        switch (action) {
        case "/Add":
           createLand(request);
           break;
        case "/Remove" : 
           removeLand(request);
        default:
           break;
        }
        List<Land> landList = getLandList();
        request.setAttribute("landList", landList);
        request.getRequestDispatcher("/land.jsp").forward(request, response);

	}
	
	private List<Land> getLandList() {
	    LandManager landManager = (LandManager) this.getServletContext().getAttribute("LandManager");
	    try {
	       return landManager.getLandList();
	    } catch (DatabaseException e) {
	       e.printStackTrace();
	    }
	    return null;
	 }
	
	private void createLand(HttpServletRequest request) {
	    LandManager landManager = (LandManager) this.getServletContext().getAttribute("LandManager");
	    
	    Double size = Double.parseDouble(request.getParameter("size"));
	    Double buildUpArea = Double.parseDouble(request.getParameter("buildUpArea"));
	    String catastralArea = request.getParameter("catastralArea");
	    String type = request.getParameter("type");
	    String notes = request.getParameter("notes");
	      
	    Land land = new Land();
	      
	    land.setSize(size);
	    land.setBuildUpArea(buildUpArea);
	    land.setCatastralArea(catastralArea);
	    land.setType(type);
	    land.setNotes(notes);
        try {
	       landManager.createLand(land);
	    } catch (DatabaseException e) {
	       e.printStackTrace();
	    }

	}
	   
	private void removeLand(HttpServletRequest request) {
	      
	    LandManager landManager = (LandManager) this.getServletContext().getAttribute("LandManager");
	    
	    Long removeId = Long.decode(request.getParameter("removeId"));
	    
	    Land l = new Land();
	    l.setLandID(removeId);
	    try {
	       landManager.removeLand(l);
	    } catch (DatabaseException e) {
	       e.printStackTrace();
	    }
	      
	}

}
