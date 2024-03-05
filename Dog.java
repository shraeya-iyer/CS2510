//to represent a dog
class Dog {
  String name;
  String breed;
  int yob;
  String state;
  boolean hypoallergenic;
  
  Dog(String name, String breed, int yob, String state, boolean hypoallergenic) {
    this.name = name;
    this.breed = breed;
    this.yob = yob;
    this.state = state;
    this.hypoallergenic = hypoallergenic;
  }
}

//to represent examples of a dog
class ExamplesDog {
  Dog huffle = new Dog("Hufflepuff", "Wheaten Terrier", 2012, "TX", true);
  Dog pearl = new Dog("Pearl", "Labrador Retriever", 2016, "MA", false);
  Dog rufus = new Dog("Roof", "Staffordshire Bull Terrior", 2020, "WA", false);
} 