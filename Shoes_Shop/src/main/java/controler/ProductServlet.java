package controler;

import DAO.IProductDAO;
import DAO.ITypeDAO;
import DAO.ProductDAO;
import DAO.TypeDAO;
import Model.Product;
import Model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.*;

@WebServlet(name = "userServlet", urlPatterns = "/product")
public class ProductServlet extends HttpServlet {
//    private static final long serialVersionUID = 1L;
    private String errors;
    private ProductDAO productDAO;
    private TypeDAO typeDAO;

    public void init() {
        productDAO = new ProductDAO();
        typeDAO = new TypeDAO();
        if (this.getServletContext().getAttribute("listType")==null){
            this.getServletContext().setAttribute("listType",typeDAO.selectAllType() );
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) {
            action = "";
        }
        try {
            switch (action) {
                case "create":
                    showNewForm(req, resp);
                    break;
                case "update":
                    showEditForm(req,resp);
                    break;
                case "delete":
                    deleteProduct(req,resp);
                    break;
                default:
                    listProductPagging(req, resp);
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) {
            action = "";
        }
        try {
            switch (action) {
                case "create":
                    inserProduct(req, resp);
                    break;
                case "update":
                    updateProduct(req,resp);
                    break;
                default:
                    listProductPagging(req, resp);
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    private void listProductPagging(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int page = 1;
        int recordsPerPage = 5;
        if (request.getParameter("page")!=null){
            page = Integer.parseInt(request.getParameter("page"));
        }
        List<Product> listProduct = productDAO.selectProductPagging((page-1)*recordsPerPage,recordsPerPage);
        int noOfRecord = productDAO.getNoOfRecord();
        int noOfPage = (int)Math.ceil(noOfRecord*1.15)/recordsPerPage;
        request.setAttribute("listProduct", listProduct);
        request.setAttribute("noOfPage", noOfPage);
        request.setAttribute("currentPage", page);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("view/listproduct.jsp");
        requestDispatcher.forward(request, response);
    }

    private void listProduct(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
        List<Product> listProduct = productDAO.selectAllProduct();
        request.setAttribute("listProduct", listProduct);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("view/listproduct.jsp");
        requestDispatcher.forward(request, response);
    }

    private void inserProduct(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
//        String productName = request.getParameter("productName");
//        String productDescription = request.getParameter("productDescription");
//        double price = Double.parseDouble(request.getParameter("price"));
//        int quaility = Integer.parseInt(request.getParameter("quaility"));
//        Product product = new Product(productName, productDescription, price, quaility);
//        productDAO.insertProduct(product);
//        response.sendRedirect("product");
        Product product = new Product();
        boolean flag = true;
        Map<String, String> hashmap = new HashMap<String, String>();
        try{
            String productName = request.getParameter("productName");
            String productDescription = request.getParameter("productDescription");
            double price = Double.parseDouble(request.getParameter("price"));
            int quaility = Integer.parseInt(request.getParameter("quaility"));
            int typeID = Integer.parseInt(request.getParameter("typeID"));
            product = new Product(productName, productDescription, price, quaility,typeID);
            ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
            Validator validator = validatorFactory.getValidator();
            Set<ConstraintViolation<Product>> constraintViolations = validator.validate(product);
            if (!constraintViolations.isEmpty()) {
                errors = "<ul>";
                for (ConstraintViolation<Product> constraintViolation : constraintViolations) {
                    errors += "<li>" + constraintViolation.getPropertyPath() + " " + constraintViolation.getMessage() +
                            "</li>";
                }
                errors += "</ul>";
                request.setAttribute("product", product);
                request.setAttribute("errors", errors);
                request.getRequestDispatcher("view/createproduct.jsp").forward(request, response);
            } else {
                productDAO.insertProduct(product);
                response.sendRedirect("product");
            }
        }catch (NumberFormatException e){
            errors = "<ul>";
            errors+= "<li>" +"input format is wrong, price is a number"+
                    "</li>"+
                    "</ul>";
            request.setAttribute("product", product);
            request.setAttribute("errors", errors);
            request.getRequestDispatcher("view/createproduct.jsp").forward(request, response);
        }
    }
    private void deleteProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("productID"));
        productDAO.deleteProductByID(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("product?action=users");
        dispatcher.forward(request,response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("view/createproduct.jsp");
        dispatcher.forward(request, response);
    }
    private void updateProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int productID = Integer.parseInt(request.getParameter("productID"));
        String productName = request.getParameter("productName");
        String productDescription = request.getParameter("productDescription");
        double price = Double.parseDouble(request.getParameter("price"));
        int quaility = Integer.parseInt(request.getParameter("quaility"));
        int typeID = Integer.parseInt(request.getParameter("typeID"));
        Product product = new Product(productID,productName,productDescription,price,quaility,typeID);
        System.out.println(productID+" " +productName+" " + productDescription+" " + price+" "+ quaility);
        productDAO.updateProductByID(product);
        RequestDispatcher requestDispatcher =request.getRequestDispatcher("product?action=users");
        requestDispatcher.forward(request,response);
    }
    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("productID"));
        Product product = productDAO.selectProductByID(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("view/editproduct.jsp");
        request.setAttribute("product", product);
        typeDAO = new TypeDAO();
        //request.setAttribute("listType",typeDAO.selectAllType() );

        //getServletContext().setAttribute("listType",typeDAO.selectAllType() );
        dispatcher.forward(request, response);

    }
}
