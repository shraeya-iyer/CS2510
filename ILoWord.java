import tester.Tester;

import java.awt.Color;
import javalib.funworld.WorldScene;
import javalib.worldimages.*;

//represents a list of words
interface ILoWord {

  //sorts all words in a list into alphabetical order in lower case
  ILoWord sort(); 

  //inserts a string into a sorted list, outputs sorted list
  ILoWord insert(IWord w); 

  //returns true if a list is sorted in alphabetical order
  boolean isSorted(); 

  //keeps track of the word in the list
  boolean isSortedHelper(IWord word);

  //combines two lists with their contents placed in alternating slots
  ILoWord interleave(ILoWord wordList); 

  //merges two sorted lists into one sorted list
  ILoWord merge(ILoWord wordList); 

  //an accumulator that keeps track of a word in a non empty list
  boolean mergeHelper(IWord word);

  //removes the first letter of an active word if it matches the given string
  ILoWord checkAndReduce(String str); 

  //adds the given word to the end of the list
  ILoWord addToEnd(IWord word);

  //filters out all words that have an empty string from the list
  ILoWord filterOutEmpties();

  //draws the words in the ILoWord onto the WorldScene
  WorldScene draw(WorldScene ws);

}





//represents an empty list of words
class MtLoWord implements ILoWord {

  //sorts all words in a list into alphabetical order in lower case
  public ILoWord sort() {
    return this;
  }

  //inserts a string into an empty list
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
  public ILoWord insert(IWord word) {
    return new ConsLoWord(word, this);
  }

  //returns true if a list is sorted in alphabetical order
  public boolean isSorted() {
    return true;
  }

  //keeps track of the word in the list
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
  public boolean isSortedHelper(IWord word) {
    return true;
  }

  //combines two lists with their contents placed in alternating slots
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
  public ILoWord interleave(ILoWord wordList) {
    return wordList;
  }

  //merges two sorted lists into one sorted list
  public ILoWord merge(ILoWord wordList) {
    return wordList;
  }

  //an accumulator that keeps track of a word in a non empty list
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
  public boolean mergeHelper(IWord word) {
    return false;
  }

