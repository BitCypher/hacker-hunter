package test;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Formatter;
import java.util.HashMap;

import hh.HHScore;
import hh.HHType;
import hh.Hacker;
import hh.Project;

public class Hk2PjScoreTest extends ScoreTest{

	private Hacker[] hackers;
	private Project[] projects;
	
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
	
	private void initProjects(int pLen) { 
		projects = new Project[pLen];
		for (int i = 0; i < pLen; i++) { 
			projects[i] = new Project(
					initMap(new HashMap<String, Integer>(), 
							interestArr, 2, 1, 1), 
					initMap(new HashMap<String, Integer>(), 
							skillArr, 2, 1, 1), 
					initMap(new HashMap<String, Integer>(), 
							pGoalArr, 2, 1, 1)); 
		}
	}
	
	public void testH10P20() throws IOException {
		testHelper(10, 20);
	}
	
	private void testHelper(int hLen, int pLen) throws IOException {
		for (int i = 1; i <= 3; i++) { 
			initHackers(hLen); 
			initProjects(pLen); 
			// print list of each hacker and project profile. 
			writeHackers("hk2pj-hackers"+hLen/10+""+i+".txt", hackers);
			writeProjects("hk2pj-projects"+hLen/10+""+i+".txt", projects);

			RandomAccessFile matchData = new RandomAccessFile("hk2pj-match"+hLen/10+""+i+".txt", "rw"); 
			for(int j = 0; j < hackers.length; j++) { 
				// rate all projects against this hacker. 
				HHScore[] scores = new HHScore[projects.length];
				for(int k = 0; k < projects.length; k++) { 
					scores[k] = hackers[j].hhScore(projects[k]);
				}
				
				// find project with best rating against this hacker.
				int best = 0; 
				for (int k = 0; k < projects.length; k++) { 
					if (scores[best] == null 
							|| (scores[k].getComposite(HHType.Hacker, HHType.Project) 
							> scores[best].getComposite(HHType.Hacker, HHType.Project))) 
						best = k;
				}
				matchData.writeBytes(new Formatter(new StringBuilder()).
						format("Hacker#%d's match: Project#%d\n\tScore: %d, %d, %d\n"
								+ "\tComposite Score: %d\n\n", j, best, 
								scores[best].getInterests(), 
								scores[best].getSkills(), 
								scores[best].getProjGoals(), 
								scores[best].getComposite(HHType.Hacker, HHType.Hacker)).
						toString());
			}
			matchData.close();
		}
	}
}
