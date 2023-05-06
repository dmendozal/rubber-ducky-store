package com.danielml.rubbyduckystore.domain.ports;

import com.danielml.rubbyduckystore.domain.models.Ducky;

public interface IDuckyRepository {
    Iterable<Ducky> getDuckies();
    Ducky getDuckyById(int id);
    Ducky saveDucky(Ducky ducky);
    void updateDucky(Ducky ducky);
}
