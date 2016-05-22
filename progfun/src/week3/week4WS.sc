package week3

import com.sun.net.httpserver.Authenticator.Success

object week4WS {
  def f(a:Int):Int =  a+1
  f(2)
  
  object List  extends Function0[List[Int]]
  	with Function1[Int,List[Int]]
  	with Function2[Int,Int,List[Int]]{
  	
  	def apply()= new Nil[Int];
  	
  	def apply(x:Int) = new Cons[Int](new Nil[Int],x)
  	
  	def apply(x:Int, y:Int) = new Cons[Int](new Cons[Int](new Nil[Int],y),x)
  	
  }
  
  List()

	List(2)
	
	List(2,3)
	
}