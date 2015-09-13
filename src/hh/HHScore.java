package hh;
	/** 
	 * Represents a (somewhat) detailed score of another Hacker 
	 * against this. 
	 * 
	 * @author Mogab R. Elleithy
	 * @version Sept 12, 2015
	 */
	public class HHScore { 
	
		private HHElement source, target;
		private int 
			/** Sum of results of interests against interests 
			 * and project goals against project goals. */
			interests, 
			/** Results of this.skills against other.learnGoals;  
			 * indicates ability of this as a mentor to other. */
			skills, 
			/** Results of this.learnGoals against other.skills;  
			 * indicates ability of other as a mentor to this. */
			learnGoals, 
			projGoals;
		
		public HHScore(HHElement source, HHElement target, 
				int interest, int skills, int learnGoals, int projGoals) {
			this.source = source; 
			this.target = target;
			this.interests = interest; 
			this.skills = skills; 
			this.learnGoals = learnGoals; 
			this.projGoals = projGoals;
		}
	
		public HHScore addScore(HHScore score) {
			this.source = (this.source.equals(score.source)) ? this.source : null; 
			this.target = (this.target.equals(score.target)) ? this.target : null;
			this.interests += score.interests; 
			this.skills += score.skills; 
			this.learnGoals += score.learnGoals; 
			this.projGoals += score.projGoals; 
			return this;
		}

		public int getInterests() { return interests; }
		public int getSkills() { return skills; }
		public int getLearnGoals() { return learnGoals; }
		public int getProjGoals() { return projGoals; }
		public HHElement getSource() { return source; }
		public HHElement getTarget() { return target; }

		public int getComposite(HHType srcType, HHType trgType) { 
			if (srcType == HHType.Hacker && trgType == HHType.Hacker) {
				Hacker h = (Hacker) source;
				return interests + projGoals
						+ (h.wantsMentee() ? skills : (int)Math.sqrt((double)skills))
						+ (h.wantsMentor() ? learnGoals : (int)Math.sqrt((double)learnGoals)); 
			} else if (srcType == HHType.Hacker && trgType == HHType.Project) { 
				return interests+skills+projGoals;
			}
			return interests + learnGoals + skills + projGoals;
		}
	}
