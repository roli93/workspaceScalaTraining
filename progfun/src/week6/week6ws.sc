package week6

object week6ws {

	implicit def toDividable(value:Int):DividableInt = new DividableInt(value)
                                                  //> toDividable: (value: Int)week6.DividableInt

	def isPrime(n: Int): Boolean = !2.until(n).exists(n.isDividedBy(_))
                                                  //> isPrime: (n: Int)Boolean
	
	1.until(30).map{n => (n,isPrime(n))}      //> res0: scala.collection.immutable.IndexedSeq[(Int, Boolean)] = Vector((1,true
                                                  //| ), (2,true), (3,true), (4,false), (5,true), (6,false), (7,true), (8,false), 
                                                  //| (9,false), (10,false), (11,true), (12,false), (13,true), (14,false), (15,fal
                                                  //| se), (16,false), (17,true), (18,false), (19,true), (20,false), (21,false), (
                                                  //| 22,false), (23,true), (24,false), (25,false), (26,false), (27,false), (28,fa
                                                  //| lse), (29,true))

	def sum(a: Option[Int], b: Option[Int]):Option[Int] =
		a.flatMap{aValue => b.map{bValue=> aValue+bValue}}
                                                  //> sum: (a: Option[Int], b: Option[Int])Option[Int]
		
	sum(Some(1),Some(5))                      //> res1: Option[Int] = Some(6)

	def forSum(a: Option[Int], b: Option[Int]):Option[Int] =
		for{
			aValue <- a
			bValue <- b
		} yield aValue+bValue             //> forSum: (a: Option[Int], b: Option[Int])Option[Int]
	
	forSum(Some(1),Some(5))                   //> res2: Option[Int] = Some(6)
	
	for{
		a <- List(1,2,3,4,5)
	} yield a*2                               //> res3: List[Int] = List(2, 4, 6, 8, 10)
	

	def dotProduct(x : List[Double], y : List[Double]): Double =
	(for((xComponent,yComponent) <- (x zip y))yield xComponent*yComponent).sum
                                                  //> dotProduct: (x: List[Double], y: List[Double])Double
                                                   
	dotProduct(List(2,3), List(4,5))          //> res4: Double = 23.0

	def isSafe(col: Int, queens: List[Int]): Boolean = {
		def threatens(threatener: Tuple2[Int,Int], threatened: Tuple2[Int,Int]): Boolean ={
			val difference = (threatener,threatened) match {case ((x1,y1),(x2,y2))=>(x2-x1,y2-y1)}
			difference match{
				case (0,_ ) => true
				case (_,0) => true
				case (x,y) => x == y //This should use abs
			}
			}
		val queenCoordinates = queens.reverse.zipWithIndex.map(_.swap) //(row,col)
		val newRow = queens.size
		val newCoordinate = (newRow,col)
		queenCoordinates.forall(!threatens(_,newCoordinate))
		}                                 //> isSafe: (col: Int, queens: List[Int])Boolean
		
	isSafe(3, List(0,3,1))                    //> res5: Boolean = false
	
	
	val books: List[Book] = List(
							new Book("1",List("a1")),
							new Book("2",List("a2")),
							new Book("3",List("a3","a1")),
							new Book("4",List("x4"))
							)
                                                  //> books  : List[week6.Book] = List([1 by List(a1)], [2 by List(a2)], [3 by Li
                                                  //| st(a3, a1)], [4 by List(x4)])
							
	 books.flatMap{
	 	b1 => books.filter{b2 => b2 != b1}.flatMap{
	 			b2 => b2.authors.flatMap{
	 				a2 => b1.authors.filter{
	 					a1 => a1 == a2
	 				}
	 			}
	 		}
	 	}                                 //> res6: List[String] = List(a1, a1)
	
	 for {
	 		b1 <- books
	 		b2 <- books
	 		if b1 != b2
	 		a1 <- b1.authors
	 		a2 <- b2.authors
	 		if a1 == a2
	 } yield a1                               //> res7: List[String] = List(a1, a1)
	
