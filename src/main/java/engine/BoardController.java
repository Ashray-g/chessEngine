package engine;

import com.github.bhlangonijr.chesslib.Board;
import com.github.bhlangonijr.chesslib.Piece;
import com.github.bhlangonijr.chesslib.Side;
import com.github.bhlangonijr.chesslib.Square;
import com.github.bhlangonijr.chesslib.game.Event;
import com.github.bhlangonijr.chesslib.game.Game;
import com.github.bhlangonijr.chesslib.game.Round;
import com.github.bhlangonijr.chesslib.move.Move;

import java.util.HashMap;
import java.util.Map;

public class BoardController {
    private static Board board = new Board();

    static HashMap<Piece, String> map = new HashMap<>();

    public static Board getBoard() {
        return board;
    }

    public static void printBoard2(){
        System.out.println(board.toString());
    }

    public static void printBoard(){

        for(int i = 7;i>=0;i--){
            System.out.print((i+1) + " ");
            for(int j = 0;j<8;j++){
                String u = map.get(board.getPiece(Square.fromValue((char)('A'+j)+""+(i+1))));
                if(u == null) System.out.print("_ ");
                else System.out.print(u + " ");
            }
            System.out.println();
        }
        System.out.println("+ A B C D E F G H");


        board.getFen();

    }

    public static void move(Square a, Square b){
        board.doMove(new Move(a, b));
    }

}
