package bupt.dawsonlee1790.sop.entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "sop_steps")
public class SopStep {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String operationContent;
    @NotBlank
    private String executor;
    @OneToOne(cascade = CascadeType.ALL)
    private SopStep next;

    public SopStep(){}

    public SopStep(String operationContent,String executorGroup, SopStep next){
        this.executor = executorGroup;
        this.operationContent = operationContent;
        this.next = next;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getOperationContent() {
        return operationContent;
    }

    public void setOperationContent(String operationContent) {
        this.operationContent = operationContent;
    }

    public String getExecutor() {
        return executor;
    }

    public void setExecutor(String executor) {
        this.executor = executor;
    }

    public SopStep getNext() {
        return next;
    }

    public void setNext(SopStep next) {
        this.next = next;
    }
}
