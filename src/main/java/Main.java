import com.github.bhlangonijr.chesslib.Piece;
import com.github.bhlangonijr.chesslib.move.Move;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {


        Scanner scan = new Scanner(System.in);

        int last = 0;
        while(!BoardController.getBoard().isMated()) {
            Move best = Minimax.nextMove(4, true);
            BoardController.getBoard().doMove(best);
            System.out.println("--------------------------");
            BoardController.printBoard();
            System.out.println("calc: " + (Minimax.calcTotal - last));
            last = Minimax.calcTotal;
            System.out.println("--------------------------");


//            System.out.println(BoardController.getBoard().getFen());
//            Image image = null;
//            String fen = BoardController.getBoard().getFen().replaceAll(" ", "%20");
//            URL url = new URL("https://fen2png.com/api/?fen=" + fen + "&raw=true");
//            image = ImageIO.read(url);
//            url.openConnection();
//            ImageIO.write((RenderedImage) image, "png", new File("src/main/java/output.png"));



//            System.out.println("--------------------------");

////            while(true) {
////                Square a = Square.valueOf(scan.next());
////                Square b = Square.valueOf(scan.next());
////                boolean pos = BoardController.getBoard().isMoveLegal(new Move(a, b), true);
////
////                if (pos){
////                    BoardController.move(a, b);
////                    break;
////                }
////            }

            Move best2 = Minimax.nextMove(4, false);
            BoardController.getBoard().doMove(best2);

            System.out.println("--------------------------");
            BoardController.printBoard();
            System.out.println("calc: " + (Minimax.calcTotal - last));
            last = Minimax.calcTotal;

//            fen = BoardController.getBoard().getFen().replaceAll(" ", "%20");
//            url = new URL("https://fen2png.com/api/?fen=" + fen + "&raw=true");
//            image = ImageIO.read(url);
//            url.openConnection();
//            ImageIO.write((RenderedImage) image, "png", new File("src/main/java/output.png"));
//            System.out.println(BoardController.getBoard().getFen());
            System.out.println("--------------------------");
        }

    }

}
