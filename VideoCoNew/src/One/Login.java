package One;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;

import java.awt.BorderLayout;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JCheckBox;

public class Login {

	private JFrame frmLoginPage;
	private JTextField username_textField;
	private JTextField password_textField;
	
	Map<String, String> userlist = new HashMap<String, String>();
	Map<String, String> adminlist = new HashMap<String, String>();
	
	Clients user = new Clients();
	Admin admin = new Admin();
	

	
	
	
    


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
					window.frmLoginPage.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Login(){
		initialize();
	}
	public Login(HashMap<String, String> users) {
		initialize();
		this.userlist = users;
	}
	
	

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmLoginPage = new JFrame();
		frmLoginPage.setTitle("Login Page");
		frmLoginPage.setBounds(100, 100, 626, 330);
		frmLoginPage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmLoginPage.getContentPane().setLayout(null);
		frmLoginPage.setVisible(true);


		
		JLabel lblNewLabel = new JLabel((String) null);
		lblNewLabel.setBounds(0, 0, 0, 228);
		frmLoginPage.getContentPane().add(lblNewLabel);
		
		username_textField = new JTextField();
		username_textField.setBounds(167, 64, 370, 20);
		frmLoginPage.getContentPane().add(username_textField);
		username_textField.setFont(new Font("Times New Roman", Font.BOLD, 12));
		username_textField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		username_textField.setColumns(10);
		
		JLabel lblNewLabel_1_1 = new JLabel("Password");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblNewLabel_1_1.setBounds(31, 100, 111, 29);
		frmLoginPage.getContentPane().add(lblNewLabel_1_1);
		
	
		JLabel lblNewLabel_1 = new JLabel("Username");
		lblNewLabel_1.setBounds(31, 55, 126, 29);
		frmLoginPage.getContentPane().add(lblNewLabel_1);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 24));
		
		password_textField = new JPasswordField();
		password_textField.setFont(new Font("Times New Roman", Font.BOLD, 12));
		password_textField.setColumns(10);
		password_textField.setBounds(167, 110, 370, 20);
		frmLoginPage.getContentPane().add(password_textField);
		
		JButton client_loginButton = new JButton("Login as CLIENT");
		client_loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String username = username_textField.getText();	
				String password = password_textField.getText();
				
				
				if(e.getSource() == client_loginButton){
					// verifyLogin() returns boolean value
					if(user.verifyLogin(username, password)){
						frmLoginPage.setVisible(false);
						SearchMovie search_movie = new SearchMovie();

						// if user exists then setUsername() in client class
						search_movie.user.setUsername(username);
						
					}
					else{
						JOptionPane.showMessageDialog(null, "INVALID LOGIN", "ERROR", JOptionPane.ERROR_MESSAGE);
						password_textField.setText(null);
					}
				}
				

			}
		});
		client_loginButton.setBounds(78, 160, 141, 53);
		frmLoginPage.getContentPane().add(client_loginButton);
		
		JButton registerButton = new JButton("Register");
		registerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("register button clicked");
				
				String username = username_textField.getText();	
				String password = password_textField.getText();	
				
				if(e.getSource() == registerButton){
					frmLoginPage.setVisible(false);;
					RegisterNewUser r = new RegisterNewUser();
				}
			
				
			}
		});
		registerButton.setBounds(399, 160, 141, 53);
		frmLoginPage.getContentPane().add(registerButton);
		
		JButton admin_loginButton = new JButton("Login as ADMIN");
		admin_loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String username = username_textField.getText();	
				String password = password_textField.getText();	
				if(e.getSource() == admin_loginButton){
					// verifyLogin() returns boolean value
					if(admin.verifyLogin(username, password)){					
						frmLoginPage.setVisible(false);
						AdminWindow m = new AdminWindow();	
					}
				else{
					username_textField.setText(null);
					password_textField.setText(null);
					}
				}
			}
		});
		admin_loginButton.setBounds(229, 160, 160, 53);
		frmLoginPage.getContentPane().add(admin_loginButton);
		
		JButton btnReturnToHome = new JButton("Return Home");
		btnReturnToHome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(arg0.getSource() == btnReturnToHome){
					frmLoginPage.dispose();
					HomeWindow h = new HomeWindow();
				}
			}
		});
		btnReturnToHome.setBounds(10, 257, 147, 23);
		frmLoginPage.getContentPane().add(btnReturnToHome);
	}
}
	
