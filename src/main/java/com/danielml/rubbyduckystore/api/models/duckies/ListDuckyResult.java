package com.danielml.rubbyduckystore.api.models.duckies;

import com.danielml.rubbyduckystore.domain.enums.Color;
import com.danielml.rubbyduckystore.domain.enums.Size;

public record ListDuckyResult(
        Integer id,
        Color color,
        Size size,
        double price,
        int quantity,
        boolean isDeleted
) {
}
