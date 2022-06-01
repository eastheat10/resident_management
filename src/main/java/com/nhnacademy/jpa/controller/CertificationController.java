package com.nhnacademy.jpa.controller;

import com.nhnacademy.jpa.dto.response.certification.CertificationInfoResponse;
import com.nhnacademy.jpa.dto.response.certification.CompositionResidentResponse;
import com.nhnacademy.jpa.dto.response.certification.FamilyRelationshipInfoResponse;
import com.nhnacademy.jpa.dto.response.certification.HouseholdRegistrationInfoResponse;
import com.nhnacademy.jpa.dto.response.familyrelationship.FamilyRelationshipResidentResponse;
import com.nhnacademy.jpa.dto.response.householdmovementaddress.HouseholdMovementAddressResponse;
import com.nhnacademy.jpa.service.CertificationService;
import java.util.List;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

        FamilyRelationshipInfoResponse info =
            certificationService.getRelationshipCertificationInfo(residentSerialNumber);

        List<FamilyRelationshipResidentResponse> familyRelationship =
            certificationService.findFamilyRelationship(residentSerialNumber);

        ModelAndView mav = new ModelAndView("certification/family-relationship");

        mav.addObject("info", info);
        mav.addObject("list", familyRelationship);
        return mav;
    }

    @GetMapping("/household-registration/{residentSerialNumber}")
    public ModelAndView householdRegistration(
        @PathVariable("residentSerialNumber") Long residentSerialNumber) {

        HouseholdRegistrationInfoResponse info =
            certificationService.getHouseholdRegistrationResponse(residentSerialNumber);

        List<HouseholdMovementAddressResponse> movementList =
            certificationService.getMovementList(residentSerialNumber);

        List<CompositionResidentResponse> compositions =
            certificationService.getHouseholdComposition(residentSerialNumber);

        CompositionResidentResponse person =
            compositions.stream()
                        .filter(c -> Objects.equals(c.getRelationshipCode(), "본인"))
                        .findFirst()
                        .orElseThrow(IllegalArgumentException::new);
        ModelAndView mav = new ModelAndView("certification/household-registration");

        mav.addObject("info", info);
        mav.addObject("movementList", movementList);
        mav.addObject("person", person);
        mav.addObject("compositions", compositions);

        return mav;
    }

    @GetMapping("/list/{id}")
    public ModelAndView certificationList(@PathVariable("id") Long id, Pageable pageable) {

        ModelAndView mav = new ModelAndView("certification/list");

        mav.addObject("list", certificationService.findCertificationIssueList(id, pageable));
        return mav;
    }
}
