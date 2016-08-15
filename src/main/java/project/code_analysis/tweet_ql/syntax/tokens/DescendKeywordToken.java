package project.code_analysis.tweet_ql.syntax.tokens;

import project.code_analysis.core.SyntaxNode;
import project.code_analysis.core.SyntaxToken;
import project.code_analysis.tweet_ql.TweetQlSyntaxTokenKind;
import project.code_analysis.tweet_ql.TweetQlTokenString;

/**
 * ProjectSouthernCross
 * <p>
 * Created by Dy.Zhao on 2016/8/11.
 */
public class DescendKeywordToken extends SyntaxToken {
    public DescendKeywordToken() {
        super(TweetQlTokenString.DESCEND_KEYWORD, TweetQlSyntaxTokenKind.DESCEND_KEYWORD_TOKEN);
    }

    public DescendKeywordToken(boolean missing, boolean unexpected) {
        super(TweetQlTokenString.DESCEND_KEYWORD, TweetQlSyntaxTokenKind.DESCEND_KEYWORD_TOKEN, missing, unexpected);
    }

    public DescendKeywordToken(int start, boolean missing, boolean unexpected) {
        super(TweetQlTokenString.DESCEND_KEYWORD, TweetQlSyntaxTokenKind.DESCEND_KEYWORD_TOKEN, start, missing, unexpected);
    }

    public DescendKeywordToken(int start, int end, boolean missing, boolean unexpected) {
        super(TweetQlTokenString.DESCEND_KEYWORD, TweetQlSyntaxTokenKind.DESCEND_KEYWORD_TOKEN, start, end, missing, unexpected);
    }

    public DescendKeywordToken(int start, int end, int fullEnd, boolean missing, boolean unexpected) {
        super(TweetQlTokenString.DESCEND_KEYWORD, TweetQlSyntaxTokenKind.DESCEND_KEYWORD_TOKEN, start, end, fullEnd, missing, unexpected);
    }

    public DescendKeywordToken(int start, int end, int fullStart, int fullEnd, boolean missing, boolean unexpected) {
        super(TweetQlTokenString.DESCEND_KEYWORD, TweetQlSyntaxTokenKind.DESCEND_KEYWORD_TOKEN, start, end, fullStart, fullEnd, missing, unexpected);
    }

    public DescendKeywordToken(boolean missing, boolean unexpected, SyntaxNode parent) {
        super(TweetQlTokenString.DESCEND_KEYWORD, TweetQlSyntaxTokenKind.DESCEND_KEYWORD_TOKEN, missing, unexpected, parent);
    }

    public DescendKeywordToken(int start, boolean missing, boolean unexpected, SyntaxNode parent) {
        super(TweetQlTokenString.DESCEND_KEYWORD, TweetQlSyntaxTokenKind.DESCEND_KEYWORD_TOKEN, start, missing, unexpected, parent);
    }

    public DescendKeywordToken(int start, int end, boolean missing, boolean unexpected, SyntaxNode parent) {
        super(TweetQlTokenString.DESCEND_KEYWORD, TweetQlSyntaxTokenKind.DESCEND_KEYWORD_TOKEN, start, end, missing, unexpected, parent);
    }

    public DescendKeywordToken(int start, int end, int fullEnd, boolean missing, boolean unexpected, SyntaxNode parent) {
        super(TweetQlTokenString.DESCEND_KEYWORD, TweetQlSyntaxTokenKind.DESCEND_KEYWORD_TOKEN, start, end, fullEnd, missing, unexpected, parent);
    }

    public DescendKeywordToken(int start, int end, int fullStart, int fullEnd, boolean missing, boolean unexpected, SyntaxNode parent) {
        super(TweetQlTokenString.DESCEND_KEYWORD, TweetQlSyntaxTokenKind.DESCEND_KEYWORD_TOKEN, start, end, fullStart, fullEnd, missing, unexpected, parent);
    }
}