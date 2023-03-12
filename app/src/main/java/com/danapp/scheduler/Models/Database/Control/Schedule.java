package com.danapp.scheduler.Models.Database.Control;

import java.util.ArrayList;

public class Schedule extends General {

    public Schedule() {
        super();
    }

    public ArrayList<String> getFreePeople(int hour_interval, int day, int semester) {
        try {
            String query1 = "SELECT name FROM people WHERE id NOT IN (SELECT id_person FROM schedule WHERE id IN (SELECT id_schedule FROM courses_per_schedule WHERE id_course IN (SELECT id_course FROM course_properties WHERE days = ? AND id_hour_interval = ?)) and semester = ?)";

            ps = con.prepareStatement(query1);
            ps.setInt(1, day);
            ps.setInt(2, hour_interval);
            ps.setInt(3, semester);
            rs = ps.executeQuery();

            ArrayList<String> result = new ArrayList<>();
            while (rs.next()) {
                result.add(rs.getString("name"));
            }

            return result;
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }

        return null;
    }

    public ArrayList<String> getBusyPeople(int hour_interval, int day, int semester) {
        try {
            String query1 = "SELECT id_course FROM course_properties WHERE days = ? AND id_hour_interval = ?";

            ps = con.prepareStatement(query1);
            ps.setInt(1, day);
            ps.setInt(2, hour_interval);
            rs = ps.executeQuery();

            ArrayList<Integer> result = new ArrayList<>();
            while (rs.next()) {
                result.add(rs.getInt("id_course"));
            }

            ArrayList<String> totalResult = new ArrayList<>();
            for (int id_course : result) {
                String courseName = "SELECT noun FROM course WHERE id = ?";

                ps = con.prepareStatement(courseName);
                ps.setInt(1, id_course);
                rs = ps.executeQuery();

                if (rs.next()) {
                    courseName = rs.getString("noun");
                }

                String query2 = "SELECT name FROM people WHERE id IN (SELECT id_person FROM schedule WHERE id IN (SELECT id_schedule FROM courses_per_schedule WHERE id_course = ?) and semester = ?)";

                ps = con.prepareStatement(query2);
                ps.setInt(1, id_course);
                ps.setInt(2, semester);
                rs = ps.executeQuery();

                while (rs.next()) {
                    totalResult.add(rs.getString("name") + " - " + courseName);
                }
            }

            return totalResult;
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        return null;
    }

}
