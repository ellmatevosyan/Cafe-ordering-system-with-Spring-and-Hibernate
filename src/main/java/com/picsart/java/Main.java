package com.picsart.java;

import org.hibernate.Session;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        try(AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(HibernateConfig.class)){
            MenuItemService menuItemService = context.getBean(MenuItemService.class);
            OrderDetailService orderDetailService = context.getBean(OrderDetailService.class);
            OrderService orderService = context.getBean(OrderService.class);
            BillService billService = context.getBean(BillService.class);
            Session session = context.getBean(Session.class);
            boolean exit = false;
            while(!exit){
                System.out.println("You can choose from the following options:");
                System.out.println("1. Menu operations.");
                System.out.println("2. Order operations.");
                System.out.println("3. Bill.");
                System.out.println("4. Exit.");
                System.out.println("Now enter your choice: ");
                Scanner scanner = new Scanner(System.in);
                String choice = scanner.nextLine();
                switch (choice){
                    case "1" -> {
                        boolean exit2 = false;
                        while(!exit2){
                            System.out.println("You can choose whether you want to add, remove or update items of the menu:");
                            System.out.println("1. Add new item to the menu.");
                            System.out.println("2. Update the existing item.");
                            System.out.println("3. Remove the item from the menu.");
                            System.out.println("4. Return to the previous step.");
                            System.out.println("5. Exit.");
                            System.out.println("6. view menu Items.");
                            String choice2 = scanner.nextLine();
                            switch (choice2){
                                case "1" -> menuItemService.createMenuItem(session);
                                case "2" -> menuItemService.updateMenuItem(session);
                                case "3" -> menuItemService.deleteMenuItem(session);
                                case "4" -> exit2 = true;
                                case "5" -> {
                                    exit = true;
                                    exit2 = true;
                                }
                                case "6" -> menuItemService.viewMenuItems(session);
                                default -> System.out.println("Invalid input! You can choose only 1,2,3,4 or 5.");
                            }
                        }
                    }
                    case "2" -> {
                        boolean exit3 = false;
                        while(!exit3){
                            System.out.println("1. Place order.");
                            System.out.println("2. View order.");
                            System.out.println("3. Cancel order.");
                            System.out.println("4. View Past Orders.");
                            System.out.println("5. Exit.");
                            System.out.println("Now enter your choice.");
                            String choice3 = scanner.nextLine();
                            switch (choice3) {
                                case "1" -> orderDetailService.placeNewOrder(session);
                                case "2" -> orderDetailService.viewOrders(session);
                                case "3" -> orderDetailService.cancelOrder(session);
                                case "4" -> orderService.viewPastOrders(session);
                                case "5" -> exit3 = true;
                                default -> System.out.println("Invalid input. Enter only 1,2,3,4 or 5");
                            }
                        }
                    }
                    case "3" -> {
                        boolean exit4 = false;
                        while (!exit4){
                            System.out.println("1. Generate bill.");
                            System.out.println("2. View Bills.");
                            System.out.println("Now enter your choice.");
                            String choice4 = scanner.nextLine();
                            switch (choice4){
                                case "1" -> billService.generateBill(session);
                                case "2" -> billService.viewBills(session);
                                default -> System.out.println("Invalid input. Enter only 1 or 2");
                            }
                        }
                    }
                    case "4" -> exit = true;
                    default -> System.out.println("Invalid input. Enter only 1,2,3 or 4");
                }
            }
        }
   
    }
}