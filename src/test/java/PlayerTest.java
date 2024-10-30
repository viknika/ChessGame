import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.stream.Stream;

public class PlayerTest
{
    private static ArrayList<Player> players;
    private static HashMap<String, Piece> pieceHashMap;

    @BeforeAll
    public static void setUp()
    {
        ChessMain main = new ChessMain();
        players = main.createPlayers();
        pieceHashMap = main.createPieces();
    }

    @BeforeEach
    public void beforeEachTest()
    {
        System.out.println("Running before each");
    }

    @AfterEach
    public void afterEachTest()
    {
        System.out.println("Running after each");
    }

    @AfterAll
    public static void tearDown()
    {
        System.out.println("Running after all the tests");
    }

    @Test
    public void validMoveTest()
    {
        players.get(0).movePiece(pieceHashMap.get("white_king"),new Spot("H",8) );
        Assertions.assertEquals(8,pieceHashMap.get("white_king").getSpot().getY());
    }

    @Test
    public void invalidMovePieceColorTest()
    {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            players.get(0).movePiece(pieceHashMap.get("black_king"),new Spot("H",8) );
        });
    }

    @Test
    public void invalidMoveBadSpotTest()
    {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            players.get(0).movePiece(pieceHashMap.get("white_king"),new Spot("Z",8) );
        });
    }

    @Test
    public void playerCreationSuccessTest()
    {
        Player whitePlayer = new Player("AAA BBBB", "aaa.bbb@gmail.com",true, 150,35);
        Assertions.assertEquals("AAA BBBB", whitePlayer.getName());
        Assertions.assertEquals("aaa.bbb@gmail.com", whitePlayer.getEmail());
        Assertions.assertTrue(whitePlayer.isWhite());
        Assertions.assertEquals(150, whitePlayer.getRank());
        Assertions.assertEquals(35, whitePlayer.getAge());

    }

    @ParameterizedTest
    @MethodSource("nameError")
    public void playerCreationIncorrectNameTest(String name)
    {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
        Player whitePlayer = new Player(name, "aaa.bbb@gmail.com",true, 150,35);

    });
    }


    @ParameterizedTest
    @MethodSource("emailError")
    public void playerCreationIncorrectEmailTest(String email)
    {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Player whitePlayer = new Player("AAA BBBB", email,true, 150,35);

        });
    }

    static Stream<String> nameError()
    {
        return Stream.of("", "  ", null);
    }

    static Stream<String> emailError()
    {
        return Stream.of("", "  ", null, "aaa.bbbgmail.com", "aaabbb@gmailcom");
    }
}
