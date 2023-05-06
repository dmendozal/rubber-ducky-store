package com.danielml.rubbyduckystore.infrastructure.adapter;

import com.danielml.rubbyduckystore.infrastructure.persistence.mappings.OrderEntity;
import org.springframework.data.repository.CrudRepository;

public interface IOrderCrudRepository extends CrudRepository<OrderEntity, Integer> {
}
