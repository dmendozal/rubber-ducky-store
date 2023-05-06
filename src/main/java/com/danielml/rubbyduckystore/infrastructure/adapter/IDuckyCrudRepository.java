package com.danielml.rubbyduckystore.infrastructure.adapter;

import com.danielml.rubbyduckystore.infrastructure.persistence.mappings.DuckyEntity;
import org.springframework.data.repository.CrudRepository;

public interface IDuckyCrudRepository extends CrudRepository<DuckyEntity, Integer> {
}
