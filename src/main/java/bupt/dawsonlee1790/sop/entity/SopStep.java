package bupt.dawsonlee1790.sop.entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity // 1
@Table(name = "sop_steps")
public class SopStep {

    @Id // 2
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 3
    private long id;
    private String operationContent;
    @NotBlank // 4
    private String executor;
    @OneToOne(cascade = CascadeType.ALL) // 5
    private SopStep next;

//  ========getter和setter=========

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
