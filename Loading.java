import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
public class Loading {
    private static java.io.File file;
    private static Scanner scan;

    public static FileSystem load() {
        file = new java.io.File("data.txt");
        try {
            scan = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Directory root = new Directory("root");
        FileSystem fileSystem = new FileSystem(root, 20);
        if(file.length() == 0)return fileSystem ;
        while(scan.hasNext()) {
            String type = scan.next();
            if(type.equals("f")) {
                String path = scan.next();
                int size = scan.nextInt();
                fileSystem.createFile(path, size);
            }
            else {
                String path = scan.next();
                fileSystem.createDirectory(path);
            }
        }
        return fileSystem;
    }
    private static ArrayList<Integer> makeList (int start, int size) {
        ArrayList<Integer>blocksToBeAllocated = new ArrayList<>();
        for (int i = start; i <start+size ; i++) {
            blocksToBeAllocated.add(i);
        }
        return blocksToBeAllocated;
    }
}
