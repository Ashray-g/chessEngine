package lichess;

import com.github.bhlangonijr.chesslib.move.MoveList;

import java.io.IOException;

public class Wrapper {
    public static MoveList getMoves(String gameId) throws IOException {
        String moves = APIControl.update(gameId);
        MoveList mv = new MoveList();
        mv.loadFromSan(moves);
        return mv;
    }

}
