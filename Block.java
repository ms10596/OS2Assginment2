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
        neighbors.clear();
    }

    public ArrayList<Integer> getNeighbors() {
        return neighbors;
    }

    @Override
    public String toString() {
        String string = new String(Integer.toString(id)+"->");
        if(neighbors.size() > 0) string += " "+neighbors;
        if(isFree()) string += "(free)";
        else string += "(used)";
        return string;

    }

    public int size() {
        return neighbors.size();
    }
}
