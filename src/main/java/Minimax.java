import com.github.bhlangonijr.chesslib.Board;
import com.github.bhlangonijr.chesslib.Square;
import com.github.bhlangonijr.chesslib.move.Move;

import javax.management.Notification;
import java.util.List;

public class Minimax {

    public static int calcTotal = 0;

    public static Move nextMove(int depth, boolean white){
        Move best = new Move(Square.A2, Square.A3);
        Board temp = new Board();
        temp.loadFromFen(BoardController.getBoard().getFen());
        double evalMax = -1000;
        for(Move j : BoardController.getBoard().legalMoves()){
            temp.doMove(j);
            double eval = Minimax.minimaxAlphaBeta(depth, false, temp, -100000, 100000, white);
            if(eval > evalMax){
                evalMax = eval;
                best = j;
            }
            temp.loadFromFen(BoardController.getBoard().getFen());
        }
        return best;
    }

    public static double minimaxAlphaBeta(int depth, boolean maximisingPlayer, Board pos, double alpha, double beta, boolean white){
        if(depth == 0 || pos.isStaleMate() || pos.isMated() || pos.isDraw()){
            return Eval.eval(pos, white);
        }
        calcTotal++;

        if(maximisingPlayer){
            double maxEval = -10000;
            List<Move> ls = pos.legalMoves();
            Board temp = new Board();
            temp.loadFromFen(pos.getFen());
            for(Move m : ls){
                temp.doMove(m);
                double eval = minimaxAlphaBeta(depth - 1,  false, temp, alpha, beta, white);
                maxEval = Math.max(maxEval, eval);
                alpha = Math.max(alpha, eval);
                if(beta <= alpha) break;
                temp.loadFromFen(pos.getFen());
            }
            return maxEval;
        }else{
            double minEval = 1000000;
            List<Move> ls = pos.legalMoves();
            Board temp = new Board();
            temp.loadFromFen(pos.getFen());
            for(Move m : ls){
                temp.doMove(m);
                double eval = minimaxAlphaBeta(depth - 1,  true, temp, alpha, beta, white);
                minEval = Math.min(minEval, eval);
                beta = Math.min(beta, eval);
                if(beta <= alpha) break;
                temp.loadFromFen(pos.getFen());
            }
            return minEval;
        }
    }
}
