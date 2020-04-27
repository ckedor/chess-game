package chess;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;
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
	
	public boolean[][] possibleMoves(ChessPosition sourcePosition){
		Position position = sourcePosition.toPosition();
		validateSourcePosition(position);
		return board.getPiece(position).possibleMoves();
	}
	
	public ChessPiece performChessMove(ChessPosition sourcePosition, ChessPosition targetPosition) {
		Position source = sourcePosition.toPosition();
		Position target = targetPosition.toPosition();
		validateSourcePosition(source);
		validateTargetPosition(source, target);
		Piece capturedPiece = makeMove(source, target);
		return (ChessPiece)capturedPiece;
	}
	
	private Piece makeMove(Position source, Position target) {
		Piece p = board.removePiece(source);
		Piece capturedPiece = board.removePiece(target);
		board.placePiece(p, target);
		
		return capturedPiece;
		
	}
	
	private void validateSourcePosition(Position pos) {
		if (!board.positionFull(pos))
			throw new ChessException("Error performing Chess Move: There is no piece in position " + pos);
		if (!board.getPiece(pos).isThereAnyPossibleMove()) {
			throw new ChessException("Error performing Chess Move: There is no possible move for the selected piece");
		}
	}
	
	private void validateTargetPosition(Position source, Position target) {
		if (!board.getPiece(source).possibleMove(target))
			throw new ChessException("Error performing Chess Move: The chosed piece can't move to chosed position");
	}
	
	private void placeNewPiece(char column, int row, ChessPiece piece) {
		board.placePiece(piece, new ChessPosition(column, row).toPosition());
	}
	
	private void inicialSetup() {
		placeNewPiece('a', 1, new Rook(board, Color.WHITE));
	}
	
	
	
}
