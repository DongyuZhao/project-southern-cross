package project.code_analysis.tweet_ql.syntax.builders;

import project.code_analysis.core.SyntaxToken;
import project.code_analysis.tweet_ql.syntax.nodes.AttributeListSyntax;
import project.code_analysis.tweet_ql.syntax.nodes.StreamListSyntax;

import java.util.ArrayList;

/**
 * ProjectSouthernCross
 * <p>
 * Created by Dy.Zhao on 2016/8/14.
 */
public class StreamListBuilder {
    private ArrayList<SyntaxToken> tokenList = new ArrayList<>();
    public void append(SyntaxToken token) {
        this.tokenList.add(token);
    }

    public void clear() {
        this.tokenList.clear();
    }

    public StreamListSyntax build() {
        return null;
    }
}