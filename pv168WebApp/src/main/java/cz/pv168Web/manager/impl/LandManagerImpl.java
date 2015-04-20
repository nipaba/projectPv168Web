package cz.pv168Web.manager.impl;

import java.util.List;

import javax.sql.DataSource;

import cz.pv168Web.dao.LandDao;
import cz.pv168Web.dao.impl.LandDaoImpl;
import cz.pv168Web.manager.LandManager;
import cz.pv168Web.model.Land;
import cz.pv168Web.utils.ConnectorDB;
import cz.pv168Web.utils.DatabaseException;

public class LandManagerImpl implements LandManager {

   private LandDao landDao;
   
   public LandManagerImpl(DataSource ds){
      landDao = new LandDaoImpl(ds);
   }
   
   public void createLand(Land land) throws DatabaseException {
      landDao.createLand(land);
      
   }

   public void updateLand(Land land) throws DatabaseException {
      landDao.updateLand(land);
   }

   public void removeLand(Land land) throws DatabaseException {
      landDao.removeLand(land);
   }

   public Land getLandById(Long id) throws DatabaseException {
      return landDao.getLandById(id);
       
   }

   public List<Land> getLandList() throws DatabaseException {
      return landDao.getLandList();
   }

   public List<Land> getLandByArea(String catastralArea) throws DatabaseException {
      return landDao.getLandByArea(catastralArea);
   }
   

   public void dropTableLand() throws DatabaseException {
      landDao.dropTableLand();
   }
 
   public void createTableLand() throws DatabaseException {
      landDao.createTableLand();
   }

   

}
