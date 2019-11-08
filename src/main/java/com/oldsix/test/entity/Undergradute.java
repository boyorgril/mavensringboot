package com.oldsix.test.entity;

public class Undergradute extends StudentEntity{

    private String teacherName;
    private String myclass;

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getMyclass() {
        return myclass;
    }

    public void setMyclass(String myclass) {
        this.myclass = myclass;
    }
}
