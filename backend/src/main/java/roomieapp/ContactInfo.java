package roomieapp;

/**
 * Stores a user's email, phone number, and discord
 */
public class ContactInfo {
    /** user this contact info belongs to */
    public String username;
    /** user's email address */
    public String email;
    /** user's phone number */
    public String phoneNumber;
    /** user's discord name */
    public String discord;

    /**
     * Store contact information provided in parameters in this class
     * @param username user's identifier
     * @param email user's email address
     * @param phoneNumber user's phone number
     * @param discord user's discord name
     */
    public ContactInfo(String username, String email,
                       String phoneNumber, String discord) {
        this.username = username;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.discord = discord;
    }
}
