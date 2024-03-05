import java.util.*;

import tester.*;
import javalib.impworld.WorldScene;
import java.awt.Color;
import javalib.worldimages.*;
import javalib.impworld.World;

//interface to hold constants
interface IConstants {

  //array list for the different suits in the deck
  ArrayList<String> suits = new ArrayList<String>(Arrays.asList("♥", "♠", 
      "♦", "♣"));

  //array list for the different ranks in the deck
  ArrayList<String> ranks = new ArrayList<String>(Arrays.asList("ace", "2", "3",
      "4", "5", "6", "7", "8", "9", "10", "jack", "queen", "king"));

  //constants to retrieve each suit
  String heart = suits.get(0);
  String spade = suits.get(1);
  String diamond = suits.get(2);
  String club = suits.get(3);

  //board dimensions
  int BOARD_HEIGHT = 800;
  int BOARD_WIDTH = 1320;

  //card dimensions
  int CARD_HEIGHT = 120;
  int CARD_WIDTH = 80;

  //length/width between each card and the bounds
  int WHITESPACE = 20;

  //constant used to dictate how many flips player is allowed to use
  int FLIPSLEFT = 250;

  //constants for background of card front 
  WorldImage backgroundOuter = new RectangleImage(CARD_WIDTH, CARD_HEIGHT, 
      OutlineMode.OUTLINE, Color.black);
  WorldImage backgroundInner = new RectangleImage(CARD_WIDTH, CARD_HEIGHT, 
      OutlineMode.SOLID, Color.white);
  WorldImage background = new OverlayImage(backgroundOuter, backgroundInner);


  //to create board for examples and tests
  Card heartAce = new Card("A", heart);
  Card heart2 = new Card("2", heart);
  Card heart3 = new Card("3", heart);
  Card heart4 = new Card("4", heart);
  Card heart5 = new Card("5", heart);
  Card heart6 = new Card("6", heart);
  Card heart7 = new Card("7", heart);
  Card heart8 = new Card("8", heart);
  Card heart9 = new Card("9", heart);
  Card heart10 = new Card("10", heart);
  Card heartJack = new Card("J", heart);
  Card heartQueen = new Card("Q", heart);
  Card heartKing = new Card("K", heart);

  Card spadesAce = new Card("A", spade);
  Card spades2 = new Card("2", spade);
  Card spades3 = new Card("3", spade);
  Card spades4 = new Card("4", spade);
  Card spades5 = new Card("5", spade);
  Card spades6 = new Card("6", spade);
  Card spades7 = new Card("7", spade);
  Card spades8 = new Card("8", spade);
  Card spades9 = new Card("9", spade);
  Card spades10 = new Card("10", spade);
  Card spadesJack = new Card("J", spade);
  Card spadesQueen = new Card("Q", spade);
  Card spadesKing = new Card("K", spade);

  Card diamondsAce = new Card("A", diamond);
  Card diamonds2 = new Card("2", diamond);
  Card diamonds3 = new Card("3", diamond);
  Card diamonds4 = new Card("4", diamond);
  Card diamonds5 = new Card("5", diamond);
  Card diamonds6 = new Card("6", diamond);
  Card diamonds7 = new Card("7", diamond);
  Card diamonds8 = new Card("8", diamond);
  Card diamonds9 = new Card("9", diamond);
  Card diamonds10 = new Card("10", diamond);
  Card diamondsJack = new Card("J", diamond);
  Card diamondsQueen = new Card("Q", diamond);
  Card diamondsKing = new Card("K", diamond);

  Card clubsAce = new Card("A", club);
  Card clubs2 = new Card("2", club);
  Card clubs3 = new Card("3", club);
  Card clubs4 = new Card("4", club);
  Card clubs5 = new Card("5", club);
  Card clubs6 = new Card("6", club);
  Card clubs7 = new Card("7", club);
  Card clubs8 = new Card("8", club);
  Card clubs9 = new Card("9", club);
  Card clubs10 = new Card("10", club);
  Card clubsJack = new Card("J", club);
  Card clubsQueen = new Card("Q", club);
  Card clubsKing = new Card("K", club);

