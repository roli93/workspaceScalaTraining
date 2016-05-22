package week3

trait List[T] {
  def head : T 
  def tail : List[T]
  def isEmpty : Boolean
  def nth(index : Int) : T
}

class Nil[T] extends List[T]{
  def isEmpty = true
  def tail = throw new NoSuchElementException("Nil.tail")
  def head = throw new NoSuchElementException("Nil.head")
  def nth(index : Int) = throw new IndexOutOfBoundsException
  
  override def toString() = "[]"
  
}

class Cons[T](val tail: List[T], val head: T) extends List[T]{
  def isEmpty = false 
  def nth(index :Int) = 
    if(index < 0) throw new IndexOutOfBoundsException
    else if (index == 0) head
    else tail.nth(index-1)
    
  override def toString() = head+" : "+ tail
}