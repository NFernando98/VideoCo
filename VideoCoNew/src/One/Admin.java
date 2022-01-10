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
import java.util.Scanner;

import javax.swing.JOptionPane;

public class Admin {
	private HashMap<String,String> adminlist;
	String filepath = "admins.txt"; 
	String movieFile = "movies.txt";
	String clientFile = "members.txt";
	private ArrayList<Movie> movieList;
	private static Scanner x;

	


	public Admin(){
		adminlist = new HashMap<String,String>();
		movieList = new ArrayList<Movie>();
	}
	
	
	public void addUsers(String username, String password){
		adminlist.put(username, password);
	}
	
	public HashMap<String,String> getUserList(){
		return adminlist;
	}
	
	public boolean checkEmailExist(String email){
		boolean exist = false;
				
		BufferedReader br = null;
		String line = "";
		try{
			
			br = new BufferedReader(new FileReader(filepath));
			
			while((line = br.readLine()) != null){
				String[] values = line.split(",");
				 if(values.length == 5 && values[3].equals(email)){
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
				e.printStackTrace();
			}
		}
		
		return exist;
	}
	
	// verify Admin login credentials ...
	// my note: took out one of the arguments
	public boolean verifyLogin(String username, String password){
		
		boolean found = false;
		String name1 = ""; String username1 = ""; String password1 = ""; String email1 = ""; String id1 = "";
		
		try{
			x = new Scanner(new File(filepath));
			x.useDelimiter("[,\n]");
			
			while(x.hasNext() && !found){
				name1 = x.next();
				username1 = x.next();
				password1 = x.next();
				email1 = x.next();
				id1 = x.next();
				
				if(username1.equals(username) && password1.equals(password)){
					found = true;
				}
			}
			
		}
		catch(Exception e){
			JOptionPane.showMessageDialog(null, "INVALID LOGIN", "ERROR", JOptionPane.ERROR_MESSAGE);

		}
		
		return found;
	}
	
	public void saveRecordOfAdmin(String name, String username, String password, String email, int userID, String filepath){
		adminlist.put(username, password);
		
		try{
			FileWriter fw = new FileWriter(filepath, true);
			// append data without erasing
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter pw = new PrintWriter(bw);
			
			pw.println(name + "," + username + "," + password + "," + email + "," + userID);	
			// makes sure all data is written to the file
			pw.flush();
			pw.close();
			JOptionPane.showMessageDialog(null, "ADMIN REGISTERED");
		}
		catch(Exception E){
			JOptionPane.showMessageDialog(null, "RECORD NOT SAVED");

		}
	}
	
	
	public void addMovies(Movie movie){
		movieList.add(movie);
		
		try{
			FileWriter fw = new FileWriter(movieFile, true);
			// append data without erasing
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter pw = new PrintWriter(bw);
			
			pw.print(movie.name + "," + movie.genre + "," + movie.actors.toString().replace("[", "").replace("]", "") + "," + movie.quantity + "\n");
			
			// makes sure all data is written to the file
			pw.flush();
			pw.close();
			
			JOptionPane.showMessageDialog(null, "movie added");
		}
		catch(Exception E){
			JOptionPane.showMessageDialog(null, "movie cannot be added");

		}
	}
	
	public void removeMovies(String titleMovie){
		String tempFile = "temp.txt";
		File oldFile = new File(movieFile);		
		File newFile = new File(tempFile);
		
//		String username1 = ""; String title1 = ""; String actor1 = ""; String actor2 = ""; String quantity1 = "";
		
		try{
			FileWriter fw = new FileWriter(tempFile, true);
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter pw = new PrintWriter(bw);
			
			String currentLine = "";
			FileReader fr = new FileReader(movieFile);
			BufferedReader br = new BufferedReader(fr);
			
			while((currentLine = br.readLine()) != null){
				String data[] = currentLine.split(",");
				if(!(data[0].equals(titleMovie))){
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

		} 
		catch(Exception e){
			e.printStackTrace();

		}
	}
	
	public void removeClient(String username){
		String tempFile = "temp.txt";
		File oldFile = new File(clientFile);		
		File newFile = new File(tempFile);
				
		try{
			FileWriter fw = new FileWriter(tempFile, true);
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter pw = new PrintWriter(bw);
			
			
			String currentLine = "";
			FileReader fr = new FileReader(clientFile);
			BufferedReader br = new BufferedReader(fr);
			
			while((currentLine = br.readLine()) != null){
				String data[] = currentLine.split(",");
				
				if(data.length == 6 && !data[1].equals(username)){
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
			File dump = new File(clientFile);
			newFile.renameTo(dump);
			
			JOptionPane.showMessageDialog(null, "CLIENT REMOVED!");


		} 
		catch(Exception e){
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "ERROR!");

		}
	}
	
//	public void removeClient(String username){
//		String tempFile = "temp.txt";
//		File oldFile = new File(clientFile);		
//		File newFile = new File(tempFile);
//		
////		String username1 = ""; String title1 = ""; String actor1 = ""; String actor2 = ""; String quantity1 = "";
//		
//		try{
//			FileWriter fw = new FileWriter(tempFile, true);
//			BufferedWriter bw = new BufferedWriter(fw);
//			PrintWriter pw = new PrintWriter(bw);
//			
//			String currentLine = "";
//			FileReader fr = new FileReader(clientFile);
//			BufferedReader br = new BufferedReader(fr);
//			
//			while((currentLine = br.readLine()) != null){
//				String data[] = currentLine.split(",");
//				String username1 = data[1];
//				if(!(username1.equals(username))){
//					pw.println(currentLine);
//				}
//			}
//			
//			pw.flush();
//			pw.close();
//			
//			fr.close();
//			br.close();
//			bw.close();
//			fw.close();
//			
//			oldFile.delete();
//			File dump = new File(clientFile);
//			newFile.renameTo(dump);
//
//		} 
//		catch(ArrayIndexOutOfBoundsException exception) {
//		    System.out.println("exeption reached");
//		}
//		catch(Exception e){
//			e.printStackTrace();
//
//		}
	
	public void removeMovieFromStore(String movie, int spinnerQuantity){
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
				
				if(data.length > 4 && data[0].equals(movie)){
					int q = Integer.parseInt(data[4]);
					q = q - spinnerQuantity;
					String quantity1 = String.valueOf(q);

					if(q < 0){
					}
					else{
						pw.println(data[0] + "," + data[1] + "," + data[2] + "," + data[3] + "," + quantity1);
					}
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
			
			JOptionPane.showMessageDialog(null, "Given quantity of this movie is removed!");

		} 
		catch(Exception e){
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "ERROR movie does not exist!");

		}
	}
	
	
	
	
	public static void main(String[] args){
		// testing saveRecordOfAdmin and verifyLogin methods
		Admin a1 = new Admin();
//		a1.saveRecordOfAdmin("nishith", "nish98", "nishsam1", "nkithmina24@gmail.com" ,0, a1.filepath);
//		System.out.println(a1.verifyLogin("kaze", "ok", a1.filepath));
		
		// testing addMovie() method
//		List<String> actors = new ArrayList<String>();
//		actors.add("Larry");
//		actors.add("Wang");
//		actors.add("Chris");
//		actors.add("Jolie");
//		
//		Movie m1 = new Movie("Salt", "Action", actors, 1);		
//		a1.addMovies(m1);
		
		// testing checkEmailExist()
//		System.out.println(a1.checkEmailExist("nkithmina24@gmail.com"));
//		a1.removeClient("nish98");
		a1.removeMovieFromStore("Thor", 1);
	}
	
	
}
