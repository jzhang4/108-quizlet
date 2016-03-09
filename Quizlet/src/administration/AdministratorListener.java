package administration;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * Application Lifecycle Listener implementation class SiteStatisticsListener
 *
 */
@WebListener
public class AdministratorListener implements HttpSessionListener {
	public Administrator siteStats;
	public Achievements achieve;
    /**
     * Default constructor. 
     */
    public AdministratorListener() {
    }

	/**
     * @see HttpSessionListener#sessionCreated(HttpSessionEvent)
     */
    public void sessionCreated(HttpSessionEvent arg0)  { 
//    	siteStats = new Administrator();
//    	achieve = new Achievements(siteStats.passConnection());
//        HttpSession session = arg0.getSession();	
//    	session.setAttribute("currentStats", siteStats);
//    	session.setAttribute("achieveLookUp", achieve);
    }

	/**
     * @see HttpSessionListener#sessionDestroyed(HttpSessionEvent)
     */
    public void sessionDestroyed(HttpSessionEvent arg0)  { 
         
    }
	
}
