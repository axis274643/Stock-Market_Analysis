package socks;

import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;

import javax.swing.JFrame;

public class IntervalSimulation extends Share{
	
	public IntervalSimulation(String file) {
		super(file);
	}
	
	public double intervalMethod(int interval, int buy, int sell) {
		//buy and sell thing
		reset();
		reserveMoney = initialTestMoney;
		for(int i = 0; i < data.size(); i++) {
			if(i % interval == buy) {
				buy(i, reserveMoney);
			}
			if(i % interval == sell) {
				sell(i);
			}
		}
		
		sell(data.size()-1);
		
		return reserveMoney;
	}
	
	
	
	public static void main(String[] args) {
		IntervalSimulation s = new IntervalSimulation(".\\\\^IXIC.csv");
		test = true;
		System.out.println("Data size: " + data.size());
		
		double[][][] simulation = new double[18][19][19];
		
		for(int interval = 2; interval < 20; interval++) {
			System.out.println(interval + ":");
			for(int buy = 0; buy < interval; buy++) {
				for(int sell = 0; sell < interval; sell++) {
					simulation[interval-2][buy][sell] = s.intervalMethod(interval,buy,sell);
					
					
					System.out.print("[" + buy + "," + sell + " = " + round(simulation[interval-2][buy][sell]) + "] ");
				}
				System.out.println();
			}
		}
		
		double max = simulation[0][0][0];
		double min = simulation[0][0][0];
		
		int[] indexOfMax = new int[3];
		int[] indexOfMin = new int[3];
		
		for(int interval = 2; interval < 10; interval++) {
			for(int buy = 0; buy < interval; buy++) {
				for(int sell = 0; sell < interval; sell++) {
					if(simulation[interval-2][buy][sell] > max) {
						max = simulation[interval-2][buy][sell];
						indexOfMax[0] = interval;
						indexOfMax[1] = buy;
						indexOfMax[2] = sell;
					}
					if(simulation[interval-2][buy][sell] < min) {
						min = simulation[interval-2][buy][sell];
						indexOfMin[0] = interval;
						indexOfMin[1] = buy;
						indexOfMin[2] = sell;
					}
				}
			}
		}
		System.out.println("Most money made: (" + indexOfMax[0] + "," + indexOfMax[1] + "," + indexOfMax[2] + ") " + round(max) + ", Least money made: (" + indexOfMin[0] + "," + indexOfMin[1] + "," + indexOfMin[2] + ") " + round(min));
		
		JFrame frame = new JFrame();
		Graph graph = new Graph("when you bought it in that interval", "average of money earned", 10, 2);
		graph.setText("each line represents a different interval length where green = 2 and dark blue = 10");
		
		
		for(int x = 0; x < simulation.length; x++) {
			ArrayList<Point> line = new ArrayList<Point>();
			for(int y = 0; y < simulation[0].length; y++) {
				ArrayList<Double> array = new ArrayList<Double>();
				for(int z = 0; z < simulation[0][0].length; z++) {
					if(simulation[x][y][z] != 0) {
						array.add(simulation[x][y][z]);
					}
				}
				Point p = new Point(y*10, (int) getAverage(array)*50);
				System.out.println(getAverage(array));
				line.add(p);
			}
			graph.addLine(line, new Color(0, 255 - x*2, x*2));
			
		}
		
		
		frame.setSize(1500,800);
		frame.setLocation(0,0);
		
		frame.add(graph);
		
		frame.setVisible(true);
		
	}

}
