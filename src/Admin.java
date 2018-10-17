import java.sql.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Admin {
	private String userName;
	private static String password;
	static Admin a=null;
	Connection con=ConnectDatabase.con;

	Scanner sc=new Scanner(System.in);
	
	//get password
	private boolean getPassword(String psw)
	{	try{
			
		Statement stmt=con.createStatement();
		ResultSet rs=stmt.executeQuery("select password from admin");
		
		while(rs.next()){
			password=rs.getString(1);
			}
		if(psw.equals(password))
			return true;
			
		
	
		
		}catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		return false;
	}
	private String getName()
	{	try{
			
		Statement stmt=con.createStatement();
		ResultSet rs=stmt.executeQuery("select name from admin");
		
		if(rs.next()){
			userName=rs.getString(1);				
		}
		return userName;
	
		
		}catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		return null;
	}
	
	//private constructor
	
	private Admin() {}
	
	//authenticating admin
	public static Admin getInstance(String psw){
			if(a==null) {
				a=new Admin();
				if(!a.getPassword(psw))
				{	System.out.println("Authentication failed");
					a=null;
				}
				}
			else
				if(!a.getPassword(psw))
				{
					System.out.println("Authentication failed");
					a=null;
				}

		
		return a;
	}
	public void setPassword(String password) {
		try{
			Statement stmt=con.createStatement();
			
			if(stmt.executeUpdate("update admin set password='+"+password+"'")>0){
				System.out.println("Updated successfully.");
			}else{
				System.out.println("Failed.");
			}
			
		}
		catch(Exception e){
			System.out.println(e.getMessage());
		}
	}
	
	public void deposit(int amount){
		
		try{
			Statement stmt=con.createStatement();
			
			if(stmt.executeUpdate("update admin set balance=balance+"+amount)>0){
				System.out.println("Updated Balance in Vending Machine Rs."+getBalance()+"");
			}else{
				System.out.println("Failed.");
			}
			
		}
		catch(Exception e){
			System.out.println(e.getMessage());
		}
		
	}
	
	public void withdrawl(int amount){
		
		try{
			Statement stmt=con.createStatement();
			System.out.println("Your available balance is: "+getBalance());
			if(amount>getBalance())
				{System.out.println("Input amount is greater than current balance");
				
				}
			else
			{
			if(stmt.executeUpdate("update admin set balance=balance-"+amount)>0){
				System.out.println("Remainig Balance\t"+getBalance()+"");
				 
			}else{
				System.out.println("Failed.");
			}
			}
			
		}
		catch(Exception e){
			System.out.println(e.getMessage());
		}
	}
	
	public void addItems(String tCode,int Quantity){
		try{
		
			Statement stmt=con.createStatement();
			String sql="update tray set availability=availability+"+Quantity +" where code='" + tCode + "'";
			if(stmt.executeUpdate(sql)>0){
				System.out.println("Inventory updated");
				 
			}else{
				System.out.println("Failed.");
			}

			}catch(Exception e)
			{

			}
		
	}
	
	public int getBalance(){

		int tBalance=0;
		try{
			Statement stmt=con.createStatement();
			ResultSet rs=stmt.executeQuery("select balance from admin");
			if(rs.next()){
				tBalance=rs.getInt(1);				
			}
		
			//con.close();
		return tBalance;
			
		}catch(Exception e){
			
			System.out.println(e.getMessage());
		}
		
		return tBalance;
	
	}
	public void Input() throws InvalidEntryException
	{	char i='y';
		String k="";
		try{
			do{
			System.out.println("Total Balance in Vending Machine Rs."+getBalance());
			System.out.println("Admin Menu\n"
							+ "1.Withdrawl\n"
							+ "2.Deposit\n"
							+ "3.Update Inventory Items\n");
			System.out.println("Enter your choice (1,2,3)");
			
			k=sc.next();
			if(!k.matches("[0-9]{1}")){
				System.out.println("Enter a valid input");
				k=sc.next();
			}
				
			
			switch(k)
			{	case "1": System.out.println("Enter the amount to withdraw");
						int wit=sc.nextInt();
						withdrawl(wit);
						break;
				case "2": System.out.println("Enter the amount to deposit");
						int dep=sc.nextInt();
						deposit(dep);
						break;
				case "3":		Tray.getInstance().display();
					System.out.println("Enter Tray code");
						String tCode=sc.next().toUpperCase();
						System.out.println("Enter Quantity");
						int Quantity=sc.nextInt();
						addItems(tCode,Quantity);
						break;
				default:System.out.println("Invalid choice");
				
			}
			System.out.println("Do you want to perform operation again (Y/N)");
			i=sc.next().toUpperCase().charAt(0);
			}while(i=='Y'||i=='y');
		}catch(InputMismatchException e){
			System.out.println("Robot Detected");
		}
	}
}
