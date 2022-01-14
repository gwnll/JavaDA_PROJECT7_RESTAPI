package com.nnk.springboot.repositories;

import com.nnk.springboot.domain.Trade;
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
public class TradeRepositoryTest {

	@Autowired
	private TradeRepository tradeRepository;

	@Test
	public void tradeTest() {
		Trade trade = new Trade("Trade Account", "Type", 10.0);

		// Save
		trade = tradeRepository.save(trade);
		Assert.assertNotNull(trade.getId());
		Assert.assertTrue(trade.getAccount().equals("Trade Account"));

		// Update
		trade.setAccount("Trade Account Update");
		trade = tradeRepository.save(trade);
		Assert.assertTrue(trade.getAccount().equals("Trade Account Update"));

		// Find
		List<Trade> listResult = IterableUtils.toList(tradeRepository.findAll());
		Assert.assertTrue(listResult.size() > 0);

		// Delete
		Integer id = trade.getId();
		tradeRepository.delete(trade);
		Optional<Trade> tradeList = tradeRepository.findById(id);
		Assert.assertFalse(tradeList.isPresent());
	}
}
