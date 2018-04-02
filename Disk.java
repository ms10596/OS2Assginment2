import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Disk {
    private ArrayList <Extent> extents;
    private final int n;

    public Disk(int n, int extentSize) {
        this.n = n;
        extents = new ArrayList<>();
        for(int i=0;i<n;i+=extentSize) {
            extents.add(new Extent(i, extentSize));
        }
    }
    public ArrayList<Integer> allocate(int requiredBlocks){
        Collections.sort(extents, new Comparator<Extent>() {
            @Override
            public int compare(Extent t1, Extent t2) {
                return Integer.compare(t1.freeBlocksSize(), t2.freeBlocksSize());
            }
        });
        for(Extent extent:extents) {
            if(extent.freeBlocksSize() >= requiredBlocks) {
                return extent.requestSpace(requiredBlocks);
            }
        }
        return null;
    }
    public boolean free(ArrayList<Integer> toBeFreed){
        for (Extent extent:extents) {
            if(toBeFreed.size() == 0) return true;
            extent.freeSpace(toBeFreed);
        }
        return true;
    }
    public int emptySpace() {
        int emptySpaces = 0;
        for(Extent extent:extents) {
            emptySpaces += extent.freeBlocksSize();
        }
        return emptySpaces;
    }
    public int allocatedSpace() {
        return n - emptySpace();
    }
    public ArrayList<Extent> getExtents() {
        return extents;
    }
}