  ArrayList<Card> hearts = 
      new ArrayList<Card>(Arrays.asList(heartAce, heart2, heart3, 
          heart4, heart5, heart6, heart7, heart8, heart9, heart10, 
          heartJack, heartQueen, heartKing));

  ArrayList<Card> spades = 
      new ArrayList<Card>(Arrays.asList(spadesAce, spades2, spades3, 
          spades4, spades5, spades6, spades7, spades8, spades9, 
          spades10, spadesJack, spadesQueen, spadesKing));

  ArrayList<Card> diamonds = 
      new ArrayList<Card>(Arrays.asList(diamondsAce, diamonds2, 
          diamonds3, diamonds4, diamonds5, diamonds6, diamonds7, 
          diamonds8, diamonds9, diamonds10, diamondsJack, 
          diamondsQueen, diamondsKing));

  ArrayList<Card> clubs = 
      new ArrayList<Card>(Arrays.asList(clubsAce, clubs2, 
          clubs3, clubs4, clubs5, clubs6, clubs7, clubs8, 
          clubs9, clubs10, clubsJack, clubsQueen, clubsKing));

  ArrayList<ArrayList<Card>> board1 = new ArrayList<ArrayList<Card>>(
      Arrays.asList(hearts, spades, diamonds, clubs));

  ArrayList<Card> board = new ArrayList<Card>(Arrays.asList(heartAce, heart2, 
      heart3, heart4, heart5, heart6, heart7, heart8, heart9, heart10, heartJack,
      heartQueen, heartKing, spadesAce, spades2, spades3, spades4, spades5, 
      spades6, spades7, spades8, spades9, spades10, spadesJack, spadesQueen, spadesKing,
      diamondsAce, diamonds2, diamonds3, diamonds4, diamonds5, diamonds6, diamonds7, 
      diamonds8, diamonds9, diamonds10, diamondsJack, diamondsQueen, diamondsKing,
      clubsAce, clubs2, clubs3, clubs4, clubs5, clubs6, clubs7, clubs8, clubs9, clubs10, 
      clubsJack, clubsQueen, clubsKing));

}

//represents a card
class Card implements IConstants {
  String rank;
  String suit;
  Random rand = new Random();
  boolean side;
  int width;
  int height;
  boolean outline;

  //constructor, random has already been initialized so not included
  Card(String rank, String suit, boolean side, boolean outline) {
    this.rank = rank;
    this.suit = suit;
    this.side = side;
    this.width = CARD_WIDTH;
    this.height = CARD_HEIGHT;
    this.outline = outline;
  }

  //constructor, random has already been initialized so not included
  Card(String rank, String suit) {
    this.rank = rank;
    this.suit = suit;
    this.side = false;
    this.width = CARD_WIDTH;
    this.height = CARD_HEIGHT;
    this.outline = false;
  }

  //determines whether two cards have the same color of suit
  boolean sameSuit(Card c) {
    return (this.suit.equals(heart) 
        && (c.suit.equals(diamond)))
        || (this.suit.equals(diamond) 
            && (c.suit.equals(heart)))
        || (this.suit.equals(spade) 
            && (c.suit.equals(club)))
        || (this.suit.equals(club) 
            && (c.suit.equals(spade)));

  }

  //returns whether or not the given card and this are the same
  boolean cardEquals(Card c) {
    return this.rank.equals(c.rank) && this.sameSuit(c);
  }

  //color of text depending on suit
  Color chooseColor() {
    if (this.suit.equals(heart) || this.suit.equals(diamond)) {
      return Color.red;
    }
    else {
      return Color.black;
    }
  }

  //draws the card depending on whether it should be facing up or down
  WorldImage drawCard() {
    if (this.side) {
      if (outline) {
        return new OverlayImage(new RectangleImage(CARD_WIDTH, CARD_HEIGHT, 
            OutlineMode.OUTLINE, Color.green), this.drawCardFront());
      }
      else {
        return this.drawCardFront();
      }
    }
    else {
      return this.drawCardBack();
    }
  }

