package One;

import java.awt.EventQueue;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class WarehouseEmployeeWindow {
	private String ordersFile = "orders.txt";
	private String warehouse = "warehouse.txt";
	BufferedReader br = null;
	
	
	private void removeOrder(String ordernum){
		String tempFile = "temp.txt";
		File oldFile = new File(ordersFile);		
		File newFile = new File(tempFile);
				
		try{
			FileWriter fw = new FileWriter(tempFile, true);
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter pw = new PrintWriter(bw);
			
			
			String currentLine = "";
			FileReader fr = new FileReader(ordersFile);
			BufferedReader br = new BufferedReader(fr);
			
			while((currentLine = br.readLine()) != null){
				String data[] = currentLine.split(",");
				
				if(data.length > 5 && !data[1].equals(ordernum)){
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
			File dump = new File(ordersFile);
			newFile.renameTo(dump);
			
			JOptionPane.showMessageDialog(null, "ORDER REMOVED!");


		} 
		catch(Exception e){
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "ERROR!");

		}
	}
	
	private String getNewID(){
		Random rnd = new Random();
	    int number = rnd.nextInt(999999);

	    return String.format("%06d", number);
	}
	
	public void saveRecordOfWarehouse(String name, String username, String password, String email){
		
		try{
			FileWriter fw = new FileWriter(warehouse, true);
			// append data without erasing
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter pw = new PrintWriter(bw);
			
			String userID = this.getNewID();
						
			pw.println(name + "," + email + "," + "#" + userID);	
			// makes sure all data is written to the file
			pw.flush();
			pw.close();
			
			JOptionPane.showMessageDialog(null, "Record saved");
		}
		catch(Exception E){
			JOptionPane.showMessageDialog(null, "Record NOT saved");

		}
	}
	
	private List<String> getOrderNumbers(){
		String line = "";
		List<String> ordersFound = new ArrayList<String>();
		try{
			br = new BufferedReader(new FileReader(ordersFile));
			while((line = br.readLine()) != null){
				String[] values = line.split(",");
				// return order numbers
				if(values.length > 5){
					ordersFound.add(values[1]);	
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
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return ordersFound;
	}
	
	private void warehouse_updateShippingStatus(String orderNum){
		String tempFile = "temp.txt";
		File oldFile = new File(ordersFile);		
		File newFile = new File(tempFile);
				
		try{
			FileWriter fw = new FileWriter(tempFile, true);
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter pw = new PrintWriter(bw);
			
			
			String currentLine = "";
			FileReader fr = new FileReader(ordersFile);
			BufferedReader br = new BufferedReader(fr);
			
			while((currentLine = br.readLine()) != null){
				String data[] = currentLine.split(",");
				
				if(data.length > 5 && !data[1].equals(orderNum)){
					pw.println(currentLine);
				}
				else{
					// if the order number is found in the file, then do the below
					for(int i = 0; i < data.length; i++){
						if(data[data.length-1].equals("shipped")){
							JOptionPane.showMessageDialog(null, "This order has already been shipped!");
							break;
						}
						if(i == data.length - 1 ){
							pw.print("shipped" + "\n");
						}
						else{
							pw.print(data[i] + ",");
						}
					}
					
				}
				System.out.println(data.length);
			}
			
			pw.flush();
			pw.close();
			
			fr.close();
			br.close();
			bw.close();
			fw.close();
			
			oldFile.delete();
			File dump = new File(ordersFile);
			newFile.renameTo(dump);
			
			JOptionPane.showMessageDialog(null, "Order Shipped!");


		} 
		catch(Exception e){
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "ORDER NUMBER IS INVALID!");

		}
	}

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WarehouseEmployeeWindow window = new WarehouseEmployeeWindow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		WarehouseEmployeeWindow w1 = new WarehouseEmployeeWindow();
		System.out.println(w1.getOrderNumbers());
	}

	/**
	 * Create the application.
	 */
	public WarehouseEmployeeWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JComboBox<String> orderss = new JComboBox<String>();
		orderss.addItem("Orders");
		for(String m: this.getOrderNumbers()){
			orderss.addItem(m);
		}
		orderss.setBounds(51,40,122,26);
		frame.getContentPane().add(orderss);
		
		JButton btnShipped = new JButton("ship");
		btnShipped.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(arg0.getSource() == btnShipped){
					WarehouseEmployeeWindow w = new WarehouseEmployeeWindow();
					String orderNum = (String) orderss.getSelectedItem();
					// updates shipment to "shipped" from "not shipped" in orders file
					w.warehouse_updateShippingStatus(orderNum);
					// keep track of shipments, date, delivered and so on
//					w.shipmentlog()
				}
			}
		});
		btnShipped.setBounds(199, 42, 89, 23);
		frame.getContentPane().add(btnShipped);
		
		JButton btnNewButton = new JButton("remove from catalog");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(arg0.getSource() == btnNewButton){
					WarehouseEmployeeWindow w = new WarehouseEmployeeWindow();
					String orderNum = (String) orderss.getSelectedItem();

					w.removeOrder(orderNum);
				}
					
			}
		});
		btnNewButton.setBounds(199, 79, 151, 23);
		frame.getContentPane().add(btnNewButton);
		
	
	}
	


}
