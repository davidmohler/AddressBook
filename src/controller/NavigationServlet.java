package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.ContactInfo;
import model.Person;

/**
 * Servlet implementation class NavigationServlet
 */
@WebServlet("/navigationServlet")
public class NavigationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NavigationServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PersonHelper ph = new PersonHelper();
		
		request.setAttribute("allPeople", ph.showAllPeople());
		
		if(ph.showAllPeople().isEmpty()) {
				request.setAttribute("allPeople", " ");
		}
		
		getServletContext().getRequestDispatcher("/view-all-people.jsp").forward(request, response);		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String act = request.getParameter("doThisToPerson");
		String path = "/viewAllPeopleServlet";
		
		PersonHelper dao = new PersonHelper();
		ContactInfoHelper cih = new ContactInfoHelper();
		act = request.getParameter("doThisToPerson");
		
		if(act.contentEquals("delete")) {
			try{Integer tempId = Integer.parseInt(request.getParameter("personId"));
			Person personToRemove = dao.searchForPersonById(tempId);
			dao.removePerson(personToRemove);
			}catch(NumberFormatException e) {
				System.out.println("No person selected");
			}
	
		} else if (act.contentEquals("edit")) {
			try {
			Integer tempId = Integer.parseInt(request.getParameter("personId"));
			Person personToEdit = dao.searchForPersonById(tempId);
			ContactInfo ciToEdit = cih.searchForContactInfoByID(tempId);
			request.setAttribute("personToEdit", personToEdit);
			request.setAttribute("ciToEdit", ciToEdit);
			path = "/edit-person.jsp";
			}catch(NumberFormatException e){
				System.out.println("No person selected");
			}
		
		} else if (act.contentEquals("add")) {
			path = "/index.html";
		}
		
		getServletContext().getRequestDispatcher(path).forward(request, response);
		}
	}


