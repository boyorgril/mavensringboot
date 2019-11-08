package com.oldsix.test.entity;

public enum StudentStatus {

    ACTIVE("正校"), GRADUATION("毕业");

    StudentStatus(String displayName) {
        this.displayName = displayName;
    }
    private String displayName;

    public String getDisplayName() {
        return this.displayName;
    }

}
