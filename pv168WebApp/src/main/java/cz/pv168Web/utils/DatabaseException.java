package cz.pv168Web.utils;

public class DatabaseException extends Exception {

   private static final long serialVersionUID = -8720173716208195168L;

   
   public DatabaseException(String msg) {
      super(msg);
  }

  public DatabaseException(Throwable cause) {
      super(cause);
  }

  public DatabaseException(String message, Throwable cause) {
      super(message, cause);
  }
}
