package engine;

import com.github.bhlangonijr.chesslib.Piece;
import com.github.bhlangonijr.chesslib.PieceType;
import com.github.bhlangonijr.chesslib.Square;
import com.github.bhlangonijr.chesslib.move.Move;

import java.util.Comparator;

public class Sort {
    public static class MoveComparator implements Comparator<Move>
    {
        public int compare(Move c1, Move c2)
        {
            double m1 = 0;
            double m2 = 0;
            Square to = c1.getTo();
            Square from = c1.getFrom();
            Piece at = BoardController.getBoard().getPiece(to);
            Piece attacker = BoardController.getBoard().getPiece(from);
            if(at.getPieceType() != null && attacker.getPieceType() != null && at.getPieceSide()!= attacker.getPieceSide()){
//                System.out.println("at: " + at);
//                System.out.println("att: " + attacker);
                m1 += Eval.PieceScore(at, BoardController.getBoard())
                        - Eval.PieceScore(attacker, BoardController.getBoard());
            }

            Square to2 = c2.getTo();
            Square from2 = c2.getFrom();
            Piece at2 = BoardController.getBoard().getPiece(to2);
            Piece attacker2 = BoardController.getBoard().getPiece(from2);
            if(at2.getPieceType() != null && attacker2.getPieceType() != null  && at2.getPieceSide()!= attacker2.getPieceSide()){
                m2 += Eval.PieceScore(at2, BoardController.getBoard()) - Eval.PieceScore(attacker2, BoardController.getBoard());
            }

            int bonus1 =0;
            int bonus2 =0;
            if(attacker.getPieceType()==PieceType.PAWN && to.getRank().toString().equals("8")) bonus1+=500;
            if(attacker.getPieceType()==PieceType.PAWN && to.getRank().toString().equals("8")) bonus2+=500;

            return (int)(m1-m2 + bonus1 - bonus2);

        }
    }
}
