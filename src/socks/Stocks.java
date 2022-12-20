package socks;
import java.io.*;
import java.util.*;

import javax.swing.*;

import java.awt.*;
import java.math.*;
public class Stocks extends ArrayList<Stock>{  
	
	public Stocks(String file){
		String line = "";  
		String splitBy = ",";  
		try   {  
			BufferedReader br = new BufferedReader(new FileReader(file)); 
			
			int count = 0;
	
			while ((line = br.readLine()) != null)   {  
	
				String[] data = line.split(splitBy);
			
				if(count != 0) {
					this.add(new Stock(data[0], Double.valueOf(data[1]), Double.valueOf(data[4]), Double.valueOf(data[6])));
				}
				
				count++;
			}  
		}  
		catch (IOException e)   {  
			e.printStackTrace();  
		}  
	}  
	
	
}  