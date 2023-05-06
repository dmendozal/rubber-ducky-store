package com.danielml.rubbyduckystore.api.models.duckies;

import com.danielml.rubbyduckystore.domain.enums.Color;
import com.danielml.rubbyduckystore.domain.enums.Size;

public record GetDuckyResult(
        Integer id,
        Color color,
        Size size,
        int quantity,
        double price,
        boolean isDeleted
) {
}
