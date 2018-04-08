import java.util.ArrayList;
import java.util.Arrays;

public class Extent {
    private int start, size;
    private ArrayList<Integer> blocks;
    private boolean[] allocatedBlocks;
    public Extent(int start, int size) {
        this.start = start;
        this.size = size;
        blocks = new ArrayList<>();
        allocatedBlocks = new boolean[size];
        for (int i = start; i < start + size; i++) {
            blocks.add(i);
        }
        Arrays.fill(allocatedBlocks, false);
    }
    public int freeBlocksSize() {
        int cnt = 0;
        for (int i = 0; i < size; i++) {
            if(allocatedBlocks[i] == false) {
                ++cnt;
            }
        }
        return cnt;
    }
    public ArrayList<Integer> requestSpace(int blocks) {
        ArrayList<Integer> newelyAllocatedBlocks = new ArrayList<>();
        if(blocks <= freeBlocksSize()) {
            for (int i = 0; i < blocks; i++) {
                if(!allocatedBlocks[i]) {
                    allocatedBlocks[i] = true;
                    newelyAllocatedBlocks.add(start + i);
                }
            }
        }
        return newelyAllocatedBlocks;
    }
    public boolean freeSpace(ArrayList<Integer> toBeFreed) {
        while (toBeFreed.size() > 0 && toBeFreed.get(0) >= start && toBeFreed.get(0) < start + size){
            allocatedBlocks[toBeFreed.get(0) - start] = false;
            toBeFreed.remove(0);
        }
        return true;
    }

    @Override
    public String toString() {
        String string = new String();
        string+=Integer.toString(freeBlocksSize());
        /*string+=" 1 ";
        string+=Integer.toString(size - freeBlocksSize());
        string+=" 0 ";*/
        return string;
    }

}

