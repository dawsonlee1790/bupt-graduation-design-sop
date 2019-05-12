package bupt.dawsonlee1790.sop.entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "sops")
//@JsonIgnoreProperties(value = {"id"})
public class Sop {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotBlank
    private String name;
    @NotNull
    private long number;
    @OneToOne(cascade = CascadeType.ALL)
    private SopStep startStep;
    @OneToMany(mappedBy = "sop")
    private List<ProductionPlan> productionPlanList;

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
