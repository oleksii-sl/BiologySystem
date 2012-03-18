package biosys;

import java.sql.Connection;
import java.util.Locale;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.sql.DataSource;

import biosys.model.DBBiosystem;

public class Application implements ServletRequestListener {
	
	public static final String MODEL = "model";

	@Override
	public void requestInitialized(ServletRequestEvent event) {
		try {
    		Locale.setDefault(Locale.ENGLISH);
			Context cont = (Context) new InitialContext().lookup("java:comp/env");
			DataSource ds = (DataSource) cont.lookup("connectInfo");
			
			Connection conn = ds.getConnection();
			DBBiosystem dbc = new DBBiosystem(conn);
			event.getServletRequest().setAttribute(MODEL, dbc);
    	}
    	catch (Exception e) {
    		throw new RuntimeException(e);
    	}
	}
	
	@Override
	public void requestDestroyed(ServletRequestEvent event) {
		((DBBiosystem) event.getServletRequest().getAttribute(MODEL)).close();
	}

}
