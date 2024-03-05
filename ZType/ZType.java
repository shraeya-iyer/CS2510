import tester.Tester;

import java.awt.Color;
import javalib.worldimages.*;

import java.util.Random;
import javalib.funworld.*;

//represents a list of words
interface ILoWord {

  //removes the first letter of an active word if it matches the given string
  ILoWord checkAndReduce(String str); 

  //adds the given word to the end of the list
  ILoWord addToEnd(IWord word);

  //filters out all words that have an empty string from the list
  ILoWord filterOutEmpties();

  //draws the words in the ILoWord onto the WorldScene
  WorldScene draw(WorldScene ws);

  //checks if any word in the list is at the bottom of screen
  boolean areWordsAtBottom();

  //moves words down on the screen at an increment of 10 pixels per tick
  ILoWord wordsDown();

  //determines if any of the words in the list are active
  boolean wordsActive();

  //activates 
  ILoWord activateWord(String str);

}

//represents an empty list of words
class MtLoWord implements ILoWord {

  //removes the first letter of an active word if it matches the given string
  public ILoWord checkAndReduce(String str) {
    return this;
  }

  //adds the given word to the end of the list
  /* fields of word: none
   * 
   * methods of word:
   * word.comesBefore(IWord word) -- boolean
   * word.comesBeforeHelper(String acc) -- boolean
   * word.removeFirstLetter(String str) -- IWord
   * word.equalsEmptyString() -- boolean
   * word.drawWord(WorldScene ws) -- WorldScene
   * 
   * methods for fields of word: none
   */
  public ILoWord addToEnd(IWord word) {
    return new ConsLoWord(word, new MtLoWord());
  }

  //filters out all words that have an empty string from the list
  /* fields of word: none
   * 
   * methods of word:
   * wordList.sort() -- ILoWord
   * wordList.insert(IWord w) -- ILoWord
   * wordList.isSorted() -- boolean
   * wordList.isSortedHelper(IWord word) -- boolean
   * wordList.interLeave(ILoWord wordList) -- merge
   * wordList.merge(ILoWord wordList) -- ILoWord
   * wordList.mergeHelper(IWord word) -- boolean
   * wordList.checkAndReduce(String str) -- ILoWord
   * wordList.addToEnd(IWord word) -- ILoWord
   * wordList.filterOutEmpties() -- ILoWord
   * wordList.draw(WorldScene ws) -- WorldScene
   * 
   * methods for fields of word: none
   */
  public ILoWord filterOutEmpties() {
    return this;
  }

  //returns an empty WorldScene 
  public WorldScene draw(WorldScene ws) {
    return ws;
  }

  //checks if any word in the list is at the bottom of screen
  public boolean areWordsAtBottom() {
    return false;
  }

  //shifts all words down by 10 pixels
  public ILoWord wordsDown() {
    return this;
  }

  //makes words into active words
  public boolean wordsActive() {
    return false;
  }

  //activates a word beginning with the given string
  public ILoWord activateWord(String str) {
    return this;
  }


  /*
   * TEMPLATE:
   * --------
   * Fields: n/a
   * 
   * Methods:
   * this.sort                       -- ILoWorld
   * this.insert(IWord word)         -- ILoWorld
   * this.isSorted()                 -- boolean
   * this.interleave(ILoWOrd word)   -- ILoWord
   * this.merge(ILoWord wordList)    -- ILoWord
   * this.mergeHelper(IWord word)    -- boolean
   * this.checkAndReduce(String str) -- ILoWord
   * this.addToEnd(IWord word)       -- ILoWord
   * this.filterOutEmpties()         -- ILoWord
   * this.draw(WorldScene ws)        -- WorldScene
   * 
   * Methods for Fields: n/a
   */

}


class ConsLoWord implements ILoWord {
  IWord first;
  ILoWord rest;

  ConsLoWord(IWord first, ILoWord rest) {
    this.first = first;
    this.rest = rest;
  }

  /*
   * TEMPLATE:
   * --------
   * Fields: 
   * ... this.first ...  -- IWord
   * ... this.rest ...   -- ILoWord
   * 
   * Methods:
   * this.sort                       -- ILoWorld
   * this.insert(IWord word)         -- ILoWorld
   * this.isSorted()                 -- boolean
   * this.interleave(ILoWOrd word)   -- ILoWord
   * this.merge(ILoWord wordList)    -- ILoWord
   * this.mergeHelper(IWord word)    -- boolean
   * this.checkAndReduce(String str) -- ILoWord
   * this.addToEnd(IWord word)       -- ILoWord
   * this.filterOutEmpties()         -- ILoWord
   * this.draw(WorldScene ws)        -- WorldScene
   * 
   * Methods for Fields: 
   * this.first.comesBefore(ILoWord wordList) -- IWord
   * this.first.helperhelperCompareTo(String Acc) -- IWord
   * this.first.removeFirstLetter(String str) -- IWord
   * this.first.equalsEmptyString() -- boolean
   * this.first.drawWord(WorldScene ws) -- 
   * this.rest.sort() -- ILoWord
   * this.rest.insert() -- ILoWord
   * this.rest.interleave(ILoWord word) -- ILoWord
   * this.rest.isSorted() -- boolean
   * this.rest.isSortedHelper(IWord word) -- boolean
   * this.rest.merge(IWord word) -- boolean
   * this.rest.mergeHelper(IWord word) -- boolean
   * this.rest.checkAndReduce(String str) -- ILoWord
   * this.rest.addToEnd(IWord word) -- ILoWord
   * this.rest.filterOutEmpties() -- ILoWord
   * this.rest.draw(WorldScene ws) -- WorldScene
   */

