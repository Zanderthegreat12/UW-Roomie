package roomieapp;

/**
 * Stores a user's email, phone number, and discord.
 */
public class ContactInfo {
    /** User this contact info belongs to */
    public String username;

    /**
     * User's email address.
     * Must follow format: email_username@domain.type.
     */
    public String email;

    /**
     * user's phone number.
     * Must have 10 digits.
     */
    public long phoneNumber;

    /**
     * User's discord name.
     * Must follow format: discord_username#xxxx (where x is a digit 1-9).
     */
    public String discord;

    /**
     * Creates empty contact info for user with the given username
     * @param username user's identifier
     */
    public ContactInfo(String username) {
        this.username = username;
    }

    /**
     * Store contact information provided in parameters in this class.
     * @param username user's identifier
     * @param email user's email address. Requires email format to be correct
     * @param phoneNumber user's phone number. Requires phone number format to be correct
     * @param discord user's discord name. Requires discord name format to be correct
     */
    public ContactInfo(String username, String email,
                       long phoneNumber, String discord) {
        this.username = username;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.discord = discord;
    }
}
