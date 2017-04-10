
package videoengine;

/**
 * This abstract data type is a predictive engine for video ratings in a streaming video system. It
 * stores a set of users, a set of videos, and a set of ratings that users have assigned to videos.
 *
 */
public interface VideoEngine extends Video, Film, TvEpisode, TvSeries, User{

    /**
     * The abstract methods below are declared as void methods with no parameters. You need to
     * expand each declaration to specify a return type and parameters, as necessary. You also need
     * to include a detailed comment for each abstract method describing its effect, its return
     * value, any corner cases that the client may need to consider, any exceptions the method may
     * throw (including a description of the circumstances under which this will happen), and so on.
     * You should include enough details that a client could use this data structure without ever
     * being surprised or not knowing what will happen, even though they haven't read the
     * implementation.
     */

    /**
     *Adds a new video to the video set, avoiding duplicates. If newVideo is a not null, 
     *the set does not contain newVideo, and the set has the available
     *capacity, addVideo returns true and adds newVideo to the system. 
     *If newVideo is null of if the video set already contains newVideo, addVideo returns false and does not 
     *modify the set. If set has a capacity limit and adding newVideo would put it above its capacity, 
     *addVideo return false and does not modify the set. 
     *
     *@param newVideo to be added to the system
     *@return True if video was added to the set, false if video was not added.
     */
	
    public boolean addVideo(Video newVideo);
    
    
    /**
     * Removes an existing video from the system if possible. If the system contains the Video, 
     * then the video will modify the video set by removing it and true will be 
     * returned. If the set does not contain the Video, or newVideo is null, 
     * then the set will not be modified and false returned.
     * 
     *  
     *  @param video the Video to be added to the system
     *  @return true if video was removed, false if not removed
     *  
     */
    public boolean removeVideo(Video video);

    /**
     * Adds an existing television episode to an existing television series. If the series 
     * and episode are not null, the episode is added to the series and true is returned.
     * If series is null, method throws IllegalArgumentException, and false is returned
     * 
     * 
     * @param episode	the episode that is being added to the series
     * @param series the series the episode is being added to
     * @return true if episode was added to series, false if not
     * @throws IllegalArgumentException if TvSeries series is null.
     */
    public boolean addToSeries(TvEpisode episode, TvSeries series) throws IllegalArgumentException;

    /**
     * Removes a television episode from a television series if possible. If episode 
     * and series are not null, removes the specified episode from the series, and 
     * true is returned. If episode or series is null, then nothing is modified and 
     * false is returned.
     * 
     * @param episode The episode being added
     * @param series The series the episode is to be added too
     * @return true if episode is removed, false if episode is null or set does not
     * contain the episode. 
     * @throws IllegalArgumentException if TvSeries series is null
     */
    public boolean removeFromSeries(TvEpisode episode, TvSeries series) throws IllegalArgumentException;

    /**
     * Sets a user's rating for a video, as a number of stars from 1 to 5. If the user has not already
     * rated this video, then allows the user to add a rating assigned to the video. If the user has already
     * rated the video this method overwrite the previous rating. If either video or user is null 
     * method throws NullPointerException.
     * 
     * @param TvEpisode episode that is being rated
     * @param TvSeries series that the episode belongs too
     * @param int rating being applied to the video
     * @return returns true if rating was applied to the video, false if not
     * @throws NullPointerException if either rating, theVideo, or theUser is null
     * 
     * 
     */
   public boolean rateVideo(User theUser, Video theVideo, int rating);

    /**
     * Clears a user's rating on a video. If this user has rated this video and the rating has not
     * already been cleared, then the rating is cleared and the state will appear as if the rating
     * was never made. If this user has not rated this video, or if the rating has already been
     * cleared, then this method will throw an IllegalArgumentException.
     *
     * @param theUser user whose rating should be cleared
     * @param theVideo video from which the user's rating should be cleared
     * @throws IllegalArgumentException if the user does not currently have a rating on record for
     * the video
     * @throws NullPointerException if either the user or the video is null
     */
   public void clearRating(User theUser, Video theVideo);

    /**
     * Predicts the rating a user will assign to a video that they have not yet rated, as a number
     * of stars from 1 to 5. If user or video are null, null will be returned. If all videos have been rated,
     * or the video set is empty, null will be returned.
     * 
     * @param User theUser whose rating will be predicted
     * @param Video theVideo from which the rating will be predicted
     * @return int number rating for the video, or null if no video exists
     * @throws NullPointerException throws if user or video are null
     */
   public int predictRating(User theUser, Video theVideo);

    /**
     * Suggests a video for a user (e.g.,based on their predicted ratings). If the user
     * does not have any predicted ratings then method returns null as no prediction data
     * exists. If the video set is empty, then null will also be returned.
     * 
     * 
     * 
     * @param theUser The user that the video is being suggested to
     * @return the video to suggest if possible, or null if no suitable video exists
     */
   public Video suggestVideo(User theUser);


}

