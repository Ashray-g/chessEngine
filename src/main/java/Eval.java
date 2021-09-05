import com.github.bhlangonijr.chesslib.*;

import java.util.List;

public class Eval {
    public static double basicEval(Board br, boolean white){
        int queen = br.getPieceLocation(Piece.WHITE_QUEEN).size() * BonusBoards.pieceValue.get(Piece.BLACK_QUEEN);
        int bishop = br.getPieceLocation(Piece.WHITE_BISHOP).size() * BonusBoards.pieceValue.get(Piece.BLACK_BISHOP);
        int rook = br.getPieceLocation(Piece.WHITE_ROOK).size() * BonusBoards.pieceValue.get(Piece.BLACK_ROOK);
        int knight = br.getPieceLocation(Piece.WHITE_KNIGHT).size() * BonusBoards.pieceValue.get(Piece.BLACK_KNIGHT);
        int pawn = br.getPieceLocation(Piece.WHITE_PAWN).size() * BonusBoards.pieceValue.get(Piece.BLACK_PAWN);

        int queen2 = br.getPieceLocation(Piece.BLACK_QUEEN).size() * BonusBoards.pieceValue.get(Piece.BLACK_QUEEN);
        int bishop2 = br.getPieceLocation(Piece.BLACK_BISHOP).size() * BonusBoards.pieceValue.get(Piece.BLACK_BISHOP);
        int rook2 = br.getPieceLocation(Piece.BLACK_ROOK).size() * BonusBoards.pieceValue.get(Piece.BLACK_ROOK);
        int knight2 = br.getPieceLocation(Piece.BLACK_KNIGHT).size() * BonusBoards.pieceValue.get(Piece.BLACK_KNIGHT);
        int pawn2 = br.getPieceLocation(Piece.BLACK_PAWN).size() * BonusBoards.pieceValue.get(Piece.BLACK_PAWN);

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
    public static double evalLocations(Board br, boolean white){

        int queen = PieceScore(Piece.WHITE_QUEEN, br);
        int bishop = PieceScore(Piece.WHITE_BISHOP, br);
        int rook = PieceScore(Piece.WHITE_ROOK, br);
        int knight = PieceScore(Piece.WHITE_KNIGHT, br);
        int pawn = PieceScore(Piece.WHITE_PAWN, br);

        int queen2 = PieceScore(Piece.BLACK_QUEEN, br);
        int bishop2 = PieceScore(Piece.BLACK_BISHOP, br);
        int rook2 = PieceScore(Piece.BLACK_ROOK, br);
        int knight2 = PieceScore(Piece.BLACK_KNIGHT, br);
        int pawn2 = PieceScore(Piece.BLACK_PAWN, br);
        //add king

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
    public static int PieceScore(Piece p, Board br){
        if(p.getPieceSide() == Side.WHITE) {
            List<Square> loc = br.getPieceLocation(p);
            int score = BonusBoards.pieceValue.get(p) * loc.size();

            for(Square sq : loc){
                Rank r = sq.getRank();
                File f = sq.getFile();
                int x = f.getNotation().charAt(0) - 'A';
                int y = Integer.parseInt(r.getNotation())-1;

                int lo = (7 - y) * 8 + x;
                PieceType pt = p.getPieceType();
                int bonus = BonusBoards.bonus.get(pt)[lo];
                score += bonus;
            }
            return score;

        }else{
            List<Square> loc = br.getPieceLocation(p);
            int score = BonusBoards.pieceValue.get(p) * loc.size();

            for(Square sq : loc){
                Rank r = sq.getRank();
                File f = sq.getFile();
                int x = f.getNotation().charAt(0) - 'A';
                int y = 7 - Integer.parseInt(r.getNotation())+1;
//                System.out.println(x + " " + y);
//
                int lo = (7 - y) * 8 + x;
                PieceType pt = p.getPieceType();
                int bonus = BonusBoards.bonus.get(pt)[lo];
                score += bonus;
            }
            return score;
        }
    }
}
