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
    private String responsible;
    @OneToOne(cascade = CascadeType.ALL)
    private ProductionOrder startOrder;
    @OneToOne(cascade = CascadeType.ALL)
    @JsonIgnore
    private Sop sop;

    @OneToOne(cascade = CascadeType.ALL)
    private ProductionOrder executedOrder;

    private boolean isException;


    public void goToNextOrder() throws Exception {
        if(isException) throw new Exception("生产批次计划在异常状态，请等待生产责任人处理");
        if(executedOrder == null) executedOrder = startOrder;
        if(executedOrder.getNext() == null) throw new Exception("生产批次计划已经完成，没有下一个生产指令了");
        executedOrder = executedOrder.getNext();
        executedOrder.setExecuteTime(new Date());
    }

    public void reportException(String operationContent, String executorGroup, String executor) {
        ProductionOrder exceptionOrder = new ProductionOrder();
        exceptionOrder.setOperationContent(operationContent);
        exceptionOrder.setExecutorGroup(executorGroup);
        exceptionOrder.setExecutor(executor);
        exceptionOrder.setExecuteTime(new Date());
        ProductionOrder productionOrder = executedOrder.getNext();
        exceptionOrder.setNext(productionOrder);
        executedOrder.setNext(exceptionOrder);
        executedOrder = exceptionOrder;
        isException = true;
    }

    public void handleException(String content,String executorGroup){
        // 生产责任人处理异常的记录
        ProductionOrder handleOrder = new ProductionOrder();
        handleOrder.setOperationContent("处理生产过程异常");
        handleOrder.setExecutorGroup("ProductionLeader");
        handleOrder.setExecutor(responsible);
        handleOrder.setExecuteTime(new Date());

        // 根据处理意见创建的生产指令
        ProductionOrder newOrder = new ProductionOrder();
        newOrder.setExecutorGroup(executorGroup);
        newOrder.setOperationContent(content);

        // 等待处理的生产指令
        ProductionOrder tempOrder = executedOrder.getNext();

        executedOrder.setNext(handleOrder);
        handleOrder.setNext(newOrder);
        newOrder.setNext(tempOrder);

        executedOrder = handleOrder;

        isException = false;
    }

    public enum PlanStatus{
        Approve("批准"),
        NotApprove("不批准"),
        WaittingReview("待审核");

        private String value;

        PlanStatus(String value) {
            this.value = value;
        }
    }


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

    public String getResponsible() {
        return responsible;
    }

    public void setResponsible(String responsible) {
        this.responsible = responsible;
    }

    public ProductionOrder getStartOrder() {
        return startOrder;
    }

    public void setStartOrder(ProductionOrder startOrder) {
        this.startOrder = startOrder;
    }

    public ProductionOrder getExecutedOrder() {
        return executedOrder;
    }

    public void setExecutedOrder(ProductionOrder executedOrder) {
        this.executedOrder = executedOrder;
    }

    public boolean isException() {
        return isException;
    }

    public void setException(boolean exception) {
        isException = exception;
    }
}
