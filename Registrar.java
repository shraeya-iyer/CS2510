import tester.Tester;
import java.util.function.Predicate;
import java.util.function.BiFunction;

//represents a general list
interface IList<T> {

  //maps the given function onto every member of this list, returning a list of
  // the results
  boolean ormap(Predicate<T> pred);

  //combines the items in this list using the given function
  <R> Integer fold(BiFunction<T, R, Integer> fun, R s, Integer base);
  
}

//represents a course

class Course {
  String name;
  Instructor prof;
  IList<Student> students;

  //constructor 
  Course(String name, Instructor prof) {
    this.name = name;
    this.prof = prof;
    this.students = new MtList<Student>();
    this.prof.addCourse(this);
  }

  //enrolls student in given course
  void enroll(Student s) {
    this.students = new ConsList<Student>(s, students);
  }

  //is given student in this course?
  boolean isStudentIn(Student s) {
    return this.students.ormap(new SameStudent(s));   
  }
}

//represents an instructor
class Instructor {
  String name;
  IList<Course> courses;

  //constructor for prof with an empty list of courses
  Instructor(String name) {
    this.name = name;
    this.courses = new MtList<Course>();
  }

  //add a new course to a professor's course list
  public void addCourse(Course c) {
    this.courses = new ConsList<Course>(c, this.courses);
  }

  //determines whether the given student is in more than one of
  //this instructor' classes
  public boolean dejavu(Student c) {
    return this.courses.fold(new SameCourses(), c, 0) > 1;
  }

}

//represents a student
class Student {
  String name;
  int id;
  IList<Course> courses;

  //constructor
  Student(String name, int id) {
    this.name = name;
    this.id = id;
    this.courses = new MtList<Course>();
  }

  //enrolls student in given course
  void enroll(Course c) {
    this.courses = new ConsList<Course>(c, courses);
    c.enroll(this);
  }

  //determines if this student is the same as the given student
  boolean sameStudent(Student c) {
    return this.id == c.id;
  }

  //returns true if this student and the given student are in any of the 
  //same courses
  boolean classmates(Student c) {
    return this.courses.ormap(new StudentInCourse(c));
  }
}

//represents an empty list
class MtList<T> implements IList<T> {

  //maps the given function onto every member of this list, returning a list of
  //the results
  public boolean ormap(Predicate<T> pred) {
    return false;
  }

  //combines the items in this list using the given function
  public <R> Integer fold(BiFunction<T, R, Integer>  fun, R s, Integer base) {
    return base;
  }
}

//represents a non-empty list
class ConsList<T> implements IList<T> {
  T first;
  IList<T> rest;

  //constructor
  ConsList(T first, IList<T> rest) {
    this.first = first;
    this.rest = rest;
  }

  //maps the given function onto every member of this list, returning a list of
  //the results
  public boolean ormap(Predicate<T> pred) {
    return pred.test(this.first) ||
        this.rest.ormap(pred);
  }

  //combines the items in this list using the given function
  public <R> Integer fold(BiFunction<T, R, Integer> fun, R s, Integer base) {
    return fun.apply(this.first, s) + this.rest.fold(fun, s, base);
  }
}

//function object to that returns true if this student has the same
//id number as the given student
class SameStudent implements Predicate<Student> {
  Student stu;

  //constructor
  SameStudent(Student stu) {
    this.stu  = stu;
  }

  // determines if student IDs are the same
  public boolean test(Student s) {
    return this.stu.sameStudent(s);
  }
}

//function object that returns true if this student is in the 
//given course
class StudentInCourse implements Predicate<Course> {
  Student stu;

  StudentInCourse(Student stu) {
    this.stu  = stu;
  }

  //returns true if this student is in the given course's list of students
  public boolean test(Course c) {
    return c.isStudentIn(this.stu);
  }
}

//function object that returns 1 if the given student is in the
//given course
class SameCourses implements BiFunction<Course, Student, Integer> {

  //returns 1 if the given student is in the
  //given course, returns 0 if not.  
  public Integer apply(Course t, Student u) {
    if (t.isStudentIn(u)) {
      return 1;
    }
    else {
      return 0;
    }
  }
}



class ExamplesRegistrar {

  Student shraeya;
  Student lily;
  Student jaena;
  Student bob;
  Student charlie;
  Student bill;


  IList<Student> emptyStudent; 
  IList<Student> students1;
  IList<Student> students2;
  IList<Student> students3;

  Course fundies2;
  Course discrete;
  Course thv;
  Course cyber;

  IList<Course> emptyCourse;
  IList<Course> courses1;
  IList<Course> courses2;
  IList<Course> courses3;
  IList<Course> courses4;
  IList<Course> courses5;
  IList<Course> courses6;


  Instructor prof1;
  Instructor prof2;
  Instructor prof3;


