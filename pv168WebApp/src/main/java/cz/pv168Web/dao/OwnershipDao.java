package cz.pv168Web.dao;

import java.util.List;

import cz.pv168Web.model.Land;
import cz.pv168Web.model.Ownership;
import cz.pv168Web.model.Person;
import cz.pv168Web.utils.DatabaseException;

public interface OwnershipDao {
   /**
    * 
    * @param ownership
    * @throws DatabaseException
    */
   public abstract void createOwnership(Ownership ownership) throws DatabaseException;
   /**
    * 
    * @param ownership
    * @throws DatabaseException
    */
   public abstract void updateOwnerShip(Ownership ownership)throws DatabaseException;
   /**
    * 
    * @param ownership
    * @throws DatabaseException
    */
   public abstract void removeOwnership(Ownership ownership)throws DatabaseException;
   /**
    * 
    * @return
    * @throws DatabaseException
    */
   public abstract List<Person> getListOfPersonsOfLand()throws DatabaseException;
   /**
    * 
    * @return
    * @throws DatabaseException
    */
   public abstract List<Land> getListOfLandsOfPerson()throws DatabaseException;
   /**
    * 
    * @return
    * @throws DatabaseException
    */
   public abstract List<Ownership> getOwnershipList()throws DatabaseException;
   /**
    * 
    * @param id
    * @return
    * @throws DatabaseException
    */
   public abstract Ownership getOwnershipById(Long id)throws DatabaseException;
   /**
    * 
    * @throws DatabaseException
    */
   public abstract void createTableOwnership() throws DatabaseException;
   /**
    * 
    * @throws DatabaseException
    */
   public abstract void dropTableOwnership() throws DatabaseException;
}