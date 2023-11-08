import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;

public class BlockChain {
  // +--------+------------------------------------------------------
  // | Fields |
  // +--------+

  /*
   * The first block in the BlockChain
   */
  Node first;

  /*
   * the most recent block added to the BlockChain
   */
  Node last;

  // +--------------+------------------------------------------------
  // | Constructors |
  // +--------------+

  /*
   * creates a BlockChain that possess a single
   * block the starts with the given initial amount
   */
  public BlockChain(int initial) {
    try{
    Block blk = new Block(0, initial, null);
    this.first = new Node(blk, null);
    this.last = this.first;
    } catch (Exception e){
      System.err.println("noSuchAlgorithm");
    }
  }// BlockChain(int)

  // +----------------+----------------------------------------------
  // | Public Methods |
  // +----------------+

  /*
   * mines a new candidate block to be added to the end of the chain
   */
  public Block mine(int amount){
    try{
      Block blk = new Block(this.last.data.getNum() + 1, amount, this.last.data.getHash());
      return blk;
    }catch (Exception e){
      System.err.println("noSuchAlgorithm");
      return null;
    }

  }// mine(int)

  /*
   * returns the size of the blockchain
   */
  public int getSize() {
    return this.last.data.getNum();
  }// getSize()

  /*
   * adds this block to the list
   */
  public void append(Block blk) throws IllegalArgumentException{
    this.last.next = new Node(blk, null);
    this.last = this.last.next;
  }// append(Block)

  /*
   * removes the last block from the chain, returning true.
   * If the chain only contains 1 block, then nothing happens and false is returned
   */
  public boolean removeLast(){
    if(this.last.data.getNum() == 0){
      return false;
    } else {
      //stub
      return true;
    }
  }// removeLast()

  /*
   * returns the hash of the last block in the chain
   */
  public Hash getHash(){
    return this.last.data.getHash();
  }// getHash

  /*
   * walks the blockchain and ensures that its blocks are consistent and valid
   */
  public boolean isValidBlockChain(){
    return false;
    //stub
  }// isValidBlockChain

  /*
   * prints Alexis’s and Blake’s respective balances
   */
  public void printBalances(){

  }// printBalances

  /*
   * returns a string representation of the BlockChain
   */
  public String toString(){
    String result = "";
    Node chain = this.first;
    for(int i = 0; i < this.last.data.getNum(); i++){
      result = chain.data.toString();
      chain = chain.next;
    }
    return result;
  }// toString()

}// class BlockChain

// +---------------+-----------------------------------------------------
// | Inner Classes |
// +---------------+

/**
 * Nodes in the linked list.
 */
class Node {
  // +--------+-----------------------------------------------------------
  // | Fields |
  // +--------+

  /**
   * The data stored in the node.
   */
  Block data;

  /**
   * The next node in the list. Set to null at the end of the list.
   */
  Node next;

  // +--------------+-----------------------------------------------------
  // | Constructors |
  // +--------------+

  /**
   * Create a new node with specified data and next.
   */
  public Node(Block data, Node next) {
    this.data = data;
    this.next = next;
  } // Node(T, Node)
} // class Node
