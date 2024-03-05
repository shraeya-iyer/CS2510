import tester.Tester; 
import java.util.*;

/**
 * A class that defines a new permutation code, as well as methods for encoding
 * and decoding of the messages that use this code.
 */
class PermutationCode {
  // The original list of characters to be encoded
  ArrayList<Character> alphabet = 
      new ArrayList<Character>(Arrays.asList(
          'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 
          'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 
          't', 'u', 'v', 'w', 'x', 'y', 'z'));

  ArrayList<Character> code = new ArrayList<Character>(26);

  // A random number generator
  Random rand = new Random();

  // Create a new instance of the encoder/decoder with a new permutation code 
  PermutationCode() {
    this.code = this.initEncoder();
  }

  //Create a new instance of the encoder/decoder with a new permutation code 
  PermutationCode(Random rand) {
    this.rand = rand;
    this.code = this.initEncoder();
  }

  // Create a new instance of the encoder/decoder with the given code 
  PermutationCode(ArrayList<Character> code) {
    this.code = code;
  }

  // Initialize the encoding permutation of the characters
  ArrayList<Character> initEncoder() {
    ArrayList<Character> updated = new ArrayList<Character>();
    ArrayList<Character> alphabet2 = new ArrayList<Character>(this.alphabet);

    for (int i = 0; i < this.alphabet.size(); i++) {
      int max = alphabet2.size();    
      int randomIndex = rand.nextInt(max);
      updated.add(alphabet2.get(randomIndex));
      alphabet2.remove(randomIndex);
    }
    return updated;
  }

  //produce an encoded String from the given String
  String encode(String source) {
    return this.codeAbs(source, this.code, this.alphabet);

  }

  //produce a decoded String from the given String
  String decode(String codemsg) {
    return this.codeAbs(codemsg, this.alphabet, this.code);
  }

  //produce an encoded String from the given String
  String codeAbs(String codeMessage, ArrayList<Character> al1, ArrayList<Character> al2) {
    String message = "";
    for (int i = 0; i < codeMessage.length(); i++) {
      message = message + al1.get(al2.indexOf(codeMessage.charAt(i)));
    }
    return message; 
  } 
}

class ExamplesPermutation {

  PermutationCode pc = new PermutationCode();

  PermutationCode pcRand = new PermutationCode(new Random(10));

  ArrayList<Character> al0 = 
      new ArrayList<Character>(Arrays.asList(
          'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 
          'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 
          't', 'u', 'v', 'w', 'x', 'y', 'z'));
  PermutationCode pc0 = new PermutationCode(al0);

  ArrayList<Character> al1 = 
      new ArrayList<Character>(Arrays.asList(
          'y', 'o', 'l', 'v', 'm', 'q', 'g', 'h', 'r', 'z', 
          'k', 'c', 'e', 'n', 'b', 'p', 'f', 'i', 's', 
          'w', 'u', 'd', 't', 'x', 'a', 'j'));
  PermutationCode pc1 = new PermutationCode(al1);

  ArrayList<Character> al2 = 
      new ArrayList<Character>(Arrays.asList(
          'j', 'l', 'm', 't', 'v', 'd', 'e', 'r', 'u', 'k', 
          'b', 'c', 'y', 'x', 'z', 'f', 'o', 'q', 'p', 
          's', 'i', 'h', 'w', 'g', 'n', 'a'));
  
  PermutationCode pc2 = new PermutationCode(al2);

  //tests for initEncoder
  void testInitEncoder(Tester t) {
    t.checkExpect(pc.initEncoder().size(), 26);
    t.checkExpect(pc.initEncoder().containsAll(pc.alphabet), true);
    t.checkExpect(pc.initEncoder().equals(pc0.alphabet), false);
  }

  //tests for encode
  boolean testEncode(Tester t) {
    return
        t.checkExpect(this.pcRand.encode("string"), "yscdtw")
        &&
        t.checkExpect(this.pc1.encode("abc"), "yol")
        &&
        t.checkExpect(this.pc1.encode("ace"), "ylm")
        &&
        t.checkExpect(this.pc2.encode("abc"), "jlm")
        &&
        t.checkExpect(this.pc2.encode("ace"), "jmv")
        &&
        t.checkExpect(this.pc0.encode("abc"), "abc")
        &&
        t.checkExpect(this.pc0.encode("ace"), "ace");   

  }

  //tests for decode
  boolean testDecode(Tester t) {
    return
        t.checkExpect(this.pcRand.decode("string"), "tnhjdo")
        &&
        t.checkExpect(this.pc1.decode("yol"), "abc")
        &&
        t.checkExpect(this.pc1.decode("ylm"), "ace") 
        &&
        t.checkExpect(this.pc2.decode("jlm"), "abc")
        &&
        t.checkExpect(this.pc2.decode("jmv"), "ace") 
        &&
        t.checkExpect(this.pc0.decode("abc"), "abc")
        &&
        t.checkExpect(this.pc0.decode("ace"), "ace");
  }

  //tests for codeAbs
  boolean testCodeAbs(Tester t) {
    return 
        t.checkExpect(this.pcRand.codeAbs("string", pcRand.alphabet, pcRand.code), "tnhjdo")
        &&
        t.checkExpect(this.pcRand.codeAbs("string", pcRand.code, pcRand.alphabet), "yscdtw")
        &&
        t.checkExpect(this.pc1.codeAbs("yol", pc1.alphabet, pc1.code), "abc")
        &&
        t.checkExpect(this.pc1.codeAbs("abc", pc1.code, pc1.alphabet), "yol")
        &&
        t.checkExpect(this.pc2.codeAbs("jlm", pc2.alphabet, pc2.code), "abc")
        &&
        t.checkExpect(this.pc2.codeAbs("abc", pc2.code, pc2.alphabet), "jlm")
        &&
        t.checkExpect(this.pc0.codeAbs("jlm", pc0.alphabet, pc0.code), "jlm")
        &&
        t.checkExpect(this.pc0.codeAbs("abc", pc0.code, pc0.alphabet), "abc");

  } 
}