  //draws the back side of the card
  WorldImage drawCardBack() {
    WorldImage background = new FromFileImage("src/nemoCards.png");
    return background;
  }

  //draws the front side of the card
  WorldImage drawCardFront() {
    WorldImage textSuit = new TextImage(this.suit, 20, this.chooseColor());
    WorldImage textRank = new TextImage(this.rank, 20, this.chooseColor());
    WorldImage cardRankTop = new OverlayOffsetImage(textRank, 25, 40, background);
    WorldImage cardSuit = new OverlayOffsetImage(textSuit, 5, 0, cardRankTop);
    WorldImage cardRankBottom = new OverlayOffsetImage(textRank, -20, -40, cardSuit);

    return cardRankBottom;
  }

  //flips the card so that the card is facing up - FIX FIELDS OF FIELDS HERE PLEASE
  void flipCard() {
    if (this.side && !this.outline) {
      this.side = false;

    }
    else {
      this.side = true;
    }
  }

  //EFFECT: changes outline to true 
  void outlineGreen() {
    this.outline = true;
  }
}

// represents the concentration world game
class Concentration extends World implements IConstants {
  ArrayList<ArrayList<Card>> board = new ArrayList<>();
  ArrayList<Card> cardsFlipped;
  Random rand;
  int score;
  int timer;
  int remainingFlips;

  //constructor 
  Concentration() {
    this.rand = new Random();
    this.board = initBoard();
    this.cardsFlipped = new ArrayList<Card>();
    this.score = 26;
    this.timer = 0;
    this.remainingFlips = FLIPSLEFT;
  }

  //constructor for ranodm seed
  Concentration(Random rand) {
    this.rand = rand;
    this.board = initBoard();
    this.cardsFlipped = new ArrayList<Card>();
    this.score = 26;
    this.timer = 0;
    this.remainingFlips = FLIPSLEFT;
  }

  //constructor for tests
  Concentration(ArrayList<ArrayList<Card>> board) {
    this.board = board;
    this.cardsFlipped = new ArrayList<Card>();
    this.score = 20;
    this.timer = 10;
    this.remainingFlips = FLIPSLEFT;
  }

  //creates a random 2d array of cards
  ArrayList<ArrayList<Card>> initBoard() {
    ArrayList<ArrayList<Card>> board = new ArrayList<>(); 
    ArrayList<Card> deck = this.makeDeck();
    for (int i = 0; i < 4; i++) {
      ArrayList<Card> row = new ArrayList<>();
      for (int j = 0; j < 13; j++) {
        int index = rand.nextInt(deck.size());
        row.add(deck.get(index));
        deck.remove(index);
      }
      board.add(row);
    }
    return board;
  }

  //makes a deck of 52 cards
  ArrayList<Card> makeDeck() {
    ArrayList<String> suits = new ArrayList<String>(Arrays.asList("♥", "♠", 
        "♦", "♣"));
    ArrayList<String> values = new ArrayList<String>(Arrays.asList("A", "2", "3",
        "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"));
    ArrayList<Card> cards = new ArrayList<Card>();

    for (int i = 0; i < suits.size(); i++) {
      for (int j = 0; j < values.size(); j++) {
        cards.add(new Card(values.get(j), suits.get(i)));
      }
    }
    return cards;
  }

