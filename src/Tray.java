import java.sql.*;


public class Tray{
	//amount entered by user
	int price;
	String code;
	String product_name;
	int availability;
	Connection con=ConnectDatabase.con;
	//fetching product details 

	public void retrieve (String code)throws Exception{
		try{
		Statement stmt=con.createStatement();
		ResultSet rs=stmt.executeQuery("select * from tray where code='" + code + "'");
		if(rs.next()){
			price=rs.getInt(3);
			this.code=code;
			product_name=rs.getString(2);
			availability=rs.getInt(4);
			
		}
		else
			throw new InvalidProductCodeException("invalid product code");
		}
	catch(Exception e){
		System.out.println(e);
	}
}
		
	//dispensing item
	public boolean drop(int amount) throws ProductNotFoundException{
		try{
		if(price<=amount){
			if(availability>=1)
			{System.out.println(product_name+" dropped..Please collect your item..");
			String query = "update tray set availability = ? where code = ?";
		      PreparedStatement preparedStmt = con.prepareStatement(query);
		      availability-=1;
		      preparedStmt.setInt(1,availability);
		      preparedStmt.setString(2,code);
		      preparedStmt.executeUpdate();
		      Statement stmt=con.createStatement();
				stmt.executeUpdate("update admin set balance=balance+"+price);
		      //System.out.println("updated");
		      return true;
		      }
			else
			{
				throw new ProductNotFoundException("Inventory is empty for the product..");
				//System.out.println("Inventry is empty for the product..");
				//return false;
			}
		}
		else{
			throw new InvalidAmountException("Invalid amount..");
			//System.out.println("Invalid amount..");
			//return false;
		}
		}catch(Exception e){
			System.out.println(e);
		}
		return false;
	
	}
	//adding product to the inventory
	public void addProduct(String code,int count){
		try{
			  String query = "update tray set availability = ? where code = ?";
		      PreparedStatement preparedStmt = con.prepareStatement(query);
		      count=count+availability;
		      preparedStmt.setInt(1,count );
		      preparedStmt.setString(2,code);

		      // execute the java preparedstatement
		      preparedStmt.executeUpdate();
		      con.close();
		}catch(Exception e){
			System.out.println(e);
		}
	}
	public void display(){
		try{
			Statement stmt=con.createStatement();
			ResultSet rs=stmt.executeQuery("select * from tray");
			System.out.format("%10s %30s %5s %5s\n","Code","Product Name","Price","Availability");
			while(rs.next()){
				System.out.format("%10s %30s %5d %5d\n",rs.getString(1),rs.getString(2).toUpperCase(),rs.getInt(3),rs.getInt(4));
				
			}
			}
		catch(Exception e){
			System.out.println(e);
		}
	}
	public static Tray getInstance(){
		return new Tray();
	}
	
}