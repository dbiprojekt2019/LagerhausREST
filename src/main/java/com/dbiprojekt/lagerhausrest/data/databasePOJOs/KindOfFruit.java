package com.dbiprojekt.lagerhausrest.data.databasePOJOs;

import lombok.Data;
import javax.persistence.*;

@Data
@Entity
@Table(name = "KindOfFruit")
public class KindOfFruit {

    protected KindOfFruit(){}

    public KindOfFruit(String description){
        this.description = description;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int fruitID;

    private String description;
}
