package project.code_analysis.core;

import java.util.ArrayList;
import java.util.List;

/**
 * Project Southern Cross
 * A language parser framework come up with TweetQL parser. Originally designed for R.A.P.I.D
 *
 * Created by Dy.Zhao on 2016/7/11.
 */
public class SyntaxNode extends SyntaxNodeOrTrivia {

    private ArrayList<SyntaxNode> childNodes = new ArrayList<>();

    private ArrayList<SyntaxTrivia> leadingTrivia = new ArrayList<>();
    private ArrayList<SyntaxTrivia> trialingTrivia = new ArrayList<>();

    public SyntaxNode(ISyntaxKind kind) {
        super(kind);
    }

    public SyntaxNode(ISyntaxKind kind, boolean missing, boolean unexpected) {
        super(kind, missing, unexpected);
    }

    public SyntaxNode(ISyntaxKind kind, int start, boolean missing, boolean unexpected) {
        super(kind, start, missing, unexpected);
    }

    public SyntaxNode(ISyntaxKind kind, int start, int end, boolean missing, boolean unexpected) {
        super(kind, start, end, missing, unexpected);
    }

    public SyntaxNode(ISyntaxKind kind, int start, int end, int fullEnd, boolean missing, boolean unexpected) {
        super(kind, start, end, fullEnd, missing, unexpected);
    }

    public SyntaxNode(ISyntaxKind kind, int start, int end, int fullStart, int fullEnd, boolean missing, boolean unexpected) {
        super(kind, start, end, fullStart, fullEnd, missing, unexpected);
    }

    public SyntaxNode(ISyntaxKind kind, boolean missing, boolean unexpected, SyntaxNode parent) {
        super(kind, missing, unexpected, parent);
    }

    public SyntaxNode(ISyntaxKind kind, int start, boolean missing, boolean unexpected, SyntaxNode parent) {
        super(kind, start, missing, unexpected, parent);
    }

    public SyntaxNode(ISyntaxKind kind, int start, int end, boolean missing, boolean unexpected, SyntaxNode parent) {
        super(kind, start, end, missing, unexpected, parent);
    }

    public SyntaxNode(ISyntaxKind kind, int start, int end, int fullEnd, boolean missing, boolean unexpected, SyntaxNode parent) {
        super(kind, start, end, fullEnd, missing, unexpected, parent);
    }

    public SyntaxNode(ISyntaxKind kind, int start, int end, int fullStart, int fullEnd, boolean missing, boolean unexpected, SyntaxNode parent) {
        super(kind, start, end, fullStart, fullEnd, missing, unexpected, parent);
    }

    public boolean hasChildNode() {
        return this.childNodes.size() != 0;
    }

    public List<? extends SyntaxNode> getDescendNodesAndSelf() {
        ArrayList<SyntaxNode> result = new ArrayList<>();
        result.add(this);
        result.addAll(this.getDescendNodes());
        return result;
    }

//    public void addChildNode(SyntaxNode child) {
//        int index = this.findSyntaxUnitInsertIndex(child.getFullStart(), child.getFullEnd(), this.childNodes);
//        if (index != -1) {
//            this.addChildNode(child, index);
//            return;
//        }
//        throw new IllegalArgumentException("New SyntaxNode's position is not suitable for the existing nodes.");
//    }

    public List<? extends SyntaxNode> getDescendNodes() {
        ArrayList<SyntaxNode> result = new ArrayList<>(this.childNodes);
        for (int i = 0; i < result.size(); i++) {
            result.addAll(result.get(i).getChildNodes());
        }
        return result;
    }

    public List<? extends SyntaxNode> getChildNodes() {
        return new ArrayList<>(this.childNodes);
    }

    public List<? extends SyntaxNode> getAncestorNodeAndSelf() {
        ArrayList<SyntaxNode> result = new ArrayList<>();
        result.add(this);
        result.addAll(this.getAncestorNode());
        return result;
    }

    public void addChildNode(SyntaxNode node) {
        this.addChildNode(node, this.getChildNodes().size());
    }

