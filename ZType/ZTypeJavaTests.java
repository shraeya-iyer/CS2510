import java.awt.Color;
import java.util.Random;

import javalib.funworld.World;
import javalib.funworld.WorldScene;
import javalib.worldimages.TextImage;
import tester.Tester;

//all examples and tests for ILoWord
class ExamplesWordLists implements IZtypeConstants {

  //examples of an ActiveWord
  IWord apple = new ActiveWord("Apple", 4, 6);
  IWord appleplus10 = new ActiveWord("Apple", 4, 16);
  IWord pple = new ActiveWord("pple", 4, 6);
  IWord arnold = new ActiveWord("arnold", 19, 28);
  IWord rnold = new ActiveWord("rnold", 19, 28);
  IWord baseball = new ActiveWord("baseball", 10, 15);
  IWord cat = new ActiveWord("Cat", 7, 15);
  IWord kong = new ActiveWord("Kong", 8, 12);
  IWord pulled = new ActiveWord("pulled", 9, 11);
  IWord quack = new ActiveWord("Quack", 17, 19);
  IWord broach = new ActiveWord("broach", 200, 400);

  //examples of an InactiveWord
  IWord dog = new InactiveWord("dog", 30, 10);
  IWord realisticApple = new InactiveWord("Apple", 300, 20);
  IWord jorts = new InactiveWord("Jorts", 23, 27);
  IWord jortsplus10 = new InactiveWord("Jorts", 23, 37);
  IWord lemur = new InactiveWord("Lemur", 14, 26);
  IWord alexa = new InactiveWord("Alexa", 15, 25);
  IWord realisticAlexa = new InactiveWord("Alexa", 237, 169);
  IWord store = new InactiveWord("store", 2, 9);
  IWord wombat = new InactiveWord("Wombat", 1, 29);
  IWord ombat = new ActiveWord("ombat", 1, 29);
  IWord wombatplus10 = new InactiveWord("Wombat", 1, 39);
  IWord zebraplus10 = new InactiveWord("Zebra", 5, 15);
  IWord zebra = new InactiveWord("zebra", 5, 5);
  IWord emptyword = new InactiveWord("", 2, 7);

  //examples of an empty list
  ILoWord mt = new MtLoWord();

  //examples of a ZTypeWorld
  ZTypeWorld world3 = new ZTypeWorld(this.list6B);
  ZTypeWorld world2 = new ZTypeWorld(this.list6A, dropRate, new Random());
  ZTypeWorld world1 = new ZTypeWorld(this.list1E, 0, new Random(4));

  //examples of a ConsLoWord
  ILoWord list1A = new ConsLoWord(this.apple, this.mt);
  ILoWord list1D = new ConsLoWord(this.jorts, this.mt);
  ILoWord list1E = new ConsLoWord(this.realisticApple, this.mt);
  ILoWord list1F = new ConsLoWord(this.realisticAlexa, this.mt);
  ILoWord list2A = new ConsLoWord(this.wombat, this.list1A);
  ILoWord list2B = new ConsLoWord(this.wombatplus10, new ConsLoWord(this.appleplus10, mt));
  ILoWord list3A = new ConsLoWord(this.dog, this.list2A);
  ILoWord list4A = new ConsLoWord(this.zebra, this.list3A);
  ILoWord list5A = new ConsLoWord(this.lemur, this.list4A);
  ILoWord list6A = new ConsLoWord(this.baseball, this.list5A);
  ILoWord list6B = new ConsLoWord(this.broach, this.list5A);
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


  //merge output lists
  ILoWord list1920merge = new ConsLoWord(this.apple,
      new ConsLoWord(this.dog,
          new ConsLoWord(this.jorts,
              new ConsLoWord(this.lemur, this.mt))));

  ILoWord list2021merge = new ConsLoWord(this.apple,
      new ConsLoWord(this.jorts,
          new ConsLoWord(this.kong, this.mt)));

  //
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
          new ConsLoWord(this.arnold,
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

  boolean testBigBang(Tester t) {                                                  
    ZTypeWorld w = new ZTypeWorld(this.list1E, 0, new Random(4));
    int worldWidth = width;
    int worldHeight = height;
    double tickRate = 0.2;
    return w.bigBang(worldWidth, worldHeight, tickRate);
  }

  //to test checkAndReduce
  boolean testCheckAndReduce(Tester t) {
    return t.checkExpect(this.list15A.checkAndReduce("a"), this.list15A)
        && t.checkExpect(this.mt.checkAndReduce("a"), this.mt);
  }     

  //to removeFirstLetter
  boolean testRemoveFirstLetter(Tester t) {
    return t.checkExpect(this.apple.removeFirstLetter("A"), this.pple)
        && t.checkExpect(this.arnold.removeFirstLetter("a"), this.rnold)
        && t.checkExpect(this.alexa.removeFirstLetter("a"), this.alexa);
  } 

  //to test andToEnd
  boolean testAddToEnd(Tester t) {
    return t.checkExpect(this.list1A.addToEnd(this.wombat), this.list1C)
        && t.checkExpect(this.mt.addToEnd(this.apple), this.list1A);
  }

  //to test filterOutEmpties
  boolean testFilterOutEmpties(Tester t) {
    return t.checkExpect(this.list16A.filterOutEmpties(), this.list16A)
        && t.checkExpect(this.list17A.filterOutEmpties(), this.list17B) 
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
            .placeImageXY((new TextImage("Apple", 20, Color.pink)), 4, 6))
        && 
        t.checkExpect(this.list1D.draw(new WorldScene(400, 400)),
            new WorldScene(400, 400)
            .placeImageXY((new TextImage("Jorts", 20, Color.green)), 23, 27))
        &&
        t.checkExpect(this.mt.draw(new WorldScene(400, 400)),
            new WorldScene(400, 400)); 
  }

