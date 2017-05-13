package com.andy.cart;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

import javax.swing.*;
import javax.swing.text.*;

@SuppressWarnings("serial")
public class CartQuery extends JPanel implements ActionListener {

	JLabel queryItemIDLabel = null;
	JTextField queryItemIDText = null;
	JButton queryOneButton = null;
	JButton queryFirstButton = null;
	JButton queryLastButton = null;
	JTextPane resultPane = null;
	JScrollPane resultScrollPane = null;
	JButton clearTextPaneButton = null;
	Map<Integer, String[]> cartMap = null;

	public CartQuery(Map<Integer, String[]> cartMap) {
		this.cartMap = cartMap;
		SpringLayout layout = new SpringLayout();
		setLayout(layout);
		queryItemIDLabel = new JLabel("Goods ID:");
		queryItemIDText = new JTextField(12);
		queryOneButton = new JButton("Query");
		queryFirstButton = new JButton("Query First Goods");
		queryLastButton = new JButton("Query Last Goods");
		clearTextPaneButton = new JButton("Clear Text Pane");
		queryOneButton.addActionListener(this);
		queryFirstButton.addActionListener(this);
		queryLastButton.addActionListener(this);
		clearTextPaneButton.addActionListener(this);
		resultPane = new JTextPane();
		resultPane.setEditable(false);
		resultScrollPane = new JScrollPane(resultPane);
		resultScrollPane.setPreferredSize(new Dimension(380, 160));
		add(queryItemIDLabel);
		add(queryItemIDText);
		add(queryOneButton);
		add(queryFirstButton);
		add(queryLastButton);
		add(resultScrollPane);
		add(clearTextPaneButton);
		layout.putConstraint(SpringLayout.WEST, queryItemIDLabel, 120, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, queryItemIDLabel, 20, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.WEST, queryItemIDText, 20, SpringLayout.EAST, queryItemIDLabel);
		layout.putConstraint(SpringLayout.NORTH, queryItemIDText, 16, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.WEST, queryFirstButton, 25, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, queryFirstButton, 20, SpringLayout.SOUTH, queryItemIDLabel);
		layout.putConstraint(SpringLayout.WEST, queryOneButton, 20, SpringLayout.EAST, queryFirstButton);
		layout.putConstraint(SpringLayout.NORTH, queryOneButton, 20, SpringLayout.SOUTH, queryItemIDLabel);
		layout.putConstraint(SpringLayout.WEST, queryLastButton, 20, SpringLayout.EAST, queryOneButton);
		layout.putConstraint(SpringLayout.NORTH, queryLastButton, 20, SpringLayout.SOUTH, queryItemIDLabel);
		layout.putConstraint(SpringLayout.WEST, resultScrollPane, 50, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, resultScrollPane, 20, SpringLayout.SOUTH, queryOneButton);
		layout.putConstraint(SpringLayout.WEST, clearTextPaneButton, 172, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, clearTextPaneButton, 10, SpringLayout.SOUTH, resultScrollPane);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == queryOneButton) {
			Object a;
			try {
				a = Integer.parseInt(queryItemIDText.getText().trim());
			} catch (Exception e2) {
				JOptionPane.showMessageDialog(null, "illegal goods ID!");
				return;
			}
			String[] info;
			if ((info = cartMap.get(a)) != null) {
				String queryText = queryItemIDText.getText().trim();
				insertMsg("Query OK! Goods ID: ", false, Color.GRAY, 15);
				insertMsg(queryText + "\n", true, Color.BLACK, 15);
				insertMsg("Goods Name: " + info[0] + " - ", false, Color.BLACK, 15);
				insertMsg("Count: " + info[1] + " - ", false, Color.BLACK, 15);
				insertMsg("Price: " + info[2] + "\n", false, Color.BLACK, 15);
				queryItemIDText.setText(null);
			} else {
				String queryText = queryItemIDText.getText().trim();
				insertMsg("Query Failed! Goods ID: ", false, Color.GRAY, 15);
				insertMsg(queryText, true, Color.RED, 15);
				insertMsg(" NO Match Goods!\n", false, Color.black, 15);
			}
		} else if (e.getSource() == queryFirstButton) {
			if (cartMap.isEmpty() == true) {
				insertMsg("Query Failed! No Item In Cart!\n", false, Color.GRAY, 15);
				return;
			}
			String[] info;
			for (int i = 0; i <= UIManager.itemID; i++) {
				if ((info = cartMap.get(i)) != null) {
					insertMsg("Query OK! Goods ID: ", false, Color.GRAY, 15);
					insertMsg(i + "\n", true, Color.BLACK, 15);
					insertMsg("Goods Name: " + info[0] + " - ", false, Color.BLACK, 15);
					insertMsg("Count: " + info[1] + " - ", false, Color.BLACK, 15);
					insertMsg("Price: " + info[2] + "\n", false, Color.BLACK, 15);
					return;
				}
			}
		} else if (e.getSource() == queryLastButton) {
			if (cartMap.isEmpty() == true) {
				insertMsg("Query Failed! No Item In Cart!\n", false, Color.GRAY, 15);
				return;
			}
			String[] info;
			for (int i = UIManager.itemID; i >= 0; i--) {
				if ((info = cartMap.get(i)) != null) {
					insertMsg("Query OK! Goods ID: ", false, Color.GRAY, 15);
					insertMsg(i + "\n", true, Color.BLACK, 15);
					insertMsg("Goods Name: " + info[0] + " - ", false, Color.BLACK, 15);
					insertMsg("Count: " + info[1] + " - ", false, Color.BLACK, 15);
					insertMsg("Price: " + info[2] + "\n", false, Color.BLACK, 15);
					return;
				}
			}
		} else if (e.getSource() == clearTextPaneButton) {
			resultPane.setText(null);
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
