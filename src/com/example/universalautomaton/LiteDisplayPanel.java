package com.example.universalautomaton;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class LiteDisplayPanel extends JPanel{
	private static final long serialVersionUID = 1L;
	Board board;
	int rows;
	int cols;
	int cellsSideSize;
	
	public LiteDisplayPanel(Board board, int cellSideSize) {
		super();
		this.board = board;
		this.cellsSideSize = cellSideSize;
		this.rows = board.getRows();
		this.cols = board.getCols();
	}

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		
		for(int j=0, l=0; j<(cols-1)*cellsSideSize; j+=cellsSideSize, l++)
			for(int i=0, k=0; i<(rows-1)*cellsSideSize; i+=cellsSideSize, k++)
			{
				if(board.getCell(k, l).getState() == C.PADD) {
					continue;
				}
				else if(board.getCell(k, l).getState() == C.OFF) {
					g2d.setPaint(Color.BLACK);
				}
				else if(board.getCell(k, l).getState() == C.ON) {
					g2d.setPaint(Color.ORANGE);
				}
				else if(board.getCell(k, l).getState() == C.HEAD) {
					g2d.setPaint(Color.BLUE);
				}
				else if(board.getCell(k, l).getState() == C.TAIL) {
					g2d.setPaint(Color.RED);
				}
				g2d.drawRect(j, i, cellsSideSize, cellsSideSize);
				g2d.fillRect(j, i, cellsSideSize, cellsSideSize);
			}
					
	}
	
}
