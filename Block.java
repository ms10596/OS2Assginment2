import java.util.ArrayList;

public class Block {
    private ArrayList <Integer> neighbors;
    private boolean free ;
    public Block() {
        neighbors = new ArrayList<>();
        free = true;
    }
    public void addNeighbor(int blockIndex) {
        neighbors.add(blockIndex);
    }
    public boolean isFree() {
        return free;
    }
    public void allocate() {
        free = false;
    }
    public void free() {
        free = true;
    }

    public ArrayList<Integer> getNeighbors() {
        return neighbors;
    }

    @Override
    public String toString() {
        return "Block{" +
                "neighbors=" + neighbors +
                ", free=" + free +
                '}';
    }
}
