import tester.Tester; 

//to represent the EmbroideryPiece
class EmbroideryPiece {
  String name;
  IMotif motif;

  //constructor
  EmbroideryPiece(String name, IMotif motif) {
    this.name = name;
    this.motif = motif;
  }


  /*
   * TEMPLATE:
   * --------
   * Fields:
   * ... this.name ...     -- String
   * ... this.motifs ...    -- IMotif
   * 
   * Methods:
   * this.countMotifs()      -- int
   * this.averageDifficulty() -- double
   * this.embroideryInfo() -- String
   * 
   * Methods for Fields:
   * this.motifs.getDifficulty() -- double
   * this.motifs.countMotifs() -- double
   * this.motifs.motifToString --String
   */

  //number of motifs
  public int countMotifs() {
    return this.motif.countMotifs();
  }

  //averages the difficulty level of the motifs in an EmbroideryPiece
  public double averageDifficulty() {
    if (this.countMotifs() < 1) {
      return 0 ;
    }
    else {
      return this.motif.getDifficulty() / this.motif.countMotifs();
    }
  }

  //string with the motifs's data type and name
  public String embroideryInfo() {
    return this.name + ": " + this.motif.motifToString() + ".";
  }
}

//to represent an IMotif
interface IMotif {

  //retrieves difficulty level of motifs
  double getDifficulty();

  //number of motifs
  int countMotifs();

  //a string of the name and type of the motif
  String motifToString(); 
}

//to represent a CrossStitchMotif
class CrossStitchMotif implements IMotif {
  String description;
  Double difficulty;

  //constructor
  CrossStitchMotif(String description, Double difficulty) {
    this.description = description;
    this.difficulty = difficulty;
  }

  /*
   * TEMPLATE:
   * --------
   * Fields:
   * ... this.description ...     -- String
   * ... this.difficulty ...      -- Double
   * Methods:
   * this.getDifficulty() -- double
   * this.countMotifs() -- int
   * this.motifToString() -- String
   * Methods for Fields: n/a
   */

  //retrieves difficulty level of motifs
  public double getDifficulty() {
    return difficulty;
  }

  //number of motifs
  public int countMotifs() {
    return 1;
  } 

  //a string of the name and type of the motif
  public String motifToString() {
    return this.description + " (cross stitch)";
  }
}


//to represent a ChainStitchMotif
class ChainStitchMotif implements IMotif {
  String description;
  Double difficulty;

  //constructor
  ChainStitchMotif(String description, Double difficulty) {
    this.description = description;
    this.difficulty = difficulty;
  }

  /*
   * TEMPLATE:
   * --------
   * Fields:
   * ... this.description ...     -- String
   * ... this.difficulty ...      -- Double
   * Methods:
   * this.getDifficulty() -- double
   * this.countMotifs() -- int
   * this.motifToString() -- String
   * Methods for Fields: n/a
   */

  //retrieves difficulty level of motifs
  public double getDifficulty() {
    return difficulty;
  }
  
  //number of motifs
  public int countMotifs() {
    return 1;
  }

  //a string of the name and type of the motif
  public String motifToString() {
    return this.description + " (chain stitch)";
  }

}

//to represent a GroupMotif
class GroupMotif implements IMotif {
  String description;
  ILoMotif motifs;

  //constructor
  GroupMotif(String description, ILoMotif motifs) {
    this.description = description;
    this.motifs = motifs;
  }

  /*
   * TEMPLATE:
   * --------
   * Fields:
   * ... this.description ...     -- String
   * ... this.difficulty ...      -- ILoMotif
   * 
   * Methods:
   * this.getDifficulty() -- double
   * this.countMotifs() -- int
   * this.motifToString() -- String
   * 
   * Methods for Fields:
   * this.motifs.countMotifs() -- int
   * this.motifs.totalDifficulty() -- double
   * this.motifs.motifsToString() -- String
   */

  //number of motifs
  public int countMotifs() {
    return this.motifs.countMotifs();
  }

  //retrieves difficulty level of motifs
  public double getDifficulty() {
    return this.motifs.totalDifficulty();
  }

  //a string of the name and type of the motif
  public String motifToString() {
    return this.motifs.motifsToString();
  }
}


//
interface ILoMotif {

  //number of motifs
  int countMotifs();

  //the sum of the averages of the list of motifs
  double totalDifficulty();

  //extracts a string of the name and type of the motif from a list
  String motifsToString();


}

//to count the number of motifs in MtLoMotif
class MtLoMotif implements ILoMotif {

  //number of motifs
  public int countMotifs() {
    return 0;
  } 

  //the sum of the averages of the list of motifs
  public double totalDifficulty() {
    return 0;
  }

  //extracts a string of the name and type of the motif from a list
  public String motifsToString() {
    return "";
  }
}


/*
 * TEMPLATE:
 * --------
 * Fields: n/a
 * 
 * Methods:
 * this.countMotifsy() -- int
 * this.totalDifficulty() -- double
 * this.motifsToString -- String
 * 
 * Methods for Fields: n/a 
 */

//to represent a ConsLoMotif
class ConsLoMotif implements ILoMotif {
  IMotif first;
  ILoMotif rest;

  //constructor
  ConsLoMotif(IMotif first, ILoMotif rest) {
    this.first = first;
    this.rest = rest;
  }

