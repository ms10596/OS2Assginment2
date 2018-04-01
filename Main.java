import java.util.Scanner;
public class Main {
    private static Scanner cin = new Scanner(System.in);
    public static void main(String[] args) {
        System.out.print("Enter disk size: ");
        int diskSize = cin.nextInt();
        System.out.print("Enter extent size: ");
        int extentSize = cin.nextInt();
        FileSystem fileSystem = new FileSystem(Loading.load(),diskSize,extentSize);
        while(true){
            String command = cin.nextLine();
            String[] commands = command.split(" ");
            switch (commands[0]) {
                case "file":
                    fileSystem.createFile(commands[1], Integer.parseInt(commands[2]));
                    break;
                case "folder":
                    fileSystem.createFolder(commands[1]);
                    break;
                case "deleteFile":
                    fileSystem.deleteFile(commands[1]);
                    break;
                case "deleteFolder":
                    fileSystem.deleteFolder(commands[1]);
                    break;
                case "status":
                    fileSystem.displayDiskStatus();
                    break;
                case "struct":
                    fileSystem.displayDiskStructure();
                    break;
                case "exit":
                    Saving.save(fileSystem.getRoot());
                    return;
            }
        }
    }
}
