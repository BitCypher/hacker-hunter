package test;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Formatter;
import java.util.HashMap;

import hh.HHScore;
import hh.HHType;
import hh.Hacker;

public class Hk2HkScoreTest extends ScoreTest {
	
	private Hacker[] hackers;
	
	private void initHackers(int hLen) {
		hackers = new Hacker[hLen];
		for (int i = 0; i < hLen; i++) {
			hackers[i] = new Hacker(
					initMap(new HashMap<String, Integer>(), 
							interestArr, 3, 2, 1), 
					initMap(new HashMap<String, Integer>(), 
							skillArr, 3, 2, 1), 
					initMap(new HashMap<String, Integer>(), 
							lGoalArr, 3, 2, 1), 
					initMap(new HashMap<String, Integer>(), 
							pGoalArr, 2, 1, 1)); 
		}
	}
	
	public void test10() throws IOException { 
		testHelper(10); 
	}
	
	public void test20() throws IOException { 
		testHelper(20); 
	}

	public void test30() throws IOException { 
		testHelper(30); 
	}

	private void testHelper(int hLen) throws IOException { 
		for(int i = 1; i <= 3; i++) {
			initHackers(hLen);
			// print list of each hacker profile. 
			writeHackers("hk2hk-hackers"+hLen/10+""+i+".txt", hackers);
			
			RandomAccessFile matchData = new RandomAccessFile("hk2hk-match"+hLen/10+""+i+".txt", "rw"); 
			for(int j = 0; j < hackers.length; j++) { 
				// rate all other hackers against this hacker. 
				HHScore[] scores = new HHScore[hackers.length];
				for(int k = 0; k < hackers.length; k++) { 
					if (j == k) { 
						// would compare against self; skip this iteration. 
						scores[k] = null;
						continue; 
					}
					scores[k] = hackers[j].hhScore(hackers[k]);
				}
				
				// find hacker with best rating against this hacker.
				int best = 0; 
				for (int k = 0; k < hackers.length; k++) { 
					if (j == k || scores[k] == null) 
						// would compare against self; skip this iteration. 
						continue; 
					if (scores[best] == null 
							|| scores[k].getComposite(HHType.Hacker, HHType.Hacker) 
							> scores[best].getComposite(HHType.Hacker, HHType.Hacker)) 
						best = k;
				}
				matchData.writeBytes(new Formatter(new StringBuilder()).
						format("Hacker#%d's match: Hacker#%d\n\tScore: %d, %d, %d\n"
								+ "\tComposite Score: %d\n\n", j, best, 
								scores[best].getInterests() + scores[best].getProjGoals(), 
								scores[best].getSkills(), 
								scores[best].getLearnGoals(), 
								scores[best].getComposite(HHType.Hacker, HHType.Hacker)).
						toString());
			}
			matchData.close();
		}
	}
}
