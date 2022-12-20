package socks;

import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;

import javax.swing.JFrame;

public class ManageStocks extends Share{
	//run this one
	
	public ManageStocks(String file) {
		super(file);
	}
	
	
	
	public static void main(String[] args) {
		IntervalSimulation s = new IntervalSimulation(".\\\\^IXIC.csv");
		test = true;
		
		//alglorithm 1
		for(int day = 0; day < data.size(); day++) {
			//for a certain period before current day:
			//add all increasing segments into an arraylist
			//add all decreasing segments into an arraylist
			//use those recent segment patterns to predict how future increasing and decreasing will go
			//buy at the end of an increasing segment, sell at the end of a decreasing segment
			
			for(int i = 0; i < day; i++) {
				//wip
			}
		}
		
		//alglorithm 2
		Share share = new Share(".\\\\^IXIC.csv");
		boolean hasStocks = false;
		boolean hasSold = false;
		String transacted = "None";
		share.buy(0, reserveMoney);
		hasStocks = true;
		for(int day = 1; day < data.size(); day++) {
			//if current day is greater than previous day:
			//perhaps it'll keep going up so I shall buy
			//if current day is less than previous day:
			//perhaps it'll keep going down so I shall sell
			if(!hasSold && hasStocks && data.get(day).openPrice < data.get(day-1).openPrice) {
				share.sell(day, (double)reserveMoney/2);
				hasStocks = false;
				hasSold = true;
				transacted = "Sold";
			}else if(!hasStocks && hasSold && data.get(day).openPrice > data.get(day-1).openPrice) {
				share.buy(day, reserveMoney);
				hasStocks = true;
				hasSold = false;
				transacted = "Bought";
			}else {
				share.update(day);
				transacted = "None";
			}
			System.out.println("Date: " + data.get(day).date + "\nAction: " + transacted + "\n" + share);
		}
		System.out.println(reserveMoney);
	}
	

}
