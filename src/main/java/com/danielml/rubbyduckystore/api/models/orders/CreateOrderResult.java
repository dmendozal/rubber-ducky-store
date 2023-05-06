package com.danielml.rubbyduckystore.api.models.orders;

import com.danielml.rubbyduckystore.domain.enums.PackagingType;
import com.danielml.rubbyduckystore.domain.enums.ProtectionType;

import java.util.List;

public record CreateOrderResult(
        PackagingType packagingType,
        ProtectionType protectionType,
        boolean hasMoistureAbsorbingBalls,
        double totalCost,
        List<String> details
) {
}
