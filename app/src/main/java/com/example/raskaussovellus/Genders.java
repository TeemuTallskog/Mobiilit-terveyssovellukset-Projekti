package com.example.raskaussovellus;

public class Genders {
    private String gender;

    /**
     * Creates the genders for the list
     * @param name
     */
    public Genders(String name){
        this.gender = name;
    }

    /**
     * Called when ListView shows genders
     * @return gender as string in list
     */
    @Override
    public String toString(){
        return this.gender;
    }
}