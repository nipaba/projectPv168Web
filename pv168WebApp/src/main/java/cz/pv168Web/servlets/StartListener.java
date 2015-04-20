package cz.pv168Web.servlets;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cz.pv168Web.manager.LandManager;
import cz.pv168Web.manager.OwnershipManager;
import cz.pv168Web.manager.PersonManager;
import cz.pv168Web.manager.impl.LandManagerImpl;
import cz.pv168Web.manager.impl.OwnershipManagerImpl;
import cz.pv168Web.manager.impl.PersonManagerImpl;
import cz.pv168Web.utils.ConnectorDB;
import cz.pv168Web.utils.DatabaseException;

/**
 * Application Lifecycle Listener implementation class StartListener
 *
 */
@WebListener
public class StartListener implements ServletContextListener {

   private static Logger LOGGER = LoggerFactory.getLogger(StartListener.class);

   PersonManager         personManager;
   LandManager           landManager;
   OwnershipManager      ownershipManager;
   
    public StartListener() {
    }

    public void contextDestroyed(ServletContextEvent arg0)  { 
       destroyTables();
       LOGGER.info("Web ended");
    }

    public void contextInitialized(ServletContextEvent ev)  { 
       LOGGER.info("Web started");

       ServletContext servletContext = ev.getServletContext();

       DataSource ds = ConnectorDB.initDatasource();

       try {
          Class.forName("org.apache.derby.jdbc.EmbeddedDriver");// org.apache.derby.jdbc.EmbeddedDriver,org.apache.derby.jdbc.ClientDriver
       } catch (ClassNotFoundException e1) {
          e1.printStackTrace();
       }

       personManager = new PersonManagerImpl(ds);
       landManager = new LandManagerImpl(ds);
       ownershipManager = new OwnershipManagerImpl(ds);

       initTables();

       servletContext.setAttribute("DataSource", ds);
       servletContext.setAttribute("LandManager", landManager);
       servletContext.setAttribute("PersonManager", personManager);
       servletContext.setAttribute("OwnershipManager", ownershipManager);
    }
    // --------------------------------------------------------------
    private void initTables() {
       try {
          personManager.createTablePerson();
       } catch (DatabaseException e) {
          LOGGER.error("Person Table exists");
       }

       try {
          landManager.createTableLand();
       } catch (DatabaseException e) {
          LOGGER.error("Land Table exists");
       }
       try {
          ownershipManager.createTableOwnership();
       } catch (DatabaseException e) {
          LOGGER.error("Ownership Table exists");
       }
    }

    private void destroyTables() {
       try {
          ownershipManager.dropTableOwnership();
          personManager.droptablePerson();
          landManager.dropTableLand();
       } catch (DatabaseException e) {
          e.printStackTrace();
       }
    }
}
