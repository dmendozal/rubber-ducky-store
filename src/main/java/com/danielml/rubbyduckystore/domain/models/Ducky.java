package com.danielml.rubbyduckystore.domain.models;

import com.danielml.rubbyduckystore.domain.enums.Color;
import com.danielml.rubbyduckystore.domain.enums.Size;
import lombok.Data;

@Data
public class Ducky {
    private Integer id;
    private Color color;
    private Size size;
    private double price;
    private int quantity;
    private boolean isDeleted;

    public Ducky(){

    }

    public Ducky(int id,
                 Color color,
                 Size size,
                 double price,
                 int quantity,
                 boolean isDeleted) {
        this.id = id;
        this.color = color;
        this.size = size;
        this.price = price;
        this.quantity = quantity;
        this.isDeleted = isDeleted;
    }

    public void addNewDucky(int quantity) {
        this.quantity = this.quantity + quantity;
    }

    public void remove() {
        this.isDeleted = true;
    }

    public void updateDucky(int quantity, double price) {
        this.quantity = quantity;
        this.price = price;
    }
}
