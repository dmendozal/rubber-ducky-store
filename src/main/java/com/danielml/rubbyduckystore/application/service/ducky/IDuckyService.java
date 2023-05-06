package com.danielml.rubbyduckystore.application.service.ducky;

import com.danielml.rubbyduckystore.domain.models.Ducky;

public interface IDuckyService {
    Iterable<Ducky> getDuckies();
    Ducky getDuckyById(int id);
    void updateDucky(int id, int quantity, double price);
    Ducky saveDucky(Ducky ducky);
    void deleteDucky(int id);
}
