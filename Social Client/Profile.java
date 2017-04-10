import java.lang.reflect.Array;

public class Profile<T> implements ProfileInterface{

	private static final Object[] ProfileInterface = null;
	private Set<ProfileInterface> followings = new Set<ProfileInterface>(25); 
	private String name;
	private String aboutMe;
	private int size = 0;
	
	/**You must include a constructor public Profile() 
	 * that initializes the name and “about me” blurb to 
	 * be empty strings, and a constructor public Profile(String name, String about) 
	 * that initializes these member variables with values provided by the caller.
	 */
	public Profile(){
		this("","");
	}
	
	public Profile(String name, String about){
		this.name = name;
		aboutMe = about;
	}
	
	
	
	
	
	 /**
     * Sets this profile's name.
     *
     * <p> If newName is not null, then setName modifies this profile so that
     * its name is newName. If newName is null, then setName throws
     * java.lang.IllegalArgumentException without modifying the profile.
     *
     * @param newName  The new name
     * @throws java.lang.IllegalArgumentException  If newEntry is null
     */
	public void setName(String newName) throws IllegalArgumentException {
		if(newName != null){
		name = newName;
		}
		try{
			
		}
		catch(Exception e){
			System.out.println("not a valid name");
		}
		
	}

	/**
     * Gets this profile's name.
     *
     * @return  The name
     */
	public String getName() {
		
		return name;
	}

	 /**
     * Sets this profile's "about me" blurb.
     *
     * <p> If newAbout is not null, then setAbout modifies this profile so that
     * its about blurb is newAbout. If newAbout is null, then setAbout throws
     * java.lang.IllegalArgumentException without modifying the profile.
     *
     * @param newAbout  The new blurb
     * @throws java.lang.IllegalArgumentException  If newAbout is null
     */
	public void setAbout(String newAbout) throws IllegalArgumentException {
		
		try{
			aboutMe = newAbout;
		}
		catch(Exception e){
			
		}
		
		
	}

	/**
     * Gets this profile's "about me" blurb
     *
     * @return  The blurb
     */
	public String getAbout() {
		
		return aboutMe;
	}

	/**
     * Adds another profile to this profile's following set.
     *
     * <p> If this profile's following set is at capacity, or if other is null,
     * then follow returns false without modifying the profile. Otherwise, this
     * profile in modified in such a way that other is added to this profile's
     * following set.
     *
     * @param other  The profile to follow
     * @return  True if successful, false otherwise
     */
	public boolean follow(ProfileInterface other) {
	//System.out.println("OTHER: " + other);
	boolean follow = false;
	
		if(other == null){
			//System.out.println("FIRST CASESSSSSS");
			follow = false;
		}
		
		else{
			try {
				//System.out.println("ADDING" + other);
				followings.add(other);
				size++;
				follow = true;
			} catch (IllegalArgumentException e) {
				
			} catch (SetFullException e) {
				
			}
			
		}
		//System.out.println("current size of set: " + followings.getCurrentSize());
		return follow;
	}

	/**
     * Removes the specified profile from this profile's following set.
     *
     * <p> If this profile's following set does not contain other, or if other
     * is null, then unfollow returns false without modifying the profile.
     * Otherwise, this profile in modified in such a way that other is removed
     * from this profile's following set.
     *
     * @param other  The profile to follow
     * @return  True if successful, false otherwise
     */
	public boolean unfollow(ProfileInterface other) {
		//this is redundant
		
		if(!followings.contains(other) || other == null){
			return false;
		}
		else if (followings.contains(other)){
			followings.remove(other);
			size--;
			return true;
		}
		else{
			return false;
		}
		
		
	}

	 /**
     * Returns a preview of this profile's following set.
     *
     * <p> The howMany parameter is a maximum size. The returned array may be
     * less than the requested size if this profile is following fewer than
     * howMany other profiles. Clients of this method must be careful to check
     * the size of the returned array to avoid
     * java.lang.ArrayIndexOutOfBoundsException.
     *
     * <p> Specifically, following returns an array of size max(howMany,
     * &lt;number of profiles that this profile is following&gt;). This array is
     * populated with arbitrary profiles that this profile follows.
     *
     * @param howMany  The maximum number of profiles to return
     * @return  An array of size &le;howMany, containing profiles that this
     * profile follows
     */
	public ProfileInterface[] following(int howMany) {
		
		
		
		if(howMany > followings.getCurrentSize()){
			howMany = followings.getCurrentSize();
		}
		ProfileInterface[] followingArray = new ProfileInterface[howMany];
		Object[] array = followings.toArray();
		
//		if(followings.toArray() < howMany){
//			for(int i = followings.getCurrentSize(); i < howMany; i++){
//				
//			}
//		}
		
//		if(followings.getCurrentSize() < howMany){
//			for(int i = followings.getCurrentSize(); i < howMany; i ++){
//				array[i] = null;
//			}
//		}
//		if(array.length < howMany){
//			for(int i = array.length; i < howMany; i++){
//				array[i] = null;
//			}
//		}
		
		
			for(int i = 0; i < howMany; i++){
				followingArray[i] = (Profile) array[i];
			
			}
			//This works: System.out.println(followingArray[i]);
			// Index out of bounds: System.out.println(array[i]);
			
		
		
		
		
		return followingArray;	
		
	}

	/**
     * Recommends a profile for this profile to follow. This returns a profile
     * followed by one of this profile's followed profiles. Should not recommend
     * this profile to follow someone they already follow, and should not
     * recommend to follow oneself.
     *
     * <p> For example, if this profile is Alex, and Alex follows Bart, and Bart
     * follows Crissy, this method might return Crissy.
     *
     * @return  The profile to suggest, or null if no suitable profile is
     * possible.
     */
	public ProfileInterface recommend() {
		
	ProfileInterface[] friends = this.following(followings.getCurrentSize()); //gets actual array
//		
	for(int i = 0; i<friends.length;i++){
		ProfileInterface[] theirFriends = friends[i].following(Integer.MAX_VALUE);
				for(int j = 0; j < theirFriends.length; j++){
					if( followings.contains(theirFriends[j]) || this != theirFriends[j]){
						return theirFriends[j];
					}
				}
	}
//	if(followings.getCurrentSize() == 0)
//		return null;
//		
//	Object[] array = followings.toArray();
//	
//	
//	ProfileInterface friend = (Profile) array[0];
//	System.out.println(friend);
//	
//	ProfileInterface[] rec = friend.following();
//	
//	ProfileInterface recommended = rec[0];
//		
	
	
	return null;
		
		
	}
	
}