  //places the drawn board onto a world scene and outputs the world scene
  public WorldScene makeScene() {
    int y = WHITESPACE + CARD_HEIGHT / 2;
    WorldScene board = new WorldScene(BOARD_WIDTH, BOARD_HEIGHT);
    board.placeImageXY(new FromFileImage("src/oceanFloor.jpeg"), 
        BOARD_WIDTH / 2, BOARD_HEIGHT / 2);
    for (int i = 0; i < 4; i++) {
      int x = WHITESPACE + CARD_WIDTH / 2;
      for (int j = 0; j < 13; j++) {
        board.placeImageXY(this.board.get(i).get(j).drawCard(), x, y);
        x += WHITESPACE + CARD_WIDTH;
      }
      y += WHITESPACE + CARD_HEIGHT;
    }
    board.placeImageXY(new RectangleImage(2200, 100, OutlineMode.SOLID, 
        Color.gray), 500, 680);
    board.placeImageXY(new TextImage("Score: " + score, 30, 
        Color.black), 100, 680);
    board.placeImageXY(new TextImage("Flips Remaining: " + remainingFlips, 
        30, Color.black), 650, 680);
    board.placeImageXY(new TextImage("Time: " + this.timerMin() 
        + " seconds", 30, Color.black), 1150, 680);

    return board;
  }


  //if the cards match, they will be outlined in green and stay face up 
  //otherwise, they will be flipped back down
  void ifMatch(Card c1, Card c2) {
    if (c1.cardEquals(c2)) {
      c1.outlineGreen();
      c2.outlineGreen();
      score--;
    }
    else {
      c1.flipCard();
      c2.flipCard();
    }
  }

  //call flip card on the items in array
  void flipCard(int i, int j) {
    this.board.get(i).get(j).flipCard();
  }

  //mouse click to flip cards
  void clickHelp(Posn pos) {
    int topMarginY = WHITESPACE;
    int bottomMarginY = CARD_HEIGHT + WHITESPACE;

    for (int i = 0; i < suits.size(); i++) {
      if (pos.y >= topMarginY && pos.y <= bottomMarginY 
          && this.cardsFlipped.size() < 2 && this.remainingFlips > 0) {
        int topMarginX = WHITESPACE + CARD_WIDTH;
        int bottomMarginX = WHITESPACE;
        for (int j = 0; j < ranks.size(); j++) {
          if (pos.x <= topMarginX && pos.x >= bottomMarginX) {
            this.flipCard(i, j);
            this.cardsFlipped.add(this.board.get(i).get((j)));
            remainingFlips--;
          }
          topMarginX += WHITESPACE + CARD_WIDTH;
          bottomMarginX += WHITESPACE + CARD_WIDTH;
        }
      }
      topMarginY += WHITESPACE + CARD_HEIGHT;
      bottomMarginY += WHITESPACE + CARD_HEIGHT;
    }
  }

  //on mouse click
  public void onMouseClicked(Posn pos) {
    //public void so it can access the cards class of the game
    this.clickHelp(pos);
  }

  //is the game over?
  boolean isGameOver() {
    return this.score == 0 || this.remainingFlips == 0;
  }

  //returns time as a string as minutes and seconds
  String timerMin() {
    if (this.timer % 60 == 0) {
      return String.valueOf(this.timer / 60) + ":00";
    }
    if (this.timer % 60 > 9) {
      return String.valueOf(this.timer / 60) + ":" 
          + String.valueOf(this.timer % 60);
    }
    else {
      return 
          String.valueOf(this.timer / 60) + ":0" 
          + String.valueOf(this.timer % 60);
    }
  }

  //ending scene
  public WorldScene lastScene(String message) {
    WorldScene ws = new WorldScene(BOARD_WIDTH, BOARD_HEIGHT);
    ws.placeImageXY(new FromFileImage("src/endingNemo.jpeg"),
        BOARD_WIDTH / 2, BOARD_HEIGHT / 2);
    WorldImage msg = new TextImage(message, 35, Color.white);
    ws.placeImageXY(msg, 900, 200);
    return ws;
  }

  //EFFECT: resets game
  //moves world on key
  public void onKeyEvent(String key) {
    //public void to access card class of game for on key events
    if (key.equals("r")) {
      ArrayList<ArrayList<Card>> newBoard = this.initBoard();
      this.board = newBoard;
      this.score = 26;
      this.remainingFlips = FLIPSLEFT;
      this.timer = 0;
      this.makeScene();
    }
  }

