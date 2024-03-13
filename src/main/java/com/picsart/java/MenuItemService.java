package com.picsart.java;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Service;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

@Service
public class MenuItemService {
    public void createMenuItem(Session session){
        Transaction transaction = session.beginTransaction();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter new menu item's category.");
        String category = scanner.nextLine();
        System.out.println("Enter name of the new item.");
        String name = scanner.nextLine();
        System.out.println("Enter description of the new item.");
        String description = scanner.nextLine();
        boolean exit = false;
        double price = 0.0;
        while(!exit){
            try{
            System.out.println("Enter the price of the new menu item.");
            price = scanner.nextDouble();
            exit = true;
            }catch(InputMismatchException e){
                System.out.println("Incorrect input. Enter only numbers for the price.");
                scanner.next();
            }
        }
        MenuItem menuItem = new MenuItem(name,description,price,category);
        session.persist(menuItem);
        transaction.commit();
        System.out.println("New menu item was created successfully");
    }

    public void updateMenuItem(Session session){
        Transaction transaction = session.beginTransaction();
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;
        System.out.println("Enter the Id of the item which you want to update");
        while(!exit){
            try{
                Long item_id = scanner.nextLong();
                scanner.nextLine();
                MenuItem menuItem = session.get(MenuItem.class,item_id);
                if(menuItem != null){
                    System.out.println("Enter the property that you want to update of this item.");
                    String input = scanner.nextLine();
                    if(input.equalsIgnoreCase("name")){
                        System.out.println("Enter updated name of the item");
                        String newName = scanner.nextLine();
                        menuItem.setName(newName);
                    } else if (input.equalsIgnoreCase("description")) {
                        System.out.println("Enter updated description for the item");
                        String newDescription = scanner.nextLine();
                        menuItem.setDescription(newDescription);
                    } else if (input.equalsIgnoreCase("price")) {
                        System.out.println("Enter updated price for the item.");
                        Double newPrice = scanner.nextDouble();
                        menuItem.setPrice(newPrice);
                    } else if (input.equalsIgnoreCase("category")) {
                        System.out.println("Enter the updated category for the item");
                        String newCategory = scanner.nextLine();
                        menuItem.setCategory(newCategory);
                    }
                }else{
                    System.out.println("Invalid input. Please try again!");
                }
            }catch (InputMismatchException e){
                System.out.println("Invalid input.Enter only numbers for the Id!");
            }
        }
        transaction.commit();
    }

    public void deleteMenuItem(Session session){
        Transaction transaction = session.beginTransaction();
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;
        System.out.println("Enter the Id of the item which you want to delete.");
        while(!exit){
            try{
                Long item_id = scanner.nextLong();
                MenuItem menuItem = session.get(MenuItem.class,item_id);
                if(menuItem != null){
                    session.remove(menuItem);
                    System.out.println("The item was deleted successfully!");
                }else {
                    System.out.println("Invalid input. There is no item with that Id.");
                }
            }catch (InputMismatchException e){
                System.out.println("Invalid input. Enter only numbers for the item Id!");
            }
            transaction.commit();
        }
    }

    public void viewMenuItems(Session session){
        String hql = "FROM MenuItem";
        List<MenuItem> menuItems = session.createQuery(hql, MenuItem.class).getResultList();
        for(MenuItem menuItem : menuItems){
            System.out.println("Item ID: " + menuItem.getItemId() +
                    ", Name: " + menuItem.getName() +
                    ", Description: " + menuItem.getDescription() +
                    ", Price: " + menuItem.getPrice() +
                    ", Category: " + menuItem.getCategory());
        }
    }
}
