package biosys;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;


/**
 * Servlet implementation class UpdateHandler
 */
public class UpdateHandler extends HttpServlet {
    
    private static final long serialVersionUID = 1L;
    
    private static final Logger log = Logger.getLogger(UpdateHandler.class);
    
    private ActionFactory factory = new ActionFactory();
    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	Map<String, String[]> map = request.getParameterMap();
    	try {
        	factory.create(map.get("action")[0]).perform(request, response);
            response.sendRedirect("main");
        } catch (SQLException e) {
        	log.error(e);
            throw new RuntimeException(e);
        }
    }

}
