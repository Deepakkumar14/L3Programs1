package ZCoin;

import java.util.ArrayList;
import java.util.Queue;
import java.util.Scanner;

public class UserInterface {
    public Scanner input = new Scanner(System.in);
    //private UserCredentials user = new UserCredentials();
    private LogicalHandler logic = LogicalHandler.OBJECT;

    public static void main(String[] args) {
        String mail = "deepak@gmail.com";
        String password = "Deep@1234";
        CacheMemory.INSTANCE.setZEValue(mail, password);
        new UserInterface().login();
    }

    public void login() {
        int choice = 0;
        System.out.println("Welcome to ZCoin Login page");
        System.out.println("Enter 1 for Register");
        System.out.println("Enter 2 for Login ");
        System.out.println("Enter 3 to close");
        choice = input.nextInt();
        input.nextLine();
        switch (choice) {
            case 1: {
                UserCredentials user = new UserCredentials();
                System.out.println("Enter name");
                user.setName(input.nextLine());
                System.out.println("Enter MailId");
                user.setMailId(input.nextLine());
                System.out.println("Enter Mobile number");
                user.setMobile(input.nextInt());
                System.out.println("Enter human Id");
                user.setHID(input.nextInt());
                input.nextLine();
                System.out.println("Enter Password");
                System.out.println("Password must contain 8 characters.Must use number and special Character");
                String password = input.nextLine();
                while (logic.passwordValidator(password) != "true") {
                    System.out.println("Enter password again.Password must contain 8 characters.Must use number and special Character");
                    System.out.println("Enter the new password");
                    password = input.nextLine();
                }
                user.setPassword(password);
                System.out.println("Enter Real Amount");
                user.setRCAmount(input.nextDouble());
                logic.setUserList(user);
                login();
                break;
            }
            case 2: {
                System.out.println("1.User Login\n2.ZEmployee login");
                choice = input.nextInt();
                input.nextLine();
                switch (choice) {
                    case 1: {
                        System.out.println("Enter MailId");
                        String mailId = input.nextLine();
                        System.out.println("Enter Password");
                        String password = input.nextLine();
                        String output = logic.loginApproval(mailId, password);
                        if (output.equals("true")) {
                            UserCredentials user = logic.getUserValue(mailId);
                            System.out.println(user);
                            transactions(mailId,user);
                        } else
                            System.out.println(output);
                        login();
                        break;
                    }
                    case 2: {
                        System.out.println("Enter MailId");
                        String mailId = input.nextLine();
                        System.out.println("Enter Password");
                        String password = input.nextLine();
                        String output = logic.employeeApproval(mailId, password);
                        if (output.equals("true")) {
                            employee(mailId);
                        }
                        else
                            System.out.println(output);
                        login();
                        break;
                    }
                }

            }
            case 3: {
                break;
            }
            default: {
                System.out.println("Invalid option");
                login();
                break;
            }
        }
    }

