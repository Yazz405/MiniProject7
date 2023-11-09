/**
 * An implementation of BlockChain, a singly-linked
 * structure with a first and last pointer.
 * 
 * @author Alma Ordaz
 * @author Madel Sibal
 * 
 *         Documentation from the reading "Mini-Project 7: Blockchains"
 */
public class BlockChain {

  // +--------+------------------------------------------------------
  // | Fields |
  // +--------+

  /**
   * The first block in the BlockChain
   */
  private Node first;

  /**
   * The most recent block added to the BlockChain
   */
  private Node last;

  /**
   * The number of blocks in the BlockChain
   */
  private int size;

  // +--------------+------------------------------------------------
  // | Constructors |
  // +--------------+

  /**
   * Creates a BlockChain that possesses a single block
   * that starts with the given initial amount
   */
  public BlockChain(int initial) {
    Block initialBlock = new Block(0, initial, null);
    first = new Node(initialBlock);
    last = first;
    size = 1;
  }

  // +----------------+----------------------------------------------
  // | Public Methods |
  // +----------------+

  /**
   * Mines a new candidate block to be added to the end of the chain
   */
  public Block mine(int amount) {
    if (size == 0) {
      throw new IllegalStateException("Cannot mine a block without an initial block.");
    }

    Block prevBlock = last.getBlock();
    Block newBlock = new Block(size, amount, prevBlock.getHash());

    return newBlock;
  }

  /**
   * Returns the size of the blockchain
   */
  public int getSize() {
    return size;
  }

  /**
   * Adds this block to the list
   */
  public void append(Block blk) {
    last.next = new Node(blk);
    last = last.next;
    size++;
  }

  /**
   * Removes the last block from the chain, returning true.
   * If the chain only contains 1 block, then nothing happens, and false is
   * returned.
   */
  public boolean removeLast() {
    if (size <= 1) {
      return false;
    }

    Node current = first;
    while (current.next != last) {
      current = current.next;
    }

    last = current;
    last.next = null;
    size--;

    return true;
  }

  /**
   * Returns the hash of the last block in the chain
   */
  public Hash getHash() {
    if (size == 0) {
      return null;
    }
    return last.getBlock().getHash();
  }

  /**
   * Walks the blockchain and ensures that its blocks are consistent and valid
   */
  public boolean isValidBlockChain() {
    Node current = first;
    Block prevBlock = null;

    while (current != null) {
      Block block = current.getBlock();

      if (prevBlock != null && !prevBlock.getHash().equals(block.getPrevHash())) {
        return false;
      }

      prevBlock = block;
      current = current.next;
    }

    return true;
  }

  /**
   * Prints Alexis’s and Blake’s respective balances
   */
  public void printBalances() {
    int alexisBalance = 0;
    int blakeBalance = 0;

    Node current = first;
    while (current != null) {
      Block block = current.getBlock();
      if (block.getAmount() > 0) {
        alexisBalance += block.getAmount();
      } else {
        blakeBalance -= block.getAmount();
        alexisBalance += block.getAmount();
      }
      current = current.next;
    }

    System.out.println("Alexis: " + alexisBalance + ", Blake: " + blakeBalance);
  }

  /**
   * Returns a string representation of the BlockChain
   */
  public String toString() {
    StringBuilder result = new StringBuilder();
    Node current = first;

    while (current != null) {
      result.append(current.getBlock()).append("\n");
      current = current.next;
    }

    return result.toString();
  }

  // +---------------+-----------------------------------------------------
  // | Inner Classes |
  // +---------------+

  /**
   * Nodes in the linked list.
   */
  private class Node {

    // +--------+-----------------------------------------------------------
    // | Fields |
    // +--------+

    /**
     * The data stored in the node.
     */
    private Block block;

    /**
     * The next node in the list. Set to null at the end of the list.
     */
    private Node next;

    // +--------------+-----------------------------------------------------
    // | Constructors |
    // +--------------+

    /**
     * Create a new node with specified data and next.
     */
    Node(Block block) {
      this.block = block;
      this.next = null;
    }

    /**
     * Retrieves the block contained within this Node.
     */
    Block getBlock() {
      return block;
    }
  }// class Node
}// class BlockChain