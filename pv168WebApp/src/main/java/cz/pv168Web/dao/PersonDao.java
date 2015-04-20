package cz.pv168Web.dao;

import java.util.List;

import cz.pv168Web.model.Person;
import cz.pv168Web.utils.DatabaseException;

public interface PersonDao {

   /**
    * 
    * @param person
    * @throws DatabaseException
    */
   public abstract void createPerson(Person person) throws DatabaseException;

   /**
    * 
    * @param id
    * @return
    * @throws DatabaseException
    */
   public abstract Person getPersonById(Long id) throws DatabaseException;

   /**
    * 
    * @param name
    * @return
    * @throws DatabaseException
    */
   public abstract List<Person> getPersonByName(String name) throws DatabaseException;

   /**
    * 
    * @return
    * @throws DatabaseException
    */
   public abstract List<Person> getPersonList() throws DatabaseException;
   
   /**
    * 
    * @param person
    * @throws DatabaseException
    */
   public abstract void removePerson(Person person)throws DatabaseException;
   /**
    * 
    * @param person
    * @throws DatabaseException
    */
   public abstract void updatePerson(Person person) throws DatabaseException;
   /**
    * 
    * @throws DatabaseException
    */
   public abstract void droptablePerson() throws DatabaseException;
   /**
    * 
    * @throws DatabaseException
    */
   public abstract void createTablePerson() throws DatabaseException;
}