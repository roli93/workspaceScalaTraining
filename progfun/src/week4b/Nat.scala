package week4b



	abstract class Nat {
	  def isZero: Boolean
	  def predecessor: Nat
	  def + (that: Nat): Nat
	  def - (that: Nat): Nat
	  def units: List[Nat]
		override def toString = units.size.toString()
		def successor = new Succ(this)
	}

	object Zero extends Nat{
		def isZero = true
		
		override def predecessor = throw new NoSuchElementException("Negative numbers are not natural")
		
		def + (that: Nat): Nat = that
	  def - (that: Nat): Nat = throw new NoSuchElementException("Negative numbers are not natural")

		def units = List()

	}
	
	class Succ(n: Nat) extends Nat{
		
		def isZero = false
		
		override def predecessor = n
		
		override def + (that: Nat): Nat =
			if(that.isZero) this
			else successor + that.predecessor
		
	  override def - (that: Nat): Nat =
		  if(that.isZero) this
			else predecessor - that.predecessor
			
		def units: List[Nat] = this :: predecessor.units
	
	}
	
	trait Expression{
	  def show:String = this match {
	    case Num(n) => n.toString
	    case Sum(a,b) => a.show + " + " + b.show
	  }
	}
	
	case class Sum(e1: Expression, e2:Expression) extends Expression{
	}
	
	case class Num(n:Int) extends Expression{
	  
	}