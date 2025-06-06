/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rentalmobileapp;

/**
 *
 * @author okafo
 */
import java.time.LocalDate;
import java.util.Scanner;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;


public class RentalMobil {
    
    private static final double DISCOUNT = 0.01; // 1% discount for rentals over 7 days
    List<Mobil> availableCars;
    List<RentHistoryEntry> rentalHistory;
    
    public RentalMobil(){
        availableCars = new ArrayList<>();
        rentalHistory = new ArrayList<>();
        availableCars.add(new Mobil(1, "Toyota Avanza ", 5000000));
        availableCars.add(new Mobil(2, "Honda Civic ", 700000));
        availableCars.add(new Mobil(3, "BMW X5 ", 1000000));
        availableCars.add(new Mobil(4, "Suzuki Swift ", 350000));
        availableCars.add(new Mobil(5, "Honda CRV", 6000000));
        
    }
    
  
    
    
    public void ShowMobil(){
        System.out.println("== Available Cars ==");
        for (int i = 0; i < availableCars.size(); i++){
            System.out.println((i + 1)+ ". " + availableCars.get(i).nameMobil + " - Rp" + availableCars.get(i).tarifPerDay + " per day");
       
        }
    }
    
       
    
    public double calculateCost(int mobilId, int duration){
        Mobil mobilChoice = availableCars.get(mobilId - 1);
        double totalPrice = mobilChoice.getTarifPerDay() * duration;
        if (duration <= 7) {
            return totalPrice;
        }else{
            int discountDays = duration -7;
            double fullPrice = mobilChoice.tarifPerDay * 7;
            
            double discountedPrice = recursDiscount(mobilChoice.tarifPerDay, discountDays) * discountDays;
            return fullPrice + discountedPrice;
        }
              
    }
 
    public double recursDiscount(double initialPrice, int discountDays){
        double price = initialPrice;
        for(int i =0; i< discountDays; i++){
            price = initialPrice*(1- DISCOUNT);
    }
    return price;
            
    }
    
    
    public double LateFees (LocalDate rentDate, LocalDate returnDate, int duration) {
        long daysLate = ChronoUnit.DAYS.between(rentDate.plusDays(duration), returnDate);
        return daysLate > 0 ? daysLate * 100000 : 0;
    }
   
    
    public void showReceipt(List<Mobil> selectedCars, String payMethod ,int duration, LocalDate rentDate, LocalDate returnDate){
        double beforeDiscountTotal = 0;
        double afterDiscountTotal = 0;

        double lateFee =  LateFees(rentDate, returnDate, duration);
        
        System.out.println("\n=== PAYMENT RECEIPT ===");
        System.out.println("Rental Duration: " + duration + " days");
        
        long lateDays = ChronoUnit.DAYS.between(rentDate.plusDays(duration), returnDate);
        System.out.println("Late Return by " + (lateDays > 0 ? lateDays : 0) + " days");
        System.out.println("Late Fee: Rp " + lateFee + "\n");
        
        for(Mobil mobil : selectedCars){
            double beforeDiscount = mobil.tarifPerDay * duration;
            beforeDiscountTotal += beforeDiscount;
            
            double discountedPrice = calculateCost(mobil.id, duration);
            afterDiscountTotal += discountedPrice;
            
            System.out.println("  ** " + mobil.nameMobil + "for " + duration + " days");
            System.out.println("Before Discount: Rp " + beforeDiscount);
            System.out.println("After Recursive Discount: Rp " + discountedPrice + "\n");
        }
        
        double totalDiscount =  beforeDiscountTotal - afterDiscountTotal;
        double totalCostFees = afterDiscountTotal + lateFee;
        
 
        
        System.out.printf("Total Before Discount: Rp%.2f%n", beforeDiscountTotal);
        System.out.printf("Discount Amount: Rp%.2f%n", totalDiscount);
        System.out.printf("Total After Discount: Rp%.2f%n",afterDiscountTotal );
        System.out.printf("Total cost (After Discount + Late Fee):Rp%.2f%n ", totalCostFees);
        System.out.println("Payment Method:" + payMethod);
      
        //saving for rental history
        RentHistoryEntry entry = new RentHistoryEntry(selectedCars, duration);
        rentalHistory.add(entry);
    }
     
    public boolean isValidPayMethod(String payMethod){
        return payMethod.equalsIgnoreCase("Cash")||
                payMethod.equalsIgnoreCase("Credit Card");
    }

    public void showRentalHistory(){
        if (rentalHistory.isEmpty()){
            System.out.println("No rental history available.");
            return;   
        }
        System.out.println("=== RENTAL HISTORY ===");
        for (int i = 0; i < rentalHistory.size(); i++){
            RentHistoryEntry entry = rentalHistory.get(i);          
            System.out.println("Cars rented: ");
            for(Mobil mobil : entry.selectedCars){
                System.out.println(" ** " + mobil.nameMobil + "|| Rent Price: " + mobil.tarifPerDay + " per day " + "|| Duration: " + entry.duration + " days" + " || Total paid: Rp " + mobil.tarifPerDay * entry.duration);
            }
            
        }
    }
    
    
     public class RentHistoryEntry{
            List<Mobil> selectedCars;
            int duration;

            public RentHistoryEntry(List<Mobil>selectedCars, int duration){
                this.selectedCars = new ArrayList<>(selectedCars);
                this.duration = duration;
            }
            public List<Mobil> selectedCars(){
                return selectedCars;
            }
            public int getduration(){
                return duration;
            }
           
            
     }

        
     
}

        

    
    

