package com.example.dami.anif;

import static android.R.attr.name;
import static com.example.dami.anif.R.id.carbons;
import static com.example.dami.anif.R.id.fats;
import static com.example.dami.anif.R.id.proteins;

/**
 * Created by dami on 07.06.2017.
 */

public class Word {
    private String name;
    private int kcl;
    private int grams;
    private int proteins;
    private int carbons;
    private int fats;
    private boolean isFavorite;

    //TODO
    //do wstecznej kompatybilności po dodaniu bazy danych usunac!!!!!
    public Word(int kcl, int proteins, int carbons, int fats) {
        this.kcl = kcl;
        this.proteins = proteins;
        this.carbons = carbons;
        this.fats = fats;
    }

    public Word(String name, int kcl, int grams, int proteins, int carbons, int fats, boolean isFavorite) {
        this.name = name;
        this.kcl = kcl;
        this.grams = grams;
        this.proteins = proteins;
        this.carbons = carbons;
        this.fats = fats;
        this.isFavorite = isFavorite;
    }

    public String getName() {
        return name;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public int getGrams() {
        return grams;
    }

    public int getKcl() {
        return kcl;
    }

    public int getProteins() {
        return proteins;
    }

    public int getCarbons() {
        return carbons;
    }

    public int getFats() {
        return fats;
    }

    public String getStringKcl() {
        return Integer.toString(kcl);
    }

    public String getStringProteins() {
        return Integer.toString(proteins);
    }

    public String getStringCarbons() {
        return Integer.toString(carbons);
    }

    public String getStringFats() {
        return Integer.toString(fats);
    }

}
