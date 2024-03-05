import tester.Tester;

//represents a type of entertainment
interface IEntertainment {
  //compute the total price of this Entertainment
  double totalPrice();

  //computes the minutes of entertainment of this IEntertainment
  int duration();

  //produce a String that shows the name and price of this IEntertainment
  String format();

  //is this IEntertainment the same as that one?
  boolean sameEntertainment(IEntertainment that);

  //compares two magazines
  boolean magazineCompare(Magazine that);

  //compares two tv series
  boolean tvSeriesCompare(TVSeries that);

  //compares two podcasts
  boolean podcastCompare(Podcast that);


}

abstract class AEntertainment implements IEntertainment {
  String name;
  double price;
  int installments;

  AEntertainment(String name, double price, int installments) {
    this.name = name;
    this.price = price;
    this.installments = installments;
  }

  //compute the total price of this Entertainment
  public double totalPrice() {
    return this.price * this.installments;
  }

  //computes the minutes of entertainment of this IEntertainment
  public int duration() {
    return (50 * this.installments);
  }

  //produce a String that shows the name and price of this IEntertainment
  public String format() {
    return this.name + ", " + Double.toString(this.price) + ".";
  }

  //is this IEntertainment the same as that one?
  abstract public boolean sameEntertainment(IEntertainment that);

  //compares to magazines
  public boolean magazineCompare(Magazine that) {
    return false;
  }

  //compares two tv series
  public boolean tvSeriesCompare(TVSeries that) {
    return false;
  }

  //compares two podcasts
  public boolean podcastCompare(Podcast that) {
    return false;
  }
}


//represents a magazine
class Magazine extends AEntertainment {
  String genre;
  int pages;

  Magazine(String name, double price, String genre, int pages, int installments) {
    super(name, price, installments);
    this.genre = genre;
    this.pages = pages;
  }

  /* fields 
   * ... this.genre ... -- String
   * ... this.pages ... -- int
   * ... this.name ... -- String
   * ... this.price ... -- int
   * ... this.installments ... -- int
   * 
   * methods
   * this.duration() -- int
   * this.totalPrice() -- double
   * this.format() -- String
   * this.sameEntertainment(IEntertainment that) -- boolean
   * this.magazineCompare(Magazine that) -- boolean
   * this.tvSeriesCompares(TVSeries that) -- boolean
   * this.podcastCompare(Podcast that) -- boolean
   * 
   * methods for fields: n/a
   */

  //computes the minutes of entertainment of this Magazine, (includes all installments)
  @Override
  public int duration() {
    return (5 * this.pages * this.installments);
  }

  //is this IEntertainment the same as that one?
  /* fields of that: n/a
   * 
   * methods of that:
   * that.duration() -- int
   * that.totalPrice() -- double
   * that.format() -- String
   * that.sameEntertainment(IEntertainment that) -- boolean
   * that.magazineCompare(Magazine that) -- boolean
   * that.tvSeriesCompares(TVSeries that) -- boolean
   * that.podcastCompare(Podcast that) -- boolean
   * 
   * methods for fields of that: n/a
   */
  public boolean sameEntertainment(IEntertainment that) {
    return that.magazineCompare(this);
  }

  //is this Magazine the same as that IEntertainment?
  /* fields of that: 
   * ... this.genre ... -- String
   * ... this.pages ... -- int
   * ... this.name ... -- String
   * ... this.price ... -- int
   * ... this.installments ... -- int
   * 
   * methods of that:
   * that.duration() -- int
   * that.totalPrice() -- double
   * that.format() -- String
   * that.sameEntertainment(IEntertainment that) -- boolean
   * that.magazineCompare(Magazine that) -- boolean
   * 
   * methods for fields of that: n/a
   */
  @Override
  public boolean magazineCompare(Magazine that) {
    return this.name.equals(that.name)
        && this.price == that.price
        && this.genre.equals(that.genre)
        && this.pages == that.pages
        && this.installments == that.installments;
  }
}



//represents a Television series
class TVSeries extends AEntertainment {
  String corporation;

  TVSeries(String name, double price, int installments, String corporation) {
    super(name, price, installments);
    this.corporation = corporation;
  }

