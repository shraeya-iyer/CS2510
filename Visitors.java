import tester.Tester;
import java.util.function.BiFunction;
import java.util.function.Function;


//represents an arithmetic expression
interface IArith {

  //To return the result of applying the given visitor to this Arith
  <R> R accept(IArithVisitor<R> visitor);
}

//function object class for constants
class Const implements IArith {
  double num;

  Const(double num) {
    this.num = num;
  }


  //To return the result of applying the given visitor to this Const
  public <R> R accept(IArithVisitor<R> visitor) { 
    return visitor.visitConst(this); 
  }

}

//function object class for unary formula
class UnaryFormula implements IArith {
  Function<Double, Double> func;
  String name;
  IArith child;

  UnaryFormula(Function<Double, Double> func, String name, IArith child) {
    this.func = func;
    this.name = name;
    this.child = child;
  }  

  //To return the result of applying the given visitor to this UnaryFormula
  public <R> R accept(IArithVisitor<R> visitor) { 
    return visitor.visitUnaryFormula(this); 
  }
}

//function object class for binary formula
class BinaryFormula implements IArith {
  BiFunction<Double, Double, Double> func;
  String name;
  IArith left;
  IArith right;

  BinaryFormula(BiFunction<Double, Double, Double> func, String name, IArith left, IArith right) {
    this.func = func;
    this.name = name;
    this.left = left;
    this.right = right;
  }

  //To return the result of applying the given visitor to this BinaryFormula
  public <R> R accept(IArithVisitor<R> visitor) { 
    return visitor.visitBinaryFormula(this); 
  }
}

//represents a visitor that visits IArith
interface IArithVisitor<R> {

  //visits const
  R visitConst(Const c);

  //visits UnaryFormula
  R visitUnaryFormula(UnaryFormula unaryF);

  //visis BinaryFormula
  R visitBinaryFormula(BinaryFormula binaryF);

}

//visits the IArith and evaluates to a double
class EvalVisitor implements IArithVisitor<Double> {

  //produces the number inside the constant when constant is inputted
  public Double visitConst(Const c) {
    return c.num;
  }

  //returns all the doubles in the unary function
  public Double visitUnaryFormula(UnaryFormula uF) {
    return uF.func.apply(uF.child.accept(this));
  }

  //returns all the doubles in the binary function
  public Double visitBinaryFormula(BinaryFormula bF) {
    return bF.func.apply(bF.left.accept(this), bF.right.accept(this));    
  }

  //apply method for EvalVisitor
  public Double apply(IArith t) {
    return t.accept(this);
  }

}

//returns arith with all the constants doubles
class DoublerVisitor implements IArithVisitor<IArith> {

  //returns arith with the constant doubled
  public IArith visitConst(Const c) {
    return new Const(c.num * 2);
  }

  //returns arith with all the constants doubled in the unary function
  public IArith visitUnaryFormula(UnaryFormula uF) {
    return new UnaryFormula(uF.func, uF.name, uF.child.accept(this));
  }

  //returns arith with all the constants doubled in the binary function
  public IArith visitBinaryFormula(BinaryFormula bF) {
    return new BinaryFormula(bF.func, bF.name, bF.left.accept(this), bF.right.accept(this));
  } 

  //apply method for DoublerVisitor
  public IArith apply(IArith t) {
    return t.accept(this);
  }
}


//returns true of the arithmetic expression has a constant smaller than 10
class AllSmallVisitor implements IArithVisitor<Boolean> {

  //returns true if the given constant is smaller than 10
  public Boolean visitConst(Const c) {
    return (Math.abs(c.num) < 10);
  }

  //returns true if the given constant is smaller than 10 in the unary function
  public Boolean visitUnaryFormula(UnaryFormula uF) {
    return uF.child.accept(this);
  }

  //returns true if the given constant is smaller than 10 in the binary function
  public Boolean visitBinaryFormula(BinaryFormula bF) {
    return bF.right.accept(this) 
        && bF.left.accept(this);
  }   

  //apply method for AllSmallVisitor
  public Boolean apply(IArith t) {
    return t.accept(this);
  }
}


//returns true if arith is named "div" and the right child is > 0.0001
class NoDivBy0 implements IArithVisitor<Boolean> {

  //always returns true as a constant can't be names "div"
  public Boolean visitConst(Const c) {
    return true;
  }

  //explores the child in the tree to verify if it's dividing by zero
  public Boolean visitUnaryFormula(UnaryFormula uF) {
    return uF.child.accept(this);
  }

  //returns true if arith is named "div" and the right child is > 0.0001
  public Boolean visitBinaryFormula(BinaryFormula bF) {
    return bF.name.equals("div") 
        && Math.abs(bF.right.accept(new EvalVisitor())) > 0.0001;
  } 

  //apply method for NoDivBy0
  public Boolean apply(IArith t) {
    return t.accept(this);
  }
}

//Function object class to square the given number
class Sqr implements Function<Double, Double> {

  //returns the given number squared
  public Double apply(Double t) { 
    return t * t;
  }  
}