  //handles world on tick
  public void onTick() {
    //public void to access card class of game for on tick
    timer++;
    //    if (this.isGameOver()) {
    if (this.score == 0) {
      this.endOfWorld("you're alive! my dude!");
    }
    else if (this.remainingFlips == 0) {
      this.endOfWorld("you suffer from short term memory loss :(");
    }
    else {
      if (this.cardsFlipped.size() == 2) {
        Card c1 = this.cardsFlipped.get(0);
        Card c2 = this.cardsFlipped.get(1);
        this.ifMatch(c1, c2);
        this.cardsFlipped.remove(1);
        this.cardsFlipped.remove(0);
      }
    }
  }
}

class ExamplesConcentration implements IConstants {

  Card flippedUp1;
  Card flippedUp2;
  Card flippedUp3;
  Card flippedUp4;
  Card c1;
  Card c2;
  Card c3;
  Card c4;
  Card c5;
  Card c6;
  Card c7;
  WorldImage cardSuitc3;
  WorldImage cardRankTopc3;
  WorldImage cardRankBottomc3; 
  WorldImage textSuitc3; 
  WorldImage textRankc3;
  WorldImage cardSuitc6;
  WorldImage cardRankTopc6;
  WorldImage cardRankBottomc6; 
  WorldImage textSuitc6; 
  WorldImage textRankc6;
  WorldImage backOfCard; 
  WorldImage backgroundEndScene;
  ArrayList<Card> miniDeck1;
  ArrayList<Card> miniDeck2;
  ArrayList<Card> miniDeck3;
  ArrayList<Card> miniDeck4;
  ArrayList<Card> miniDeck5;

  ArrayList<ArrayList<Card>> board1;
  ArrayList<ArrayList<Card>> board2;
  ArrayList<ArrayList<Card>> board3;

  Concentration concentration;
  Concentration conc2;
  Concentration conc3;
  Concentration concRand;

  void init() {
    this.flippedUp1 = new Card("Ace", heart, true, false);
    this.flippedUp2 = new Card("5", diamond, true, false);

    this.c1 = new Card("A", heart, true, false);
    this.c2 = new Card("A", diamond, true, false);
    this.c3 = new Card("10", diamond, true, false);
    this.c4 = new Card("10", heart, false, true);
    this.c5 = new Card("Q", spade, true, true);
    this.c6 = new Card("Q", club, true, false);
    this.c7 = new Card("10", club, false, true);


    this.concentration = new Concentration();

    this.textSuitc3 = new TextImage(diamond, 20, Color.red);
    this.textRankc3 = new TextImage("10", 20, Color.red);
    this.cardRankTopc3 = new OverlayOffsetImage(textRankc3, 25, 40, background);
    this.cardSuitc3 = new OverlayOffsetImage(textSuitc3, 5, 0, cardRankTopc3);
    this.cardRankBottomc3 = new OverlayOffsetImage(textRankc3, -20, -40, cardSuitc3);

    this.textSuitc6 = new TextImage(club, 20, Color.black);
    this.textRankc6 = new TextImage("Q", 20, Color.black);
    this.cardRankTopc6 = new OverlayOffsetImage(textRankc6, 25, 40, background);
    this.cardSuitc6 = new OverlayOffsetImage(textSuitc6, 5, 0, cardRankTopc6);
    this.cardRankBottomc6 = new OverlayOffsetImage(textRankc6, -20, -40, cardSuitc6);

    this.backOfCard = new FromFileImage("src/nemoCards.png");
    this.backgroundEndScene = new FromFileImage("src/endingNemo.jpeg");

    this.miniDeck1 = new ArrayList<Card>(Arrays.asList(c1));
    this.miniDeck2 = new ArrayList<Card>(Arrays.asList(c2));
    this.miniDeck3 = new ArrayList<Card>(Arrays.asList(c4));
    this.miniDeck4 = new ArrayList<Card>(Arrays.asList(c7));
    this.miniDeck5 = new ArrayList<Card>(Arrays.asList(c5));


    this.board1 = new ArrayList<ArrayList<Card>>(Arrays.asList(miniDeck1, miniDeck2));
    this.board2 = new ArrayList<ArrayList<Card>>(Arrays.asList(miniDeck3, miniDeck4));
    this.board3 = new ArrayList<ArrayList<Card>>(Arrays.asList(miniDeck3, miniDeck5));

    this.conc2 = new Concentration(board1);
    this.conc3 = new Concentration(board2);
    this.concRand = new Concentration(new Random(5));
  }

