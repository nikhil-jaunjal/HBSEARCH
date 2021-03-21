package com.nikhil.repositories;

import org.springframework.data.repository.CrudRepository;
import com.nikhil.model.ManagerEntity;

public interface ManagerRepository extends CrudRepository<ManagerEntity, Integer>{

}
