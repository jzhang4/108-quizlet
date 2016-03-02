package listeners;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import user.AccountManager;
import user.DBConnection;
import user.MyDBInfo;

/**
 * Application Lifecycle Listener implementation class AccountManagerListener
 *
 */
@WebListener
public class AccountManagerListener implements ServletContextListener {

    /**
     * Default constructor. 
     */
    public AccountManagerListener() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent arg0)  { 
         // TODO Auto-generated method stub
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent arg0)  { 
        AccountManager am = new AccountManager();
        ServletContext sc = arg0.getServletContext();
        sc.setAttribute("AccountManager", am);
    }
	
}
