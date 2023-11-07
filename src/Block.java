import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.nio.ByteBuffer;

/**
 * An implementation of a block with all the necessary information.
 * 
 * @author Alma Ordaz
 * @author Madel Sibal
 * 
 * Documentation from the reading "Mini-Project 7: Blockchains"
 */

public class Block {

  // +--------+------------------------------------------------------
  // | Fields |
  // +--------+

 /*
  * The number of the block in the blockchain
  */
  Integer index;

 /*
  * The amount transferred between the two parties
  */
  Integer amount;

 /*
  * The hash of the previous block in the chain
  */
  Hash prev;

 /*
  * The nonce
  */
  Long nonce;

 /*
  * The hash of this block
  */
  Hash current;

 /*
  * Creates a new block from the specified parameters, 
  * performing the mining operation to discover the nonce and hash for this block given these parameters
  */
  public Block(int num, int amount, Hash prevHash) throws NoSuchAlgorithmException {
      this.index = num;
      this.amount = amount;
      this.prev = prevHash;
      mineBlock();
  }

 /*
  * Creates a new block from the specified parameters, 
  * using the provided nonce and additional parameters to generate the hash for the block
  */
  public Block(int num, int amount, Hash prevHash, long nonce) throws NoSuchAlgorithmException {
      this.index = num;
      this.amount = amount;
      this.prev = prevHash;
      this.nonce = nonce;
      calculateHash();
  }

  // +----------------+----------------------------------------------
  // | Public Methods |
  // +----------------+

  /*
   * Returns the number of this block
   */
  public int getNum() {
      return this.index;
  }

  /*
   * Returns the amount transferred that is recorded in this block
   */
  public int getAmount() {
      return this.amount;
  }

  /*
   * Returns the nonce of this block
   */
  public long getNonce() {
      return this.nonce;
  }

  /*
   * Returns the hash of the previous block in the blockchain
   */
  public Hash getPrevHash() {
      return this.prev;
  }

  /*
   * Returns the hash of this block
   */
  public Hash getHash() {
      return this.current;
  }

  /*
   * Returns a string representation of the block
   */
  public String toString() {
      return "Block " + this.index + " (Amount: " + this.amount + ", Nonce: " + this.nonce + ", prevHash: " + this.prev + ", hash: " + this.current;
  }

  /*
   * Mines the current block by finding a valid 
   * nonce that results in a hash with a specific pattern.
   */
  private void mineBlock() throws NoSuchAlgorithmException {
      MessageDigest md = MessageDigest.getInstance("SHA-256");
      ByteBuffer buffer = ByteBuffer.allocate(16); // 4 bytes for index, 4 bytes for amount, and 8 bytes for nonce

      while (true) {
          buffer.clear();
          buffer.putInt(this.index);
          buffer.putInt(this.amount);
          buffer.putLong(this.nonce);

          byte[] input = buffer.array();
          md.update(input);

          byte[] hashBytes = md.digest();

          if (isHashValid(hashBytes)) {
              this.current = new Hash(hashBytes);
              break;
          }

          this.nonce++;
      }
  } // toString

  /*
   * Calculates the hash of the current block without mining.
   */
  private void calculateHash() throws NoSuchAlgorithmException {
      MessageDigest md = MessageDigest.getInstance("SHA-256");
      ByteBuffer buffer = ByteBuffer.allocate(16); // 4 bytes for index, 4 bytes for amount, and 8 bytes for nonce

      buffer.putInt(this.index);
      buffer.putInt(this.amount);
      buffer.putLong(this.nonce);

      byte[] input = buffer.array();
      md.update(input);

      byte[] hashBytes = md.digest();
      this.current = new Hash(hashBytes);
  } // calculateHash


  /*
   * Checks whether a given hash is valid.
   */
  private boolean isHashValid(byte[] hash) {
      for (int i = 0; i < 4; i++) {
          if (hash[i] != 0) {
              return false;
          }
      }
      return true;
  } // isHashValid
} // class Block
