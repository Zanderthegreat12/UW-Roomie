package roomieapp;

/**
 * Contains a user's survey answers
 */
public class Survey {
    /** A user's identifier */
    public final String username;

    /**
     * User's top dorm preference.
     * firstDorm must be a valid UW dorm.
     */
    public final String firstDorm;

    /**
     * User's second dorm preference.
     * secondDorm must be a valid UW dorm.
     */
    public final String secondDorm;

    /**
     * User's third dorm preference.
     * thirdDorm must be a valid UW dorm.
     */
    public final String thirdDorm;

    /**
     * User's desired room type. Only the following values are allowed.
     * 1 = single/studio;
     * 2 = double/2 bedroom;
     * 3 = triple/3 bedroom;
     * 4 = quad/4 bedroom;
     * 5 = 5 bedroom;
     * 6 = 6 bedroom;
     */
    public final int roomType;

    /**
     * Whether the user wants to be in gender inclusive dorm.
     * Only the following values are allowed.
     * 0 = doesn't want to be in gender inclusive dorm;
     * 1 = does want to be in gender inclusive dorm;
     */
    public final int genderInclusive;

    /**
     * User's year (not their status).
     * Only the following values are allowed.
     * 1 = first year at UW;
     * 2 = second year at UW;
     * 3 = third year at UW;
     * 4 = fourth year at UW;
     */
    public final int studentYear;

    /**
     * User's preferred roommate year (not status).
     * Only the following values are allowed.
     * 1 = first year at UW;
     * 2 = second year at UW;
     * 3 = third year at UW;
     * 4 = fourth year at UW;
     */
    public final int roommateYear;

    /**
     * If User is OK with drinking.
     * Only the following values are allowed.
     * 0 = does not tolerate drinking;
     * 1 = they themselves drink or are fine with roommate drinking;
     */
    public final int drinkingPref;

    /**
     * At what hour the user typically wakes up.
     * wakeTime can range between 0-23.
     * (e.g. 8 means the user wakes up between 8 am and 9 am)
     */
    public final int wakeTime;

    /**
     * At what hour the user typically goes to sleep.
     * sleepTime can range between 0-23.
     * (e.g. 23 means the user goes to bed between 11 pm and 12 am)
     */
    public final int sleepTime;

    /**
     * Whether the user is a heavy sleeper or not.
     * Only the following values are allowed.
     * 0 = light sleeper;
     * 1 = heavy sleeper;
     */
    public final int heavySleep;

    /**
     * Whether the user considers themselves an introvert, extrovert, or ambivert.
     * Only the following values are allowed.
     * 0 = introvert;
     * 1 = ambivert;
     * 2 = extrovert;
     */
    public final int studentVert;

    /**
     * Whether the user prefers their roommate to be an introvert, extrovert, or ambivert.
     * Only the following values are allowed.
     * 0 = introvert;
     * 1 = ambivert;
     * 2 = extrovert;
     * 3 = don't care;
     */
    public final int roommateVert;

    /**
     * Whether the user will want to have friends over at the dorm.
     * Only the following values are allowed.
     * 0 = won't bring friends to the dorm;
     * 1 = will bring friends to the dorm;
     */
    public final int studentFriends;

    /**
     * Whether the user is OK with roommate bringing friends over to the dorm.
     * Only the following values are allowed.
     * 0 = roommate can bring friends to the dorm;
     * 1 = roommate cannot bring friends to the dorm;
     */
    public final int roommateFriends;

    /**
     * Whether the user considers themselves clean, neat, and organized.
     * Only the following values are allowed.
     * 0 = student is messy;
     * 1 = student is neat;
     */
    public final int studentNeat;

    /**
     * Whether the user wants their roommate to be clean, neat, and organized.
     * Only the following values are allowed.
     * 0 = Doesn't care if roommate is messy;
     * 1 = Prefers that their roommate is neat;
     */
    public final int roommateNeat;

