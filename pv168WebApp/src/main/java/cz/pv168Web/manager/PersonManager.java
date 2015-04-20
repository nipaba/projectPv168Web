package cz.pv168Web.manager;

import java.util.List;

import cz.pv168Web.model.Person;
import cz.pv168Web.utils.DatabaseException;

public interface PersonManager {

   /**
    * 
    * @param person
    * @throws DatabaseException
    */
   public void createPerson(Person person) throws DatabaseException;
   /**
    * 
    * @param id
    * @return
    * @throws DatabaseException
    */
   public Person getPersonById(Long id) throws DatabaseException;
   /**
    * 
    * @param name
    * @return
    * @throws DatabaseException
    */
   public List<Person> getPersonByName(String name) throws DatabaseException;
   /**
    * 
    * @return
    * @throws DatabaseException
    */
   public List<Person> getPersonList() throws DatabaseException;
   /**
    * 
    * @param person
    * @throws DatabaseException
    */
   public void removePerson(Person person) throws DatabaseException;
   /**
    * 
    * @param person
    * @throws DatabaseException
    */
   public void updatePerson(Person person) throws DatabaseException;
   /**
    * 
    * @throws DatabaseException
    */
   public void droptablePerson() throws DatabaseException;
   /**
    * 
    * @throws DatabaseException
    */
   public void createTablePerson() throws DatabaseException;
}
