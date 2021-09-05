import com.github.bhlangonijr.chesslib.Piece;
import com.github.bhlangonijr.chesslib.PieceType;

public class Setup {
    static String[] white = {"♔", "♕", "♖", "♗", "♘", "♙"};
    static String[] black = {"♚", "♛", "♜", "♝", "♞", "♟︎"};
    public static void setup(){
        BoardController.map.put(Piece.BLACK_KING, black[0]);
        BoardController.map.put(Piece.BLACK_QUEEN, black[1]);
        BoardController.map.put(Piece.BLACK_ROOK, black[2]);
        BoardController.map.put(Piece.BLACK_BISHOP, black[3]);
        BoardController.map.put(Piece.BLACK_KNIGHT, black[4]);
        BoardController.map.put(Piece.BLACK_PAWN, black[5]);


        BoardController.map.put(Piece.WHITE_KING, white[0]);
        BoardController.map.put(Piece.WHITE_QUEEN, white[1]);
        BoardController.map.put(Piece.WHITE_ROOK, white[2]);
        BoardController.map.put(Piece.WHITE_BISHOP, white[3]);
        BoardController.map.put(Piece.WHITE_KNIGHT, white[4]);
        BoardController.map.put(Piece.WHITE_PAWN, white[5]);

        BonusBoards.pieceValue.put(Piece.BLACK_QUEEN, 900);
        BonusBoards.pieceValue.put(Piece.BLACK_ROOK, 500);
        BonusBoards.pieceValue.put(Piece.BLACK_BISHOP, 300);
        BonusBoards.pieceValue.put(Piece.BLACK_KNIGHT, 300);
        BonusBoards.pieceValue.put(Piece.BLACK_PAWN, 100);

        BonusBoards.pieceValue.put(Piece.WHITE_QUEEN, 900);
        BonusBoards.pieceValue.put(Piece.WHITE_ROOK, 500);
        BonusBoards.pieceValue.put(Piece.WHITE_BISHOP, 300);
        BonusBoards.pieceValue.put(Piece.WHITE_KNIGHT, 300);
        BonusBoards.pieceValue.put(Piece.WHITE_PAWN, 100);

        BonusBoards.bonus.put(PieceType.BISHOP, BonusBoards.bishop);
        BonusBoards.bonus.put(PieceType.KNIGHT, BonusBoards.knight);
        BonusBoards.bonus.put(PieceType.ROOK, BonusBoards.rook);
        BonusBoards.bonus.put(PieceType.KING, BonusBoards.king);
        BonusBoards.bonus.put(PieceType.QUEEN, BonusBoards.queen);
        BonusBoards.bonus.put(PieceType.PAWN, BonusBoards.pawn);

    }
}
