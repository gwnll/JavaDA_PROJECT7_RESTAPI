package com.nnk.springboot.services;

import com.nnk.springboot.Application;
import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.repositories.CurveRepository;
import lombok.SneakyThrows;
import org.apache.commons.collections4.IterableUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Primary;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import sun.awt.geom.Curve;

import java.util.List;
import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class CurveServiceTest {

    @Mock
    CurvePoint curvePoint;

    @Autowired
    private WebApplicationContext webApplicationContext;

    protected MockMvc mockMvc;

    @Autowired
    CurveService curveService;

    @Autowired
    CurveRepository curveRepository;

    @BeforeEach
    void setUp() {
        curvePoint = Mockito.mock(CurvePoint.class);
        curvePoint.setId(77);
        curvePoint.setCurveId(66);
        curvePoint.setTerm(55);
        curvePoint.setValue(44);
        curveRepository.save(curvePoint);
    }

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
        CurvePoint curvePointTest = curveService.getCurvePointById(5).orElseThrow(() -> new IllegalArgumentException());
        curvePointTest.setTerm(55);
        curveService.update(curvePointTest);
        CurvePoint curvePointUpdated = curveService.getCurvePointById(5).orElseThrow(() -> new IllegalArgumentException());
        Assert.assertEquals(curvePointTest, curvePointUpdated);
    }

    @Test
    public void deleteByIdTest() {
        Integer curveId = curvePoint.getId();
        curveService.deleteById(curveId);
        Optional <CurvePoint> test = curveService.getCurvePointById(77);
        Assert.assertFalse(test.isPresent());
    }

}
