package hh;
import java.util.Map;

/**
 * An object used to hold the capabilities of and required skills for 
 * a particular hacking resource. 
 * 
 * @author Mogab R. Elleithy
 * @version Sept 12, 2015
 */
public class Resource extends HHElement {

	public Resource(Map<String, Integer> interests, Map<String, Integer> skills) {
		super(interests, skills);
	}
}