  boolean testGame(Tester t) {
    this.init();
    Concentration test = new Concentration();
    test.bigBang(BOARD_WIDTH, BOARD_HEIGHT, 1);
    return true;
  }

  boolean testSameSuit(Tester t) {
    this.init();
    return
        t.checkExpect(this.c1.sameSuit(c2), true)
        && t.checkExpect(this.c2.sameSuit(c3), false)
        && t.checkExpect(this.c5.sameSuit(c6), true)       
        && t.checkExpect(this.c5.sameSuit(c5), false);
  }

  boolean testCardEquals(Tester t) {
    this.init();
    return
        t.checkExpect(this.c1.cardEquals(c2), true)       
        && t.checkExpect(this.c3.cardEquals(c2), false)
        && t.checkExpect(this.c5.cardEquals(c6), true)
        && t.checkExpect(this.c7.cardEquals(c5), false); 
  }

  boolean testChooseColor(Tester t) {
    this.init();
    return
        t.checkExpect(this.c1.chooseColor(), Color.red)
        && t.checkExpect(this.c2.chooseColor(), Color.red)
        && t.checkExpect(this.c5.chooseColor(), Color.black)
        && t.checkExpect(this.c6.chooseColor(), Color.black);
  }

  boolean testDrawCard(Tester t) {
    this.init();
    return 
        t.checkExpect(this.c3.drawCard(), cardRankBottomc3)
        && t.checkExpect(this.c6.drawCard(), cardRankBottomc6)
        && t.checkExpect(this.c4.drawCard(), backOfCard)
        && t.checkExpect(this.c7.drawCard(), backOfCard); 
  }

  boolean testDrawBack(Tester t) {
    this.init();
    return 
        t.checkExpect(this.c3.drawCardBack(), backOfCard)
        && t.checkExpect(this.c6.drawCardBack(), backOfCard); 
  }

  boolean testDrawCardFront(Tester t) {
    this.init();
    return 
        t.checkExpect(this.c3.drawCardFront(), cardRankBottomc3)
        && t.checkExpect(this.c6.drawCardFront(), cardRankBottomc6);
  }

  void testFlipCard(Tester t) {
    //testing flip card in the card class
    this.init();   
    t.checkExpect(c1.side, true);
    t.checkExpect(c2.side, true);
    t.checkExpect(c4.side, false);
    t.checkExpect(c7.side, false);

    c1.flipCard();
    c2.flipCard();
    c4.flipCard();
    c7.flipCard();   
    t.checkExpect(c1.side, false);  
    t.checkExpect(c2.side, false);
    t.checkExpect(c4.side, true);
    t.checkExpect(c7.side, true);

    //testing flip card in the concentration class
    this.init();

    t.checkExpect(board1.get(0).get(0).side, true);
    t.checkExpect(board3.get(1).get(0).side, true);

    concentration.board = board1;
    this.concentration.flipCard(0, 0);
    t.checkExpect(board1.get(0).get(0).side, false);

    concentration.board = board3;
    this.concentration.flipCard(1,  0);
    t.checkExpect(board3.get(1).get(0).side, true);

  }

  void testOutlineGreen(Tester t) {
    this.init(); 
    t.checkExpect(c1.outline, false);
    t.checkExpect(c2.outline, false);
    t.checkExpect(c4.outline, true);
    t.checkExpect(c5.outline, true);

    c1.outlineGreen();
    c2.outlineGreen();
    t.checkExpect(c1.outline, true);
    t.checkExpect(c2.outline, true);
    t.checkExpect(c4.outline, true);
    t.checkExpect(c5.outline, true);
  }

