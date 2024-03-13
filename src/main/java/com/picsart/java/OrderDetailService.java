package com.picsart.java;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

@Service
public class OrderDetailService {
    public void placeNewOrder(Session session){
        Transaction transaction = session.beginTransaction();
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;
        MenuItemService menuItemService = new MenuItemService();
        menuItemService.viewMenuItems(session);
        System.out.println();
        System.out.println("Enter menu item Id which you want to order: ");
        while(!exit){
            try{
                Long itemId = scanner.nextLong();
                MenuItem menuItem = session.get(MenuItem.class,itemId);
                if(menuItem != null){
                    System.out.println("Enter the quantity.");
                    Integer quantity = scanner.nextInt();
                    Order order = new Order();
                    System.out.println("Enter the table number");
                    Integer tableNumber = scanner.nextInt();
                    System.out.println("Enter the waiter Id");
                    Integer waiterId = scanner.nextInt();
                    order.setTableNumber(tableNumber);
                    order.setWaiterId(waiterId);
                    order.setOrderTime(LocalDate.now());
                    order.setFinalized(true);
                    session.persist(order);
                    session.flush();
                    Double priceAtTime = quantity * menuItem.getPrice();
                    OrderDetail orderDetail = new OrderDetail();
                    orderDetail.setOrder(order);
                    orderDetail.setPriceAtTime(priceAtTime);
                    orderDetail.setMenuItem(menuItem);
                    orderDetail.setQuantity(quantity);
                    session.persist(orderDetail);
                    session.flush();
                    System.out.println("Order placed successfully!");
                    exit = true;
                }else{
                    System.out.println("Invalid input. There is no item with the specified Id in the menu.");
                }
            }catch (InputMismatchException e){
                System.out.println("Enter only numbers for the Id");
            }
        }
        transaction.commit();
    }

    public void viewOrders(Session session){
        String hql = "FROM OrderDetail";
        List<OrderDetail> orderDetails = session.createQuery(hql,OrderDetail.class).getResultList();

        for(OrderDetail orderDetail : orderDetails){
            System.out.println("Order detail Id: " + orderDetail.getOrderDetailId() + ", " +
                    "Price at time: " + orderDetail.getPriceAtTime() + ", " +
                    "Quantity: " + orderDetail.getQuantity() + ", " +
                    "Item Id: " + orderDetail.getMenuItem() + ", " +
                    "Order Id: " + orderDetail.getOrder() + ".");
        }
    }

    public void cancelOrder(Session session) {
        Transaction transaction = session.beginTransaction();
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;
        System.out.println("Enter the order Id that you want to delete.");
        while (!exit){
            try{
                Long orderId = scanner.nextLong();
                Order order = session.get(Order.class,orderId);
                if(order != null){
                    session.remove(order);
                    String hqlOrderDetail = "DELETE FROM OrderDetail WHERE order =:order";
                    session.createQuery(hqlOrderDetail).setParameter("order",order).executeUpdate();

                    String hqlBill = "DELETE FROM Bill WHERE order =:order";
                    session.createQuery(hqlBill).setParameter("order",order).executeUpdate();
                    exit = true;
                }else {
                    System.out.println("Invalid input. There is no order with the specified Id.");
                }
            }catch (InputMismatchException e){
                System.out.println("Enter only numbers for Order Id.");
            }
        }
        transaction.commit();
    }
}
