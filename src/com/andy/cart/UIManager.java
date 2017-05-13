package com.andy.cart;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

import javax.swing.*;

@SuppressWarnings("serial")
public class UIManager extends JFrame {

	JPanel title = null;
	JTabbedPane funcPane = null;
	CartInsert cartInsert = null;
	CartDelete cartDelete = null;
	CartQuery cartQuery = null;
	CartSettlement cartSettlement = null;
	Map<Integer, String[]> cartMap = null;
	public static int itemID = 0;

	public UIManager() {
		super("Cart System");
		cartMap = new HashMap<Integer, String[]>();
		funcPane = new JTabbedPane(JTabbedPane.TOP);
		title = new JPanel();
		title.add(new JLabel("Cart Manager"));
		cartInsert = new CartInsert(cartMap);
		cartDelete = new CartDelete(cartMap);
		cartQuery = new CartQuery(cartMap);
		cartSettlement = new CartSettlement(cartMap);
		funcPane.add("Add Item", cartInsert);
		funcPane.add("Delete Item", cartDelete);
		funcPane.add("Query Item", cartQuery);
		funcPane.add("Settlement", cartSettlement);
		setLayout(new BorderLayout());
		add(title, BorderLayout.NORTH);
		add(funcPane, BorderLayout.CENTER);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setVisible(true);
		setBounds(0, 0, 500, 400);
		validate();
	}

}
