package com.kuzaev.twoprojectnote.object;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "beers")
public class Beer implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String titleBeer;
    private int priceBeer;
    private int ratingBeer;


    public Beer(int id, String titleBeer, int priceBeer, int ratingBeer) {
        this.id = id;
        this.titleBeer = titleBeer;
        this.priceBeer = priceBeer;
        this.ratingBeer = ratingBeer;
    }

    @Ignore
    public Beer(String titleBeer, int priceBeer, int ratingBeer) {
        this.titleBeer = titleBeer;
        this.priceBeer = priceBeer;
        this.ratingBeer = ratingBeer;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitleBeer(String titleBeer) {
        this.titleBeer = titleBeer;
    }

    public void setPriceBeer(int priceBeer) {
        this.priceBeer = priceBeer;
    }

    public void setRatingBeer(int ratingBeer) {
        this.ratingBeer = ratingBeer;
    }

    public String getTitleBeer() {
        return titleBeer;
    }

    public int getPriceBeer() {
        return priceBeer;
    }

    public int getRatingBeer() {
        return ratingBeer;
    }
}
