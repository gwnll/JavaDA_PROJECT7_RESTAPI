package com.nnk.springboot.services;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.repositories.RatingRepository;
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
@SpringBootTest
public class RatingServiceTest {

    @Autowired
    RatingService ratingService;

    @Autowired
    RatingRepository ratingRepository;

    @Test
    public void getratingsTest() {
        Rating ratingTest = new Rating("moodysRating", "sandPRating", "fitchRating", 65);
        ratingRepository.save(ratingTest);
        List<Rating> ratingList = IterableUtils.toList(ratingService.getRatings());
        Assert.assertTrue(ratingList.size() > 0);
    }

    @Test
    public void getratingByIdTest() {
        Rating ratingTest = new Rating("moodysRating", "sandPRating", "fitchRating", 65);
        ratingTest.setId(3);
        ratingService.addRating(ratingTest);
        Assert.assertNotNull(ratingService.getRatingById(3));
    }

    @Test
    public void addratingTest() {
        Rating ratingTest = new Rating("moodysRating", "sandPRating", "fitchRating", 65);
        List<Rating> ratingBefore = IterableUtils.toList(ratingRepository.findAll());
        ratingService.addRating(ratingTest);
        List<Rating> ratingAfter = IterableUtils.toList(ratingRepository.findAll());
        Assert.assertTrue(ratingAfter.size() > ratingBefore.size());
    }

    @Test
    public void updateTest() {
        Rating ratingTest = ratingService.getRatingById(40).orElseThrow(IllegalArgumentException::new);
        ratingTest.setFitchRating("new fitchRating");
        ratingService.update(ratingTest);
        Rating ratingUpdated = ratingService.getRatingById(40).orElseThrow(IllegalArgumentException::new);
        Assert.assertEquals(ratingTest, ratingUpdated);
    }

    @Test
    public void deleteByIdTest() {
        ratingService.deleteById(50);
        Optional<Rating> test = ratingService.getRatingById(50);
        Assert.assertFalse(test.isPresent());
    }
    
}
