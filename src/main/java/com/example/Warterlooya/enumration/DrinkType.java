package com.example.Warterlooya.enumration;

public enum DrinkType {

    WATER("물"),
    COFFEE("커피"),
    TEA("차"),
    Milk("우유"),
    SPARKLING_WATER("탄산수"),
    Juice("쥬스");

    private final String description;

    DrinkType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }


}
