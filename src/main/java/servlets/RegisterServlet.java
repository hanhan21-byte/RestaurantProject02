package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import utils.Constans;
import utils.LogFactory;

import java.io.IOException;
import java.sql.SQLException;

import dao.UserDao;
import entities.User;


/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet(urlPatterns = "/register")
public class RegisterServlet extends HttpServlet {


    private static final long serialVersionUID = 1L;
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Trả về trang register.jsp
        request.getRequestDispatcher("views/register-user.jsp").forward(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, 
	HttpServletResponse response) throws ServletException, IOException {
        // Get data from the request using request.getParameter()
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");
        
        // Set data for the user
        User user = new User(firstName, lastName, email, userName, password);
        try {
            UserDao userDao = new UserDao();
            // Call registerUser() method to insert user into DB
            if (userDao.registerUser(user)) {
                // 	Send a attribute name as "userRegister" to register-user-process.jsp page
                request.setAttribute("userRegister", user);
                // Forward to register-user-process.jsp page
                request.getRequestDispatcher("/views/login.jsp").
							forward(request, response);
            } else {
                // send a attribute name as "message" to register-user.jsp page
                request.setAttribute("message", Constans.REGISTER_FAIL_MESSAGE);
                // forward to register-user.jsp page
                request.getRequestDispatcher("/views/register-user.jsp").
							forward(request, response);
            }
        } catch (ClassNotFoundException | SQLException e) {
            // log error if exception occurs
            LogFactory.getLogger().severe("An exception occurs while register user");
        }
    }
}