  void testIfMatch(Tester t) {
    this.init();
    t.checkExpect(flippedUp1.side, true);
    t.checkExpect(flippedUp2.side, true);
    t.checkExpect(c1.side, true);
    t.checkExpect(c2.side, true);
    t.checkExpect(c1.outline, false);
    t.checkExpect(c2.outline, false);

    concentration.ifMatch(flippedUp1, flippedUp2);
    concentration.ifMatch(c1, c2);

    t.checkExpect(flippedUp1.side, false);
    t.checkExpect(flippedUp2.side, false);
    t.checkExpect(c1.side, true);
    t.checkExpect(c2.side, true);
    t.checkExpect(c1.outline, true);
    t.checkExpect(c2.outline, true);
  }

  //test for on key
  void testKey(Tester t) {
    init();
    conc2.board = board1;

    t.checkExpect(conc2.score, 20);
    t.checkExpect(conc2.timer, 10);
    t.checkExpect(conc2.board, board1);

    conc2.onKeyEvent("r");
    t.checkExpect(conc2.score, 26);
    t.checkExpect(conc2.timer, 0);
    t.checkExpect(conc2.board, board1);

  }

  void testInitBoard(Tester t) {
    this.init();

    concRand.board = this.board1;
    concRand.initBoard();
    t.checkExpect(this.board1, new ArrayList<ArrayList<Card>>(Arrays.asList(miniDeck1, miniDeck2)));
  }

  void testMakeDeck(Tester t) {
    this.init();

    t.checkExpect(this.concentration.board.size(), 52);
    this.concentration.board = new ArrayList<Card>();
    t.checkExpect(this.concentration.board, board);
    this.concentration.makeDeck();
    t.checkExpect(this.concentration.board.size(), 52);

    ArrayList<Card> firstRow = new ArrayList<Card>(Arrays.asList(
        this.concentration.board.get(0).get(0), this.concentration.board.get(1).get(0), 
        this.concentration.board.get(2).get(0), this.concentration.board.get(3).get(0), 
        this.concentration.board.get(4).get(0), this.concentration.board.get(5).get(0), 
        this.concentration.board.get(6).get(0), this.concentration.board.get(7).get(0), 
        this.concentration.board.get(8).get(0), this.concentration.board.get(9).get(0), 
        this.concentration.board.get(10).get(0), this.concentration.board.get(11).get(0), 
        this.concentration.board.get(12).get(0))); 

    ArrayList<Card> secondRow = new ArrayList<Card>(Arrays.asList(
        this.concentration.board.get(13).get(0), this.concentration.board.get(14).get(0), 
        this.concentration.board.get(15).get(0), this.concentration.board.get(16).get(0), 
        this.concentration.board.get(17).get(0), this.concentration.board.get(18).get(0), 
        this.concentration.board.get(19).get(0), this.concentration.board.get(20).get(0), 
        this.concentration.board.get(21).get(0), this.concentration.board.get(22).get(0), 
        this.concentration.board.get(23).get(0), this.concentration.board.get(24).get(0), 
        this.concentration.board.get(25).get(0))); 

    ArrayList<Card> thirdRow = new ArrayList<Card>(Arrays.asList(
        this.concentration.board.get(26).get(0), this.concentration.board.get(27).get(0), 
        this.concentration.board.get(28).get(0), this.concentration.board.get(29).get(0), 
        this.concentration.board.get(30).get(0), this.concentration.board.get(31).get(0), 
        this.concentration.board.get(32).get(0), this.concentration.board.get(33).get(0), 
        this.concentration.board.get(34).get(0), this.concentration.board.get(35).get(0), 
        this.concentration.board.get(36).get(0), this.concentration.board.get(37).get(0), 
        this.concentration.board.get(38).get(0))); 

    ArrayList<Card> fourthRow = new ArrayList<Card>(Arrays.asList(
        this.concentration.board.get(39).get(0), this.concentration.board.get(40).get(0), 
        this.concentration.board.get(41).get(0), this.concentration.board.get(42).get(0), 
        this.concentration.board.get(43).get(0), this.concentration.board.get(44).get(0), 
        this.concentration.board.get(45).get(0), this.concentration.board.get(46).get(0), 
        this.concentration.board.get(47).get(0), this.concentration.board.get(48).get(0), 
        this.concentration.board.get(49).get(0), this.concentration.board.get(50).get(0), 
        this.concentration.board.get(51).get(0))); 
  }

