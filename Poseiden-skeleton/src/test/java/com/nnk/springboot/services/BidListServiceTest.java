package com.nnk.springboot.services;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.repositories.BidListRepository;
import org.apache.commons.collections4.IterableUtils;
import org.checkerframework.checker.units.qual.A;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BidListServiceTest {

    @Autowired
    private BidListRepository bidListRepository;

    @Autowired
    private BidListService bidListService;



    @Test
    public void getBidListsTest() {
        BidList bidListTest = new BidList("Account Test", "Type Test", 10d);
        List<BidList> bidList = IterableUtils.toList(bidListService.getBidLists());
        Assert.assertTrue(bidList.size() > 0);
    }

    @Test
    public void addBidTest() {
        BidList bidListTest = new BidList("Account Test", "Type Test", 10d);
        List<BidList> bidListBefore = IterableUtils.toList(bidListRepository.findAll());
        bidListService.addBid(bidListTest);
        List<BidList> bidListAfter = IterableUtils.toList(bidListRepository.findAll());
        Assert.assertTrue(bidListAfter.size() > bidListBefore.size());
    }

    @Test
    public void deleteByIdTest() {
        BidList bidListTest = new BidList("Account Test", "Type Test", 10d);
        bidListTest.setBidListId(3);
        Integer bidListId = bidListTest.getBidListId();
        bidListService.deleteById(bidListId);
        Assert.assertFalse(IterableUtils.toList(bidListService.getBidLists()).size() > 0);
    }
}
