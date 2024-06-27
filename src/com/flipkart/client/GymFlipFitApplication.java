package com.flipkart.client;

import com.flipkart.business.GymOwnerService;
import com.flipkart.business.CustomerService;
import com.flipkart.business.CustomerInterface;

import java.util.Random;
import java.util.Scanner;

import static com.flipkart.constants.ColorConstants.*;

public class GymFlipFitApplication {
    static GymFlipFitGymOwnerMenu owner = new GymFlipFitGymOwnerMenu();
    static GymFlipFitCustomerMenu customer = new GymFlipFitCustomerMenu();
    static GymOwnerService gymOwnerService = new GymOwnerService();

    static CustomerInterface userService = new CustomerService();
    static Scanner obj = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("\n");
        System.out.println("Welcome to FlipFit!");
        System.out.println("\n");
        boolean exitFlag = false;
        while(true) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Press 1 for Login");
            System.out.println("Press 2 for Registration");
            System.out.println("Press 3 for Update Password");
            System.out.println("Press 4 for Exit");
            int x = scanner.nextInt();
            switch (x) {
                case 1 :
                    System.out.println("Enter email");
                    String userId = obj.nextLine();
                    System.out.println("Enter password");
                    String password = obj.nextLine();
                    System.out.println("Enter role \n1. Admin\n2. Customer\n3. Gym Owner\n");
                    int role = scanner.nextInt();

                    switch (role) {
                        case 1 :
                            GymFlipFitAdminMenu admin = new GymFlipFitAdminMenu();

                            if(!admin.verifyAdminCredentials(userId,password)){
                                System.out.println(ANSI_YELLOW + "Invalid Credentials"+ANSI_RESET);
                                break;
                            }

                            boolean flag = true;

                            while(flag) {

                                System.out.println("Press 1 for View all users");
                                System.out.println("Press 2 for View all Gyms");
                                System.out.println("Press 3 for View all Gym Owners");
                                System.out.println("Press 4 for Verify Gym");
                                System.out.println("Press 5 for Verify GymOwner");
                                System.out.println("Press 6 for View pending Gyms");
                                System.out.println("Press 7 for View pending Gym Owners");
                                System.out.println("Press 8 for Exit");

                                int k = Integer.parseInt(obj.nextLine());

                                switch (k) {
                                    case 1:
                                        admin.viewUsers();
                                        break;
                                    case 2:
                                        admin.viewGyms();
                                        break;
                                    case 3:
                                        admin.viewGymOwners();
                                        break;
                                    case 4:
                                        System.out.println("Enter the Gym Id to be verified ");
                                        int id1 = Integer.parseInt(obj.nextLine());
                                        admin.verifyGym(id1);
                                        break;
                                    case 5:
                                        System.out.println("Enter the Gym Owner Id to be verified ");
                                        int id2 = Integer.parseInt(obj.nextLine());
                                        admin.verifyGymOwner(id2);
                                        break;
                                    case 6:
                                        admin.viewUnverifiedGyms();
                                        break;
                                    case 7:
                                        admin.viewUnverifiedGymOwners();
                                        break;
                                    case 8:
                                        System.exit(0);
                                        flag = false;
                                        break;
                                }
                                if(!flag) break;
                            }
                            break;

                        case 2 :
                            if(!customer.userLogin(userId,password))
                                System.out.println(ANSI_YELLOW+"Invalid credentials"+ANSI_RESET);
                            break;
                        case 3 :
                            if(!owner.gymOwnerLogin(userId,password)){
                                System.out.println(ANSI_YELLOW+"Invalid credentials"+ANSI_RESET);
                            }

                            break;
                        case 4 :
                            break;
                        default:
                            System.out.println(ANSI_YELLOW+"Invalid Options Selected. Please Try Again:("+ANSI_RESET);
                            break;

                    }

                    break;
                case 2 :
                    System.out.println("Press 1 to Register as a Customer");
                    System.out.println("Press 2 to Register as a Gym Owner");
                    System.out.println("Press 3 to Go Back");
                    int k = Integer.parseInt(obj.nextLine());
                    switch(k){
                        case 1:
                            customer.createCustomer();
                            break;
                        case 2:
                            owner.createGymOwner();
                        default:
                            break;
                    }
                    break;
                case 3 :
                    System.out.println("----------------------Password Change-----------------\n");
                    System.out.println("Enter email");
                    String user = obj.nextLine();
                    System.out.println("Enter password");
                    String userPassword = obj.nextLine();
                    System.out.println("Enter role \n1. Admin\n2. Customer\n3. Gym Owner\n");
                    int respectiveRole = obj.nextInt();
                    System.out.println("Enter New password");
                    String updatedPassword = obj.nextLine();

                    switch (respectiveRole) {
                        case 2 :
                            if(!customer.validateUser(user,userPassword))
                                System.out.println(ANSI_YELLOW+"Invalid credentials"+ANSI_RESET);
                            else{
                                userService.updateGymUserPassword(user,userPassword, updatedPassword);
                            }
                            break;
                        case 3 :
                            if(!owner.verifyGymOwner(user,userPassword)){
                                System.out.println(ANSI_YELLOW+"Invalid credentials"+ANSI_RESET);
                            }
                            else{
                                gymOwnerService.updateGymOwnerPassword(user,userPassword, updatedPassword);
                            }

                            break;
                    }
                    break;
                case 4 :
                    exitFlag = true;
                    System.out.println("Thank you for using FlipFit <3");
                    break;
                default:
                    System.out.println(ANSI_YELLOW+"Invalid Options Selected. Please Try Again:( "+ANSI_RESET);
                    break;
                }
            if(exitFlag)break;
        }


    }
}

