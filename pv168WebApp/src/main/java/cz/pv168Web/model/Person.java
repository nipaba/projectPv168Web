package cz.pv168Web.model;

import java.io.Serializable;
import java.util.Date;

public class Person implements Serializable {

   private static final long serialVersionUID = 6543378899419912923L;

   private Long              personId;
   private String            name;
   private String            surname;
   private Date              birthDate;
   private String            birthNumber;
   private String            state;

   public Long getPersonId() {
      return personId;
   }

   public void setPersonId(Long personId) {
      this.personId = personId;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public String getSurname() {
      return surname;
   }

   public void setSurname(String surname) {
      this.surname = surname;
   }

   public Date getBirthDate() {
      return birthDate;
   }

   public void setBirthDate(Date birthDate) {
      this.birthDate = birthDate;
   }

   public String getBirthNumber() {
      return birthNumber;
   }

   public void setBirthNumber(String birthNumber) {
      this.birthNumber = birthNumber;
   }

   public String getState() {
      return state;
   }

   public void setState(String state) {
      this.state = state;
   }

   public static long getSerialversionuid() {
      return serialVersionUID;
   }

   @Override
   public int hashCode() {
      final int prime = 31;
      int result = 1;
      result = prime * result
            + ((birthDate == null) ? 0 : birthDate.hashCode());
      result = prime * result
            + ((birthNumber == null) ? 0 : birthNumber.hashCode());
      result = prime * result + ((name == null) ? 0 : name.hashCode());
      result = prime * result + ((personId == null) ? 0 : personId.hashCode());
      result = prime * result + ((state == null) ? 0 : state.hashCode());
      result = prime * result + ((surname == null) ? 0 : surname.hashCode());
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
      Person other = (Person) obj;
      if (birthDate == null) {
         if (other.birthDate != null)
            return false;
      } 
      if (birthNumber == null) {
         if (other.birthNumber != null)
            return false;
      } else if (!birthNumber.equals(other.birthNumber))
         return false;
      if (name == null) {
         if (other.name != null)
            return false;
      } else if (!name.equals(other.name))
         return false;
      if (personId == null) {
         if (other.personId != null)
            return false;
      } else if (!personId.equals(other.personId))
         return false;
      if (state == null) {
         if (other.state != null)
            return false;
      } else if (!state.equals(other.state))
         return false;
      if (surname == null) {
         if (other.surname != null)
            return false;
      } else if (!surname.equals(other.surname))
         return false;
      return true;
   }

   @Override
   public String toString() {
      return "Person [personId=" + personId + ", name=" + name + ", surname="
            + surname + ", birthDate=" + birthDate + ", birthNumber="
            + birthNumber + ", state=" + state + "]";
   }

}
