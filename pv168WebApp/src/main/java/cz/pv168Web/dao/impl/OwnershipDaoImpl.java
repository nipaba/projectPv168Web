package cz.pv168Web.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cz.pv168Web.dao.OwnershipDao;
import cz.pv168Web.model.*;
import cz.pv168Web.utils.ConnectorDB;
import cz.pv168Web.utils.DatabaseException;
import cz.pv168Web.utils.EntityException;

public class OwnershipDaoImpl extends GenericDaoImpl implements OwnershipDao {

   private static Logger LOGGER = LoggerFactory.getLogger(PersonDaoImpl.class);

   public OwnershipDaoImpl(DataSource dataSource) {
      super(dataSource);
   }

   // ============================================================================================
   /*
    * (non-Javadoc)
    * 
    * @see
    * cz.pv168Web.dao.impl.OwnershipDao#createOwnership(cz.pv168Web.model.Ownership)
    */
   public void createOwnership(Ownership ownership) throws DatabaseException {
      StringBuilder sql = new StringBuilder();
      sql.append(" insert into proj.Ownership ");
      sql.append(" (personID,landId,start_date,end_date) ");
      sql.append(" values(?,?,?,?)");
      Connection conn = null;
      PreparedStatement st = null;
      try {
         validateOwnership(ownership);
         conn = dataSource.getConnection();
         conn.setAutoCommit(false);

         st = conn.prepareStatement(sql.toString(),
               Statement.RETURN_GENERATED_KEYS);
         setStatementFromOwnership(st, ownership, false);

         if (st.executeUpdate() != 1) {
            throw new DatabaseException(
                  "Internal Error: more or none rows were inserted "
                        + ownership.toString());
         }
         ResultSet rset = st.getGeneratedKeys();
         rset.next();
         ownership.setLandId(rset.getLong(1));

         conn.commit();

      } catch (SQLException | EntityException e) {
         throw new DatabaseException("db connection problem" + e);
      } finally {
         ConnectorDB.close(conn, st);
      }

      LOGGER.debug("Ownership created : " + ownership.toString());
   }

   // ============================================================================================
   /*
    * (non-Javadoc)
    * 
    * @see
    * cz.pv168Web.dao.impl.OwnershipDao#updateOwnerShip(cz.pv168Web.model.Ownership)
    */
   public void updateOwnerShip(Ownership ownership) throws DatabaseException {
      StringBuilder sql = new StringBuilder();
      sql.append(" UPDATE OWNERSHIP ");
      sql.append(" SET  ");
      sql.append(" personID = ?,");
      sql.append(" landId = ?,");
      sql.append(" start_date = ?,");
      sql.append(" end_date = ?");
      sql.append(" WHERE  ownerShipID = ? ");

      Connection conn = null;
      PreparedStatement st = null;
      try {

         validateOwnership(ownership);

         conn = dataSource.getConnection();
         conn.setAutoCommit(false);
         st = conn.prepareStatement(sql.toString(),
               Statement.RETURN_GENERATED_KEYS);

         setStatementFromOwnership(st, ownership, true);
         st.executeUpdate();
         conn.commit();
      } catch (SQLException | EntityException e1) {
         throw new DatabaseException("db connection problem" + e1);
      } finally {
         ConnectorDB.close(conn, st);
      }

      LOGGER.debug("Ownership UPDATED");
   }

   // ============================================================================================
   /*
    * (non-Javadoc)
    * 
    * @see
    * cz.pv168Web.dao.impl.OwnershipDao#removeOwnership(cz.pv168Web.model.Ownership)
    */
   public void removeOwnership(Ownership ownership) throws DatabaseException {
      StringBuilder sql = new StringBuilder();
      sql.append(" DELETE FROM OWNERSHIP");
      sql.append(" WHERE ownerShipID = ? ");

      Connection conn = null;
      PreparedStatement st = null;
      try {
         conn = dataSource.getConnection();
         conn.setAutoCommit(false);
         st = conn.prepareStatement(sql.toString(),
               Statement.RETURN_GENERATED_KEYS);
         st.setLong(1, ownership.getOwnerShipID());
         st.executeUpdate();
         conn.commit();
      } catch (SQLException e1) {
         throw new DatabaseException("db connection problem" + e1);
      } finally {
         ConnectorDB.close(conn, st);
      }
      LOGGER.debug("Ownership with id :" + ownership.getOwnerShipID()
            + "  removed");
   }

   // ============================================================================================
   /*
    * (non-Javadoc)
    * 
    * @see cz.pv168Web.dao.impl.OwnershipDao#getListOfPersonsOfLand()
    */
   public List<Person> getListOfPersonsOfLand() {
      return null;
   }

   // ============================================================================================
   /*
    * (non-Javadoc)
    * 
    * @see cz.pv168Web.dao.impl.OwnershipDao#getListOfLandsOfPerson()
    */
   public List<Land> getListOfLandsOfPerson() {
      return null;
   }

