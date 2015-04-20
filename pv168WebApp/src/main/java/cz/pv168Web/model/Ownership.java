package cz.pv168Web.model;

import java.io.Serializable;
import java.util.Date;

public class Ownership implements Serializable{

   private static final long serialVersionUID = -1909341954347520115L;
   
   private Long ownerShipID;
   private Long personID;
   private Long landId;
   private Date startDate;
   private Date endDate;
   
   
   public Long getOwnerShipID() {
      return ownerShipID;
   }
   public void setOwnerShipID(Long ownerShipID) {
      this.ownerShipID = ownerShipID;
   }
   public Long getPersonID() {
      return personID;
   }
   public void setPersonID(Long personID) {
      this.personID = personID;
   }
   public Long getLandId() {
      return landId;
   }
   public void setLandId(Long landId) {
      this.landId = landId;
   }
   public Date getStartDate() {
      return startDate;
   }
   public void setStartDate(Date startDate) {
      this.startDate = startDate;
   }
   public Date getEndDate() {
      return endDate;
   }
   public void setEndDate(Date endDate) {
      this.endDate = endDate;
   }
   public static long getSerialversionuid() {
      return serialVersionUID;
   }
   
   @Override
   public int hashCode() {
      final int prime = 31;
      int result = 1;
      result = prime * result + ((endDate == null) ? 0 : endDate.hashCode());
      result = prime * result + ((landId == null) ? 0 : landId.hashCode());
      result = prime * result
            + ((ownerShipID == null) ? 0 : ownerShipID.hashCode());
      result = prime * result + ((personID == null) ? 0 : personID.hashCode());
      result = prime * result
            + ((startDate == null) ? 0 : startDate.hashCode());
      return result;
   }
   @Override
   public boolean equals(Object obj) {
      if (this == obj)
         return true;
      if (obj == null)
         return false;
      if (getClass() != obj.getClass())
         return false;
      Ownership other = (Ownership) obj;
      if (landId == null) {
         if (other.landId != null)
            return false;
      } else if (!landId.equals(other.landId))
         return false;
      if (ownerShipID == null) {
         if (other.ownerShipID != null)
            return false;
      } else if (!ownerShipID.equals(other.ownerShipID))
         return false;
      if (personID == null) {
         if (other.personID != null)
            return false;
      } else if (!personID.equals(other.personID))
         return false;
      
      return true;
   }
   @Override
   public String toString() {
      return "Ownership [ownerShipID=" + ownerShipID + ", personID=" + personID
            + ", landId=" + landId + ", startDate=" + startDate + ", endDate="
            + endDate + "]";
   }
   
   
}
