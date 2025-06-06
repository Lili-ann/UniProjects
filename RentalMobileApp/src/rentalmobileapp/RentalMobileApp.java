/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package rentalmobileapp;

/**
 *
 * @author okafo
 */
import java.time.LocalDate;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class RentalMobileApp {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        RentalMobil rental = new RentalMobil();
        
        
        List<Mobil> selectedCars = new ArrayList<>();
        String RentingChoice;
        
        System.out.println("=== WELCOME TO BUMPTY CAR RENTAL ===");
        rental.ShowMobil();

        //DISPLAY AVAILABLE CARS
        do{
        //SELECT CAR
            int mobilId;
                while(true){
                System.out.println("CHOOSE A CAR (Enter car ID): ");
                mobilId = scanner.nextInt();

            if (mobilId < 1 || mobilId > 5) {
                System.out.println("Error: Invalid car ID! Please select an ID between 1 and 5.");
                } else {
                      break; // Valid ID entered, exit the loop
                }     //OR
            } //wile(mobilId < 1 || mobilId > 5);     
               
            selectedCars.add(rental.availableCars.get(mobilId - 1));
                        
            System.out.println("Do you want to rent more cars? (Y|N)");
            RentingChoice = scanner.next();
        }  while (RentingChoice.equalsIgnoreCase("Y"));

        //Payment method 
        scanner.nextLine();
        String payMethod = "";
        
        while (true){
            System.out.println("Choose your payment method (Cash or Credit card): ") ;
            payMethod = scanner.nextLine();

                if(rental.isValidPayMethod(payMethod)){
                    break;
                }else{
                    System.out.println("Invalid Choice, Input Payment method correctly.");
                }
        
    }


        //INPUT RENTAL DURATION
        System.out.print("How many days would you like to rent this car? ");
        int duration = scanner.nextInt();
        
        scanner.nextLine();
        System.out.println("When is your rent date?(yyyy-mm-dd) ");
        String startRent = scanner.nextLine();
        System.out.println("When is your return date(yyyy-mm-dd)");
        String endRent =  scanner.nextLine();
        
        LocalDate rentDate = LocalDate.parse(startRent);
        LocalDate returnDate = LocalDate.parse(endRent);
        
        System.out.println("Rent Date: " + rentDate);
        System.out.println("Return date: " + returnDate);
        
      /*   double totalPrice = 0;
        for (Mobil mobil: selectedCars) {
            totalPrice += mobil.tarifPerDay * duration; */
      //  }

     //   System.out.println("Total price for renting cars: $" + totalPrice);
        //DISPLAY RECEIPT
        System.out.println();
        rental.showReceipt(selectedCars, payMethod, duration, rentDate, returnDate);
        
        System.out.println("\nDo you want to view your rental history? (Y | N)");
        String viewHistory = scanner.nextLine();
        if (viewHistory.equalsIgnoreCase("Y")){
            rental.showRentalHistory();
            
        }else{
            System.out.println("=== THANK YOU FOR RENTING AT BUMPTY RENTAL MOBIL APP ===");
        }
        
        scanner.close();
        

    }
} 