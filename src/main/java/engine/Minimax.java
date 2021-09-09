package engine;

import com.github.bhlangonijr.chesslib.Board;
import com.github.bhlangonijr.chesslib.Square;
import com.github.bhlangonijr.chesslib.move.Move;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class Minimax {

    public static int calcTotal = 0;

    static HashMap<Long, Double> evals = new HashMap<>();

    public static Move nextMove(int depth, boolean white){
        Move best = new Move(Square.A2, Square.A3);
        Board temp = new Board();
        temp.loadFromFen(BoardController.getBoard().getFen());
        double evalMax = -1000000;
        int ct = 0;
        List<Move> ms = BoardController.getBoard().legalMoves();
        ms.sort(new Sort.MoveComparator());
        for(Move j : ms){
            temp.doMove(j);
            double eval = Minimax.minimaxAlphaBeta(depth, false, temp, -100000, 100000, white);
            if(eval > evalMax){
                evalMax = eval;
                best = j;
            }
            ct++;
            System.out.print("\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b");
            System.out.print("[" + ct + "/" + ms.size() + "] (" + Math.round(((double)ct*100/ms.size())) + "%)");
            temp.loadFromFen(BoardController.getBoard().getFen());
        }
        System.out.println();
        System.out.println(evalMax + " " + best);
        return best;
    }

    public static double minimaxAlphaBeta(int depth, boolean maximisingPlayer, Board pos, double alpha, double beta, boolean white){
        if(depth == 0 || pos.isStaleMate() || pos.isMated() || pos.isDraw()){
            double ev = Eval.evalLocations(pos, white);
//            double ev = engine.Eval.basicEval(pos, white);
//            long zob = pos.getZobristKey();
//            if(evals.containsKey(zob)) return evals.get(zob);
//            else evals.put(zob, ev);
            return ev;
        }
        calcTotal++;

        if(maximisingPlayer){
            double maxEval = -10000;
            List<Move> ls = pos.legalMoves();

            ls.sort(new Sort.MoveComparator());

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
