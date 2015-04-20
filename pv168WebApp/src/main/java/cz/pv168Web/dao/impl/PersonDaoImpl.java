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

import cz.pv168Web.dao.PersonDao;
import cz.pv168Web.model.Person;
import cz.pv168Web.utils.ConnectorDB;
import cz.pv168Web.utils.DatabaseException;
import cz.pv168Web.utils.EntityException;

public class PersonDaoImpl extends GenericDaoImpl implements PersonDao {

   private static Logger LOGGER = LoggerFactory.getLogger(PersonDaoImpl.class);

   public PersonDaoImpl(DataSource dataSource) {
      super(dataSource);
   }

   // ============================================================================================
/**
 * 
 * 
 */
   public void createPerson(Person person) throws DatabaseException {

      StringBuilder sql = new StringBuilder();
      sql.append("INSERT INTO Proj.PERSON ");
      sql.append("(name,surname,birth_date,birth_number,country)");
      sql.append(" VALUES (?,?,?,?,?) ");

      Connection conn = null;
      PreparedStatement st = null;
      try {
         validatePerson(person);
         conn = dataSource.getConnection();
         conn.setAutoCommit(false);

         st = conn.prepareStatement(sql.toString(),
               Statement.RETURN_GENERATED_KEYS);
         setStatementFromPerson(st, person, false);

         if (st.executeUpdate() != 1) {
            throw new DatabaseException(
                  "Internal Error: more or none rows were inserted "
                        + person.toString());
         }
         ResultSet rset = st.getGeneratedKeys();
         rset.next();
         person.setPersonId(rset.getLong(1));

         conn.commit();

      } catch (SQLException | EntityException e) {
         throw new DatabaseException("db connection problem" + e);
      } finally {
         ConnectorDB.close(conn, st);
      }

      LOGGER.debug("Person created : " + person.toString());
   }

   // ============================================================================================
   /**
    * 
    */
   public Person getPersonById(Long id) throws DatabaseException {
      StringBuilder sql = new StringBuilder();
      Person person = null;
      sql.append(" SELECT personId,name,surname,birth_date,birth_number,country");
      sql.append(" FROM Person ");
      sql.append(" WHERE personId = ? ");

      Connection conn = null;
      PreparedStatement st = null;
      try {
         conn = dataSource.getConnection();
         st = conn.prepareStatement(sql.toString(),
               Statement.RETURN_GENERATED_KEYS);
         st.setLong(1, id);

         ResultSet rset = st.executeQuery();
         while (rset.next()) {
            person = setPersonRowToEntity(rset);
         }

      } catch (SQLException e1) {
         throw new DatabaseException("db connection problem" + e1);
      } finally {
         ConnectorDB.close(conn, st);
      }

      if (person == null) {
         throw new DatabaseException("Person is not existing. ID : " + id);
      } else {
         LOGGER.debug("Person with id :" + id + "  -   " + person.toString());
      }
      return person;
   }

   // ==============================================================================================
   /**
    * 
    */
   public List<Person> getPersonByName(String name) throws DatabaseException {

      StringBuilder sql = new StringBuilder();
      List<Person> list = new ArrayList<Person>();
      sql.append(" SELECT personId,name,surname,birth_date,birth_number,country");
      sql.append(" FROM Person ");
      sql.append(" WHERE name = ? ");

      Connection conn = null;
      PreparedStatement st = null;

      try {
         conn = dataSource.getConnection();
         st = conn.prepareStatement(sql.toString(),
               Statement.RETURN_GENERATED_KEYS);
         st.setString(1, name);

         ResultSet rset = st.executeQuery();
         while (rset.next()) {
            list.add(setPersonRowToEntity(rset));
         }

      } catch (SQLException e1) {
         throw new DatabaseException("db connection problem" + e1);
      } finally {
         ConnectorDB.close(conn, st);
      }

      printListOfPersons(list);
      return list;
   }

   // ==============================================================================================
   /**
    * 
    */
   public List<Person> getPersonList() throws DatabaseException {
      StringBuilder sql = new StringBuilder();
      List<Person> list = new ArrayList<Person>();
      sql.append(" SELECT personId,name,surname,birth_date,birth_number,country");
      sql.append(" FROM Person ");

      Connection conn = null;
      PreparedStatement st = null;
      try {
         conn = dataSource.getConnection();
         st = conn.prepareStatement(sql.toString(),
               Statement.RETURN_GENERATED_KEYS);
         ResultSet rset = st.executeQuery();
         while (rset.next()) {
            list.add(setPersonRowToEntity(rset));
         }

      } catch (SQLException e1) {
         throw new DatabaseException("db connection problem" + e1);
      } finally {
         ConnectorDB.close(conn, st);
      }

      printListOfPersons(list);
      return list;
   }

