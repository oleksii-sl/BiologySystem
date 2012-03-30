package biosys.actions;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import biosys.Application;
import biosys.model.BioClass;
import biosys.model.BiosystemDAO;

public class UpdateClassAction implements Action {

    @Override
    public void perform(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        
        BiosystemDAO bioSystem = (BiosystemDAO)request.getAttribute(Application.MODEL);
        Map<String, String[]> map = request.getParameterMap();
        BioClass bioClass = new BioClass();
        bioClass.setId(Integer.parseInt(map.get("id")[0]));
        bioClass.setName(map.get("name")[0]);
        String s;
        if (!(s = map.get("parentId")[0]).isEmpty())
            bioClass.setParentId(Integer.parseInt(s));
        else
            bioClass.setParentId(null);
        bioSystem.updateBioClass(bioClass);
        response.sendRedirect("main");
    }

}