  /* fields 
   * ... this.name ... -- String
   * ... this.price ... -- int
   * ... this.installments ... - ints
   * ... this.corporation ... -- int
   * 
   * methods
   * this.duration() -- int
   * this.totalPrice() -- double
   * this.format() -- String
   * this.sameEntertainment(IEntertainment that) -- boolean
   * this.magazineCompare(Magazine that) -- boolean
   * this.tvSeriesCompares(TVSeries that) -- boolean
   * this.podcastCompare(Podcast that) -- boolean
   * 
   * methods for fields: n/a
   */

  //is this TVSeries the same as that IEntertainment?
  /* fields of that: n/a
   * 
   * methods of that:
   * that.duration() -- int
   * that.totalPrice() -- double
   * that.format() -- String
   * that.sameEntertainment(IEntertainment that) -- boolean
   * that.magazineCompare(Magazine that) -- boolean
   * that.tvSeriesCompares(TVSeries that) -- boolean
   * that.podcastCompare(Podcast that) -- boolean
   * 
   * methods for fields of that: n/a
   */
  public boolean sameEntertainment(IEntertainment that) {
    return that.tvSeriesCompare(this);
  }

  //is this Magazine the same as that IEntertainment?
  /* fields of that: 
   * ... this.name ... -- String
   * ... this.price ... -- int
   * ... this.installments ... - ints
   * ... this.corporation ... -- int
   * 
   * methods of that:
   * that.duration() -- int
   * that.totalPrice() -- double
   * that.format() -- String
   * that.sameEntertainment(IEntertainment that) -- boolean
   * that.tvSeriesCompare(TVSeries that) -- boolean
   * 
   * methods for fields of that: n/a
   */
  @Override
  public boolean tvSeriesCompare(TVSeries that) {
    return this.name.equals(that.name)
        && this.price == that.price
        && this.corporation == that.corporation
        && this.installments == that.installments;
  }
}

//represents a podcast
class Podcast extends AEntertainment {

  Podcast(String name, double price, int installments) {
    super(name, price, installments);
  }

  /* fields 
   * ... this.name ... -- String
   * ... this.price ... -- int
   * ... this.installments ... - int
   * 
   * methods
   * this.duration() -- int
   * this.totalPrice() -- double
   * this.format() -- String
   * this.sameEntertainment(IEntertainment that) -- boolean
   * this.magazineCompare(Magazine that) -- boolean
   * this.tvSeriesCompares(TVSeries that) -- boolean
   * this.podcastCompare(Podcast that) -- boolean
   * 
   * methods for fields: n/a
   */

  //is this Podcast the same as that IEntertainment?
  /* fields of that: n/a
   * 
   * methods of that:
   * that.duration() -- int
   * that.totalPrice() -- double
   * that.format() -- String
   * that.sameEntertainment(IEntertainment that) -- boolean
   * that.magazineCompare(Magazine that) -- boolean
   * that.tvSeriesCompares(TVSeries that) -- boolean
   * that.podcastCompare(Podcast that) -- boolean
   * 
   * methods for fields of that: n/a
   */
  public boolean sameEntertainment(IEntertainment that) {
    return that.podcastCompare(this);
  }

  //is this Magazine the same as that IEntertainment?
  /* fields: n/a
   * 
   * methods
   * that.duration() -- int
   * that.totalPrice() -- double
   * that.format() -- String
   * that.sameEntertainment(IEntertainment that) -- boolean
   * that.podcastCompare(Podcast that) -- boolean
   * 
   * methods for fields: n/a
   */
  @Override
  public boolean podcastCompare(Podcast that) {
    return this.name.equals(that.name)
        && this.price == that.price
        && this.installments == that.installments;
  }
}

class ExamplesEntertainment {
  IEntertainment rollingStone = new Magazine("Rolling Stone", 2.55, "Music", 60, 12);
  IEntertainment elle = new Magazine("Elle", 4.66, "Fashion", 50, 8);
  IEntertainment houseOfCards = new TVSeries("House of Cards", 5.25, 13, "Netflix");
  IEntertainment gossipGirl = new TVSeries("Gossip Girl", 0.0, 5, "HBO");
  IEntertainment npr = new Podcast("NPR", 0.0, 15);
  IEntertainment serial = new Podcast("Serial", 0.0, 8);

  //testing total price method
  boolean testTotalPrice(Tester t) {
    return t.checkInexact(this.rollingStone.totalPrice(), 2.55 * 12, .0001) 
        && t.checkInexact(this.houseOfCards.totalPrice(), 5.25 * 13, .0001)
        && t.checkInexact(this.serial.totalPrice(), 0.0, .0001)
        && t.checkInexact(this.gossipGirl.totalPrice(), 0.0, 0.001);
  }

