import java.util.ArrayList;

public class File {
    private String path;
    private ArrayList<Integer> allocatedBlocks;
    public File(String filePath, ArrayList<Integer> allocatedBlocks) {
        this.path = filePath;
        this.allocatedBlocks = allocatedBlocks;
    }
    public ArrayList<Integer> getAllocatedBlocks() {
        return allocatedBlocks;
    }
    @Override
    public String toString() {
        return "•"+path;
    }
    public String getName() {
        PathParser pathParser = new PathParser(path);
        return pathParser.getLastPieceOfPath();
    }
    public String getPath() {
        return path;
    }
}
