package com.example.raskaussovellus;

import java.util.ArrayList;
import java.util.List;

public class GenderSingleton {
    /**
     * Instance is created automatically when the class is loaded
     */
    private static final GenderSingleton genderInstance = new GenderSingleton();

    private List<Genders> genders;

    /**
     * Get reference to the Gender data stories
     * @return
     */
    public static GenderSingleton getInstanceGender() {
        return genderInstance;
    }

    /**
     * Creates empty list
     * adds genders to that list
     */
    private GenderSingleton() {
        genders = new ArrayList<Genders>();
        genders.add(new Genders("Female names"));
        genders.add(new Genders("Male names"));
        genders.add(new Genders("Neutral names"));
    }

    /**
     * returns genders to the ListView widget on each row
     * @return
     */
    public List<Genders> getGenders() {
        return this.genders;
    }

}