  //testing duration method
  boolean testDuration(Tester t) {
    return t.checkExpect(this.rollingStone.duration(), 5 * 60 * 12)
        && t.checkExpect(this.elle.duration(), 5 * 50 * 8)
        && t.checkExpect(this.houseOfCards.duration(), 50 * 13)
        && t.checkExpect(this.gossipGirl.duration(), 50 * 5)
        && t.checkExpect(this.npr.duration(), 50 * 15)
        && t.checkExpect(this.serial.duration(), 50 * 8);
  }

  //testing format method
  boolean testFormat(Tester t) {
    return t.checkExpect(this.rollingStone.format(), "Rolling Stone, 2.55.")
        && t.checkExpect(this.elle.format(), "Elle, 4.66.")
        && t.checkExpect(this.houseOfCards.format(), "House of Cards, 5.25.")
        && t.checkExpect(this.gossipGirl.format(), "Gossip Girl, 0.0.")
        && t.checkExpect(this.npr.format(), "NPR, 0.0.")
        && t.checkExpect(this.serial.format(), "Serial, 0.0.");
  }

  //testing same entertainment method
  boolean testSameEntertainment(Tester t) {
    return t.checkExpect(this.elle.sameEntertainment(this.rollingStone), false)
        && t.checkExpect(this.elle.sameEntertainment(this.elle), true)
        && t.checkExpect(this.gossipGirl.sameEntertainment(this.houseOfCards), false)
        && t.checkExpect(this.gossipGirl.sameEntertainment(this.gossipGirl), true)
        && t.checkExpect(this.npr.sameEntertainment(this.serial), false)
        && t.checkExpect(this.npr.sameEntertainment(this.npr), true)
        && t.checkExpect(this.elle.sameEntertainment(this.gossipGirl), false)
        && t.checkExpect(this.houseOfCards.sameEntertainment(this.serial), false)
        && t.checkExpect(this.npr.sameEntertainment(this.rollingStone), false);
  }

  //testing the magazineCompare method
  boolean testMagazineCompare(Tester t) {
    return t.checkExpect(this.rollingStone.magazineCompare(
        new Magazine("Rolling Ston", 2.55, "Music", 60, 12)), false)
        && t.checkExpect(this.rollingStone.magazineCompare(
            new Magazine("Rolling Stone", 2.00, "Music", 60, 12)), false)
        && t.checkExpect(this.rollingStone.magazineCompare(
            new Magazine("Rolling Stone", 2.55, "Music", 65, 12)), false)
        && t.checkExpect(this.rollingStone.magazineCompare(
            new Magazine("Rolling Stone", 2.55, "Music", 60, 10)), false)
        && t.checkExpect(this.rollingStone.magazineCompare(
            new Magazine("Rolling Stone", 2.55, "Fashion", 60, 12)), false)
        && t.checkExpect(this.rollingStone.magazineCompare(
            new Magazine("Rolling Stone", 2.55, "Music", 60, 12)), true);
  }

  //testing the tvSeriesCompare method
  boolean testTvSeriesCompare(Tester t) {
    return t.checkExpect(this.gossipGirl.tvSeriesCompare(
        new TVSeries("Gossip Gir", 0.0, 5, "HBO")), false)
        && t.checkExpect(this.gossipGirl.tvSeriesCompare(
            new TVSeries("Gossip Girl", 1.0, 5, "HBO")), false)
        && t.checkExpect(this.gossipGirl.tvSeriesCompare(
            new TVSeries("Gossip Girl", 0.0, 6, "HBO")), false)
        && t.checkExpect(this.gossipGirl.tvSeriesCompare(
            new TVSeries("Gossip Girl", 0.0, 5, "Netflix")), false)
        && t.checkExpect(this.gossipGirl.tvSeriesCompare(
            new TVSeries("Gossip Girl", 0.0, 5, "HBO")), true);
  }

  //testing the podcastCompare method
  boolean testPodcastCompare(Tester t) {
    return t.checkExpect(this.npr.podcastCompare(
        new Podcast("NP", 0.0, 15)), false)
        && t.checkExpect(this.npr.podcastCompare(
            new Podcast("NPR", 1.0, 15)), false)
        && t.checkExpect(this.npr.podcastCompare(
            new Podcast("NPR", 0.0, 16)), false)
        && t.checkExpect(this.npr.podcastCompare(
            new Podcast("NPR", 0.0, 15)), true);
  }
}