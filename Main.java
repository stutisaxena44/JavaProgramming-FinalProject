import java.util.Scanner; 

public class Main {  //defines the main class

    public static void main(String[] args) {  //jvm so java virtual machine can access it
    //static so it can run without creating object of main
    //void so it doesnt return anything //main starts program //string args array for commnd-line arguments

        Scanner sc = new Scanner(System.in); //reads user keyboard input

        int again = 1; //created an integer named again with value 1, controls whether program repeats

        System.out.println("==========================================");
        System.out.println("          TRAVEL FARE CALCULATOR");
        System.out.println("==========================================");

        while (again == 1) { //starts a while loop, == asks if is again still equal to one?

            // Number of travelers
            System.out.print("\nEnter number of travelers: "); // \n new line

            int numberOfTravelers = sc.nextInt(); //saves the input by user in numberOfTravelers

            //checks whther the number of traveller is valid or not (error handling)
            if (numberOfTravelers <= 0) { 
                System.out.println("Invalid number of travelers.");
                System.out.println(
                        "Please enter a number greater than 0."
                );
                sc.close();  //closes the scanner input
                return;  //stops execution of current method which is main()
            }

            // Create traveler array to store traveler objects
            Traveler[] travelers =
                    new Traveler[numberOfTravelers]; //stores number of travelers

            // Remove leftover Enter key
            sc.nextLine();

            // Traveler Registration for loop
            System.out.println("\n----- Traveler Registration -----");
            //loops once for each traveler
            for (int i = 0; i < numberOfTravelers; i++) {

                System.out.println("\nTraveler " + (i + 1)); //adds 1 to the array indexing 

                // Name
                System.out.print("Enter name: ");
                String name = sc.nextLine();

                // Age
                System.out.print("Enter age: ");
                int age = sc.nextInt();

                // Age validation
                while (age <= 0) { //used while cause it repeats the code as long as age is invalid

                    System.out.println("Invalid age.");
                    System.out.print("Enter age again: ");

                    age = sc.nextInt(); //reads integer and stores it in age
                }

                // Creates and stores Traveler object
                travelers[i] =
                        new Traveler(name, age); //new meaning creates a new object
                        //traveler calls the traveller constructor

                // Remove leftover Enter key
                sc.nextLine();
            }

            //calls Destination metohod from destination.class
            String destination =
                    Destination.selectDestination(sc); //returned value is stored here
                    //Because the method is static, we call it using the class name.

            //calls  Travel class from travelclass.java
            String travelClass =
                    TravelClass.selectClass(sc); //returned value stored

            // Duration
            System.out.print(
                    "\nEnter trip duration in days: "
            );

            int duration = sc.nextInt();

            while (duration <= 0) { //repeats until a valid duration is entered, positive number

                System.out.println("Invalid duration.");
                System.out.print(
                        "Enter duration again: "
                );

                duration = sc.nextInt();
            }

            // Base fare- double means decimal
            double baseFare =
                    Destination.getBaseFare(destination);

            // Class multiplier
            double classMultiplier =
                    TravelClass.getClassMultiplier(
                            travelClass
                    );

            // Total fare- passes 4 arguments and returned calculation is stored in totalFare
            double totalFare =
                    FareCalculator.calculateBasicFare(
                            baseFare,
                            classMultiplier,
                            duration,
                            numberOfTravelers
                    );

            // Discount //calls calculateDiscount and stores it in discount
            double discount =
                    FareCalculator.calculateDiscount(
                            totalFare,
                            numberOfTravelers
                    );

            // Final fare calls finalFare
            double finalFare =
                    FareCalculator.calculateFinalFare(
                            totalFare,
                            discount
                    );

            // Discount percentage - depends on number of travellers
            double discountRate =
                    FareCalculator.getDiscountRate(
                            numberOfTravelers
                    );

            // Quotation
            System.out.println("\n==========================================");
            System.out.println("              TRIP QUOTATION");
            System.out.println("==========================================");

            System.out.println(
                    "Destination        : " + destination //prints the selected destination
            );

            System.out.println(
                    "Travel Class       : " + travelClass //prints the selected class
            ); 

            System.out.println(
                    "Duration            : "
                    + duration + " days" //prints the selected duration
            );

            System.out.println(
                    "Number of Travelers : " //prints the number of travellers
                    + numberOfTravelers
            );

            System.out.println("\n----- Traveler Details -----"); 

            for (int i = 0; i < numberOfTravelers; i++) { //loops through every traveller in array

                System.out.println( 
                        (i + 1) //gets traveller object in position 1
                        + ". "
                        + travelers[i].getName() //gets name and age
                        + " - Age: "
                        + travelers[i].getAge()
                );
            }

            System.out.println("\n----- Fare Details -----");

            System.out.println(
                    "Base Fare per Traveler/Day : ₹"
                    + baseFare
            );

            System.out.println(
                    "Class Multiplier            : "
                    + classMultiplier
            );

            System.out.println(
                    "Total Fare Before Discount  : ₹"
                    + totalFare
            );

            System.out.println(
                    "Group Discount              : "
                    + discountRate + "%"
            );

            System.out.println(
                    "Discount Amount             : ₹"
                    + discount
            );

            System.out.println(
                    "Final Travel Cost           : ₹"
                    + finalFare
            );

            System.out.println("==========================================");

            // Repeat quotation
            System.out.println(
                    "\nDo you want to calculate another quotation?"
            );

            System.out.println("1. Yes");
            System.out.println("2. No");

            System.out.print("Enter choice: ");

            again = sc.nextInt();

            while (again != 1 && again != 2) {

                System.out.print(
                        "Invalid choice. Enter 1 or 2: "
                );

                again = sc.nextInt();
            }
        }

        System.out.println(
                "\nThank you for using Travel Fare Calculator!"
        );

        sc.close();
    }
}