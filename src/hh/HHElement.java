package hh;
import java.util.Map;

/**
 * A superclass for many Hacker Hunter elements. 
 * 
 * @author Mogab R. Elleithy
 * @version Sept 12, 2015
 */
public class HHElement {
	
	public final static int SQUARE_FACTOR = 2;
	
	private Map<String, Integer> 
		/** The areas of interest the hacker would like to work in
		 * and the importance of working in that area on a 1-10 scale. */
		interests, 
		/** The name of the skills the hacker possesses 
		 * and their proficiency in that skill on a 1-10 scale. */
		skills;

	/**
	 * @param interests 
	 * 			Areas of interest to work in
	 * @param skills 
	 * 			Skills possessed
	 */
	public HHElement (
			Map<String, Integer> interests, 
			Map<String, Integer> skills) { 
		this.interests = interests; 
		this.skills = skills;
	}

	public Map<String, Integer> getInterests() { return interests; }
	public Map<String, Integer> getSkills() { return skills; }
}
