package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.services.RuleNameService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
public class RuleNameController {

    @Autowired
    RuleNameService ruleNameService;

    @RequestMapping("/ruleName/list")
    public String home(Model model, Principal principal) {
        model.addAttribute("username", PrincipalUtils.getUsername(principal));
        model.addAttribute("ruleNames", ruleNameService.getRuleNames());
        return "ruleName/list";
    }

    @GetMapping("/ruleName/add")
    public ModelAndView addRuleForm(RuleName ruleName) {
        return new ModelAndView("ruleName/add", "ruleName", new RuleName());
    }

    @PostMapping("/ruleName/validate")
    public String validate(@Valid RuleName ruleName, BindingResult result, Model model) {
        log.info("Received request POST /rating/validate with parameter {}", ruleName);
        if (!result.hasErrors()) {
            ruleNameService.addRuleName(ruleName);
            model.addAttribute("ruleNames", ruleNameService.getRuleNames());
            return "ruleName/list";
        }
        return "ruleName/add";
    }

    @GetMapping("/ruleName/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        log.info("Received request GET /ruleName/update with path variable {}", id);
        RuleName ruleName = ruleNameService.getRuleNameById(id).orElseThrow(() -> new IllegalArgumentException("Invalid rule Name Id:" + id));
        model.addAttribute("ruleName", ruleName);
        return "ruleName/update";
    }

    @PostMapping("/ruleName/update/{id}")
    public String updateRuleName(@PathVariable("id") Integer id, @Valid RuleName ruleName,
                                 BindingResult result, Model model) {
        log.info("Received request POST /ruleName/update with path variable {} and parameter {}", id, ruleName);
        if (result.hasErrors()) {
            return "ruleName/update";
        }
        ruleNameService.update(ruleName);
        return "redirect:/ruleName/list";
    }

    @GetMapping("/ruleName/delete/{id}")
    public String deleteRuleName(@PathVariable("id") Integer id, Model model) {
        log.info("Received request GET /ruleName/delete with path variable {}", id);
        ruleNameService.deleteById(id);
        return "redirect:/ruleName/list";
    }
}
