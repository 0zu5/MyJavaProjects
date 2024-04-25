import java.util.*;

public class EncryptionProgram {
    private Scanner scanner;
    private Random random;
    private ArrayList<Character> list;
    private ArrayList<Character> shuffledlist;
    private char character;
    private String line;
    private char[] letters;
    private char[] secretletters;


    EncryptionProgram(){
        scanner = new Scanner(System.in);
        random= new Random();
        list = new ArrayList();
        shuffledlist = new ArrayList();
        character = ' ';

        newKey();
        askQuestion();
    }

    private void askQuestion(){
        while (true){
            System.out.println("*********************************************");
            System.out.println("what do you want to do?");
            System.out.println("(N)ewkey,(G)etKey,(E)ncrypt,(D)ecrypt,(Q)uit");
            char response = Character.toUpperCase(scanner.nextLine().charAt(0));

            switch (response){
                case 'N':
                    newKey();
                    break;
                case 'G':
                    getKey();
                    break;
                case 'E':
                    encrypt();
                    break;
                case 'D':
                    decrypt();
                    break;
                case 'Q':
                    quit();
                    break;
                default:
                    System.out.println("Not a valid answer");
            }
        }
    }
    private void newKey(){
        character = ' ';
        list.clear();
        shuffledlist.clear();

        for (int i=32;i<127;i++){
            list.add(Character.valueOf(character));
                    character++;
        }
        shuffledlist = new ArrayList(list);
        Collections.shuffle(shuffledlist);
        System.out.println("A new key has been generated");

    }
    private void getKey(){
        System.out.println("Key: ");
        for (Character x :list){
            System.out.print(x);
        }
        System.out.println();
        for (Character x :shuffledlist){
            System.out.print(x);
        }
        System.out.println();

    }
    private void encrypt(){
        System.out.println("Enter a message to be encrypted: ");
        String message = scanner.nextLine();

        letters = message.toCharArray();

        for (int i =0;i<letters.length;i++){
            for (int j=0;j<list.size();j++){
                if (letters[i]==list.get(j)){
                    letters[i]=shuffledlist.get(j);
                    break;
                }
            }
        }

        System.out.println("Encrypted: ");
        for (char x : letters){
            System.out.print(x);
        }
        System.out.println();
    }
    private void decrypt(){
        System.out.println("Enter a message to be decrypted: ");
        String message = scanner.nextLine();

        letters = message.toCharArray();

        for (int i =0;i<letters.length;i++){
            for (int j=0;j<shuffledlist.size();j++){
                if (letters[i]==shuffledlist.get(j)){
                    letters[i]=list.get(j);
                    break;
                }
            }
        }

        System.out.println("Decrypted: ");
        for (char x : letters){
            System.out.print(x);
        }
        System.out.println();
    }
    private void quit(){
        System.out.println("GoodBye!");
        System.exit(0);
    }
}
