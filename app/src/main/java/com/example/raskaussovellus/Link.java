package com.example.raskaussovellus;

import java.util.ArrayList;
import java.util.List;

public class Link {
    private List<Information> information;

    private static final Link ourInstance = new Link();

    public static Link getInstance() {
        return ourInstance;
    }

    // list names and websites
    private Link() {
        information = new ArrayList<Information>();
        information.add(new Information("Suosituksia raskaana?\n- Ruokaviraston taulukko elintarvikkeista\n",  "https://www.ruokavirasto.fi/henkiloasiakkaat/tietoa-elintarvikkeista/elintarvikkeiden-turvallisen-kayton-ohjeet/turvallisen-kayton-ohjeet/"));
        information.add(new Information("\nLemmikit ja raskaus\n- Toksoplamoosi\n", "https://www.terveyskirjasto.fi/terveyskirjasto/tk.koti?p_artikkeli=dlk00619\n"));
        information.add(new Information("\nLemmikit ja raskaus\n- Hyviä huomioita ammattilisilta koirien ja kissojen hankita perheeseen\n", "https://yle.fi/aihe/artikkeli/2010/10/15/koirat-ja-kissat-lapsiperheessa-ammattilaisten-vinkit"));
        information.add(new Information("\nFyysisyys raskaudessa\n- Terveyskylä\n", "https://www.terveyskyla.fi/naistalo/raskaus-ja-synnytys/raskauden-tuomat-muutokset-ja-yleiset-huolenaiheet/liikunta"));
        information.add(new Information("\nFyysisyys raskaudessa\n-Terveyskirjasto\n", "https://www.terveyskirjasto.fi/terveyskirjasto/tk.koti?p_artikkeli=dlk01034"));
        information.add(new Information("\nTyö raskaana\n- Työ olot raskauden aikana\n", "https://www.ttl.fi/tyoymparisto/altisteet/tyoolot-raskauden-aikana/"));
        information.add(new Information("\nKosmetiikka\n- Kosmetiikka ja hyönteiskarkotteet\n", "https://www.hus.fi/potilaalle/nain-saat-apua/aidin-laakeneuvonta-teratologinen-tietopalvelu/kosmetiikka-ja"));
        information.add(new Information("\nRaskaus viikko viikolta\n", "https://natalben.fi/raskaus-viikko-viikolta/\n"));
        information.add(new Information("\nFun fact lapsesi koosta raskauden aikana!\n", "https://www.vau.fi/raskaus/nyt-vauva-kohdussasi-on-yhta-iso-kuin/"));
        information.add(new Information("\nKela asiat kuntoon!\n", "https://www.kela.fi/aitiysraha"));
        information.add(new Information("\nSynnytyksiin valmistautumine\n", "https://www.terveyskyla.fi/naistalo/raskaus-ja-synnytys/synnytys/synnytykseen-valmistautuminen-ja-synnytys"));
    }

    // return toString method
    public List<Information> getLink() {
        return information;
    }

}

