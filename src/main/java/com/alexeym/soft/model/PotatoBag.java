package com.alexeym.soft.model;

import java.time.Instant;

/**
 * Created by Alexey Matveev on 3/7/2018.
 */
public class PotatoBag {

    private String id;

    private int potatoes;

    private String supplier;

    private Instant packDate;

    private int price;

    public PotatoBag(String id, int potatoes, String supplier, Instant packDate, int price) {
        this.id = id;
        this.potatoes = potatoes;
        this.supplier = supplier;
        this.packDate = packDate;
        this.price = price;
    }

    public PotatoBag withId(String id) {
        return new PotatoBag(id, potatoes, supplier, packDate, price);
    }

    public String getId() {
        return id;
    }

    public int getPotatoes() {
        return potatoes;
    }

    public String getSupplier() {
        return supplier;
    }

    public Instant getPackDate() {
        return packDate;
    }

    public int getPrice() {
        return price;
    }
}
