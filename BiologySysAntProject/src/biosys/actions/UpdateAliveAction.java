package biosys.actions;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import biosys.Application;
import biosys.model.BiosystemDAO;
import biosys.model.Alive;

public class UpdateAliveAction implements Action {

	@Override
	public void perform(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException {
		
		BiosystemDAO bioSystem = (BiosystemDAO)request.getAttribute(Application.MODEL);
		Map<String, String[]> map = request.getParameterMap();
		Alive alive = new Alive();
        alive.setId(Integer.parseInt(map.get("id")[0]));
        alive.setName(map.get("name")[0]);
        alive.setNameLatin(map.get("nameLatin")[0]);
        alive.setLifespan(Integer.parseInt(map.get("lifespan")[0]));
        alive.setAvgWeight(Double.parseDouble(map.get("avgWeight")[0]));
        alive.setNativeRange(map.get("nativeRange")[0]);
        alive.setPopulation(Long.parseLong(map.get("population")[0]));
        alive.setBioClass(Integer.parseInt(map.get("bioClass")[0]));
        bioSystem.updateAlive(alive);
        response.sendRedirect("main");
	}

}
