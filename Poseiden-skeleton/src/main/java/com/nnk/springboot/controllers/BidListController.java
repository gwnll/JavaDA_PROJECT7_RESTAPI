package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.domain.User;
import com.nnk.springboot.services.BidListService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.security.Principal;

@Slf4j
@Controller
public class BidListController {

    @Autowired
    BidListService bidListService;

    @RequestMapping("/bidList/list")
    public String home(Model model, Principal principal, @AuthenticationPrincipal User user) {
        model.addAttribute("username", PrincipalUtils.getUsername(principal, user));
        model.addAttribute("bidLists", bidListService.getBidLists());
        return "bidList/list";
    }

    @GetMapping("/bidList/add")
    public ModelAndView addBidForm() {
        return new ModelAndView("bidList/add", "bidList", new BidList());
    }

    @PostMapping("/bidList/validate")
    public String validate(@Valid BidList bid, BindingResult result, Model model) {
        log.info("Received request POST /bidList/validate with body {}", bid);
        if (!result.hasErrors()) {
            bidListService.addBid(bid);
            Iterable<BidList> bidLists = bidListService.getBidLists();
            model.addAttribute("bidLists", bidLists);
            return "bidList/list";
        } else {
            return "bidList/add";
        }
    }

    @GetMapping("/bidList/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        log.info("Received request GET /bidList/update with path variable {}", id);
        BidList bidList = bidListService.getBidById(id).orElseThrow(() -> new IllegalArgumentException("Invalid bidList Id:" + id));
        model.addAttribute("bidList", bidList);
        return "bidList/update";
    }

    @PostMapping("/bidList/update/{id}")
    public String updateBid(@PathVariable("id") Integer id, @Valid BidList bidList, BindingResult result, Model
            model) {
        log.info("Received request POST /bidList/update with path variable {} and parameter {}", id, bidList);
        if (result.hasErrors()) {
            return "bidList/update";
        }
        bidListService.update(bidList);
        model.addAttribute("bidLists", bidListService.getBidLists());
        return "redirect:/bidList/list";
    }

    @GetMapping("/bidList/delete/{id}")
    public String deleteBid(@PathVariable("id") Integer id, Model model) {
        log.info("Received request GET /bidList/delete with path variable {}", id);
        bidListService.deleteById(id);
        return "redirect:/bidList/list";
    }
}
