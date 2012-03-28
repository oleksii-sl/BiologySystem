package biosys.actions;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Action {
    
    public void perform(HttpServletRequest request, HttpServletResponse response) 
            throws SQLException, IOException;
}
