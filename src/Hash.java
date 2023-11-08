import java.util.Arrays;

/**
 * An implementation of a hash function, which is a mapping from 
 * values of a data type of arbitrary size to a fixed size bit string.
 * 
 * @author Alma Ordaz
 * @author Madel Sibal
 * 
 * Documentation from the reading "Mini-Project 7: Blockchains"
 */

public class Hash {

  // +--------+------------------------------------------------------
  // | Fields |
  // +--------+

  /**
   * Stores the hash data associated with each Hash object.
   */
  private byte[] hash;

  // +----------------+----------------------------------------------
  // | Public Methods |
  // +----------------+

  /**
   * Constructs a new Hash object that contains 
   * the given hash (as an array of bytes).
   */
  public Hash(byte[] data) {
      this.hash = data;
  } // Hash(byte[] data)

  /**
   * Returns the hash contained in the object.
   */
  public byte[] getData() {
      return hash;
  } // getData()

  /**
   * Returns true if hash meets the criteria for validity.
   */
  public boolean isValid() {
      return hash[0] == 0 && hash[1] == 0 && hash[2] == 0;
  } // isValid()

  /**
   * Returns the string representation of the hash as 
   * a string of hexadecimal digits, 2 digits per byte.
   */
  public String toString() {
    String result = "";
    for(int i = 0; i < this.hash.length; i++){
        result += String.format("%02X", this.hash[i]);
    }
      return result;
  } // toString()

  /**
   * Returns true if this hash is structurally equal to the argument.
   */
  public boolean equals(Object other) {
      if (this == other) {
          return true;
      }

      if (other == null || getClass() != other.getClass()) {
          return false;
      }

      Hash otherHash = (Hash) other;

      return Arrays.equals(hash, otherHash.hash);
  } // equals(Object other)

public boolean startsWith(String string) {
    return false;
}
} // class Hash