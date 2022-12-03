package com.example.proyectopvzgw2;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Character {
    @PrimaryKey(autoGenerate = true)
    int id;

    int image;
    String name;
    String description;

    public Character(int image, String name, String description) {
        this.name = name;
        this.description = description;
        this.image = image;
    }
}
