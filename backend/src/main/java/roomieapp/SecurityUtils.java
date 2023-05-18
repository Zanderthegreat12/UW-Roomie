package roomieapp;

import java.io.*;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Random;
import javax.crypto.*;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;


/**
 * A collection of utility methods to help with managing passwords
 */
public class SecurityUtils {

  private KeyPair key;
  private final int CRYPT_KEY_LENGTH = 2048;
  private final String keyPath = "tmpKey";
  private final String algorithm = "RSA";

  /**
   * Initializes the key required for encrypting/decrypting data
   */
  public SecurityUtils() {
    //Creating KeyPair generator object
    try {
      try {
        this.key = this.loadKeyPair(algorithm);
      } catch (FileNotFoundException e) {
        KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance(algorithm);
        keyPairGen.initialize(CRYPT_KEY_LENGTH);
        this.key = keyPairGen.generateKeyPair();
        this.saveKeyPair(this.key);
      }
    } catch (Exception e) {
      e.printStackTrace();
      throw new RuntimeException();
    }
  }

  /**
   * Returns the decrypted version of user input
   * @param encryptedInfo the bytes of the info we want to decrypt
   * @return the unencrypted version of encryptedInfo as a String.
   *         It will have a length of 2048
   */
  public String decrypt(byte[] encryptedInfo) {
    try {
      Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
      cipher.init(Cipher.DECRYPT_MODE, key.getPrivate());

      //Add data to the cipher
      cipher.update(encryptedInfo);

      //decrypting the data
      byte[] decipheredText = cipher.doFinal();
      String info = new String(decipheredText);
      return info;

    } catch (Exception e) {
      throw new RuntimeException();
    }
  }

  /**
   * Returns the encrypted version of user input
   * @param info the bytes of the info we want to encrypt
   * @return the encrypted version of info as byte[].
   *          It will have a length of 2048
   */
  public byte[] encrypt(String info) {
    try {
      Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
      cipher.init(Cipher.ENCRYPT_MODE, key.getPublic());

      //Decrypting the text
      byte[] input = info.getBytes();
      cipher.update(input);
      byte[] cipheredText = cipher.doFinal();
      return cipheredText;

    } catch (Exception e) {
      throw new RuntimeException();
    }
  }

  /**
   * Generates a cryptographically-secure salted password.
   * @param password non-encrypted password.
   * @return byte[] containing encrypted form of password
   */
  public static byte[] hashPassword(String password) {
    byte[] salt = generateSalt();
    byte[] saltedHash = generateSaltedPassword(password, salt);

    //adds salt to end of hashPassword
    byte[] hashPassword = new byte[KEY_LENGTH+SALT_LENGTH];
    for(int i = 0; i < KEY_LENGTH; i++) {
      hashPassword[i] = saltedHash[i];
    }
    for(int i = 0; i < SALT_LENGTH; i++) {
      hashPassword[i+KEY_LENGTH] = salt[i];
    }

    return hashPassword;
  }

  /**
   * Verifies whether the plaintext password can be hashed to provided salted hashed password.
   * @param plaintext non-encrypted form of password to compare.
   * @param saltedHashed encrypted form of password to compare.
   * @return true if passwords match, otherwise false
   */
  public static boolean plaintextMatchesHash(String plaintext, byte[] saltedHashed) {
    // extract the salt from the byte array (i.e., undo the logic implemented in
    // hashPassword), then use the salt to salt the plaintext and check whether
    // it matches the password hash.
    byte[] salt = new byte[SALT_LENGTH];
    for(int i = 0; i < SALT_LENGTH; i++) {
      salt[i] = saltedHashed[i+KEY_LENGTH];
    }
    byte[] plaintextHashed = generateSaltedPassword(plaintext, salt);
    for(int i = 0; i < KEY_LENGTH; i++) {
      if(plaintextHashed[i] != saltedHashed[i]) {
        return false;
      }
    }
    return true;
  }

  // Password hashing parameter constants.

  // The higher the hash_strength, the less likely there's a collision
  private static final int HASH_STRENGTH = 65536;
  // The length of encrypted password
  private static final int KEY_LENGTH = 128;
  // The length of salt part of encrypted password
  private static final int SALT_LENGTH = 16;

  /**
   * Generate a small bit of randomness to serve as a password "salt"
   * @return a random byte array representing the salt part of the encrypted password
   */
  private static byte[] generateSalt() {
    byte[] salt = new byte[SALT_LENGTH];
    Random rand = new Random();
    rand.nextBytes(salt);
    return salt;
  }

  /**
   * Uses the provided salt to generate a cryptographically-secure hash of the provided password.
   * The resultant byte array should be KEY_LENGTH bytes long.
   * @param password non-encrypted password
   * @param salt random btye[] used to generate encrypted password
   * @return byte[] containing the encrypted password generated from password and salt
   */
  private static byte[] generateSaltedPassword(String password, byte[] salt)
    throws IllegalStateException {
    // Specify the hash parameters, including the salt
    KeySpec spec = new PBEKeySpec(password.toCharArray(), salt,
                                  HASH_STRENGTH, KEY_LENGTH * 8 /* length in bits */);

    // Hash the whole thing
    SecretKeyFactory factory = null;
    byte[] hash = null;
    try {
      factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
      hash = factory.generateSecret(spec).getEncoded();
      return hash;
    } catch (NoSuchAlgorithmException | InvalidKeySpecException ex) {
      throw new IllegalStateException();
    }
  }

  private void saveKeyPair(KeyPair keyPair) throws IOException {
    PrivateKey privateKey = keyPair.getPrivate();
    PublicKey publicKey = keyPair.getPublic();

    // Store Public Key.
    X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(
            publicKey.getEncoded());
    File publicKeyFile = new File(keyPath + "/public.key");
    publicKeyFile.createNewFile();
    File privateKeyFile = new File(keyPath + "/private.key");
    privateKeyFile.createNewFile();

    FileOutputStream fos = new FileOutputStream(keyPath + "/public.key");
    fos.write(x509EncodedKeySpec.getEncoded());
    fos.close();

    // Store Private Key.
    PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(
            privateKey.getEncoded());
    fos = new FileOutputStream(keyPath + "/private.key");
    fos.write(pkcs8EncodedKeySpec.getEncoded());
    fos.close();
  }

  private KeyPair loadKeyPair(String algorithm)
          throws IOException, NoSuchAlgorithmException,
          InvalidKeySpecException {
    // Read Public Key.
    File filePublicKey = new File(keyPath + "/public.key");
    FileInputStream fis = new FileInputStream(keyPath + "/public.key");
    byte[] encodedPublicKey = new byte[(int) filePublicKey.length()];
    fis.read(encodedPublicKey);
    fis.close();

    // Read Private Key.
    File filePrivateKey = new File(keyPath + "/private.key");
    fis = new FileInputStream(keyPath + "/private.key");
    byte[] encodedPrivateKey = new byte[(int) filePrivateKey.length()];
    fis.read(encodedPrivateKey);
    fis.close();

    // Generate KeyPair.
    KeyFactory keyFactory = KeyFactory.getInstance(algorithm);
    X509EncodedKeySpec publicKeySpec = new X509EncodedKeySpec(
            encodedPublicKey);
    PublicKey publicKey = keyFactory.generatePublic(publicKeySpec);

    PKCS8EncodedKeySpec privateKeySpec = new PKCS8EncodedKeySpec(
            encodedPrivateKey);
    PrivateKey privateKey = keyFactory.generatePrivate(privateKeySpec);

    return new KeyPair(publicKey, privateKey);
  }
}
