package com.danielml.rubbyduckystore.api.models.duckies;

import com.danielml.rubbyduckystore.domain.enums.Color;
import com.danielml.rubbyduckystore.domain.enums.Size;

public record CreateDuckyResult(Integer id, Color color, Size size, int quantity, double price) {
}
