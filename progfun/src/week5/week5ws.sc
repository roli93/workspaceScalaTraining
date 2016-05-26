package week5

object week5ws {
  implicit object IntMonoid extends Monoid[Int] {
    def add(x: Int, y: Int): Int = x + y
    def unit: Int = 0
  }
  //Works because it is accesible from here
	println(ImplicitTest.sum(List(1, 2, 3)))  //> 6
	//I don't now why ths doesn't work:	 println(ImplicitTest.sum(List("a", "b", "c")))
	
	implicit def asInt(x: String): Int = x.length
                                                  //> asInt: (x: String)Int

	implicit def asReflexivePair[T](x: T):(T,T) = (x,x)
                                                  //> asReflexivePair: [T](x: T)(T, T)
	"lala"._2                                 //> res0: String = lala
	
	"30"-1                                    //> res1: Int = 1
 	
 	def pack[T](xs: List[T]): List[List[T]] = xs match {
    case Nil      => Nil
    case x :: _ => xs.filter(_==x) :: pack(xs.filterNot(_==x))
  }                                               //> pack: [T](xs: List[T])List[List[T]]
  
  def encode[T](xs: List[T]): List[(T,Int)] = pack(xs).map{singleList=>(singleList.head,singleList.size)}
                                                  //> encode: [T](xs: List[T])List[(T, Int)]
 	
 	val list = List(3,2,1,3,2,3)              //> list  : List[Int] = List(3, 2, 1, 3, 2, 3)

	pack(list)                                //> res2: List[List[Int]] = List(List(3, 3, 3), List(2, 2), List(1))

	encode(list)                              //> res3: List[(Int, Int)] = List((3,3), (2,2), (1,1))
 	
 	def mapFun[T, U](xs: List[T], f: T => U): List[U] =
    (xs foldRight List[U]())(f(_)::_)             //> mapFun: [T, U](xs: List[T], f: T => U)List[U]

  def lengthFun[T](xs: List[T]): Int =
    (xs foldRight 0){(elem,lenghtSoFar) => 1+lenghtSoFar}
                                                  //> lengthFun: [T](xs: List[T])Int
    
  mapFun[Int,Int](list, _*10)                     //> res4: List[Int] = List(30, 20, 10, 30, 20, 30)
  
  lengthFun[Int](list)                      //> res6: Iterable[Int] = List(1, 2)
 	
}



abstract class SemiGroup[A] {
  def add(x: A, y: A): A
}
abstract class Monoid[A] extends SemiGroup[A] {
  def unit: A
}

object Monoid{
	implicit object StringMonoid extends Monoid[String] {
    def add(x: String, y: String): String = x concat y
    def unit: String = ""
  }
}

object ImplicitTest {
  def sum[A](xs: List[A])(implicit m: Monoid[A]): A =
    if (xs.isEmpty) m.unit
    else m.add(xs.head, sum(xs.tail))

}