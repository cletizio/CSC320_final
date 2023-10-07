import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Automobile {
    private String make;
    private String model;
    private String color;
    private int year;
    private int mileage;

    // Parameterized constructor
    public Automobile(String make, String model, String color, int year, int mileage) {
        this.make = make;
        this.model = model;
        this.color = color;
        this.year = year;
        this.mileage = mileage;
    }

    // Default constructor
    public Automobile(){

    }


    // Method to add a vehicle
    public static String addVehicle(ArrayList<Automobile> inventory, Automobile vehicle) {
        try {
            inventory.add(vehicle);
            return "Vehicle added successfully.";
        } catch (Exception e) {
            return "Failed to add vehicle: " + e.getMessage();
        }
    }

    public static String[] printInventory(ArrayList<Automobile> inventory) {
        try {
            String[] vehicleInformation = new String[inventory.size()];
            for (int i = 0; i < inventory.size(); i++) {
                Automobile vehicle = inventory.get(i);
                vehicleInformation[i] = "Make: " + vehicle.getMake() + "\nModel: " + vehicle.getModel() + "\nColor: "
                        + vehicle.getColor() + "\nYear: " + vehicle.getYear() + "\nMileage: " +
                        vehicle.getMileage() + "\n";
            }
            return vehicleInformation;
        } catch (Exception e) {
            return new String[]{"Unable to print vehicle inventory: " + e.getMessage()};
        }
    }

    public static String removeVehicle(ArrayList<Automobile> inventory, int index) {
        try {
            if (index >= 0 && index < inventory.size()) {
                inventory.remove(index);
                return "Vehicle removed successfully.";
            } else {
                return "Index invalid. Please enter a valid index. Vehicle not removed.";
            }
        } catch (Exception e) {
            return "That index is invalid. Unable to remove vehicle." + e.getMessage();
        }
    }

    public static String updateVehicle(Automobile vehicle, String make, String model, String color, int year,
                                       int mileage) {
        try {
            vehicle.setMake(make);
            vehicle.setModel(model);
            vehicle.setColor(color);
            vehicle.setYear(year);
            vehicle.setMileage(mileage);
            return "Vehicle information updated successfully.";
        } catch (Exception e) {
            return "Unable to update vehicle." + e.getMessage();
        }

    }

    public static void printToFile(ArrayList<Automobile> inventory, String filePath) {
        try (FileWriter fileWriter = new FileWriter(filePath)) {
            String[] vehicleInfo = printInventory(inventory);
            for (String info : vehicleInfo) {
                fileWriter.write(info + "\n");
            }
            System.out.println("Vehicle information has been printed to " + filePath);
        } catch (IOException e) {
            System.out.println("Vehicle information failed to print to file " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        ArrayList<Automobile> inventory = new ArrayList<>();
        Scanner scnr = new Scanner(System.in);
        boolean willPrintToFile = false;

        while (true) {
            System.out.println("Select an option:");
            System.out.println("1. Add a new vehicle");
            System.out.println("2. List vehicle information");
            System.out.println("3. Remove a vehicle");
            System.out.println("4. Update vehicle attributes");
            System.out.println("5. Quit");

            int choice = scnr.nextInt();
            scnr.nextLine();

            switch (choice) {
                case 1 -> {
                    System.out.println("Enter the make ");
                    String make = scnr.next();
                    System.out.println("Enter the model ");
                    String model = scnr.next();
                    System.out.println("Enter the color ");
                    String color = scnr.next();
                    System.out.println("Enter the year ");
                    int year = scnr.nextInt();
                    System.out.println("Enter the mileage ");
                    int mileage = scnr.nextInt();
                    Automobile newVehicle = new Automobile(make, model, color, year, mileage);
                    String addResult = addVehicle(inventory, newVehicle);
                    System.out.println(addResult);
                    scnr.nextLine();
                }
                case 2 -> {
                    System.out.println("Vehicle Inventory: ");
                    String[] vehicleInformation;
                    vehicleInformation = printInventory(inventory);
                    for (String info : vehicleInformation) {
                        System.out.println(info);
                    }
                }
                case 3 -> {
                    System.out.println("Enter the index of the vehicle to remove.");
                    int vehicleIndex = scnr.nextInt();
                    String removeResult = removeVehicle(inventory, vehicleIndex);
                    System.out.println(removeResult);
                }
                case 4 -> {
                    System.out.println("Enter the index of the vehicle to update.");
                    int updateIndex = scnr.nextInt();
                    System.out.println("Enter the updated vehicle make: ");
                    String updateMake = scnr.next();
                    System.out.println("Enter the updated vehicle model: ");
                    String updateModel = scnr.next();
                    System.out.println("Enter the updated vehicle color: ");
                    String updateColor = scnr.next();
                    System.out.println("Enter the updated vehicle year: ");
                    int updateYear = scnr.nextInt();
                    System.out.println("Enter the updated vehicle mileage: ");
                    int updateMileage = scnr.nextInt();
                    String updateSuccess = updateVehicle(inventory.get(updateIndex), updateMake, updateModel,
                            updateColor, updateYear, updateMileage);
                    System.out.println(updateSuccess);
                }
                case 5 -> {
                    System.out.println("Exiting the program.");
                    willPrintToFile = true;
                }
                default -> System.out.println("Invalid choice please enter a number 1-5.");
            }

            if (willPrintToFile) {
                System.out.println("Do you want to print the information to a file upon quit? Enter 'Y' for yes or 'N' for no.");
                String printToFileResponse = scnr.nextLine();
                if (printToFileResponse.equalsIgnoreCase("Y")) {
                    String filePath = "C:\\Temp\\Autos.txt";
                    printToFile(inventory, filePath);
                } else if (printToFileResponse.equalsIgnoreCase("N")) {
                    System.out.println("Vehicle information will not be printed to file.");
                } else {
                    System.out.println("Invalid entry please enter 'Y' or 'N'");
                }
                scnr.close();
                System.exit(0);
            }
        }
    }

    // Getters and Setters
    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMileage() {
        return mileage;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

}