//Function object class to negate the given number
class Neg implements Function<Double, Double> {

  //returns the negation of the given number
  public Double apply(Double t) { 
    return 0.0 - t;
  }
}

//Function object class to add the given numbers
class Plus implements BiFunction<Double, Double, Double> {

  //returns the addition of the two numbers
  public Double apply(Double x, Double y) {  
    return x + y;
  } 
}

//Function object class to subtract the given numbers
class Minus implements BiFunction<Double, Double, Double> {

  //returns the subtraction of the two numbers
  public Double apply(Double x, Double y) {  
    return x - y;
  } 
}

//Function object class to multiply the given numbers
class Mul implements BiFunction<Double, Double, Double> {

  //returns the multiplication of the two numbers
  public Double apply(Double x, Double y) {  
    return x * y;
  } 
}

//Function object class to divide the given numbers
class Div implements BiFunction<Double, Double, Double> {

  //returns the division of the two numbers
  public Double apply(Double x, Double y) {  
    return Math.abs(x / y);
  } 

}

//class to hold examples
class ExamplesVisitors {
  ExamplesVisitors(){
  }  

  BiFunction<Double, Double, Double> plus  = new Plus();
  BiFunction<Double, Double, Double> minus = new Minus();
  BiFunction<Double, Double, Double> mul = new Mul();
  BiFunction<Double, Double, Double> div = new Div();

  Function<Double, Double> sqr = new Sqr();
  Function<Double, Double> neg = new Neg();

  Const c1 = new Const(0.0);
  Const c2 = new Const(1.0);
  Const c3 = new Const(3.0);
  Const c4 = new Const(11.0);
  Const c5 = new Const(23.0);
  Const c6 = new Const(6.0);
  Const c5A = new Const(-1.0);
  Const c6A = new Const(-3.0);

  BinaryFormula bF1 = new BinaryFormula(plus, "bF1", c1, c2);
  BinaryFormula bF2 = new BinaryFormula(mul, "bF1", c2, c3);
  BinaryFormula bF3 = new BinaryFormula(div, "div", c2, c1);
  BinaryFormula bF4 = new BinaryFormula(div, "div", c2, c3);
  BinaryFormula bF5 = new BinaryFormula(div, "div", c2, c4);
  BinaryFormula bF6 = new BinaryFormula(div, "div", c5, c4);
  BinaryFormula bF7 = new BinaryFormula(div, "div", c6, c3);

  UnaryFormula uF1 = new UnaryFormula(sqr, "uF1", c1);
  UnaryFormula uF2 = new UnaryFormula(neg, "uF2", c3);
  UnaryFormula uF3 = new UnaryFormula(sqr, "uF3", c3);
  UnaryFormula uF4 = new UnaryFormula(neg, "uF1", c1);
  UnaryFormula uF5 = new UnaryFormula(neg, "uF1", c4);
  UnaryFormula uF6 = new UnaryFormula(neg, "uF1", c5);
  UnaryFormula uF7 = new UnaryFormula(neg, "uF1", bF1);
  UnaryFormula uF8 = new UnaryFormula(neg, "uF1", bF3);
  UnaryFormula uF9 = new UnaryFormula(neg, "uF1", bF6);
  UnaryFormula uF10 = new UnaryFormula(neg, "uF1", bF7);

  BinaryFormula bF8 = new BinaryFormula(div, "div", uF8, c3);
  BinaryFormula bF9 = new BinaryFormula(div, "div", uF10, c3);

  boolean testPlus(Tester t) {
    return t.checkExpect(plus.apply(6.0, 3.0), 9.0)
        && t.checkExpect(plus.apply(7.0, 3.0), 10.0)
        && t.checkExpect(plus.apply(8.0, 4.0), 12.0)
        && t.checkExpect(plus.apply(12.0, 17.0), 29.0)
        && t.checkExpect(plus.apply(0.0, -3.0), -3.0);
  }

  boolean testMul(Tester t) {
    return t.checkExpect(mul.apply(6.0, 3.0), 18.0)
        && t.checkExpect(mul.apply(7.0, 3.0), 21.0)
        && t.checkExpect(mul.apply(8.0, 4.0), 32.0)
        && t.checkExpect(mul.apply(12.0, 1.0), 12.0)
        && t.checkExpect(mul.apply(0.0, -3.0), 0.0);
  }

  boolean testMinus(Tester t) {
    return t.checkExpect(minus.apply(6.0, 3.0), 3.0)
        && t.checkExpect(minus.apply(7.0, 3.0), 4.0)
        && t.checkExpect(minus.apply(8.0, 4.0), 4.0)
        && t.checkExpect(minus.apply(12.0, 17.0), -5.0)
        && t.checkExpect(minus.apply(0.0, -3.0), 3.0);
  }

  boolean testDiv(Tester t) {
    return t.checkExpect(div.apply(6.0, 3.0), 2.0)
        && t.checkExpect(div.apply(36.0, 12.0), 3.0)
        && t.checkExpect(div.apply(8.0, 4.0), 2.0)
        && t.checkExpect(div.apply(-12.0, 3.0), 4.0)
        && t.checkExpect(div.apply(0.0, -3.0), 0.0);
  }