  //to test is word at bottom
  boolean testIsWordAtBottom(Tester t) {
    return t.checkExpect(this.broach.isWordAtBottom(), true)
        && t.checkExpect(this.apple.isWordAtBottom(), false);
  }

  //to test is word at bottom
  boolean testAreWordsAtBottom(Tester t) {
    return t.checkExpect(this.list6B.areWordsAtBottom(), true)
        && t.checkExpect(this.list6A.areWordsAtBottom(), false)
        && t.checkExpect(this.mt.areWordsAtBottom(), false);
  }

  //wordDown 
  boolean testwordDown(Tester t) {
    return t.checkExpect(this.apple.wordDown(), this.appleplus10);
  }

  //wordsDown
  boolean testWordsDown(Tester t) {
    return t.checkExpect(this.list2A.wordsDown(), this.list2B)
        && t.checkExpect(this.mt.wordsDown(), this.mt);
  }

  //tests the wordsActive method
  boolean testWordsActive(Tester t) {
    return t.checkExpect(this.list1A.wordsActive(), true)
        && t.checkExpect(this.list1D.wordsActive(), false)
        && t.checkExpect(this.list6A.wordsActive(), true)
        && t.checkExpect(this.mt.wordsActive(), false);
  }

  //tests the wordActive method
  boolean testWordActive(Tester t) {
    return t.checkExpect(this.apple.wordActive(), true)
        && t.checkExpect(this.store.wordActive(), false);
  }

  //tests the firstLetterEqual method
  boolean testFirstLetterEqual(Tester t) {
    return t.checkExpect(this.jorts.firstLetterEqual("J"), true)
        && t.checkExpect(this.jorts.firstLetterEqual("j"), false)
        && t.checkExpect(this.cat.firstLetterEqual("C"), true)
        && t.checkExpect(this.cat.firstLetterEqual("c"), false);
  }

  //tests the activates method
  boolean testActivateWord(Tester t) {
    return t.checkExpect(this.list2A.activateWord("W"), 
        new ConsLoWord(this.ombat, 
            new ConsLoWord(this.apple, this.mt)))
        && t.checkExpect(this.list2A.activateWord("w"),
            new ConsLoWord(this.wombat, 
                new ConsLoWord(this.apple, this.mt))) 
        && t.checkExpect(this.mt.activateWord("A"), this.mt);
  }

  //makeScene
  boolean testMakeScene(Tester t) {
    ZTypeWorld zeroTick = new ZTypeWorld(this.list1E, 1, new Random(4513));
    World firstTick = zeroTick.onTick(); // counter = 1
    World secondTick = firstTick.onTick(); // counter = 2

    return 
        t.checkExpect(zeroTick.makeScene(), 
            new WorldScene(width, height).placeImageXY(
                new TextImage("Apple", 20, Color.green), 300, 20))
        && t.checkExpect(firstTick.makeScene(), 
            new WorldScene(width, height).placeImageXY(
                new TextImage("Apple", 20, Color.green), 300, 20 + 10))
        && t.checkExpect(secondTick.makeScene(), 
            new WorldScene(width, height).placeImageXY(
                new TextImage("Apple", 20, Color.green), 300, 20 + 20).placeImageXY(
                    (new TextImage("txecgr", 20, Color.green)), 69, 12)); 

  }

  //onKeyEvent
  boolean testOnTick(Tester t) {
    ZTypeWorld zeroTick = new ZTypeWorld(this.list1E, 0, new Random(4513));
    ZTypeWorld zeroTIckA = new ZTypeWorld(this.list1F, 0, new Random(4513));
    World firstTick = zeroTick.onTick(); // counter = 1
    World secondTick = firstTick.onTick(); // counter = 2

    return 
        t.checkExpect(zeroTick.onTick(), 
            new ZTypeWorld(new ConsLoWord(new InactiveWord("Apple", 300, 30), this.mt), 
                1, new Random(4513)))
        && t.checkExpect(secondTick.onTick(), 
            new ZTypeWorld(new ConsLoWord(new InactiveWord("Apple", 300, 50),  
                new ConsLoWord(new InactiveWord("txecgr", 69, 12), this.mt)), 
                3, new Random(4513)));
  }


  //lastScene
  boolean testLastScene(Tester t) { 
    return 
        t.checkExpect(this.world1.lastScene("END GAME"), 
            new WorldScene(width, height).placeImageXY(
                new TextImage("END GAME", 55, Color.red), 300, 200));
  }

  //onKey
  boolean testOnKey(Tester t) {
    ZTypeWorld zeroTick = new ZTypeWorld(this.list1E, 0, new Random(4513));
    World firstTick = zeroTick.onTick(); // counter = 1
    World secondTick = firstTick.onTick(); // counter = 2

    return 
        t.checkExpect(zeroTick.onKeyEvent("A"), 
            new ZTypeWorld(new ConsLoWord(new ActiveWord("pple", 300, 20), this.mt),
                0, new Random(4513)))
        && t.checkExpect(zeroTick.onKeyEvent("p"), 
            new ZTypeWorld(this.list1E, 0, new Random(4513)));
  }

  //randWord
  boolean testRand(Tester t) {
    return 
        t.checkExpect(this.world1.randWord(), new InactiveWord("qsnqfp", 261, 2))
        && t.checkExpect(this.world2.randWord(), new InactiveWord("dsqreg", 77, 2)) 
        //random information keeps changing for the second test, not sure why since
        //the rest of the randoms are working
        && t.checkExpect(this.world3.randWord(), new InactiveWord("hqkbtn", 248, 2));
  }
}
