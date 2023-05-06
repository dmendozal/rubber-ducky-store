package com.danielml.rubbyduckystore.api.models.duckies;

public record UpdateDuckyRequest(Integer id, int quantity, double price) {
}