  boolean testNeg(Tester t) {
    return t.checkExpect(neg.apply(6.0), -6.0)
        && t.checkExpect(neg.apply(36.0), -36.0)
        && t.checkExpect(neg.apply(8.0), -8.0)
        && t.checkExpect(neg.apply(-12.0), 12.0)
        && t.checkExpect(neg.apply(0.0), 0.0);
  }

  boolean testSqr(Tester t) {
    return t.checkExpect(sqr.apply(6.0), 36.0)
        && t.checkExpect(sqr.apply(2.0), 4.0)
        && t.checkExpect(sqr.apply(1.0), 1.0)
        && t.checkExpect(sqr.apply(-12.0), 144.0)
        && t.checkExpect(sqr.apply(0.0), 0.0);
  }

  boolean testConst(Tester t) {
    return
        t.checkExpect(this.c1.accept(new EvalVisitor()), 0.0)
        && t.checkExpect(this.c2.accept(new EvalVisitor()), 1.0);
  }

  boolean testUnaryFormula(Tester t) {
    return
        t.checkExpect(this.uF1.accept(new EvalVisitor()), 0.0)
        && t.checkExpect(this.uF2.accept(new EvalVisitor()), -3.0);
  }

  // tests EvalVisitor function object class
  boolean testEvalVisitor(Tester t) {
    return
        t.checkExpect(this.c1.accept(new EvalVisitor()), 0.0)
        && t.checkExpect(this.c2.accept(new EvalVisitor()), 1.0)
        && t.checkExpect(this.uF10.accept(new EvalVisitor()), -2.0)
        && t.checkExpect(this.uF2.accept(new EvalVisitor()), -3.0)
        && t.checkExpect(this.bF1.accept(new EvalVisitor()), 1.0)
        && t.checkExpect(this.bF7.accept(new EvalVisitor()), 2.0);
  }  

  // tests DoublerVisitor function object class
  boolean testDoublerVisitor(Tester t) {
    return
        t.checkExpect(this.c1.accept(new DoublerVisitor()), c1)
        && t.checkExpect(this.c4.accept(new DoublerVisitor()), new Const(22.0))
        && t.checkExpect(this.uF2.accept(new DoublerVisitor()), new UnaryFormula(
            neg, "uF2", new Const(6.0)))
        && t.checkExpect(this.uF1.accept(new DoublerVisitor()), new UnaryFormula(
            sqr, "uF1", new Const(0.0)))
        && t.checkExpect(this.bF1.accept(new DoublerVisitor()), new BinaryFormula(
            plus, "bF1", new Const(0.0), new Const(2.0)))
        && t.checkExpect(this.bF5.accept(new DoublerVisitor()), new BinaryFormula(
            div, "div", new Const(2.0), new Const(22.0)));
  }

  // tests AllSmallVisitor Visitor function object class
  boolean testAllSmallVisitor(Tester t) {
    return
        t.checkExpect(this.c1.accept(new AllSmallVisitor()), true)
        && t.checkExpect(this.c3.accept(new AllSmallVisitor()), true)
        && t.checkExpect(this.c5.accept(new AllSmallVisitor()), false)
        && t.checkExpect(this.c4.accept(new AllSmallVisitor()), false)
        && t.checkExpect(this.uF1.accept(new AllSmallVisitor()), true)
        && t.checkExpect(this.uF2.accept(new AllSmallVisitor()), true)
        && t.checkExpect(this.uF5.accept(new AllSmallVisitor()), false)
        && t.checkExpect(this.uF6.accept(new AllSmallVisitor()), false)
        && t.checkExpect(this.bF1.accept(new AllSmallVisitor()), true)
        && t.checkExpect(this.bF2.accept(new AllSmallVisitor()), true)
        && t.checkExpect(this.bF5.accept(new AllSmallVisitor()), false)
        && t.checkExpect(this.bF6.accept(new AllSmallVisitor()), false);
  }

  // tests NoDivBy0 Visitor function object class
  boolean testNoDivBy0(Tester t) {
    return
        t.checkExpect(this.c1.accept(new NoDivBy0()), true)
        && t.checkExpect(this.c6.accept(new NoDivBy0()), true)
        && t.checkExpect(this.uF1.accept(new NoDivBy0()), true)
        && t.checkExpect(this.uF9.accept(new NoDivBy0()), true)
        && t.checkExpect(this.uF8.accept(new NoDivBy0()), false)
        && t.checkExpect(this.uF7.accept(new NoDivBy0()), false)
        && t.checkExpect(this.bF3.accept(new NoDivBy0()), false)
        && t.checkExpect(this.bF1.accept(new NoDivBy0()), false)
        && t.checkExpect(this.bF4.accept(new NoDivBy0()), true)
        && t.checkExpect(this.bF6.accept(new NoDivBy0()), true);
  }
}


