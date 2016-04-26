package cz.vutbr.web.css;

public class CodeLocation {

	private final int offset;
	private final int length;
	private final int startLine;
	private final int startColumn;
	private final int endLine;
	private final int endColumn;

	public CodeLocation(int offset, int length, int startLine, int startColumn, int endLine, int endColumn) {
		this.offset = offset;
		this.length = length;
		this.startLine = startLine;
		this.startColumn = startColumn;
		this.endLine = endLine;
		this.endColumn = endColumn;
	}
	
	public int getOffset() {
		return offset;
	}

	public int getLength() {
		return length;
	}

	public int getStartLine() {
		return startLine;
	}

	public int getStartColumn() {
		return startColumn;
	}

	public int getEndLine() {
		return endLine;
	}

	public int getEndColumn() {
		return endColumn;
	}

	@Override
	public String toString() {
		return "Offset: " + offset + " Length: " + length + "Line: " + startLine + " Column: " + startColumn + " - Line: " + endLine + " Column: " + endColumn;
	}

}
