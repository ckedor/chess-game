package boardgame;

import boardgame.BoardException;

public class Board {
	
	private int rows;
	private int columns;
	private Piece[][] pieces;
	
	public Board (int rows, int columns) {
		if (rows < 1 || columns < 1)
			throw new BoardException("Error creating board: there must be at least 1 column and 1 row");
		this.rows = rows;
		this.columns = columns;
		pieces = new Piece[rows][columns];
	}

	public int getRows() {
		return rows;
	}

	public int getColumns() {
		return columns;
	}
	
	public Piece getPiece(int row, int column) {
		if (!positionExists(row, column))
			throw new BoardException("Error getting a Piece: Position not on the board");
		return this.pieces[row][column];
	}
	
	public Piece getPiece(Position position) {
		if (!positionExists(position))
			throw new BoardException("Error getting a Piece: Position not on the board");
		return this.pieces[position.getRow()][position.getColumn()];
	}
	
	public void placePiece(Piece piece, Position pos) {
		if (positionFull(pos))
			throw new BoardException("Error placing a Piece: Position " + pos + " already has a piece");
		pieces[pos.getRow()][pos.getColumn()] = piece;
		piece.position = pos;
	}
	
	private boolean positionExists(int row, int column) {
		return row >= 0 && row < rows && column >=0 && column < columns;
	}
	
	public boolean positionExists(Position pos){
		return positionExists(pos.getRow(), pos.getColumn());
	}
	
	public boolean positionFull(Position pos) {
		if (!positionExists(pos))
			throw new BoardException("Error placing a Piece: Position not on the board");
		return getPiece(pos) != null;
	}
	
	public Piece removePiece(Position pos) {
		if (!positionExists(pos))
			throw new BoardException("Error placing a Piece: Position not on the board");
		if (getPiece(pos) == null) {
			return null;
		}
		Piece aux = getPiece(pos);
		aux.position = null;
		pieces[pos.getRow()][pos.getColumn()] = null;
		return aux;
	}
}
