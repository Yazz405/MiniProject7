import java.io.PrintWriter;
import java.util.Scanner;

/*
 * @author Alma Ordaz
 * @author Madel Sibal
 */
public class BlockChainDriver {
  
  public static void main(String[] args){
    PrintWriter pen = new PrintWriter(System.out, true);
    Scanner input = new Scanner(System.in);

    String exp;
    while(true){
      exp = input.nextLine();

      if(exp.equals("mine")){

      }else if(exp.equals("append")){

      }else if(exp.equals("remove")){

      }else if(exp.equals("check")){

      }else if(exp.equals("report")){

      }else if(exp.equals("help")){
        pen.println("Valid commands: \n\tmine: discovers the nonce for a given transaction");
        pen.println("\tappend: appends a new block onto the end of the chain");
        pen.println("\tremove: removes the last block from the end of the chain");
        pen.println("\tcheck: checks that the block chain is valid");
        pen.println("\treport: reports the balances of Alexis and Blake");
        pen.println("\thelp: prints this list of commands");
        pen.println("\tquit: quits the program");
      }else if(exp.equals("quit")){
        break;
      }else {
        System.err.println("Not a valid command, enter help for a list of commands");
      }// else if 
    }// while

  }// main
}// class BlockChainDriver
