package com.example.raskaussovellus;

import android.util.Log;

import java.util.ArrayList;

/**
 * Contains a list of fun facts about your baby and returns them based on the pregnancy week.
 */
public class FunFacts {

    public FunFacts(){
    }

    public String getFact(int week){
        if(week > 20){
            return topArray(week);
        }
        else if(week < 21 && week > 5){
            return bottomArray(week);
        }else{
            return "";
        }
    }
    private String topArray(int data){
        data = data -21;
        ArrayList<String> infoList = new ArrayList<>();
        infoList.add("Lapsesi on nyt naisten 39 kokoisen kengän kokoinen! (täysmitta: 25cm)");
        infoList.add("Lapsesi on nyt kesäkurpitsan mittainen! (täyspituus: 26 cm)");
        infoList.add("Lapsesi on nyt numeroa 42 olevan kengän kokoinen! (täyspituus: 27 cm)");
        infoList.add("Lapsesi on nyt ruokalautasen halkaisijan kokoinen! (28 cm)");
        infoList.add("Lapsesi on nyt amerikkalaisen jalkapallon kokoinen! (29 cm)");
        infoList.add("Lapsesi on nyt barbienuken kokoinen! (30 cm)");
        infoList.add("Lapsesi on nyt LP-levyn kansikotelon kokoinen! (täyspituus: 31 cm)");
        infoList.add("Lapsesi on nyt Baby Born -nuken kokoinen! (32 cm)");
        infoList.add("Lapsesi on nyt avatun servietin sivun mittainen! (täyspituus: 33,5 cm)");
        infoList.add("Lapsesi on nyt neulepuikon kokoinen! (35 cm)");
        infoList.add("Lapsesi on nyt retkikirveen kokoinen! (36 cm)");
        infoList.add("Lapsesi on nyt postilaatikon kokoinen! (täyspituus: 37 cm)");
        infoList.add("Lapsesi on nyt 15-tuumaisen Macbook Pro -tietokoneen kokoinen! (38 cm)");
        infoList.add("Lapsesi on nyt peruspitsan halkaisijan mittainen! (täyspituus: 39 cm)");
        infoList.add("Lapsesi on nyt rumpukapulan kokoinen! (41 cm)");
        infoList.add("Lapsesi on nyt A3-arkin pitkän sivun mittainen! (täyspituus: 42,5 cm)");
        infoList.add("Lapsesi on nyt alustabletin mittainen! (täyspituus: 44cm)");
        infoList.add("Lapsesi on nyt uunipellin pitkän sivun mittainen! (täyspituus: 46 cm)");
        infoList.add("Lapsesi on nyt paistinpannun kokoinen! (48 cm)");
        infoList.add("Lapsesi on nyt keskimittaisen vastasyntyneen kokoinen! (täysmitta: 51 cm)");

        return infoList.get(data);
    }

    private String bottomArray(int week){
        week = week - 5;

        ArrayList<String> infoList = new ArrayList<>();
        infoList.add("Lapsesi on nyt unikonsiemenen kokoinen! (0,5–1 mm)");
        infoList.add("Lapsesi on nyt seesaminsiemenen kokoinen! (1–3 mm)");
        infoList.add("Lapsesi on nyt Hamahelmen kokoinen! (4-8 mm)");
        infoList.add("Lapsesi on nyt mustikan kokoinen! (9–14 mm)");
        infoList.add("Lapsesi on nyt vadelman kokoinen! (15–22 mm)");
        infoList.add("Lapsesi on nyt klemmarin kokoinen! (CRL: 23–32 mm)");
        infoList.add("Lapsesi on nyt mansikan kokoinen! (CRL: 33-41 mm)");
        infoList.add("Lapsesi on nyt limen kokoinen! (CRL: 41–52 mm)");
        infoList.add("Lapsesi on nyt kotiavaimen kokoinen! (CRL: 5,2 cm)");
        infoList.add("Lapsesi on nyt luottokortin kokoinen! (täyspituus: 8,5 cm)");
        infoList.add("Lapsesi on nyt naisen kämmenen mittainen! (täyspituus: 10 cm)");
        infoList.add("Lapsesi on nyt 0,3 litran juomatölkin kokoinen! (täyspituus: 12cm)");
        infoList.add("Lapsesi on nyt sokeripaketin kokoinen! (täyspituus: 16 cm)");
        infoList.add("Lapsesi on nyt blue-ray -kotelon kokoinen! (täyspituus: 17 cm)");
        infoList.add("Lapsesi on nyt hammasharjan kokoinen! (18 cm)");
        infoList.add("Lapsesi on nyt maitotölkin kokoinen! (täyspituus: 21,5 cm)");

        return infoList.get(week);
    }
}
