package servlets;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import dao.MenuDao;
import entities.MenuItem;

import java.util.Arrays;
import java.util.List;

public class MenuServletTest {
    private MenuServlet menuServlet;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private MenuDao menuDAO;

    @Mock
    private RequestDispatcher dispatcher;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        menuServlet = new MenuServlet();
        menuServlet.menuDAO = menuDAO; // Inject mock DAO
    }

    @Test
    void testAddMenuItemSuccess() throws Exception {
        // Giả lập request
        when(request.getParameter("action")).thenReturn("add");
        when(request.getParameter("ten_mon")).thenReturn("Pizza");
        when(request.getParameter("gia")).thenReturn("150");
        when(request.getParameter("mo_ta")).thenReturn("Delicious pizza");
        when(request.getParameter("nguyen_lieu")).thenReturn("Cheese, Tomato");
        when(request.getParameter("hinh_anh")).thenReturn("pizza.jpg");
        when(request.getParameter("category")).thenReturn("Fast Food");
        when(menuDAO.addMenuItem(any(MenuItem.class))).thenReturn(true);

        // Giả lập dispatcher
        when(request.getRequestDispatcher("/views/product.jsp")).thenReturn(dispatcher);

        // Thực hiện POST
        menuServlet.doPost(request, response);

        // Xác minh forward đến đúng trang
        verify(dispatcher).forward(request, response);
    }

    @Test
    void testAddMenuItemInvalidData() throws Exception {
        when(request.getParameter("action")).thenReturn("add");
        when(request.getParameter("ten_mon")).thenReturn(null); // Thiếu tên món ăn
        when(request.getRequestDispatcher("/views/product.jsp")).thenReturn(dispatcher);

        menuServlet.doPost(request, response);

        ArgumentCaptor<String> statusCaptor = ArgumentCaptor.forClass(String.class);
        verify(request).setAttribute(eq("status"), statusCaptor.capture());
        assertEquals("invalid_data", statusCaptor.getValue());
        verify(dispatcher).forward(request, response);
    }

    @Test
    void testListAllMenuItems() throws Exception {
        when(request.getParameter("action")).thenReturn(null);
        when(request.getRequestDispatcher("/views/product.jsp")).thenReturn(dispatcher);

        List<MenuItem> mockMenuItems = Arrays.asList(
            new MenuItem(1, "Pizza", 150, "Delicious pizza", "Cheese, Tomato", "pizza.jpg", "Fast Food"),
            new MenuItem(2, "Burger", 100, "Juicy burger", "Beef, Lettuce", "burger.jpg", "Fast Food")
        );
        when(menuDAO.getAllMenuItems()).thenReturn(mockMenuItems);

        menuServlet.doGet(request, response);

        verify(request).setAttribute(eq("menuItems"), eq(mockMenuItems));
        verify(dispatcher).forward(request, response);
    }

    @Test
    void testDeleteMenuItem() throws Exception {
        when(request.getParameter("action")).thenReturn("delete");
        when(request.getParameter("id")).thenReturn("1");
        when(menuDAO.deleteMenuItem(1)).thenReturn(true);
        when(request.getRequestDispatcher("/views/product.jsp")).thenReturn(dispatcher);

        menuServlet.doPost(request, response);

        verify(menuDAO).deleteMenuItem(1);
        verify(dispatcher).forward(request, response);
    }
}
