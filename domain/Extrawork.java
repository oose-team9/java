package com.domain;

import java.sql.Date;
import java.sql.Timestamp;

public class Extrawork {
    private int id;
    private int applicant;
    private Timestamp workingDate;
    private int workingTimes;
    private int approved;
    private Timestamp approvedDate;
    private int approvedStatus;     // 0: 결재대기, 1: 승인완료, 2: 반려

    public Extrawork() {
    }

    public Extrawork(int applicant, Timestamp workingDate, int workingTimes, int approved, Timestamp approvedDate, int approvedStatus) {
        this.applicant = applicant;
        this.workingDate = workingDate;
        this.workingTimes = workingTimes;
        this.approved = approved;
        this.approvedDate = approvedDate;
        this.approvedStatus = approvedStatus;
    }

    public Extrawork(int id, int applicant, Timestamp workingDate, int workingTimes, int approved, Timestamp approvedDate, int approvedStatus) {
        this.id = id;
        this.applicant = applicant;
        this.workingDate = workingDate;
        this.workingTimes = workingTimes;
        this.approved = approved;
        this.approvedDate = approvedDate;
        this.approvedStatus = approvedStatus;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getApplicant() {
        return applicant;
    }

    public void setApplicant(int applicant) {
        this.applicant = applicant;
    }

    public Timestamp getWorkingDate() {
        return workingDate;
    }

    public void setWorkingDate(Timestamp workingDate) {
        this.workingDate = workingDate;
    }

    public int getWorkingTimes() {
        return workingTimes;
    }

    public void setWorkingTimes(int workingTimes) {
        this.workingTimes = workingTimes;
    }

    public int getApproved() {
        return approved;
    }

    public void setApproved(int approved) {
        this.approved = approved;
    }

    public Timestamp getApprovedDate() {
        return approvedDate;
    }

    public void setApprovedDate(Timestamp approvedDate) {
        this.approvedDate = approvedDate;
    }

    public int getApprovedStatus() {
        return approvedStatus;
    }

    public void setApprovedStatus(int approvedStatus) {
        this.approvedStatus = approvedStatus;
    }
}
