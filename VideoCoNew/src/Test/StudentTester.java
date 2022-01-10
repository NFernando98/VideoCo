package Test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import One.Admin;
import One.Clients;
import One.Movie;
import One.Order;
import junit.framework.Assert;

public class StudentTester {
	String movieFile = "movies.txt";
	BufferedReader br = null;

//	@Before
//	public void setUp throws Exception{
//		
//	}
	
	
	@SuppressWarnings("deprecation")
	@Test
	public void test_Admin(){
		Admin a1 = new Admin();
		
		// test checkEmail()
		Assert.assertEquals(true, a1.checkEmailExist("nkithmina24@gmail.com"));    // has to be added
		Assert.assertEquals(false, a1.checkEmailExist("nkithmina24@yahoo.com"));	
		
		// test verifyLogin()
		Assert.assertTrue(a1.verifyLogin("nish98", "nishsam1"));     // has to be added
		Assert.assertFalse(a1.verifyLogin("kaze98", "nishsam1"));
		
		

	}
	
	
	@SuppressWarnings("deprecation")
	@Test
	public void test_Client(){
		Clients user = new Clients();
		
		// test verifyLogin
		Assert.assertEquals(false, user.verifyLogin("nish98","nishasm1"));
		Assert.assertEquals(true, user.verifyLogin("kaze98","nishsam1"));         // has to be added to txt
		
		// test getMovieByTitle()
		Assert.assertEquals(true, user.getMovieByTitle("Thor"));
		Assert.assertEquals(false, user.getMovieByTitle("Rampage Jackson"));

	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void test_Movie(){
		Movie movie = new Movie();

		// test getQuantity()
		String quantity = "";
		String quantityWithoutString = "";
		
		int counter = 0;

		String line = "";
		try{
			br = new BufferedReader(new FileReader(movieFile));
			while((line = br.readLine()) != null){
				String[] values = line.split(",");
				if(values[0].contains("Thor")){
					quantity += "[Qt: " + values[values.length-1] + "]";
					quantityWithoutString += values[values.length-1];
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
				
		
		Assert.assertEquals(quantity, movie.getQuantity("Thor"));
		
		// Testing isMovieAvailable()
		Assert.assertEquals(true, movie.isMovieAvailable("Thor"));
		
		// Testing moviesInCartFile()
		Assert.assertEquals(counter, movie.moviesInCartFile("nish98").size());
		
		// Testing decrementQuantity()
		movie.decrementQuantity("Thor");
		int uyes = Integer.parseInt(quantityWithoutString);
		uyes = uyes - 1;
		String idk = "[Qt: " + uyes + "]";
		Assert.assertEquals(idk, movie.getQuantity("Thor"));

 	}
	
	@Test
	public void test_Order(){
		Order order = new Order();
		
		// Testing getMovies_forOrderNum()
		
	}
	

	
	
	
	
}
