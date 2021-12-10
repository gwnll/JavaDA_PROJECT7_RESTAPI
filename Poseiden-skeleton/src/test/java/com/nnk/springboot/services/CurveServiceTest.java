package com.nnk.springboot.services;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.repositories.CurveRepository;
import org.apache.commons.collections4.IterableUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CurveServiceTest {

    @Autowired
    CurveService curveService;

    @Autowired
    CurveRepository curveRepository;

    @Test
    public void getCurvePointsTest() {
        CurvePoint curvePointTest = new CurvePoint(10, 10d, 30d);
        curveRepository.save(curvePointTest);
        List<CurvePoint> curvePointList = IterableUtils.toList(curveService.getCurvePoints());
        Assert.assertTrue(curvePointList.size() > 0);
    }

    @Test
    public void getCurvePointByIdTest() {
        CurvePoint curvePointTest = new CurvePoint(10, 10d, 30d);
        curvePointTest.setId(3);
        curveService.addCurvePoint(curvePointTest);
        Assert.assertNotNull(curveService.getCurvePointById(3));
    }

    @Test
    public void addCurvePointTest() {
        CurvePoint curvePointTest = new CurvePoint(10, 10d, 30d);
        List<CurvePoint> curvePointBefore = IterableUtils.toList(curveRepository.findAll());
        curveService.addCurvePoint(curvePointTest);
        List<CurvePoint> curvePointAfter = IterableUtils.toList(curveRepository.findAll());
        Assert.assertTrue(curvePointAfter.size() > curvePointBefore.size());
    }

    @Test
    public void updateTest() {
        CurvePoint curvePointTest = curveService.getCurvePointById(5).orElseThrow(IllegalArgumentException::new);
        curvePointTest.setTerm(55);
        curveService.update(curvePointTest);
        CurvePoint curvePointUpdated = curveService.getCurvePointById(5).orElseThrow(IllegalArgumentException::new);
        Assert.assertEquals(curvePointTest, curvePointUpdated);
    }

    @Test
    public void deleteByIdTest() {
        curveService.deleteById(6);
        Optional <CurvePoint> test = curveService.getCurvePointById(6);
        Assert.assertFalse(test.isPresent());
    }

}
