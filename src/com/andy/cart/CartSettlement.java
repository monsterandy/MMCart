package com.andy.cart;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

import javax.swing.*;
import javax.swing.text.*;

import com.andy.cart.UIManager;

@SuppressWarnings("serial")
public class CartSettlement extends JPanel implements ActionListener {

	JButton printListButton = null;
	JButton settlementButton = null;
	JTextPane resultPane = null;
	JScrollPane resultScrollPane = null;
	JButton clearTextPaneButton = null;
	Map<Integer, String[]> cartMap = null;

	public CartSettlement(Map<Integer, String[]> cartMap) {
		this.cartMap = cartMap;
		SpringLayout layout = new SpringLayout();
		setLayout(layout);
		printListButton = new JButton("Print Cart List");
		settlementButton = new JButton("Check Out");
		clearTextPaneButton = new JButton("Clear Text Pane");
		printListButton.addActionListener(this);
		settlementButton.addActionListener(this);
		clearTextPaneButton.addActionListener(this);
		resultPane = new JTextPane();
		resultPane.setEditable(false);
		resultScrollPane = new JScrollPane(resultPane);
		resultScrollPane.setPreferredSize(new Dimension(380, 200));
		add(printListButton);
		add(settlementButton);
		add(resultScrollPane);
		add(clearTextPaneButton);
		layout.putConstraint(SpringLayout.WEST, printListButton, 100, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, printListButton, 20, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.WEST, settlementButton, 30, SpringLayout.EAST, printListButton);
		layout.putConstraint(SpringLayout.NORTH, settlementButton, 20, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.WEST, resultScrollPane, 50, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, resultScrollPane, 10, SpringLayout.SOUTH, printListButton);
		layout.putConstraint(SpringLayout.WEST, clearTextPaneButton, 170, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, clearTextPaneButton, 10, SpringLayout.SOUTH, resultScrollPane);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == printListButton) {
			resultPane.setText(null);
			if (cartMap.isEmpty() == true) {
				insertMsg("No Goods In Cart!\n", false, Color.GRAY, 15);
				return;
			}
			printCartList();
		} else if (e.getSource() == settlementButton) {
			resultPane.setText(null);
			if (cartMap.isEmpty() == true) {
				insertMsg("No Goods In Cart!\n", false, Color.GRAY, 15);
				return;
			}
			String[] info;
			double sumPrice = 0;
			for (int i = 0; i <= UIManager.itemID; i++) {
				if ((info = cartMap.get(i)) != null) {
					sumPrice += Integer.parseInt(info[1]) * Double.parseDouble(info[2]);
				}
			}
			insertMsg("Your Total Price is ", false, Color.BLUE, 18);
			insertMsg(Double.toString(sumPrice) + "\n", true, Color.RED, 18);
			printCartList();
		} else if (e.getSource() == clearTextPaneButton) {
			resultPane.setText(null);
		}

	}

	private void printCartList() {
		String[] info;
		for (int i = 0; i <= UIManager.itemID; i++) {
			if ((info = cartMap.get(i)) != null) {
				insertMsg("Goods Name: " + info[0] + " - ", false, Color.BLACK, 15);
				insertMsg("Count: " + info[1] + " - ", false, Color.BLACK, 15);
				insertMsg("Price: " + info[2] + "\n", false, Color.BLACK, 15);
			}
		}
	}

	public void insertMsg(String str, boolean isBold, Color color, int fontSize) {
		StyledDocument doc = resultPane.getStyledDocument();
		SimpleAttributeSet attr = new SimpleAttributeSet();
		StyleConstants.setBold(attr, isBold);
		StyleConstants.setForeground(attr, color);
		StyleConstants.setFontSize(attr, fontSize);
		try {
			doc.insertString(doc.getLength(), str, attr);
		} catch (BadLocationException ble) {
			System.out.println("BadLocationException:   " + ble);
		}
	}

}
