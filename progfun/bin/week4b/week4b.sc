package week4b

object week4b {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet
  
  val four =
  	new Succ(new Succ(new Succ(new Succ(Zero))))
                                                  //> four  : week4b.Succ = 4
  	
  val six =
  	new Succ(new Succ(new Succ(new Succ( new Succ( new Succ(Zero) ) ))))
                                                  //> six  : week4b.Succ = 6
  	
 	def insert(elem: Int, list: List[Int]):List[Int] = list match{
 		case List() => List(elem)
 		case x :: xs =>
 			if(elem < x) elem :: x :: xs
 			else x :: insert(elem,xs)
 	}                                         //> insert: (elem: Int, list: List[Int])List[Int]
  	
  six+four                                        //> res0: week4b.Nat = 10
  
  six-four                                        //> res1: week4b.Nat = 2
  
  Sum(Num(1),Sum(Num(2),Num(3))).show             //> res2: String = 1 + 2 + 3
  
  insert(4,List())                                //> res3: List[Int] = List(4)
  
  insert(4,List(1,2,3,5,6,7))     
  
}