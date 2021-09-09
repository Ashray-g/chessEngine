package lichess;

import com.github.bhlangonijr.chesslib.Square;
import com.github.bhlangonijr.chesslib.move.Move;
import com.github.bhlangonijr.chesslib.move.MoveList;
import engine.BoardController;
import engine.LoadGames;
import engine.Minimax;
import engine.Setup;

import java.io.IOException;

public class Jumpstarter {
    public static void main(String[] args) throws Exception {
        Setup.setup();
        LoadGames.load();

//        String game = APIControl.createChallenge(4);
        // String game = APIControl.createChallenge("Sidd444", true);
        String game = "rAkVCl64WAF0";

        System.out.println("Id: " + game);

        Thread.sleep(1000);

        int moves = 10;

        while(!BoardController.getBoard().isMated()
                && !BoardController.getBoard().isStaleMate()
                && !BoardController.getBoard().isDraw()){

            BoardController.getBoard().loadFromFen("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1");

            MoveList boardLichess = Wrapper.getMoves(game);
            while(boardLichess.size() % 2 == 1){
                Thread.sleep(2000);
                boardLichess = Wrapper.getMoves(game);
            }

            for(Move g : boardLichess){
                BoardController.getBoard().doMove(g);
            }

            if(!(!BoardController.getBoard().isMated()
                    && !BoardController.getBoard().isStaleMate()
                    && !BoardController.getBoard().isDraw())){
                break;
            }

            BoardController.printBoard();
            Move best;
            if(moves!=0) {
                best = Minimax.nextMove(4, true);
            }else{
                best = new Move(Square.E2, Square.E4);
            }

            BoardController.getBoard().doMove(best);

            APIControl.playMove(best, game);
            moves++;

        }
    }
}
