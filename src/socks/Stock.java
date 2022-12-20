package socks;

public class Stock{
	public String date;
	public double openPrice;
	public double closePrice;
	public double volume;
	
	public static double moneyReserved = 1;
	public static double moneyInvested;
	public double profit;
	
	public Stock(String d, double o, double c, double v) {
		date = d;
		openPrice = o;
		closePrice = c;
		volume = v;
	}

}
//sad