package servlets;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;

import dao.MenuDao;
import entities.MenuItem;

@WebServlet("/menu")
public class MenuServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private MenuDao menuDAO = new MenuDao();  // Khởi tạo MenuDao trực tiếp

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        try {
            if ("add".equals(action)) {
                addMenuItem(request, response);
            } else if ("update".equals(action)) {
                updateMenuItem(request, response);
            } else if ("delete".equals(action)) {
                deleteMenuItem(request, response);
            }
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    private void addMenuItem(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String tenMon = request.getParameter("ten_mon");
        double gia = Double.parseDouble(request.getParameter("gia"));
        String moTa = request.getParameter("mo_ta");
        String nguyenLieu = request.getParameter("nguyen_lieu");
        String hinhAnh = request.getParameter("hinh_anh");

        MenuItem menuItem = new MenuItem(0, tenMon, gia, moTa, nguyenLieu, hinhAnh);
        boolean success = menuDAO.addMenuItem(menuItem);
        
        if (success) {
            response.sendRedirect("/views/product.jsp?status=success");
        } else {
            response.sendRedirect("/views/product.jsp?status=failure");
        }
    }

    private void updateMenuItem(HttpServletRequest request, HttpServletResponse response) throws Exception {
        int id = Integer.parseInt(request.getParameter("id"));
        String tenMon = request.getParameter("ten_mon");
        double gia = Double.parseDouble(request.getParameter("gia"));
        String moTa = request.getParameter("mo_ta");
        String nguyenLieu = request.getParameter("nguyen_lieu");
        String hinhAnh = request.getParameter("hinh_anh");

        MenuItem menuItem = new MenuItem(id, tenMon, gia, moTa, nguyenLieu, hinhAnh);
        boolean success = menuDAO.updateMenuItem(menuItem);
        
        if (success) {
            response.sendRedirect("/views/product.jsp?status=success");
        } else {
            response.sendRedirect("/views/product.jsp?status=failure");
        }
    }

    private void deleteMenuItem(HttpServletRequest request, HttpServletResponse response) throws Exception {
        int id = Integer.parseInt(request.getParameter("id"));
        boolean success = menuDAO.deleteMenuItem(id);
        
        if (success) {
            response.sendRedirect("/views/product.jsp?status=success");
        } else {
            response.sendRedirect("/views/product.jsp?status=failure");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        try {
            if ("search".equals(action)) {
                searchMenuItems(request, response);
            } else {
                listAllMenuItems(request, response);
            }
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    private void searchMenuItems(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String tenMon = request.getParameter("ten_mon");
        List<MenuItem> menuItems = menuDAO.searchMenuItems(tenMon);
        request.setAttribute("menuItems", menuItems);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/views/product.jsp");
        dispatcher.forward(request, response);
    }

    private void listAllMenuItems(HttpServletRequest request, HttpServletResponse response) throws Exception {
        List<MenuItem> menuItems = menuDAO.getAllMenuItems();
        request.setAttribute("menuItems", menuItems);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/views/product.jsp");
        dispatcher.forward(request, response);
    }
}
