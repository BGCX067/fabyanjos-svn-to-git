package com.alfa.tracking.spring.helper;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.alfa.tracking.model.Objeto;

public class ListObjectTree implements Serializable, Tree{

	private static final long serialVersionUID = 2947048958021131964L;
	
	private List<LeafNode> children;
	
	public ListObjectTree(List<Objeto> objetos) {
		children = new ArrayList<LeafNode>();
		for (Objeto objeto : objetos) {
			children.add(new ObjectItem(objeto));
		}
	}
	
	/* (non-Javadoc)
	 * @see com.alfa.tracking.spring.helper.Tree#getChildren()
	 */
	@Override
	public List<LeafNode> getChildren() {
		return children;
	}
	
}
