import forcomp.Anagrams._

object scalaWeek6assigmentWS {
	subsets(List('b','a','a'))                //> res0: List[List[Char]] = List(List(), List(a), List(a), List(a, a), List(b), 
                                                  //| List(b, a), List(b, a), List(b, a, a))
     
  expand(('x',4))                                 //> res1: forcomp.Anagrams.OccurrecnceExpansion = List(x, x, x, x)
  
  def condense(expansion: OccurrecnceExpansion) =
  	wordOccurrences(expansion.mkString)       //> condense: (expansion: forcomp.Anagrams.OccurrecnceExpansion)forcomp.Anagrams
                                                  //| .Occurrences
  
  condense(List('a', 'b','a'))                    //> res2: forcomp.Anagrams.Occurrences = List((a,2), (b,1))
  
  subsets(List(('a',2),('b',1)).flatMap(expand))
  .map(condense)                                  //> res3: List[forcomp.Anagrams.Occurrences] = List(List(), List((b,1)), List((a
                                                  //| ,1)), List((a,1), (b,1)), List((a,1)), List((a,1), (b,1)), List((a,2)), List
                                                  //| ((a,2), (b,1)))
	
	val expandedOccss = List(('b',2),('a',2)).map(expand)
                                                  //> expandedOccss  : List[forcomp.Anagrams.OccurrecnceExpansion] = List(List(b, 
                                                  //| b), List(a, a))
  def combs(expandedOccs:List[OccurrecnceExpansion]):List[OccurrecnceExpansion] =
 	(expandedOccs match{
 		case exp :: exps =>  	for{
 			subExpansion <- subsets(exp)
 			comb <- combs(exps)
 		} yield subExpansion ++ comb
 		case List() => List(List())
 	}).distinct                               //> combs: (expandedOccs: List[forcomp.Anagrams.OccurrecnceExpansion])List[forco
                                                  //| mp.Anagrams.OccurrecnceExpansion]
                                                  
  combs(expandedOccss).map(condense)              //> res4: List[forcomp.Anagrams.Occurrences] = List(List(), List((a,1)), List((a
                                                  //| ,2)), List((b,1)), List((a,1), (b,1)), List((a,2), (b,1)), List((b,2)), List
                                                  //| ((a,1), (b,2)), List((a,2), (b,2)))
  
  val x = List(('a', 1), ('d', 1), ('l', 1), ('r', 1))
                                                  //> x  : List[(Char, Int)] = List((a,1), (d,1), (l,1), (r,1))
  
  sentenceOccurrences( List("me") )               //> res5: forcomp.Anagrams.Occurrences = List((e,1), (m,1))
  
  combinations(sentenceOccurrences( List("me") )) //> res6: List[forcomp.Anagrams.Occurrences] = List(List(), List((m,1)), List((e
                                                  //| ,1)), List((e,1), (m,1)))
  
  combinations(sentenceOccurrences( List("en", "my") )).filter(dictionaryByOccurrences.contains(_))
                                                  //> res7: List[forcomp.Anagrams.Occurrences] = List(List((m,1), (y,1)), List((e,
                                                  //| 1), (n,1)), List((e,1), (m,1)), List((e,1), (m,1), (n,1)))
  
	dictionaryByOccurrences(List(('e',1), ('m',1)))
                                                  //> res8: List[forcomp.Anagrams.Word] = List(em, me)
		val sentenceOccurrencess = sentenceOccurrences( List("en", "my") )
                                                  //> sentenceOccurrencess  : forcomp.Anagrams.Occurrences = List((e,1), (m,1), (
                                                  //| n,1), (y,1))

	def sentences = List(List("I"))           //> sentences: => List[List[String]]
		
  def anag(occurrences: Occurrences): List[Sentence] = occurrences match{
  	case List() => List(List())
  	case occurrences => for{
			combination <- combinations(occurrences)
			if dictionaryByOccurrences.contains(combination)
			word <- dictionaryByOccurrences(combination)
			sentence <- anag(subtract(occurrences, combination))
		} yield word::sentence
  }                                               //> anag: (occurrences: forcomp.Anagrams.Occurrences)List[forcomp.Anagrams.Sent
                                                  //| ence]
	
  anag(sentenceOccurrences(List("yes","man")))    //> res9: List[forcomp.Anagrams.Sentence] = List(List(my, as, en), List(my, en,
                                                  //|  as), List(my, sane), List(my, Sean), List(yes, man), List(en, my, as), Lis
                                                  //| t(en, as, my), List(men, say), List(as, my, en), List(as, en, my), List(say
                                                  //| , men), List(man, yes), List(sane, my), List(Sean, my))
	
	dictionaryByOccurrences(List(('e',1), ('m',1)))
                                                  //> res10: List[forcomp.Anagrams.Word] = List(em, me)
	
	List(List("a"),List("b"))                 //> res11: List[List[String]] = List(List(a), List(b))

}