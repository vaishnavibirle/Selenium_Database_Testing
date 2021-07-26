package com.blz.selenium.test;

import com.blz.selenium.base.BaseClass;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Database_Test extends BaseClass {

    static Statement statement;
    static String sqlQuery;

    @Test(priority = 6)
    public void data_to_be_insert() {
        try {
            sqlQuery = "insert into data_table (first_name, last_name, id) " +
                    "values('Rushika', 'mehta', '89'), ('Shreya', 'jain', '90'),('Ani','Chaturvedi','91')";
            int noOfRowsAffected = statement.executeUpdate(sqlQuery);
            Assert.assertEquals(noOfRowsAffected, 3);
            System.out.println("Data is inserted successfully");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test(priority = 7)
    public void data_to_be_update() {
        try {
            sqlQuery = "update data_table set last_name = 'Singh' where first_name = 'Rushika';";
            int noOfRowsAffected = statement.executeUpdate(sqlQuery);
            Assert.assertEquals(noOfRowsAffected, 1);
            System.out.println("Data is updated successfully");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test(priority = 8)
    public void data_to_be_retrieved() {
        try {
            List<Object[]> retrievedData = new ArrayList<>();
            sqlQuery = "select * from data_table";
            ResultSet resultSet = statement.executeQuery(sqlQuery);
            while (resultSet.next()) {
                String first_name = resultSet.getString(1);
                String last_name = resultSet.getString(2);
                int id = resultSet.getInt(3);
                System.out.println(" "+first_name+" " +" "+last_name+" "+id+" ");
                Object[] set = new Object[]{first_name, last_name, id};
                retrievedData.add(set);
            }
            Assert.assertEquals(retrievedData.size(), 4);
            System.out.println("Data is retrieved successfully");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test(priority = 9)
    public void data_to_be_delete() {
        try {
            sqlQuery = "delete from data_table where first_name = 'Shreya'";
            int noOfRowsAffected = statement.executeUpdate(sqlQuery);
            Assert.assertEquals(noOfRowsAffected, 1);
            System.out.println("Data is deleted successfully");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