	for (b <- books; a <- b.authors if a startsWith "x") yield b.name
                                                  //> res8: List[String] = List(4)
	
	books.flatMap(b => for(a <-b.authors if a startsWith "x") yield b.name )
                                                  //> res9: List[String] = List(4)
	
	books.flatMap(b => for(a <-b.authors.withFilter(x => x startsWith "x") ) yield b.name )
                                                  //> res10: List[String] = List(4)
                                                  
	books.flatMap(b => b.authors.withFilter(x => x startsWith "x").map(a => b.name) )
                                                  //> res11: List[String] = List(4)
                                                  
	//Now, knowing how to translate it:
	
	for {
	 		b1 <- books
	 		b2 <- books
	 		if b1 != b2
	 		a1 <- b1.authors
	 		a2 <- b2.authors
	 		if a1 == a2
	 } yield a1                               //> res12: List[String] = List(a1, a1)
	 
	 books.flatMap{b1 =>
		 for {
		 		b2 <- books
		 		if b1 != b2
		 		a1 <- b1.authors
		 		a2 <- b2.authors
		 		if a1 == a2
		 } yield a1
	 }                                        //> res13: List[String] = List(a1, a1)
	 
	 books.flatMap{b1 =>
		 for {
		 		b2 <- books.withFilter{b2 => b1 != b2}
		 		a1 <- b1.authors
		 		a2 <- b2.authors
		 		if a1 == a2
		 } yield a1
	 }                                        //> res14: List[String] = List(a1, a1)
	 
	 books.flatMap{b1 =>
		 books.withFilter{b2 => b1 != b2}.flatMap{ b2 =>
			 	for {
			 		a1 <- b1.authors
			 		a2 <- b2.authors
		 			if a1 == a2
				} yield a1
		 }
	 }                                        //> res15: List[String] = List(a1, a1)
	 
	books.flatMap{b1 =>
		 books.withFilter{b2 => b1 != b2}.flatMap{ b2 =>
			 	b1.authors.flatMap{ a1 =>
				 	for {
				 		a2 <- b2.authors
			 			if a1 == a2
					} yield a1
			 	}
		 }
	 }                                        //> res16: List[String] = List(a1, a1)

	books.flatMap{b1 =>
		 books.withFilter{b2 => b1 != b2}.flatMap{ b2 =>
			 	b1.authors.flatMap{ a1 =>
				 	for {
				 		a2 <- b2.authors.withFilter{a2 => a1 == a2}
					} yield a1
			 	}
		 }
	 }                                        //> res17: List[String] = List(a1, a1)

	books.flatMap{b1 =>
		 books.withFilter{b2 => b1 != b2}.flatMap{ b2 =>
			 	b1.authors.flatMap{ a1 =>
			 		b2.authors.withFilter{a2 => a1 == a2}.map{a2 =>
			 			a1
			 		}
			 	}
		 }
	 }                                        //> res18: List[String] = List(a1, a1)
	
	//First attempt at translating, rewritten to make comparison easier
	books.flatMap{b1 =>
		books.filter{b2 => b2 != b1}.flatMap{b2 =>
	  	b1.authors.flatMap{a1 =>
	  		b2.authors.filter{a2 => a1 == a2}
	 			}
	 		}
	 	}                                 //> res19: List[String] = List(a1, a1)
	
	/*The only difference is the last map. As he said "yield a1" instead of "yield a2" -which
	is the same, given they are equal- he introduces that final map. In my case I did the
	equivalent to a "yield a2", which would result in a map(a2=>a2) which is equal to
	map(identity), which in turn returns the original list and is the ame as making no mapping
	at all, as I did*/
	
}

	class Book(val name: String, val authors:List[String]){
		override def toString() = "["+name+" by "+authors+"]"
	}
	
class DividableInt(value:Int){
	def isDividedBy(n:Int) = value %n ==0
}