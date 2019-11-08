package com.oldsix.test.datashow;

public class CourseShow {
    /**id*/
    private Long id;
    /**课程号*/
    private String courseNumber;
    /**课程名称*/
    private String courseName;
    /**教师*/
    private String teacher;
    /**简介*/
    private String introduction;
    /**学分*/
    private Double score;
    /**最大人数*/
    private int maxNumberOfStudent;
    /**现在人数*/
    private int numberOfStudent;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCourseNumber() {
        return courseNumber;
    }

    public void setCourseNumber(String courseNumber) {
        this.courseNumber = courseNumber;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public int getMaxNumberOfStudent() {
        return maxNumberOfStudent;
    }

    public void setMaxNumberOfStudent(int maxNumberOfStudent) {
        this.maxNumberOfStudent = maxNumberOfStudent;
    }

    public int getNumberOfStudent() {
        return numberOfStudent;
    }

    public void setNumberOfStudent(int numberOfStudent) {
        this.numberOfStudent = numberOfStudent;
    }
}
