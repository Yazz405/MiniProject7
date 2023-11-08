import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.io.PrintWriter;
import java.nio.ByteBuffer;
import java.util.Random;

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
      this.current = calculateHash(this.index, this.amount, this.prev, this.nonce);
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
    Random rand = new Random();


    while (true) {
        this.nonce = rand.nextLong();
        Hash test = calculateHash(this.index, this.amount, this.prev, this.nonce);

      if (test.isValid()) {
        this.current = test;
        break;
      }//if
    }//while
  }// mineBlock() 

  /*
   * calculates hash
   */
  public Hash calculateHash(Integer index, Integer amount, Hash prevHash,Long nonce) throws NoSuchAlgorithmException{
    MessageDigest md = MessageDigest.getInstance("SHA-256");
    ByteBuffer buffer = ByteBuffer.allocate(16);// 4 bytes for index, 4 bytes for amount, and 8 bytes for nonce
    buffer.putInt(this.index);
    buffer.putInt(this.amount);
    if(prevHash != null){
        buffer.put(prevHash.getData());
    }
    buffer.putLong(this.nonce);
    byte[] input = buffer.array();
    md.update(input);

    return new Hash(md.digest());
  }// calculateHash()

  public static void main(String[] args) throws NoSuchAlgorithmException{
     PrintWriter pen = new PrintWriter(System.out, true);
     Block blk = new Block(0, 300, null);
     pen.println(blk.toString());
  }
  
} // class Block
