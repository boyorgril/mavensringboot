package com.oldsix.test.entity;

public enum SexType {

    MAN("男"), WOMAN("女"), MIDDLE("其他");

    SexType(String displayName) {
        this.displayName = displayName;
    }
    private String displayName;

    public String getDisplayName() {
        return this.displayName;
    }

}
