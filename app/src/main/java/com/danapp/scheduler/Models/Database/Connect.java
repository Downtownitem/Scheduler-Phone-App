package com.danapp.scheduler.Models.Database;

import android.os.StrictMode;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Class to connect to the database automatically
 *
 * @version 18.0.1
 * @since 2022-06-12
 */

public class Connect {

    public static Connection con;

    public Connect() {
        if (con == null) {
            try {
                StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                StrictMode.setThreadPolicy(policy);

                Class.forName("com.mysql.jdbc.Driver");
                con = DriverManager.getConnection(
                        "jdbc:mysql://us-east.connect.psdb.cloud/scheduler?sslMode=VERIFY_IDENTITY",
                        "au5bjgjihutdma43qjvd",
                        "pscale_pw_8nL8fBXMTjH2Rd3QOvaUa7usJ8qUKxNkwoSOGB1Lo0j");

            } catch (ClassNotFoundException | SQLException e) {
                System.out.println("Error: " + e);
            }
        }
    }

    public Connection getConnection() {
        return con;
    }

    public void disconnect() {
        con = null;
        System.out.println("Disconnected");
    }

}
