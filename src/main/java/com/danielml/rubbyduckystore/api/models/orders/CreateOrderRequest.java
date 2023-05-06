package com.danielml.rubbyduckystore.api.models.orders;

import com.danielml.rubbyduckystore.domain.enums.Country;
import com.danielml.rubbyduckystore.domain.enums.ShippingMode;

public record CreateOrderRequest(
        Integer duckyId,
        int quantity,
        Country country,
        ShippingMode shippingMode
) {
}
