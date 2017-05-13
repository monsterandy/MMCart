package com.andy.cart;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

import javax.swing.*;

@SuppressWarnings("serial")
public class CartInsert extends JPanel implements ActionListener {

	JLabel itemNameLabel = null;
	JLabel itemCountLabel = null;
	JLabel itemPriceLabel = null;
	JTextField itemNameText = null;
	JTextField itemCountText = null;
	JTextField itemPriceText = null;
	JButton confirmButton = null;
	Map<Integer, String[]> cartMap = null;

	public CartInsert(Map<Integer, String[]> cartMap) {
		this.cartMap = cartMap;
		SpringLayout layout = new SpringLayout();
		setLayout(layout);
		itemNameLabel = new JLabel("Goods Name:");
		itemCountLabel = new JLabel("Goods Count:");
		itemPriceLabel = new JLabel("Goods Price:");
		itemNameText = new JTextField(12);
		itemCountText = new JTextField(12);
		itemPriceText = new JTextField(12);
		confirmButton = new JButton("Confirm");
		confirmButton.addActionListener(this);
		add(itemNameLabel);
		add(itemNameText);
		add(itemCountLabel);
		add(itemCountText);
		add(itemPriceLabel);
		add(itemPriceText);
		add(confirmButton);
		layout.putConstraint(SpringLayout.WEST, itemNameLabel, 110, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, itemNameLabel, 60, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.WEST, itemNameText, 12, SpringLayout.EAST, itemNameLabel);
		layout.putConstraint(SpringLayout.NORTH, itemNameText, 56, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.WEST, itemCountLabel, 110, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, itemCountLabel, 30, SpringLayout.SOUTH, itemNameLabel);
		layout.putConstraint(SpringLayout.WEST, itemCountText, 10, SpringLayout.EAST, itemCountLabel);
		layout.putConstraint(SpringLayout.NORTH, itemCountText, 19, SpringLayout.SOUTH, itemNameText);
		layout.putConstraint(SpringLayout.WEST, itemPriceLabel, 110, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, itemPriceLabel, 30, SpringLayout.SOUTH, itemCountLabel);
		layout.putConstraint(SpringLayout.WEST, itemPriceText, 18, SpringLayout.EAST, itemPriceLabel);
		layout.putConstraint(SpringLayout.NORTH, itemPriceText, 19, SpringLayout.SOUTH, itemCountText);
		layout.putConstraint(SpringLayout.WEST, confirmButton, 195, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, confirmButton, 30, SpringLayout.SOUTH, itemPriceText);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == confirmButton) {
			String[] info = new String[3];
			try {
				info[0] = itemNameText.getText().trim();
			} catch (Exception e2) {
				JOptionPane.showMessageDialog(null, "illegal goods name!");
				return;
			}
			try {
				int a = Integer.parseInt(itemCountText.getText().trim());
				if (a < 0) {
					JOptionPane.showMessageDialog(null, "illegal goods count!");
					return;
				}
				info[1] = Integer.toString(a);
			} catch (Exception e2) {
				JOptionPane.showMessageDialog(null, "illegal goods count!");
				return;
			}
			try {
				double b = Double.parseDouble(itemPriceText.getText().trim());
				if (b < 0) {
					JOptionPane.showMessageDialog(null, "illegal goods price!");
					return;
				}
				info[2] = Double.toString(b);
			} catch (Exception e2) {
				JOptionPane.showMessageDialog(null, "illegal goods price!");
				return;
			}
			cartMap.put(UIManager.itemID, info);
			UIManager.itemID++;
			JOptionPane.showMessageDialog(null, "Add Success! Goods ID is " + (UIManager.itemID - 1));
			itemNameText.setText(null);
			itemCountText.setText(null);
			itemPriceText.setText(null);
		}
	}
}
