/*
 * The Apache Software License, Version 1.1
 *
 *
 * Copyright (c) 1999 The Apache Software Foundation.  All rights 
 * reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 * 1. Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer. 
 *
 * 2. Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in
 *    the documentation and/or other materials provided with the
 *    distribution.
 *
 * 3. The end-user documentation included with the redistribution,
 *    if any, must include the following acknowledgment:  
 *       "This product includes software developed by the
 *        Apache Software Foundation (http://www.apache.org/)."
 *    Alternately, this acknowledgment may appear in the software itself,
 *    if and wherever such third-party acknowledgments normally appear.
 *
 * 4. The names "Xerces" and "Apache Software Foundation" must
 *    not be used to endorse or promote products derived from this
 *    software without prior written permission. For written 
 *    permission, please contact apache@apache.org.
 *
 * 5. Products derived from this software may not be called "Apache",
 *    nor may "Apache" appear in their name, without prior written
 *    permission of the Apache Software Foundation.
 *
 * THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED
 * WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 * OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED.  IN NO EVENT SHALL THE APACHE SOFTWARE FOUNDATION OR
 * ITS CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF
 * USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT
 * OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF
 * SUCH DAMAGE.
 * ====================================================================
 *
 * This software consists of voluntary contributions made by many
 * individuals on behalf of the Apache Software Foundation and was
 * originally based on software copyright (c) 1999, International
 * Business Machines, Inc., http://www.apache.org.  For more
 * information on the Apache Software Foundation, please see
 * <http://www.apache.org/>.
 * 
 * This class was used as inspiration
 * Modified by Karel Piwko, 2008
 * 
 */

package cz.vutbr.web.domassign;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.traversal.NodeFilter;
import org.w3c.dom.traversal.TreeWalker;

/** 
 * A fallback implementation of the TreeWalker interface. It is used when the used DOM
 * parser does not support the Traversal extension.
 */
public class GenericTreeWalker implements TreeWalker {

	/** The whatToShow mask. */
	int whatToShow;

	/** The current Node. */
	Node currentNode;

	/** The root Node. */
	Node root;

	public GenericTreeWalker(Node root, int whatToShow) {
		this.root = root;
		this.currentNode = root;
		this.whatToShow = whatToShow;
	}

	public Node getRoot() {
		return root;
	}

	/** Return the whatToShow value */
	public int getWhatToShow() {
		return whatToShow;
	}

	/** Return the NodeFilter */
	public NodeFilter getFilter() {
		return null;
	}

	/** Return whether children entity references are included in the iterator. */
	public boolean getExpandEntityReferences() {
		return true;
	}

	/** Return the current Node. */
	public Node getCurrentNode() {
		return currentNode;
	}

	/** Return the current Node. */
	public void setCurrentNode(Node node) {
		currentNode = node;
	}

	/**
	 * Return the parent Node from the current node, after applying filter,
	 * whatToshow. If result is not null, set the current Node.
	 */
	public Node parentNode() {

		if (currentNode == null)
			return null;

		Node node = getParentNode(currentNode);

		if (node != null)
			currentNode = node;

		return node;

	}

	/**
	 * Return the first child Node from the current node, after applying filter,
	 * whatToshow. If result is not null, set the current Node.
	 */
	public Node firstChild() {

		if (currentNode == null)
			return null;

		Node node = getFirstChild(currentNode);

		if (node != null)
			currentNode = node;

		return node;
	}

	/**
	 * Return the last child Node from the current node, after applying filter,
	 * whatToshow. If result is not null, set the current Node.
	 */
	public Node lastChild() {

		if (currentNode == null)
			return null;

		Node node = getLastChild(currentNode);

		if (node != null)
			currentNode = node;

		return node;
	}

	/**
	 * Return the previous sibling Node from the current node, after applying
	 * filter, whatToshow. If result is not null, set the current Node.
	 */
	public Node previousSibling() {

		if (currentNode == null)
			return null;

		Node node = getPreviousSibling(currentNode);
		if (node != null)
			currentNode = node;

		return node;
	}

	/**
	 * Return the next sibling Node from the current node, after applying
	 * filter, whatToshow. If result is not null, set the current Node.
	 */
	public Node nextSibling() {

		if (currentNode == null)
			return null;

		Node node = getNextSibling(currentNode);
		if (node != null)
			currentNode = node;

		return node;
	}

	/**
	 * Return the previous Node from the current node, after applying filter,
	 * whatToshow. If result is not null, set the current Node.
	 */
	public Node previousNode() {

		if (currentNode == null)
			return null;

		// get sibling
		Node result = getPreviousSibling(currentNode);
		if (result == null) {
			result = getParentNode(currentNode);
			if (result != null) {
				currentNode = result;
				return result;
			}
			return null;
		}

		// get the lastChild of result.
		Node lastChild = getLastChild(result);

		Node prev = lastChild;
		while (lastChild != null) {
			prev = lastChild;
			lastChild = getLastChild(prev);
		}

		lastChild = prev;

		// if there is a lastChild which passes filters return it.
		if (lastChild != null) {
			currentNode = lastChild;
			return lastChild;
		}

		// otherwise return the previous sibling.
		currentNode = result;
		return result;
	}

