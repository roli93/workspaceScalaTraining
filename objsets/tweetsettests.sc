import common._
import objsets._

object tweetsettests {
	val t1 = new Tweet("","1",1)              //> t1  : objsets.Tweet = 1
	val t2 = new Tweet("","2",2)              //> t2  : objsets.Tweet = 2
	val t3 = new Tweet("","3",3)              //> t3  : objsets.Tweet = 3
	val t4 = new Tweet("","4",4)              //> t4  : objsets.Tweet = 4
	val t5 = new Tweet("","5",5)              //> t5  : objsets.Tweet = 5
	val t6 = new Tweet("","6",6)              //> t6  : objsets.Tweet = 6
	val t7 = new Tweet("","7",7)              //> t7  : objsets.Tweet = 7
	val t8 = new Tweet("","8",8)              //> t8  : objsets.Tweet = 8
	val t9 = new Tweet("","9",9)              //> t9  : objsets.Tweet = 9
	
	val ts =
	new NonEmpty(
	t2,
	new NonEmpty(
		t1,
		new Empty,
		new Empty
		),
	new NonEmpty(
		t3,
		new Empty,
		new Empty
		)
	)                                         //> ts  : objsets.NonEmpty = {{.<-1->.}<-2->{.<-3->.}}
	
	val ts2 = 	new NonEmpty(
	t8,
	new NonEmpty(
		t7,
		new Empty,
		new Empty
		),
	new NonEmpty(
		t9,
		new Empty,
		new Empty
		)
	)                                         //> ts2  : objsets.NonEmpty = {{.<-7->.}<-8->{.<-9->.}}
	
	val ts3 = ts.incl(t5).incl(t4).incl(t6)   //> ts3  : objsets.TweetSet = {{.<-1->.}<-2->{.<-3->{{.<-4->.}<-5->{.<-6->.}}}}
	
	ts3.union(ts2)                            //> res0: objsets.TweetSet = {{{{.<-1->.}<-2->{.<-3->{{.<-4->.}<-5->{.<-6->.}}}}
                                                  //| <-7->.}<-8->{.<-9->.}}
	ts3.filter(tw => tw.text > "2" && tw.text != "4")
                                                  //> res1: objsets.TweetSet = {.<-3->{.<-5->{.<-6->.}}}
	
	"aaa">"bbb"                               //> res2: Boolean = false

	
}