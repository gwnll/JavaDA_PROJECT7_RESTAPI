package com.nnk.springboot.repositories;

import com.nnk.springboot.domain.BidList;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;


public interface BidListRepository extends CrudRepository<BidList, Integer> {

    /**
     * Override findById
     * Replace Id by BidListId
     * @param bidListId
     */
    @Query(value = "SELECT b FROM BidList b " +
            "WHERE b.bidListId = :bidListId ")
    Optional<BidList> findById(Integer bidListId);

}
