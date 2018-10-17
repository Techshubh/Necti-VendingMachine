/** 
 * This project replicate the functioning of vending machine
 * @author Shubham Singhal
 * @author Kunwar Dheerendra
 * @author Tarun Sikka
 * @author Madhu
 * @since 20 september 2018
 * */
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;
class VendingMachine
{	private static String type;
	private static String visitor;
	private static  int p1;
	private static  int p2;
	static int deposited;
	static int balance;
	private static int due=0;
	private static char add='y';
	private static String proCode;
	
//main function
	public static void main(String args[])throws Exception{
		Tray tr=null;
		Scanner sc=new Scanner(System.in);
		ConnectDatabase.connect();
		Connection con=ConnectDatabase.con;
		tr=Tray.getInstance();
		try{
			Statement stmt=con.createStatement();
			ResultSet rs=stmt.executeQuery("select balance from admin");
			if(rs.next()){
				balance=rs.getInt(1);				
			}
		//return tBalance;
			
		}catch(Exception e){
			
			System.out.println(e.getMessage());
		}
		//Scanner sc=new Scanner(System.in);
		System.out.println("Admin/User");
		visitor=sc.next();
		if(visitor.equalsIgnoreCase("User"))
		{
			
			System.out.println("Product details");
			//Table with code
			tr.display();
			User user=User.getInstance();
				user.input();
			deposited=user.deposited;
			
			System.out.println("Enter Product code");
			proCode=sc.next();
			proCode=proCode.toUpperCase();
			try{
				
				tr.retrieve(proCode);
				due=deposited-tr.price;
				if(due>balance){
				System.out.println("Insufficient balance for return");	
				}
				else if(tr.drop(deposited))
				{
					
					System.out.println("Deducted money: "+tr.price);
					System.out.println("Collect remaining balance of Rs. "+due);
				}
				else{
					due=deposited;
					System.out.println("Collect remaining balance of Rs. "+due);
				}
				System.out.println("Thanks for using vending machine\n\n\n\n");
			}catch(InvalidProductCodeException ex1)
			{
				System.out.println(ex1.getMessage());
			}
			catch(ProductNotFoundException e)
			{
				System.out.println(e.getMessage());
			}catch(InvalidAmountException ex)
			{
				System.out.println(ex.getMessage());
			}

		}
		else if(visitor.equalsIgnoreCase("Admin"))
		{
			//Admin admin= Admin.getInstance();
			System.out.println("Admin entered");
			Admin a1=null;
			
			do{
				
				System.out.println("Enter the admin password");
				String p=sc.next();
				a1=Admin.getInstance(p);
				if(a1!=null)
					{
					a1.Input();
					
					}
			}while(a1==null);
		
			a1=null;
			System.out.println("Logged out..........\n\n\n");
		}
		
		else
			throw new InvalidEntryException("Please enter as user or admin");
		while(true){
			//String[] a={};
			main(null);
		}
	}
}