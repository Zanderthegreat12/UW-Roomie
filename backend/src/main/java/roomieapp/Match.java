package roomieapp;

/**
 * Stores the relationship between 2 users including if
 * they matched with each other and their compatibility rating with each other
 */
public class Match {
    /** username for first user */
    public String user1;

    /** username for second user */
    public String user2;

    /**
     * dorming compatibility between user1 and user2 .
     * 0 <= compatibility <= 100
     * The higher the compatibility, the more compatible user1 and user2 are
     */
    public float compatibility;

    /**
     * match status between user1 and user2.
     * 3 = user1 and user2 both matched with each other;
     * 2 = user2 sent match request to user1;
     * 1 = user1 sent match request to user2;
     * 0 = neither user1 nor user2 matched with each other;
     */
    public int matchStatus;

    /**
     * Stores Match info into this object with given parameters.
     * Look at class fields for value restrictions.
     * @param user1 username for first user. Must be a valid username
     * @param user2 username for second user. Must be a valid username
     * @param compatibility How compatible user1 and user2 would be dorming together.
     * @param matchStatus whether user1 and user2 have a pending match request between them
     *                    or if they have successfully matched with each other
     * @throws IllegalArgumentException if user1 >= user2;
     */
    public Match(String user1, String user2, float compatibility, int matchStatus) {
        if(user1.compareTo(user2) >= 0) {
            throw new IllegalArgumentException();
        }
        this.user1 = user1;
        this.user2 = user2;
        this.compatibility = compatibility;
        this.matchStatus = matchStatus;
    }
}
