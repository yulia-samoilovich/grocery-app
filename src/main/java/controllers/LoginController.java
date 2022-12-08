package controllers;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import models.entities.Customer;
import models.managers.CustomerManager;

import java.io.IOException;

@WebServlet(name = "LoginController", value = "/login")
public class LoginController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setAttribute("message", "message from servlet");
        request.setAttribute("username", "Customer");
        request.getRequestDispatcher("WEB-INF/login.jsp").forward(request, response);
    }
        @Override
        protected void doPost(HttpServletRequest request, HttpServletResponse response) {
            HttpSession session = request.getSession();
            try{
                String email = request.getParameter("email");
                String password = request.getParameter("password");
                Customer user = CustomerManager.getUserLogin(email, password);
                if(user!=null){
                    session.setAttribute("userLogin", user);
                    if(session.getAttribute("cartList")!=null){
                        session.setAttribute("userLogin", user);
                        request.getRequestDispatcher("WEB-INF/shoppingCart.jsp").forward(request, response);
                    }else {
                        request.getRequestDispatcher("WEB-INF/home.jsp").forward(request, response);
                    }
                } else {
                    request.getRequestDispatcher("WEB-INF/login.jsp").forward(request, response);
                }
            } catch(Exception ex){
                ex.printStackTrace();
            }
        }
}
