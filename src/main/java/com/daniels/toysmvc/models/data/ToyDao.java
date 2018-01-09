package com.daniels.toysmvc.models.data;

import com.daniels.toysmvc.models.Toy;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface ToyDao extends CrudRepository<Toy, Integer> {
}
