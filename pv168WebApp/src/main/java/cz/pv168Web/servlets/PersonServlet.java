package cz.pv168Web.servlets;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cz.pv168Web.manager.PersonManager;
import cz.pv168Web.model.Person;
import cz.pv168Web.utils.DatabaseException;

/**
 * Servlet implementation class PersonServlet
 */
@WebServlet("/Person/*")
public class PersonServlet extends HttpServlet {
   private static final long serialVersionUID = 1L;

   private static Logger     LOGGER           = LoggerFactory
                                                    .getLogger(PersonServlet.class);

   public PersonServlet() {
      super();
      // TODO Auto-generated constructor stub
   }

   protected void doPost(HttpServletRequest request,
         HttpServletResponse response) throws ServletException, IOException {

      String action = request.getPathInfo() == null ? "NotSet" : request
            .getPathInfo();
      LOGGER.info(" Servlet accessed : PersonServlet  POST " + action);

      switch (action) {
      case "/Add":
         createPerson(request);
         break;
      case "/Remove" : 
         removePerson(request);
      default:
         break;
      }

      List<Person> personList = getPersonList();
      request.setAttribute("personList", personList);

      request.getRequestDispatcher("/person.jsp").forward(request, response);
   }



   protected void doGet(HttpServletRequest request, HttpServletResponse response)
         throws ServletException, IOException {

      String action = request.getPathInfo() == null ? "NotSet" : request
            .getPathInfo();

      LOGGER.info(" Servlet accessed : PersonServlet  GET " + action);

      List<Person> personList = getPersonList();
      request.setAttribute("personList", personList);

      request.getRequestDispatcher("/person.jsp").forward(request, response);
   }

   // --------------------------------------------------------------------------------
   private List<Person> getPersonList() {
      PersonManager personManager = (PersonManager) this.getServletContext()
            .getAttribute("PersonManager");

      try {
         return personManager.getPersonList();
      } catch (DatabaseException e) {
         e.printStackTrace();
      }
      return null;
   }

   private void createPerson(HttpServletRequest request) {
      PersonManager personManager = (PersonManager) this.getServletContext()
            .getAttribute("PersonManager");

      String name = request.getParameter("name");
      String surname = request.getParameter("surname");
      String state = request.getParameter("state");
      String birthNumber = request.getParameter("birthNumber");
      //Date birthDate = new Date(request.getParameter("birthDate"));
      
      
      Person person = new Person();
      
      person.setName(name);
      person.setSurname(surname);
      person.setBirthDate(new Date());
      person.setBirthNumber(birthNumber);
      person.setState(state);

      try {
         personManager.createPerson(person);
      } catch (DatabaseException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }

   }
   
   private void removePerson(HttpServletRequest request) {
      
      
      PersonManager personManager = (PersonManager) this.getServletContext()
            .getAttribute("PersonManager");
      
      Long removeId = Long.decode(request.getParameter("removeId"));
      
      Person p = new Person();
      p.setPersonId(removeId);
      try {
         personManager.removePerson(p);
      } catch (DatabaseException e) {
         e.printStackTrace();
      }
      
   }

}
