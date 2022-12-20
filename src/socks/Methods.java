package socks;

import java.util.ArrayList;

public class Methods {


	public static ArrayList<Double> sort(ArrayList<Double> arr) {
		
		for(int i = 0; i < arr.size(); i++) {
			int indexOfMin = i;
			for(int j = i; j < arr.size(); j++) {
				if(arr.get(j) < arr.get(indexOfMin)) {
					indexOfMin = j;
				}
			}
			
			double temp = arr.get(i);
			arr.set(i,arr.get(indexOfMin));
			arr.set(indexOfMin,temp);
		}
		
		return arr;
	}
	
	public static double round(double d) {
		d *= 100;
		d = (int) d;
		return d/100.0;
	}
	
	public static double mad(ArrayList<Double> arr) {
		double average = getAverage(arr);
		
		double total = 0;
		for(int i = 0; i < arr.size(); i++) {
			total += Math.abs(arr.get(i) - average);
		}
		return total/arr.size();
	}
	
	public static ArrayList<Integer> findLocalMaxs(ArrayList<Double> arr) {
		ArrayList<Double> sortedList = new ArrayList<Double>(); 
		for(int i = 0; i < arr.size(); i++) {
			sortedList.add(arr.get(i));
		}
		sort(sortedList);
		ArrayList<Integer> localMaxIndexes = new ArrayList<Integer>();
		int cutOffIndex = sortedList.size()*3/4;
		if(cutOffIndex > 0) {
			cutOffIndex -= 1;
		}
		for(int i = 0; i < arr.size(); i++) {
			if(arr.get(i) > sortedList.get(cutOffIndex)) {
				localMaxIndexes.add(i);
			}
		}
		return localMaxIndexes;
	}
	
	public static ArrayList<Integer> findLocalMins(ArrayList<Double> arr) {
		ArrayList<Double> sortedList = new ArrayList<Double>(); 
		for(int i = 0; i < arr.size(); i++) {
			sortedList.add(arr.get(i));
		}
		sort(sortedList);
		ArrayList<Integer> localMinIndexes = new ArrayList<Integer>();
		int cutOffIndex = sortedList.size()*1/4;
		for(int i = 0; i < arr.size(); i++) {
			if(arr.get(i) < sortedList.get(cutOffIndex)) {
				localMinIndexes.add(i);
			}
		}
		return localMinIndexes;
	}
	
	public static double getAverage(ArrayList<Double> array) {
		double total = 0;
		for(int i = 0; i < array.size(); i++) {
			total += array.get(i);
		}
		return total/array.size();
	}
	
	public static double getAverage(double[] array) {
		double total = 0;
		for(int i = 0; i < array.length; i++) {
			total += array[i];
		}
		return total/array.length;
	}
	
	public static double getAverage(double[][] array) {
		double total = 0;
		for(int x = 0; x < array.length; x++) {
			for(int y = 0; y < array[0].length; y++) {
				total += array[x][y];
			}
		}
		return total/(array.length*array[0].length);
	}
	
	public static double getAverage(double[][][] array) {
		double total = 0;
		for(int x = 0; x < array.length; x++) {
			for(int y = 0; y < array[0].length; y++) {
				for(int z = 0; z < array[0][0].length; z++) {
					total += array[x][y][z];
				}
			}
		}
		return total/(array.length*array[0].length*array[0][0].length);
	}

}
