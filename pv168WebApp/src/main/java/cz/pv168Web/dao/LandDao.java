package cz.pv168Web.dao;

import java.util.List;

import cz.pv168Web.model.Land;
import cz.pv168Web.utils.DatabaseException;

public interface LandDao {
   /**
    * 
    * @param land
    * @throws DatabaseException
    */
   public abstract void createLand(Land land) throws DatabaseException;

   /**
    * 
    * @param land
    * @throws DatabaseException
    */
   public abstract void updateLand(Land land) throws DatabaseException;

   /**
    * 
    * @param land
    * @throws DatabaseException
    */
   public abstract void removeLand(Land land) throws DatabaseException;

   /**
    * 
    * @param id
    * @return
    * @throws DatabaseException
    */
   public abstract Land getLandById(Long id) throws DatabaseException;

   /**
    * 
    * @return
    * @throws DatabaseException
    */
   public abstract List<Land> getLandList() throws DatabaseException;

   /**
    * 
    * @param catastralArea
    * @return
    * @throws DatabaseException
    */
   public abstract List<Land> getLandByArea(String catastralArea)
         throws DatabaseException;

   /**
    * 
    * @throws DatabaseException
    */
   public abstract void dropTableLand() throws DatabaseException;

   /**
    * 
    * @throws DatabaseException
    */
   public abstract void createTableLand() throws DatabaseException;

}