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

public class SystemAdminWindow {

	private JFrame frmSystemAdministratorWindow;
	private JTextField name_textField;
	private JTextField username_textField;
	private JTextField password_textField_1;


	Clients user = new Clients();
	Admin admin = new Admin();
	WarehouseEmployeeWindow warehouse = new WarehouseEmployeeWindow();
	
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
					SystemAdminWindow window = new SystemAdminWindow();
					window.frmSystemAdministratorWindow.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public SystemAdminWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmSystemAdministratorWindow = new JFrame();
		frmSystemAdministratorWindow.setTitle("System Administrator Window");
		frmSystemAdministratorWindow.setBounds(100, 100, 562, 375);
		frmSystemAdministratorWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmSystemAdministratorWindow.getContentPane().setLayout(null);
		frmSystemAdministratorWindow.setVisible(true);
		
		
		JComboBox<String> comboBox = new JComboBox<String>();
		frmSystemAdministratorWindow.getContentPane().add(comboBox);
		comboBox.addItem("Admin");				
		comboBox.addItem("Warehouse");				
		comboBox.setBounds(240, 11, 192, 22);


		
		JButton btnReturn = new JButton("Return Home");
		btnReturn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				if(e.getSource() == btnReturn){
					frmSystemAdministratorWindow.dispose();
//					Login l = new Login(user.getUserList());
					HomeWindow h = new HomeWindow();
				}
			}
		});
		btnReturn.setBounds(10, 286, 147, 39);
		frmSystemAdministratorWindow.getContentPane().add(btnReturn);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Registration", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(64, 77, 403, 166);
		frmSystemAdministratorWindow.getContentPane().add(panel);
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
				
				JButton btnRegister = new JButton("Add into the system");
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
							if((comboBox.getSelectedItem()).equals("Admin")){
								if(!(admin.checkEmailExist(email))){
									admin.saveRecordOfAdmin(name, username,password, email,0 , admin.filepath);	
								}
								else{
									JOptionPane.showMessageDialog(null, "ADMIN EXISTS", "ERROR", JOptionPane.ERROR_MESSAGE);
								}
							}
							else if(comboBox.getSelectedItem().equals("Warehouse")){
								
								warehouse.saveRecordOfWarehouse(name, username,password, email);
							}
							else{
									JOptionPane.showMessageDialog(null, "Employee Exists", "ERROR", JOptionPane.ERROR_MESSAGE);
								}
							
						}
						else{
							JOptionPane.showMessageDialog(null, "MUST FILL IN ALL FIELDS", "ERROR", JOptionPane.ERROR_MESSAGE);
						}
					}	
				}
				});
				btnRegister.setBounds(191, 286, 188, 39);
				frmSystemAdministratorWindow.getContentPane().add(btnRegister);
				
				JLabel lblRegisterAs = new JLabel("Register Employee as:");
				lblRegisterAs.setBounds(72, 15, 147, 14);
				frmSystemAdministratorWindow.getContentPane().add(lblRegisterAs);
				
		
	}
	
	
	public Map<String,String> getUserList(){
		return user.getUserList();
	}
}
