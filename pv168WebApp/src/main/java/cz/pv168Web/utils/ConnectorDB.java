package cz.pv168Web.utils;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConnectorDB {

   private static Logger LOGGER = LoggerFactory.getLogger(ConnectorDB.class);

   public static DataSource initDatasource() {
      BasicDataSource bsd = new BasicDataSource();

      Properties prop = new Properties();

      String resourceName = "connection.properties";
      ClassLoader loader = Thread.currentThread().getContextClassLoader();

      try (InputStream resourceStream = loader
            .getResourceAsStream(resourceName)) {
         prop.load(resourceStream);

         bsd = new BasicDataSource();
         bsd.setUrl((String) prop.get("connection.url"));
         bsd.setUsername((String) prop.get("connection.username"));
         bsd.setPassword((String) prop.get("connection.password"));
         bsd.setMaxOpenPreparedStatements(50);
         
         LOGGER.debug("Connection properties file Loaded");
         
      } catch (IOException e) {
         LOGGER.error("ERROR : IO  - properties file " + e.toString());
      }


      return bsd;

   }

   public static void close(Connection conn, Statement st) {
      if (st != null) {
         try {
            st.close();
            //LOGGER.debug("Statement Closed");
         } catch (SQLException ex) {
            LOGGER.error("Error when closing statement", ex);
         }
      }
      if (conn != null) {
         try {
            conn.setAutoCommit(true);
         } catch (SQLException ex) {
            LOGGER.error("Error when switching autocommit mode back to true",
                  ex);
         }
         try {
            conn.close();
            //LOGGER.debug("Connection Closed");
         } catch (SQLException ex) {
            LOGGER.error("Error when closing connection", ex);
         }
      }
      
   }
   
   public static void rollback(Connection conn) {
      if (conn != null) {
          try {
              if (conn.getAutoCommit()) {
                  throw new IllegalStateException("Connection is in the autocommit mode!");
              }
              conn.rollback();
              //LOGGER.debug("Rollback completed Loaded");
          } catch (SQLException ex) {
             LOGGER.error("Error when doing rollback", ex);
          }
      }
  }
}
