package One;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextPane;
import javax.swing.JEditorPane;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JSpinner;
import javax.swing.JSlider;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;


import javax.swing.UIManager;
import java.awt.Color;
import java.awt.Font;

public class AdminWindow {

	private JFrame frmSystemAdministrator;
	private JTextField movieTitle_textField;
	
	Admin admin = new Admin();
	Clients user = new Clients();
	private JTextField movieGenre_textField;
	private JTextField Actors_textField;
	private JTextField removeMovie_textField;
	private JTextField remveClient_textField;
	private JTextField name_textField;
	private JTextField email_textField;
	private JTextField address_textField;
	private JTextField phoneNum_textField;
	private JTextField username_textField;
	private JTextField password_textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminWindow window = new AdminWindow();
					window.frmSystemAdministrator.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public AdminWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmSystemAdministrator = new JFrame();
		frmSystemAdministrator.setTitle("Admin");
		frmSystemAdministrator.setBounds(100, 100, 951, 500);
		frmSystemAdministrator.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmSystemAdministrator.getContentPane().setLayout(null);
		frmSystemAdministrator.setVisible(true);
		
		JPanel panel = new JPanel();
		panel.setForeground(Color.BLACK);
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Add movie", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(10, 11, 449, 225);
		frmSystemAdministrator.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblMovieTitle = new JLabel("Movie Title:");
		lblMovieTitle.setBounds(115, 43, 86, 14);
		panel.add(lblMovieTitle);
		
		JLabel lblMovieGenre = new JLabel("Movie Genre:");
		lblMovieGenre.setBounds(110, 68, 91, 14);
		panel.add(lblMovieGenre);
		
		JLabel lblActors = new JLabel("Actors(separated by comma):");
		lblActors.setBounds(10, 93, 191, 14);
		panel.add(lblActors);
		
		JLabel lblQuantity = new JLabel("Quantity:");
		lblQuantity.setBounds(129, 118, 64, 14);
		panel.add(lblQuantity);
		
		JButton btnAddMovie = new JButton("Add Movie to store");
		btnAddMovie.setBounds(203, 173, 161, 28);
		panel.add(btnAddMovie);
		
		Actors_textField = new JTextField();
		Actors_textField.setBounds(203, 90, 86, 20);
		panel.add(Actors_textField);
		Actors_textField.setColumns(10);
		
		movieGenre_textField = new JTextField();
		movieGenre_textField.setBounds(203, 65, 86, 20);
		panel.add(movieGenre_textField);
		movieGenre_textField.setColumns(10);
		
		movieTitle_textField = new JTextField();
		movieTitle_textField.setBounds(203, 40, 86, 20);
		panel.add(movieTitle_textField);
		movieTitle_textField.setColumns(10);
		
		JSpinner quantity_spinner = new JSpinner();
		quantity_spinner.setBounds(203, 115, 29, 20);
		panel.add(quantity_spinner);
		
		JLabel lblMax = new JLabel("* add only 2 for this demo");
		lblMax.setFont(new Font("Tahoma", Font.ITALIC, 10));
		lblMax.setBounds(299, 93, 140, 14);
		panel.add(lblMax);
		
		JLabel lblAdd = new JLabel("* add 1 only");
		lblAdd.setFont(new Font("Tahoma", Font.ITALIC, 10));
		lblAdd.setBounds(299, 68, 207, 14);
		panel.add(lblAdd);
		btnAddMovie.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String movieTitle = movieTitle_textField.getText();	
				String movieGenre = movieGenre_textField.getText();	
				String actor = Actors_textField.getText();	
				int quantity = (Integer) quantity_spinner.getValue();
				
				
				List<String> movieActors = new ArrayList<String>();
				movieActors.add(actor);

