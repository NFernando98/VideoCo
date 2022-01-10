package One;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.UIManager;
import java.awt.Color;

public class HomeWindow {

	private JFrame frmHomepage;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HomeWindow window = new HomeWindow();
					window.frmHomepage.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public HomeWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmHomepage = new JFrame();
		frmHomepage.setTitle("HomePage");
		frmHomepage.setBounds(100, 100, 374, 361);
		frmHomepage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmHomepage.getContentPane().setLayout(null);
		frmHomepage.setVisible(true);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(36, 55, 263, 238);
		frmHomepage.getContentPane().add(panel_1);
		panel_1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Start", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_1.setLayout(null);
		
		JButton btnNewButton = new JButton("Sign Up");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(arg0.getSource() == btnNewButton){
					frmHomepage.setVisible(false);;
					RegisterNewUser r = new RegisterNewUser();
				}
			}
		});
		btnNewButton.setBounds(102, 11, 151, 23);
		panel_1.add(btnNewButton);
		
		JButton btnSignIn = new JButton("Sign In");
		btnSignIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource() == btnSignIn){
					frmHomepage.setVisible(false);
					Login r = new Login();
				}
			}
		});
		btnSignIn.setBounds(102, 40, 151, 23);
		panel_1.add(btnSignIn);
		
		JLabel lblEmailNkithminafacebookcom = new JLabel("Email: VideoCo@gmail.com");
		lblEmailNkithminafacebookcom.setBounds(102, 202, 158, 14);
		panel_1.add(lblEmailNkithminafacebookcom);
		
		JLabel lblVisEmail = new JLabel("Phone: 647-553-6801");
		lblVisEmail.setBounds(102, 188, 119, 14);
		panel_1.add(lblVisEmail);
		
		JLabel lblContactVideoco = new JLabel("Contact VideoCo");
		lblContactVideoco.setBounds(102, 169, 141, 14);
		panel_1.add(lblContactVideoco);
		lblContactVideoco.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		JLabel lblEmailNkithminafacebookcom_1 = new JLabel("Location: Toronto");
		lblEmailNkithminafacebookcom_1.setBounds(102, 213, 158, 14);
		panel_1.add(lblEmailNkithminafacebookcom_1);
		
		JLabel lblVideoco = new JLabel("VideoCo");
		lblVideoco.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblVideoco.setBounds(130, 0, 119, 44);
		frmHomepage.getContentPane().add(lblVideoco);
	}
}
