package bupt.dawsonlee1790.sop.dto;

import java.util.Date;

public class MakePlanDTO {

    private String planName;
    private long sopId;
    private long number;
    private Date startDate;
    private Date endDate;
    private long responsible;

    public long getResponsible() {
        return responsible;
    }

    public void setResponsible(long responsible) {
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
