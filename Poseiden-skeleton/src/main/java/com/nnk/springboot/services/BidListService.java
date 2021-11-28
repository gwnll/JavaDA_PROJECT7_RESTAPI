package com.nnk.springboot.services;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.repositories.BidListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BidListService {

    @Autowired
    private BidListRepository bidListRepository;

    public Iterable<BidList> getBidLists() {
        return bidListRepository.findAll();
    }

    public Optional<BidList> getBidById(Integer id) {
        return bidListRepository.findById(id);
    }

    public BidList addBid(BidList bid) {
        return bidListRepository.save(bid);
    }

    public void deleteById(Integer id) {
        bidListRepository.deleteById(id);
    }

    public BidList update(BidList bid) {
        bidListRepository.save(bid);
        return bid;
    }
}
