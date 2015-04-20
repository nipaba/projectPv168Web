package cz.pv168Web.utils;

public class EntityException extends Exception {

   private static final long serialVersionUID = -8720173716208195168L;

   
   public EntityException(String msg) {
      super("EntityException : ERROR WITH VALIDATING ENTITY - " + msg);
  }

  public EntityException(Throwable cause) {
      super(cause);
  }

  public EntityException(String message, Throwable cause) {
      super(message, cause);
  }
}
