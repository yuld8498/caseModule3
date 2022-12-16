package DAO;

import Model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDao implements IUserDao {
    private String jdbcURL = "jdbc:mysql://localhost:3306/quanlisanpham?useSSL=false";
    private String jdbcUsername = "root";
    private String jdbcPassword = "1234";
    private static final String INSERT_USER_SQL = "INSERT INTO users " +
            "(userName, password, fullname, age, email, phoneNumber, address)VALUES" + "(?,?,?,?,?,?,?);";
    private static final String SELECT_ALL_USERS = "select * from users";
    private static final String DELETE_USERS_SQL = "delete from users where id = ?;";
    private static final String UPDATE_USERS_SQL = "update users" +
            " set fullname = ?, age =?,email= ? ,phoneNumber =?, address =? where id = ?;";
    private static final String SELECT_USER_BY_ID = "select fullName,age, email,phoneNumber, address from users where id =?";
    private static final String UPDATE_PASSWORD_SQL = "update users" +
            " set password =? where id = ?;";

    public UserDao() {
    }

    protected Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection= DriverManager.getConnection(jdbcURL,jdbcUsername,jdbcPassword);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return connection;
    }

    @Override
    public void insertUser(User user) {
        try(Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USER_SQL)){
            preparedStatement.setString(1,user.getUserName());
            preparedStatement.setString(2,user.getPassword());
            preparedStatement.setString(3,user.getFullName());
            preparedStatement.setInt(4,user.getAge());
            preparedStatement.setString(5,user.getEmail());
            preparedStatement.setString(6,user.getPhoneNumber());
            preparedStatement.setString(7,user.getAddress());
            preparedStatement.executeUpdate();
        }catch (SQLException ex){
            printSQLException(ex);
        }
    }

    @Override
    public User selectUserByID(int id) {
        User user = null;
        try(Connection connection =getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_ID)){
            preparedStatement.setInt(1,id);
            ResultSet resultSet =preparedStatement.executeQuery();
            while (resultSet.next()){
                String fullName = resultSet.getString("fullName");
                int age = resultSet.getInt("age");
                String email = resultSet.getString("email");
                String phoneNumber = resultSet.getString("phoneNumber");
                String address = resultSet.getString("address");
                user = new User(fullName,age,email,phoneNumber,address);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return user;
    }

    @Override
    public List<User> selectAllUsers() {
        List<User> list = new ArrayList<>();
        try(Connection connection =getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_USERS)){
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                int userID = resultSet.getInt("UserID");
                String userName = resultSet.getString("UserName");
                String password = resultSet.getString("password");
                String fullName = resultSet.getString("fullName");
                int age = resultSet.getInt("age");
                String email = resultSet.getString("email");
                String phoneNumber = resultSet.getString("phoneNumber");
                String address = resultSet.getString("address");
                list.add(new User(userID,userName,password,fullName,age,email,phoneNumber,address));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return list;
    }

    @Override
    public boolean deleteUser(int id) throws SQLException {
        boolean rowDeleted;
        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(DELETE_USERS_SQL);) {
            preparedStatement.setInt(1, id);
            rowDeleted = preparedStatement.executeUpdate() > 0;
        }
        return rowDeleted;
    }

    @Override
    public boolean updateUserPassword(int id, String newPassword) throws SQLException {
        boolean updatePassword;
        try(Connection connection =getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_PASSWORD_SQL)){
            preparedStatement.setString(1,newPassword);
            preparedStatement.setInt(2, id);
            updatePassword =preparedStatement.executeUpdate()>0;
        }
        return updatePassword;
    }

    @Override
    public boolean updateUser(User user) throws SQLException {
        boolean rowUpdate;
        try(Connection connection = getConnection(); PreparedStatement preparedStatement =connection.prepareStatement(UPDATE_USERS_SQL)){
            preparedStatement.setString(1, user.getFullName());
            preparedStatement.setInt(2,user.getAge());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setString(4, user.getPhoneNumber());
            preparedStatement.setString(5, user.getAddress());
            preparedStatement.setInt(6,user.getUserID());

            rowUpdate = preparedStatement.executeUpdate()>0;
        }
        return rowUpdate;
    }


    private void printSQLException(SQLException ex) {
        for (Throwable e : ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }
}
