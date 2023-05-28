package roomieapp;

/**
 * Stores the relationship between 2 users including if
 * they matched with each other and their compatibility rating with each other
 */
public class Match {
    /** username for first user */
    public final String user1;

    /** username for second user */
    public final String user2;

    /**
     * dorming compatibility between user1 and user2.
     * compatibility between 0 and 100.
     * The higher the compatibility, the more compatible user1 and user2 are
     */
    public final float compatibility;

    /**
     * match status between user1 and user2.
     * 3 = user1 and user2 both matched with each other;
     * 2 = user2 sent match request to user1;
     * 1 = user1 sent match request to user2;
     * 0 = neither user1 nor user2 matched with each other;
     */
    public final int matchStatus;

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
        if(user1.compareToIgnoreCase(user2) >= 0) {
            throw new IllegalArgumentException();
        }
        this.user1 = user1;
        this.user2 = user2;
        this.compatibility = compatibility;
        this.matchStatus = matchStatus;
    }

    /**
     * Return true if this object and other object have equal user1, user2, compatibility,
     * and matchStatus, otherwise return false.
     * @param other comparison object
     * @return true if objects equal, otherwise false
     */
    @Override
    public boolean equals(Object other) {
        if(!(other instanceof Match)) {
            return false;
        }
        Match otherMatch = (Match) other;
        return (this.user1.equals(otherMatch.user1)) && (this.user2.equals(otherMatch.user2))
                && (Math.abs(this.compatibility - otherMatch.compatibility) < .001) &&
                (this.matchStatus == otherMatch.matchStatus);
    }
}
