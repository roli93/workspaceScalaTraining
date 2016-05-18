package funsets

import org.scalatest.FunSuite


import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

/**
 * This class is a test suite for the methods in object FunSets. To run
 * the test suite, you can either:
 *  - run the "test" command in the SBT console
 *  - right-click the file in eclipse and chose "Run As" - "JUnit Test"
 */
@RunWith(classOf[JUnitRunner])
class FunSetSuite extends FunSuite {

  /**
   * Link to the scaladoc - very clear and detailed tutorial of FunSuite
   *
   * http://doc.scalatest.org/1.9.1/index.html#org.scalatest.FunSuite
   *
   * Operators
   *  - test
   *  - ignore
   *  - pending
   */

  /**
   * Tests are written using the "test" operator and the "assert" method.
   */
  // test("string take") {
  //   val message = "hello, world"
  //   assert(message.take(5) == "hello")
  // }

  /**
   * For ScalaTest tests, there exists a special equality operator "===" that
   * can be used inside "assert". If the assertion fails, the two values will
   * be printed in the error message. Otherwise, when using "==", the test
   * error message will only say "assertion failed", without showing the values.
   *
   * Try it out! Change the values so that the assertion fails, and look at the
   * error message.
   */
  // test("adding ints") {
  //   assert(1 + 2 === 3)
  // }


  import FunSets._

  test("contains is implemented") {
    assert(contains(x => true, 100))
  }

  /**
   * When writing tests, one would often like to re-use certain values for multiple
   * tests. For instance, we would like to create an Int-set and have multiple test
   * about it.
   *
   * Instead of copy-pasting the code for creating the set into every test, we can
   * store it in the test class using a val:
   *
   *   val s1 = singletonSet(1)
   *
   * However, what happens if the method "singletonSet" has a bug and crashes? Then
   * the test methods are not even executed, because creating an instance of the
   * test class fails!
   *
   * Therefore, we put the shared values into a separate trait (traits are like
   * abstract classes), and create an instance inside each test method.
   *
   */

  trait TestSets {
    val s1 = singletonSet(1)
    val s2 = singletonSet(2)
    val s3 = singletonSet(3)
    val negatives:Set = _ < 0
  }

  /**
   * This test is currently disabled (by using "ignore") because the method
   * "singletonSet" is not yet implemented and the test would fail.
   *
   * Once you finish your implementation of "singletonSet", exchange the
   * function "ignore" by "test".
   */
  test("singletonSet(1) contains 1") {

    /**
     * We create a new instance of the "TestSets" trait, this gives us access
     * to the values "s1" to "s3".
     */
    new TestSets {
      /**
       * The string argument of "assert" is a message that is printed in case
       * the test fails. This helps identifying which assertion failed.
       */
      assert(contains(s1, 1), "Singleton")
    }
  }

  test("union contains all elements of each set") {
    new TestSets {
      val s = union(s1, s2)
      assert(contains(s, 1), "Union 1")
      assert(contains(s, 2), "Union 2")
      assert(!contains(s, 3), "Union 3")
    }
  }
  
  test("negatives contains -5") {
    new TestSets {
      assert(contains(negatives, -5), "contains -5")
    }
  }
  
  test("negatives filtered by > -5 contain -1 but not -10") {
    new TestSets {
      val first5negatives = filter(negatives, _>(-5))
      assert(contains(first5negatives, -1), "contains -1")
      assert(!contains(first5negatives, -10), "but not -10")
    }
  }
  
  test("negatives minus those smaller than -5 contain -1 but not -10") {
    new TestSets {
      val smallerThanMinus5:Set = _<(-5)
      val first5negatives = diff(negatives,smallerThanMinus5)
      assert(contains(first5negatives, -1), "contains -1")
      assert(!contains(first5negatives, -10), "but not -10")
    }
  }
  
  test("all negatives are smaller than 100"){
    new TestSets {
      assert(forall(negatives, _<100), "All hold the predicate")
    }
  }
  
  test("not all negatives are smaller than -100"){
    new TestSets {
      assert(!forall(negatives, _<(-100)), "Not all hold the predicate")
    }
  }
  
  test("there is a negative greater than -2"){
    new TestSets {
      assert(exists(negatives,_>(-2)), "At least one holds the predicate")
    }
  }

  test("there are no negatives greater than -1"){
    new TestSets {
      assert(!exists(negatives,_>(-1)), "No one holds the predicate")
    }
  }
  
  test("singleton(1) mapped by +1 contains 2"){
    new TestSets {
      val s1plus1 = map(s1,_+1)
      assert(contains(s1plus1,2), "It is contained")
    }
  }
  
    test("singleton(1) mapped by +5 doesnt contain 2"){
    new TestSets {
      val s1plus1 = map(s1,_+5)
      assert(!contains(s1plus1,2), "It is not contained")
    }
  }
    
  test("negatives mapped by *-1 contain 500"){
    new TestSets {
      val positives = map(negatives,_*(-1))
      assert(contains(positives,500), "It is contained")
    }
  }

      
  test("negatives mapped by *-1 don't contain -300"){
    new TestSets {
      val positives = map(negatives,_*(-1))
      assert(!contains(positives,-300), "It is not contained")
    }
  }
  
}
