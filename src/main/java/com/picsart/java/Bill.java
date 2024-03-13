package com.picsart.java;

import jakarta.persistence.*;


@Entity
@Table(name = "bills")
public class Bill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bill_id")
    private Long billId;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id",nullable = false)
    private Order order;
    @Column(name = "service_fee",nullable = false)
    private Double serviceFee;
    @Column(name = "tax",nullable = false)
    private Double tax;
    @Column(name = "tip",nullable = false)
    private Double tip;
    @Column(name = "total",nullable = false)
    private Double total;

    public Bill() {
    }

    public Bill(Order order, Double serviceFee, Double tax, Double tip, Double total) {
        this.order = order;
        this.serviceFee = serviceFee;
        this.tax = tax;
        this.tip = tip;
        this.total = total;
    }

    public Long getBillId() {
        return billId;
    }

    public void setBillId(Long billId) {
        this.billId = billId;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Double getServiceFee() {
        return serviceFee;
    }

    public void setServiceFee(Double serviceFee) {
        this.serviceFee = serviceFee;
    }

    public Double getTax() {
        return tax;
    }

    public void setTax(Double tax) {
        this.tax = tax;
    }

    public Double getTip() {
        return tip;
    }

    public void setTip(Double tip) {
        this.tip = tip;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "Bill{" +
                "billId=" + billId +
                ", order=" + order +
                ", serviceFee=" + serviceFee +
                ", tax=" + tax +
                ", tip=" + tip +
                ", total=" + total +
                '}';
    }
}
