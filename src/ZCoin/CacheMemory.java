package ZCoin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public enum CacheMemory {
    INSTANCE;

  private  static  HashMap<String,UserCredentials> userMap=new HashMap<>();
  private static  ZEmployeeCredentials ZECred=new ZEmployeeCredentials();
    private static  Queue<UserCredentials> list=new LinkedList<>();
    private static HashMap<Long, ArrayList<String>> transactions=new HashMap<>();
    private static HashMap<Long,String> tempMap=new HashMap<>();
    private  int ZCoin=2;

    public  void setUser(UserCredentials user){
        userMap.put(user.getMailId(), user);
        tempMap.put( user.getZID(),user.getMailId());
    }

    public  HashMap<String,UserCredentials> returnMap(){
        return userMap;
    }

    public  void setZEValue(String mail,String password){
        ZECred.setMailId(mail);
        ZECred.setPassword(password);
    }

    public  ZEmployeeCredentials returnEmployee(){
        return ZECred;
    }

    public void setList(UserCredentials user){
        list.add(user);
    }

    public Queue<UserCredentials> getList(){
        return list;
    }

    public UserCredentials getUserValue(String mailId){
        UserCredentials user=userMap.get(mailId);
        return  user;
    }


    public void clearList() {
        list.clear();
    }

    public boolean setTransactionDetails(String output, String mailId, long zid, UserCredentials user) {
       userMap.put(mailId,user);
       ArrayList<String> out=transactions.getOrDefault(zid,new ArrayList<String>());
       out.add(output);
       transactions.put(zid,out);
        return true;
    }

    public boolean setZCoinRate(int value){
        ZCoin=value;
        return true;
    }

    public int getZCoinRate(){
        return ZCoin;
    }

    public ArrayList<String> getTransactionHistory(double zid) {
        return transactions.get(zid);
    }

    public String getMailId(long zid1) {
        return tempMap.get(zid1);
    }

    public boolean setAccountTransfer(String output, String mailId, long zid, UserCredentials user, String output1, long zid1, String mailId1, UserCredentials user1) {
        userMap.put(mailId,user);
        ArrayList<String> out=transactions.getOrDefault(zid,new ArrayList<String>());
        out.add(output);
        transactions.put(zid,out);
        userMap.put(mailId1,user1);
        ArrayList<String> out1=transactions.getOrDefault(zid1,new ArrayList<String>());
        out1.add(output1);
        transactions.put(zid,out1);
        return true;
    }
}
