package patmat
import Huffman._

object huffman {
  decodedSecret                                   //> res0: List[Char] = List(h, u, f, f, m, a, n, e, s, t, c, o, o, l)
  encode(createCodeTree("xxxxtte".toList))("ext".toList)
                                                  //> res1: List[patmat.Huffman.Bit] = List(0, 0, 1, 0, 1)
}