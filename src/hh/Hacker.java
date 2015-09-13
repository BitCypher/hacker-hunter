package hh;
import java.util.Map;

/** 
 * Represents a hackathon hacker and her/his skills, 
 * interests, and goals (learning and project)
 * 
 * @author Mogab R. Elleithy
 * @version Sept 12, 2015
 */

public class Hacker extends HHElement{

	private Map<String, Integer> 
		/** The name of the skills the hacker would like to learn
		 * and the importance of learning that skill on a 1-10 scale. */
		learnGoals,
		/** The over-arching goals the hacker would like to achieve
		 * and the importance of achieving those goals on a 1-10 scale. */
		projGoals;
	private boolean wantsMentor, wantsMentee; 
	
	/**
	 * @param interests 
	 * 			Areas of interest to work in
	 * @param skills 
	 * 			Skills possessed
	 * @param learnGoals 
	 * 			Skills to learn
	 * @param projGoals 
	 * 			Goals to be accomplished by project
	 */
	public Hacker(
			Map<String, Integer> interests, 
			Map<String, Integer> skills, 
			Map<String, Integer> learnGoals, 
			Map<String, Integer> projGoals) { 
		super(interests, skills); 
		this.learnGoals = learnGoals;
		this.projGoals = projGoals;
		this.wantsMentor = true; 
		this.wantsMentee = false; 
	}
	
	/**
	 * Generates a "compatibility" score of Hacker <i>other</i> against <b>this</b>. <br>
	 * This is calculated by summing the product of interests against other. 
	 * @param other 
	 * 			The other Hacker against whom this score is being generated. 
	 * @return The Hacker Hunter score of <i>other</i> against <b>this</b>. 
	 */
	public HHScore hhScore(Hacker other) { 
		int 	inScore = 0, skScore = 0, 
				lgScore = 0, pgScore = 0;
		// no need to declare a new val for every element of every Map; 
		// reuse the same one. 
		Integer val = 0;
		
		for (String interest : this.getInterests().keySet()) { 
			val = other.getInterests().get(interest);
			inScore += (val == null) ? 0 : 
				(SQUARE_FACTOR+this.getInterests().get(interest))*(SQUARE_FACTOR+val.intValue());
		}
		for (String skill : this.getSkills().keySet()) { 
			val = other.getSkills().get(skill);
			skScore += (val == null) ? 0 : 
				(SQUARE_FACTOR+this.getSkills().get(skill))*(SQUARE_FACTOR+val.intValue());
		}
		for (String learnGoal : this.learnGoals.keySet()) { 
			val = other.learnGoals.get(learnGoal);
			lgScore += (val == null) ? 0 : 
				(SQUARE_FACTOR+this.learnGoals.get(learnGoal))*(SQUARE_FACTOR+val.intValue());
		}
		for (String projGoal : this.projGoals.keySet()) { 
			val = other.projGoals.get(projGoal);
			pgScore += (val == null) ? 0 : 
				(SQUARE_FACTOR+this.projGoals.get(projGoal))*(SQUARE_FACTOR+val.intValue());
		}
		
		return new HHScore(this, other, inScore, skScore, lgScore, pgScore); 
	}
	
	/**
	 * Generates a "compatibility" score of Project <i>proj</i> against <b>this</b>. <br>
	 * This is calculated by summing the product of interests against proj. 
	 * @param other 
	 * 			The other Hacker against whom this score is being generated. 
	 * @return The Hacker Hunter score of <i>other</i> against <b>this</b>. 
	 */
	public HHScore hhScore(Project proj) { 
		int 	inScore = 0, skScore = 0, 
				pgScore = 0;
		// no need to declare a new val for every element of every Map; 
		// reuse the same one. 
		Integer val = 0;
		
		for (String interest : this.getInterests().keySet()) { 
			val = proj.getInterests().get(interest);
			inScore += (val == null) ? 0 : 
				(SQUARE_FACTOR+this.getInterests().get(interest))*(SQUARE_FACTOR+val.intValue());
		}
		for (String skill : this.getSkills().keySet()) { 
			val = proj.getSkills().get(skill);
			skScore += (val == null) ? 0 : 
				(SQUARE_FACTOR+this.getSkills().get(skill))*(SQUARE_FACTOR+val.intValue());
		}
		for (String projGoal : this.projGoals.keySet()) { 
			val = proj.getProjGoals().get(projGoal);
			pgScore += (val == null) ? 0 : 
				(SQUARE_FACTOR+this.projGoals.get(projGoal))*(SQUARE_FACTOR+val.intValue());
		}
		
		return new HHScore(this, proj, inScore, skScore, 0, pgScore);
	}

	public Map<String, Integer> getLearnGoals() { return learnGoals; }
	public Map<String, Integer> getProjGoals() { return projGoals; }
	public boolean wantsMentor() { return wantsMentor; }
	public void setWantsMentor(boolean wantsMentor) { this.wantsMentor = wantsMentor; }
	public boolean wantsMentee() { return wantsMentee; }
	public void setWantsMentee(boolean wantsMentee) { this.wantsMentee = wantsMentee; }
}
