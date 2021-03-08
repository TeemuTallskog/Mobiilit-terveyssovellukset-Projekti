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
        infoList.add("Lapsesi on nyt naisten 39 kokoisen kengän kokoinen!\n(täysmitta: 25cm)");
        infoList.add("Lapsesi on nyt kesäkurpitsan mittainen!\n(täyspituus: 26 cm)");
        infoList.add("Lapsesi on nyt numeroa 42 olevan kengän kokoinen!\n(täyspituus: 27 cm)");
        infoList.add("Lapsesi on nyt ruokalautasen halkaisijan kokoinen!\n(28 cm)");
        infoList.add("Lapsesi on nyt amerikkalaisen jalkapallon kokoinen!\n(29 cm)");
        infoList.add("Lapsesi on nyt barbienuken kokoinen!\n(30 cm)");
        infoList.add("Lapsesi on nyt LP-levyn kansikotelon kokoinen!\n(täyspituus: 31 cm)");
        infoList.add("Lapsesi on nyt Baby Born -nuken kokoinen!\n(32 cm)");
        infoList.add("Lapsesi on nyt avatun servietin sivun mittainen!\n(täyspituus: 33,5 cm)");
        infoList.add("Lapsesi on nyt neulepuikon kokoinen!\n(35 cm)");
        infoList.add("Lapsesi on nyt retkikirveen kokoinen!\n(36 cm)");
        infoList.add("Lapsesi on nyt postilaatikon kokoinen!\n(täyspituus: 37 cm)");
        infoList.add("Lapsesi on nyt 15-tuumaisen Macbook Pro -tietokoneen kokoinen!\n(38 cm)");
        infoList.add("Lapsesi on nyt peruspitsan halkaisijan mittainen!\n(täyspituus: 39 cm)");
        infoList.add("Lapsesi on nyt rumpukapulan kokoinen!\n(41 cm)");
        infoList.add("Lapsesi on nyt A3-arkin pitkän sivun mittainen!\n(täyspituus: 42,5 cm)");
        infoList.add("Lapsesi on nyt alustabletin mittainen!\n(täyspituus: 44cm)");
        infoList.add("Lapsesi on nyt uunipellin pitkän sivun mittainen!\n(täyspituus: 46 cm)");
        infoList.add("Lapsesi on nyt paistinpannun kokoinen!\n(48 cm)");
        infoList.add("Lapsesi on nyt keskimittaisen vastasyntyneen kokoinen!\n(täysmitta: 51 cm)");

        return infoList.get(data);
    }

    private String bottomArray(int week){
        week = week - 5;

        ArrayList<String> infoList = new ArrayList<>();
        infoList.add("Lapsesi on nyt unikonsiemenen kokoinen!\n(0,5–1 mm)");
        infoList.add("Lapsesi on nyt seesaminsiemenen kokoinen!\n(1–3 mm)");
        infoList.add("Lapsesi on nyt Hamahelmen kokoinen!\n(4-8 mm)");
        infoList.add("Lapsesi on nyt mustikan kokoinen!\n(9–14 mm)");
        infoList.add("Lapsesi on nyt vadelman kokoinen!\n(15–22 mm)");
        infoList.add("Lapsesi on nyt klemmarin kokoinen!\n(CRL: 23–32 mm)");
        infoList.add("Lapsesi on nyt mansikan kokoinen!\n(CRL: 33-41 mm)");
        infoList.add("Lapsesi on nyt limen kokoinen!\n(CRL: 41–52 mm)");
        infoList.add("Lapsesi on nyt kotiavaimen kokoinen!\n(CRL: 5,2 cm)");
        infoList.add("Lapsesi on nyt luottokortin kokoinen!\n(täyspituus: 8,5 cm)");
        infoList.add("Lapsesi on nyt naisen kämmenen mittainen!\n(täyspituus: 10 cm)");
        infoList.add("Lapsesi on nyt 0,3 litran juomatölkin kokoinen!\n(täyspituus: 12cm)");
        infoList.add("Lapsesi on nyt sokeripaketin kokoinen!\n(täyspituus: 16 cm)");
        infoList.add("Lapsesi on nyt blue-ray -kotelon kokoinen!\n(täyspituus: 17 cm)");
        infoList.add("Lapsesi on nyt hammasharjan kokoinen!\n(18 cm)");
        infoList.add("Lapsesi on nyt maitotölkin kokoinen!\n(täyspituus: 21,5 cm)");

        return infoList.get(week);
    }
}
