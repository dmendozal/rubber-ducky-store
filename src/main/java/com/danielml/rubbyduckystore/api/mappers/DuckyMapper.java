package com.danielml.rubbyduckystore.api.mappers;

import com.danielml.rubbyduckystore.api.models.duckies.CreateDuckyRequest;
import com.danielml.rubbyduckystore.api.models.duckies.CreateDuckyResult;
import com.danielml.rubbyduckystore.api.models.duckies.GetDuckyResult;
import com.danielml.rubbyduckystore.api.models.duckies.ListDuckyResult;
import com.danielml.rubbyduckystore.domain.models.Ducky;
import com.danielml.rubbyduckystore.infrastructure.persistence.mappings.DuckyEntity;

import java.util.ArrayList;
import java.util.List;

public class DuckyMapper {

    public static Ducky toDucky(CreateDuckyRequest duckyRequest) {
        Ducky ducky = new Ducky();
        ducky.setColor(duckyRequest.color());
        ducky.setSize(duckyRequest.size());
        ducky.setPrice(duckyRequest.price());
        ducky.setQuantity(duckyRequest.quantity());

        return ducky;
    }

    public static Ducky toDucky(DuckyEntity duckyEntity) {
        return new Ducky(duckyEntity.getId(),
                duckyEntity.getColor(),
                duckyEntity.getSize(),
                duckyEntity.getPrice(),
                duckyEntity.getQuantity(),
                duckyEntity.isDeleted());
    }

    public static Iterable<Ducky> toDuckies(Iterable<DuckyEntity> duckyEntities) {
        List<Ducky> duckies = new ArrayList<>();

        for (DuckyEntity duckyEntity : duckyEntities) {
            duckies.add(toDucky(duckyEntity));
        }

        return duckies;
    }

    public static Iterable<ListDuckyResult> toDuckiesResult(Iterable<Ducky> duckies) {
        List<ListDuckyResult> duckiesResult = new ArrayList<>();

        for (Ducky ducky : duckies) {
            duckiesResult.add(new ListDuckyResult(
                    ducky.getId(),
                    ducky.getColor(),
                    ducky.getSize(),
                    ducky.getPrice(),
                    ducky.getQuantity(),
                    ducky.isDeleted()));
        }

        return duckiesResult;
    }

    public static DuckyEntity toDuckyEntity(Ducky ducky) {
        return new DuckyEntity(ducky.getId(),
                ducky.getColor(),
                ducky.getSize(),
                ducky.getPrice(),
                ducky.getQuantity(),
                ducky.isDeleted());
    }

    public static CreateDuckyResult toCreateDuckyResult(Ducky ducky) {
        return new CreateDuckyResult(ducky.getId(),
                ducky.getColor(),
                ducky.getSize(),
                ducky.getQuantity(),
                ducky.getPrice());
    }

    public static GetDuckyResult toGetDuckyResult(Ducky ducky) {
        return new GetDuckyResult(ducky.getId(),
                ducky.getColor(),
                ducky.getSize(),
                ducky.getQuantity(),
                ducky.getPrice(),
                ducky.isDeleted());
    }
}
