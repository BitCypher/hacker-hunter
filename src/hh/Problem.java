package hh;
import java.util.Map;

/**
 * An object used to request help from a mentor 
 * for a project that doesn't have a Project Object. 
 * 
 * @author Mogab R. Elleithy
 * @version Sept 12, 2015
 */
public class Problem extends HHElement {

	public Problem(Map<String, Integer> interests, Map<String, Integer> skills) {
		super(interests, skills);
	}
}
