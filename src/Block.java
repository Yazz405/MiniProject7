import java.nio.ByteBuffer;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

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

    /**
     * Creates a new block from the specified parameters, performing the mining 
     * operation to discover the nonce and hash for this block given these parameters
     */
    public Block(int num, int amount, Hash prevHash) {
        this.num = num;
        this.amount = amount;
        this.prevHash = prevHash;
        mineBlock();
    }

    /**
     * Creates a new block from the specified parameters, 
     * using the provided nonce and additional parameters to generate the hash for the block
     */
    public Block(int num, int amount, Hash prevHash, long nonce) {
        this.num = num;
        this.amount = amount;
        this.prevHash = prevHash;
        this.nonce = nonce;
        calculateHash();
    }

    // +----------------+----------------------------------------------
    // | Public Methods |
    // +----------------+

    /**
     * Returns the number of this block
     */
    public int getNum() {
        return num;
    }

    /**
     * Returns the amount transferred that is recorded in this block
     */
    public int getAmount() {
        return amount;
    }

    /**
     * Returns the nonce of this block
     */
    public long getNonce() {
        return nonce;
    }

    /**
     * Returns the hash of the previous block in the blockchain
     */
    public Hash getPrevHash() {
        return prevHash;
    }

    /**
     * Returns the hash of this block
     */
    public Hash getHash() {
        return hash;
    }

    /**
     * Mines the current block by finding a valid 
     * nonce that results in a hash with a specific pattern.
     */
    private void mineBlock() {
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("SHA-256 is not available.");
        }

        ByteBuffer buffer = ByteBuffer.allocate(16);
        buffer.putInt(num);
        buffer.putInt(amount);
        if (prevHash != null) {
            md.update(prevHash.getData());
        }

        long currentNonce = 0;
        while (true) {
            buffer.putLong(currentNonce);
            byte[] data = buffer.array();
            md.update(data);
            byte[] blockHash = md.digest();
            if (isValidHash(blockHash)) {
                nonce = currentNonce;
                hash = new Hash(blockHash);
                break;
            }
            currentNonce++;
            buffer.clear();
        }
    }

    /**
     * Calculates hash
     */
    private void calculateHash() {
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("SHA-256 is not available.");
        }

        ByteBuffer buffer = ByteBuffer.allocate(16); // For integers and long
        buffer.putInt(num);
        buffer.putInt(amount);
        if (prevHash != null) {
            md.update(prevHash.getData());
        }
        buffer.putLong(nonce);
        byte[] data = buffer.array();
        md.update(data);
        byte[] blockHash = md.digest();
        hash = new Hash(blockHash);
    }

    /**
     * Checks whether a given hash is considered valid
     */
    private boolean isValidHash(byte[] hash) {
        return hash[0] == 0 && hash[1] == 0 && hash[2] == 0;
    }

    /**
     * Returns a string representation of the block
     */
    public String toString() {
        return "Block " + num + " (Amount: " + amount + ", Nonce: " + nonce + ", prevHash: " + (prevHash != null ? prevHash.toString() : "null") + ", hash: " + hash.toString() + ")";
    }
}
