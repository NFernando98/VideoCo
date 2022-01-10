package One;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import java.util.Timer;

import java.util.TimerTask;
import java.util.Vector;

import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;

public class Order {
	String orderID;
	private String ordersFile = "orders.txt";
	private String cartFile = "cart.txt";
	BufferedReader br = null;
//	private String username;'
	String username;
	
	static int interval;
	static Timer timer;
	
	private List<String> ordernums = new ArrayList<String>();
	
	public Order(){
		
	}
	
	
//	void setUsername(String username){
//		this.username = username;
//	}
//	public String getUsername(){
//		return this.username;
//	}
	
	private String getNewID(){
		Random rnd = new Random();
	    int number = rnd.nextInt(999999);

	    return String.format("%06d", number);
	}
	// add the movie names into orders and the rest..orderID and shipping status
	public void saveRecordOfOrder(String username, String card_number, String shipping_address, List<String> movies){
		
		try{

					FileWriter fw = new FileWriter(ordersFile, true);
					// append data without erasing
					BufferedWriter bw = new BufferedWriter(fw);
					PrintWriter pw = new PrintWriter(bw);
					// get newID using new function
					this.orderID = this.getNewID();
					pw.println(username + "," + "#" + this.orderID + "," + card_number + "," + shipping_address + "," + movies + "," + "not shipped");	
					// makes sure all data is written to the file
					pw.flush();
					pw.close();
					
					JOptionPane.showMessageDialog(null, "Purchase made!");
				
				
			
		}
		catch(FileNotFoundException e){
			e.printStackTrace();
		}
		catch(IOException e){
			e.printStackTrace();
	
		}
		
	}
	
	public String getShippingStatus(){
		String status = "";
		if(this.interval == 0){
			status = "Shipped";
		}
		else{
			status = "Before shipping";
		}
		
		return status;
	}
	
	
	
	static final int setInterval() {
	    if (interval == 1)
	        timer.cancel();
	    return --interval;
	}
	
	public void getOrderNumbers(){
		ordernums = this.getOrderNumbers(this.username);
	}
	
	// get all ordernums given username.. not used I believe, delete
	public List<String> getOrderNumbers(String username){
		String line = "";
		List<String> ordersFound = new ArrayList<String>();
		
		try{
			br = new BufferedReader(new FileReader(ordersFile));
			while((line = br.readLine()) != null){
				String[] values = line.split(",");
				if(values[0].equals(username)){
					ordersFound.add(values[1]);		
				}
			}
		}
		catch(FileNotFoundException e){
			e.printStackTrace();
		}
		catch(IOException e){
			e.printStackTrace();
		}
		finally{
			try {
				br.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return ordersFound;
	}
	
	// given Order number, return all the movies
	public List<String> getMovies_forOrderNum(String orderNum){
		String line = "";
		List<String> movies = new ArrayList<String>();
		
		int startPosition = 0;
		int endPosition = 0;
		
		try{
			br = new BufferedReader(new FileReader(ordersFile));
			while((line = br.readLine()) != null){
				String[] values = line.split(",");
				
				startPosition = 4;
				endPosition = values.length - 2;
				
				if(values.length > 5 && values[1].equals(orderNum))
				{

					for(int i = startPosition; i <= endPosition; i++){
						movies.add(values[i]);									
					}
				}
			}
		}
		catch(FileNotFoundException e){
			e.printStackTrace();
		}
		catch(IOException e){
			e.printStackTrace();
		}
		finally{
			try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return movies;
	}
	
	
	
	// returns not just order numbers but movies attached to the order numbers
	public Map<String, List<String>> getOrders(String username){
		String line = "";
		
		// positions of movie list in my ordersFile
		int startPosition = 0;
		int endPosition = 0;
		
		Map<String , List<String>> result = new HashMap<String , List<String>>();
		List<String> movies = new ArrayList<String>();
		
		try{
			br = new BufferedReader(new FileReader(ordersFile));
			while((line = br.readLine()) != null){
				String[] values = line.split(",");
				
				startPosition = 4;
				endPosition = values.length - 2;
				
				
				if(values[0].equals(username)){
					for(int i = startPosition; i <= endPosition; i++){
						movies.add(values[i]);									
					}					
					result.put(values[1], movies);
				}
				// a new movie for the other order numbers
				movies = new ArrayList<String>();
				
			}
		}
		catch(FileNotFoundException e){
			e.printStackTrace();
		}
		catch(IOException e){
			e.printStackTrace();
		}
		finally{
			try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	
	
	
	public static void main(String[] args) {
//	    Scanner sc = new Scanner(System.in);
//	    System.out.print("Input seconds => : ");
//	    String secs = sc.nextLine();
//	    int delay = 1000;
//	    int period = 1000;
//	    timer = new Timer();
//	    interval = Integer.parseInt(secs);
//	    System.out.println(secs);
//	    timer.scheduleAtFixedRate(new TimerTask() {
//
//	        public void run() {
//	            System.out.println(setInterval());
//
//	        }
//	    }, delay, period);
//	    
//	    
	    
	    
	    
		Order order1 = new Order();
//		System.out.println(order1.getOrderNumbers("nish98"));
//		System.out.println(order1.getOrders("nish98"));
		System.out.println(order1.getMovies_forOrderNum("#827322"));


	}

	
	
	
}





	

	

