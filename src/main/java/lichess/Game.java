package lichess;

import com.github.bhlangonijr.chesslib.Board;
import com.github.bhlangonijr.chesslib.Square;
import com.github.bhlangonijr.chesslib.move.Move;
import com.github.bhlangonijr.chesslib.move.MoveList;
import engine.BoardController;
import engine.LoadGames;
import engine.Minimax;
import engine.Setup;

public class Game {
    public static void main(String[] args) throws Exception {

        Setup.setup();
        LoadGames.load();

//        String game = APIControl.createChallenge(1);
        String game = APIControl.createChallenge("Sidd444", true);

        System.out.println("Id: " + game);

        Thread.sleep(10000);

        int moves = 0;
        MoveList gm = LoadGames.grandMasterGamesMoves.get(0);

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
            Move best = new Move(Square.A1, Square.A2);
            System.out.println("--------------------------");
            if(moves == 0) {
                best = gm.get(0);
                System.out.println("Grandmaster move");
            }else if(moves <= 15){
                boolean f = false;
                for(MoveList ml : LoadGames.grandMasterGamesMoves){
                    if(ml.size() >= moves*2) {
                        Board temp = new Board();
                        for (int i = 0; i < moves * 2; i++) {
                            temp.doMove(ml.get(i));
                        }
                        if (temp.equals(BoardController.getBoard())) {
                            best = ml.get(moves * 2);
                            f = true;
                            System.out.println(ml);
                            break;
                        }
                    }
                }
                if(!f) best = Minimax.nextMove(4, true);
                else System.out.println("Grandmaster move");
            }else {
                best = Minimax.nextMove(4, true);
            }
//            if(moves!=0) {
//                best = Minimax.nextMove(4, true);
//            }else{
//                best = new Move(Square.E2, Square.E4);
//            }

            BoardController.getBoard().doMove(best);

            APIControl.playMove(best, game);
            moves++;

        }
    }
}
