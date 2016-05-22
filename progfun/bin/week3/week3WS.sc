package week3

object week3WS {
  val l:List[Int] = new Cons(new Nil[Int], 1)     //> l  : week3.List[Int] = week3.Cons@73a51852
  l.head                                          //> res0: Int = 1
  val l2 =  new Cons(l,2)                         //> l2  : week3.Cons[Int] = week3.Cons@7ec77512
  l2.head + l2.tail.head                          //> res1: Int = 3
  
  l2.nth(-1)                                      //> java.lang.IndexOutOfBoundsException
                                                  //| 	at week3.Nil.nth(Nil.scala:14)
                                                  //| 	at week3.Nil.nth(Nil.scala:10)
                                                  //| 	at week3.Cons.nth(Nil.scala:21)
                                                  //| 	at week3.Cons.nth(Nil.scala:21)
                                                  //| 	at week3.week3WS$$anonfun$main$1.apply$mcV$sp(week3.week3WS.scala:9)
                                                  //| 	at org.scalaide.worksheet.runtime.library.WorksheetSupport$$anonfun$$exe
                                                  //| cute$1.apply$mcV$sp(WorksheetSupport.scala:76)
                                                  //| 	at org.scalaide.worksheet.runtime.library.WorksheetSupport$.redirected(W
                                                  //| orksheetSupport.scala:65)
                                                  //| 	at org.scalaide.worksheet.runtime.library.WorksheetSupport$.$execute(Wor
                                                  //| ksheetSupport.scala:75)
                                                  //| 	at week3.week3WS$.main(week3.week3WS.scala:3)
                                                  //| 	at week3.week3WS.main(week3.week3WS.scala)
 
  
}