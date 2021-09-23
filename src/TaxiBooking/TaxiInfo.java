package TaxiBooking;

public class TaxiInfo {
    private int taxiNo;
    private long totalEarnings;
    private char startPoint;
    private char endPoint;
    private int pickTime;
    private int dropTime;
    private int customerId;
    private long amount;
    private int bookingId;
    private int freeTime;

    public int getFreeTime() {
        return freeTime;
    }

    public void setFreeTime(int freeTime) {
        this.freeTime = freeTime;
    }

    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public int getTaxiNo() {
        return taxiNo;
    }

    public void setTaxiNo(int taxiNo) {
        this.taxiNo = taxiNo;
    }

    public long getTotalEarnings() {
        return totalEarnings;
    }

    public void setTotalEarnings(long totalEarnings) {
        this.totalEarnings = totalEarnings;
    }

    public char getStartPoint() {
        return startPoint;
    }

    public void setStartPoint(char startPoint) {
        this.startPoint = startPoint;
    }

    public char getEndPoint() {
        return endPoint;
    }

    public void setEndPoint(char endPoint) {
        this.endPoint = endPoint;
    }

    public int getPickTime() {
        return pickTime;
    }

    public void setPickTime(int pickTime) {
        this.pickTime = pickTime;
    }

    public int getDropTime() {
        return dropTime;
    }

    public void setDropTime(int dropTime) {
        this.dropTime = dropTime;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public String toString(){
        return taxiNo+"\t\t\t"+totalEarnings+"\t\t\t"+"\n"+""+bookingId+"\t\t\t"+startPoint+"\t\t\t"+endPoint+
                "\t\t\t"+pickTime+"\t\t\t"+dropTime+"\t\t\t"+amount;
    }
}
