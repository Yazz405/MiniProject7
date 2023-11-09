import java.nio.ByteBuffer;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * An implementation of a block with all the necessary information.
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

  /**
   * The number of the block in the blockchain
   */
  private int num;

  /**
   * The amount transferred between the two parties
   */
  private int amount;

  /**
   * The hash of the previous block in the chain
   */
  private Hash prevHash;

  /**
   * The nonce
   */
  private long nonce;

  /**
   * The hash of this block
   */
  private Hash hash;

  // +--------------+------------------------------------------------
  // | Constructors |
  // +--------------+

  /**
   * Creates a new block from the specified parameters, performing the mining
   * operation to discover the nonce and hash for this block given these
   * parameters
   */
  public Block(int num, int amount, Hash prevHash) {
    this.num = num;
    this.amount = amount;
    this.prevHash = prevHash;
    mineBlock();
  }

  /**
   * Creates a new block from the specified parameters,
   * using the provided nonce and additional parameters to generate the hash for
   * the block
   */
  public Block(int num, int amount, Hash prevHash, long nonce) {
    this.num = num;
    this.amount = amount;
    this.prevHash = prevHash;
    this.nonce = nonce;

    Hash correctHash;

    try {
      correctHash = calculateHash(this.num, this.amount, this.prevHash, this.nonce);
    } catch (NoSuchAlgorithmException e) {
      throw new RuntimeException("SHA-256 is not available.");
    }

    this.hash = correctHash;
  }

  // +----------------+----------------------------------------------
  // | Public Methods |
  // +----------------+

  /**
   * Returns the number of this block
   */
  public int getNum() {
    return num;
  }// getNum

  /**
   * Returns the amount transferred that is recorded in this block
   */
  public int getAmount() {
    return amount;
  }// getAmount

  /**
   * Returns the nonce of this block
   */
  public long getNonce() {
    return nonce;
  }// getNonce

  /**
   * Returns the hash of the previous block in the blockchain
   */
  public Hash getPrevHash() {
    return prevHash;
  }// getPrevHash

  /**
   * Returns the hash of this block
   */
  public Hash getHash() {
    return hash;
  }// getHash

  /**
   * Mines the current block by finding a valid
   * nonce that results in a hash with a specific pattern.
   */
  private void mineBlock() {
    long currentNonce = 0;

    while (true) {
      Hash currentHash;
      try {
        currentHash = calculateHash(this.num, this.amount, this.prevHash, currentNonce);
      } catch (NoSuchAlgorithmException e) {
        throw new RuntimeException("SHA-256 is not available.");
      }

      if (currentHash.isValid()) {
        this.nonce = currentNonce;
        this.hash = currentHash;
        break;
      } // if
      currentNonce++;
    } // while
  }// mineBlock()

  /*
   * calculates a Hash with the given inputs
   */
  public Hash calculateHash(Integer index, Integer amount, Hash prevHash, Long nonce) throws NoSuchAlgorithmException {
    MessageDigest md = MessageDigest.getInstance("SHA-256");
    ByteBuffer buffer1 = ByteBuffer.allocate(8);// 4 bytes for index, 4 bytes for amount, and 8 bytes for nonce
    ByteBuffer buffer2 = ByteBuffer.allocate(8);

    buffer1.putInt(index);
    buffer1.putInt(amount);
    md.update(buffer1.array());
    buffer1.clear();

    if (prevHash != null) {
      md.update(prevHash.getData());
    }

    buffer2.putLong(nonce);
    md.update(buffer2.array());
    buffer2.clear();

    return new Hash(md.digest());
  }// calculateHash()

  /**
   * Returns a string representation of the block
   */
  public String toString() {
    return "Block " + num + " (Amount: " + amount + ", Nonce: " + nonce + ", prevHash: "
        + (prevHash != null ? prevHash.toString() : "null") + ", hash: " + hash.toString() + ")";
  }// toString()
}// class Block
