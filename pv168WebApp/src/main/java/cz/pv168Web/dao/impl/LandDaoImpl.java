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

import cz.pv168Web.dao.LandDao;
import cz.pv168Web.model.Land;
import cz.pv168Web.utils.ConnectorDB;
import cz.pv168Web.utils.DatabaseException;
import cz.pv168Web.utils.EntityException;

public class LandDaoImpl extends GenericDaoImpl implements LandDao {

   private static Logger LOGGER = LoggerFactory.getLogger(LandDaoImpl.class);

   /**
    * 
    * @param dataSource
    */
   public LandDaoImpl(DataSource dataSource) {
      super(dataSource);
   }

   /**
   * 
   */
   public void createLand(Land land) throws DatabaseException {
      StringBuilder sql = new StringBuilder();
      sql.append("INSERT INTO Proj.LAND ");
      sql.append("(size,buildUpArea,catastralArea,land_type,notes)");
      sql.append(" VALUES (?,?,?,?,?) ");

      Connection conn = null;
      PreparedStatement st = null;
      try {
         validateLand(land);
         conn = dataSource.getConnection();
         conn.setAutoCommit(false);

         st = conn.prepareStatement(sql.toString(),
               Statement.RETURN_GENERATED_KEYS);
         setStatementFromLand(st, land, false);

         if (st.executeUpdate() != 1) {
            throw new DatabaseException(
                  "Internal Error: more or none rows were inserted "
                        + land.toString());
         }
         ResultSet rset = st.getGeneratedKeys();
         rset.next();
         land.setLandID(rset.getLong(1));

         conn.commit();

      } catch (SQLException | EntityException e) {
         throw new DatabaseException("db connection problem" + e);
      } finally {
         ConnectorDB.close(conn, st);
      }

      LOGGER.debug(" created : " + land.toString());
   }

   /**
    * 
    */
   public void updateLand(Land land) throws DatabaseException {
      StringBuilder sql = new StringBuilder();
      sql.append(" UPDATE LAND ");
      sql.append(" SET  ");
      sql.append(" size = ?,");
      sql.append(" buildUpArea = ?,");
      sql.append(" catastralArea = ?,");
      sql.append(" land_type = ?,");
      sql.append(" notes = ? ");
      sql.append(" WHERE  landID = ? ");

      Connection conn = null;
      PreparedStatement st = null;
      try {

         validateLand(land);

         conn = dataSource.getConnection();
         conn.setAutoCommit(false);
         st = conn.prepareStatement(sql.toString(),
               Statement.RETURN_GENERATED_KEYS);
         setStatementFromLand(st, land, true);
         st.executeUpdate();
         conn.commit();
      } catch (SQLException | EntityException e1) {
         throw new DatabaseException("db connection problem" + e1);
      } finally {
         ConnectorDB.close(conn, st);
      }

      LOGGER.debug("Land UPDATED");
   }

   /**
	 * 
	 */
   public void removeLand(Land land) throws DatabaseException {
      StringBuilder sql = new StringBuilder();
      sql.append(" DELETE FROM LAND");
      sql.append(" WHERE landID = ? ");

      Connection conn = null;
      PreparedStatement st = null;
      try {
         conn = dataSource.getConnection();
         conn.setAutoCommit(false);
         st = conn.prepareStatement(sql.toString(),
               Statement.RETURN_GENERATED_KEYS);
         st.setLong(1, land.getLandID());
         st.executeUpdate();
         conn.commit();
      } catch (SQLException e1) {
         throw new DatabaseException("db connection problem" + e1);
      } finally {
         ConnectorDB.close(conn, st);
      }
      LOGGER.debug("Land with id :" + land.getLandID() + "  removed");
   }

   /**
	 * 
	 */
   public Land getLandById(Long id) throws DatabaseException {
      StringBuilder sql = new StringBuilder();
      Land land = null;
      sql.append(" SELECT landID,size,buildUpArea,catastralArea,land_type,notes");
      sql.append(" FROM Land ");
      sql.append(" WHERE landID = ? ");

      Connection conn = null;
      PreparedStatement st = null;
      try {
         conn = dataSource.getConnection();
         st = conn.prepareStatement(sql.toString(),
               Statement.RETURN_GENERATED_KEYS);
         st.setLong(1, id);

         ResultSet rset = st.executeQuery();
         while (rset.next()) {
            land = setLandRowToEntity(rset);
         }

      } catch (SQLException e1) {
         throw new DatabaseException("getLandById : db connection problem" + e1);
      } finally {
         ConnectorDB.close(conn, st);
      }

      if (land == null) {
         throw new DatabaseException("Land is not existing. ID : " + id);
      } else {
         LOGGER.debug("Land with id :" + id + "  -   " + land.toString());
      }
      return land;
   }

