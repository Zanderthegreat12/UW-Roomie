package roomieapp;

/**
 * Contains a user's survey answers
 */
public class Survey {
    /** A user's identifier */
    public String username;

    /**
     * User's top dorm preference.
     * firstDorm must be a valid UW dorm
     */
    public String firstDorm;

    /**
     * User's second dorm preference.
     * secondDorm must be a valid UW dorm
     */
    public String secondDorm;

    /**
     * User's third dorm preference.
     * thirdDorm must be a valid UW dorm
     */
    public String thirdDorm;

    /**
     * User's desired room type. Only the following values are allowed.
     * 1 = single/studio;
     * 2 = double/2 bedroom;
     * 3 = triple/3 bedroom;
     * 4 = quad/4 bedroom;
     * 5 = 5 bedroom;
     * 6 = 6 bedroom;
     */
    public int roomType;

    /**
     * Whether the user wants to be in gender inclusive dorm.
     * Only the following values are allowed.
     * 0 = doesn't want to be in gender inclusive dorm;
     * 1 = does want to be in gender inclusive dorm;
     */
    public int genderInclusive;

    /**
     * User's year (not their status).
     * Only the following values are allowed.
     * 1 = first year at UW;
     * 2 = second year at UW;
     * 3 = third year at UW;
     * 4 = fourth year at UW;
     */
    public int studentYear;

    /**
     * User's preferred roommate year (not status).
     * Only the following values are allowed.
     * 1 = first year at UW;
     * 2 = second year at UW;
     * 3 = third year at UW;
     * 4 = fourth year at UW;
     */
    public int roommateYear;

    /**
     * If User is OK with drinking.
     * Only the following values are allowed.
     * 0 = does not tolerate drinking;
     * 1 = they themselves drink or are fine with roommate drinking;
     */
    public int drinkingPref;

    /**
     * At what hour the user typically wakes up.
     * wakeTime can range between 0-23.
     * (e.g. 8 means the user wakes up between 8 am and 9 am)
     */
    public int wakeTime;

    /**
     * At what hour the user typically goes to sleep.
     * sleepTime can range between 0-23.
     * (e.g. 23 means the user goes to bed between 11 pm and 12 am)
     */
    public int sleepTime;

    /**
     * Whether the user is a heavy sleeper or not.
     * Only the following values are allowed.
     * 0 = light sleeper;
     * 1 = heavy sleeper;
     */
    public int heavySleep;

    /**
     * Whether the user considers themselves an introvert, extrovert, or ambivert.
     * Only the following values are allowed.
     * 0 = introvert;
     * 1 = ambivert;
     * 2 = extrovert;
     */
    public int studentVert;

    /**
     * Whether the user prefers their roommate to be an introvert, extrovert, or ambivert.
     * Only the following values are allowed.
     * 0 = introvert;
     * 1 = ambivert;
     * 2 = extrovert;
     */
    public int roommateVert;

    /**
     * Whether the user will want to have friends over at the dorm.
     * Only the following values are allowed.
     * 0 = won't bring friends to the dorm;
     * 1 = will bring friends to the dorm;
     */
    public int studentFriends;

    /**
     * Whether the user is OK with roommate bringing friends over to the dorm.
     * Only the following values are allowed.
     * 0 = roommate can bring friends to the dorm;
     * 1 = roommate cannot bring friends to the dorm;
     */
    public int roommateFriends;

    /**
     * Whether the user considers themselves clean, neat, and organized.
     * Only the following values are allowed.
     * 0 = student is messy;
     * 1 = student is neat;
     */
    public int studentNeat;

    /**
     * Whether the user wants their roommate to be clean, neat, and organized.
     * Only the following values are allowed.
     * 0 = Doesn't care if roommate is messy;
     * 1 = Prefers that their roommate is neat;
     */
    public int roommateNeat;

    /**  Description of user's hobbies */
    public String hobbies;

    /**
     * Creates empty survey answers that belong to user with given username
     * @param username user's identifier
     */
    public Survey(String username) {
        this.username = username;
    }
}
