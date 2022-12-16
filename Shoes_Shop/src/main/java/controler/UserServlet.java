package controler;

import DAO.IUserDao;
import DAO.UserDao;
import Model.Product;
import Model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@WebServlet(name = "UserServlet", urlPatterns = "/users")
public class UserServlet extends HttpServlet {
    private String errors;
    private IUserDao userDao;

    @Override
    public void init() throws ServletException {
        userDao = new UserDao();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action==null){
            action="";
        }
        try {
            switch (action) {
                case "create":
                    showNewFormUser(req, resp);
                    break;
                case "edit":
                    showEditUserForm(req, resp);
                    break;
                case "delete":
                    deleteUser(req, resp);
                    break;
                default:
//                    resp.sendRedirect("view/listuser.jsp");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action==null){
            action="";
        }
        switch (action){
            case "create":
                insertUser(req,resp);
                break;
            case "edit":
                break;
            default:
                RequestDispatcher requestDispatcher = req.getRequestDispatcher("/product");
                requestDispatcher.forward(req,resp);
        }
    }
    private void showNewFormUser(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("view/createuser.jsp");
        dispatcher.forward(request, response);
    }
    private void showEditUserForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("user"));
        User user = userDao.selectUserByID(id);
        request.setAttribute("user", user);
        RequestDispatcher dispatcher = request.getRequestDispatcher("view/edituser.jsp");
        dispatcher.forward(request, response);
    }
//    private void editUser(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        RequestDispatcher dispatcher = request.getRequestDispatcher("view/editproduct.jsp");
//        dispatcher.forward(request, response);
//    }
    private void updateUser(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("userID"));
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");
        String fullName = request.getParameter("fullName");
        String email = request.getParameter("email");
        int age = Integer.parseInt(request.getParameter("age"));
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");
        userDao.updateUser(new User(id,userName,password,fullName,age,email,phone,address));
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("users?action=list");
        requestDispatcher.forward(request,response);
    }
    private void deleteUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
//        RequestDispatcher requestDispatcher = request.getRequestDispatcher("view/listuser.jsp");
    }
    private void insertUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = new User();
        boolean flag = true;
        Map<String, String> hashmap = new HashMap<String, String>();
        int userID = Integer.parseInt(request.getParameter("userID"));
        try{
            String userName = request.getParameter("userName");
            String password = request.getParameter("password");
            String fullname = request.getParameter("fullName");
            int age = Integer.parseInt(request.getParameter("age"));
            String email = request.getParameter("email");
            String phone = request.getParameter("phone");
            String address = request.getParameter("address");
            user = new User(userID,userName, password, fullname, age,email,phone,address);
            ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
            Validator validator = validatorFactory.getValidator();
            Set<ConstraintViolation<User>> constraintViolationSet = validator.validate(user);
            if (constraintViolationSet.isEmpty()) {
                errors = "<ul>";
                for (ConstraintViolation<User> constraintViolation : constraintViolationSet) {
                    errors += "<li>" + constraintViolation.getPropertyPath() + " " + constraintViolation.getMessage() +
                            "</li>";
                }
                errors += "</ul>";
                request.setAttribute("user", user);
                request.setAttribute("errors", errors);
                request.getRequestDispatcher("view/createuser.jsp").forward(request, response);
            } else {
                userDao.insertUser(user);
                response.sendRedirect("product");
            }
        }catch (NumberFormatException ex){
            errors = "<ul>";
            errors+= "<li>" +"input format is wrong, price is a number"+
                    "</li>"+
                    "</ul>";
            request.setAttribute("user", user);
            request.setAttribute("errors", errors);
//            request.getRequestDispatcher("view/createuser.jsp").forward(request,response);
        }
    }
}
