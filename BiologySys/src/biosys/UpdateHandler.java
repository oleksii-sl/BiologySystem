package biosys;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import biosys.model.BioClass;
import biosys.model.Biosystem;
import biosys.model.Living;

import org.apache.log4j.Logger;


/**
 * Servlet implementation class UpdateHandler
 */
public class UpdateHandler extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	private static final Logger log = Logger.getLogger(UpdateHandler.class);

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map<String, String[]> map = request.getParameterMap();
		Biosystem system = (Biosystem)request.getAttribute(Application.MODEL);
		try {
			if (map.containsKey("update_class")) {
				BioClass bioClass = new BioClass();
				bioClass.setId(Integer.parseInt(map.get("id")[0]));
				bioClass.setName(map.get("name")[0]);
				bioClass.setParentId(map.get("parentId")[0]);
				if (log.isDebugEnabled())
					log.debug(bioClass);
				system.updateBioClass(bioClass);
			} else if (map.containsKey("update_living")) {
				Living living = new Living();
				living.setId(Integer.parseInt(map.get("id")[0]));
				living.setName(map.get("name")[0]);
				living.setNameLatin(map.get("nameLatin")[0]);
				living.setLifespan(Integer.parseInt(map.get("lifespan")[0]));
				living.setAvgWeight(Double.parseDouble(map.get("avgWeight")[0]));
				living.setNativeRange(map.get("nativeRange")[0]);
				living.setPopulation(Long.parseLong(map.get("population")[0]));
				living.setBioClass(Integer.parseInt(map.get("bioClass")[0]));
				system.updateLiving(living);
				if (log.isDebugEnabled())
					log.debug(living);
			} else if (map.containsKey("delete_living")) {
				system.removeLiving(Integer.parseInt(map.get("id")[0]));
				log.info("Removing living");
			} else if (map.containsKey("delete_class")) {
				system.removeBioClass(Integer.parseInt(map.get("id")[0]));
				log.info("Removing class");
			} else if (map.containsKey("add_living")) {
				Living living = new Living();
				living.setName(map.get("name")[0]);
				living.setNameLatin(map.get("nameLatin")[0]);
				living.setLifespan(Integer.parseInt(map.get("lifespan")[0]));
				living.setAvgWeight(Double.parseDouble(map.get("avgWeight")[0]));
				living.setNativeRange(map.get("nativeRange")[0]);
				living.setPopulation(Long.parseLong(map.get("population")[0]));
				living.setBioClass(Integer.parseInt(map.get("bioClass")[0]));
				system.addLiving(living);
			} else if (map.containsKey("add_class")) {
				BioClass bioClass = new BioClass();
				bioClass.setName(map.get("name")[0]);
				bioClass.setParentId(map.get("parentId")[0]);
				system.addBioClass(bioClass);
			}
			response.sendRedirect("main");
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

}
