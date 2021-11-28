package com.nnk.springboot.repositories;

import com.nnk.springboot.domain.CurvePoint;
import org.springframework.data.repository.CrudRepository;


public interface CurveRepository extends CrudRepository<CurvePoint, Integer> {

}
