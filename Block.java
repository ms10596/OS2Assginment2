import java.util.ArrayList;

public class Block {
    private int id;
    private ArrayList <Integer> neighbors;
    private boolean free ;
    public Block(int id) {
        this.id = id;
        this.neighbors = new ArrayList<>();
        this.free = true;
    }

    public int getId() {
        return id;
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
                "id=" + id +
                ", neighbors=" + neighbors +
                ", free=" + free +
                '}';
    }

    public int size() {
        return neighbors.size();
    }
}
