public class FileSystem {
    private Directory root ;
    private Disk disk;
    public FileSystem(Directory root, int size) {
        this.root = root;
        disk = new Disk(size);
    }

    public Directory getRoot() {
        return root;
    }

    public boolean createFile(String path, int size){
        Block indexBlock = disk.allocate(size);
        if(indexBlock != null){
            root.createFile(path, indexBlock);
            return true;
        }
        return false;
    }
    public boolean createDirectory(String path){
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
        for(Block block:disk.getBlocks()) {
            System.out.println(block);
        }
    }
    public void displayDiskStructure(){
        System.out.println(root);
    }

}
