import tester.Tester;
class CakeRecipe {
  double flour;
  double sugar;
  double eggs;
  double butter;
  double milk;
  //first constructor: outputs flour, sugar, eggs, butter, and milk if they meet the constraints
  CakeRecipe(double flour, double sugar, double eggs, double butter, double milk) {
    Utils u = new Utils();
    this.flour = u.compareWeights(flour, sugar, 
        "Invalid weight: weights of flour and sugar are inequal");
    this.sugar = u.compareWeights(sugar, flour,
        "Invalid weight: weights of sugar and flour are inequal");
    this.eggs = u.compareWeights(eggs, butter,
        "Invalid weight: weights of eggs and butter are unequal");
    this.butter = u.compareWeights(butter, eggs,
        "Invalid weight: weights of butter and eggs are unequal");
    this.milk = u.compareWeights((eggs + milk), sugar,
        "Invalid weight: milk weight doesn't equal the weight of "
        + "the flour minus the eggs");
  }
  //second constructor: substitutes flour for sugar and eggs for butter since they are equal
  CakeRecipe(double flour, double eggs, double milk) {
    this(flour, flour, eggs, eggs, milk);
  }
  //third constructor: returns the ingredients based on whether they are measured in volumes or not
  CakeRecipe(double flour, double eggs, double milk, boolean areVolumes) {
    if (areVolumes) {
      Utils u = new Utils();
      this.flour = flour * 4.25; 
      this.sugar = this.flour; 
      this.eggs = eggs * 1.75;
      this.butter = this.eggs;
      this.milk = u.compareWeights(this.flour, milk * 8 + this.eggs,
          "Invalid weight: milk weight doesn't equal the weight of the eggs minus the flour");
    } 
    else {
      Utils u = new Utils(); 
      this.flour = flour;
      this.sugar = flour;
      this.eggs = eggs;
      this.butter = eggs;
      this.milk = u.compareWeights(this.flour, milk + this.eggs,
          "Invalid weight: milk weight doesn't equal the weight of the eggs minus the flour");
    }
  }
  /*
   * TEMPLATE:
   * --------
   * Fields: 
   * ... this.flour ... -- double
   * ... this.sugar ... -- double
   * ... this.eggs ... -- double
   * ... this.butter ... -- double
   * ... this.milk ... -- double
   * Methods:
   * this.sameRecipe(CakeRecipe other) -- CakeRecipe
   * Methods for Fields: n/a
   */
  //checks if two recipes have the same weights for each ingredient
  /* fields of other: n/a
   * 
   * methods of other: 
   * other.sameRecipe(CakeRecipe other) -- CakeRecipe
   * 
   * methods for fields of other: n/a
   */
  public boolean sameRecipe(CakeRecipe other) {
    return Math.abs(this.flour - other.flour) < 0.001
        && Math.abs(this.sugar - other.sugar) < 0.001
        && Math.abs(this.eggs - other.eggs) < 0.001
        && Math.abs(this.butter - other.butter) < 0.001
        && Math.abs(this.milk - other.milk) < 0.001;
  }
}
//compares two doubles to determine if they are the same
class Utils {
  public double compareWeights(double x, double y, String s) {
    if (Math.abs(x - y) <= 0.001) {
      return x;
    }
    else {
      throw new IllegalArgumentException(s);
    }
  }
}
//examples
class ExamplesCakes {

  //examples that use the first constructor
  CakeRecipe cake1 = new CakeRecipe(12.0, 12.0, 8.0, 8.0, 4.0);
  CakeRecipe cake2 = new CakeRecipe(4.0, 4.0, 1.0, 1.0, 3.0);
  CakeRecipe cake3 = new CakeRecipe(10.0, 10.0, 5.0, 5.0, 5.0);

  //examples that use the second constructor
  CakeRecipe cake4 = new CakeRecipe(7.0, 3.0, 4.0);
  CakeRecipe cake5 = new CakeRecipe(4.0, 1.0, 3.0);
  CakeRecipe cake6 = new CakeRecipe(13.0, 8.0, 5.0);

  //examples that use the third constructor
  CakeRecipe cake7 = new CakeRecipe(34.0, 10.0, 24.0, false);
  CakeRecipe cake8 = new CakeRecipe(8, 4, 3.375, true);

  //to test the sameRecipe method
  boolean testSameRecipe(Tester t) {
    return t.checkExpect(this.cake1.sameRecipe(this.cake1), true)
        && t.checkExpect(this.cake2.sameRecipe(this.cake1), false)
        && t.checkExpect(this.cake2.sameRecipe(this.cake2), true)
        && t.checkExpect(this.cake3.sameRecipe(this.cake2), false)
        && t.checkExpect(this.cake3.sameRecipe(this.cake5), false)
        && t.checkExpect(this.cake3.sameRecipe(this.cake6), false)
        && t.checkExpect(this.cake5.sameRecipe(this.cake2), true);
  }

  //to test the exceptions within the constructors
  boolean testExceptions(Tester t) {
    return t.checkConstructorException(
        new IllegalArgumentException("Invalid weight: weights of flour and sugar are inequal"),
        "CakeRecipe", 9.0, 4.0, 1.0, 1.0, 8.0)
        && t.checkConstructorException(
            new IllegalArgumentException("Invalid weight: milk weight doesn't equal the weight "
                + "of the flour minus the eggs"), "CakeRecipe", 7.0, 7.0, 6.0, 6.0, 3.0)
        && t.checkConstructorException(
            new IllegalArgumentException("Invalid weight: weights of eggs and butter are unequal"), 
            "CakeRecipe", 7.0, 7.0, 4.0, 6.0, 3.0);
  }
  
  //test the constructors 
  boolean testConstructor(Tester t) {
    return t.checkExpect(this.cake1.butter, 8.0)
        && t.checkExpect(this.cake4.flour, 7.0)
        && t.checkExpect(this.cake8.eggs, 7.0)
        && t.checkExpect(this.cake7.flour, 34.0);
  }

}