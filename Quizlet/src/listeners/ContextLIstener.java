package listeners;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;

import administration.Achievements;
import administration.Administrator;
import user.AccountManager;
import user.DBConnection;
import user.MyDBInfo;
import java.sql.*;

/**
 * Application Lifecycle Listener implementation class ContextLIstener
 *
 */
@WebListener
public class ContextLIstener implements ServletContextListener {
	
	DBConnection con;

    /**
     * Default constructor. 
     */
    public ContextLIstener() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent arg0)  { 
    	con.closeConnection();
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent arg0)  { 
    	con = new DBConnection();
    	ServletContext sc = arg0.getServletContext();
    	sc.setAttribute("Connection", con);
        AccountManager am = new AccountManager(con.getStatement());
        sc.setAttribute("AccountManager", am);
        
        Administrator siteStats = new Administrator();
    	Achievements achieve = new Achievements(siteStats.passConnection());
    	sc.setAttribute("currentStats", siteStats);
    	sc.setAttribute("achieveLookUp", achieve); 
    }
	
}
