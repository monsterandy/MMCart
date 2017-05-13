package com.andy.cart;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

import javax.swing.*;

@SuppressWarnings("serial")
public class CartDelete extends JPanel implements ActionListener {

	JLabel deleteItemIDLabel = null;
	JTextField deleteItemIDText = null;
	JButton deleteOneButton = null;
	JButton deleteAllButton = null;
	Map<Integer, String[]> cartMap = null;

	public CartDelete(Map<Integer, String[]> cartMap) {
		this.cartMap = cartMap;
		SpringLayout layout = new SpringLayout();
		setLayout(layout);
		deleteItemIDLabel = new JLabel("Goods ID:");
		deleteItemIDText = new JTextField(12);
		deleteOneButton = new JButton("Delete One");
		deleteAllButton = new JButton("Delete All");
		deleteAllButton.setForeground(Color.RED);
		deleteOneButton.addActionListener(this);
		deleteAllButton.addActionListener(this);
		add(deleteItemIDLabel);
		add(deleteItemIDText);
		add(deleteOneButton);
		add(deleteAllButton);
		layout.putConstraint(SpringLayout.WEST, deleteItemIDLabel, 120, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, deleteItemIDLabel, 60, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.WEST, deleteItemIDText, 20, SpringLayout.EAST, deleteItemIDLabel);
		layout.putConstraint(SpringLayout.NORTH, deleteItemIDText, 56, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.WEST, deleteOneButton, 110, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, deleteOneButton, 30, SpringLayout.SOUTH, deleteItemIDLabel);
		layout.putConstraint(SpringLayout.WEST, deleteAllButton, 30, SpringLayout.EAST, deleteOneButton);
		layout.putConstraint(SpringLayout.NORTH, deleteAllButton, 23, SpringLayout.SOUTH, deleteItemIDText);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == deleteOneButton) {
			Object a;
			try {
				a = Integer.parseInt(deleteItemIDText.getText().trim());
			} catch (Exception e2) {
				JOptionPane.showMessageDialog(null, "illegal goods ID!");
				return;
			}
			if (cartMap.remove(a) != null) {
				JOptionPane.showMessageDialog(null, "Delete Success!");
				deleteItemIDText.setText(null);
			} else {
				JOptionPane.showMessageDialog(null, "No Match Goods ID!");
				return;
			}
		} else if (e.getSource() == deleteAllButton) {
			cartMap.clear();
			JOptionPane.showMessageDialog(null, "All Deleted!");
			deleteItemIDText.setText(null);
		}
	}

}
