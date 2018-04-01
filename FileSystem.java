import java.util.ArrayList;

public class FileSystem {
    private Directory root ;
    private Disk disk;
    public FileSystem(Directory root, int size, int extentSize) {
        this.root = root;
        disk = new Disk(size, extentSize);
    }

    public Directory getRoot() {
        return root;
    }

    public boolean createFile(String path, int size){
        ArrayList<Integer> requiredBlocks = disk.allocate(size);
        if(requiredBlocks != null){
            root.createFile(path, requiredBlocks);
            return true;
        }
        return false;
    }
    public boolean createFolder(String path){
        return root.createDirectory(path);
    }
    public boolean deleteFile(String path){
        return disk.free(root.deleteFile(path));
    }
    public boolean deleteFolder(String path){
        return disk.free(root.deleteDirectory(path));
    }
    public void displayDiskStatus(){
        System.out.println("Empty space: "+disk.emptySpace()+ " KB");
        System.out.println("Allocated space: "+disk.allocatedSpace() + " KB");
        for(Extent extent:disk.getExtents()) {
            System.out.println(extent);
        }
    }
    public void displayDiskStructure(){
        System.out.println(root);
    }

}
