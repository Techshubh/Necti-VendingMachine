import java.util.Scanner;


public class User {
	private static String type;
	private static  int p1;
	private static  int p2;
	int deposited=0;
	private static char add='y';
	static Scanner sc=new Scanner(System.in);
	public void input(){
		while(add=='y'||add=='Y')
		{
		System.out.println("Enter type of input(Coin/Note)");
		type=sc.next();

		if(type.equalsIgnoreCase("Coin"))
		{
		System.out.println("enter radius & weight\nfor 5 rs. enter (1 10)\nfor 10 rs. enter (1 20)");
		p1=sc.nextInt();
		p2=sc.nextInt();
		Currency c=Coin.getInstance(p1,p2);//init here
		if(c.value()!=-1)
			deposited+=c.value();
		else
			System.out.println("fake coin");

		}
		else if(type.equalsIgnoreCase("note"))
		{
		System.out.println("enter length & width\nfor 5 rs. enter(6 3)\nfor 10 rs. enter (7 4)\nfor 20 rs. enter (8 4)");
		p1=sc.nextInt();
		p2=sc.nextInt();
		Currency c=Note.getInstance(p1,p2);
		if(c.value()!=-1)
			deposited+=c.value();
		else
			System.out.println("fake note");
		}
		System.out.println("Total money deposited: "+deposited);
		System.out.println("Do you want to add more money(Y/N)");
		add=sc.next().charAt(0);
		}
	}
	public static User getInstance(){
		return new User();
	}
}
