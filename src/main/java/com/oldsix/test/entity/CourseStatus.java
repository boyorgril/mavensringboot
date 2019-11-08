package com.oldsix.test.entity;

public enum CourseStatus {

    FUll("满员"), NOFUll("未满员");

    CourseStatus(String displayName) {
        this.displayName = displayName;
    }
    private String displayName;

    public String getDisplayName() {
        return this.displayName;
    }


}
