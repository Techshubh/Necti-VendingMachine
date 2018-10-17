
public class Coin extends Currency{
	private static int radius;
	private static int weight;
	static Currency c;
	
	public static Currency getInstance(int radius1,int weight1) {
		radius=radius1;
		weight=weight1;		
		return new Coin();		
	}

	@Override
	public int value() {
		if(radius==1 && weight==10) {
			amount=5;
			return amount;
		}
		else if(radius == 1 && weight == 20) {
			amount=10;
			return amount;
		}
		
		
		return -1;
	}
	
}
