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
        information.add(new Information("Mitä ei saa tai suositella syötväksi tai juotavaksi raskaana?\n- Ruokaviraston taulukko elintarvikkeista:",  "https://www.ruokavirasto.fi/henkiloasiakkaat/tietoa-elintarvikkeista/elintarvikkeiden-turvallisen-kayton-ohjeet/turvallisen-kayton-ohjeet/"));
        information.add(new Information("Lemmikit ja raskaus\n- Toksoplamoosi -> kissan ulosteesta tuleva saaste", "https://www.terveyskirjasto.fi/terveyskirjasto/tk.koti?p_artikkeli=dlk00619"));
        information.add(new Information("Lemmikit ja raskaus\n- Hyviä huomioita ammattilisilta koirien ja kissojen hankita perheeseen", "https://yle.fi/aihe/artikkeli/2010/10/15/koirat-ja-kissat-lapsiperheessa-ammattilaisten-vinkit"));
        information.add(new Information("Fyysisyys raskaudessa\n- Terveyskylä -> liikunta", "https://www.terveyskyla.fi/naistalo/raskaus-ja-synnytys/raskauden-tuomat-muutokset-ja-yleiset-huolenaiheet/liikunta"));
        information.add(new Information("- Terveyskirjasto -> raskaus ja liikunta", "https://www.terveyskirjasto.fi/terveyskirjasto/tk.koti?p_artikkeli=dlk01034"));
        information.add(new Information("Työ raskaana\n- Työ olot raskauden aikana", "https://www.ttl.fi/tyoymparisto/altisteet/tyoolot-raskauden-aikana/"));
        information.add(new Information("Kosmetiikka\n- Kosmetiikka ja hyönteiskarkotteet raskaana oleville", "https://www.hus.fi/potilaalle/nain-saat-apua/aidin-laakeneuvonta-teratologinen-tietopalvelu/kosmetiikka-ja"));
        information.add(new Information("Fun fact lapsesi koosta raskauden aikana!\n", "https://www.vau.fi/raskaus/nyt-vauva-kohdussasi-on-yhta-iso-kuin/"));
        information.add(new Information("Kela asiat kuntoon!\n", "https://www.kela.fi/aitiysraha"));
        information.add(new Information("Synnytyksiin valmistautumine\n", "https://www.terveyskyla.fi/naistalo/raskaus-ja-synnytys/synnytys/synnytykseen-valmistautuminen-ja-synnytys"));
    }

    // return toString method
    public List<Information> getLink() {
        return information;
    }

}

