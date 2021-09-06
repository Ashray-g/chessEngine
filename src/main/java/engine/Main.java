package engine;

import com.github.bhlangonijr.chesslib.Board;
import com.github.bhlangonijr.chesslib.Square;
import com.github.bhlangonijr.chesslib.move.Move;
import com.github.bhlangonijr.chesslib.move.MoveList;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {

        Setup.setup();
        LoadGames.load();


        Scanner scan = new Scanner(System.in);
        int move = 0;
        int last = 0;
        MoveList gm = LoadGames.grandMasterGamesMoves.get((int)(Math.random()*6000));
        System.out.println(gm);
        while(!BoardController.getBoard().isMated() && !BoardController.getBoard().isStaleMate() && !BoardController.getBoard().isDraw()) {
//            if(move % 5 == 1) engine.Minimax.evals.clear();
            System.out.println(Minimax.evals.size());
            Move best = new Move(Square.A1, Square.A2);
            System.out.println("--------------------------");
            if(move == 0) {
                best = gm.get(0);
                System.out.println("Grandmaster move");
            }else if(move <= 5){
                boolean f = false;
                for(MoveList ml : LoadGames.grandMasterGamesMoves){
                    if(ml.size() >= 10) {
                        Board temp = new Board();
                        for (int i = 0; i < move * 2; i++) {
                            temp.doMove(ml.get(i));
                        }
                        if (temp.equals(BoardController.getBoard())) {
                            best = ml.get(move * 2);
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
            BoardController.getBoard().doMove(best);
            BoardController.printBoard();
//            System.out.println("calc: " + (engine.Minimax.calcTotal - last));
            System.out.println("engine.Eval: " + -Eval.evalLocations(BoardController.getBoard(), true));
            last = Minimax.calcTotal;
            System.out.println("--------------------------");


//            System.out.println(engine.BoardController.getBoard().getFen());
//            Image image = null;
//            String fen = engine.BoardController.getBoard().getFen().replaceAll(" ", "%20");
//            URL url = new URL("https://fen2png.com/api/?fen=" + fen + "&raw=true");
//            image = ImageIO.read(url);
//            url.openConnection();
//            ImageIO.write((RenderedImage) image, "png", new File("src/main/java/output.png"));



//            System.out.println("--------------------------");

            while(true) {
                Square a = Square.valueOf(scan.next());
                Square b = Square.valueOf(scan.next());
                boolean pos = BoardController.getBoard().isMoveLegal(new Move(a, b), true);

                if (pos){
                    BoardController.move(a, b);
                    break;
                }
            }

            if(BoardController.getBoard().isMated() || BoardController.getBoard().isStaleMate() || BoardController.getBoard().isDraw()){
                break;
            }

//            Move best2 = engine.Minimax.nextMove(4, false);
//            engine.BoardController.getBoard().doMove(best2);

            System.out.println("--------------------------");
            BoardController.printBoard();
//            System.out.println("calc: " + (engine.Minimax.calcTotal - last));
//            last = engine.Minimax.calcTotal;

//            fen = engine.BoardController.getBoard().getFen().replaceAll(" ", "%20");
//            url = new URL("https://fen2png.com/api/?fen=" + fen + "&raw=true");
//            image = ImageIO.read(url);
//            url.openConnection();
//            ImageIO.write((RenderedImage) image, "png", new File("src/main/java/output.png"));
//            System.out.println(engine.BoardController.getBoard().getFen());
            System.out.println("engine.Eval: " + -Eval.evalLocations(BoardController.getBoard(), true));
            System.out.println("--------------------------");
            move+=1;
        }
        System.out.println("Stale: " + BoardController.getBoard().isStaleMate());
        System.out.println("Mate: " + BoardController.getBoard().isMated());
        System.out.println("Draw: " + BoardController.getBoard().isDraw());


    }

}
