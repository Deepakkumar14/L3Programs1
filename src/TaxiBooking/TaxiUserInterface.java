package TaxiBooking;

import java.util.ArrayList;
import java.util.Scanner;

public class TaxiUserInterface {
    static Scanner input=new Scanner(System.in);
    public static void main(String[]args) {
        ArrayList<TaxiInfo> taxiList = new ArrayList();
        for (int i = 1; i <= 4; i++) {
            TaxiInfo info = new TaxiInfo();
            info.setTaxiNo(i);
            info.setStartPoint('A');
            info.setFreeTime(6);
            info.setTotalEarnings(0);
            taxiList.add(info);
        }
        for(int i=0;i<taxiList.size();i++){
            System.out.println(taxiList.get(i).toString());
        }
        System.out.println("Enter Choice 1 to Book a Taxi");
        System.out.println("Enter Choice 2 to see Taxi Details");
        int choice=input.nextInt();
        TaxiInfo info = new TaxiInfo();
        int bookingId=0;
        switch (choice){
            case 1:
                System.out.println("Enter pickup point");
                info.setStartPoint(input.next().charAt(0));
                System.out.println("Enter drop point");
                info.setEndPoint(input.next().charAt(0));
                System.out.println("Enter pickup time");
                info.setPickTime(input.nextInt());
                info.setBookingId(++bookingId);

        }

    }
}
