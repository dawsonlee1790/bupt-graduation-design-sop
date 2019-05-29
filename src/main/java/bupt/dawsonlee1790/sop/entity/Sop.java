package bupt.dawsonlee1790.sop.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity // 1
@Table(name = "sops")
@JsonIgnoreProperties(value = {"id"}, allowGetters = true) // 2
public class Sop {

    @Id // 3
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 4
    private long id;

    @NotBlank // 5
    private String name;

    @NotNull // 6
    private long number;

    @OneToOne(cascade = CascadeType.ALL) // 7
    private SopStep startStep;

    @OneToMany // 8
    @JsonIgnore // 9
    private List<ProductionPlan> productionPlanList;

    //========= setter getter ==========

    private Sop() {

    }

    public Sop(String name, long number, SopStep startStep) {
        this.name = name;
        this.number = number;
        this.startStep = startStep;
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

    public SopStep getStartStep() {
        return startStep;
    }

    public void setStartStep(SopStep startStep) {
        this.startStep = startStep;
    }

    public List<ProductionPlan> getProductionPlanList() {
        return productionPlanList;
    }

    public void setProductionPlanList(List<ProductionPlan> productionPlanList) {
        this.productionPlanList = productionPlanList;
    }
}
