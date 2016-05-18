package week1

object session {
  def sum(f: Int => Int)(a:Int,b:Int):Int =
  	if(a>b) 0 else f(a) + sum(f)(a+1,b)       //> sum: (f: Int => Int)(a: Int, b: Int)Int
  
  def prod(f: Int => Int)(a:Int,b:Int):Int =
  	if(a>b) 1 else f(a) * prod(f)(a+1,b)      //> prod: (f: Int => Int)(a: Int, b: Int)Int
  	
  def factorial:(Int=>Int) = prod(x=>x)(1,_)      //> factorial: => Int => Int
  
  def reduceWith(neutral: Int,f:(Int,Int)=>Int)(g:Int=>Int)(a:Int,b:Int):Int =
   if (a>b) neutral else f(g(a),reduceWith(neutral,f)(g)(a+1,b))
                                                  //> reduceWith: (neutral: Int, f: (Int, Int) => Int)(g: Int => Int)(a: Int, b: I
                                                  //| nt)Int
   
   
  reduceWith(0, (x,y)=>x+y)(x=>x)(2, 5)           //> res0: Int = 14
  
  def fibonacci(n:Int):Double =
  	if(n == 1 || n == 0) 1
  	else (fibonacci(n-1)+fibonacci(n-2))%100  //> fibonacci: (n: Int)Double
  	
  fibonacci(6)                                    //> res1: Double = 13.0
  
  def fibo(n:Int):Double = {
  	def loop(prev:Int,acc:Int, i:Int):Int = {
	  		if(i == n) acc
	  		else loop(acc,(acc+prev)%100,(i+1))
  	}
  	loop(0,1,0)
  }                                               //> fibo: (n: Int)Double
  	
  fibo(6)                                         //> res2: Double = 13.0
  
  def fact(n:Int):Int = {
  n match{
	  	case 0 => 1
	  	case n => n * fact(n-1)
  	}
  }                                               //> fact: (n: Int)Int
  
  fact(5)                                         //> res3: Int = 120
  
}

/*
fibonacci(6)
fibonacci(5)+fibonacci(4)
fibonacci(3)+fibonacci(4)+fibonacci(2)+fibonacci(3)
fibonacci(1)+fibonacci(2)+fibonacci(2)+fibonacci(3)+fibonacci(0)+fibonacci(1)+fibonacci(1)+fibonacci(2)
1+fibonacci(0)+fibonacci(1)+fibonacci(0)+fibonacci(1)+fibonacci(1)+fibonacci(2)+1+1+1+fibonacci(0)+fibonacci(1)
1+1+1+1+1+1+fibonacci(0)+fibonacci(1)+1+1+1+1+1
1+1+1+1+1+1+1+1+1+1+1+1+1
13
*/