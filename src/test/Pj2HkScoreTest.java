package test;

import java.io.IOException;
import java.util.HashMap;

import hh.Hacker;
import hh.Project;

public class Pj2HkScoreTest extends ScoreTest {

	private Project[] projects;
	private Hacker[] hackers;
	
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
	
	public void testP10H20() throws IOException { 
		testHelper(10, 20); 
	}
	
	private void testHelper(int pLen, int hLen) throws IOException { 
		for (int i = 1; i <= 3; i++) { 
			initProjects(pLen); 
			initHackers(hLen); 
			writeHackers("pj2hk-hackers"+pLen/10+""+i+".txt", hackers);
			writeProjects("pj2hk-projects"+pLen/10+""+i+".txt", projects);
		}
	}
}