  boolean testmakeScene(Tester t) {
    this.init();

    int y = WHITESPACE + CARD_HEIGHT / 2;
    WorldScene board = new WorldScene(BOARD_WIDTH, BOARD_HEIGHT);
    board.placeImageXY(new FromFileImage("src/oceanFloor.jpeg"), BOARD_WIDTH / 2, BOARD_HEIGHT / 2);
    for (int i = 0; i < 4; i++) {
      int x = WHITESPACE + CARD_WIDTH / 2;
      for (int j = 0; j < 13; j++) {
        board.placeImageXY(concentration.board.get(i).get(j).drawCard(), x, y);
        x += WHITESPACE + CARD_WIDTH;
      }
      y += WHITESPACE + CARD_HEIGHT;
    }
    board.placeImageXY(new RectangleImage(2200, 100, OutlineMode.SOLID, Color.gray), 500, 680);
    board.placeImageXY(new TextImage("Score: " + concentration.score, 30, Color.black), 100, 680);
    board.placeImageXY(new TextImage("Flips Remaining: " + concentration.remainingFlips, 
        30, Color.black), 650, 680);
    board.placeImageXY(new TextImage("Time: " + concentration.timerMin() + " seconds", 
        30, Color.black), 1150, 680);


    return t.checkExpect(concentration.makeScene(), board);
  }

  //click help, on mouse clicked

  void testTimerMin(Tester t) {

    this.init();
    conc2.timer = 119;
    t.checkExpect(conc2.timerMin(), "1:59");

    this.init();
    conc2.timer = 120;
    t.checkExpect(conc2.timerMin(), "2:00");

    this.init();
    conc2.timer = 135;
    t.checkExpect(conc2.timerMin(), "2:15");

    this.init();
    conc2.timer = 69;
    t.checkExpect(conc2.timerMin(), "1:09");

    this.init();
    conc2.timer = 68;
    t.checkExpect(conc2.timerMin(), "1:08");

  }

  void testOnTick(Tester t) {
    this.init();

    concentration.score = 0;
    concentration.onTick();

    t.checkExpect(concentration.onTick(), concentration.lastScene("you're alive! my dude!"));

    concentration.score = 2;
    concentration.remainingFlips = 0;
    t.checkExpect(concentration.onTick(), concentration.lastScene("you suffer from short term "
        + "memory loss :("));

    concentration.cardsFlipped = new ArrayList<Card>(Arrays.asList(c1, c2));
    t.checkExpect(concentration.cardsFlipped.size(), 2);
    t.checkExpect(concentration.cardsFlipped.get(0).outline, false);

    concentration.onTick();
    t.checkExpect(concentration.cardsFlipped.size(), 0);
    t.checkExpect(concentration.cardsFlipped.get(0).outline, true);

  }

  void testLastScene(Tester t) {
    this.init();

    WorldScene ws = new WorldScene(BOARD_WIDTH, BOARD_HEIGHT);
    ws.placeImageXY(backgroundEndScene, BOARD_WIDTH / 2, BOARD_HEIGHT / 2);
    ws.placeImageXY(new TextImage("just keep swimming", 35, Color.white), 900, 200);

    t.checkExpect(this.concentration.lastScene("just keep swimming"), ws);

    WorldScene ws2 = new WorldScene(BOARD_WIDTH, BOARD_HEIGHT);
    ws2.placeImageXY(backgroundEndScene, BOARD_WIDTH / 2, BOARD_HEIGHT / 2);
    ws2.placeImageXY(new TextImage("you swam!", 35, Color.white), 900, 200);

    t.checkExpect(this.conc2.lastScene("you swam!"), ws2);
  }
}


