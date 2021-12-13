package com.nnk.springboot.controllers;

import com.nnk.springboot.ApplicationTest;
import com.nnk.springboot.domain.BidList;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@ActiveProfiles("test")
@AutoConfigureMockMvc(secure = false)
@SpringBootTest(classes = {ApplicationTest.class})
public class BidListControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void validateTest() throws Exception {

        BidList bidListTest = new BidList("Account Test", "Type", 15);

        mockMvc.perform(post("/bidList/validate")
                .param("account", bidListTest.getAccount())
                .param("type", bidListTest.getType())
                .param("bidQuantity", String.valueOf(bidListTest.getBidQuantity())))
                .andDo(print())
                .andExpect(view().name("redirect:/bidList/list"))
                .andExpect(status().is3xxRedirection())
                .andReturn().getResponse().containsHeader("Account Test");
    }

}
