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
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import java.io.*;

import javax.swing.JOptionPane;

public class Clients{
	
	private HashMap<String,String> userlist;
//	String filepath = "C:/Users/Nishith/Desktop/eclipse/VideoCoNew/members";
	String filepath = "members.txt";
	String movieFile = "movies.txt";
	String cartFile = "cart.txt";
	private String orderFile = "orders.txt";
	private static Scanner x;
	// this bufferedReader was in verifylogin before
	BufferedReader br = null;
	String username;
	private List<String> movies_in_cart;
	
	public Clients(){
		userlist = new HashMap<String,String>();
		movies_in_cart = new ArrayList<String>();
	}
	
	public List<String> getMovieInCartList(){
		return movies_in_cart;
	}
	
	public void setUsername(String username){
		this.username = username;
	}
	public String getUsername(){
		return this.username;
	}
		
	public void addUsers(String username, String password){
		userlist.put(username, password);
	}
	
	public HashMap<String,String> getUserList(){
		return userlist;
	}
	

	
	public boolean verifyLogin(String username, String password){
		
		boolean found = false;
		String name1 = ""; String username1 = ""; String password1 = ""; String email1 = ""; String id1 = ""; String LoyaltyPoints1 = "";
		
		try{
			x = new Scanner(new File(filepath));
			x.useDelimiter("[,\n]");
			
			while(x.hasNext() && !found){
				name1 = x.next();
				username1 = x.next();
				password1 = x.next();
				email1 = x.next();
				id1 = x.next();
				LoyaltyPoints1 = x.next();
				
				if(username1.equals(username) && password1.equals(password)){
					found = true;
					break;
				}
			}
			
		}
		catch(Exception e){
			JOptionPane.showMessageDialog(null, "INVALID LOGIN", "ERROR", JOptionPane.ERROR_MESSAGE);
		}
		
		return found;
	}
	
	// create random number
	private String getNewID(){
		Random rnd = new Random();
	    int number = rnd.nextInt(999999);

	    return String.format("%06d", number);
	}
	
	// writing into csv file
	public void saveRecordOfClient(String name, String username, String password, String email){
		userlist.put(username, password);
		
		try{
			FileWriter fw = new FileWriter(filepath, true);
			// append data without erasing
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter pw = new PrintWriter(bw);
			
			String userID = this.getNewID();
			
			pw.println(name + "," + username + "," + password + "," + email + "," + "#" + userID + "," + "0");	
			// makes sure all data is written to the file
			pw.flush();
			pw.close();
			
			JOptionPane.showMessageDialog(null, "Record saved");
		}
		catch(Exception E){
			JOptionPane.showMessageDialog(null, "Record NOT saved");

		}
	}
	
