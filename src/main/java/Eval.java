import com.github.bhlangonijr.chesslib.Board;
import com.github.bhlangonijr.chesslib.Piece;

public class Eval {
    public static double eval(Board br, boolean white){
        int queen = br.getPieceLocation(Piece.WHITE_QUEEN).size() * 9;
        int bishop = br.getPieceLocation(Piece.WHITE_BISHOP).size() * 3;
        int rook = br.getPieceLocation(Piece.WHITE_ROOK).size() * 5;
        int knight = br.getPieceLocation(Piece.WHITE_KNIGHT).size() * 3;
        int pawn = br.getPieceLocation(Piece.WHITE_PAWN).size();

        int queen2 = br.getPieceLocation(Piece.BLACK_QUEEN).size() * 9;
        int bishop2 = br.getPieceLocation(Piece.BLACK_BISHOP).size() * 3;
        int rook2 = br.getPieceLocation(Piece.BLACK_ROOK).size() * 5;
        int knight2 = br.getPieceLocation(Piece.BLACK_KNIGHT).size() * 3;
        int pawn2 = br.getPieceLocation(Piece.BLACK_PAWN).size();

        if(white) return queen - queen2 +
                bishop - bishop2 +
                rook - rook2 +
                knight - knight2 +
                pawn - pawn2;
        else return queen2 - queen +
                bishop2 - bishop +
                rook2 - rook +
                knight2 - knight +
                pawn2 - pawn;
    }
}
