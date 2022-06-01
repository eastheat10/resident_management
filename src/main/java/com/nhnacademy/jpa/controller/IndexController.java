package com.nhnacademy.jpa.controller;

import com.nhnacademy.jpa.service.ResidentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
public class IndexController {

    private final ResidentService residentService;

    @GetMapping
    public String index(Pageable pageable) {
        return "redirect:/list?size=5&page=0";
    }

    @GetMapping("/list")
    public ModelAndView list(Pageable pageable) {

        ModelAndView mav = new ModelAndView("index");

        mav.addObject("list", residentService.findList(pageable));

        return mav;
    }

    @GetMapping("/resident/{id}")
    public ModelAndView delete(@PathVariable("id") Long id, RedirectAttributes redirect) {

        ModelAndView mav = new ModelAndView("redirect:/list?size=5&page=0");

        if (!residentService.delete(id)) {
            redirect.addFlashAttribute("cantDelete", true);
        }

        return mav;
    }
}