	public boolean getMovieByTitle(String title){
		String line = "";
		boolean movieFound = false;
		try{
			br = new BufferedReader(new FileReader(movieFile));
			while((line = br.readLine()) != null){
				String[] values = line.split(",");
				System.out.println(values[0]);
				if(values.length > 4 && values[0].equalsIgnoreCase(title)){
					movieFound = true;
					break;
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
		return movieFound;
	}
	
//	public ArrayList<String> getMovieByTitle1(String username) {
//
//	 }
	
	public List<String> getMovieByGenre(String genre){
		String line = "";
		List<String> moviesFound = new ArrayList<String>();
		try{
			br = new BufferedReader(new FileReader(movieFile));
			while((line = br.readLine()) != null){
				String[] values = line.split(",");
				if(values.length > 4 && values[1].equalsIgnoreCase(genre)){
					moviesFound.add(values[0]);
				
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
		return moviesFound;
	}
	
	public List<String> getMoviesByActors(String actor){
		String line = "";
		List<String> moviesFound = new ArrayList<String>();
		try{
			br = new BufferedReader(new FileReader(movieFile));
			while((line = br.readLine()) != null){
				String[] values = line.split(",");
				for(int i = 2; i<values.length; i++){
					if(values.length > 4 && values[i].equalsIgnoreCase(actor)){
						moviesFound.add(values[0]);
						System.out.println("values   " + values[0]);						
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
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return moviesFound;
	}
	
	public void addToCartFile(String movie){
		movies_in_cart.add(movie);

		try{
			// new			
			FileWriter fw = new FileWriter(cartFile, true);
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter pw = new PrintWriter(bw);
			
			pw.println(this.getUsername() + "," + movie);	
			pw.flush();
			pw.close();
			
			JOptionPane.showMessageDialog(null, "Movie added to Cart");
		}
		catch(Exception E){
			JOptionPane.showMessageDialog(null, "Record NOT saved");

		}
	}
	
	// removes all movies for particular user from the shopping cart.. once Rent button is pressed
	public void removeAllMoviesPerUser(String usernames){
		String tempFile = "temp.txt";
		File oldFile = new File(cartFile);		
		File newFile = new File(tempFile);
				
		try{
			FileWriter fw = new FileWriter(tempFile, true);
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter pw = new PrintWriter(bw);
			
			
			String currentLine = "";
			FileReader fr = new FileReader(cartFile);
			BufferedReader br = new BufferedReader(fr);
			
			while((currentLine = br.readLine()) != null){
				String data[] = currentLine.split(",");
				
				if(data.length == 2 && !data[0].equals(username)){
					pw.println(currentLine);
				}
			}
			
			pw.flush();
			pw.close();
			
			fr.close();
			br.close();
			bw.close();
			fw.close();
			
			oldFile.delete();
			File dump = new File(cartFile);
			newFile.renameTo(dump);
			

		} 
		catch(Exception e){
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "ERROR!");

		}
	}
	
	// removes just one movie at a time from shopping cart
	public void removeOneMovieCartFile(String titlemovie, String username){
		
		String tempFile = "temp.txt";
		File oldFile = new File(cartFile);		
		File newFile = new File(tempFile);
				
		try{
			FileWriter fw = new FileWriter(tempFile, true);
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter pw = new PrintWriter(bw);
			
			
			String currentLine = "";
			FileReader fr = new FileReader(cartFile);
			BufferedReader br = new BufferedReader(fr);
			
			while((currentLine = br.readLine()) != null){
				String data[] = currentLine.split(",");
				
				// && || used to doesn't get false every time
				if(data.length == 2 && !data[0].equals(username) || !data[1].equals(titlemovie)){
					pw.println(currentLine);				
				}
			}
			
			pw.flush();
			pw.close();
			
			fr.close();
			br.close();
			bw.close();
			fw.close();
			
			oldFile.delete();
			File dump = new File(cartFile);
			newFile.renameTo(dump);
			
			JOptionPane.showMessageDialog(null, "MOVIE REMOVED FROM CART");


		} 
		catch(Exception e){
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "ERROR!");

		}
		
	}

//	public void  removeFromCartFile(String uName) {
//		try {
//			String tempFile = "temp.txt";
//
//			File oldFile = new File(cartFile);
//			File newFile = new File(tempFile);
//			
//			FileWriter fileWriter = new FileWriter(tempFile, true);
//			BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
//			PrintWriter printWriter = new PrintWriter(bufferedWriter);
//			
//			x = new Scanner(new File(cartFile));
//			x.useDelimiter("[,\n]");
//			
//			while (x.hasNext()) {
//				String userType = x.next();
//				String userName = x.next();
//				
//
//				if (userName.equals(uName)) {
//					printWriter.print("");
//				}
//				
//				else {
//					 printWriter.printf("%s,%s", userType, userName);
//				}
//			}
//			x.close();
//			printWriter.flush();
//			printWriter.close();
//			oldFile.delete();
//			File dump = new File(cartFile);
//			newFile.renameTo(dump);
//			}
//			catch(IOException e) {
//				
//			}
//		
//	}
	
	// returns a list of movie titles that are in the cart for specific clients
	public List<String> getShoppingCart(String username){
		String line = "";
		List<String> moviesFound = new ArrayList<String>();
		try{
			br = new BufferedReader(new FileReader(cartFile));
			while((line = br.readLine()) != null){
				String[] values = line.split(",");
				if(values[0].contains(username)){
					moviesFound.add(values[1]);
//					movies_in_cart.add(values[1]);					
				}
			}
		}
		catch(Exception e){
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
		return moviesFound;
	}
	
	
	public String getFinalPrice(){
		String line = "";
		double priceOfOne = 15.0;
		double finalPrice = 0.0;
		int counter = 0;
		try{
			br = new BufferedReader(new FileReader(cartFile));
			while((line = br.readLine()) != null){
				String[] values = line.split(",");
				if(values.length == 2 && values[0].contains(username)){
					counter++;
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
		finalPrice = priceOfOne * counter;
		String result = String.valueOf(finalPrice);
		return result;
	}
	
	
	
	
	// Loyalty Points
	public String getLP(String username){
		String line = "";
		String LP = "";
		
		try{
			br = new BufferedReader(new FileReader(filepath));
			while((line = br.readLine()) != null){
				String[] values = line.split(",");
				if(values.length > 5 && values[1].equals(username)){
					LP = values[5];
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
		
		return LP;
	}

	
	// when user makes purchase with credt/debit, give them LP
	public void increaseLP(String username){
		String tempFile = "temp.txt";
		File oldFile = new File(filepath);		
		File newFile = new File(tempFile);
				
		try{
			FileWriter fw = new FileWriter(tempFile, true);
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter pw = new PrintWriter(bw);
			
			
			String currentLine = "";
			FileReader fr = new FileReader(filepath);
			BufferedReader br = new BufferedReader(fr);
			
			while((currentLine = br.readLine()) != null){
				String data[] = currentLine.split(",");
				
				if(data.length > 5 && data[0].equals(username)){
					int q = Integer.parseInt(data[5]);
					q = q+10;
					String quantity1 = String.valueOf(q);
					pw.println(data[0] + "," + data[1] + "," + data[2] + "," + data[3] + "," + quantity1);
				}
				else{
					pw.println(currentLine);
				}
			}
			
			pw.flush();
			pw.close();
			
			fr.close();
			br.close();
			bw.close();
			fw.close();
			
			oldFile.delete();
			File dump = new File(filepath);
			newFile.renameTo(dump);
			
			JOptionPane.showMessageDialog(null, "LP used");


		} 
		catch(Exception e){
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "ERROR!");

		}
		
	}
	/*
	 * int convert = Integer.parseInt(values[5]);
					convert = convert - 10;
					String result = String.valueOf(convert);
	 */
	
	public void decreaseLP(String username){
		String tempFile = "temp.txt";
		File oldFile = new File(filepath);		
		File newFile = new File(tempFile);
				
		try{
			FileWriter fw = new FileWriter(tempFile, true);
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter pw = new PrintWriter(bw);
			
			
			String currentLine = "";
			FileReader fr = new FileReader(filepath);
			BufferedReader br = new BufferedReader(fr);
			
			while((currentLine = br.readLine()) != null){
				String data[] = currentLine.split(",");
				
				if(data.length > 5 && data[0].equals(username)){
					int q = Integer.parseInt(data[5]);
					q = q-10;
					String quantity1 = String.valueOf(q);
					pw.println(data[0] + "," + data[1] + "," + data[2] + "," + data[3] + "," + quantity1);
				}
				else{
					pw.println(currentLine);
				}
			}
			
			pw.flush();
			pw.close();
			
			fr.close();
			br.close();
			bw.close();
			fw.close();
			
			oldFile.delete();
			File dump = new File(filepath);
			newFile.renameTo(dump);
			
			JOptionPane.showMessageDialog(null, "LP used");


		} 
		catch(Exception e){
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "ERROR!");

		}
	}

	
	
	
	public boolean checkEmailExist(String email){
		boolean exist = false;
				
		BufferedReader br = null;
		String line = "";
		try{
			
			br = new BufferedReader(new FileReader(filepath));
			
			while((line = br.readLine()) != null){
				String[] values = line.split(",");
				 if(values.length == 6 && values[3].equals(email)){
					exist = true;
				 }
				
			}
		}
		catch(Exception e){
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
		
		return exist;
	}
	
	
	
	
	public boolean checkLoyaltyPoints(String username){
		boolean enoughForOrder = false;
				
		BufferedReader br = null;
		String line = "";
		try{
			
			br = new BufferedReader(new FileReader(filepath));
			
			while((line = br.readLine()) != null){
				String[] values = line.split(",");
				 if(values.length == 6 && values[1].equals(username)){
					int pointsAvailable = Integer.parseInt(values[5]);
					if(pointsAvailable >= 10){
						enoughForOrder = true;
					}
				 }				
			}
		}
		catch(Exception e){
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
		
		return enoughForOrder;
	}
	
	// when order returned, take that order out of the orderFile
	public void returnOrder(String username ,String orderNum){
		String tempFile = "temp.txt";
		File oldFile = new File(orderFile);		
		File newFile = new File(tempFile);
				
		try{
			FileWriter fw = new FileWriter(tempFile, true);
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter pw = new PrintWriter(bw);
			
			
			String currentLine = "";
			FileReader fr = new FileReader(orderFile);
			BufferedReader br = new BufferedReader(fr);
			
			while((currentLine = br.readLine()) != null){
				String data[] = currentLine.split(",");
				
				if(data.length > 5 && !data[1].equals(orderNum)){
					pw.println(currentLine);
				}
			}
			
			pw.flush();
			pw.close();
			
			fr.close();
			br.close();
			bw.close();
			fw.close();
			
			oldFile.delete();
			File dump = new File(orderFile);
			newFile.renameTo(dump);
			


		} 
		catch(Exception e){
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "ORDER NUMBER IS INVALID!");

		}
	}

	public boolean getShippingStatus(String orderNum){
		boolean shipped = false;
		
		BufferedReader br = null;
		String line = "";
		try{
			
			br = new BufferedReader(new FileReader(orderFile));
			
			while((line = br.readLine()) != null){
				String[] values = line.split(",");
				int lastValue = values.length - 1;
				 if(values.length > 5 && values[1].equals(orderNum) && values[lastValue].equals("shipped")){
					 shipped = true;
				 }
				
			}
		}
		catch(Exception e){
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
		
		return shipped;
	}
	
	
	public static void main(String[] args){
		Clients u1 = new Clients();
		
//		u1.saveRecordOfClients("nishith", "nish98", "nishsam1", "nkithmina24@gmail.com" ,0, u1.filepath);	
//		System.out.println(u1.verifyLogin("kaze", "ok", u1.filepath));
//		
//		System.out.println("movie by title 'Arcane': " + u1.getMovieByTitle("Arcane"));
//		System.out.println(u1.getMovieByGenre("Comedy"));
//		
//		
//		String name = "Jolie";
//		BufferedReader br = null;
//		String line = "";
//	    List<String[]> content = new ArrayList<>();
//		try{
//			br = new BufferedReader(new FileReader(u1.movieFile));
//			while((line = br.readLine()) != null){
//				String[] values = line.split(",");
//				for(int i = 2; i<values.length; i++){
//					if(values[i].contains(name)){
//						System.out.println("values   " + values[0]);
//						
//					}
//				}
//				
//
//			}
//		}
//		catch(FileNotFoundException e){
//			e.printStackTrace();
//		}
//		catch(IOException e){
//			e.printStackTrace();
//		}
//		finally{
//			try {
//				br.close();
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
//		
//		// Testing viewCart() function
//		System.out.println(u1.viewShoppingCart("nish98"));
//		System.out.println(u1.getMovieInCartList());
//		
		// Testing removeFromCartFile() function
//		System.out.println(u1.verifyLogin("nish98", "nishsam1", u1.filepath));
//		u1.removeFromCartFile("Arcane", "nish98");
//			System.out.println(u1.checkEmailExist("nkithmina24@gmail.com"));
	
//		System.out.println(u1.verifyLogin("nish98", "nishsam1"));
		
//		System.out.println(u1.getShippingStatus("#123233"));
		u1.increaseLP("nish98");
		System.out.println(u1.getLP("nish98"));

	

	
	}
	

	
	
	
}
