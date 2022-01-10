package One;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Map;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;

public class RegisterNewUser {

	private JFrame frmRegisterNewUser;
	private JTextField name_textField;
	private JTextField username_textField;
	private JTextField password_textField_1;


	Clients user = new Clients();
	Admin admin = new Admin();
	
	private JTextField email_textField;
	private JTextField address_textField;
	private JTextField phoneNum_textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegisterNewUser window = new RegisterNewUser();
					window.frmRegisterNewUser.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public RegisterNewUser() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmRegisterNewUser = new JFrame();
		frmRegisterNewUser.setTitle("Register new user window");
		frmRegisterNewUser.setBounds(100, 100, 562, 375);
		frmRegisterNewUser.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmRegisterNewUser.getContentPane().setLayout(null);
		frmRegisterNewUser.setVisible(true);


		
		JButton btnReturn = new JButton("Return Home");
		btnReturn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				if(e.getSource() == btnReturn){
					frmRegisterNewUser.dispose();
//					Login l = new Login(user.getUserList());
					HomeWindow h = new HomeWindow();
				}
			}
		});
		btnReturn.setBounds(10, 286, 147, 39);
		frmRegisterNewUser.getContentPane().add(btnReturn);
		
//		JButton registerAdmin_btn = new JButton("Register Admin");
//		registerAdmin_btn.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				
//				String name = name_textField.getText();
//				String username = username_textField.getText();	
//				String password = password_textField_1.getText();
//				String email = email_textField.getText();
//
//				
//				if(e.getSource() == registerAdmin_btn){
//					if(!(admin.checkEmailExist(email))){
//						admin.saveRecordOfAdmin(name, username,password, email,0 , admin.filepath);
//
//					}	
//					else{
//						JOptionPane.showMessageDialog(null, "Email address is already being used", "ERROR", JOptionPane.ERROR_MESSAGE);
//					}
//				}
//
//				
//			}
//		});
//		registerAdmin_btn.setBounds(179, 226, 130, 23);
//		frame.getContentPane().add(registerAdmin_btn);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Registration", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(21, 34, 461, 209);
		frmRegisterNewUser.getContentPane().add(panel);
						panel.setLayout(null);
				
						
						JLabel lblName = new JLabel("Name:");
						lblName.setBounds(92, 21, 62, 14);
						panel.add(lblName);
				
				JLabel lblNewLabel = new JLabel("Email:");
				lblNewLabel.setBounds(92, 46, 56, 14);
				panel.add(lblNewLabel);
				
				name_textField = new JTextField();
				name_textField.setBounds(178, 18, 204, 20);
				panel.add(name_textField);
				name_textField.setColumns(10);
				
				email_textField = new JTextField();
				email_textField.setBounds(178, 43, 204, 20);
				panel.add(email_textField);
				email_textField.setColumns(10);
				
				JLabel lblAddress = new JLabel("Location:");
				lblAddress.setBounds(78, 71, 73, 14);
				panel.add(lblAddress);
				
				address_textField = new JTextField();
				address_textField.setBounds(178, 66, 204, 20);
				panel.add(address_textField);
				address_textField.setColumns(10);
				
				JLabel lblPhoneNumber = new JLabel("Phone Number:");
				lblPhoneNumber.setBounds(47, 96, 113, 14);
				panel.add(lblPhoneNumber);
				
				phoneNum_textField = new JTextField();
				phoneNum_textField.setBounds(178, 93, 204, 20);
				panel.add(phoneNum_textField);
				phoneNum_textField.setColumns(10);
				
				username_textField = new JTextField();
				username_textField.setBounds(178, 118, 204, 20);
				panel.add(username_textField);
				username_textField.setColumns(10);
				
				JLabel lblUsername = new JLabel("UserName:");
				lblUsername.setBounds(70, 121, 98, 14);
				panel.add(lblUsername);
				
				JLabel lblPassword = new JLabel("Password:");
				lblPassword.setBounds(75, 144, 85, 14);
				panel.add(lblPassword);
				
				password_textField_1 = new JTextField();
				password_textField_1.setBounds(178, 141, 204, 20);
				panel.add(password_textField_1);
				password_textField_1.setColumns(10);
				
				JButton btnRegister = new JButton("Register");
				btnRegister.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						String name = name_textField.getText();
						String username = username_textField.getText();	
						String password = password_textField_1.getText();
						String email = email_textField.getText();
						String address = address_textField.getText();
						String phonenum = phoneNum_textField.getText();

						
					if(e.getSource() == btnRegister){
						if(name.isEmpty() == false && username.isEmpty() == false && password.isEmpty() == false &&
								email.isEmpty() == false && address.isEmpty() == false && phonenum.isEmpty() == false) {
							if(user.checkEmailExist(email)){
								JOptionPane.showMessageDialog(null, "EMAIL ADDRESS IS BEING USED", "ERROR", JOptionPane.ERROR_MESSAGE);
							}
							else{
								user.saveRecordOfClient(name, username,password, email);
							}

						}
						else{
							JOptionPane.showMessageDialog(null, "MUST FILL IN ALL FIELDS", "ERROR", JOptionPane.ERROR_MESSAGE);
						}
					}
				}
				});
				btnRegister.setBounds(378, 286, 142, 39);
				frmRegisterNewUser.getContentPane().add(btnRegister);
				
				JButton btnNewButton = new JButton("Go to Login");
				btnNewButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(e.getSource() == btnNewButton){
							frmRegisterNewUser.dispose();
							Login l = new Login(user.getUserList());
						}
					}
				});
				btnNewButton.setBounds(191, 286, 147, 39);
				frmRegisterNewUser.getContentPane().add(btnNewButton);
				
		
	}
	
	
	public Map<String,String> getUserList(){
		return user.getUserList();
	}
}
