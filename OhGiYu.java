//represents the resources
interface IResource {

}

//represents the actions
interface IAction {

}

//represents a monster
class Monster implements IResource {
  String name;
  int hp;
  int attack;

  Monster(String name, int hp, int attack) {
    this.name = name;
    this.hp = hp;
    this.attack = attack;
  }

}

//represents a fusion
class Fusion implements IResource {
  String name;
  Monster monster1;
  Monster monster2;

  Fusion(String name, Monster monster1, Monster monster2) {
    this.name = name;
    this.monster1 = monster1;
    this.monster2 = monster2;
  }
}

//represents a trap
class Trap implements IResource {
  String description;
  boolean continuous;

  Trap(String description, boolean continuous) {
    this.description = description;
    this.continuous = continuous;
  }

}


//represents an attack
class Attack implements IAction {
  IResource attacker;
  IResource defender;

  Attack(IResource attacker, IResource defender) {
    this.attacker = attacker;
    this.defender = defender;

  }
}

//represents an activate
class Activate implements IAction {
  Trap trap;
  IResource target;

  Activate(Trap trap, IResource target) {
    this.trap = trap;
    this.target = target;
  }

}

//represents examples of the game
class ExamplesGame {
  // represents examples of resources
  Monster kuriboh = new Monster("Kuriboh", 200, 100);
  Monster jinzo = new Monster("Jinzo", 500, 400);
  Fusion kurizo = new Fusion("Kurizo", kuriboh, jinzo);
  Trap trapHole = new Trap("Kills a monster", false);
  Monster alvin = new Monster("Alvin", 210, 310);
  Trap trapDynamite = new Trap("Blows up a Monster", false);

  // represents examples of an action
  IAction attack1 = new Attack(jinzo, kuriboh);
  IAction attack2 = new Attack(alvin, kuriboh);
  IAction activate1 = new Activate(trapDynamite, kurizo);
  IAction activate2 = new Activate(trapHole, jinzo);

}

// i think this was poor data design because the trap did not need 
// to be a part of the IResources. the trap was only used in the examples
// and their purpose was more similar to the IAction interface. If it was 
// housed inside the IAction interface, it would make more sense. 
// Additionally, this was poor data design because multiple parts of the
// assignments only wanted to use the monsters and fusions parts of the 
// IResources interfaces. With the trap added as a part of this interface
// it over complicates things.