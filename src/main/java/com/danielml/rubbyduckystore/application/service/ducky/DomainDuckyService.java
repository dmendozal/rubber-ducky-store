package com.danielml.rubbyduckystore.application.service.ducky;

import com.danielml.rubbyduckystore.domain.models.Ducky;
import com.danielml.rubbyduckystore.domain.ports.IDuckyRepository;
import com.danielml.rubbyduckystore.infrastructure.exceptions.ResourceNotFoundException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class DomainDuckyService implements IDuckyService {
    private final IDuckyRepository duckyRepository;

    public DomainDuckyService(IDuckyRepository duckyRepository) {
        this.duckyRepository = duckyRepository;
    }

    @Override
    public Iterable<Ducky> getDuckies() {
        List<Ducky> duckies = (List<Ducky>) this.duckyRepository.getDuckies();
        Collections.sort(duckies, new Comparator<Ducky>() {
            @Override
            public int compare(Ducky o1, Ducky o2) {
                return Integer.compare(o2.getQuantity(), o1.getQuantity());
            }
        });

        return duckies.stream().filter(ducky -> !ducky.isDeleted()).collect(Collectors.toList());
    }

    @Override
    public Ducky getDuckyById(int id) {
        Ducky ducky = this.duckyRepository.getDuckyById(id);

        return ducky.isDeleted() ? null : ducky;
    }

    @Override
    public void updateDucky(int id, int quantity, double price) {
        Ducky ducky = this.duckyRepository.getDuckyById(id);
        ducky.updateDucky(quantity, price);
        this.duckyRepository.updateDucky(ducky);
    }

    @Override
    public Ducky saveDucky(Ducky ducky) {
        Iterable<Ducky> duckies = this.duckyRepository.getDuckies();
        Ducky duckySelected = null;

        for (Ducky item : duckies){
            if (item.getPrice() == ducky.getPrice()
                    && item.getColor() == ducky.getColor()
                    && item.getSize() == ducky.getSize()){
                duckySelected = item;
            }
        }

        if (duckySelected != null){
           duckySelected.addNewDucky(ducky.getQuantity());
           this.duckyRepository.updateDucky(duckySelected);

           return duckySelected;
        }

        return this.duckyRepository.saveDucky(ducky);
    }

    @Override
    public void deleteDucky(int id) {
        Ducky ducky = this.duckyRepository.getDuckyById(id);
        ducky.remove();

        this.duckyRepository.updateDucky(ducky);
    }
}
