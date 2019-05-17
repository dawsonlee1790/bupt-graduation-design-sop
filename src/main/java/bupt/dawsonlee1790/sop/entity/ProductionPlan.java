package bupt.dawsonlee1790.sop.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "production_plans")
@JsonIgnoreProperties(value = {"id", "startOrder"}, allowGetters = true)
public class ProductionPlan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotBlank
    private String name;
    @NotNull
    private long number;
    @NotBlank
    private String status;
    @NotNull
    @Temporal(TemporalType.DATE)
    private Date planningStartDate;
    @NotNull
    @Temporal(TemporalType.DATE)
    private Date planningEndDate;
    @NotNull
    private long responsible;
    @OneToOne(cascade = CascadeType.ALL)
    private ProductionOrder startOrder;
    @OneToOne(cascade = CascadeType.ALL)
    @JsonIgnore
    private Sop sop;

    // ======== getter setter ==========

    public Sop getSop() {
        return sop;
    }

    public void setSop(Sop sop) {
        this.sop = sop;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getNumber() {
        return number;
    }

    public void setNumber(long number) {
        this.number = number;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getPlanningStartDate() {
        return planningStartDate;
    }

    public void setPlanningStartDate(Date planningStartDate) {
        this.planningStartDate = planningStartDate;
    }

    public Date getPlanningEndDate() {
        return planningEndDate;
    }

    public void setPlanningEndDate(Date planningEndDate) {
        this.planningEndDate = planningEndDate;
    }

    public long getResponsible() {
        return responsible;
    }

    public void setResponsible(long responsible) {
        this.responsible = responsible;
    }

    public ProductionOrder getStartOrder() {
        return startOrder;
    }

    public void setStartOrder(ProductionOrder startOrder) {
        this.startOrder = startOrder;
    }
}
