package One;

import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;
import javax.swing.JComboBox;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.awt.event.ActionEvent;
import javax.swing.JList;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.AbstractListModel;
import javax.swing.JTextPane;
import javax.swing.ListModel;
import javax.swing.JProgressBar;
import javax.swing.JCheckBox;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JScrollBar;
import javax.swing.JSpinner;
import javax.swing.JSlider;
import javax.swing.JTextArea;
import javax.swing.JPasswordField;
import javax.swing.JToolBar;


public class OrderWindow {
	
	Order order = new Order();
	Clients user =  new Clients();
	Movie movie = new Movie();
	String username = "";
	private JFrame frmYourOrderWindow;
	private JTextField ordernum_textField;

	/**
	 * Launch the application.
	 */
	

	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OrderWindow window = new OrderWindow();
					window.frmYourOrderWindow.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
	}

	/**
	 * Create the application.
	 */
	public OrderWindow() {
		initialize();
	}


	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmYourOrderWindow = new JFrame();
		frmYourOrderWindow.setTitle("Your Order Window");
		frmYourOrderWindow.setBounds(100, 100, 706, 329);
		frmYourOrderWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmYourOrderWindow.getContentPane().setLayout(null);
		frmYourOrderWindow.setVisible(true);
		
		JButton btnReturnOrder = new JButton("Return order");
		btnReturnOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(arg0.getSource() == btnReturnOrder){
					
					String orderNum = ordernum_textField.getText();					
					user.returnOrder(user.getUsername(),orderNum);
					JOptionPane.showMessageDialog(null, "Order Returned!");
//					movie.incrementQuantity(ordernum_textField);
					System.out.println(orderNum);
					
				}
			}
		});
		btnReturnOrder.setBounds(137, 183, 119, 23);
		frmYourOrderWindow.getContentPane().add(btnReturnOrder);
		
		JButton btnNewButton = new JButton("cancel order");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource() == btnNewButton){
					
					String orderNum = ordernum_textField.getText();
					
					if(user.getShippingStatus(orderNum)){
						JOptionPane.showMessageDialog(null, "Order has already been SHIPPED!", "In transit", JOptionPane.ERROR_MESSAGE);
						ordernum_textField.setText(null);
					}
					else{
						user.returnOrder(user.getUsername(),orderNum); 
						JOptionPane.showMessageDialog(null, "Order cancelled!");

					}
				}
			}
		});
		btnNewButton.setBounds(137, 208, 119, 23);
		frmYourOrderWindow.getContentPane().add(btnNewButton);
		
		JButton btnGoBack = new JButton("Go back ");
		btnGoBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource() == btnGoBack){
					frmYourOrderWindow.setVisible(false);
					SearchMovie search = new SearchMovie();
					search.user.setUsername(user.getUsername());

				}
			}
		});
		btnGoBack.setBounds(10, 256, 89, 23);
		frmYourOrderWindow.getContentPane().add(btnGoBack);
		
		// not for myself: next two blocks were below view order button
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "", TitledBorder.CENTER, TitledBorder.ABOVE_TOP, null, new Color(0, 0, 0)));
		panel.setBounds(10, 31, 670, 68);
		frmYourOrderWindow.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel labelforOrders = new JLabel("");
		labelforOrders.setBounds(10, 11, 650, 41);
		panel.add(labelforOrders);
		
		JButton btnViewOrders = new JButton("View Orders");
		btnViewOrders.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(arg0.getSource() == btnViewOrders){
					if(user.getUsername() == null){
						JOptionPane.showMessageDialog(null, "EMPTY!", "ERROR", JOptionPane.ERROR_MESSAGE);
					}
					else{
//						labelforOrders.setText(order.getOrderNumbers(username).toString().replace("[", "").replace("]", ""));
						labelforOrders.setText(order.getOrders(username).toString());
						//.replace("[", "").replace("]", "").replace("{", "").replace("}", "").replace(",", "")

					}
				}
			}
		});
		btnViewOrders.setBounds(277, 100, 105, 23);
		frmYourOrderWindow.getContentPane().add(btnViewOrders);
		
		ordernum_textField = new JTextField();
		ordernum_textField.setBounds(137, 165, 119, 20);
		frmYourOrderWindow.getContentPane().add(ordernum_textField);
		ordernum_textField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Enter order number:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_1.setBounds(10, 186, 136, 14);
		frmYourOrderWindow.getContentPane().add(lblNewLabel_1);
		
		JLabel lblStartingWith = new JLabel("Starting with \"#\"");
		lblStartingWith.setFont(new Font("Tahoma", Font.ITALIC, 10));
		lblStartingWith.setBounds(263, 168, 80, 14);
		frmYourOrderWindow.getContentPane().add(lblStartingWith);
		
	
		
		JLabel lblAllOfYour = new JLabel("All of your orders");
		lblAllOfYour.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblAllOfYour.setBounds(263, 11, 159, 14);
		frmYourOrderWindow.getContentPane().add(lblAllOfYour);
		
	

	

	}		
}
