package com.danapp.scheduler.Models.Database.Control;

public class Settings extends General{

    public Settings() {
        super();
    }

    public String getMessage() {
        String message = "";
        try {
            String query = "SELECT * FROM settings WHERE name = 'global-message'";

            ps = con.prepareStatement(query);
            rs = ps.executeQuery();

            if (rs.next()) {
                message = rs.getString("content");
            }
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        return message;
    }

}
