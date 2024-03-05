import tester.Tester;

//represents a function
interface IFunc<Arg, Ret> {
  Ret apply(Arg t);
}

//function object class for square 
class SquareNum implements IFunc<Double, Double> {
  
  //returns the squared value of the given number
  public Double apply(Double t) {
    return t * t;
  }
}

//function object class for sine 
class SineNum implements IFunc<Double, Double> {
  
  //returns the squared value of the given number
  public Double apply(Double t) {
    return Math.sin(t);
  }
}

//function object class for identity 
class Identity<T> implements IFunc<T, T> {
  
  //returns the given number
  public T apply(T t) {
    return t;
  }
}

//function object class for plusN method
class PlusN implements IFunc<Double, Double> {
  Double n;

  PlusN(Double n) {
    this.n = n;
  }

  //returns the given number plus field n
  public Double apply(Double t) {
    return t + n;
  }
}

//function object class for function composition 
class FunctionComposition<Arg, Mid, Ret> implements IFunc<Arg, Ret> {

  private final IFunc<Arg, Mid> func1;
  private final IFunc<Mid, Ret> func2;

  FunctionComposition(IFunc<Arg, Mid> func1, IFunc<Mid, Ret> func2) {
    this.func1 = func1;
    this.func2 = func2;

  }
  
  //returns the numbner that's outputted by nesting the 
  //first function into the second
  public Ret apply(Arg t) {
    Mid that = this.func1.apply(t);
    return this.func2.apply(that);
  }
}

//represents a BiFunction
interface IFunc2<Arg1, Arg2, Ret> {

  Ret apply(Arg1 arg1, Arg2 arg2);
}

/*
 * This means the first input represents an f(x) function that takes in an x and outputs a
 * y. The second input takes in a y and outputs a z. The returning function takes in an x
 * and outputs a z, which essentially applies the second function to the first. This differs 
 * from FunctionCommposition as FunctionComposition requires you to an x initially which 
 * would output a y to produce a function that requires an additional input of y to get to 
 * z. On the other hand, ComposeFunctions only takes in a x which produces a y, which is 
 * then taken in by the returning function which takes in a x to produce a z. The main 
 * difference is that  FunctionnComposition ultimately return a the number produced by
 * nesting the first function inside the second as it's input. However, the Composition 
 * Function returns a function instead of a number, the function being the first function 
 * nested inside the second as its input. 
 */

//function object class for ComposeFunction
class ComposeFunctions<Arg1, Arg2, Ret> 
    implements IFunc2<IFunc<Arg1, Arg2>, IFunc<Arg2, Ret>, IFunc<Arg1, Ret>> {

  //returns the function thats outputted by nesting the first into the second
  public IFunc<Arg1, Ret> apply(IFunc<Arg1, Arg2> func1, IFunc<Arg2, Ret> func2) {
    return new FunctionComposition<Arg1, Arg2, Ret>(func1, func2);
  }
}

//represents a general list
interface IList<T> {
  <U> U foldl(IFunc2<T, U, U> converter, U initial);
}

//represents an empty list
class MtList<T> implements IList<T> {
  //returns the empty list 
  public <U> U foldl(IFunc2<T, U, U> converter, U initial) {
    return initial;
  }
}

//represents a list
class ConsList<T> implements IList<T> {
  T first;
  IList<T> rest;

  ConsList(T first, IList<T> rest) {
    this.first = first;
    this.rest = rest;
  }

  //applies given function to the list starting from the left
  public <U> U foldl(IFunc2<T, U, U> converter, U initial) {
    return this.rest.foldl(converter, converter.apply(this.first, initial)); 
  }
}

//class to hold examples
class ExamplesListFunctions {

  //examples

  IFunc<Double, Double> identity = new Identity<Double>();
  IFunc<Double, Double> square = new SquareNum();
  IFunc<Double, Double> sine = new SineNum();
  //IFunc<int, int> id = new Identity<int>();
  IFunc<Double, Double> addn = new PlusN(1.0);
  IFunc<Double, Double> funcComp = 
      new FunctionComposition<Double, Double, Double>(identity, square);
  IFunc<Double, Double> funcComp1 = 
      new FunctionComposition<Double, Double, Double>(sine, addn);
  IFunc<Double, Double> funcComp2 =
      new FunctionComposition<Double, Double, Double>(funcComp1, square);
  IFunc<Double, Double> funcComp3 = 
      new FunctionComposition<Double, Double, Double>(funcComp, square);
  IFunc<Double, Double> funcComp4 = 
      new FunctionComposition<Double, Double, Double>(funcComp2, funcComp3);
  IFunc2<IFunc<Double, Double>, IFunc<Double, Double>, IFunc<Double, Double>> compFunc = 
      new ComposeFunctions<Double, Double, Double>();
  IList<IFunc<Double, Double>> empty =
      new MtList<IFunc<Double, Double>>();
  IList<IFunc<Double, Double>> list1 = 
      new ConsList<IFunc<Double, Double>>(this.square, empty);
  IList<IFunc<Double, Double>> list2 = 
      new ConsList<IFunc<Double, Double>>(this.addn, this.list1);
  IList<IFunc<Double, Double>> flist = 
      new ConsList<IFunc<Double, Double>>(this.sine, this.list2);

  //tests apply for square num
  boolean testSquareNum(Tester t) {
    return
        t.checkExpect(this.square.apply(6.5), 42.25)
        && t.checkExpect(this.square.apply(0.0), 0.0); 
  }

  //tests apply for sine num
  boolean testSineNum(Tester t) {
    return 
        t.checkExpect(this.sine.apply(Math.PI / 2), 1.0)
        && t.checkExpect(this.sine.apply(0.0), 0.0);
  }

  //tests apply for plus N
  boolean testPlusN(Tester t) { 
    return
        t.checkExpect(this.addn.apply(0.0), 1.0)
        && t.checkExpect(this.addn.apply(1.5), 2.5);
  }

  //tests apply for identity
  boolean testIdentity(Tester t) {  
    return
        t.checkExpect(this.identity.apply(2.0), 2.0)
        && t.checkExpect(this.identity.apply(3.0), 3.0);
  }

  //tests apply for function composition
  boolean testFunctionComposition(Tester t) {
    return
        t.checkExpect(this.funcComp.apply(2.0), 4.0)
        && t.checkExpect(this.funcComp1.apply(0.0), 1.0)
        && t.checkExpect(this.funcComp2.apply(0.0), 1.0);
  }

  //tests apply for compose function 
  boolean testComposeFunction(Tester t) {
    return
        t.checkExpect(this.compFunc.apply(funcComp1, square), funcComp2)
        && t.checkExpect(this.compFunc.apply(funcComp, square), funcComp3)
        && t.checkExpect(this.compFunc.apply(funcComp2, funcComp3), funcComp4);

  }

  boolean testfoldl(Tester t) {
    return 
        t.checkExpect(this.flist.foldl(this.compFunc, this.identity), this.funcComp2)
        //Since we haven't implemented any "same" or .equals method to compare the two
        //methods in the check expect, the check expect cannot tell you that they are the same,
        //so they will directly compare the input and the output word for word, which will
        //yield a false because the input and output are not directly the same
        && t.checkExpect(this.empty.foldl(this.compFunc, this.identity), this.identity)
        && t.checkExpect(this.empty.foldl(this.compFunc, this.addn), this.addn);

  }
}