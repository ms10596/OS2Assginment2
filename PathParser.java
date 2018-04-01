import java.util.ArrayList;
import java.util.Arrays;
public class PathParser {
    private final String path;
    private ArrayList<String> directories;
    public PathParser(String path) {
        this.path = path;
        directories = new ArrayList<>(Arrays.asList(path.split("/")));
    }
    public boolean isLastPiece() {
        return directories.size() == 1;
    }
    public String getParent() {
        return directories.get(0);
    }
    public String retrieveParent() {
        String parent = getParent();
        directories.remove(0);
        return parent;
    }
    public String getLastPieceOfPath() {
        return directories.get(directories.size() - 1);
    }
    public static int dashes(String path){
        return path.split("/").length;
    }

}
