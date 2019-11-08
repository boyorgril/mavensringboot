package com.oldsix.test.datashow;

public class StudentShow {

    private int id;
    /**姓名*/
    private String name;
    /**学号*/
    private String empId;
    /**区分研究生本科生*/
    private String status;
    /**性别*/
    private String sex;
    /**年级*/
    private String grade;
    /**专业*/
    private String profession;
    /**总学分*/
    private Double totalScore;
    /**导师*/
    private String tutor;
    /**研究方向*/
    private String searchDirection;
    /**班主任*/
    private String teacherName;
    /**班级*/
    private String myclass;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public Double getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(Double totalScore) {
        this.totalScore = totalScore;
    }

    public String getTutor() {
        return tutor;
    }

    public void setTutor(String tutor) {
        this.tutor = tutor;
    }

    public String getSearchDirection() {
        return searchDirection;
    }

    public void setSearchDirection(String searchDirection) {
        this.searchDirection = searchDirection;
    }

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
