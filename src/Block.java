import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * An implementation of a block with all the necessary information
 * 
 * @author Alma Ordaz
 * @author Madel Sibal
 * 
 *         Documentation from the reading "Mini-Project 7: Blockchains"
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
  Long nounce;

  /*
   * The hash of this block
   */
  Hash current;

  // +--------------+------------------------------------------------
  // | Constructors |
  // +--------------+

  /*
   * creates a new block from the specified parameters, 
   * performing the mining operation to discover the nonce and hash for this block given these parameters
   */
  public Block(int num, int amount, Hash prevHash)throws NoSuchAlgorithmException{
    this.index = num;
    this.amount = amount;
    this.prev = prevHash;
    //this.nounce = ;

    MessageDigest md = MessageDigest.getInstance("sha-256");
    md.update(this.index.byteValue());
    md.update(this.amount.byteValue());
    md.update(this.prev.getData());
    md.update(this.nounce.byteValue());

    this.current = new Hash(md.digest());



  }//Block(int, int, Hash)

  /*
   * creates a new block from the specified parameters, 
   * using the provided nonce and additional parameters to generate the hash for the block
   */
  public Block(int num, int amount, Hash prevHash, long nonce) throws NoSuchAlgorithmException{
    this.index = num;
    this.amount = amount;
    this.prev = prevHash;
    this.nounce = nonce;

    MessageDigest md = MessageDigest.getInstance("sha-256");
    md.update(this.index.byteValue());
    md.update(this.amount.byteValue());
    md.update(this.prev.getData());
    md.update(this.nounce.byteValue());

    this.current = new Hash(md.digest());

  }//Block(int, int, Hash, long)

  // +----------------+----------------------------------------------
  // | Public Methods |
  // +----------------+

  /*
   * returns the number of this block
   */
  public int getNum(){
    return this.index;
  }//getNum()

  /*
   * returns the amount transferred that is recorded in this block
   */
  public int getAmount(){
    return this.amount;
  }//getAmount()

  /*
   * returns the nonce of this block
   */
  public long getNonce(){
    return this.nounce;
  }//getNonce()

  /*
   * returns the hash of the previous block in the blockchain
   */
  public Hash getPrevHash(){
    return this.prev;
  }//getPrevHash()

  /*
   * returns the hash of this block
   */
  public Hash getHash(){
    return this.current;
  }//getHash

  /*
   * returns a string representation of the block
   */
  public String toString(){
    return "Block " + this.index + " (Amount: " + this.amount + ", Nounce: " + this.nounce + ", prevHash: " 
            + this.prev + ", hash: " + this.current;
  }//toString

}// class Block