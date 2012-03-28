package biosys.actions;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import biosys.model.BioClass;
import biosys.model.BiosystemDAO;

public class GetClassesHierarchyAction implements Action {

    @Override
    public void perform(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
         
        BiosystemDAO bioSystem = (BiosystemDAO)request.getAttribute("model");
        List<BioClass> hierarchyList;
        String id = request.getParameter("id");
        
        if (id == null || id.isEmpty()) {
            hierarchyList = bioSystem.getAllClassesHierarchy();
        } else {
            hierarchyList = bioSystem.getClassesHierarchy(Integer.parseInt(id));
        }
        request.getSession().setAttribute("classesHierarchy", hierarchyList);
        response.sendRedirect("hierarchy");
    }

}
