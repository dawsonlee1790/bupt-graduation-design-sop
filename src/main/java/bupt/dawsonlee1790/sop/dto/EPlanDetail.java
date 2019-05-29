package bupt.dawsonlee1790.sop.dto;

import java.util.Date;

public class EPlanDetail {
    private long id;
    private String sopName;
    private String eContent;
    private String reportPerson;
    private Date reportTime;
    private String planName;

    public String getPlanName() {
        return planName;
    }

    public void setPlanName(String planName) {
        this.planName = planName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSopName() {
        return sopName;
    }

    public void setSopName(String sopName) {
        this.sopName = sopName;
    }

    public String geteContent() {
        return eContent;
    }

    public void seteContent(String eContent) {
        this.eContent = eContent;
    }

    public String getReportPerson() {
        return reportPerson;
    }

    public void setReportPerson(String reportPerson) {
        this.reportPerson = reportPerson;
    }

    public Date getReportTime() {
        return reportTime;
    }

    public void setReportTime(Date reportTime) {
        this.reportTime = reportTime;
    }
}
