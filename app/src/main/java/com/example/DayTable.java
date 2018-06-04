package com.example.dami.anif.Data;

/**
 * Created by dami on 11.06.2017.
 */

public class DayTable {
    private String name;
    private int kcl;
    private int grams;
    private int proteins;
    private int carbons;
    private int fats;
    private boolean isFavorite;

    // constructors
    public DayTable() {
    }

    public DayTable(String name, int kcl, int grams, int proteins, int carbons, int fats, boolean isFavorite) {
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

    public void setName(String name) {
        this.name = name;
    }

    public int getKcl() {
        return kcl;
    }

    public void setKcl(int kcl) {
        this.kcl = kcl;
    }

    public int getGrams() {
        return grams;
    }

    public void setGrams(int grams) {
        this.grams = grams;
    }

    public int getProteins() {
        return proteins;
    }

    public void setProteins(int proteins) {
        this.proteins = proteins;
    }

    public int getCarbons() {
        return carbons;
    }

    public void setCarbons(int carbons) {
        this.carbons = carbons;
    }

    public int getFats() {
        return fats;
    }

    public void setFats(int fats) {
        this.fats = fats;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }
}
