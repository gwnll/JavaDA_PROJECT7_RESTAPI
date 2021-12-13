package com.nnk.springboot.services;

import com.nnk.springboot.ApplicationTest;
import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.repositories.BidListRepository;
import org.apache.commons.collections4.IterableUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {ApplicationTest.class})
public class BidListServiceTest {

    @Autowired
    BidListService bidListService;

    @Autowired
    BidListRepository bidListRepository;

    @Test
    public void getbidListsTest() {
        BidList bidListTest = new BidList("Compte Lists", "Type", 15);
        bidListRepository.save(bidListTest);
        List<BidList> bidListList = IterableUtils.toList(bidListService.getBidLists());
        Assert.assertTrue(bidListList.size() > 0);
    }

    @Test
    public void getbidListByIdTest() {
        BidList bidListTest = new BidList("Compte ById", "Type", 15);
        bidListTest.setBidListId(3);
        bidListService.addBid(bidListTest);
        Assert.assertNotNull(bidListService.getBidById(3));
    }

    @Test
    public void addbidListTest() {
        BidList bidListTest = new BidList("Compte Add", "Type", 15);
        List<BidList> bidListBefore = IterableUtils.toList(bidListRepository.findAll());
        bidListService.addBid(bidListTest);
        List<BidList> bidListAfter = IterableUtils.toList(bidListRepository.findAll());
        Assert.assertTrue(bidListAfter.size() > bidListBefore.size());
    }

    @Test
    public void updateTest() {
        BidList bidListTest = bidListService.getBidById(20).orElseThrow(IllegalArgumentException::new);
        bidListTest.setBid(14.0);
        bidListService.update(bidListTest);
        BidList bidListUpdated = bidListService.getBidById(20).orElseThrow(IllegalArgumentException::new);
        Assert.assertEquals(bidListTest, bidListUpdated);
    }

    @Test
    public void deleteByIdTest() {
        bidListService.deleteById(30);
        Optional<BidList> test = bidListService.getBidById(30);
        Assert.assertFalse(test.isPresent());
    }
}
