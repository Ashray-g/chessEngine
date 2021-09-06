package engine;

import com.github.bhlangonijr.chesslib.File;
import com.github.bhlangonijr.chesslib.game.Game;
import com.github.bhlangonijr.chesslib.move.MoveList;
import com.github.bhlangonijr.chesslib.pgn.PgnHolder;

import java.util.ArrayList;

public class LoadGames {
    static ArrayList<Game> grandMasterGames = new ArrayList<>();
    static ArrayList<MoveList> grandMasterGamesMoves = new ArrayList<>();
    public static void load() throws Exception {
//        for(int i = 1;i<=3;i++){
//            PgnHolder pgn = new PgnHolder("src/main/java/games/master_games("+i+").pgn");
//            pgn.loadPgn();
//            grandMasterGames.addAll(pgn.getGames());
//        }
        PgnHolder pgn = new PgnHolder("src/main/java/games/Nakamura.pgn");
        PgnHolder pgn2 = new PgnHolder("src/main/java/games/Kasparov.pgn");
        pgn.loadPgn();
        pgn2.loadPgn();
        grandMasterGames.addAll(pgn.getGames());
        grandMasterGames.addAll(pgn2.getGames());
        for(Game g : grandMasterGames){
            grandMasterGamesMoves.add(g.getCurrentMoveList());
        }
        System.out.println(grandMasterGamesMoves.size());
    }
}
