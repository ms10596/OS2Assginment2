import java.util.ArrayList;

public class Disk {
    private final int n;
    private ArrayList <Block> blocks;
    public Disk(int n, int extentSize) {
        this.n = n;
        blocks = new ArrayList<>();
    }

    public Block allocate(int requiredBlocks){
        Block indexBlock = null;
        if(requiredBlocks > emptySpace() - 1 ) return indexBlock;
        for(int i=0; i<blocks.size() && requiredBlocks > 0; i++) {
            if(blocks.get(i).isFree()) {
                indexBlock = blocks.get(i);
                for (int j = i + 1; j < blocks.size() && requiredBlocks > 0; j++) {
                    if(blocks.get(j).isFree()) {
                        --requiredBlocks;
                        indexBlock.addNeighbor(j);
                    }
                }
            }
        }
        return indexBlock;
    }
    public boolean free(Block indexBlock){
        ArrayList <Integer> toBeFreedBlocks = indexBlock.getNeighbors();
        for(Integer index:toBeFreedBlocks) {
            blocks.get(index).free();
        }
        return true;
    }
    public boolean free(ArrayList<Block>blocks) {
        for(Block block:blocks) {
            free(block);
        }
        return true;
    }

    public int emptySpace() {
        int cnt = 0;
        for(Block block:blocks) {
            if(block.isFree()) ++cnt;
        }
        return cnt;
    }

    public int allocatedSpace() {
        return n - emptySpace();
    }

    public ArrayList<Block> getBlocks() {
        return blocks;
    }
}