   /**
	 * 
	 */
   public List<Land> getLandList() throws DatabaseException {
      StringBuilder sql = new StringBuilder();
      List<Land> list = new ArrayList<Land>();
      sql.append(" SELECT landID,size,buildUpArea,catastralArea,land_type,notes");
      sql.append(" FROM Land ");

      Connection conn = null;
      PreparedStatement st = null;
      try {
         conn = dataSource.getConnection();
         st = conn.prepareStatement(sql.toString(),
               Statement.RETURN_GENERATED_KEYS);
         ResultSet rset = st.executeQuery();
         while (rset.next()) {
            list.add(setLandRowToEntity(rset));
         }

      } catch (SQLException e1) {
         throw new DatabaseException("db connection problem" + e1);
      } finally {
         ConnectorDB.close(conn, st);
      }

      printListOfLands(list);
      return list;
   }

   /**
    * 
    */
   public List<Land> getLandByArea(String catastralArea)
         throws DatabaseException {
      StringBuilder sql = new StringBuilder();
      List<Land> list = new ArrayList<Land>();
      sql.append(" SELECT size,buildUpArea,catastralArea,land_type,notes");
      sql.append(" FROM Land ");
      sql.append(" WHERE catastralArea = ? ");

      Connection conn = null;
      PreparedStatement st = null;

      try {
         conn = dataSource.getConnection();
         st = conn.prepareStatement(sql.toString(),
               Statement.RETURN_GENERATED_KEYS);
         st.setString(1, catastralArea);

         ResultSet rset = st.executeQuery();
         while (rset.next()) {
            list.add(setLandRowToEntity(rset));
         }

      } catch (SQLException e1) {
         throw new DatabaseException("db connection problem" + e1);
      } finally {
         ConnectorDB.close(conn, st);
      }

      printListOfLands(list);
      return list;
   }

   /**
	 * 
	 */
   public void createTableLand() throws DatabaseException {

      StringBuilder sql = new StringBuilder();
      sql.append("create table proj.Land (");
      sql.append("landID INTEGER NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY,  ");
      sql.append("size DOUBLE,");
      sql.append("buildUpArea DOUBLE,");
      sql.append("catastralArea VARCHAR(20),");
      sql.append("land_type VARCHAR(20),");
      sql.append("notes VARCHAR(400)");
      sql.append(")");

      connectAndExecute(sql.toString());
      LOGGER.debug("Table land created");
   }

   // ==============================================================================================-
   /**
	 * 
	 */
   public void dropTableLand() throws DatabaseException {

      StringBuilder sql = new StringBuilder();
      sql.append("drop table proj.land");

      connectAndExecute(sql.toString());
      LOGGER.debug("Table land dropped");
   }

   // ==============================================================================================-
   // ADDITIONAL FUNCTIONS FOR DATA HANDLING
   // ==============================================================================================
   /**
    * 
    * @param Land
    * @return
    * @throws EntityException
    */
   private Boolean validateLand(Land land) throws EntityException {

      StringBuilder errMsg = new StringBuilder();
      if (land.getSize() == null || land.getSize() < 0) {
         errMsg.append("Validation Error : Size of land is Empty  \n");
      }
      if (land.getBuildUpArea() == null || land.getBuildUpArea() < 0) {
         errMsg.append("Validation Error : BuildUpArea of land is Empty  \n");
      }
      if (land.getCatastralArea() == null || land.getCatastralArea().isEmpty()) {
         errMsg.append("Validation Error : CatastralArea of land is Empty  \n");
      }
      if (land.getType() == null || "".equals(land.getType())) {
         errMsg.append("Validation Error : Type of land is Empty  \n");
      }
      if (land.getNotes() == null || "".equals(land.getNotes())) {
         errMsg.append("Validation Error : Notes of land is Empty  ");
      }
      if (errMsg.length() > 0) {
         throw new EntityException(errMsg + land.toString());
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
   private Land setLandRowToEntity(ResultSet rset) throws SQLException {

      Land land = new Land();
      land.setLandID(rset.getLong("landID"));
      land.setSize(rset.getDouble("size"));
      land.setBuildUpArea(rset.getDouble("BuildUpArea"));
      land.setCatastralArea(rset.getString("catastralArea"));
      land.setType(rset.getString("land_type"));
      land.setNotes(rset.getString("notes"));
      return land;
   }

   // ==============================================================================================
   /**
    * 
    * @param st
    * @param land
    * @param update
    * @throws SQLException
    */
   private void setStatementFromLand(PreparedStatement st, Land land,
         boolean update) throws SQLException {
      st.setDouble(1, land.getSize());
      st.setDouble(2, land.getBuildUpArea());
      st.setString(3, land.getCatastralArea());
      st.setString(4, land.getType());
      st.setString(5, land.getNotes());
      if (update) {
         st.setLong(6, land.getLandID());
      }
   }

   // ==============================================================================================
   /**
    * 
    * @param list
    */
   private void printListOfLands(List<Land> list) {
      LOGGER.debug("____________________________________________________");
      LOGGER.debug("TABULKA Land : ");
      for (Land l : list) {
         LOGGER.debug(l.toString());
      }
      LOGGER.debug("____________________________________________________");
   }

}