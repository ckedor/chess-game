package chess;

import boardgame.Board;
import boardgame.Position;
import chess.pieces.King;
import chess.pieces.Rook;

public class ChessMatch {
	
	private Board board;
	
	public ChessMatch() {
		board = new Board(8,8);
		inicialSetup();
	}
	
	/*Retorna uma matriz de peças de xadrez (O estado atual do jogo)*/
	public ChessPiece[][] getPieces(){     
		
		/*Criando uma Matriz de peças de xadrez*/
		ChessPiece[][] mat = new ChessPiece[board.getRows()][board.getColumns()];
	    
		/*Pegando as peças do tabuleiro (Downcasting: Transforma Piece em ChessPiece) */
		for (int i=0;i<board.getRows();i++) {
			for (int j=0; j<board.getColumns();j++) {
				mat[i][j] = (ChessPiece)board.getPiece(i, j);
			}
		}
		return mat;
	}
	
	private void inicialSetup() {
		board.placePiece(new Rook(board, Color.WHITE), new Position(2,1));
		board.placePiece(new King(board, Color.BLACK), new Position(3,1));
	}
	
}
