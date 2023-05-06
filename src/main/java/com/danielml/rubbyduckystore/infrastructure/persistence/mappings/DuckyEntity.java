package com.danielml.rubbyduckystore.infrastructure.persistence.mappings;

import com.danielml.rubbyduckystore.domain.enums.Color;
import com.danielml.rubbyduckystore.domain.enums.Size;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.Collection;
import java.util.Set;

@Entity
@NoArgsConstructor
@Table(name = "duckies")
@Data
public class DuckyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Enumerated(EnumType.STRING)
    private Color color;
    @Enumerated(EnumType.STRING)
    private Size size;
    private double price;
    private int quantity;
    private boolean isDeleted;

    @OneToOne(mappedBy = "ducky")
    private OrderEntity order;

    public DuckyEntity(int id, Color color, Size size, double price, int quantity, boolean isDeleted) {
        this.id = id;
        this.color = color;
        this.size = size;
        this.price = price;
        this.quantity = quantity;
        this.isDeleted = isDeleted;
    }
}
