package com.danapp.scheduler.Models.Database.Control;

import com.danapp.scheduler.Models.Database.Connect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class General {

    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    public General() {
        Connect connect = new Connect();
        con = connect.getConnection();
    }

}
