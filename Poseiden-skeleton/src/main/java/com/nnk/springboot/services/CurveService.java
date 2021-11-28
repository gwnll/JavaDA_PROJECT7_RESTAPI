package com.nnk.springboot.services;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.repositories.CurveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CurveService {

    @Autowired
    private CurveRepository curveRepository;

    public Iterable<CurvePoint> getCurvePoints() {
        return curveRepository.findAll();
    }

    public Optional<CurvePoint> getCurvePointById(Integer id) {
        return curveRepository.findById(id);
    }

    public CurvePoint addCurvePoint(CurvePoint curvePoint) {
        return curveRepository.save(curvePoint);
    }

    public void deleteById(Integer id) {
        curveRepository.deleteById(id);
    }

    public CurvePoint update(CurvePoint curvePoint) {
        curveRepository.save(curvePoint);
        return curvePoint;
    }

}
