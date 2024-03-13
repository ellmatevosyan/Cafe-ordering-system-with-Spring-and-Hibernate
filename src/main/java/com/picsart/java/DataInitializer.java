package com.picsart.java;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.math.BigDecimal;

public class DataInitializer {
    public static void initializeData(SessionFactory sessionFactory){
        try(Session session = sessionFactory.openSession()){
            Transaction transaction = session.beginTransaction();
            MenuItem cheeseburger = new MenuItem("Cheeseburger", "Juicy beef patty with melted cheese on a sesame seed bun", 9.99, "Burgers");
            MenuItem caesarSalad = new MenuItem("Caesar Salad", "Crisp romaine lettuce with Caesar dressing, croutons, and Parmesan cheese", 7.99, "Salads");
            MenuItem margheritaPizza = new MenuItem("Margherita Pizza", "Classic pizza with tomato sauce, mozzarella cheese, and fresh basil", 12.99, "Pizza");
            MenuItem spaghettiCarbonara = new MenuItem("Spaghetti Carbonara", "Pasta with creamy sauce, bacon, eggs, and Parmesan cheese", 11.99, "Pasta");
            MenuItem grilledSalmon = new MenuItem("Grilled Salmon", "Fresh salmon fillet grilled to perfection, served with steamed vegetables", 14.99, "Seafood");
            MenuItem chickenAlfredo = new MenuItem("Chicken Alfredo", "Fettuccine pasta with creamy Alfredo sauce and grilled chicken", 10.99, "Pasta");
            MenuItem mushroomRisotto = new MenuItem("Mushroom Risotto", "Creamy risotto cooked with mushrooms, onions, and Parmesan cheese", 8.99, "Risotto");
            MenuItem fishAndChips = new MenuItem("Fish and Chips", "Crispy battered fish served with French fries and tartar sauce", 11.99, "Seafood");
            MenuItem vegetableStirFry = new MenuItem("Vegetable Stir-Fry", "Assorted vegetables stir-fried in a savory sauce, served with rice", 9.99, "Vegetarian");
            MenuItem tiramisu = new MenuItem("Tiramisu", "Classic Italian dessert made with layers of coffee-soaked ladyfingers and mascarpone cheese", 6.99, "Desserts");

            session.persist(cheeseburger);
            session.persist(caesarSalad);
            session.persist(margheritaPizza);
            session.persist(spaghettiCarbonara);
            session.persist(grilledSalmon);
            session.persist(chickenAlfredo);
            session.persist(mushroomRisotto);
            session.persist(fishAndChips);
            session.persist(vegetableStirFry);
            session.persist(tiramisu);

            session.flush();
            transaction.commit();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
