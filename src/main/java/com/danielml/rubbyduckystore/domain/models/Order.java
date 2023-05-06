package com.danielml.rubbyduckystore.domain.models;

import com.danielml.rubbyduckystore.domain.enums.Country;
import com.danielml.rubbyduckystore.domain.enums.PackagingType;
import com.danielml.rubbyduckystore.domain.enums.ProtectionType;
import com.danielml.rubbyduckystore.domain.enums.ShippingMode;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Order {
    private Integer id;
    private Country destinationCountry;
    private ShippingMode shippingMode;
    private PackagingType packagingType;
    private ProtectionType protectionType;
    private int quantity;
    private boolean hasMoistureAbsorbingBalls;
    private double total;

    private Integer duckyId;
    private Ducky ducky;

    private List<String> details = new ArrayList<>();

    public Order() {

    }

    private Order(Integer id,
                  Country destinationCountry,
                  ShippingMode shippingMode,
                  PackagingType packaging,
                  ProtectionType protectionType,
                  int quantity,
                  boolean hasMoistureAbsorbingBalls,
                  double total,
                  Integer duckyId) {
        this.id = id;
        this.destinationCountry = destinationCountry;
        this.shippingMode = shippingMode;
        this.packagingType = packaging;
        this.protectionType = protectionType;
        this.quantity = quantity;
        this.hasMoistureAbsorbingBalls = hasMoistureAbsorbingBalls;
        this.total = total;
        this.duckyId = duckyId;
    }

    public static Order create(Integer id,
                               Country destinationCountry,
                               ShippingMode shippingMode,
                               PackagingType packaging,
                               ProtectionType protectionType,
                               int quantity,
                               boolean hasMoistureAbsorbingBalls,
                               double total,
                               Integer duckyId) {
        return new Order(id,
                destinationCountry,
                shippingMode,
                packaging,
                protectionType,
                quantity,
                hasMoistureAbsorbingBalls,
                total,
                duckyId);
    }

    public void applyPackaging() {
        switch (this.ducky.getSize()) {
            case XLarge, Large -> this.packagingType = PackagingType.Wood;
            case Medium -> this.packagingType = PackagingType.PaperBoard;
            case Small, XSmall -> this.packagingType = PackagingType.Plastic;
        }
    }

    public void applyProtection() {
        switch (this.shippingMode) {
            case Air -> {
                if (this.packagingType == PackagingType.Wood || this.packagingType == PackagingType.PaperBoard) {
                    this.protectionType = ProtectionType.PlasticFormBalls;
                } else {
                    this.protectionType = ProtectionType.BubbleBags;
                }
            }
            case Land -> this.protectionType = ProtectionType.PlasticFormBalls;
            case Sea -> {
                this.protectionType = ProtectionType.BubbleBags;
                this.hasMoistureAbsorbingBalls = true;
            }
        }
    }

    public void calculateTotalCost() {
        this.total = this.quantity * this.ducky.getPrice();
    }

    public void applyDiscountByQuantity() {
        if (this.quantity > 100) {
            this.total = this.total - (this.total * 0.2);
            details.add("Se aplicó descuento del 20% por cantidad mayor a 100 unidades");
        }
    }

    public void calculateTotalCostByPackaging() {
        switch (this.packagingType) {
            case Wood -> {
                this.total = this.total + (this.total * 0.05);
                details.add("Se aplicó un incremento de 5% del total por el paquete de madera");
            }
            case PaperBoard -> {
                this.total = this.total - (this.total * 0.01);
                details.add("Se aplicó un descuento de 1% del total por el paquete de cartón");
            }
            case Plastic -> {
                this.total = this.total + (this.total * 0.1);
                details.add("Se aplicó un incremento de 10% del total por el paquete de plástico");
            }
        }
    }

    public void calculateTotalCostByDestinationCountry() {
        switch (this.destinationCountry) {
            case Bolivian -> {
                this.total = this.total + (this.total * 0.13);
                details.add("Se aplicó un incremento de 13% del total por Bolivia como país de destino");
            }
            case India -> {
                this.total = this.total + (this.total * 0.19);
                details.add("Se aplicó un incremento de 19% del total por India como país de destino");
            }
            case USA -> {
                this.total = this.total + (this.total * 0.18);
                details.add("Se aplicó un incremento de 18% del total por USA como país de destino");
            }
            default -> {
                this.total = this.total + (this.total * 0.15);
                details.add("Se aplicó un incremento de 15% del total por el país de destino");
            }
        }
    }

    public void calculateTotalCostByShippingMode() {
        switch (this.shippingMode) {

            case Land -> {
                this.total = 10 * this.quantity;
                details.add("Se aplicó un incremento de 10USD por la cantidad del pedido al total por el modo de envío (Tierra)");
            }
            case Air -> {
                this.total = 30 * this.quantity;
                details.add("Se aplicó un incremento de 30USD por la cantidad del pedido al total por el modo de envío (Aire)");

                if (this.quantity > 1000) {
                    this.total = this.total - (this.total * 0.15);
                    details.add("Se aplicó un descuento de 15% al total por el pedido que excede las 1000 unidades (Aire)");
                }
            }
            case Sea -> {
                this.total = this.total + 400;
                details.add("Se aplicó un incremento de 400USD al total por el modo de envío (Mar)");
            }
        }
    }
}
