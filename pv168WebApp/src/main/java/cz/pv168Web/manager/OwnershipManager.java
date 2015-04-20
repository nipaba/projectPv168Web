package cz.pv168Web.manager;

import java.util.List;

import cz.pv168Web.model.Land;
import cz.pv168Web.model.Ownership;
import cz.pv168Web.model.Person;
import cz.pv168Web.utils.DatabaseException;

public interface OwnershipManager {
   /**
    * 
    * @param ownership
    * @throws DatabaseException
    */
   public void createOwnership(Ownership ownership) throws DatabaseException;
   /**
    * 
    * @param ownership
    * @throws DatabaseException
    */
   public void updateOwnerShip(Ownership ownership) throws DatabaseException;
   /**
    * 
    * @param ownership
    * @throws DatabaseException
    */
   public void removeOwnership(Ownership ownership) throws DatabaseException;
   /**
    * 
    * @return
    * @throws DatabaseException
    */
   public List<Person> getListOfPersonsOfLand() throws DatabaseException;
   /**
    * 
    * @return
    * @throws DatabaseException
    */
   public List<Land> getListOfLandsOfPerson()throws DatabaseException;
   /**
    * 
    * @return
    * @throws DatabaseException
    */
   public List<Ownership> getOwnershipList()throws DatabaseException;
   /**
    * 
    * @param id
    * @return
    * @throws DatabaseException
    */
   public Ownership getOwnershipById(Long id)throws DatabaseException;
   /**
    * 
    * @throws DatabaseException
    */
   public void createTableOwnership() throws DatabaseException;
   /**
    * 
    * @throws DatabaseException
    */
   public void dropTableOwnership() throws DatabaseException;
}
