package socks;

import java.util.ArrayList;

public class Share extends Methods{
	public static Stocks data;
	
	public static double reserveMoney = 1.0;
	public static double initialTestMoney = 1.0;
	public static boolean test;

	public ArrayList<String> transactions = new ArrayList<String>();
	
	public double initialInvestments = 0;
	public double moneyInvested = 0;
	public double moneyCollected = 0;
	public double profit = 0;
	
	public Stock lastUpdatedStock;
	
	public Share(String file) {
		data = new Stocks(file);
	}
	
	public void reset() {
		reserveMoney = 1.0;
		initialInvestments = 0;
		moneyInvested = 0;
		moneyCollected = 0;
		profit = 0;
		lastUpdatedStock = null;
		transactions = new ArrayList<String>();
	}
	
	public void update(int day) {
		moneyInvested *= data.get(day).openPrice/lastUpdatedStock.openPrice;
		lastUpdatedStock = data.get(day);
	}
	
	public double netProfit() {
		return moneyCollected + moneyInvested - initialInvestments;
	}
	
	public void buy(int day, double price) {
		if(price <= reserveMoney) {
			if(lastUpdatedStock == null) {
				lastUpdatedStock = data.get(day);
			}
			update(day);
			transactions.add(new String("Bought: " + data.get(day).date + " --> $-" + price));
			initialInvestments += price;
			moneyInvested += price;
			reserveMoney -= price;
		}else {
			System.out.println("You don't have enough money to buy $" + price + "!");
		}
	}
	
	
	public void sell(int day, double price) {
		if(moneyInvested > 0) {
			update(day);
			if(price <= moneyInvested) {
				transactions.add(new String("Sold: " + data.get(day).date + " --> $+" + price));
				moneyInvested -= price;
				moneyCollected += price;
				reserveMoney += price;
			}else {
				System.out.println("You don't have enough stocks to sell $" + price + "!");
			}
		}else if(!test){
			System.out.println("You have no stocks to sell!");
		}
	}
	
	public void sell(int day) {
		if(moneyInvested > 0) {
			update(day);
			transactions.add(new String("Sold: " + data.get(day).date + " --> $+" + moneyInvested));
			moneyCollected += moneyInvested;
			reserveMoney += moneyInvested;
			moneyInvested = 0;
		}else if(!test){
			System.out.println("You have no stocks to sell!");
		}
	}
	
	
	public String toString() {
		if(test) {
			return"Bank: " + round(reserveMoney) + "\nTotal money invested: " + round(initialInvestments) + "\nTotal money collected: " + round(moneyCollected)
			+ "\nTotal money stored: " + round(moneyInvested) + "\nNet Profit: $" + round(netProfit());
		}else {
			return"Bank: " + round(reserveMoney) + "\nTransactions: " + transactions.toString() + "\nTotal money invested: " + round(initialInvestments) + "\nTotal money collected: " + round(moneyCollected)
			+ "\nTotal money stored: " + round(moneyInvested) + "\nNet Profit: $" + round(netProfit());
		}
	}
	
	public static void main(String[] args) {
		Share s = new Share(".\\\\^IXIC.csv");
		s.buy(0,1);
		s.sell(2000);
		s.buy(2001, reserveMoney);
		s.sell(2002);
		System.out.println(s);
	}

}
