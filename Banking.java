package projects;
import java.util.*;
import java.sql.*;
class bankop{
	private int accno;
	private String name;
	private static double amount;
	private static  double balance;
	private int pin;
	private static ArrayList<bankop>account=new ArrayList<bankop>();
	static Scanner sc=new Scanner(System.in);
	public bankop(int accno, String name, double amount,  int pin) {
		super();
		this.accno = accno;
		this.name = name;
		this.amount = amount;
//		this.balance = balance;
		this.pin = pin;
	}
	public int getAccno() {
		return accno;
	}
	public void setAccno(int accno) {
		this.accno = accno;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
//	public double getBalance() {
//		return balance;
//	}
//	public void setBalance(double balance) {
//		this.balance = balance;
//	}
	public int getPin() {
		return pin;
	}
	public void setPin(int pin) {
		this.pin = pin;
	}
   
	public static void addaccount(bankop acc) {
		account.add(acc);
	}
   
	public static void deposite() {
		System.out.println("enter the amount to deposite");
		double amt=sc.nextInt();
		if(amt>0) {
			balance=balance+amt;
			System.out.println("successfully deposited");
		}else {
			System.out.println("invalid amount");
		}
	}
	public static void withdraw() {
		System.out.println("enter the amount to withdraw");
		double amt=sc.nextDouble();
		if(amt>0 && amt<=balance) {
			balance=balance-amt;
			System.out.println("successful withdrawel.remaining balance:"+balance);
		}else {
			System.out.println("invalid");
		}
	}
	public static void adminvalidation() {
		
		System.out.println("enter the Account pin");
		int p=sc.nextInt();
		for(bankop acc:account) {
			if(acc.getPin()==p  ) {
			System.out.println("The account name is:"+acc.getName()+",The amount is:"+acc.getAmount());
			}
		}
	}
	public static void accountStatement() {
		System.out.println("enter the Account pin");
		int p=sc.nextInt();
		for(bankop acc:account) {
			if(acc.getPin()==p  ) {
        System.out.println("\nAccount Statement:");
        System.out.println("Name: " + acc.getName());
        System.out.println("Account Number: " + acc.getAccno());
        System.out.println("Balance: $" + acc.getAmount());
    }
		}
	}
	public static void transfer() {
	    System.out.println("Enter your account number:");
	    int senderAccNo = sc.nextInt();
	    System.out.println("Enter the target account number:");
	    int targetAccNo = sc.nextInt();
	    
	    bankop sender = null;
	    bankop receiver = null;
	    
	    for (bankop acc : account) {
	        if (acc.getAccno() == senderAccNo) {
	            sender = acc;
	        }
	        if (acc.getAccno() == targetAccNo) {
	            receiver = acc;
	        }
	    }
	    
	  
	    if (sender == null) {
	        System.out.println("Sender account not found.");
	        return;
	    }
	    if (receiver == null) {
	        System.out.println("Target account not found.");
	        return;
	    }
	    
	  
	    System.out.println("Enter the amount to transfer:");
	    double amt = sc.nextDouble();
	    if (amt > 0 && amt <= sender.getAmount()) {
	        sender.setAmount(sender.getAmount() - amt); 
	        receiver.setAmount(receiver.getAmount() + amt); 
	        System.out.println("Transfer successful!");
	        System.out.println("Remaining balance in your account: $" + sender.getAmount());
	    } else {
	        System.out.println("Invalid amount or insufficient balance.");
	    }
	}

	
	public static void login() {
		System.out.println("enter the Account number");
		int n=sc.nextInt();
		System.out.println("enter the Account pin");
		int p=sc.nextInt();
		
		for(bankop acc:account) {
			if(acc.getAccno()==n && acc.getPin()==p  ) {
				System.out.println("successful login!");
				boolean exit=false;
				while(!exit) {
			    System.out.println("\n1.admin validation");
				System.out.println("2.deposite  Amount");
				System.out.println("3.withdraw  Amount");
				System.out.println("4.transfer Amount");
				System.out.println("5.account statement");
				System.out.println("6.exit");
				System.out.println("Select the option ");
				int op=sc.nextInt();
				switch(op) {
				case 1:adminvalidation();break;
				case 2:deposite();break;
				case 3:withdraw();break;
				case 4:transfer();break;
				case 5:accountStatement();break;
				case 6:System.out.println("**THANK YOU NOW YOU CAN LEAVE**");exit=false;break;
				default:System.out.println("Invalid! select other option ");
				}
			}
			}
		}
		
		
	}
	
}
public class Banking {

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		System.out.println("$$ WELCOME TO THE BANKING SERVICE $$");
		boolean exit=true;
		while(exit) {
			System.out.println("\n1.create new account");
			System.out.println("2.Account login");
			
			System.out.println("select the option");
			int op=sc.nextInt();
			switch(op) {
			case 1:System.out.println("enter the Account Number");
			       int accno=sc.nextInt();
			       sc.nextLine();
			       System.out.println("enter the account holder name:");
			       String name=sc.nextLine();
			       System.out.println("enter the amount");
			       double amount=sc.nextDouble();
			       System.out.println("enter the pin");
			       int pin=sc.nextInt();
			       bankop.addaccount(new bankop(accno,name,amount,pin));
			       System.out.println("Account created succesfully");break;
			case 2:bankop.login();
			       
			}
		}

	}

}
