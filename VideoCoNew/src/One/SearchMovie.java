package One;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.AbstractListModel;
import java.awt.Scrollbar;
import java.awt.List;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JSpinner;
import javax.swing.JComboBox;
import javax.swing.SpinnerListModel;
import javax.swing.JScrollBar;
import javax.swing.JToggleButton;

public class SearchMovie {

	private JFrame frame;
	private JTextField search_textField;
	private JTextField addMovie_textField;
	
	Clients user = new Clients();
//	ShoppingCart cart = new ShoppingCart();
	Movie movie = new Movie();

	ArrayList<String> shoppingCart = new ArrayList<String>();
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SearchMovie window = new SearchMovie();
					window.frame.setVisible(true);
					window.frame.setTitle("Search Movie");
//					window.frame.dispose();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public SearchMovie() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("Search Movie");
		frame.setBounds(100, 100, 512, 320);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setVisible(true);
		
		JLabel FetchedMovies_label = new JLabel("Fetched Movies:");
		FetchedMovies_label.setBounds(16, 65, 101, 14);
		frame.getContentPane().add(FetchedMovies_label);
		
		search_textField = new JTextField();
		search_textField.setBounds(154, 22, 174, 20);
		frame.getContentPane().add(search_textField);
		search_textField.setColumns(10);
		
		JLabel displayMovies_Label = new JLabel("");
		displayMovies_Label.setBounds(122, 62, 364, 23);
		frame.getContentPane().add(displayMovies_Label);
		
		JSpinner search_spinner = new JSpinner();
		search_spinner.setModel(new SpinnerListModel(new String[] {"Search By Title", "Search By Genre", "Search By Actor"}));
		search_spinner.setBounds(10, 22, 126, 20);
		frame.getContentPane().add(search_spinner);
		
		JButton btnReturn = new JButton("logout");
		btnReturn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource() == btnReturn){
					frame.dispose();
					Login l = new Login(user.getUserList());
					
				}
			}
		});
		btnReturn.setBounds(10, 247, 89, 23);
		frame.getContentPane().add(btnReturn);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(search_spinner.getValue().equals("Search By Title")){
					String movieTitle = search_textField.getText();
					if(e.getSource() == btnSearch){
						if(user.getMovieByTitle(movieTitle)){					
							displayMovies_Label.setText(movieTitle + movie.getQuantity(movieTitle));
						}
						else{
							JOptionPane.showMessageDialog(null, "MOVIE NOT FOUND", "ERROR", JOptionPane.ERROR_MESSAGE);
						}
					}	
				}
				
				if(search_spinner.getValue().equals("Search By Genre")){
					String genre = search_textField.getText();
					if(e.getSource() == btnSearch){
						if(!(user.getMovieByGenre(genre) == null)){
							displayMovies_Label.setText(user.getMovieByGenre(genre).toString().replace("[", "").replace("]", ""));
						}
						else{
							JOptionPane.showMessageDialog(null, "MOVIE OF THIS GENRE DOES NOT EXIST", "ERROR", JOptionPane.ERROR_MESSAGE);
						}
					}	
				}
				

				if(search_spinner.getValue().equals("Search By Actor")){
					String actor = search_textField.getText();
					if(e.getSource() == btnSearch){
						if(!(user.getMoviesByActors(actor) == null)){
							displayMovies_Label.setText(user.getMoviesByActors(actor).toString().replace("[", "").replace("]", ""));
						}
						else{
							JOptionPane.showMessageDialog(null, "MOVIE OF THIS GENRE DOES NOT EXIST", "ERROR", JOptionPane.ERROR_MESSAGE);
						}
					}
			}
			}
		});
		btnSearch.setBounds(360, 21, 89, 23);
		frame.getContentPane().add(btnSearch);
		
		JLabel lblNewLabel = new JLabel("Add Movie:");
		lblNewLabel.setBounds(16, 157, 73, 14);
		frame.getContentPane().add(lblNewLabel);
		
		addMovie_textField = new JTextField();
		addMovie_textField.setBounds(105, 154, 86, 20);
		frame.getContentPane().add(addMovie_textField);
		addMovie_textField.setColumns(10);
		
//		JComboBox comboBox = new JComboBox();
//		comboBox.setBounds(122, 100, 141, 23);
//		frame.getContentPane().add(comboBox);
//		
//		
//		JComboBox<String> movies = new JComboBox<String>();
//		movies.addItem("Movies");
//		for(String m: user.getMovieByTitle1(user.getUsername())){
//			movies.addItem(m);
//		}
//		movies.setBounds(31,56,381,32);
//		comboBox.add(movies);
		
		JButton btnAddMovie = new JButton("Add Movie to Cart");
		btnAddMovie.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String movieTextField = addMovie_textField.getText();
				if(e.getSource() == btnAddMovie){
					if(movie.isMovieAvailable(movieTextField)){
						user.addToCartFile(movieTextField);
//						cart.MoviesInCart.add(movieTextField);
						movie.decrementQuantity(movieTextField);
					}
					else{
						JOptionPane.showMessageDialog(null, movieTextField +" IS NOT IN STOCK", ":(", JOptionPane.ERROR_MESSAGE);
					}
			
//					cart.user.addMovieToShoppingCart(movie);
					
				}
			}
		});
		
//		public ArrayList<String> getShoppingCart(){
//			return shoppingCart;
//		}
		btnAddMovie.setBounds(105, 182, 201, 23);
		frame.getContentPane().add(btnAddMovie);
		
		JButton btnViewCart = new JButton("Goto Shoppingcart");
		btnViewCart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(arg0.getSource() == btnViewCart){
					frame.setVisible(false);
					String movie = addMovie_textField.getText();
					ShoppingCart cart = new ShoppingCart();
					cart.user.setUsername(user.getUsername());
					cart.username = user.getUsername();
				}
			}
		});
		btnViewCart.setBounds(319, 247, 167, 23);
		frame.getContentPane().add(btnViewCart);
		
		JButton btnGotoOrders = new JButton("Goto Orders");
		btnGotoOrders.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource() == btnGotoOrders){
					frame.setVisible(false);
					OrderWindow orderwindow = new OrderWindow();
					orderwindow.user.setUsername(user.getUsername());
					orderwindow.username = user.getUsername();


				}
			}
		});
		btnGotoOrders.setBounds(162, 247, 114, 23);
		frame.getContentPane().add(btnGotoOrders);
	}
}
