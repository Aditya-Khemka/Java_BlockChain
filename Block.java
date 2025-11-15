import java.util.Date;

public class Block {

    public String hash;
    public String previousHash;
    private String data;
    private long timeStamp;
    private int nonce;          //  counter

    // Block Constructor.
    public Block(String data, String previousHash) {
    	System.out.println("Mining Block.....");
        this.data = data;
        this.previousHash = previousHash;
        this.timeStamp = new Date().getTime();
        this.nonce = 0;
        this.hash = calculateHash(); // initial hash (nonce = 0)
    }

    // Getters / setters 
    public String getHash() { return hash; }
    public String getPreviousHash() { return previousHash; }
    public String getData() { return data; }
    public long getTimeStamp() { return timeStamp; }
    public int getNonce() { return nonce; }

    //  hash calculation 
    public String calculateHash() {
        String input = previousHash + data + Long.toString(timeStamp) + Integer.toString(nonce);
        return StringUtil.applySha256(input);
    }

    // Proof-of-Work / mining: find a nonce such that hash starts with difficulty zeros
    public void mineBlock(int difficulty) {
        String target = StringUtil.getDifficultyString(difficulty); // e.g., "0000"
        // Keep incrementing nonce until the hash has required leading zeros
        while (!hash.substring(0, difficulty).equals(target)) {
            nonce++;
            hash = calculateHash();
        }
        // Block mined: hash and nonce are final for this block
        // System.out.println("Block mined. nonce: " + nonce + " Hash: " + hash);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{\n");
        sb.append("  Data               = ").append(data).append(",\n");
        sb.append("  PreviousBlockHash  = ").append(previousHash).append(",\n");
        sb.append("  CurrentBlockHash   = ").append(hash).append(",\n");
        sb.append("  TimeStamp          = ").append(timeStamp).append(",\n");
        sb.append("  Nonce              = ").append(nonce).append("\n");
        sb.append("}\n");
        return sb.toString();
    }
}
