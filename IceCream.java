//represents an ice cream
interface IIceCream {
  
}

//to represent an empty serving
class EmptyServing implements IIceCream {
  boolean cone;
  
  EmptyServing(boolean cone) {
    this.cone = cone;
  }
  
}

//to represent a scooped 
class Scooped implements IIceCream {
  IIceCream more;
  String flavor;
  
  Scooped(IIceCream more, String flavor) {
    this.more = more;
    this.flavor = flavor;
  }
  
}

//represents examples for an ice cream
class ExamplesIceCream implements IIceCream {
  //to represent the two possibilities of EmptyServing
  IIceCream cone = new EmptyServing(true);
  IIceCream cup = new EmptyServing(false);
  //to represent an example IIceCream in a cup
  IIceCream ic11 = new Scooped(this.cup, "coffee");
  IIceCream ic12 = new Scooped(this.ic11, "black raspberry");
  IIceCream ic13 = new Scooped(this.ic12, "caramel swirl");
  IIceCream order1 = new Scooped(this.ic13, "mint chip");
  //to represent an example IIceCream in a cone
  IIceCream ic21 = new Scooped(this.cone, "strawberry");
  IIceCream ic22 = new Scooped(this.ic21, "vanilla");
  IIceCream order2 = new Scooped(this.ic22, "chocolate");
}