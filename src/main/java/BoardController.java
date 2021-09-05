import com.github.bhlangonijr.chesslib.Board;
import com.github.bhlangonijr.chesslib.Piece;
import com.github.bhlangonijr.chesslib.Side;
import com.github.bhlangonijr.chesslib.Square;
import com.github.bhlangonijr.chesslib.move.Move;

import java.util.HashMap;
import java.util.Map;

public class BoardController {
    private static Board board = new Board();

    static String[] white = {"♔", "♕", "♖", "♗", "♘", "♙"};
    static String[] black = {"♚", "♛", "♜", "♝", "♞", "♟︎"};
    static HashMap<Piece, String> map = new HashMap<>();


    public static Board getBoard() {
        return board;
    }

    public static void printBoard2(){
        System.out.println(board.toString());
    }

    public static void printBoard(){
        map.put(Piece.BLACK_KING, black[0]);
        map.put(Piece.BLACK_QUEEN, black[1]);
        map.put(Piece.BLACK_ROOK, black[2]);
        map.put(Piece.BLACK_BISHOP, black[3]);
        map.put(Piece.BLACK_KNIGHT, black[4]);
        map.put(Piece.BLACK_PAWN, black[5]);


        map.put(Piece.WHITE_KING, white[0]);
        map.put(Piece.WHITE_QUEEN, white[1]);
        map.put(Piece.WHITE_ROOK, white[2]);
        map.put(Piece.WHITE_BISHOP, white[3]);
        map.put(Piece.WHITE_KNIGHT, white[4]);
        map.put(Piece.WHITE_PAWN, white[5]);

        for(int i = 0;i<8;i++){
            for(int j = 0;j<8;j++){
                String u = map.get(board.getPiece(Square.fromValue((char)('A'+j)+""+(i+1))));
                if(u == null) System.out.print("_ ");
                else System.out.print(u + " ");
            }
            System.out.println();
        }


        board.getFen();

    }

    public static void move(Square a, Square b){
        board.doMove(new Move(a, b));
    }

}