  //examples 
  void init() {
    this.shraeya = new Student("Shraeya", 234);
    this.lily = new Student("Lily", 234);
    this.jaena = new Student("Jaena", 145);
    this.bob = new Student("Bob", 444);
    this.charlie = new Student("Charlie", 334);
    this.bill = new Student("Bill", 231);


    this.emptyStudent = new MtList<Student>();
    this.students1 = new ConsList<Student>(this.shraeya, this.emptyStudent);
    this.students2 = new ConsList<Student>(this.lily, this.emptyStudent);
    this.students3 = new ConsList<Student>(this.jaena, this.students2);

    this.prof1 = new Instructor("Leena Razzaq");
    this.prof2 = new Instructor("Meica Magnani");
    this.prof3 = new Instructor("Justin Wang");

    this.fundies2 = new Course("Fundies 2", this.prof1);
    this.discrete = new Course("Discrete", this.prof1);
    this.thv = new Course("Tech and Human Values", this.prof2);
    this.cyber = new Course("Intro to Cybersecurity", this.prof3);

    this.emptyCourse = new MtList<Course>();
    this.courses1 = new ConsList<Course>(this.fundies2, this.emptyCourse);
    this.courses2 = new ConsList<Course>(this.discrete, this.courses1);
    this.courses3 = new ConsList<Course>(this.thv, this.emptyCourse);
    this.courses4 = new ConsList<Course>(this.cyber, this.emptyCourse);
    this.courses5 = new ConsList<Course>(this.fundies2, this.courses3);
    this.courses6 = new ConsList<Course>(this.discrete, this.emptyCourse);

    shraeya.enroll(this.fundies2);
    shraeya.enroll(this.cyber);
    lily.enroll(this.discrete);
    lily.enroll(this.thv);
    jaena.enroll(this.cyber);
    jaena.enroll(this.discrete);

  }

  //
  void testEnroll(Tester t) {
    this.init();

    bob.enroll(this.fundies2);
    t.checkExpect(this.bob.courses, courses1);

    charlie.enroll(this.discrete);
    t.checkExpect(this.charlie.courses, courses6);

    bill.enroll(this.discrete);
    t.checkExpect(this.bill.courses, courses2);
  }

  boolean testClassmates(Tester t) {
    this.init();

    bob.enroll(this.fundies2);
    charlie.enroll(this.fundies2);
    bill.enroll(this.thv);

    return 
        t.checkExpect(this.bob.classmates(this.charlie), true)
        && t.checkExpect(this.charlie.classmates(this.bill), false);        
  }

  boolean testIsStudentIn(Tester t) {
    this.init();
    bob.enroll(this.fundies2);
    bill.enroll(this.discrete);
    charlie.enroll(this.fundies2);

    return 
        t.checkExpect(this.fundies2.isStudentIn(bob), true)
        &&
        t.checkExpect(this.fundies2.isStudentIn(charlie), true)
        &&
        t.checkExpect(this.discrete.isStudentIn(bill), true)
        &&
        t.checkExpect(this.cyber.isStudentIn(bob), false)
        &&
        t.checkExpect(this.thv.isStudentIn(bill), false)
        &&
        t.checkExpect(this.fundies2.isStudentIn(bill), false);
  }

  boolean testSameStudent(Tester t) {
    this.init();
    return
        t.checkExpect(this.shraeya.sameStudent(shraeya), true)
        &&
        t.checkExpect(this.shraeya.sameStudent(jaena), false);
  }
  
  //testing the function object called SameCourses
  boolean testSameCourses(Tester t) {
    this.init();
    return 
        t.checkExpect(new SameCourses().apply(fundies2, shraeya), 1)
        &&
        t.checkExpect(new SameCourses().apply(fundies2, jaena), 0)
        &&
        t.checkExpect(new SameCourses().apply(discrete, shraeya), 1)
        &&
        t.checkExpect(new SameCourses().apply(cyber, shraeya), 1)
        &&
        t.checkExpect(new SameCourses().apply(thv, lily), 1);
  }

  //tests the fold method
  boolean testFold(Tester t) {
    this.init();
    return
        t.checkExpect(this.emptyCourse.fold(new SameCourses(), this.shraeya, 0), 0)
        &&
        t.checkExpect(this.courses1.fold(new SameCourses(), this.shraeya, 0), 1)
        &&
        t.checkExpect(this.courses2.fold(new SameCourses(), this.lily, 0), 2)
        &&
        t.checkExpect(this.courses2.fold(new SameCourses(), this.shraeya, 0), 2)
        &&
        t.checkExpect(this.courses5.fold(new SameCourses(), this.jaena, 0), 0)
        &&
        t.checkExpect(this.courses5.fold(new SameCourses(), this.lily, 0), 2)
        &&
        t.checkExpect(this.emptyCourse.fold(new SameCourses(), this.shraeya, 0), 0);
  }

  boolean testDejavu(Tester t) {
    this.init();
    return 
        t.checkExpect(this.prof1.dejavu(this.shraeya), true)
        &&
        t.checkExpect(this.prof2.dejavu(this.jaena), false)
        &&
        t.checkExpect(this.prof3.dejavu(this.jaena), true);
  }

  //cannot test add courses 

  boolean testOrMap(Tester t) {
    this.init();
    return
        t.checkExpect(this.emptyCourse.ormap(new StudentInCourse(this.shraeya)), false)
        && 
        t.checkExpect(this.courses1.ormap(new StudentInCourse(this.shraeya)), true)
        && 
        t.checkExpect(this.courses2.ormap(new StudentInCourse(this.lily)), true)
        && 
        t.checkExpect(this.courses2.ormap(new StudentInCourse(this.shraeya)), false);
  }
}
