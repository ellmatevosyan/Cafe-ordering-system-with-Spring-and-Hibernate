package com.picsart.java;

import org.hibernate.Session;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

@Service
public class OrderService {
    public void viewPastOrders(Session session){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter start date (yyyy-MM-dd)");
        LocalDate startDate = LocalDate.parse(scanner.nextLine());

        System.out.println("Enter end date (yyyy-MM-dd)");
        LocalDate endDate = LocalDate.parse(scanner.nextLine());

        String hql = "From Order WHERE orderTime BETWEEN :startDate AND :endDate";
        List<Order> orders = session.createQuery(hql, Order.class)
                .setParameter("startDate",startDate)
                .setParameter("endDate", endDate)
                .getResultList();

        System.out.println("Orders within the specified date range:");
        for(Order order : orders){
            System.out.println(order.toString());
        }
    }
}
