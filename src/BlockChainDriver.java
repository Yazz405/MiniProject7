import java.util.Scanner;

/**
 * An implementation of BlockChainDriver, which allows users to interact with
 * a blockchain by providing a command-line interface for managing and
 * monitoring
 * blockchain operations.
 * 
 * @author Alma Ordaz
 * @author Madel Sibal
 * 
 *         Documentation from the reading "Mini-Project 7: Blockchains"
 */
public class BlockChainDriver {
  /**
   * Processes command-line arguments, initializes the blockchain,
   * and provides an interactive menu for users to manage the blockchain.
   */
  public static void main(String[] args) {
    if (args.length != 1) {
      System.out.println("Usage: java BlockChainDriver <initial_amount>");
      System.exit(1);
    }

    int initialAmount = Integer.parseInt(args[0]);
    BlockChain blockchain = new BlockChain(initialAmount);
    System.out.println(blockchain.toString());

    Scanner scanner = new Scanner(System.in);

    while (true) {
      System.out.print("Enter a command: ");
      String command = scanner.nextLine();

      switch (command) {
        case "mine":
          mineBlock(blockchain, scanner);
          break;
        case "append":
          appendBlock(blockchain, scanner);
          break;
        case "remove":
          removeLastBlock(blockchain);
          break;
        case "check":
          checkBlockchain(blockchain);
          break;
        case "report":
          reportBalances(blockchain);
          break;
        case "help":
          printHelp();
          break;
        case "quit":
          scanner.close();
          System.exit(0);
        default:
          System.out.println("Invalid command. Type 'help' for a list of commands.");
      }

      System.out.println("\nCurrent Blockchain:");
      System.out.println(blockchain.toString());
    }
  }// main()

  /*
   * Gets information from the user, and uses that to call the correct method to
   * mine for a block
   */
  private static void mineBlock(BlockChain blockchain, Scanner scanner) {
    System.out.print("Enter transaction amount for mining: ");
    int amount = scanner.nextInt();
    scanner.nextLine();

    Block blk = blockchain.mine(amount);

    System.out.println("Amount: " + blk.getAmount() + ", Nounce: " + blk.getNonce());
  }// mineBlock()

  /*
   * Gets information from the user, and uses that to call the correct method to
   * append a block to the blockchain
   */
  private static void appendBlock(BlockChain blockchain, Scanner scanner) {
    System.out.print("Enter transaction amount for appending: ");
    int amount = scanner.nextInt();
    System.out.print("Enter nonce for appending: ");
    long nonce = scanner.nextLong();
    scanner.nextLine();

    Block newBlock = new Block(blockchain.getSize(), amount, blockchain.getHash(), nonce);
    try {
      blockchain.append(newBlock);
    } catch (IllegalArgumentException e) {
      System.out.println("Error: " + e.getMessage());
    }
  }// appendBlock()

  /*
   * calls the correct method to remove the las block and prints a statement
   * accordingly
   */
  private static void removeLastBlock(BlockChain blockchain) {
    if (blockchain.removeLast()) {
      System.out.println("Last block removed.");
    } else {
      System.out.println("Cannot remove the last block. The blockchain only contains a single block.");
    }
  }// removeLastBlock()

  /*
   * Calls the correct method to check the validity of the BlockChain and prints
   * a statement accordingly
   */
  private static void checkBlockchain(BlockChain blockchain) {
    if (blockchain.isValidBlockChain()) {
      System.out.println("The blockchain is valid.");
    } else {
      System.out.println("The blockchain is not valid.");
    }
  }// checkBlockChain()

  /*
   * calls the correct method to report the balances to the user
   */
  private static void reportBalances(BlockChain blockchain) {
    blockchain.printBalances();
  }// reportBalances

  /*
   * prints a list of commands
   */
  private static void printHelp() {
    System.out.println("Blockchain Commands:");
    System.out.println("mine - Discover nonce for a transaction");
    System.out.println("append - Append a new block");
    System.out.println("remove - Remove the last block");
    System.out.println("check - Check if the blockchain is valid");
    System.out.println("report - Report balances of Alexis and Blake");
    System.out.println("help - Print this list of commands");
    System.out.println("quit - Quit the program");
  }// printHelp()
}// class BlockChainDriver
