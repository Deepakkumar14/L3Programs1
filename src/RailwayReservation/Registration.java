package RailwayReservation;

public class Registration {
    private long seatNo;
    private String name;
    private int age;
    private String gender;
    private String birthPref;
    private String birth;
    private String status;
    private char child;
    private int ticketId;

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public long getSeatNo() {
        return seatNo;
    }

    public void setSeatNo(long seatNo) {
        this.seatNo = seatNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBirthPref() {
        return birthPref;
    }

    public void setBirthPref(String birthPref) {
        this.birthPref = birthPref;
    }

    public char getChild() {
        return child;
    }

    public void setChild(char child) {
        this.child = child;
    }

    public int getTicketId() {
        return ticketId;
    }

    public void setTicketId(int ticketId) {
        this.ticketId = ticketId;
    }

    @Override
    public String toString() {
        return "Registration{" +
                "seatNo=" + seatNo +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", gender='" + gender + '\'' +
                ", birth='" + birth + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
