package cz.vutbr.web.domassign;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.traversal.DocumentTraversal;
import org.w3c.dom.traversal.TreeWalker;

/**
 * This class implements traversal of DOM tree with simplified Visitor
 * pattern.
 * 
 * @author kapy
 * 
 */
public abstract class Traversal<T> 
{
    protected Object source;
    protected TreeWalker walker;

    public Traversal(TreeWalker walker, Object source) {
        this.source = source;
        this.walker = walker;
    }

    public Traversal(Document doc, Object source, int whatToShow) {
        if (doc instanceof DocumentTraversal) {
            DocumentTraversal dt = (DocumentTraversal) doc;
            this.walker = dt.createTreeWalker(doc.getDocumentElement(), whatToShow, null, false);
        } else {
            this.walker = new GenericTreeWalker(doc.getDocumentElement(), whatToShow);
        }
        this.source = source;
    }

    public void listTraversal(T result) {

        // tree traversal as nodes are found inside
        Node current, checkpoint = null;
        current = walker.nextNode();
        while (current != null) {
            // this method can change position in walker
            checkpoint = walker.getCurrentNode();
            processNode(result, current, source);
            walker.setCurrentNode(checkpoint);
            current = walker.nextNode();
        }
    }

    public void levelTraversal(T result) {

        // this method can change position in walker
        Node current, checkpoint = null;
        current = checkpoint = walker.getCurrentNode();
        processNode(result, current, source);
        walker.setCurrentNode(checkpoint);

        // traverse children:
        for (Node n = walker.firstChild(); n != null; n = walker
                .nextSibling()) {
            levelTraversal(result);
        }

        // return position to the current (level up):
        walker.setCurrentNode(checkpoint);
    }

    protected abstract void processNode(T result, Node current,
            Object source);

    public Traversal<T> reset(TreeWalker walker, Object source) {
        this.walker = walker;
        this.source = source;
        return this;
    }

}
