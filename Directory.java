import java.util.ArrayList;

public class Directory {
    private String path;
    private ArrayList<File> files;
    private ArrayList<Directory> directories;

    public Directory(String path) {
        this.path = path;
        this.files = new ArrayList<>();
        this.directories = new ArrayList<>();
    }
    public String getPath() {
        return path;
    }
    public ArrayList<File> getFiles() {
        return files;
    }
    public ArrayList<Directory> getDirectories() {
        return directories;
    }
    public String getName() {
        PathParser pathParser = new PathParser(path);
        return pathParser.getLastPieceOfPath();
    }

    private ArrayList<Integer> deleteDirectoryInCurrentDirectory(String name) {
        System.out.println(name);
        Directory toBeDeleted = findDirectoryInCurrentDirectory(name);
        ArrayList<Integer> allocatedBlocks = new ArrayList<>();
        if (toBeDeleted.isEmpty()) {
            directories.remove(toBeDeleted);
            return allocatedBlocks;
        }
        allocatedBlocks.addAll(toBeDeleted.deleteAllFilesInCurrentDirectory());
        while (toBeDeleted.directories.size() > 0) {
            Directory directory = directories.get(0);
            allocatedBlocks.addAll(toBeDeleted.deleteDirectoryInCurrentDirectory(directory.getName()));
        }
        return allocatedBlocks;
    }
    private ArrayList<Integer> deleteAllDirectoriesInCurrentDirectory() {
        ArrayList<Integer> allocatedBlocks = new ArrayList<>();
        while (directories.size() > 0) {
            Directory directory = directories.get(0);
            allocatedBlocks.addAll(directory.deleteAllFilesInCurrentDirectory());
            //while(directories.get(0).)
            allocatedBlocks.addAll(deleteDirectoryInCurrentDirectory(directory.getName()));
        }
        return allocatedBlocks;
    }
    private Directory findDirectoryInCurrentDirectory(String name) {
        for (Directory directory : directories) {
            if (directory.getName().equals(name)) {
                return directory;
            }
        }
        return null;
    }
    public boolean createDirectory(String path) {
        PathParser pathParser = new PathParser(path);
        Directory currentDirectory = this;
        pathParser.retrieveParent();
        while (pathParser.isLastPiece() == false) {
            currentDirectory = currentDirectory.findDirectoryInCurrentDirectory(pathParser.retrieveParent());
        }
        currentDirectory.directories.add(new Directory(path));
        return true;
    }
    public ArrayList<Integer> deleteDirectory(String path) {
        PathParser pathParser = new PathParser(path);
        Directory currentDirectory = this;
        pathParser.retrieveParent();
        while (pathParser.isLastPiece() == false) {
            currentDirectory = currentDirectory.findDirectoryInCurrentDirectory(pathParser.retrieveParent());
        }
        currentDirectory = currentDirectory.findDirectoryInCurrentDirectory(pathParser.getParent());
        ArrayList<Integer> allocatedBlocks = currentDirectory.deleteAllFilesInCurrentDirectory();
        allocatedBlocks.addAll(currentDirectory.deleteAllDirectoriesInCurrentDirectory());
        directories.remove(currentDirectory);
        return allocatedBlocks;
    }
    public boolean isEmpty() {
        return directories.size() == 0 && files.size() == 0;
    }

    private ArrayList<Integer> deleteFileInCurrentDirectory(String name) {
        for (File file : files) {
            if (file.getName().equals(name)) {
                ArrayList<Integer> allocatedBlocks = file.getAllocatedBlocks();
                files.remove(file);
                return allocatedBlocks;
            }
        }
        return null;
    }
    private ArrayList<Integer> deleteAllFilesInCurrentDirectory() {
        ArrayList<Integer> allocatedBlocks = new ArrayList<>();
        while (files.size() > 0) {
            allocatedBlocks.addAll(deleteFileInCurrentDirectory(files.get(0).getName()));
        }
        return allocatedBlocks;
    }
    public boolean createFile(String path, ArrayList<Integer> allocatedBlocks) {
        PathParser pathParser = new PathParser(path);
        Directory currentDirectory = this;
        pathParser.retrieveParent();
        while (pathParser.isLastPiece() == false) {
            currentDirectory = currentDirectory.findDirectoryInCurrentDirectory(pathParser.retrieveParent());
        }
        currentDirectory.files.add(new File(path, allocatedBlocks));
        return true;
    }
    public ArrayList<Integer> deleteFile(String path) {
        PathParser pathParser = new PathParser(path);
        Directory currentDirectory = this;
        pathParser.retrieveParent();
        while (pathParser.isLastPiece() == false) {
            currentDirectory = currentDirectory.findDirectoryInCurrentDirectory(pathParser.retrieveParent());
        }
        return currentDirectory.deleteFileInCurrentDirectory(pathParser.getLastPieceOfPath());
    }

    @Override
    public String toString() {
        String string = new String();
        string += "\n"+tabs(PathParser.dashes(getPath())) + "▼" + getPath();
        for (File file : files) {
            string += "\n" + tabs(PathParser.dashes(file.getPath())) + file.toString();

        }
        for (Directory directory : directories) {
            if (directory.isEmpty()) {
                string += "\n" + tabs(PathParser.dashes(directory.getPath())) + "►" + directory.getPath();
                return string;
            }
            string += directory.toString();
        }
        return string;
    }
    private String tabs(int n) {
        String string = new String();
        for (int i = 1; i < n; i++) {
            string += "\t";
        }
        return string;
    }
}
