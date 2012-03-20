package biosys;

import java.sql.SQLException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import biosys.model.BioClass;
import biosys.model.BiosystemDAO;

public class AddClassAction implements Action {

	@Override
	public void perform(HttpServletRequest request, HttpServletResponse response)
			throws SQLException {
		
		BiosystemDAO bioSystem = (BiosystemDAO)request.getAttribute(Application.MODEL);
		Map<String, String[]> map = request.getParameterMap();
		BioClass bioClass = new BioClass();
        bioClass.setName(map.get("name")[0]);
        bioClass.setParentId(map.get("parentId")[0]);
        bioSystem.addBioClass(bioClass);

	}

}