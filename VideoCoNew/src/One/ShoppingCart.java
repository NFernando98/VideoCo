package One;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.awt.event.ActionEvent;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;
import java.awt.Color;
import java.awt.List;
import java.awt.Font;

public class ShoppingCart {

	private JFrame frame;
	private JTextField shipAddress_textField;
	private JTextField cardInfo_textField;
		
	String username = "";
	
	Clients user = new Clients();
	Movie movie = new Movie();
	Order orders = new Order();
	private JTextField movieToRemove_textField;
	
	ArrayList<String> MoviesInCart = new ArrayList<String>();
	private JRadioButton rdbtnLoyaltyPoints;
	private JRadioButton rdbtnCredit;
	private JRadioButton rdbtnDebit;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ShoppingCart window = new ShoppingCart();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ShoppingCart() {
		initialize();
	}
	
	
	

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("Shopping Cart");
		frame.setBounds(100, 100, 640, 578);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setVisible(true);
		
		JLabel lblNewLabel_1 = new JLabel("Select payment method:");
		lblNewLabel_1.setBounds(8, 322, 160, 14);
		frame.getContentPane().add(lblNewLabel_1);
		
		rdbtnDebit = new JRadioButton("Debit");
		rdbtnDebit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(rdbtnDebit.isSelected()){
					rdbtnCredit.setSelected(false);
					rdbtnLoyaltyPoints.setSelected(false);
				}
			}
		});
		rdbtnDebit.setBounds(181, 318, 68, 23);
		frame.getContentPane().add(rdbtnDebit);
		
		JLabel lblEnterShippingAddress = new JLabel("Enter Shipping address:");
		lblEnterShippingAddress.setBounds(8, 417, 160, 14);
		frame.getContentPane().add(lblEnterShippingAddress);
		
		shipAddress_textField = new JTextField();
		shipAddress_textField.setBounds(176, 414, 131, 20);
		frame.getContentPane().add(shipAddress_textField);
		shipAddress_textField.setColumns(10);
		
		// Jcombobox
//		JComboBox<String> orderss = new JComboBox<String>();
//		for(String m: user.getMovies(username)){
//			orderss.addItem(m);
//		}
//		orderss.setBounds(17,184,229,26);
//		frame.getContentPane().add(orderss);
		
		JButton btnPushcase = new JButton("Rent");
		btnPushcase.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*
				 *  movies get added to orders and removed from shopping cart
				 */
				String cardnumber = cardInfo_textField.getText();
				String shipping_address = shipAddress_textField.getText();
				
		if(e.getSource() == btnPushcase){
			
			if(rdbtnLoyaltyPoints.isSelected()){
				if(shipping_address.isEmpty() == false) {
					if(cardnumber.isEmpty()){
						if(user.checkLoyaltyPoints(user.getUsername())){
							
							orders = new Order();
							orders.saveRecordOfOrder(user.getUsername(), cardnumber, shipping_address, user.getShoppingCart(user.getUsername()));
							// removes from cart file
							user.removeAllMoviesPerUser(user.getUsername());
//							user.decreaseLP(user.getUsername());

						}
						else{
							JOptionPane.showMessageDialog(null, "Not enough points available to make this transaction", ":(", JOptionPane.ERROR_MESSAGE);
						}
					}else{
							JOptionPane.showMessageDialog(null, "DO NOT provide card number when selecting LP", ":(", JOptionPane.ERROR_MESSAGE);
						}								
					}
					else{
						JOptionPane.showMessageDialog(null, "Fill in shipping address", "ERROR", JOptionPane.ERROR_MESSAGE);

					}
					}
					else if(rdbtnCredit.isSelected() || rdbtnDebit.isSelected()){
						if(cardnumber.isEmpty() == false && shipping_address.isEmpty() == false){
						
							orders.saveRecordOfOrder(user.getUsername(), cardnumber, shipping_address, user.getShoppingCart(user.getUsername()));
							user.removeAllMoviesPerUser(user.getUsername());
//							user.increaseLP(user.getUsername());
							

						}
						else{
							JOptionPane.showMessageDialog(null, "Fill in shipping address and card number", "ERROR", JOptionPane.ERROR_MESSAGE);
						}
					}
					else{
						JOptionPane.showMessageDialog(null, "Select Payment method", "error", JOptionPane.ERROR_MESSAGE);
					}									}
			}
		});
		btnPushcase.setBounds(472, 482, 109, 49);
		frame.getContentPane().add(btnPushcase);
		
		rdbtnCredit = new JRadioButton("Credit");
		rdbtnCredit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(rdbtnCredit.isSelected()){
					rdbtnDebit.setSelected(false);
					rdbtnLoyaltyPoints.setSelected(false);
				}
			}
		});
		rdbtnCredit.setBounds(265, 318, 89, 23);
		frame.getContentPane().add(rdbtnCredit);
		
		JLabel payment_label = new JLabel("Card number:");
		payment_label.setBounds(47, 386, 109, 14);
		frame.getContentPane().add(payment_label);
		
		cardInfo_textField = new JTextField();
		cardInfo_textField.setColumns(10);
		cardInfo_textField.setBounds(176, 383, 131, 20);
		frame.getContentPane().add(cardInfo_textField);
		
		// attempting to use Jlist here
