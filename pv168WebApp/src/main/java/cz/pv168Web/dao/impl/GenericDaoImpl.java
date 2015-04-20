package cz.pv168Web.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cz.pv168Web.utils.ConnectorDB;
import cz.pv168Web.utils.DatabaseException;

public class GenericDaoImpl {
   protected final DataSource dataSource;
   private static Logger    LOGGER = LoggerFactory
                                         .getLogger(GenericDaoImpl.class);

   public GenericDaoImpl(DataSource dataSource) {
      this.dataSource = dataSource;
   }
   
   public void connectAndExecute(String sql) throws DatabaseException{
      Connection conn = null;
      PreparedStatement st = null;
      try {
         conn = dataSource.getConnection();
         st = conn.prepareStatement(sql.toString(),
               Statement.RETURN_GENERATED_KEYS);
         st.executeUpdate();

      } catch (SQLException e1) {
         throw new DatabaseException("db connection problem" + e1);
      }
      finally {
         ConnectorDB.close(conn, st);
      }

   }
}
