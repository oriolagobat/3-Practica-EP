package data;

/**
 * Essential data classes
 */
final public class DocPath {
    // Represents the route, path to a directory
    private final String path;

    public DocPath(String docPath) {
        checkDocPath(docPath);

        this.path = docPath;
    }

    private void checkDocPath(String docPath) {
        if (docPath == null) throw new NullPointerException("El path del document és null");
    }

    public String getDocPath() {
        return path;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DocPath doc_path = (DocPath) o;
        return path.equals(doc_path.path);
    }

    @Override
    public int hashCode() {
        return path.hashCode();
    }

    @Override
    public String toString() {
        return "DocPath{" + "path documento='" + path + '\'' + '}';
    }
}