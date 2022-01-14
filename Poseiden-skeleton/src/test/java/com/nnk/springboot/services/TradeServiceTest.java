package com.nnk.springboot.services;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.repositories.TradeRepository;
import org.apache.commons.collections4.IterableUtils;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

@SpringBootTest
@ExtendWith({SpringExtension.class})
public class TradeServiceTest {

    @Autowired
    TradeService tradeService;

    @Autowired
    TradeRepository tradeRepository;

    @Test
    public void getTradeTest() {
        Trade tradeTest = new Trade("Trade Account", "Type", 0.0);
        tradeRepository.save(tradeTest);
        List<Trade> TradeList = IterableUtils.toList(tradeService.getTrades());
        Assert.assertTrue(TradeList.size() > 0);
    }

    @Test
    public void getTradeByIdTest() {
        Trade tradeTest = new Trade("Trade Account", "Type", 5.0);
        tradeTest.setId(3);
        tradeService.addTrade(tradeTest);
        Assert.assertNotNull(tradeService.getTradeById(3));
    }

    @Test
    public void addTradeTest() {
        Trade tradeTest = new Trade("Trade Account", "Type", 10.0);
        List<Trade> tradeBefore = IterableUtils.toList(tradeRepository.findAll());
        tradeService.addTrade(tradeTest);
        List<Trade> tradeAfter = IterableUtils.toList(tradeRepository.findAll());
        Assert.assertTrue(tradeAfter.size() > tradeBefore.size());
    }

    @Test
    public void updateTest() {
        Trade tradeTest = tradeService.getTradeById(80).orElseThrow(IllegalArgumentException::new);
        tradeTest.setAccount("New Account");
        tradeService.update(tradeTest);
        Trade tradeUpdated = tradeService.getTradeById(80).orElseThrow(IllegalArgumentException::new);
        Assert.assertEquals(tradeTest, tradeUpdated);
    }

    @Test
    public void deleteByIdTest() {
        tradeService.deleteById(90);
        Optional<Trade> test = tradeService.getTradeById(90);
        Assert.assertFalse(test.isPresent());
    }

}
