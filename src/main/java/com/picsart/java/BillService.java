package com.picsart.java;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BillService {
    public void generateBill(Session session){
        Transaction transaction = session.beginTransaction();
        String hql = "FROM OrderDetail";
        List<OrderDetail> orderDetails = session.createQuery(hql, OrderDetail.class).getResultList();

        for(OrderDetail orderDetail : orderDetails){
            Order orderId = orderDetail.getOrder();
            Double priceAtTime = orderDetail.getPriceAtTime(); //priceAtTime = quantity * menuItem

            Double serviceFee = priceAtTime * 0.1;
            Double tax = priceAtTime * 0.2;
            Double tip = priceAtTime * 0.1;
            Double total = priceAtTime + serviceFee + tax + tip;
            Bill bill = new Bill(orderId,serviceFee,tax,tip,total);
            session.persist(bill);
            session.flush();
        }
        transaction.commit();
    }

    public void viewBills(Session session){
        String hql = "FROM Bill";
        List<Bill> bills = session.createQuery(hql, Bill.class).getResultList();

        for(Bill bill : bills){
            System.out.println(bill.toString());
        }
    }
}
