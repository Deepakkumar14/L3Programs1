package RailwayReservation;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public enum CacheMemory {
    OBJECT;

    private HashMap<Integer,HashMap<Long,Registration>> confirmedTickets=new HashMap<>();
    private Queue<Registration> RACTickets=new LinkedList<Registration>();
    private Queue<Registration> waitingList=new LinkedList<Registration>();
    private long available =2;
    private  long RAC=1;
    private long waiting=1;
    private long upper=1;
    private long lower=0;
    private long middle=0;
    private long sideUpper=1;
    private long sideLower=1;

    public long getUpper() {
        return upper;
    }

    public void setUpper() {
       upper--;
    }

    public long getLower() {
        return lower;
    }

    public void setLower() {
       lower--;
    }

    public long getMiddle() {
        return middle;
    }

    public void setMiddle() {
       middle--;
    }

    public long getSideUpper() {
        return sideUpper;
    }

    public void setSideUpper() {
       sideUpper--;
    }

    public long getAvailable() {
        return available;
    }

    public void incrementAvailable() {
        available++;
    }

    public long getRAC() {
        return RAC;
    }

    public long getWaiting() {
        return waiting;
    }

    public Registration getRACList() {
        RAC++;
        return RACTickets.poll();
    }


    public Registration getWaitingList() {
        waiting++;
        return waitingList.poll();
    }

    public void setConfirmedTickets(Registration reg){
        HashMap<Long,Registration> map=confirmedTickets.getOrDefault(reg.getTicketId(),new HashMap<Long,Registration>());
        map.put(reg.getSeatNo(),reg);
        confirmedTickets.put(reg.getTicketId(),map);
        available--;
    }

    public void setRAC(Registration passenger) {
        RACTickets.add(passenger);
        RAC--;
    }

    public void setWaitingList(Registration passenger) {
        waitingList.add(passenger);
        waiting--;
    }

    public Registration getInnerMap(int id, long seatNo) {
       Registration reg= confirmedTickets.get(id).remove(seatNo);
        return reg;
    }

    public HashMap<Long,Registration> getMap(int id){
        return confirmedTickets.get(id);
    }
}