   // ============================================================================================
   /*
    * (non-Javadoc)
    * 
    * @see cz.pv168Web.dao.impl.OwnershipDao#getOwnershipList()
    */
   public List<Ownership> getOwnershipList() throws DatabaseException {

      StringBuilder sql = new StringBuilder();
      List<Ownership> list = new ArrayList<Ownership>();
      sql.append(" SELECT ownerShipID,personID,landId,start_date,end_date");
      sql.append(" FROM Ownership ");

      Connection conn = null;
      PreparedStatement st = null;
      try {
         conn = dataSource.getConnection();
         st = conn.prepareStatement(sql.toString(),
               Statement.RETURN_GENERATED_KEYS);
         ResultSet rset = st.executeQuery();
         while (rset.next()) {
            list.add(setOwnershipRowToEntity(rset));
         }

      } catch (SQLException e1) {
         throw new DatabaseException("db connection problem" + e1);
      } finally {
         ConnectorDB.close(conn, st);
      }

      printListOfOwnerships(list);
      return list;
   }

   // ============================================================================================
   /*
    * (non-Javadoc)
    * 
    * @see cz.pv168Web.dao.impl.OwnershipDao#getOwnershipById(java.lang.Long)
    */
   public Ownership getOwnershipById(Long id) throws DatabaseException {
      StringBuilder sql = new StringBuilder();
      Ownership ownership = null;
      sql.append(" SELECT ownerShipID,personID,landId,start_date,end_date");
      sql.append(" FROM Ownership ");
      sql.append(" WHERE ownerShipID = ? ");

      Connection conn = null;
      PreparedStatement st = null;
      try {
         conn = dataSource.getConnection();
         st = conn.prepareStatement(sql.toString(),
               Statement.RETURN_GENERATED_KEYS);
         st.setLong(1, id);

         ResultSet rset = st.executeQuery();
         while (rset.next()) {
            ownership = setOwnershipRowToEntity(rset);
         }

      } catch (SQLException e1) {
         throw new DatabaseException("db connection problem" + e1);
      } finally {
         ConnectorDB.close(conn, st);
      }

      if (ownership == null) {
         throw new DatabaseException("Ownership is not existing. ID : " + id);
      } else {
         LOGGER.debug("Ownership with id :" + id + "  -   "
               + ownership.toString());
      }
      return ownership;
   }

   /**
    * 
    */
   public void createTableOwnership() throws DatabaseException {
      StringBuilder sql = new StringBuilder();

      sql.append("  create table proj.Ownership(");
      sql.append("  ownerShipID INTEGER NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY,  ");
      sql.append("  personID INTEGER NOT NULL,");
      sql.append("  landId INTEGER NOT NULL,");
      sql.append("  start_date DATE,");
      sql.append("  end_date DATE,");
      sql.append("  FOREIGN KEY (personID) REFERENCES proj.Person(personId), ");
      sql.append("  FOREIGN KEY (landId) REFERENCES proj.Land(landId)");

      sql.append(" )");
      connectAndExecute(sql.toString());
      LOGGER.debug("Table Ownership created");
   }

   /**
    * 
    */
   public void dropTableOwnership() throws DatabaseException {
      StringBuilder sql = new StringBuilder();
      sql.append("drop table proj.Ownership");
      connectAndExecute(sql.toString());
      LOGGER.debug("Table Ownership dropped");

   }

   // ============================================================================================
   // ==============================================================================================-
   // ADDITIONAL FUNCTIONS FOR DATA HANDLING
   // ==============================================================================================
   /**
    * 
    * @param person
    * @return
    * @throws EntityException
    */
   private Boolean validateOwnership(Ownership ownership)
         throws EntityException {

      if (ownership.getPersonID() == null) {
         throw new EntityException(
               "Cannot create ownership with person ID = null");
      }

      if (ownership.getLandId() == null) {
         throw new EntityException(
               "Cannot create ownership with land ID = null");
      }
      if (ownership.getStartDate() == null) {
         throw new EntityException(
               "Cannot create ownership with StartDate = null");
      }
      if (ownership.getStartDate() != null && ownership.getEndDate() != null
            && ownership.getStartDate().compareTo(ownership.getEndDate()) < 0) {
         throw new EntityException(
               "Cannot create ownership with StartDate>ENDDATE");
      }
      return true;
   }

   // ==============================================================================================
   /**
    * 
    * @param rset
    * @return
    * @throws SQLException
    */
   private Ownership setOwnershipRowToEntity(ResultSet rset)
         throws SQLException {

      Ownership ownership = new Ownership();

      ownership.setOwnerShipID(rset.getLong("ownerShipID"));
      ownership.setPersonID(rset.getLong("personID"));
      ownership.setLandId(rset.getLong("landId"));
      ownership.setStartDate(rset.getDate("start_date"));
      ownership.setEndDate(rset.getDate("end_date"));
      return ownership;
   }

   // ==============================================================================================
   /**
    * (personID,landId,start_date,end_date)
    * 
    * @param st
    * @param person
    * @param update
    * @throws SQLException
    */
   private void setStatementFromOwnership(PreparedStatement st,
         Ownership ownership, boolean update) throws SQLException {
      st.setLong(1, ownership.getPersonID());
      st.setLong(2, ownership.getLandId());
      st.setDate(3, new java.sql.Date(ownership.getStartDate().getTime()));
      st.setDate(4, new java.sql.Date(ownership.getStartDate().getTime()));
      if (update) {
         st.setLong(5, ownership.getOwnerShipID());
      }
   }

   // ==============================================================================================
   /**
    * 
    * @param list
    */
   private void printListOfOwnerships(List<Ownership> list) {
      LOGGER.debug("____________________________________________________");
      LOGGER.debug("TABULKA Ownership : ");
      for (Ownership o : list) {
         LOGGER.debug(o.toString());
      }
      LOGGER.debug("____________________________________________________");
   }

}