   // ==============================================================================================
   /**
    * 
    */
   public void removePerson(Person person) throws DatabaseException {
      StringBuilder sql = new StringBuilder();
      sql.append(" DELETE FROM PERSON");
      sql.append(" WHERE personId = ? ");

      Connection conn = null;
      PreparedStatement st = null;
      try {
         conn = dataSource.getConnection();
         conn.setAutoCommit(false);
         st = conn.prepareStatement(sql.toString(),
               Statement.RETURN_GENERATED_KEYS);
         st.setLong(1, person.getPersonId());
         st.executeUpdate();
         conn.commit();
      } catch (SQLException e1) {
         throw new DatabaseException("db connection problem" + e1);
      } finally {
         ConnectorDB.close(conn, st);
      }
      LOGGER.debug("Person with id :" + person.getPersonId() + "  removed");
   }

   // ==============================================================================================
   /**
    * 
    */
   public void updatePerson(Person person) throws DatabaseException {

      StringBuilder sql = new StringBuilder();
      sql.append(" UPDATE PERSON ");
      sql.append(" SET  ");
      sql.append(" name = ?,");
      sql.append(" surname = ?,");
      sql.append(" birth_date = ?,");
      sql.append(" birth_number = ?,");
      sql.append(" country = ? ");
      sql.append(" WHERE  personId = ? ");

      Connection conn = null;
      PreparedStatement st = null;
      try {
         
         validatePerson(person);
         
         conn = dataSource.getConnection();
         conn.setAutoCommit(false);
         st = conn.prepareStatement(sql.toString(),
               Statement.RETURN_GENERATED_KEYS);
         setStatementFromPerson(st, person, true);
         st.executeUpdate();
         conn.commit();
      } catch (SQLException | EntityException e1) {
         throw new DatabaseException("db connection problem" + e1);
      } finally {
         ConnectorDB.close(conn, st);
      }

      LOGGER.debug("Person UPDATED");
   }

   // ==============================================================================================-
   public void createTablePerson() throws DatabaseException {

      StringBuilder sql = new StringBuilder();
      sql.append("create table proj.Person (");
      sql.append("personId INTEGER NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY,  ");
      sql.append("name VARCHAR(50),");
      sql.append("surname VARCHAR(50),");
      sql.append("birth_date date ,");
      sql.append("birth_number VARCHAR(10),");
      sql.append("country VARCHAR(20)");
      sql.append(")");

      connectAndExecute(sql.toString());
      LOGGER.debug("Table person created");
   }

   // ==============================================================================================-
   public void droptablePerson() throws DatabaseException {

      StringBuilder sql = new StringBuilder();
      sql.append("drop table proj.person");

      connectAndExecute(sql.toString());
      LOGGER.debug("Table person dropped");
   }

   // ==============================================================================================-
   // ADDITIONAL FUNCTIONS FOR DATA HANDLING
   // ==============================================================================================
   /**
    * 
    * @param person
    * @return
    * @throws EntityException
    */
   private Boolean validatePerson(Person person) throws EntityException {

      StringBuilder errMsg = new StringBuilder();
      if (person.getName() == null || "".equals(person.getName())) {
         errMsg.append("Validation Error : Name of person is Empty  \n");
      }
      if (person.getSurname() == null || "".equals(person.getSurname())) {
         errMsg.append("Validation Error : Surname of person is Empty  \n");
      }
      if (person.getBirthDate() == null) {
         errMsg.append("Validation Error : BirthDate of person is Empty  \n");
      }
      if (person.getBirthNumber() == null || "".equals(person.getBirthNumber())) {
         errMsg.append("Validation Error : BirthNumber of person is Empty  \n");
      }
      if (person.getState() == null || "".equals(person.getState())) {
         errMsg.append("Validation Error : BirthNumber of person is Empty  ");
      }
      if (errMsg.length() > 0) {
         throw new EntityException(errMsg + person.toString());
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
   private Person setPersonRowToEntity(ResultSet rset) throws SQLException {

      Person person = new Person();
      person.setPersonId(rset.getLong("personId"));
      person.setName(rset.getString("name"));
      person.setSurname(rset.getString("surname"));
      person.setBirthDate(rset.getDate("birth_date"));
      person.setBirthNumber(rset.getString("birth_number"));
      person.setState(rset.getString("country"));
      return person;
   }

   // ==============================================================================================
   /**
    * 
    * @param st
    * @param person
    * @param update
    * @throws SQLException
    */
   private void setStatementFromPerson(PreparedStatement st, Person person,
         boolean update) throws SQLException {
      st.setString(1, person.getName());
      st.setString(2, person.getSurname());
      st.setDate(3, new java.sql.Date(person.getBirthDate().getTime()));
      st.setString(4, person.getBirthNumber());
      st.setString(5, person.getState());
      if (update) {
         st.setLong(6, person.getPersonId());
      }
   }

   // ==============================================================================================
   /**
    * 
    * @param list
    */
   private void printListOfPersons(List<Person> list) {
      LOGGER.debug("____________________________________________________");
      LOGGER.debug("TABULKA PERSON : ");
      for (Person p : list) {
         LOGGER.debug(p.toString());
      }
      LOGGER.debug("____________________________________________________");
   }

}