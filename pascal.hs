pascal 0 _ = 1
pascal _ 0 = 0
pascal column row = (pascal (column-1) (row-1)) + (pascal column (row-1))

--Scala: def pascal(column: Int, row: Int):Int = if(column<=0) 1 else( if(row<=0) 0 else (pascal(column-1,row-1)+pascal(column,row-1) ))

factorial 0 = 1
factorial n = n * factorial (n-1)

--

fact 0 = 1
fact n = fact2 n (n-1)

fact2 m 0 = m 
fact2 m n = fact2 (m*n) (n-1)

--

partition :: Ord a => [a] -> a -> ([a],[a])
partition [] _ = ([],[])
partition (x:xs) p | x < p     = (x:a,b)
                   | otherwise = (a,x:b)
   where
      (a,b) = partition xs p