//		ArrayList<String> movies = new ArrayList<String>();
//		for(int i = 0; i < user.getMovieInShoppingList().size(); i++ ){
//			list.add(list, user.getMovieInShoppingList().get(i));
//
//		}	
		
		JButton btnReturnToSearch = new JButton("go back");
		btnReturnToSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource() == btnReturnToSearch){
					frame.setVisible(false);
					SearchMovie search = new SearchMovie();
					search.user.setUsername(user.getUsername());
					
				}
			}
		});
		btnReturnToSearch.setBounds(8, 508, 160, 23);
		frame.getContentPane().add(btnReturnToSearch);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Movies in Cart", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(20, 11, 451, 64);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel listOfMoviesInCart_label = new JLabel("");
		listOfMoviesInCart_label.setBounds(10, 11, 429, 23);
		panel.add(listOfMoviesInCart_label);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Total", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_1.setBounds(97, 207, 100, 37);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		
		JLabel totalB_label = new JLabel("");
		totalB_label.setBounds(6, 16, 88, 14);
		panel_1.add(totalB_label);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "LP", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_2.setBounds(130, 254, 74, 37);
		frame.getContentPane().add(panel_2);
		panel_2.setLayout(null);
		
		JLabel displayloyaltyP_label = new JLabel("");
		displayloyaltyP_label.setBounds(6, 16, 58, 14);
		panel_2.add(displayloyaltyP_label);
		
		JButton btnViewCart = new JButton("View cart");
		btnViewCart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// setting list
				if(e.getSource() == btnViewCart){
					if(user.getUsername() == null){
						JOptionPane.showMessageDialog(null, "Empty!", "ERROR", JOptionPane.ERROR_MESSAGE);
					}
					else{		
						listOfMoviesInCart_label.setText(user.getShoppingCart(user.getUsername()).toString().replace("[", "").replace("]", ""));	
//						System.out.println(user.viewShoppingCart(user.getUsername().toString());
					}
				}
				
				totalB_label.setText("$" + user.getFinalPrice());
				displayloyaltyP_label.setText(user.getLP(user.getUsername()) + " LP");
			}
		});
		btnViewCart.setBounds(478, 23, 89, 52);
		frame.getContentPane().add(btnViewCart);
		
		JLabel lblNewLabel = new JLabel("Total Balance:");
		lblNewLabel.setBounds(8, 219, 89, 14);
		frame.getContentPane().add(lblNewLabel);
		
		JButton btnRemoveFromCart = new JButton("Remove Movie from Cart");
		btnRemoveFromCart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String movieTextField = movieToRemove_textField.getText();
				if(e.getSource() == btnRemoveFromCart){
					user.removeOneMovieCartFile(movieTextField, user.getUsername());
					movie.incrementQuantity(movieTextField);
				}
					
					else{
						JOptionPane.showMessageDialog(null, "found error", "ERROR", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		);
		btnRemoveFromCart.setBounds(180, 147, 192, 30);
		frame.getContentPane().add(btnRemoveFromCart);
		
		movieToRemove_textField = new JTextField();
		movieToRemove_textField.setBounds(8, 147, 148, 30);
		frame.getContentPane().add(movieToRemove_textField);
		movieToRemove_textField.setColumns(10);
		
		JLabel totalB_label_1 = new JLabel("Loyalty Points available:");
		totalB_label_1.setBounds(8, 266, 148, 23);
		frame.getContentPane().add(totalB_label_1);
		
		rdbtnLoyaltyPoints = new JRadioButton("Loyalty Points");
		rdbtnLoyaltyPoints.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(rdbtnLoyaltyPoints.isSelected()){
					rdbtnCredit.setSelected(false);
					rdbtnDebit.setSelected(false);
				}
			}
		});
		rdbtnLoyaltyPoints.setBounds(356, 318, 141, 23);
		frame.getContentPane().add(rdbtnLoyaltyPoints);
		
		JLabel lblTypeTitleTo = new JLabel("Type title to remove from cart:");
		lblTypeTitleTo.setBounds(8, 117, 189, 14);
		frame.getContentPane().add(lblTypeTitleTo);
		
		JLabel lblWhenSelectingThis = new JLabel("*when selecting LP, only provide shipping address");
		lblWhenSelectingThis.setFont(new Font("Tahoma", Font.ITALIC, 9));
		lblWhenSelectingThis.setBounds(360, 305, 264, 14);
		frame.getContentPane().add(lblWhenSelectingThis);
		
		
	
		
	
		
	}
}
