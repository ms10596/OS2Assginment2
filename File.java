public class File {
    private String path;
    private Block indexBlock;
    public File(String filePath, Block indexBlock) {
        this.path = filePath;
        this.indexBlock = indexBlock;
    }
    public Block getIndexBlock() {
        return indexBlock;
    }
    @Override
    public String toString() {
        return "•"+path;
    }
    public String getName() {
        PathParser pathParser = new PathParser(path);
        return pathParser.getLastPieceOfPath();
    }
    public String getPath() {
        return path;
    }
    public int size() {
        return indexBlock.size();
    }
}
