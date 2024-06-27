package com.flipkart.client;

import com.flipkart.bean.Gym;
import com.flipkart.bean.GymOwner;
import com.flipkart.bean.Slots;
import com.flipkart.business.GymOwnerInterface;
import com.flipkart.business.GymOwnerService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static com.flipkart.constants.ColorConstants.*;
public class GymFlipFitGymOwnerMenu {

    GymOwnerInterface gymOwnerService = new GymOwnerService();
    static Scanner obj = new Scanner(System.in);
    boolean verifyGymOwner(String email, String password) {
        return gymOwnerService.validateLogin(email, password);
    }
    boolean gymOwnerLogin(String email, String password) {
        if (gymOwnerService.validateLogin(email, password)) {
            System.out.println("\nLogin Successful\n");
            while (true) {
                System.out.println("----------------Gym Owner menu--------------------");
                System.out.println("1. Add a gym");
                System.out.println("2. View all gyms");
                System.out.println("3. Logout");
                int y = Integer.parseInt(obj.nextLine());

                switch (y) {
                    case 1:
                        addGym(email);
                        break;
                    case 2:
                        displayGyms(email);
                        break;
                    case 3:
                        return true;
                }
            }
        } else return false;
    }


    void addGym(String userId) {
        Gym gym = new Gym();

        System.out.println("Enter the following info:");
        System.out.println("\nEnter gym name:");
        String gymName = obj.nextLine();
        System.out.println("\nGym Address:");
        String address = obj.nextLine();
        System.out.println("\nGym Location:");
        String location = obj.nextLine();

        gym.setGymAddress(address);
        gym.setLocation(location);
        gym.setGymName(gymName);
        gym.setStatus("unverified");
        List<Slots> slots = new ArrayList<>();
        System.out.println("\nHow many slots to be entered?");
        int slotNo = Integer.parseInt(obj.nextLine());
        int x = 1;
        while (slotNo != 0) {
            System.out.println("Add slot no. " + x++ + "\n");
            System.out.println("\nEnter start time:");
            int startTime = Integer.parseInt(obj.nextLine());
            System.out.println("\nEnter available seats:");
            int number = Integer.parseInt(obj.nextLine());
            Slots slot = new Slots(x - 1, startTime, number);
            slots.add(slot);
            slotNo--;
        }
        gym.setSlots(slots);
        gym.setOwnerId(userId);

        gymOwnerService.addGymWithSlots(gym);
    }


    void createGymOwner() {
        System.out.println("Enter the following info:");
        System.out.println("\nYour email: ");
        String ownerEmail = obj.nextLine();
        System.out.println("\nYour name: ");
        String ownerName = obj.nextLine();
        System.out.println("\nEnter a password: ");
        String password = obj.nextLine();
        System.out.println("\nPhone number: ");
        String phoneNo = obj.nextLine();
        System.out.println("\nNation ID/ Aadhaar Number: ");
        String nationalId = obj.nextLine();
        if (nationalId.length() != 12) {
            System.out.println("\nInvalid Adhaar No. Enter a valid adhaar!\n");
            return;
        }
        System.out.println("\nGST: ");
        String GST = obj.nextLine();
        System.out.println("\nPAN Details: ");
        String PAN = obj.nextLine();
        if (PAN.length() != 10) {
            System.out.println("\nInvalid Pan Card No. Enter a valid Pan Card No!\n");
            return;
        }

        GymOwner gymOwner = new GymOwner();
        List<Gym> emptyGymList = new ArrayList<>();
        gymOwner.setOwnerEmail(ownerEmail);
        gymOwner.setPAN(PAN);
        gymOwner.setOwnerName(ownerName);
        gymOwner.setGST(GST);
        gymOwner.setPassword(password);
        gymOwner.setNationalId(nationalId);
        gymOwner.setPhoneNo(phoneNo);
        gymOwner.setGyms(emptyGymList);
        gymOwner.setStatus("Unverified");

        gymOwnerService.createGymOwner(gymOwner);
    }


    void displayGyms(String userId) {
        List<Gym> gymsList = gymOwnerService.viewMyGyms(userId);
        int x = 1;
        for (Gym gym : gymsList) {
            System.out.println("Gym " + x + ":");
            System.out.println("Name: " + gym.getGymName());
            System.out.println("Address: " + gym.getGymAddress());
            System.out.println("Location: " + gym.getLocation());
            System.out.println("Slots:");
            for (Slots slot : gym.getSlots()) {
                System.out.println("Slot ID: " + slot.getSlotsId());
                System.out.println("Slot Time: " + slot.getStartTime() + " - " + (slot.getStartTime() + 1));
                System.out.println("Seats: " + slot.getSeatCount());
                System.out.println();
            }
            x++;
            System.out.println();
        }
    }
}