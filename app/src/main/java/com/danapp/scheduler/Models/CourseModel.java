package com.danapp.scheduler.Models;

import java.util.Arrays;

public class CourseModel {

    String departament;
    String name;
    String subject;
    String group;
    String nrc;
    String level;
    String teacher;
    String[][] schedule;

    public CourseModel(String departament, String name, String subject, String group, String nrc, String level, String teacher, String[][] schedule) {
        this.departament = departament;
        this.name = name;
        this.subject = subject;
        this.group = group;
        this.nrc = nrc;
        this.level = level;
        this.teacher = teacher;
        this.schedule = schedule;
    }

    public CourseModel() {
        this.departament = "";
        this.name = "";
        this.subject = "";
        this.group = "";
        this.nrc = "";
        this.level = "";
        this.teacher = "";
        this.schedule = new String[5][2];
    }

    public String getDepartament() {
        return departament;
    }

    public void setDepartament(String departament) {
        this.departament = departament;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getNrc() {
        return nrc;
    }

    public void setNrc(String nrc) {
        this.nrc = nrc;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public String[][] getSchedule() {
        return schedule;
    }

    public void setSchedule(String[][] schedule) {
        this.schedule = schedule;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    @Override
    public String toString() {
        return "CourseModel{" +
                "departament='" + departament + '\'' +
                ", name='" + name + '\'' +
                ", subject='" + subject + '\'' +
                ", group='" + group + '\'' +
                ", nrc='" + nrc + '\'' +
                ", level='" + level + '\'' +
                ", teacher='" + teacher + '\'' +
                ", schedule=" + Arrays.toString(schedule) +
                '}';
    }
}
