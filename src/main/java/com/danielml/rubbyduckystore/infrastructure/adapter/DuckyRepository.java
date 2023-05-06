package com.danielml.rubbyduckystore.infrastructure.adapter;

import com.danielml.rubbyduckystore.domain.models.Ducky;
import com.danielml.rubbyduckystore.domain.ports.IDuckyRepository;
import com.danielml.rubbyduckystore.infrastructure.persistence.mappings.DuckyEntity;
import com.danielml.rubbyduckystore.infrastructure.exceptions.ResourceNotFoundException;
import com.danielml.rubbyduckystore.api.mappers.DuckyMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Repository
@Slf4j
public class DuckyRepository implements IDuckyRepository {
    private final IDuckyCrudRepository duckyCrudRepository;

    public DuckyRepository(IDuckyCrudRepository duckyCrudRepository) {
        this.duckyCrudRepository = duckyCrudRepository;
    }

    @Override
    public Iterable<Ducky> getDuckies() {
        return DuckyMapper.toDuckies(this.duckyCrudRepository.findAll());
    }

    @Override
    public Ducky getDuckyById(int id) throws ResourceNotFoundException {
        DuckyEntity duckyEntity = this.duckyCrudRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("El recurso solicitado no fue encontrado.")
        );

        return DuckyMapper.toDucky(duckyEntity);
    }

    @Override
    public Ducky saveDucky(Ducky ducky) {
        DuckyEntity duckyEntity = DuckyMapper.toDuckyEntity(ducky);

        return DuckyMapper.toDucky(this.duckyCrudRepository.save(duckyEntity));
    }

    @Override
    public void updateDucky(Ducky ducky) {
        DuckyEntity duckyEntity = DuckyMapper.toDuckyEntity(ducky);

        this.duckyCrudRepository.save(duckyEntity);
    }
}
