package com.example.universalautomaton;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class StructPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private MainWindow parent;
	private JLabel nameLabel;
	private JButton rotateBtn;
	private JButton mirrorBtn;
	private JPanel displayPanel;
	private Board board;
	private String name;
	private String filename;
	private LoadBoardFromFile loadFromFileObject = new LoadBoardFromFile(C.WW);
	private int rows, cols, count;
	
	public StructPanel(String filename, int count, MainWindow mainWindow) {
		super();
		this.filename = filename;
		this.count = count;
		this.count += 6;
		this.count /= 6;
		parent = mainWindow;
		parseName(filename);
		loadFromFileObject.setUsersCatalogPath(filename);
		board = loadFromFileObject.loadBoardFromFile("");
		rows = board.getRows();
		cols = board.getCols();
		setupButtons();
		setupDisplayPanel();
		setupLayout();
	}	

	private void parseName(String filename) {
		String[] directories = filename.split("\\\\");
		String file = directories[directories.length - 1];
		name = file.split("\\.")[0];
	}
	
	private void setupButtons() {
		rotateBtn = new JButton ("rotate");
		rotateBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
			}
		});
		mirrorBtn = new JButton ("mirror");
		mirrorBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
			}
		});
		board.setActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String[] position = e.getActionCommand().split(" ");
				int i = Integer.parseInt(position[0]);
				int j = Integer.parseInt(position[1]);
				parent.startStructListener(filename, i, j);
			}
		});
	}
	
	private void setupLayout() {
		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		c.insets = new Insets(1,1,1,1);
		c.gridx = 0;
		c.gridy = 0;
		nameLabel = new JLabel(name);
		add(nameLabel, c);
		c.gridx = 1;
		add(rotateBtn, c);
		c.gridx = 2;
		add(mirrorBtn, c);
		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 3;
		c.gridheight = 4;
		c.fill = GridBagConstraints.VERTICAL;
		c.insets = new Insets(5,5,5,5);
		add(displayPanel, c);
	}
	
	private void setupDisplayPanel() {
		displayPanel = new JPanel();
		int cellSideSize = Toolkit.getDefaultToolkit().getScreenSize().width/7/cols;
		if (cellSideSize > Toolkit.getDefaultToolkit().getScreenSize().height/count*4/5/rows)
			cellSideSize = Toolkit.getDefaultToolkit().getScreenSize().height/count*4/5/rows;
		board.changeCellsSize(new Dimension(cellSideSize,cellSideSize));
		for(int i=0; i<rows; i++)
			for(int j=0; j<cols; j++) {
				displayPanel.add(board.getCell(i, j));
			}
		displayPanel.setLayout(new GridLayout(rows,cols));
		displayPanel.setPreferredSize(new Dimension(cellSideSize*cols,cellSideSize*rows));
		displayPanel.setMinimumSize(new Dimension(cellSideSize*cols,cellSideSize*rows));
		displayPanel.setMaximumSize(new Dimension(cellSideSize*cols,cellSideSize*rows));
		displayPanel.setVisible(true);
	}
}