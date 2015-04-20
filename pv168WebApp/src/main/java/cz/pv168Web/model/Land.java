package cz.pv168Web.model;

import java.io.Serializable;

public class Land implements Serializable {

   private static final long serialVersionUID = 8396052538737948538L;
   
   private Long              landID;
   private Double            Size;
   private String            catastralArea;
   private Double            buildUpArea;
   private String            type;
   private String            notes;
   
   
   public Long getLandID() {
      return landID;
   }

   public void setLandID(Long landID) {
      this.landID = landID;
   }

   public Double getSize() {
      return Size;
   }

   public void setSize(Double size) {
      Size = size;
   }

   public String getCatastralArea() {
      return catastralArea;
   }

   public void setCatastralArea(String catastralArea) {
      this.catastralArea = catastralArea;
   }

   public Double getBuildUpArea() {
      return buildUpArea;
   }

   public void setBuildUpArea(Double buildUpArea) {
      this.buildUpArea = buildUpArea;
   }

   public String getType() {
      return type;
   }

   public void setType(String type) {
      this.type = type;
   }

   public String getNotes() {
      return notes;
   }

   public void setNotes(String notes) {
      this.notes = notes;
   }

   public static long getSerialversionuid() {
      return serialVersionUID;
   }

   @Override
   public int hashCode() {
      final int prime = 31;
      int result = 1;
      result = prime * result + ((Size == null) ? 0 : Size.hashCode());
      result = prime * result
            + ((buildUpArea == null) ? 0 : buildUpArea.hashCode());
      result = prime * result
            + ((catastralArea == null) ? 0 : catastralArea.hashCode());
      result = prime * result + ((landID == null) ? 0 : landID.hashCode());
      result = prime * result + ((notes == null) ? 0 : notes.hashCode());
      result = prime * result + ((type == null) ? 0 : type.hashCode());
      return result;
   }
   
   @Override
   public String toString() {
      return "Land [landID=" + landID + ", Size=" + Size + ", catastralArea="
            + catastralArea + ", buildUpArea=" + buildUpArea + ", type=" + type
            + ", notes=" + notes + "]";
   }

   @Override
   public boolean equals(Object obj) {
      if (this == obj)
         return true;
      if (obj == null)
         return false;
      if (getClass() != obj.getClass())
         return false;
      Land other = (Land) obj;
      if (Size == null) {
         if (other.Size != null)
            return false;
      } else if (!Size.equals(other.Size))
         return false;
      if (buildUpArea == null) {
         if (other.buildUpArea != null)
            return false;
      } else if (!buildUpArea.equals(other.buildUpArea))
         return false;
      if (catastralArea == null) {
         if (other.catastralArea != null)
            return false;
      } else if (!catastralArea.equals(other.catastralArea))
         return false;
      if (landID == null) {
         if (other.landID != null)
            return false;
      } else if (!landID.equals(other.landID))
         return false;
      if (notes == null) {
         if (other.notes != null)
            return false;
      } else if (!notes.equals(other.notes))
         return false;
      if (type == null) {
         if (other.type != null)
            return false;
      } else if (!type.equals(other.type))
         return false;
      return true;
   }

   
}
