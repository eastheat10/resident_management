//package com.nhnacademy.jpa.repository.impl;
//
//import com.nhnacademy.jpa.dto.request.birthdeathreport.BirthDeathReportRequest;
//import com.nhnacademy.jpa.entity.QBirthDeathReportResident;
//import com.nhnacademy.jpa.entity.QResident;
//import com.nhnacademy.jpa.repository.BirthDeathReportCustom;
//import com.nhnacademy.jpa.entity.dto.BirthReportInfo;
//import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
//
//public class BirthDeathReportImpl extends QuerydslRepositorySupport
//    implements BirthDeathReportCustom {
//
//    public BirthDeathReportImpl() {
//        super(BirthDeathReportRequest.class);
//    }
//
//    @Override
//    public BirthReportInfo findBirthInfoByResidentId(Long id) {
//
//        QBirthDeathReportResident b = QBirthDeathReportResident.birthDeathReportResident;
//        QResident r = QResident.resident;
//        QResident reportResident = new QResident("reportResident");
//
//        return from(b)
//            .innerJoin(b.resident, r)
//            .innerJoin(b.reportResident, reportResident)
//            .where(b.resident.id.eq(id))
//            .select(new QBirthReportInfo(
//                b.birthDeathReportDate,
//                b.birthReportQualificationsCode,
//                b.phoneNumber,
//                b.emailAddress,
//                r.birthDate,
//                r.name,
//                r.genderCode,
//                r.birthPlaceCode,
//                r.registrationBaseAddress,
//                reportResident.name,
//                reportResident.rrn)
//            )
//            .fetchOne();
//    }
//}
