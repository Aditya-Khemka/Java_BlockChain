import java.util.ArrayList;
import java.util.Scanner;

public class BlockChainCLI {

    public static ArrayList<Block> blockchain = new ArrayList<>();
    public static int difficulty = 5;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // create genesis block
        Block genesis = new Block("First Ever Block", "0");
        genesis.mineBlock(difficulty);
        blockchain.add(genesis);

        while (true) {
            System.out.println("\n\n\n===== BLOCKCHAIN MENU =====");
            System.out.println("1. Add new block");
            System.out.println("2. Print entire blockchain");
            System.out.println("3. Validate blockchain");
            System.out.println("4. Exit");
            System.out.println("Choose an option: ");

            int choice;
            try {
                choice = Integer.parseInt(sc.nextLine().trim());
            } catch (Exception e) {
                System.out.println("Invalid input. Enter a number from 1-4.");
                continue;
            }

            switch (choice) {
                case 1:
                    addBlock(sc);
                    break;
                case 2:
                    printChain();
                    break;
                case 3:
                    validateChain();
                    break;
                case 4:
                    System.out.println("\nThank you . All data erased");
                    sc.close();
                    return;
                default:
                    System.out.println("Invalid choice! Try again.");
            }
        }
    }

    public static void addBlock(Scanner sc) {
        System.out.print("Enter data for new block: ");
        String data = sc.nextLine();
        String prevHash = blockchain.get(blockchain.size() - 1).getHash();
        Block newBlock = new Block(data, prevHash);
        newBlock.mineBlock(difficulty);
        blockchain.add(newBlock);
        System.out.println("Block added successfully!");
        System.out.println(newBlock.toString());
    }

    public static void printChain() {
        System.out.println("\n");
        for (int i = 0; i < blockchain.size(); i++) {
            System.out.println("Block " + i + "\n" + blockchain.get(i).toString());
        }
    }

    public static void validateChain() {
        for (int i = 1; i < blockchain.size(); i++) {
            Block current = blockchain.get(i);
            Block previous = blockchain.get(i - 1);

            if (!current.getHash().equals(current.calculateHash())) {
                System.out.println("Block " + i + " has been tampered!");
                return;
            }
            if (!current.getPreviousHash().equals(previous.getHash())) {
                System.out.println("Block " + i + " is not linked to previous correctly!");
                return;
            }
            String target = StringUtil.getDifficultyString(difficulty);
            if (!current.getHash().substring(0, difficulty).equals(target)) {
                System.out.println("Block " + i + " has not been mined to required difficulty!");
                return;
            }
        }
        System.out.println("Blockchain is valid");
    }
}
