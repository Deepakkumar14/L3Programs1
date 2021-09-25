package RailwayReservation;

import java.util.ArrayList;
import java.util.HashMap;

public enum Logical {
    INSTANCE;
    private int tempSeat=1;
    private int ticketId=1;
    private int seatNumber=1000;
    private static CacheMemory cache=CacheMemory.OBJECT;
    public int bookTickets(ArrayList<Registration> tickets) {
        int count1=0;
        for (Registration passenger:tickets) {
            if(passenger.getAge()<=5){
                passenger.setBirthPref("No berth for child");
            }
            else if (passenger.getChild()=='y' || passenger.getAge()>=60){
                passenger.setBirthPref("lower");
            }
            else if(CacheMemory.OBJECT.getAvailable() == 0){
                if(CacheMemory.OBJECT.getRAC() == 0){
                    if(CacheMemory.OBJECT.getWaiting() == 0){
                        count1++;
                        passenger.setStatus("Failed no seats available");
                    } else {
                        waitingList(passenger);
                    }
                } else {
                    allotRc(passenger,ticketId);
                }
            }else{
                long count;
                switch (passenger.getBirthPref()) {
                    case "Lower": {
                        count = allotBirth("lower");
                        if (count == 0) {
                             passenger = allotAny(passenger);
                        }else
                            passenger=setBerth("Lower",passenger);
                        break;
                    }
                    case "Middle": {
                        count = allotBirth("middle");
                        if (count == 0) {
                             passenger = allotAny(passenger);
                        }
                        else
                            passenger=setBerth("Middle",passenger);
                        break;
                    }
                    case "Upper": {
                        count = allotBirth("upper");
                        if (count == 0) {
                             passenger = allotAny(passenger);
                        }
                        else
                            passenger=setBerth("Upper",passenger);
                        break;
                    }
                    case "SideUpper": {
                        count = allotBirth("sideUpper");
                        if (count == 0) {
                             passenger = allotAny(passenger);
                        }
                        else
                            passenger=setBerth("SideUpper",passenger);
                        break;
                    }
                }
                CacheMemory.OBJECT.setConfirmedTickets(passenger);
            }
        }
        if(tickets.size()==count1) {
            ticketId--;
            return 0;
        }
        ticketId++;
        return ticketId-1;
    }

    public long allotBirth(String berth) {
        long count=0;
        if(berth.equalsIgnoreCase("Lower"))
           count= CacheMemory.OBJECT.getLower();
        else if(berth.equalsIgnoreCase("Middle"))
           count= CacheMemory.OBJECT.getMiddle();
        else if(berth.equalsIgnoreCase("SideUpper"))
           count= CacheMemory.OBJECT.getSideUpper();
        else if(berth.equalsIgnoreCase("Upper"))
            count=CacheMemory.OBJECT.getUpper();

        return count;
    }

    public Registration setBerth(String berth,Registration reg){
        reg.setBirth(berth);
        reg.setStatus("Confirmed");
        reg.setSeatNo(seatNumber);
        reg.setTicketId(ticketId);
        seatNumber++;
        return reg;
    }

    public Registration allotAny(Registration passenger){
        if(cache.getUpper()>0){
            passenger.setBirth("Upper");
            passenger.setStatus("Confirmed");
            passenger.setSeatNo(seatNumber);
            passenger.setTicketId(ticketId);
            cache.setUpper();
        }
        else if(cache.getLower()>0){
            passenger.setBirth("Lower");
            passenger.setStatus("Confirmed");
            passenger.setSeatNo(seatNumber);
            passenger.setTicketId(ticketId);
            cache.setLower();
        }
        else if(cache.getMiddle()>0){
            passenger.setBirth("Middle");
            passenger.setStatus("Confirmed");
            passenger.setSeatNo(seatNumber);
            passenger.setTicketId(ticketId);
            cache.setMiddle();
        }
        else if(cache.getSideUpper()>0){
            passenger.setBirth("SideUpper");
            passenger.setStatus("Confirmed");
            passenger.setSeatNo(seatNumber);
            passenger.setTicketId(ticketId);
            cache.setSideUpper();
        }
        seatNumber++;
        return passenger;
    }

    public void waitingList(Registration passenger) {
        passenger.setStatus("waiting");
        passenger.setTicketId(ticketId);
        passenger.setSeatNo(tempSeat);
        tempSeat++;
        CacheMemory.OBJECT.setWaitingList(passenger);
        CacheMemory.OBJECT.setConfirmedTickets(passenger);
    }

    public void allotRc(Registration passenger,int ticketId) {
        passenger.setBirth("SideLower");
        passenger.setStatus("RAC");
        passenger.setSeatNo(tempSeat);
        tempSeat++;
        passenger.setTicketId(ticketId);
        CacheMemory.OBJECT.setRAC(passenger);
        CacheMemory.OBJECT.setConfirmedTickets(passenger);
    }

    public String cancel(int id, int seat) {
        Registration reg=CacheMemory.OBJECT.getInnerMap(id,seat);
        Registration regRac=CacheMemory.OBJECT.getRACList();
        Registration regWait = CacheMemory.OBJECT.getWaitingList();
        if(regRac!=null) {
            regRac.setStatus("Confirmed");
            regRac.setBirth(reg.getBirth());
            regRac.setSeatNo(reg.getSeatNo());
            CacheMemory.OBJECT.setConfirmedTickets(regRac);
        }
        if (regWait != null) {
            regWait.setStatus("RAC");
            regWait.setBirth("SideLower");
            CacheMemory.OBJECT.setRAC(regWait);
        }
       return "Ticket cancelled";
    }

    public HashMap<Long, Registration> getMap(int id) {
        return CacheMemory.OBJECT.getMap(id);
    }
}