    /**  Description of user's hobbies */
    public final String hobbies;

    /**
     * Creates empty survey answers that belong to user with given username.
     * Look at class fields for value restrictions.
     * @param username owner of survey
     * @param firstDorm user's first dorm choice
     * @param secondDorm user's second dorm choice
     * @param thirdDorm user's third dorm choice
     * @param roomType user's preferred room type
     * @param genderInclusive user's preference for genderInclusive dorming
     * @param studentYear user's year at UW (not status)
     * @param roommateYear preferred roommates year at UW (not status)
     * @param drinkingPref user's attitude towards drinking
     * @param wakeTime What hour user wakes up
     * @param sleepTime What hour user goes to sleep
     * @param heavySleep If user is heavy sleeper or not
     * @param studentVert If user is introvert, ambivert, or extrovert
     * @param roommateVert user's preference for roommate to be introvert, ambivert, or extrovert
     * @param studentFriends If user wants to have friends over
     * @param roommateFriends user's preference for roommate having friend's over
     * @param studentNeat If user is neat or not
     * @param roommateNeat if user prefers neat roommate or not
     * @param hobbies user's list of hobbies
     */
    public Survey(String username,
                  String firstDorm,
                  String secondDorm,
                  String thirdDorm,
                  int roomType,
                  int genderInclusive,
                  int studentYear,
                  int roommateYear,
                  int drinkingPref,
                  int wakeTime,
                  int sleepTime,
                  int heavySleep,
                  int studentVert,
                  int roommateVert,
                  int studentFriends,
                  int roommateFriends,
                  int studentNeat,
                  int roommateNeat,
                  String hobbies
    ) {
        this.username = username;
        this.firstDorm = firstDorm;
        this.secondDorm = secondDorm;
        this.thirdDorm = thirdDorm;
        this.roomType = roomType;
        this.genderInclusive = genderInclusive;
        this.studentYear = studentYear;
        this.roommateYear = roommateYear;
        this.drinkingPref = drinkingPref;
        this.wakeTime = wakeTime;
        this.sleepTime = sleepTime;
        this.heavySleep = heavySleep;
        this.studentVert = studentVert;
        this.roommateVert = roommateVert;
        this.studentFriends = studentFriends;
        this.roommateFriends = roommateFriends;
        this.studentNeat = studentNeat;
        this.roommateNeat = roommateNeat;
        this.hobbies = hobbies;
    }

    @Override
    public boolean equals(Object other) {
        if(!(other instanceof Survey)) {
            return false;
        }
        Survey otherSurvey = (Survey) other;
        return (this.username.equals(otherSurvey.username)) &&
            (this.firstDorm.equals(otherSurvey.firstDorm)) &&
            (this.secondDorm.equals(otherSurvey.secondDorm)) &&
            (this.thirdDorm.equals(otherSurvey.thirdDorm)) &&
            (this.roomType == otherSurvey.roomType) &&
            (this.genderInclusive == otherSurvey.genderInclusive) &&
            (this.studentYear == otherSurvey.studentYear) &&
            (this.roommateYear == otherSurvey.roommateYear) &&
            (this.drinkingPref == otherSurvey.drinkingPref) &&
            (this.wakeTime == otherSurvey.wakeTime) &&
            (this.sleepTime == otherSurvey.sleepTime) &&
            (this.heavySleep == otherSurvey.heavySleep) &&
            (this.studentVert == otherSurvey.studentVert) &&
            (this.roommateVert == otherSurvey.roommateVert) &&
            (this.studentFriends == otherSurvey.studentFriends) &&
            (this.roommateFriends == otherSurvey.roommateFriends) &&
            (this.studentNeat == otherSurvey.studentNeat) &&
            (this.roommateNeat == otherSurvey.roommateNeat) &&
            (this.hobbies.equals(otherSurvey.hobbies));
    }
}
