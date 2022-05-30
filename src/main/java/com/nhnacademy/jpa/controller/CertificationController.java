package com.nhnacademy.jpa.controller;

import com.nhnacademy.jpa.dto.response.CertificationResponse;
import com.nhnacademy.jpa.dto.response.familyrelationship.FamilyRelationshipInfoResponse;
import com.nhnacademy.jpa.service.CertificationService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/certification")
@RequiredArgsConstructor
public class CertificationController {

    private final CertificationService certificationService;

    @GetMapping("/family-relationship/{residentSerialNumber}")
    public ModelAndView familyRelationship(
        @PathVariable("residentSerialNumber") Long residentSerialNumber) {

        CertificationResponse info =
            certificationService.getRelationshipCertificationInfo(residentSerialNumber);

        List<FamilyRelationshipInfoResponse> familyRelationship =
            certificationService.findFamilyRelationship(residentSerialNumber);

        ModelAndView mav = new ModelAndView("certification/family-relationship");

        mav.addObject("info", info);
        mav.addObject("list", familyRelationship);
        return mav;
    }
}
