package com.example.demo.domain.entities;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "reports")
public class Report extends BaseEntity {

    private User reported;
    private User reportedBy;
    private String complaint;

    public Report() {
    }

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "reported_user", referencedColumnName = "id")
    public User getReported() {
        return reported;
    }

    public void setReported(User reported) {
        this.reported = reported;
    }


    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "reported_by", referencedColumnName = "id")
    public User getReportedBy() {
        return reportedBy;
    }

    public void setReportedBy(User reportedBy) {
        this.reportedBy = reportedBy;
    }

    @Column(name = "complaint", columnDefinition = "text")
    public String getComplaint() {
        return complaint;
    }

    public void setComplaint(String complaint) {
        this.complaint = complaint;
    }
}