    public void transactions(String mailId, UserCredentials user) {
        int choice = 0;
        System.out.println("1.RC Transactions");
        System.out.println("2.ZC Transactions");
        System.out.println("3.Change Password");
        System.out.println("4.Transaction History");
        System.out.println("5.Exit");
        choice = input.nextInt();
        switch (choice) {
            case 1: {
                System.out.println("1.Deposit");
                System.out.println("2.Withdraw");
                System.out.println("3.Rc to ZCoin");
                System.out.println("4.Balance");
                choice = input.nextInt();
                switch (choice) {
                    case 1: {
                        System.out.println("Enter Amount to Deposit");
                        double amount = input.nextDouble();
                        String result = logic.deposit(user.getZID(), amount, mailId);
                        System.out.println(result);
                        transactions(mailId, user);
                        break;
                    }
                    case 2: {
                        System.out.println("Enter Amount to Withdraw");
                        double amount = input.nextDouble();
                        String result = logic.Withdraw(user.getZID(), amount, mailId);
                        System.out.println(result);
                        transactions(mailId, user);
                        break;
                    }
                    case 3: {
                        System.out.println("Enter RC amount to be Converted to ZCoin");
                        double amount = input.nextDouble();
                        String result = logic.RCToZCoin(user.getZID(), amount, mailId);
                        System.out.println(result);
                        transactions(mailId, user);
                        break;
                    }
                    case 4: {
                        UserCredentials result = logic.getUserValue(mailId);
                        System.out.println("RC Balance is  " + result.getRCAmount());
                        System.out.println("ZCoin Balance is  " + result.getZCoin());
                        transactions(mailId, user);
                        break;
                    }
                    default: {
                        System.out.println("Invalid Input");
                        transactions(mailId, user);
                        break;
                    }
                }
            }
            case 2: {
                System.out.println("1.Account to Account Transaction");
                System.out.println("2.ZCoin to RC");
                choice = input.nextInt();
                switch (choice) {
                    case 1: {
                        System.out.println("Enter ZID of receiver");
                        long ZID1 = input.nextLong();
                        System.out.println("Enter ZCoin to transfer");
                        double amount = input.nextDouble();
                        String result = logic.accountTransfer(user.getZID(), ZID1, amount, mailId);
                        System.out.println(result);
                        transactions(mailId, user);
                        break;
                    }
                    case 2: {
                        System.out.println("Enter ZCoin amount to be Converted to  RC");
                        double amount = input.nextDouble();
                        String result = logic.ZCoinToRC(user.getZID(), amount, mailId);
                        System.out.println(result);
                        transactions(mailId, user);
                        break;
                    }
                    default: {
                        System.out.println("Invalid Input");
                        transactions(mailId, user);
                        break;
                    }

                }
            }
            case 3: {
                System.out.println("Enter the new password");
                String password = input.nextLine();
                while (logic.passwordValidator(password) != "true") {
                    System.out.println("Enter password again.Password must contain 8 characters.\n Must use number and special Character");
                    System.out.println("Enter the new password");
                    password = input.nextLine();
                }
                String output = logic.changePassword(mailId, password);
                System.out.println(output);
                transactions(mailId, user);
                break;
            }
            case 4: {
                ArrayList<String> list = logic.transactionHistory(user.getZID());
                System.out.println(list);
                transactions(mailId, user);
                break;
            }
            case 5: {
                break;
            }
            default: {
                System.out.println("Invalid Input");
                transactions(mailId, user);
                break;
            }
        }
    }

    public void employee(String mailId) {
        System.out.println("1.Approval or Rejection");
        System.out.println("2.Set ZCoin rate");
        System.out.println("3.Transaction history");
        System.out.println("4.Exit");
        int choice =0;
        choice=input.nextInt();
        switch (choice) {
            case 1: {
                Queue<UserCredentials> list = logic.getUserList();
                while (list.size() != 0) {
                    UserCredentials user = list.poll();
                    System.out.println(user);
                    System.out.println("Enter 1 for Approval\n2 for Reject");
                    choice = new UserInterface().input.nextInt();
                    String output1 = logic.approval(user, choice);
                    System.out.println(output1);
                }
                logic.clearList();
                employee(mailId);
                break;
            }
            case 2:{
                System.out.println("Enter the ZCoin Rate");
                int rate=input.nextInt();
                if(logic.setZCoinRate(rate))
                    System.out.println("ZCoin rate Changed");
                else
                    System.out.println("Not changed");
                employee(mailId);
                break;
            }
            case 3:{
                System.out.println("Enter ZID to check the transaction details");
                long ZID = input.nextLong();
                ArrayList<String> list = logic.transactionHistory(ZID);
                System.out.println(list);
                employee(mailId);
                break;
            }
            case 4:{
                break;
            }
            default: {
                System.out.println("Invalid Input");
               employee(mailId);
                break;
            }
        }
    }
}


//deepak
//        deepak@gmail.com
//
//        23324334
//
//        3
//        Deep@2000
//        32434543
//        dhatchu
//
//        dhatchu@gmail.com
//
//        234324
//
//        4
//        Dhatchu@123
//        2323412