  //removes the first letter of an active word if it matches the given string
  public ILoWord checkAndReduce(String str) {
    return new MtLoWord();
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



  //sorts all words in a list into alphabetical order in lower case
  public ILoWord sort() {
    return this.rest.sort().insert(this.first);
  }

  //inserts a string into a sorted list, outputs sorted list
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
  public ILoWord insert(IWord w) {
    if (this.first.comesBefore(w)) {
      return new ConsLoWord(this.first, this.rest.insert(w));
    }
    else {
      return new ConsLoWord(w, this);
    }
  }

  //returns true if a list is sorted in alphabetical order (REDO)
  public boolean isSorted() {
    return this.rest.isSortedHelper(this.first) && this.rest.isSorted();
  }

  //compares the word against each element of ILoWord
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
  public boolean isSortedHelper(IWord word) {
    return 
        word.comesBefore(this.first) && this.rest.isSortedHelper(this.first); 
  }

  //combines two lists with their contents placed in alternating slots
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
  public ILoWord interleave(ILoWord wordList) {
    return new ConsLoWord(this.first, 
        wordList.interleave(this.rest)) ;
  }

  //merges two sorted lists into one sorted list
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
  public ILoWord merge(ILoWord wordList) {
    if (wordList.mergeHelper(this.first)) {
      //true: first of wordList comes after this.first
      return wordList.merge(this);
    }
    else {
      return new ConsLoWord(this.first, wordList.merge(this.rest)); 
    }
  }

  //compares two words from different lists alphabetically
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
  public boolean mergeHelper(IWord word) {
    return this.first.comesBefore(word);
  }

  //removes the first letter of an active word if it matches the given string
  public ILoWord checkAndReduce(String str) {
    return new ConsLoWord(this.first.removeFirstLetter(str), 
        this.rest.checkAndReduce(str));
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

}



//represents a word in the ZType game
interface IWord {

  //whether the given word comes before this word
  boolean comesBefore(IWord word);

  //keeps track of the string 
  boolean comesBeforeHelper(String acc);

  //removes the first letter of an ActiveWord
  IWord removeFirstLetter(String str);

  ////keeps track of the string 
  boolean equalsEmptyString();

  //places individual words on WorldScene individually
  WorldScene drawWord(WorldScene ws);

}



//represents an active word in the ZType game
class ActiveWord implements IWord {
  String word;
  int x;
  int y;

  ActiveWord(String word, int x, int y) {
    this.word = word;
    this.x = x;
    this.y = y;
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

  //removes the first letter of an ActiveWord
  public IWord removeFirstLetter(String str) {
    if (this.word.toLowerCase().substring(0,1).equals(str)) {
      return new ActiveWord(this.word.substring(1), this.x, this.y);
    }
    
    else {
      return this;
    }
  }

  //check if a word contains an empty string
  public boolean equalsEmptyString() {
    return this.word.equals("");
  }

  //places individual words on WorldScene individually
  public WorldScene drawWord(WorldScene ws) {
    return new WorldScene(ws.width, ws.height)
        .placeImageXY(new TextImage(this.word, Color.pink), this.x, this.y);
  } 
}



//represents an inactive word in the ZType game
class InactiveWord implements IWord {
  String word;
  int x;
  int y;


  InactiveWord(String word, int x, int y) {
    this.word = word;
    this.x = x;
    this.y = y;
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
  public boolean comesBefore (IWord word) {
    return word.comesBeforeHelper(this.word);
    //word.word comes after this.word 
  }

  //keeps track of the string
  public boolean comesBeforeHelper(String str) {
    return (this.word.toLowerCase().compareTo(str.toLowerCase())) >= 0;
    //this.word comes after given word 
  }

  //returns the InActiveWord since removeFirstLetter should 
  //only work on ActiveWords
  public IWord removeFirstLetter(String str) {
    return this;
  }

  //check if a word contains an empty string
  public boolean equalsEmptyString() {
    return this.word.equals("");
  }

  //places individual words on WorldScene individually
  public WorldScene drawWord(WorldScene ws) {
    return new WorldScene(ws.width, ws.height)
        .placeImageXY(new TextImage(this.word, Color.green), this.x, this.y);
  }
}

//all examples and tests for ILoWord
class ExamplesWordLists {

  //examples of an ActiveWord
  IWord apple = new ActiveWord("Apple", 4, 6);
  IWord pple = new ActiveWord("pple", 4, 6);
  IWord arnold = new ActiveWord("arnold", 19, 28);
  IWord rnold = new ActiveWord("rnold", 19, 28);
  IWord baseball = new ActiveWord("baseball", 10, 15);
  IWord cat = new ActiveWord("Cat", 7, 15);
  IWord kong = new ActiveWord("Kong", 8, 12);
  IWord pulled = new ActiveWord("pulled", 9, 11);
  IWord quack = new ActiveWord("Quack", 17, 19);

  //examples of an InactiveWord
  IWord dog = new InactiveWord("dog", 30, 10);
  IWord jorts = new InactiveWord("Jorts", 23, 27);
  IWord lemur = new InactiveWord("Lemur", 14, 26);
  IWord alexa = new InactiveWord("Alexa", 15, 25);
  IWord store = new InactiveWord("store", 2, 9);
  IWord wombat = new InactiveWord("Wombat", 1, 29);
  IWord zebra = new InactiveWord("zebra", 5, 5);
  IWord emptyword = new InactiveWord("", 2, 7);

  //examples of an empty list
  ILoWord mt = new MtLoWord();

  //examples of a ConsLoWord
  ILoWord list1A = new ConsLoWord(this.apple, this.mt);
  ILoWord list1D = new ConsLoWord(this.jorts, this.mt);
  ILoWord list2A = new ConsLoWord(this.wombat, this.list1A);
  ILoWord list3A = new ConsLoWord(this.dog, this.list2A);
  ILoWord list4A = new ConsLoWord(this.zebra, this.list3A);
  ILoWord list5A = new ConsLoWord(this.lemur, this.list4A);
  ILoWord list6A = new ConsLoWord(this.baseball, this.list5A);
  ILoWord list7A = new ConsLoWord(this.emptyword, this.list6A);
  ILoWord list8A = new ConsLoWord(this.pulled, this.list7A);
  ILoWord list9A = new ConsLoWord(this.quack, this.list8A);
  ILoWord list10A = new ConsLoWord(this.store, this.list9A);
  ILoWord list11A = new ConsLoWord(this.cat, this.list10A);
  ILoWord list12A = new ConsLoWord(this.kong, this.list11A);
  ILoWord list13A = new ConsLoWord(this.apple,
      new ConsLoWord(this.pulled,
          new ConsLoWord(this.quack, this.mt)));

  ILoWord list19A = new ConsLoWord(this.dog,
      new ConsLoWord(this.lemur, this.mt));

  ILoWord list20A = new ConsLoWord(this.apple,
      new ConsLoWord(this.jorts, this.mt));

  ILoWord list21A = new ConsLoWord(this.kong, this.mt);

  ILoWord list1920merge = new ConsLoWord(this.apple,
      new ConsLoWord(this.dog,
          new ConsLoWord(this.jorts,
              new ConsLoWord(this.lemur, this.mt))));

  ILoWord list2021merge = new ConsLoWord(this.apple,
      new ConsLoWord(this.jorts,
          new ConsLoWord(this.kong, this.mt)));

  ILoWord list14A = new ConsLoWord(this.wombat,
      new ConsLoWord(this.lemur,
          new ConsLoWord(this.dog, this.mt)));

  ILoWord list15A = new ConsLoWord(this.wombat,
      new ConsLoWord(this.lemur,
          new ConsLoWord(this.dog, 
              new ConsLoWord(this.kong, this.mt))));

  ILoWord list16A = new ConsLoWord(this.apple,
      new ConsLoWord(this.lemur,
          new ConsLoWord(this.apple,
              new ConsLoWord(this.jorts,
                  new ConsLoWord(this.alexa, this.mt)))));

  ILoWord list16C = new ConsLoWord(this.alexa,
      new ConsLoWord(this.apple,
          new ConsLoWord(this.apple,
              new ConsLoWord(this.jorts,
                  new ConsLoWord(this.lemur, this.mt)))));

  ILoWord list17A = new ConsLoWord(this.store, 
      new ConsLoWord(this.emptyword, 
          new ConsLoWord(this.baseball, 
              new ConsLoWord(this.arnold, 
                  new ConsLoWord(this.cat, this.mt)))));

  ILoWord list18A = new ConsLoWord(this.store, 
      new ConsLoWord(this.emptyword, 
          new ConsLoWord(this.baseball, 
              new ConsLoWord(this.arnold, 
                  new ConsLoWord(this.emptyword, this.mt)))));

  //examples of sorted ConsLoWords
  ILoWord list1B = new ConsLoWord(this.apple, this.mt);

  //
  ILoWord list1C = new ConsLoWord(this.apple, 
      new ConsLoWord(this.wombat, this.mt));

  //
  ILoWord list3B = new ConsLoWord(this.apple, 
      new ConsLoWord(this.dog, 
          new ConsLoWord(this.wombat, this.mt)));
  //
  ILoWord list4B = new ConsLoWord(this.apple, 
      new ConsLoWord(this.dog, 
          new ConsLoWord(this.wombat, 
              new ConsLoWord(this.zebra, this.mt))));
  //
  ILoWord list4Binsert = new ConsLoWord(this.apple, 
      new ConsLoWord(this.dog, 
          new ConsLoWord(this.store, 
              new ConsLoWord(this.wombat, 
                  new ConsLoWord(this.zebra, this.mt)))));  
  //
  ILoWord list7B = new ConsLoWord(this.apple,
      new ConsLoWord(this.baseball, 
          new ConsLoWord(this.dog,
              new ConsLoWord(this.lemur,
                  new ConsLoWord(this.wombat,
                      new ConsLoWord(this.zebra,
                          new ConsLoWord(this.emptyword, this.mt)))))));
  //
  ILoWord list7Binsert = new ConsLoWord(this.apple,
      new ConsLoWord(this.baseball, 
          new ConsLoWord(this.cat,
              new ConsLoWord(this.dog,
                  new ConsLoWord(this.lemur,
                      new ConsLoWord(this.wombat,
                          new ConsLoWord(this.zebra,
                              new ConsLoWord(this.emptyword, this.mt))))))));
  //
  ILoWord list1314interleave =
      new ConsLoWord(this.apple, 
          new ConsLoWord(this.wombat,
              new ConsLoWord(this.pulled,
                  new ConsLoWord(this.lemur,
                      new ConsLoWord(this.quack,
                          new ConsLoWord(this.dog, this.mt))))));
  //
  ILoWord list1315interleave = 
      new ConsLoWord(this.apple, 
          new ConsLoWord(this.wombat,
              new ConsLoWord(this.pulled,
                  new ConsLoWord(this.lemur,
                      new ConsLoWord(this.quack,
                          new ConsLoWord(this.dog, 
                              new ConsLoWord(this.kong, this.mt)))))));
  //
  ILoWord list16B = new ConsLoWord(this.pple,
      new ConsLoWord(this.lemur,
          new ConsLoWord(this.rnold,
              new ConsLoWord(this.jorts,
                  new ConsLoWord(this.alexa, this.mt)))));

  //
  ILoWord list17B = new ConsLoWord(this.store, 
      new ConsLoWord(this.baseball, 
          new ConsLoWord(this.arnold, 
              new ConsLoWord(this.cat, this.mt))));
  //
  ILoWord list18B = new ConsLoWord(this.store,  
      new ConsLoWord(this.baseball, 
          new ConsLoWord(this.arnold, this.mt)));

  //TESTS

  //to test sort
  boolean testSort(Tester t) {
    return t.checkExpect(this.list3A.sort(), list3B)
        && t.checkExpect(this.mt.sort(), this.mt)
        && t.checkExpect(this.list16A.sort(), this.list16C);
  } 

  //to test insert
  boolean testInsert(Tester t) {
    return t.checkExpect(this.list4B.insert(this.store), this.list4Binsert)
        && t.checkExpect(this.mt.insert(this.apple), this.list1A)
        && t.checkExpect(this.list7B.insert(this.cat), this.list7Binsert);
  }

  //to test interleave: 
  boolean testInterLeave(Tester t) {
    return t.checkExpect(this.list13A.interleave(list14A), this.list1314interleave)
        && t.checkExpect(this.list13A.interleave(list15A), this.list1315interleave)
        && t.checkExpect(this.list13A.interleave(this.mt), this.list13A)
        && t.checkExpect(this.mt.interleave(this.mt), this.mt);
  }

  //to test isSorted
  boolean testisSorted(Tester t) {
    return t.checkExpect(this.mt.isSorted(), true)
        && t.checkExpect(this.list3B.isSorted(), true) 
        && t.checkExpect(this.list3A.isSorted(), false); 
  }

  //to test mergeHelper
  boolean testMergeHelper(Tester t) {
    return t.checkExpect(this.list1A.mergeHelper(this.cat), true)
        && t.checkExpect(this.list1D.mergeHelper(this.baseball), false);
  }

  //to test merge
  boolean testMerge(Tester t) {
    return t.checkExpect(this.list19A.merge(list20A), this.list1920merge)
        && t.checkExpect(this.mt.merge(list1A), this.list1A)
        && t.checkExpect(this.list20A.merge(list21A), this.list2021merge);
  }

  //to test checkAndReduce
  boolean testCheckAndReduce(Tester t) {
    return t.checkExpect(this.list16A.checkAndReduce("a"), this.list16B)
        && t.checkExpect(this.list15A.checkAndReduce("a"), this.list15A)
        && t.checkExpect(this.mt.checkAndReduce("a"), this.mt);
  }     

  //to removeFirstLetter
  boolean testRemoveFirstLetter(Tester t) {
    return t.checkExpect(this.apple.removeFirstLetter("a"), this.pple)
        && t.checkExpect(this.arnold.removeFirstLetter("a"), this.rnold)
        && t.checkExpect(this.alexa.removeFirstLetter("a"), this.alexa)
        && t.checkExpect(this.emptyword.removeFirstLetter("a"), this.emptyword);
  } 

  //to test andToEnd
  boolean testAddToEnd(Tester t) {
    return t.checkExpect(this.list1A.addToEnd(this.wombat), this.list1C)
        && t.checkExpect(this.mt.addToEnd(this.apple), this.list1A);
  }

  //to test filterOutEmpties
  boolean testFilterOutEmpties(Tester t) {
    return t.checkExpect(this.list16A.filterOutEmpties(), this.list16A)
        && t.checkExpect(this.list17A.filterOutEmpties(), this.list17B) //
        && t.checkExpect(this.list18A.filterOutEmpties(), this.list18B)
        && t.checkExpect(this.mt.filterOutEmpties(), this.mt);
  }


  //to test comesBefore
  boolean testComesBefore(Tester t) {
    return t.checkExpect(this.jorts.comesBefore(this.apple), false)
        && t.checkExpect(this.pulled.comesBefore(this.quack), true)
        && t.checkExpect(this.emptyword.comesBefore(this.baseball), true);
  }

  //to test comesBeforeHelper

  boolean testComesBeforeHelper(Tester t) {
    return t.checkExpect(this.pulled.comesBeforeHelper("goop"), true)
        && t.checkExpect(this.emptyword.comesBeforeHelper("poop"), false)
        && t.checkExpect(this.kong.comesBeforeHelper("zoo"), false);

  }
  
  //to test equalsEmptyString
  boolean testEmptyString(Tester t) {
    return t.checkExpect(this.pulled.equalsEmptyString(), false)
        && t.checkExpect(this.emptyword.equalsEmptyString(), true);
  }

  //to test draw
  boolean testDraw(Tester t) { 
    return 
        t.checkExpect(this.list1A.draw(new WorldScene(400, 400)),
            new WorldScene(400, 400)
            .placeImageXY((new TextImage("Apple", Color.pink)), 4, 6))
        && 
        t.checkExpect(this.list1D.draw(new WorldScene(400, 400)),
            new WorldScene(400, 400)
            .placeImageXY((new TextImage("Jorts", Color.green)), 23, 27))
        &&
        t.checkExpect(this.mt.draw(new WorldScene(400, 400)),
            new WorldScene(400, 400)); 

  }
}