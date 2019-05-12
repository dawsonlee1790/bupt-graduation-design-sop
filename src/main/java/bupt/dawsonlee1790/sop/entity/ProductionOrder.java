package bupt.dawsonlee1790.sop.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@Entity
@Table(name = "production_orders")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"executeTime", "next"}, allowGetters = true)
public class ProductionOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotBlank
    private String operationContent;
    private String executor;
    @Temporal(TemporalType.TIME)
    @LastModifiedDate
    private Date executeTime;
    @OneToOne(cascade = CascadeType.ALL)
    private ProductionOrder next;

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

    public Date getExecuteTime() {
        return executeTime;
    }

    public void setExecuteTime(Date executeTime) {
        this.executeTime = executeTime;
    }

    public ProductionOrder getNext() {
        return next;
    }

    public void setNext(ProductionOrder next) {
        this.next = next;
    }
}
