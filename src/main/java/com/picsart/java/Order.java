package com.picsart.java;

import jakarta.persistence.*;

import java.time.LocalDate;


@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long orderId;
    @Column(name = "waiter_id", nullable = false)
    private Integer waiterId;
    @Column(name = "table_number",nullable = false)
    private Integer tableNumber;
    @Column(name = "order_time",nullable = false)
    private LocalDate orderTime;
    @Column(name = "is_finalized",nullable = false)
    private boolean isFinalized;

    public Order() {
    }

    public Order(Integer waiterId, Integer tableNumber) {
        this.waiterId = waiterId;
        this.tableNumber = tableNumber;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Integer getWaiterId() {
        return waiterId;
    }

    public void setWaiterId(Integer waiterId) {
        this.waiterId = waiterId;
    }

    public Integer getTableNumber() {
        return tableNumber;
    }

    public void setTableNumber(Integer tableNumber) {
        this.tableNumber = tableNumber;
    }

    public LocalDate getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(LocalDate orderTime) {
        this.orderTime = orderTime;
    }

    public boolean isFinalized() {
        return isFinalized;
    }

    public void setFinalized(boolean finalized) {
        isFinalized = finalized;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", waiterId=" + waiterId +
                ", tableNumber=" + tableNumber +
                ", orderTime=" + orderTime +
                '}';
    }
}
