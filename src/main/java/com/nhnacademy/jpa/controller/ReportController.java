package com.nhnacademy.jpa.controller;

import com.nhnacademy.jpa.dto.response.birthdeathreport.BirthReportInfoResponse;
import com.nhnacademy.jpa.dto.response.birthdeathreport.ParentInfoResponse;
import com.nhnacademy.jpa.service.ReportService;
import java.util.List;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/report")
@RequiredArgsConstructor
public class ReportController {

    private final ReportService reportService;

    @GetMapping("/birth/{id}")
    public ModelAndView birthReport(@PathVariable("id") Long id) {

        ModelAndView mav = new ModelAndView("report/birth-report");

        List<ParentInfoResponse> parentInfoResponses = reportService.parentInfo(id);
        ParentInfoResponse father = null;
        ParentInfoResponse mother = null;

        for (ParentInfoResponse resp : parentInfoResponses) {
            if (Objects.equals(resp.getRelationship(), "부")) {
                father = resp;
            } else if (Objects.equals(resp.getRelationship(), "모")) {
                mother = resp;
            }
        }

        mav.addObject("info", reportService.birthReportInfo(id));
        mav.addObject("fa", father);
        mav.addObject("mo", mother);

        return mav;
    }
}
