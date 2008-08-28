package cz.vutbr.web.csskit.javacc;

public class SimpleNode implements Node {
  protected Node parent;
  protected Node[] children;
  protected int id;
  protected CSSParser parser;
  private String image = null;

  public SimpleNode(int i) {
    id = i;
  }

  public SimpleNode(CSSParser p, int i) {
    this(i);
    parser = p;
  }

  public void jjtOpen() {
  }

  public void jjtClose() {
  }
  
  public void jjtSetParent(Node n) { parent = n; }
  public Node jjtGetParent() { return parent; }

  public void jjtAddChild(Node n, int i) {
    if (children == null) {
      children = new Node[i + 1];
    } else if (i >= children.length) {
      Node c[] = new Node[i + 1];
      System.arraycopy(children, 0, c, 0, children.length);
      children = c;
    }
    children[i] = n;
  }

  public Node jjtGetChild(int i) {
    return children[i];
  }

  public int jjtGetNumChildren() {
    return (children == null) ? 0 : children.length;
  }

  public String toString() { return CSSParserTreeConstants.jjtNodeName[id] + (image == null ? "" : " " + image); }
  public String toString(String prefix) { return prefix + toString(); }


  public void dump(String prefix) {
    System.out.println(toString(prefix));
    if (children != null) {
      for (int i = 0; i < children.length; ++i) {
        SimpleNode n = (SimpleNode)children[i];
        if (n != null) {
	        n.dump(prefix + " ");
	      }
      }
    }
  }

  public void setImage(String n) {
    image = n;
  }

  public String getImage() {
    return image;
  }
  
  public String getType() {
      return CSSParserTreeConstants.jjtNodeName[id];
  }

}

