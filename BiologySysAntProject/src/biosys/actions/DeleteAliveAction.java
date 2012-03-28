package biosys.actions;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import biosys.Application;
import biosys.model.BiosystemDAO;

public class DeleteAliveAction implements Action {

    @Override
    public void perform(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        
        BiosystemDAO bioSystem = (BiosystemDAO)request.getAttribute(Application.MODEL);
        Map<String, String[]> map = request.getParameterMap();
        bioSystem.removeAlive(Integer.parseInt(map.get("id")[0]));
        response.sendRedirect("main");
    }

}
