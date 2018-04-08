import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Saving {
    private static PrintWriter writer;
    private static void writeDirectory(Directory root) throws Exception {
        ArrayList <Directory> directories = root.getDirectories();
        ArrayList <File> files = root.getFiles();
        if(root.getPath().equals("root")==false)writer.print("d "+root.getPath()+" ");
        for(Directory directory:directories) {
            if(directory.isEmpty()){
                writer.print("d "+directory.getPath()+" ");
                return ;
            }
            writeDirectory(directory);
        }
        for(File file:files) {
            writer.print("f "+file.getPath()+ " ");
            writer.print(file.getIndexBlock().getNeighbors().size()+" ");
            for (Integer integer:file.getIndexBlock().getNeighbors()) {
                writer.print(integer+" ");
            }
        }

    }
    public static void save(Directory root) {
        java.io.File file = new java.io.File("data.txt");
        try {
            writer = new PrintWriter(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        //writer.print(100+" "+10+" ");
        try {
            writeDirectory(root);
        } catch (Exception e) {
            e.printStackTrace();
        }
        writer.close();

    }
}