	/**
	 * Return the next Node from the current node, after applying filter,
	 * whatToshow. If result is not null, set the current Node.
	 */
	public Node nextNode() {

		if (currentNode == null)
			return null;

		Node result = getFirstChild(currentNode);

		if (result != null) {
			currentNode = result;
			return result;
		}

		result = getNextSibling(currentNode);

		if (result != null) {
			currentNode = result;
			return result;
		}

		// return parent's 1st sibling.
		Node parent = getParentNode(currentNode);
		while (parent != null) {
			result = getNextSibling(parent);
			if (result != null) {
				currentNode = result;
				return result;
			} else {
				parent = getParentNode(parent);
			}
		}

		// end , return null
		return null;
	}

	/**
	 * Internal function. Return the parent Node, from the input node after
	 * applying filter, whatToshow. The current node is not consulted or set.
	 */
	private Node getParentNode(Node node) {

		if (node == null || node == root)
			return null;

		Node newNode = node.getParentNode();
		if (newNode == null)
			return null;

		int accept = acceptNode(newNode);

		if (accept == NodeFilter.FILTER_ACCEPT)
			return newNode;
		else
			// if (accept == NodeFilter.SKIP_NODE)
			// and REJECT too.
			return getParentNode(newNode);

	}

	/**
	 * Internal function. Return the nextSibling Node, from the input node after
	 * applying filter, whatToshow. The current node is not consulted or set.
	 */
	private Node getNextSibling(Node node) {

		if (node == null || node == root)
			return null;

		Node newNode = node.getNextSibling();
		if (newNode == null) {
			newNode = node.getParentNode();
			if (newNode == null || node == root)
				return null;
			int parentAccept = acceptNode(newNode);
			if (parentAccept == NodeFilter.FILTER_SKIP) {
				return getNextSibling(newNode);
			}
			return null;
		}

		int accept = acceptNode(newNode);

		if (accept == NodeFilter.FILTER_ACCEPT)
			return newNode;
		else if (accept == NodeFilter.FILTER_SKIP) {
			Node fChild = getFirstChild(newNode);
			if (fChild == null)
				return getNextSibling(newNode);

			return fChild;
		} else
			// if (accept == NodeFilter.REJECT_NODE)
			return getNextSibling(newNode);

	}

	/**
	 * Internal function. Return the previous sibling Node, from the input node
	 * after applying filter, whatToshow. The current node is not consulted or
	 * set.
	 */
	private Node getPreviousSibling(Node node) {

		if (node == null || node == root)
			return null;

		Node newNode = node.getPreviousSibling();
		if (newNode == null) {
			newNode = node.getParentNode();
			if (newNode == null || node == root)
				return null;
			int parentAccept = acceptNode(newNode);
			if (parentAccept == NodeFilter.FILTER_SKIP)
				return getPreviousSibling(newNode);

			return null;
		}

		int accept = acceptNode(newNode);

		if (accept == NodeFilter.FILTER_ACCEPT)
			return newNode;
		else if (accept == NodeFilter.FILTER_SKIP) {
			Node fChild = getLastChild(newNode);
			if (fChild == null)
				return getPreviousSibling(newNode);

			return fChild;
		} else
			// if (accept == NodeFilter.REJECT_NODE)
			return getPreviousSibling(newNode);

	}

	/**
	 * Internal function. Return the first child Node, from the input node after
	 * applying filter, whatToshow. The current node is not consulted or set.
	 */
	private Node getFirstChild(Node node) {

		if (node == null)
			return null;

		Node newNode = node.getFirstChild();
		if (newNode == null)
			return null;

		int accept = acceptNode(newNode);
		if (accept == NodeFilter.FILTER_ACCEPT)
			return newNode;
		else if (accept == NodeFilter.FILTER_SKIP && newNode.hasChildNodes())
			return getFirstChild(newNode);

		// if (accept == NodeFilter.REJECT_NODE)
		return getNextSibling(newNode);
	}

	/**
	 * Internal function. Return the last child Node, from the input node after
	 * applying filter, whatToshow. The current node is not consulted or set.
	 */
	private Node getLastChild(Node node) {

		if (node == null)
			return null;

		Node newNode = node.getLastChild();
		if (newNode == null)
			return null;

		int accept = acceptNode(newNode);
		if (accept == NodeFilter.FILTER_ACCEPT)
			return newNode;
		else if (accept == NodeFilter.FILTER_SKIP && newNode.hasChildNodes())
			return getLastChild(newNode);

		// if (accept == NodeFilter.REJECT_NODE)
		return getPreviousSibling(newNode);

	}

	/**
	 * Internal function. The node whatToShow and the filter are combined into
	 * one result.
	 */
	private short acceptNode(Node node) {
		/***
		 * 7.1.2.4. Filters and whatToShow flags
		 * 
		 * Iterator and TreeWalker apply whatToShow flags before applying
		 * Filters. If a node is rejected by the active whatToShow flags, a
		 * Filter will not be called to evaluate that node. When a node is
		 * rejected by the active whatToShow flags, children of that node will
		 * still be considered, and Filters may be called to evaluate them.
		 ***/

		if ((whatToShow & (1 << node.getNodeType() - 1)) != 0)
			return NodeFilter.FILTER_ACCEPT;
		else
			return NodeFilter.FILTER_SKIP;
	}

	/**
	 * This class implements traversal of DOM tree with simplified Visitor
	 * pattern.
	 * 
	 * @author kapy
	 * 
	 */
	public static abstract class Traversal<T> {

		protected Object source;
		protected TreeWalker walker;

		public Traversal(TreeWalker walker, Object source) {
			this.source = source;
			this.walker = walker;
		}

		public Traversal(Document doc, Object source, int whatToShow) {
			this.walker = new GenericTreeWalker(doc.getDocumentElement(),
					whatToShow);
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
}
