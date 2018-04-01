import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
public class Loading {
    private static java.io.File file;
    private static Scanner scan;

    public static  Directory load() {
        file = new java.io.File("data.txt");
        Directory root = new Directory("root");
        if(file.length() == 0)return root ;
        try {
            scan = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        while(scan.hasNext()) {
            String type = scan.next();
            if(type.equals("f")) {
                String path = scan.next();
                int start = scan.nextInt();
                int size = scan.nextInt();
                root.createFile(path, makeList(start, size));
            }
            else {
                String path = scan.next();
                root.createDirectory(path);
            }
        }
        return root;
    }
    private static ArrayList<Integer> makeList (int start, int size) {
        ArrayList<Integer>blocksToBeAllocated = new ArrayList<>();
        for (int i = start; i <start+size ; i++) {
            blocksToBeAllocated.add(i);
        }
        return blocksToBeAllocated;
    }
}