  //removes the first letter of an active word if it matches the given string
  public ILoWord checkAndReduce(String str) {
    if (this.first.wordActive()) {
      return new ConsLoWord(this.first.removeFirstLetter(str), this.rest);
    }
    if (this.rest.wordsActive()) {
      return new ConsLoWord(this.first, this.rest.checkAndReduce(str));
    }
    else {
      return this.activateWord(str);
    }
  }

  //adds the given word to the end of the list
  /* fields of word: none
   * 
   * methods of word:
   * word.comesBefore(IWord word) -- boolean
   * word.comesBeforeHelper(String acc) -- boolean
   * word.removeFirstLetter(String str) -- IWord
   * word.equalsEmptyString() -- boolean
   * word.drawWord(WorldScene ws) -- WorldScene
   * 
   * methods for fields of word: none
   */
  public ILoWord addToEnd(IWord word) {
    return new ConsLoWord(this.first, this.rest.addToEnd(word));
  } 

  //filters out all words that have an empty string from the list
  public ILoWord filterOutEmpties() {
    if (this.first.equalsEmptyString()) {
      return this.rest.filterOutEmpties();
    }
    else {
      return new ConsLoWord(this.first, this.rest.filterOutEmpties());
    }
  }

  //draws the words in the ILoWord onto the WorldScene
  public WorldScene draw(WorldScene ws) {
    return this.rest.draw(this.first.drawWord(ws));
  }

  //checks if the word is at the bottom of the screen
  public boolean areWordsAtBottom() {
    return this.first.isWordAtBottom() || this.rest.areWordsAtBottom();
  }

  //moves words down by 10 pixels
  public ILoWord wordsDown() {
    return new ConsLoWord(this.first.wordDown(), this.rest.wordsDown());
  }

  //checks if any words in the list are active
  public boolean wordsActive() {
    return this.first.wordActive() || this.rest.wordsActive();
  }

  //activates a word beginning with the given string
  public ILoWord activateWord(String str) {
    if (this.first.firstLetterEqual(str)) {
      return new ConsLoWord(this.first.removeFirstLetter(str), this.rest);
    }
    else {
      return new ConsLoWord(this.first, this.rest.activateWord(str));
    }
  }
}


//represents a word in the ZType game
interface IWord {

  //whether the given word comes before this word
  boolean comesBefore(IWord word);

  //checks if the first letter is equal to the given string
  boolean firstLetterEqual(String str);

  //checks if the word is active
  boolean wordActive();

  //moves a word down by 10 pixels
  IWord wordDown();

  //determines whether or not the given word's y coordinate is at 
  //bottom of the screen
  boolean isWordAtBottom();

  //keeps track of the string 
  boolean comesBeforeHelper(String acc);

  //removes the first letter of an IWord
  IWord removeFirstLetter(String str);

  //checks if the string is an empty string
  boolean equalsEmptyString();

  //places individual words on WorldScene individually
  WorldScene drawWord(WorldScene ws);

}

abstract class AWord implements IWord {
  String word;
  int x;
  int y;

  AWord(String word, int x, int y) {
    this.word = word;
    this.x = x;
    this.y = y;
  }

  //check if a word contains an empty string
  public boolean equalsEmptyString() {
    return this.word.equals("");
  }


  //whether the given word comes before this word
  /* fields of word: none
   * 
   * methods of word:
   * word.comesBefore(IWord word) -- boolean
   * word.comesBeforeHelper(String acc) -- boolean
   * word.removeFirstLetter(String str) -- IWord
   * word.equalsEmptyString() -- boolean
   * word.drawWord(WorldScene ws) -- WorldScene
   * 
   * methods for fields of word: none
   */
  public boolean comesBefore(IWord word) {
    return word.comesBeforeHelper(this.word);
  }

  //keeps track of string word
  public boolean comesBeforeHelper(String str) {
    return (this.word.toLowerCase().compareTo(str.toLowerCase())) >= 0;
    //this.word comes after given word 
  }

  //places individual words on WorldScene individually
  abstract public WorldScene drawWord(WorldScene ws);

  //checks if the word is at the bottom of the screen
  public boolean isWordAtBottom() {
    return this.y >= 400;
  }

  //moves word down by ten pixels
  abstract public IWord wordDown();

  //checks if a word is active
  abstract public boolean wordActive();

  //checks if the first letter is equal to the given string
  public boolean firstLetterEqual(String str) {
    return this.word.substring(0,1).equals(str);
  }

