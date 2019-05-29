package bupt.dawsonlee1790.sop.dto;

import java.util.Date;

public class MakePlanDTO {

    private String planName;
    private long sopId;
    private long number;
    private Date startDate;
    private Date endDate;
    private String responsible;

    public MakePlanDTO() {

    }

    public MakePlanDTO(String planName,long sopId, long number, Date startDate, Date endDate, String responsible) {
        this.endDate = endDate;
        this.number = number;
        this.planName = planName;
        this.responsible = responsible;
        this.sopId = sopId;
        this.startDate = startDate;
    }

    public String getResponsible() {
        return responsible;
    }

    public void setResponsible(String responsible) {
        this.responsible = responsible;
    }

    public String getPlanName() {
        return planName;
    }

    public void setPlanName(String planName) {
        this.planName = planName;
    }

    public long getSopId() {
        return sopId;
    }

    public void setSopId(long sopId) {
        this.sopId = sopId;
    }

    public long getNumber() {
        return number;
    }

    public void setNumber(long number) {
        this.number = number;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
