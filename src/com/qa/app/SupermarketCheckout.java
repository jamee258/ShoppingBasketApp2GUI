package com.qa.app;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JTextField;

import com.qa.domain.Product;
import com.qa.model.DataProvider;

import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.NumberFormat;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import javax.swing.SwingConstants;

public class SupermarketCheckout {

	private JFrame frame;
	private JTextField latestPriceField;
	private JTextField offerDetailsField;
	private JTable table;
	private JTextField textField_2;
	private JTextField textField_3;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SupermarketCheckout window = new SupermarketCheckout();
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
	public SupermarketCheckout() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 724, 395);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel lblProductName = new JLabel("Product Name:");
		lblProductName.setBounds(10, 11, 71, 14);
		frame.getContentPane().add(lblProductName);

		List<Product> products = DataProvider.getProductsWithOffers();
		DefaultComboBoxModel model = new DefaultComboBoxModel(DataProvider.getProductNames());

		JComboBox<?> comboBox = new JComboBox<>();
		comboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				System.out.println(arg0.getItem());
				Product selectedProduct = (Product) arg0.getItem();
				NumberFormat formatter = NumberFormat.getCurrencyInstance();
				String money = formatter.format(selectedProduct.getUnitPrice());
				latestPriceField.setText(money);
				if (selectedProduct.getOffer() != null) {
					offerDetailsField.setText(selectedProduct.getOffer().toString());
				} else {
					offerDetailsField.setText("No Offers Available");
				}
			}
		});
		comboBox.setBounds(10, 36, 160, 20);

		comboBox.setModel(model);

		frame.getContentPane().add(comboBox);

		JLabel lblLatestPrice = new JLabel("Latest Price:");
		lblLatestPrice.setBounds(180, 11, 71, 14);
		frame.getContentPane().add(lblLatestPrice);

		latestPriceField = new JTextField();
		latestPriceField.setText("Price");
		latestPriceField.setEnabled(false);
		latestPriceField.setBounds(180, 36, 71, 20);
		frame.getContentPane().add(latestPriceField);
		latestPriceField.setColumns(10);

		JLabel lblOffers = new JLabel("Offers:");
		lblOffers.setBounds(261, 11, 46, 14);
		frame.getContentPane().add(lblOffers);

		offerDetailsField = new JTextField();
		offerDetailsField.setText("Offer Details");
		offerDetailsField.setBounds(260, 36, 252, 94);
		frame.getContentPane().add(offerDetailsField);
		offerDetailsField.setColumns(10);

		JLabel lblQuantity = new JLabel("Quantity:");
		lblQuantity.setBounds(526, 11, 64, 14);
		frame.getContentPane().add(lblQuantity);

		JSpinner spinner = new JSpinner();
		spinner.setBounds(526, 36, 64, 20);
		frame.getContentPane().add(spinner);

		table = new JTable();
		table.setBounds(10, 151, 688, 167);
		frame.getContentPane().add(table);

		JLabel lblBasket = new JLabel("Basket:");
		lblBasket.setBounds(10, 127, 46, 14);
		frame.getContentPane().add(lblBasket);

		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnAdd.setBounds(609, 35, 89, 23);
		frame.getContentPane().add(btnAdd);

		JButton btnRemove = new JButton("Remove");
		btnRemove.setBounds(609, 117, 89, 23);
		frame.getContentPane().add(btnRemove);

		JButton btnExit = new JButton("Exit");
		btnExit.setBounds(609, 322, 89, 23);
		frame.getContentPane().add(btnExit);

		JLabel lblNoOfItems = new JLabel("No. of Items:");
		lblNoOfItems.setBounds(10, 326, 71, 14);
		frame.getContentPane().add(lblNoOfItems);

		textField_2 = new JTextField();
		textField_2.setBounds(76, 323, 86, 20);
		frame.getContentPane().add(textField_2);
		textField_2.setColumns(10);

		JLabel lblTotal = new JLabel("Total:");
		lblTotal.setBounds(229, 326, 46, 14);
		frame.getContentPane().add(lblTotal);

		textField_3 = new JTextField();
		textField_3.setEnabled(false);
		textField_3.setBounds(260, 323, 86, 20);
		frame.getContentPane().add(textField_3);
		textField_3.setColumns(10);
	}
}
