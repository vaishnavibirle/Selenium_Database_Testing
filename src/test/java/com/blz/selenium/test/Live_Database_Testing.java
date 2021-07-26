package com.blz.selenium.test;

import com.blz.selenium.base.BaseClass1;
import com.blz.selenium.pages.Login;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.io.IOException;
import java.sql.*;


public class Live_Database_Testing extends BaseClass1 {

    public Connection connection;
    public static int rowCount;
    public int noOfRowsAffected;

    @Test(priority = 1)
    public void retrieve_data() throws SQLException {

        connection = this.getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from login_credentials");
        System.out.println(" username " + " password ");
        while (resultSet.next()) {
            String username = resultSet.getString(1);
            String password = resultSet.getString(2);
            System.out.println( username + " " + password );
            rowCount++;
        }
    }

    @Test(priority = 2)
    public void insert_data() throws SQLException {

        connection = this.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("insert into login_credentials (username,password)values(?,?)");

        preparedStatement.setString(1, "mehta@gmail.com");
        preparedStatement.setString(2, "pass@123");

        noOfRowsAffected = preparedStatement.executeUpdate();
        Assert.assertEquals(noOfRowsAffected, 1);
        retrieve_data();
    }

    @Test(priority = 3)
    public void update_data() {

        connection = this.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("update login_credentials set username=? where password=?");
            preparedStatement.setString(1, "Saniya");
            preparedStatement.setString(2, "Pass");

            noOfRowsAffected = preparedStatement.executeUpdate();
            Assert.assertEquals(noOfRowsAffected, 1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test(priority = 4)
    public void delete_data() throws SQLException {

        connection = this.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("delete from login_credentials where username=?");
        preparedStatement.setString(1, "Saniya");
        preparedStatement.executeUpdate();
    }

    @Test(priority = 5)
    public void login_using_data_from_database() throws InterruptedException, SQLException, IOException {

        setUpBrowserLaunching();
        ResultSet resultSet;
        String username;
        String password;
        connection = this.getConnection();
        Statement statement = connection.createStatement();
        resultSet = statement.executeQuery("select * from login_credentials LIMIT 1");
        while (resultSet.next()) {
            username = resultSet.getString(1);
            password = resultSet.getString(2);
            Login login = new Login(driver);
            login.login_to_twitter(username,password);
            String expectedEmail = "vaishnavibirle2209@gmail.com";
            Assert.assertEquals(username, expectedEmail);
            driver.close();
        }
    }
}
