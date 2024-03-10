import java.sql.*;

public class Main {

    public static void main(String[] args) throws Exception {
        String url = "jdbc:mysql://localhost:3306/store2";
        String user = "root";
        String password;

        Connection connection = DriverManager.getConnection(url, user, password);

        String sql = "INSERT INTO product (name, price) VALUES (?, ?)";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, "blade");
        statement.setDouble(2, 70);
        statement.executeUpdate();

        sql = "SELECT * FROM product WHERE id = 1";
        statement = connection.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            String name = resultSet.getString("name");
            String email = resultSet.getString("price");
            System.out.println("Name: " + name);
            System.out.println("Price: " + email);
        }
        sql = "UPDATE product SET name = ? WHERE id = 1";
        statement = connection.prepareStatement(sql);
        statement.setString(1, "machete");
        statement.executeUpdate();


        sql = "DELETE FROM product WHERE id = 1";
        statement = connection.prepareStatement(sql);
        statement.executeUpdate();

        connection.close();
    }
}