package ZCoin;

public class UserCredentials {
    private String name;
    private String mailId;
    private long mobile;
    private int HID;
    private String password;
    private double RCAmount;
    private double ZCoin;
    private long ZID;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMailId() {
        return mailId;
    }

    public void setMailId(String mailId) {
        this.mailId = mailId;
    }

    public long getMobile() {
        return mobile;
    }

    public void setMobile(long mobile) {
        this.mobile = mobile;
    }

    public int getHID() {
        return HID;
    }

    public void setHID(int HID) {
        this.HID = HID;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public double getRCAmount() {
        return RCAmount;
    }

    public void setRCAmount(double RCAmount) {
        this.RCAmount = RCAmount;
    }

    public double getZCoin() {
        return ZCoin;
    }

    public void setZCoin(double ZCoin) {
        this.ZCoin = ZCoin;
    }

    public long getZID() {
        return ZID;
    }

    public void setZID(long ZID) {
        this.ZID = ZID;
    }
@Override
public String toString(){
   return  "Name "+getName()+"\n"+"Mobile "+getMobile()+"\n"+"MailId "+getMailId()+"\n"+"HID "+getHID()+"\n"+"RC amount "+getRCAmount()+"\n"+"ZCoin "+getZCoin()+"\n";
}
}
