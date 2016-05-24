package patmat

import org.scalatest.FunSuite

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

import patmat.Huffman._

@RunWith(classOf[JUnitRunner])
class HuffmanSuite extends FunSuite {
	trait TestTrees {
		val t1 = Fork(Leaf('a',2), Leaf('b',3), List('a','b'), 5)
		val t2 = Fork(Fork(Leaf('a',2), Leaf('b',3), List('a','b'), 5), Leaf('d',4), List('a','b','d'), 9)
	}


  test("weight of a larger tree") {
    new TestTrees {
      assert(weight(t1) === 5)
    }
  }


  test("chars of a larger tree") {
    new TestTrees {
      assert(chars(t2) === List('a','b','d'))
    }
  }


  test("string2chars(\"hello, world\")") {
    assert(string2Chars("hello, world") === List('h', 'e', 'l', 'l', 'o', ',', ' ', 'w', 'o', 'r', 'l', 'd'))
  }
  
  test("times of nonempty works OK") {
    new TestTrees {
      assert(
          (times(List('a', 'b', 'a')) === List(('a', 2), ('b', 1)))
          ||
           (times(List('a', 'b', 'a')) === List(('b', 1), ('a', 2)))
          )
    }
  }
  
  test("times of empty works OK") {
    new TestTrees {
      assert(
          times(List()) === List()
          )
    }
  }


  test("makeOrderedLeafList for some frequency table") {
    assert(makeOrderedLeafList(List(('t', 2), ('e', 1), ('x', 3))) === List(Leaf('e',1), Leaf('t',2), Leaf('x',3)))
  }


  test("combine of some leaf list") {
    val leaflist = List(Leaf('e', 1), Leaf('t', 2), Leaf('x', 4))
    assert(combine(leaflist) === List(Fork(Leaf('e',1),Leaf('t',2),List('e', 't'),3), Leaf('x',4)))
  }
  
  test("until singleton combine three leaves") {
    val leaflist = List(Leaf('e', 1), Leaf('t', 2), Leaf('x', 4))
    assert(until(singleton,combine)(leaflist) == List(Fork(Fork(Leaf('e',1),Leaf('t',2),List('e', 't'),3), Leaf('x',4),List('e', 't','x'),7)))
  }
  
  test("createCodeTree from text") {
    assert(createCodeTree("xxtxtxe".toList) == Fork(Fork(Leaf('e',1),Leaf('t',2),List('e', 't'),3), Leaf('x',4),List('e', 't','x'),7))
  }
  
  test("The code for xxxxtte should decode ext as 00101") {
    new TestTrees {
      val aCode = createCodeTree("xxxxtte".toList)
      assert(decode(aCode, List(0,0,1,0,1)) === "ext".toList)
    }
  }
  
  test("The code for xxxxtte should decode singleton sequence x successfully as 1") {
    new TestTrees {
      val aCode = createCodeTree("xxxxtte".toList)
      assert(decode(aCode, List(1)) === "x".toList)
    }
  }
  
   test("The code for xxxxtte should encode ext as 00101") {
    new TestTrees {
      val aCode = createCodeTree("xxxxtte".toList)
      assert(encode(aCode)("ext".toList) === List(0,0,1,0,1))
    }
  }

  test("decode and encode a very short text should be identity") {
    new TestTrees {
      assert(decode(t1, encode(t1)("ab".toList)) === "ab".toList)
    }
  }
  
    test("decode and quickencode a very short text should be identity") {
    new TestTrees {
      assert(decode(t1, quickEncode(t1)("ab".toList)) === "ab".toList)
    }
  }

}
