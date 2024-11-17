package servlets;

import dao.UserDao;
import entities.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.sql.SQLException;

import static org.mockito.Mockito.*;

public class LoginServletTest {

    private LoginServlet loginServlet;
    private HttpServletRequest request;
    private HttpServletResponse response;
    private HttpSession session;
    private UserDao userDao;

    @BeforeEach
    public void setUp() {
        loginServlet = new LoginServlet();
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        session = mock(HttpSession.class);
        userDao = mock(UserDao.class);

        // Mô phỏng việc gọi getSession() trả về một session mock
        when(request.getSession()).thenReturn(session);
    }

    @Test
    public void testDoPost_SuccessfulLogin() throws ServletException, IOException, SQLException, ClassNotFoundException {
        String userName = "testUser";
        String password = "testPassword";

        // Mô phỏng việc người dùng nhập username và password
        when(request.getParameter("username")).thenReturn(userName);
        when(request.getParameter("password")).thenReturn(password);

        // Mô phỏng UserDao trả về true khi login thành công
        when(userDao.loginUser(userName, password)).thenReturn(true);

        // Thực hiện phương thức doPost
        loginServlet.doPost(request, response);

        // Kiểm tra xem session có chứa thông tin người dùng đã đăng nhập không
        verify(session).setAttribute(eq("userLogin"), any(User.class));
        // Kiểm tra xem response có điều hướng tới trang "/home" không
        verify(response).sendRedirect(request.getContextPath() + "/home");
    }

    @Test
    public void testDoPost_FailedLogin() throws ServletException, IOException, SQLException, ClassNotFoundException {
        String userName = "testUser";
        String password = "wrongPassword";

        // Mô phỏng việc người dùng nhập thông tin sai
        when(request.getParameter("username")).thenReturn(userName);
        when(request.getParameter("password")).thenReturn(password);

        // Mô phỏng UserDao trả về false khi login thất bại
        when(userDao.loginUser(userName, password)).thenReturn(false);

        // Thực hiện phương thức doPost
        loginServlet.doPost(request, response);

        // Kiểm tra xem có set thuộc tính lỗi cho trang đăng nhập không
        verify(request).setAttribute(eq("loginFail"), eq("User name or password is incorrect"));
        // Kiểm tra xem có chuyển hướng tới trang login.jsp không
        verify(request).getRequestDispatcher("/views/login.jsp");
    }

    @Test
    public void testDoGet() throws ServletException, IOException {
        // Thực hiện phương thức doGet
        loginServlet.doGet(request, response);

        // Kiểm tra xem có chuyển hướng đến trang login.jsp không
        verify(request).getRequestDispatcher("views/login.jsp");
    }
}
