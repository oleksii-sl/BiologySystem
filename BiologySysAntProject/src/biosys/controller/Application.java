package biosys.controller;

import java.util.Locale;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.sql.DataSource;

import biosys.model.DBBiosystem;
import biosys.model.BiosystemDAO;

/**
 * Class used to listen user requests and creates instance of Model for
 * current request.
 * @author Алексей
 */
public class Application implements ServletRequestListener {

    /**
     * String identificator for Model object in MVC paradigm
     */
    public static final String MODEL = "model";

    /* (non-Javadoc)
     * @see javax.servlet.ServletRequestListener#requestInitialized(javax.servlet.ServletRequestEvent)
     */
    @Override
    public void requestInitialized(ServletRequestEvent event) {
        try {
            Locale.setDefault(Locale.ENGLISH);
            Context cont = (Context) new InitialContext().lookup("java:comp/env");
            DataSource ds = (DataSource) cont.lookup("connectInfo");
            BiosystemDAO dbc = new DBBiosystem(ds);
            event.getServletRequest().setAttribute(MODEL, dbc);
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Method doesn't do anything
     */
    @Override
    public void requestDestroyed(ServletRequestEvent event) {

    }

}
