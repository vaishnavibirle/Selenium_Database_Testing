package com.blz.selenium.base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class BaseClass1 {

    public static WebDriver driver;
    static Connection connection;
    static Statement statement;
    public static String DB_URL = "jdbc:mysql://localhost/twitter_login_data";
    public static String DB_USERNAME = "root";
    public static String DB_PASSWORD = "Vaishnavi@123";

    @BeforeTest
    public Connection getConnection() {
        try {
//created a database connection
            Class.forName("com.mysql.cj.jdbc.Driver");
//get connection to database
            connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);

        } catch (ClassNotFoundException | SQLException  exception) {
            System.out.println(exception.getMessage());
        }
        return connection;
    }

    public void setUpBrowserLaunching() throws InterruptedException {

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications");
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.get("https://www.twitter.com/");
        Thread.sleep(1000);
    }

    @AfterTest
    public void tearDown() throws SQLException {
        connection.close();
    }

}
