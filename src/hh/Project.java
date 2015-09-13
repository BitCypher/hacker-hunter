package hh;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Represents a posted, project idea
 * 
 * @author Mogab R. Elleithy
 * @version Sept 12, 2015
 */
public class Project extends HHElement {

	/** The over-arching goals the hacker would like to achieve
	 * and the importance of achieving those goals on a 1-10 scale. */
	private Map<String, Integer> projGoals;
	
	public Project(
			Map<String, Integer> interests, 
			Map<String, Integer> skills, 
			Map<String, Integer> projGoals) {
		super(interests, skills);
		this.projGoals = projGoals; 
	}
	
	/**
	 * Generates a "compatibility" score of this Project against all Hackers 
	 * in List hackers, who are assumed to be in a group considering 
	 * working on this Project. This is calculated by summing the product 
	 * of interests against this. 
	 * 
	 * @param hackers
	 * 		A list of Hackers on a team. 
	 * @return List of HHScore from each hacker. 
	 * 		The first one is the sum of all hacker scores.  
	 */
	public List<HHScore> hhScore(List<Hacker> hackers) { 
		List<HHScore> scores = new ArrayList<HHScore>(hackers.size()+1);
		scores.add(0, new HHScore(null, this, 0, 0, 0, 0));
		for(int i = 0; i < hackers.size(); i++) {
			Hacker hk = hackers.get(i);
			int 	inScore = 0, skScore = 0, 
					pgScore = 0;
			// no need to declare a new val for every element of every Map; 
			// reuse the same one. 
			Integer val = 0;
			
			for (String interest : this.getInterests().keySet()) { 
				val = hk.getInterests().get(interest);
				inScore += (val == null) ? 0 : 
					(SQUARE_FACTOR+this.getInterests().get(interest))*(SQUARE_FACTOR+val.intValue());
			}
			for (String skill : this.getSkills().keySet()) { 
				val = hk.getSkills().get(skill);
				skScore += (val == null) ? 0 : 
					(SQUARE_FACTOR+this.getSkills().get(skill))*(SQUARE_FACTOR+val.intValue());
			}
			for (String projGoal : this.projGoals.keySet()) { 
				val = hk.getProjGoals().get(projGoal);
				pgScore += (val == null) ? 0 : 
					(SQUARE_FACTOR+this.projGoals.get(projGoal))*(SQUARE_FACTOR+val.intValue());
			}
			
			HHScore score = new HHScore(hk, this, inScore, skScore, 0, pgScore);
			scores.add(0, scores.get(0).addScore(score));
			scores.add(i+1, score);
		}
		return scores; 
	}

	public Map<String, Integer> getProjGoals() { return projGoals; }
}
