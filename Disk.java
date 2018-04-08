import java.util.ArrayList;

public class Disk {
    private final int n;
    private ArrayList <Block> blocks;
    public Disk(int n) {
        this.n = n;
        this.blocks = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            blocks.add(new Block(i));
        }
    }

    public Block allocate(int requiredBlocks){
        Block indexBlock = null;
        if(requiredBlocks > emptySpace() - 1 ) return indexBlock;
        for(int i=0; i<blocks.size() && requiredBlocks > 0; i++) {
            if(blocks.get(i).isFree()) {
                indexBlock = blocks.get(i);
                indexBlock.allocate();
                for (int j = i + 1; j < blocks.size() && requiredBlocks > 0; j++) {
                    if(blocks.get(j).isFree()) {
                        --requiredBlocks;
                        blocks.get(j).allocate();
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
        indexBlock.free();
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