  /*
   * TEMPLATE:
   * --------
   * Fields: 
   * ... this.first ... -- IMotif
   * ... this.rest ... -- IMotif
   * 
   * Methods:
   * this.countMotifsy() -- int
   * this.totalDifficulty() -- double
   * this.motifsToString -- String
   * 
   * Methods for Fields:
   * this.first.getDifficulty() -- double
   * this.rest.totalDifficulty() -- double 
   * this.first.countMotifs() -- int
   * this.rest.countMotifs() -- int
   * this.rest.motifsToString() -- String
   * this.rest.motifToString() -- String
   */

  //number of motifs
  public int countMotifs() {
    return this.first.countMotifs() + this.rest.countMotifs();
  }

  //the sum of the averages of the list of motifs
  public double totalDifficulty() {
    return this.first.getDifficulty() + this.rest.totalDifficulty();
  }

  //extracts a string of the name and type of the motif from a list
  public String motifsToString() {
    if (this.rest.countMotifs() == 0) {
      return this.first.motifToString();
    }
    else {
      return this.first.motifToString() + ", " + this.rest.motifsToString();
    }
  }

}


/*
 * TEMPLATE:
 * --------
 * Fields:
 * ... this.first ...     -- IMotif
 * ... this.rest ...      -- ILoMotif
 * Methods:
 *  this.countMotifs()      -- boolean
 *  this.totalDifficulty() -- boolean
 * Methods for Fields:
 *  this.first.getDifficulty()  -- boolean
 *  this.rest.countMotifs(ILoMotif)    -- boolean
 *  this.rest.totalDifficulty() -- boolean
 */

//
class ExamplesEmbroidery {
  IMotif daisy = new CrossStitchMotif("daisy", 3.2);
  IMotif poppy = new ChainStitchMotif("poppy", 4.75);
  IMotif rose = new CrossStitchMotif("rose", 5.0);

  ILoMotif mtMotifs = new MtLoMotif();

  ILoMotif list1 = new ConsLoMotif(this.daisy, this.mtMotifs);
  ILoMotif list2 = new ConsLoMotif(this.poppy, this.list1);
  ILoMotif list3 = new ConsLoMotif(this.rose, this.list2);

  IMotif flowers = new GroupMotif("flowers", this.list3);

  IMotif tree = new ChainStitchMotif("tree", 3.0);
  IMotif bird = new CrossStitchMotif("bird", 4.5);

  ILoMotif list4 = new ConsLoMotif(this.flowers, this.mtMotifs);
  ILoMotif list5 = new ConsLoMotif(this.tree, this.list4);
  ILoMotif list6 = new ConsLoMotif(this.bird, this.list5);

  IMotif nature = new GroupMotif("nature", this.list6);
  IMotif design = new GroupMotif("design", this.list4);

  EmbroideryPiece pillowCover = new EmbroideryPiece("Pillow Cover", this.nature);
  
  EmbroideryPiece lamp = new EmbroideryPiece("Lamp",this.design);

  //tests the countMotifs method
  boolean testCountMotifs(Tester t) {
    return t.checkExpect(this.daisy.countMotifs(), 1) 
        && t.checkExpect(this.mtMotifs.countMotifs(), 0)
        && t.checkExpect(this.list1.countMotifs(), 1)
        && t.checkExpect(this.flowers.countMotifs(), 3)
        && t.checkExpect(this.pillowCover.countMotifs(), 5);
  }

  //tests the averageDifficulty method
  boolean testAverageDifficulty(Tester t) {
    return t.checkExpect(this.pillowCover.averageDifficulty(), 4.09)
        && t.checkExpect(this.lamp.averageDifficulty(), 4.316666666666666);
  }

  //tests the getDifficulty method
  boolean testGetDifficulty(Tester t) {
    return t.checkExpect(this.poppy.getDifficulty(), 4.75)
        && t.checkExpect(this.flowers.getDifficulty(), 12.95);
  }

  //tests the motifToString method
  boolean testMotifToString(Tester t) {
    return t.checkExpect(this.rose.motifToString(), "rose (cross stitch)")
        && t.checkExpect(this.nature.motifToString(), "bird (cross stitch), tree (chain stitch), "
            + "rose (cross stitch), poppy (chain stitch), daisy (cross stitch)");
        
  }
  
  //tests the motifsToString method
  boolean testMotifsToString(Tester t) {
    return t.checkExpect(this.mtMotifs.motifsToString(), "")
        && t.checkExpect(this.list5.motifsToString(), "tree (chain stitch), rose (cross stitch), "
            + "poppy (chain stitch), daisy (cross stitch)")
        && t.checkExpect(this.list2.motifsToString(), "poppy (chain stitch), daisy (cross stitch)");
  }
  
  //tests the totalDifficulty method
  boolean testTotalDifficulty(Tester t) {
    return t.checkExpect(this.list1.totalDifficulty(), 3.2)
        && t.checkExpect(this.mtMotifs.totalDifficulty(), 0.0);
  }
  
  //tests the embroideryInfo method
  boolean testEmbroideryInfo(Tester t) {
    return t.checkExpect(this.pillowCover.embroideryInfo(), 
        "Pillow Cover: bird (cross stitch), tree (chain stitch), rose (cross stitch), "
        + "poppy (chain stitch), daisy (cross stitch).")
        && t.checkExpect(this.lamp.embroideryInfo(), "Lamp: rose (cross stitch), "
            + "poppy (chain stitch), daisy (cross stitch).");
  }
}