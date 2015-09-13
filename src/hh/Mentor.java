package hh;
import java.util.Map;

/**
 * Represents a hackathon mentor who would be able 
 * to help hackers in their project. 
 * 
 * @author Mogab R. Elleithy
 * @version Sept 12, 2015
 */
public class Mentor extends HHElement{

	private Map<String, Integer> 
		/** The name of the skills the hacker would like to learn
		 * and the importance of learning that skill on a 1-10 scale. */
		learnGoals,
		/** The name of the skills the hacker would like to learn
		 * and the importance of learning that skill on a 1-10 scale. */
		projGoals;

	public Mentor(
			Map<String, Integer> interests, 
			Map<String, Integer> skills, 
			Map<String, Integer> learnGoals, 
			Map<String, Integer> projGoals) {
		super(interests, skills);
		this.learnGoals = learnGoals; 
		this.projGoals = projGoals; 
	}
	
	/**
	 * Evaluates how well suited this mentor is to help the hacker 
	 * with her/his problem/project. 
	 * 
	 * @return The Hacker Hunter score of the <i>problem/project</i> 
	 * against <b>this</b> mentor. 
	 */
	public HHScore hhScore(Hacker hacker, Problem prob, Project proj, Resource res, HHType src, HHType trg, HHType priority) { 
		int 	inScore = 0, skScore = 0, 
				lgScore = 0, pgScore = 0;
		Integer val = 0,
				hkFactor = ((src==HHType.Hacker || trg==HHType.Hacker) ? 1 : 0),
				pbFactor = ((src==HHType.Problem || trg==HHType.Problem) ? 1 : 0),
				pjFactor = ((src==HHType.Project || trg==HHType.Project) ? 1 : 0),
				rsFactor = ((src==HHType.Resource || trg==HHType.Resource) ? 1 : 0);
		switch (priority) { 
			case Hacker: hkFactor *= 2; break; 
			case Problem: pbFactor *= 2; break; 
			case Project: pjFactor *= 2; break; 
			case Resource: rsFactor *= 2; break; 
			default: break;
		}

		for (String interest : this.getInterests().keySet()) { 
			val = (hacker.getInterests().get(interest)*hkFactor) 
					+ (prob.getInterests().get(interest)*pbFactor)
					+ (proj.getInterests().get(interest)*pjFactor)
					+ (res.getInterests().get(interest)*rsFactor);
			inScore += (val == null) ? 0 : 
				(SQUARE_FACTOR+this.getInterests().get(interest))*(SQUARE_FACTOR+val.intValue());
		}
		for (String skill : this.getSkills().keySet()) { 
			val = (hacker.getSkills().get(skill)*hkFactor) 
					+ (prob.getSkills().get(skill)*pbFactor)
					+ (proj.getSkills().get(skill)*pjFactor)
					+ (res.getSkills().get(skill)*rsFactor);
			skScore += (val == null) ? 0 : 
				(SQUARE_FACTOR+this.getSkills().get(skill))*(SQUARE_FACTOR+val.intValue());
		}
		for (String learnGoal : this.learnGoals.keySet()) { 
			val = hacker.getLearnGoals().get(learnGoal);
			lgScore += (val == null) ? 0 : 
				(SQUARE_FACTOR+this.learnGoals.get(learnGoal))*(SQUARE_FACTOR+val.intValue());
		}
		for (String projGoal : this.getProjGoals().keySet()) { 
			val = (hacker.getProjGoals().get(projGoal)*hkFactor) 
					+ (proj.getProjGoals().get(projGoal)*pjFactor);
			skScore += (val == null) ? 0 : 
				(SQUARE_FACTOR+this.projGoals.get(projGoal))*(SQUARE_FACTOR+val.intValue());
		}
		HHElement[] hhe = new HHElement[2]; 
		HHType[] hht = {src, trg}; 
		for (int i = 0; i < 2; i++) { 
			switch(hht[i]) {
				case Hacker: hhe[i] = hacker; break;
				case Problem: hhe[i] = prob; break;
				case Project: hhe[i] = proj; break;
				case Resource: hhe[i] = res; break;
				default: break;  
			}
		}
		return new HHScore(hhe[0], hhe[1], inScore, skScore, lgScore, pgScore);
	}

	public Map<String, Integer> getLearnGoals() { return learnGoals; } 
	public Map<String, Integer> getProjGoals() { return projGoals; } 
}
