package com.danielml.rubbyduckystore.infrastructure.persistence.mappings;

import com.danielml.rubbyduckystore.domain.enums.Country;
import com.danielml.rubbyduckystore.domain.enums.PackagingType;
import com.danielml.rubbyduckystore.domain.enums.ProtectionType;
import com.danielml.rubbyduckystore.domain.enums.ShippingMode;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Entity
@NoArgsConstructor
@Table(name = "orders")
@Data
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    private Country destinationCountry;
    @Enumerated(EnumType.STRING)
    private ShippingMode shippingMode;
    @Enumerated(EnumType.STRING)
    private PackagingType packagingType;
    @Enumerated(EnumType.STRING)
    private ProtectionType protectionType;
    private int quantity;
    private boolean hasMoistureAbsorbingBalls;
    private double total;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "ducky_id", nullable = false)
    private DuckyEntity ducky;

    public OrderEntity(Integer id,
                       Country destinationCountry,
                       ShippingMode shippingMode,
                       PackagingType packaging,
                       ProtectionType protectionType,
                       int quantity,
                       boolean hasMoistureAbsorbingBalls,
                       double total,
                       DuckyEntity ducky) {
        this.id = id;
        this.destinationCountry = destinationCountry;
        this.shippingMode = shippingMode;
        this.packagingType = packaging;
        this.protectionType = protectionType;
        this.quantity = quantity;
        this.hasMoistureAbsorbingBalls = hasMoistureAbsorbingBalls;
        this.total = total;
        this.ducky = ducky;
    }
}