  //removes the first letter of an IWord
  public IWord removeFirstLetter(String str) {
    if (this.word.substring(0,1).equals(str)) {
      return new ActiveWord(this.word.substring(1), this.x, this.y);
    }

    else {
      return this;
    }
  }
}


//represents an active word in the ZType game
class ActiveWord extends AWord {
  ActiveWord(String word, int x, int y) {
    super(word, x, y);
  }

  /*
   * TEMPLATE:
   * --------
   * Fields: 
   * ...this.word ... -- String
   * ... this.x ... -- int
   * ... this.y ... -- int
   * 
   * Methods:
   * this.comesBefore(ILoWord wordList) -- IWord
   * this.helperhelperCompareTo(String Acc) -- IWord
   * this.removeFirstLetter(String str)     -- IWord
   * this.equalsEmptyString()               -- boolean
   * this.drawWord(WorldScene ws)           -- WorldScene
   * 
   * Methods for Fields: n/a
   */

  //places individual words on WorldScene individually
  public WorldScene drawWord(WorldScene ws) {
    return ws.placeImageXY(new TextImage(this.word, 20, Color.pink), this.x, this.y);
  } 

  //moves word down by ten pixels
  public IWord wordDown() {
    return new ActiveWord(this.word, this.x, this.y + 10); 
  }

  //checks if a word is active
  public boolean wordActive() { 
    return true;
  }
}


//represents an inactive word in the ZType game
class InactiveWord extends AWord {
  InactiveWord(String word, int x, int y) {
    super(word, x, y);
  }

  /*
   * TEMPLATE:
   * --------
   * Fields: 
   * ...this.word ... -- String
   * ... this.x ... -- int
   * ... this.y ... -- int
   * 
   * Methods:
   * this.comesBefore(ILoWord wordList) -- IWord
   * this.comesBeforeHelper(String Acc) -- IWord
   * this.removeFirstLetter(String str)     -- IWord
   * this.equalsEmptyString()               -- boolean
   * this.drawWord(WorldScene ws)           -- WorldScene
   * 
   * Methods for Fields: n/a
   */

  //places individual words on WorldScene individually
  public WorldScene drawWord(WorldScene ws) {
    return ws.placeImageXY(new TextImage(this.word, 20, Color.green), this.x, this.y);
  }

  //moves word down by ten pixels
  public IWord wordDown() {
    return new InactiveWord(this.word, this.x, this.y + 10);
  }

  //checks if a word is active
  public boolean wordActive() {
    return false;
  }
}


//represents constants needed in ZType
interface IZtypeConstants {
  int width = 600;
  int height = 400;
  int dropRate = 2;
  String letters = "abcdefghijklmnopqrstuvqwxyz";
}

//represents a world program for a ZTypeWorld
class ZTypeWorld extends World implements IZtypeConstants {
  ILoWord wordList;
  int counter; //tick rate
  Random r;

  ZTypeWorld(ILoWord wordList, Random r) {
    this.wordList = wordList;
    this.counter = 0;
    this.r = r;
  }

  ZTypeWorld(ILoWord wordList, int counter, Random r) {
    this.wordList = wordList;
    this.counter = counter;
    this.r = r;
  }

  ZTypeWorld(ILoWord wordList) {
    this.wordList = wordList;
    this.counter = 0;
    this.r = new Random();
  }

  //renders the state of this ZTypeWorld
  @Override
  public WorldScene makeScene() { // does my make scene need to return a world
    WorldScene ws = new WorldScene(width, height);
    return this.wordList.draw(ws);
  }

  //handles key events for this ZTypeWorld
  @Override
  public ZTypeWorld onKeyEvent(String key) {
    return new ZTypeWorld(this.wordList.filterOutEmpties().checkAndReduce(key)); 
  } 

  //returns the ending scene that plays when you lose the game
  public WorldScene lastScene(String str) {
    WorldScene ws = new WorldScene(width, height);
    return ws.placeImageXY(new TextImage(str, 55, Color.red), 300, 200);
  }

  //returns a ZTypeWorld or endOfWorld depending on what is happening in the game
  @Override
  public World onTick() {
    if (this.counter == dropRate) {
      return new ZTypeWorld(this.wordList.addToEnd(randWord()).wordsDown(), 
          this.counter + 1, this.r);
    }
    if (this.wordList.areWordsAtBottom()) {
      return this.endOfWorld("GAME OVER");
    }
    else {
      return new ZTypeWorld(this.wordList.wordsDown(), this.counter + 1, this.r);
    }
  }

  //produces a random six letter word
  public IWord randWord() { 
    int letterone = r.nextInt(26);
    int lettertwo = r.nextInt(26);
    int letterthree = r.nextInt(26);
    int letterfour = r.nextInt(26);
    int letterfive = r.nextInt(26);
    int lettersix = r.nextInt(26);

    return new InactiveWord(
        letters.substring(letterone, letterone + 1) +
        letters.substring(lettertwo, lettertwo + 1) +
        letters.substring(letterthree, letterthree + 1) +
        letters.substring(letterfour, letterfour + 1) +
        letters.substring(letterfive, letterfive + 1) +
        letters.substring(lettersix, lettersix + 1), 
        r.nextInt(300) + 50, 2);
  }
}

