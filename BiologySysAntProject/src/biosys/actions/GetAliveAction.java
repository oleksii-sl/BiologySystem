package biosys.actions;

import java.io.IOException;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import biosys.model.Alive;
import biosys.model.BiosystemDAO;

public class GetAliveAction implements Action {

	@Override
	public void perform(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException {
		
		BiosystemDAO bioSystem = (BiosystemDAO)request.getAttribute("model");
		Map<String, String[]> map = request.getParameterMap();   
		List<String> constraints = new LinkedList<String>();
		String ordercol;
		List<Alive> aliveList;
		
		if (map.containsKey("ordercol") && !map.get("ordercol")[0].isEmpty()) {
			ordercol = (String)request.getParameter("ordercol");
		} else { 
		    ordercol = null;
		}
		if (map.containsKey("nameSubstr") && !map.get("nameSubstr")[0].isEmpty()) {
		    
			constraints.add("substr: " + "name " + map.get("nameSubstr")[0]);
		}
		if (map.containsKey("nameLatinSubstr") && !map.get("nameLatinSubstr")[0].isEmpty()) {
		       
			constraints.add("substr: " + "name_latin " + map.get("nameLatinSubstr")[0]);
		}
		if (map.containsKey("lifespanMin") && map.containsKey("lifespanMax") && 
		        !map.get("lifespanMin")[0].isEmpty() && !map.get("lifespanMax")[0].isEmpty()) {
		    
			constraints.add("between: " + "lifespan " + map.get("lifespanMin")[0] + 
		            " " + map.get("lifespanMax")[0]);
		}
		if (map.containsKey("avgWeightMin") && map.containsKey("avgWeightMax") && 
		           !map.get("avgWeightMin")[0].isEmpty() && !map.get("avgWeightMax")[0].isEmpty()) {
		       
			constraints.add("between: " + "avg_weight " + map.get("avgWeightMin")[0] + 
		               " " + map.get("avgWeightMax")[0]);
		}
		if (map.containsKey("rangeSubstr") && !map.get("rangeSubstr")[0].isEmpty()) {
		       
			constraints.add("substr: " + "native_range " + map.get("rangeSubstr")[0]);
		}
		if (map.containsKey("populatationMin") && map.containsKey("populatationMax") && 
		           !map.get("populatationMin")[0].isEmpty() && !map.get("populatationMax")[0].isEmpty()) {
		       
			constraints.add("between: " + "population " + map.get("populatationMin")[0] + 
		               " " + map.get("populatationMax")[0]);
		}
		if (constraints.isEmpty() && (ordercol == null || ordercol.isEmpty())) {
			aliveList = bioSystem.getAllAlive();
		} else {
        	aliveList = bioSystem.getAllAliveConstraint(ordercol, constraints);
		}
        request.getSession().setAttribute("aliveList", aliveList);
        response.sendRedirect("alive");
	}

}
