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
import java.util.List;
import java.util.Scanner;

import javax.swing.JOptionPane;

// try to use singleton here so only admin can do this

public class Movie {
	String name;
	String genre;
	List<String> actors;
	int quantity;
	private String movieFile = "movies.txt";
	private String cartFile = "cart.txt";
	BufferedReader br = null;
	Order order = new Order();


	public Movie(){
		
	}
	Movie(String name, String genre, List<String> actors, int quantity){
		this.name = name;
		this.genre = genre;
		this.actors = actors; 
		this.quantity = quantity;
		actors = new ArrayList<String>();
	}
	
	public String getQuantity(String movieTitle){
		String quantity = "";

		String line = "";
		try{
			br = new BufferedReader(new FileReader(movieFile));
			while((line = br.readLine()) != null){
				String[] values = line.split(",");
				// checking quantity and availability
//				int arr[] = new int [1];
//				arr[0] = Integer.parseInt(s)
		
				if(values[0].contains(movieTitle)){
					quantity +=  "[Qt: " + values[values.length-1] + "]";
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
		
		return quantity;
		
	}
	
	public boolean isMovieAvailable(String movie){
		boolean availability = false;

		String line = "";
		try{
			br = new BufferedReader(new FileReader(movieFile));
			while((line = br.readLine()) != null){
				String[] values = line.split(",");
				// checking quantity and availability
//				int arr[] = new int [1];
//				arr[0] = Integer.parseInt(s)
				if(values.length > 4 && values[0].equalsIgnoreCase(movie) ){
					int quantity = Integer.parseInt(values[values.length-1]);
					if(quantity > 0){
						availability = true;
						break;
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
		
		return availability;
	}
	

	public List<String> moviesInCartFile(String username){
		String line = "";
		List<String> cartFileMovieList = new ArrayList<String>();
		try{			
			br = new BufferedReader(new FileReader(cartFile));

			while((line = br.readLine()) != null){
				String[] values = line.split(",");
				// add all movies into arraylist
				if(values[0].contains(username))
				cartFileMovieList.add(values[1]);				
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
		return cartFileMovieList;
	}
	
	// compare cart and movie files, and decrement quantity accordingly
	public void decrementQuantity(String movieTitle){
		String tempFile = "temp.txt";
		File oldFile = new File(movieFile);		
		File newFile = new File(tempFile);
				
		try{
			FileWriter fw = new FileWriter(tempFile, true);
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter pw = new PrintWriter(bw);
			
			
			String currentLine = "";
			FileReader fr = new FileReader(movieFile);
			BufferedReader br = new BufferedReader(fr);
			
			while((currentLine = br.readLine()) != null){
				String data[] = currentLine.split(",");
				
				if(data.length > 4 && data[0].equalsIgnoreCase(movieTitle)){
					int q = Integer.parseInt(data[4]);
					q = q-1;
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
			File dump = new File(movieFile);
			newFile.renameTo(dump);
			
			JOptionPane.showMessageDialog(null, "DECREMENTED Q");


		} 
		catch(Exception e){
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "ERROR cannot decrement!");

		}
	}
	
	// increment quantity of given movie
	public void incrementQuantity(String movieTitle){
		String tempFile = "temp.txt";
		File oldFile = new File(movieFile);		
		File newFile = new File(tempFile);
		
		String username1 = ""; String title1 = "";
		
		try{
			FileWriter fw = new FileWriter(tempFile, true);
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter pw = new PrintWriter(bw);
			
			
			String currentLine = "";
			FileReader fr = new FileReader(movieFile);
			BufferedReader br = new BufferedReader(fr);
			
			while((currentLine = br.readLine()) != null){
				String data[] = currentLine.split(",");
				
				if(data.length > 4 && data[0].equals(movieTitle)){
					int q = Integer.parseInt(data[4]);
					q = q+1;
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
			File dump = new File(movieFile);
			newFile.renameTo(dump);
			
			JOptionPane.showMessageDialog(null, "INCREMENTED Q");


		} 
		catch(Exception e){
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "ERROR cannot increment!");

		}
		
	}
	
	// increments quantity for list of movies given an order number..(for returning with order number)
	public void incrementQ(String ordernumber){
		String tempFile = "temp.txt";
		File oldFile = new File(movieFile);		
		File newFile = new File(tempFile);
		
		System.out.println(order.getMovies_forOrderNum("#827322"));
		
		try{
			FileWriter fw = new FileWriter(tempFile, true);
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter pw = new PrintWriter(bw);
			
			String currentLine = "";
			FileReader fr = new FileReader(movieFile);
			BufferedReader br = new BufferedReader(fr);
			System.out.println(order.getMovies_forOrderNum(ordernumber).get(1));

			while((currentLine = br.readLine()) != null){
				String data[] = currentLine.split(",");
				
				if(data.length == 5 && data[0].equals(order.getMovies_forOrderNum(ordernumber).get(1).replace("[", "").replace("]", "")) || 
						data[0].equals(order.getMovies_forOrderNum(ordernumber).get(1).toString().replace("[", "").replace("]", "")) || 
						data[0].equals(order.getMovies_forOrderNum(ordernumber).get(2).toString().replace("[", "").replace("]", ""))){
					
					int q = Integer.parseInt(data[4]);
					q = q+1;
					String quantity1 = String.valueOf(q);
					pw.println(data[0] + "," + data[1] + "," + data[2] + "," + data[3] + "," + quantity1);
				}
				else{
					pw.println(data[0] + "," + data[1] + "," + data[2] + "," + data[3] + "," + data[4]);
				}					
			}
			
			pw.flush();
			pw.close();
			
			fr.close();
			br.close();
			bw.close();
			fw.close();
			
			oldFile.delete();
			File dump = new File(movieFile);
			newFile.renameTo(dump);
			
		} 
		catch(Exception e){
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "ERROR cannot increment!");

		}
	}

	

	
	public static void main(String[] args) throws IOException{
		Movie m1 = new Movie();
//		System.out.println(m1.isMovieAvailable("Jumanji"));
		
		// testing quantity decrement
//		System.out.println("movies in cart file: " + m1.moviesInCartFile("nish98"));
//		m1.decrementQuantity("Arcane");
//		m1.incrementQ("#827322");
		System.out.println(m1.getQuantity("Thor"));
		System.out.println(m1.getQuantity("Uncharted"));
		System.out.println(m1.isMovieAvailable("Thor"));
		
		
	}
}
