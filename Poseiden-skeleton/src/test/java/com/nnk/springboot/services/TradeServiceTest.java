package com.nnk.springboot.services;

import com.nnk.springboot.ApplicationTest;
import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.repositories.RuleNameRepository;
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
public class TradeServiceTest {

    @Autowired
    RuleNameService ruleNameService;

    @Autowired
    RuleNameRepository ruleNameRepository;

    @Test
    public void getRuleNamesTest() {
        RuleName ruleNameTest = new RuleName("name", "description", "json", "template", "SQL", "SQL");
        ruleNameRepository.save(ruleNameTest);
        List<RuleName> ruleNameList = IterableUtils.toList(ruleNameService.getRuleNames());
        Assert.assertTrue(ruleNameList.size() > 0);
    }

    @Test
    public void getRuleNameByIdTest() {
        RuleName ruleNameTest = new RuleName("name", "description", "json", "template", "SQL", "SQL");
        ruleNameTest.setId(3);
        ruleNameService.addRuleName(ruleNameTest);
        Assert.assertNotNull(ruleNameService.getRuleNameById(3));
    }

    @Test
    public void addRuleNameTest() {
        RuleName ruleNameTest = new RuleName("name", "description", "json", "template", "SQL", "SQL");
        List<RuleName> ruleNameBefore = IterableUtils.toList(ruleNameRepository.findAll());
        ruleNameService.addRuleName(ruleNameTest);
        List<RuleName> ruleNameAfter = IterableUtils.toList(ruleNameRepository.findAll());
        Assert.assertTrue(ruleNameAfter.size() > ruleNameBefore.size());
    }

    @Test
    public void updateTest() {
        RuleName ruleNameTest = ruleNameService.getRuleNameById(60).orElseThrow(IllegalArgumentException::new);
        ruleNameTest.setDescription("New Description");
        ruleNameService.update(ruleNameTest);
        RuleName ruleNameUpdated = ruleNameService.getRuleNameById(60).orElseThrow(IllegalArgumentException::new);
        Assert.assertEquals(ruleNameTest, ruleNameUpdated);
    }

    @Test
    public void deleteByIdTest() {
        ruleNameService.deleteById(70);
        Optional<RuleName> test = ruleNameService.getRuleNameById(70);
        Assert.assertFalse(test.isPresent());
    }

}
