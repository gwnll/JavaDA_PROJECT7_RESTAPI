package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.domain.User;
import com.nnk.springboot.services.CurveService;
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
public class CurveController {

    @Autowired
    CurveService curveService;

    @RequestMapping("/curvePoint/list")
    public String home(Model model, Principal principal, @AuthenticationPrincipal User user) {
        model.addAttribute("username", PrincipalUtils.getUsername(principal, user));
        model.addAttribute("curvePoints", curveService.getCurvePoints());
        return "curvePoint/list";
    }

    @GetMapping("/curvePoint/add")
    public ModelAndView addBidForm(CurvePoint bid) {
        return new ModelAndView("curvePoint/add", "curvePoint", new CurvePoint());
    }

    @PostMapping("/curvePoint/validate")
    public String validate(@Valid CurvePoint curvePoint, BindingResult result, Model model) {
        log.info("Received request POST /curvePoint/validate with parameter {}", curvePoint);
        if (!result.hasErrors()) {
            curveService.addCurvePoint(curvePoint);
            model.addAttribute("curvePoints", curveService.getCurvePoints());
            return "curvePoint/list";
        }
        return "curvePoint/add";
    }

    @GetMapping("/curvePoint/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        log.info("Received request GET /curvePoint/update with path variable {}", id);
        CurvePoint curvePoint = curveService.getCurvePointById(id).orElseThrow(() -> new IllegalArgumentException("Invalid curve point Id:" + id));
        model.addAttribute("curvePoint", curvePoint);
        return "curvePoint/update";
    }

    @PostMapping("/curvePoint/update/{id}")
    public String updateBid(@PathVariable("id") Integer id, @Valid CurvePoint curvePoint,
                            BindingResult result, Model model) {
        log.info("Received request POST /curvePoint/update with path variable {} and parameter {}", id, curvePoint);
        if (result.hasErrors()) {
            return "curvePoint/update";
        }
        curveService.update(curvePoint);
        model.addAttribute("curvePoints", curveService.getCurvePoints());
        return "redirect:/curvePoint/list";
    }

    @GetMapping("/curvePoint/delete/{id}")
    public String deleteBid(@PathVariable("id") Integer id, Model model) {
        log.info("Received request GET /curvePoint/delete with path variable {}", id);
        curveService.deleteById(id);
        return "redirect:/curvePoint/list";
    }
}
