package RailwayReservation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class TicketInterface {
    private Logical logic=Logical.INSTANCE;
    private Scanner input=new Scanner(System.in);
    public static void main(String[] args) {
            new TicketInterface().booking();
    }
    public void booking(){
        int choice=0;
        System.out.println("1.Book Tickets");
        System.out.println("2.Cancel Tickets");
        System.out.println("3.Print booked Tickets");
        System.out.println("4.Print Available Tickets");
        System.out.println("5.Exit");
        choice= input.nextInt();;
        input.nextLine();
        switch (choice){
            case 1:{
                ArrayList<Registration> tickets=new ArrayList<>();
                System.out.println("Enter total number of tickets");
                int number=input.nextInt();
                input.nextLine();
                for(int i=0;i<number;i++){
                    System.out.println("Passenger "+(i+1));
                    Registration reg=new Registration();
                    System.out.println("Enter name");
                    reg.setName(input.nextLine());
                    System.out.println("Enter age");
                    reg.setAge(input.nextInt());
                    input.nextLine();
                    System.out.println("Enter Gender \n1.Male\n2.Female");
                    int gender=input.nextInt();
                    while(true) {
                        if (gender == 1) {
                            reg.setGender("Male");
                            break;
                        }
                        else if(gender==2) {
                            reg.setGender("Female");
                            System.out.println("Are you travelling with a baby less than 5 years");
                            System.out.println("1.yes\n2.No");
                            int choice1= input.nextInt();
                            if(choice1==1)
                                reg.setChild('y');
                            else if(choice1==2)
                                    reg.setChild('n');

                            break;
                        }
                        else
                            System.out.println("Enter valid input");
                    }
                    input.nextLine();
                    System.out.println("Enter berth preference \n1.upper\n2.middle\n3.lower\n4.side upper");
                    int berth=input.nextInt();
                    while(true) {
                        if (berth == 1) {
                            reg.setBirthPref("Upper");
                            break;
                        }
                        else if(berth==2) {
                            reg.setBirthPref("Middle");
                            break;
                        }
                        else if(berth==3) {
                            reg.setBirthPref("Lower");
                            break;
                        }
                        else if(berth==4) {
                            reg.setBirthPref("SideUpper");
                            break;
                        }
                        else
                            System.out.println("Enter valid input");
                    }
                    input.nextLine();
                    tickets.add(reg);
                }
               int ticketId=logic.bookTickets(tickets);
                if(ticketId>0)
                System.out.println("Your ticket id is "+ticketId+" check info using option three");
                else
                    System.out.println("Ticket booking failed due to unavailability of seats");
                booking();
                break;
            }
            case 2:{
                System.out.println("Enter the ticket id to cancel");
                int id=input.nextInt();
                System.out.println("Enter the seat number to cancel");
                int seat=input.nextInt();
                String status=logic.cancel(id,seat);
                System.out.println(status);
                booking();
                break;
            }
            case 3: {
                System.out.println("Enter the ticket number ");
                int id = input.nextInt();
                HashMap<Long, Registration> map = logic.getMap(id);
                System.out.println(map.size());
                if (map.size() != 0) {
                    System.out.println("Ticket Id  " + id);
                    for (Map.Entry<Long, Registration> value : map.entrySet()) {
                        System.out.println(value.getValue());
                        System.out.println("-----------------------------------------------");
                    }
                    System.out.println("Total no of passengers is  " + map.size());
                } else
                    System.out.println("No ticket under the id is available");
                booking();
                break;
            }
            case 4:{
                System.out.println("The number of available tickets are");
                System.out.println("Confirm count is  " + CacheMemory.OBJECT.getAvailable());
                System.out.println("RAC count is  " + CacheMemory.OBJECT.getRAC());
                System.out.println("Waiting count is  " + CacheMemory.OBJECT.getWaiting());
              booking();
              break;
            }
            case 5:{
                break;
            }
            default:{
                System.out.println("Invalid input");
                booking();
                break;
            }
        }
    }
}