				Movie movie = new Movie(movieTitle, movieGenre, movieActors, quantity);
				if(e.getSource() == btnAddMovie){
					admin.addMovies(movie);
				}
			}
		});
		
		JButton btnRemoveClient = new JButton("remove client");
		btnRemoveClient.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String clientUsername = remveClient_textField.getText();
				if(e.getSource() == btnRemoveClient){
					admin.removeClient(clientUsername);
				}
			}
		});
		btnRemoveClient.setBounds(647, 278, 126, 23);
		frmSystemAdministrator.getContentPane().add(btnRemoveClient);
		
		JSpinner quantity_spinner_1 = new JSpinner();
		quantity_spinner_1.setBounds(376, 247, 29, 20);
		frmSystemAdministrator.getContentPane().add(quantity_spinner_1);
		
		
		JButton btnRemoveMovieFrom = new JButton("remove movie from store");
		btnRemoveMovieFrom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int quantity = (int) quantity_spinner_1.getValue();
				String movie = removeMovie_textField.getText();
				
				if(e.getSource() == btnRemoveMovieFrom){
					admin.removeMovieFromStore(movie, quantity);
				}
				
			}
		});
		btnRemoveMovieFrom.setBounds(183, 278, 187, 23);
		frmSystemAdministrator.getContentPane().add(btnRemoveMovieFrom);
		
		removeMovie_textField = new JTextField();
		removeMovie_textField.setBounds(183, 247, 187, 20);
		frmSystemAdministrator.getContentPane().add(removeMovie_textField);
		removeMovie_textField.setColumns(10);
		
		JLabel lblRemoveMovie = new JLabel("remove movie:");
		lblRemoveMovie.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblRemoveMovie.setBounds(75, 250, 98, 14);
		frmSystemAdministrator.getContentPane().add(lblRemoveMovie);
		
		JLabel lblRemoveClient = new JLabel("remove client:");
		lblRemoveClient.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblRemoveClient.setBounds(549, 250, 88, 14);
		frmSystemAdministrator.getContentPane().add(lblRemoveClient);
		
		remveClient_textField = new JTextField();
		remveClient_textField.setBounds(647, 247, 126, 20);
		frmSystemAdministrator.getContentPane().add(remveClient_textField);
		remveClient_textField.setColumns(10);
		
		JButton btnGoBack = new JButton("Go back");
		btnGoBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(arg0.getSource() == btnGoBack){
					// verifyLogin() returns boolean value
					frmSystemAdministrator.setVisible(false);
					Login login = new Login();	
				}
			}
		});
		btnGoBack.setBounds(21, 402, 98, 48);
		frmSystemAdministrator.getContentPane().add(btnGoBack);
		
	
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Add Client", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_1.setBounds(469, 11, 449, 225);
		frmSystemAdministrator.getContentPane().add(panel_1);
		
		JLabel lblName = new JLabel("Name:");
		lblName.setBounds(92, 21, 62, 14);
		panel_1.add(lblName);
		
		JLabel lblNewLabel = new JLabel("Email:");
		lblNewLabel.setBounds(92, 46, 56, 14);
		panel_1.add(lblNewLabel);
		
		name_textField = new JTextField();
		name_textField.setColumns(10);
		name_textField.setBounds(178, 18, 204, 20);
		panel_1.add(name_textField);
		
		email_textField = new JTextField();
		email_textField.setColumns(10);
		email_textField.setBounds(178, 43, 204, 20);
		panel_1.add(email_textField);
		
		JLabel lblAddress = new JLabel("Location:");
		lblAddress.setBounds(78, 71, 73, 14);
		panel_1.add(lblAddress);
		
		address_textField = new JTextField();
		address_textField.setColumns(10);
		address_textField.setBounds(178, 66, 204, 20);
		panel_1.add(address_textField);
		
		JLabel lblPhoneNumber = new JLabel("Phone Number:");
		lblPhoneNumber.setBounds(47, 96, 113, 14);
		panel_1.add(lblPhoneNumber);
		
		phoneNum_textField = new JTextField();
		phoneNum_textField.setColumns(10);
		phoneNum_textField.setBounds(178, 93, 204, 20);
		panel_1.add(phoneNum_textField);
		
		username_textField = new JTextField();
		username_textField.setColumns(10);
		username_textField.setBounds(178, 118, 204, 20);
		panel_1.add(username_textField);
		
		JLabel lblUsername = new JLabel("UserName:");
		lblUsername.setBounds(70, 121, 98, 14);
		panel_1.add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setBounds(75, 144, 85, 14);
		panel_1.add(lblPassword);
		
		password_textField_1 = new JTextField();
		password_textField_1.setColumns(10);
		password_textField_1.setBounds(178, 141, 204, 20);
		panel_1.add(password_textField_1);
		
		JButton btnNewButton = new JButton("Add Client");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = name_textField.getText();
				String username = username_textField.getText();	
				String password = password_textField_1.getText();
				String email = email_textField.getText();
				String address = address_textField.getText();
				String phonenum = phoneNum_textField.getText();
				
				if(e.getSource() == btnNewButton){
					if(name.isEmpty() == false && username.isEmpty() == false && password.isEmpty() == false &&
							email.isEmpty() == false && address.isEmpty() == false && phonenum.isEmpty() == false) {
						if(!(user.checkEmailExist(email))){
							user.saveRecordOfClient(name, username,password, email);							
						} 
						else{
							JOptionPane.showMessageDialog(null, "EMAIL ADDRESS IS BEING USED", "ERROR", JOptionPane.ERROR_MESSAGE);
						}
						
					}
					else{
						JOptionPane.showMessageDialog(null, "MUST FILL IN ALL FIELDS", "ERROR", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		btnNewButton.setBounds(178, 175, 204, 23);
		panel_1.add(btnNewButton);
		
		JLabel lblTypeUsername = new JLabel("* Type username");
		lblTypeUsername.setForeground(Color.RED);
		lblTypeUsername.setFont(new Font("Tahoma", Font.ITALIC, 10));
		lblTypeUsername.setBounds(778, 250, 147, 14);
		frmSystemAdministrator.getContentPane().add(lblTypeUsername);
		
	
	}
}
