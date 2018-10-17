import java.sql.*;
import java.sql.DriverManager;
import java.sql.Statement;


public class ConnectDatabase{
	static Connection con;
	//public static ConnectDatabse(){
		
	//}
	public static void connect(){
	try{
		Class.forName("oracle.jdbc.driver.OracleDriver");
		con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:necti","hr","hr");
		
		//Statement stmt=con.createStatement();
		//con.close();
	}catch(Exception e){
		System.out.println(e);
	}
	}
}
