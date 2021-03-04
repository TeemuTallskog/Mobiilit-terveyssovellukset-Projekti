package com.example.raskaussovellus;

public class Information {
    private String name;
    private String website;

    public Information (String name, String website){
        this.name = name;
        this.website = website;
    }

    public String getName() {
        return this.name;
    }
    public String getWebsite() {
        return this.website;
    }

    @Override
    public String toString(){
        return this.name;
    }
}

