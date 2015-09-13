package test;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Formatter;
import java.util.Map;
import java.util.Random;

import hh.Hacker;
import hh.Project;
import junit.framework.TestCase;

public class ScoreTest extends TestCase{

	protected String[] 
			interestArr = {"Front End", "Android Dev", "iOS Dev", "Music", 
					"Web Dev", "Game Dev", "SW Dev", "Crypto/Security"}, 
			skillArr = {"JavaScript", "Teamwork", "C++", "Android", "Swift", 
					"Java", "Python", "iOS", "Unity", "C#", "Graphic Design", 
					"C lang", "Leadership", "Git"}, 
			lGoalArr = {"JavaScript", "Teamwork", "C++", "Android", "Swift", 
					"Java", "Python", "iOS", "Unity", "C#", "Graphic Design", 
					"C lang", "Leadership", "Git"}, 
			pGoalArr = {"Gaming", "Social Change", "Social Networking", 
					"Convenience", "Entertainment", "Research", "Productivity", 
					"Useful Tool"}; 

	protected Map<String, Integer> initMap(
			Map<String, Integer> map, 
			String[] list, int chanPres, int chanHi, int chanLo) { 
		Random rand = new Random(System.nanoTime());
		for(String str : list) { 
			if (rand.nextInt(chanPres) == 0)
				// (1/chanPres) chance hacker won't have "this."
				continue;  
			if (rand.nextInt(chanHi+chanLo) < chanLo) 
				// (chanLo/(chanHi+chanLo)) chance 
				// hacker will be less that 50% interested in this
				map.put(str, rand.nextInt(5) + 1);
			else 
				map.put(str, rand.nextInt(5) + 6);
		}
		return map;
	}
	
	protected void writeHackers(String fileName, Hacker[] hackers) throws IOException { 
		RandomAccessFile hackerData = new RandomAccessFile(fileName, "rw"); 
		for (int j = 0; j < hackers.length; j++)  
			hackerData.writeBytes(new Formatter(new StringBuilder()).
					format("Hacker#%d: \n\tinterests: %s\n\tskills: %s\n"
							+ "\tlearning goals: %s\n\tproject goals: %s\n\n", j, 
							hackers[j].getInterests().toString(), 
							hackers[j].getSkills().toString(), 
							hackers[j].getLearnGoals().toString(), 
							hackers[j].getProjGoals().toString()).toString());
		hackerData.close(); 
	}

	protected void writeProjects(String fileName, Project[] projects) throws IOException { 
		RandomAccessFile hackerData = new RandomAccessFile(fileName, "rw"); 
		for (int j = 0; j < projects.length; j++)  
			hackerData.writeBytes(new Formatter(new StringBuilder()).
					format("Hacker#%d: \n\tinterests: %s\n\tskills: %s\n"
							+ "\tproject goals: %s\n\n", j, 
							projects[j].getInterests().toString(), 
							projects[j].getSkills().toString(), 
							projects[j].getProjGoals().toString()).toString());
		hackerData.close(); 
	}
}