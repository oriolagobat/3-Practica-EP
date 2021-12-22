package data;

import exceptions.WrongDocPathFormatException;

/**
 * Essential data classes
 */
final public class DocPath {
    // Represents the route, path to a directory
    private final String path;

    public DocPath(String doc_path) throws WrongDocPathFormatException {
        if (doc_path == null) throw new NullPointerException("El path del document és null");

        if (doc_path.length() == 0) throw new WrongDocPathFormatException("La longitud del path és zero");

        if (!slashInDocPath(doc_path))
            throw new WrongDocPathFormatException("El format del path no és correcte, hauria de contenir al menys una barra");

        this.path = doc_path;
    }

    private boolean slashInDocPath(String docPath) {
        char[] path = docPath.toCharArray();
        for (char c : path) {
            if (c == '/') {
                return true;
            }
        }
        return false;
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