    public void addChildNode(SyntaxNode node, int index) {
        if (index >= 0 && index <= this.getChildNodes().size()) {
            node.setParentNode(this);
            if (index == 0) {
                if (this.getStart() > node.getStart()) {
                    node.shiftFullSpanWindowTo(this.getStart());
                }
            } else {
                node.shiftFullSpanWindowTo(this.getChildNodes().get(index - 1).getFullEnd());
            }

            if (index < this.getChildNodes().size()) {
                this.updateSpanWindow(index, node.getRawString().length(), this.childNodes);
            }
            this.childNodes.add(index, node);
            this.updateSpanWindow(0, node.getRawString().length(), this.trialingTrivia);
            return;
        }
        throw new IllegalArgumentException("Index out of range");
    }

    public boolean hasChildToken() {
        return this.childTokens.size() != 0;
    }

    @Override
    public void addChildToken(SyntaxToken token, int index) {
        super.addChildToken(token, index);
        token.setParentNode(this);
        this.updateSpanWindow(0, token.getRawString().length(), this.trialingTrivia);
    }

    public List<SyntaxNodeOrToken> getDescendNodeOrTokensAndSelf() {
        ArrayList<SyntaxNodeOrToken> result = new ArrayList<>();
        result.add(this);
        result.addAll(this.getDescendNodeOrTokens());
        return result;
    }

    public List<SyntaxNodeOrToken> getDescendNodeOrTokens() {
        ArrayList<SyntaxNodeOrToken> result = new ArrayList<>();
        result.addAll(this.getDescendNodes());
        result.addAll(this.getDescendTokens());
        return result;
    }

    public List<SyntaxToken> getDescendTokens() {
        ArrayList<SyntaxToken> result = new ArrayList<>(this.childTokens);
        this.getDescendNodes().forEach(node -> result.addAll(node.getChildTokens()));
        return result;
    }

    public void addLeadingTrivia(SyntaxTrivia trivia) {
        this.addLeadingTrivia(trivia, this.getLeadingTrivia().size());
    }

    public void addLeadingTrivia(SyntaxTrivia trivia, int index) {
        if (index >= 0 && index <= this.getLeadingTrivia().size()) {
            trivia.setParentNode(this);
            if (index == 0) {
                if (this.getStart() > trivia.getStart()) {
                    trivia.shiftFullSpanWindowTo(this.getStart());
                }
                this.leadingTrivia.add(trivia);
            } else {
                trivia.shiftFullSpanWindowTo(this.getLeadingTrivia().get(index - 1).getFullEnd());
            }

            if (index < this.getLeadingTrivia().size()) {
                this.updateSpanWindow(index, trivia.getRawString().length(), this.leadingTrivia);
            }
            this.leadingTrivia.add(index, trivia);
            this.updateSpanWindow(0, trivia.getRawString().length(), this.childNodes);
            this.updateSpanWindow(0, trivia.getRawString().length(), this.trialingTrivia);
            return;
        }
        throw new IllegalArgumentException("Index out of range");
    }

    public List<SyntaxTrivia> getLeadingTrivia() {
        return new ArrayList<>(this.leadingTrivia);
    }

    public void addTrialingTrivia(SyntaxTrivia trivia) {
        this.addTrialingTrivia(trivia, this.getTrialingTrivia().size());
    }

    public void addTrialingTrivia(SyntaxTrivia trivia, int index) {
        if (index >= 0 && index <= this.getTrialingTrivia().size()) {
            trivia.setParentNode(this);
            if (index == 0) {
                if (this.getStart() > trivia.getStart()) {
                    trivia.shiftFullSpanWindowTo(this.getStart());
                }
                this.trialingTrivia.add(trivia);
            } else {
                trivia.shiftFullSpanWindowTo(this.getTrialingTrivia().get(index - 1).getFullEnd());
            }

            if (index < this.getTrialingTrivia().size()) {
                this.updateSpanWindow(index, trivia.getRawString().length(), this.leadingTrivia);
            }
            this.trialingTrivia.add(index, trivia);
            return;
        }
        throw new IllegalArgumentException("Index out of range");
    }

    public List<SyntaxTrivia> getTrialingTrivia() {
        return new ArrayList<>(this.trialingTrivia);
    }

    @Override
    public String getFullString() {
        return this.getRawString();
    }

    @Override
    public String toString() {
        return this.getFullString();
    }

    @Override
    public boolean isSyntaxNode() {
        return true;
    }
}
