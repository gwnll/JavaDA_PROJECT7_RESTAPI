package com.nnk.springboot.services;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.repositories.TradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TradeService {

    @Autowired
    private TradeRepository tradeRepository;

    public Iterable<Trade> getTrades() {
        return tradeRepository.findAll();
    }

    public Optional<Trade> getTradeById(Integer id) {
        return tradeRepository.findById(id);
    }

    public Trade addTrade(Trade trade) {
        return tradeRepository.save(trade);
    }

    public void deleteById(Integer id) {
        tradeRepository.deleteById(id);
    }

    public Trade update(Trade trade) {
        tradeRepository.save(trade);
        return trade;
    }
}
