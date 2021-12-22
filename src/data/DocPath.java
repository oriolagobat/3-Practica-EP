package data;

import exceptions.WrongDocPathFormatException;

/**
 * Essential data classes
 */
final public class DocPath {
    // Represents the route, path to a directory
    private final String path;

    public DocPath(String docPath) throws WrongDocPathFormatException {
        checkDocPath(docPath);

        this.path = docPath;
    }

    private void checkDocPath(String docPath) throws WrongDocPathFormatException {
        if (docPath == null) throw new NullPointerException("El path del document és null");

        if (docPath.length() == 0) throw new WrongDocPathFormatException("La longitud del path és zero");

        if (!slashInDocPath(docPath))
            throw new WrongDocPathFormatException("El format del path no és correcte, hauria de contenir al menys una barra");
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