package ZCoin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Queue;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum LogicalHandler {
    OBJECT;
     private static long ZId=100;
    public String passwordValidator(String password){
        String regex = "^(?=.*[0-9])" + "(?=.*[a-z])(?=.*[A-Z])" + "(?=.*[@#$%^&+=])"
                + "(?=\\S+$).{8,20}$";

        Pattern p = Pattern.compile(regex);
        if (password == null) {
            return "Password cannot be null";
        }

        Matcher m = p.matcher(password);
        if(m.matches())
            return "true";
        else
            return"Invalid mail id";
    }
    public String emailValidator(String email){
        String regex = "^[A-Za-z0-9+_.-]+@(.+)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        if(matcher.matches())
            return "true";
        else
            return"Invalid mail id";
    }

    public String approval(UserCredentials list, int choice) {
            if (choice == 1) {
               list.setZID(ZId);
                ZId++;
                CacheMemory.INSTANCE.setUser(list);
                return "Registered successfully";
        }
        return "your registration is rejected check the values you have entered";
    }

    public String loginApproval(String mailId, String password) {
        HashMap<String,UserCredentials> userMap=CacheMemory.INSTANCE.returnMap();
        if(userMap.containsKey(mailId)){
            UserCredentials user=userMap.get(mailId);
            if(password.equals(user.getPassword())){
                return "true";
            }
            else
                return "password is wrong";
        }
        else
            return "invalid mail id";
    }
    public String employeeApproval(String mailId, String password) {
        ZEmployeeCredentials ZE = CacheMemory.INSTANCE.returnEmployee();
        if (mailId.equals(ZE.getMailId())) {
            if (password.equals(ZE.getPassword())) {
                return "true";
            } else
                return "password is wrong";
        } else
            return "invalid mail id";
    }

    public void setUserList(UserCredentials user){
        CacheMemory.INSTANCE.setList(user);
    }

    public Queue<UserCredentials> getUserList(){
        return CacheMemory.INSTANCE.getList();
    }

    public UserCredentials getUserValue(String mailId){
        return CacheMemory.INSTANCE.getUserValue(mailId);
    }

    public void clearList() {
        CacheMemory.INSTANCE.clearList();
    }

    public String deposit(long zid, double amount, String mailId) {
       UserCredentials user= CacheMemory.INSTANCE.getUserValue(mailId);
       double totalAmount=user.getRCAmount();
       totalAmount+=amount;
       String output="Deposit of amount  "+totalAmount;
       user.setRCAmount(totalAmount);
       boolean result=CacheMemory.INSTANCE.setTransactionDetails(output,mailId,zid,user);
       if(result){
           return "Deposit of amount  "+totalAmount+"  is successful";
       }
       return "Transaction failed !!please try again later";
    }

    public String Withdraw(long zid, double amount, String mailId) {
        UserCredentials user = CacheMemory.INSTANCE.getUserValue(mailId);
        double totalAmount = user.getRCAmount();
        if (totalAmount < amount) {
            return "Insufficient balance .Your balance is " + totalAmount;
        } else {
            totalAmount -= amount;
            String output = "Withdrawal of amount  " + totalAmount;
            user.setRCAmount(totalAmount);
            boolean result=CacheMemory.INSTANCE.setTransactionDetails(output,mailId,zid,user);
            if(result){
                return "Withdrawal of amount  "+totalAmount+"  is successful";
            }
            return "Transaction failed !!please try again later";
        }
    }

    public boolean setZCoinRate(int value){
       boolean bool= CacheMemory.INSTANCE.setZCoinRate(value);
       return bool;
    }

    public String RCToZCoin(long zid, double amount, String mailId) {
        int rate=CacheMemory.INSTANCE.getZCoinRate();
        UserCredentials user = CacheMemory.INSTANCE.getUserValue(mailId);
        double totalAmount = user.getRCAmount();
        double ZCoin=user.getZCoin();
        if(totalAmount-amount>0){
            ZCoin+=amount/rate;
            totalAmount-=amount;
            user.setRCAmount(totalAmount);
            user.setZCoin(ZCoin);
            String output="Conversion of RC to ZC for  amount "+amount;
            boolean result=CacheMemory.INSTANCE.setTransactionDetails(output,mailId,zid,user);
            if(result){
                return "Conversion of RC to ZC for  amount  "+amount+"  is successful.your balance is "+totalAmount +"ZCoin balance is"+ZCoin;
            }
            return "Transaction failed !!please try again later";
        }else
            return "Insufficient balance .Your balance is " + totalAmount;
        }

    public String accountTransfer(long zid, long zid1, double amount, String mailId) {
        UserCredentials user = CacheMemory.INSTANCE.getUserValue(mailId);
        String mailId1=CacheMemory.INSTANCE.getMailId(zid1);
        UserCredentials user1 = CacheMemory.INSTANCE.getUserValue(mailId1);
        if(user.getZCoin()!=0 ) {
            double ZCoinR = user1.getZCoin();
            if(user.getZCoin()!=0) {
            double totalAmount = user.getZCoin();
            ZCoinR += amount;
            if (totalAmount < amount) {
                return "Insufficient balance .Your balance is " + totalAmount;
            } else {
                totalAmount -= amount;
                user.setZCoin(totalAmount);
                user.setZCoin(ZCoinR);
                String output = "Transfer of ZCoin amount  " + totalAmount + "to " + zid1;
                String output1 = "Deposit of ZCoin from  " + ZId + "of value " + totalAmount;
                boolean result = CacheMemory.INSTANCE.setAccountTransfer(output, mailId, zid, user, output1, zid1, mailId1, user1);
                if (result) {
                    return "Transfer of ZCoin amount  " + totalAmount + "  is successful";
                }
                return "Transaction failed !!please try again later";
            }

            }else
                return "ZCoin value of receiver is null";
        }else
            return "ZCoin value of sender is null";
    }

    public String ZCoinToRC(long zid, double amount, String mailId) {
        int rate=CacheMemory.INSTANCE.getZCoinRate();
        UserCredentials user = CacheMemory.INSTANCE.getUserValue(mailId);
        double totalAmount = user.getRCAmount();
        double ZCoin=user.getZCoin();
        if(ZCoin-amount>0){
            ZCoin-=amount;
            user.setZCoin(ZCoin);
            totalAmount+=(amount*rate)-(0.0015);
            user.setRCAmount(totalAmount);
            String output="Conversion of ZC to RC for  amount "+amount;

            boolean result=CacheMemory.INSTANCE.setTransactionDetails(output,mailId,zid,user);
            if(result){
                return "Conversion of ZC to RC for  amount  "+amount+"  is successful.your balance is "+totalAmount +"  ZCoin balance is  "+ZCoin;
            }
            return "Transaction failed !!please try again later";
        }else
            return "Insufficient balance .Your balance is " + ZCoin;
    }

    public String changePassword(String password, String mail){
        UserCredentials user = CacheMemory.INSTANCE.getUserValue(mail);
        user.setPassword(password);
        CacheMemory.INSTANCE.setUser(user);
        return "Password changed successfully";
    }

    public ArrayList<String> transactionHistory(double ZID){
        return CacheMemory.INSTANCE.getTransactionHistory(ZID);
    }
}


