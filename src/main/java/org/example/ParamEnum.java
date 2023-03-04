package org.example;

enum ParamEnum {
    MANAGER("manager"),
    SENIOR_MANAGER("senior manager"),
    LEAD_MANAGER("lead manager");
    ParamEnum(String description){
        this.description = description;
    }
    private String description;
    public String getDescription(){
        return description;
    }
}
