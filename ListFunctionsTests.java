import tester.Tester;
//class to hold examples
class ExamplesListFunctions1 {

  //examples

  IFunc<Double, Double> identity = new Identity<Double>();
  IFunc<Double, Double> square = new SquareNum();
  IFunc<Double, Double> sine = new SineNum();
  //IFunc<int, int> id = new Identity<int>();
  IFunc<Double, Double> addn = new PlusN(1.0);
  IFunc<Double, Double> funcComp = new FunctionComposition<Double, Double, Double>(identity, square);
  IFunc<Double, Double> funcComp1 = new FunctionComposition<Double, Double, Double>(sine, addn);
  IFunc<Double, Double> funcComp2 = new FunctionComposition<Double, Double, Double>(funcComp1, square);
  IFunc<Double, Double> funcComp3 = new FunctionComposition<Double, Double, Double>(funcComp, square);
  IFunc<Double, Double> funcComp4 = new FunctionComposition<Double, Double, Double>(funcComp2, funcComp3);
  IFunc2<IFunc<Double, Double>, IFunc<Double, Double>, IFunc<Double, Double>> compFunc = 
      new ComposeFunctions<Double, Double, Double>();
  IList<IFunc<Double, Double>> empty = new MtList<IFunc<Double, Double>>();
  IList<IFunc<Double, Double>> list1 = new ConsList<IFunc<Double, Double>>(this.square, empty);
  IList<IFunc<Double, Double>> list2 = new ConsList<IFunc<Double, Double>>(this.addn, this.list1);
  IList<IFunc<Double, Double>> flist = new ConsList<IFunc<Double, Double>>(this.sine, this.list2);


  
  
  
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
  
  boolean testFoldL(Tester t) {
    return 
        t.checkExpect(this.flist.foldL(this.compFunc, this.identity), this.funcComp2)
        && t.checkExpect(this.empty.foldL(this.compFunc, this.identity), this.empty);
  }
}