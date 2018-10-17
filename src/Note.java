public class Note extends Currency{
	
	private static int length;
	private static int width;
	
	
	public static Currency getInstance(int length1,int width1) {
		length=length1;
		width=width1;
		return new Note();
	}
	
	@Override
	public int value() {
		if(length==6 && width==3) {
			amount=5;
			return amount;
		}
		else if(length==7 && width==4) {
			amount=10;
			return amount;
		}
		else if(length==8 && width==4) {
			amount=20;
			return amount;
		}
		
		return -1;
	}
	
}