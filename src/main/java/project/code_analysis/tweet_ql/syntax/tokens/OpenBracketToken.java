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
public class OpenBracketToken extends SyntaxToken {
    public OpenBracketToken() {
        super(TweetQlTokenString.OPEN_BRACKET, TweetQlSyntaxTokenKind.OPEN_BRACKET_TOKEN);
    }

    public OpenBracketToken(boolean missing, boolean unexpected) {
        super(TweetQlTokenString.OPEN_BRACKET, TweetQlSyntaxTokenKind.OPEN_BRACKET_TOKEN, missing, unexpected);
    }

    public OpenBracketToken(int start, boolean missing, boolean unexpected) {
        super(TweetQlTokenString.OPEN_BRACKET, TweetQlSyntaxTokenKind.OPEN_BRACKET_TOKEN, start, missing, unexpected);
    }

    public OpenBracketToken(int start, int end, boolean missing, boolean unexpected) {
        super(TweetQlTokenString.OPEN_BRACKET, TweetQlSyntaxTokenKind.OPEN_BRACKET_TOKEN, start, end, missing, unexpected);
    }

    public OpenBracketToken(int start, int end, int fullEnd, boolean missing, boolean unexpected) {
        super(TweetQlTokenString.OPEN_BRACKET, TweetQlSyntaxTokenKind.OPEN_BRACKET_TOKEN, start, end, fullEnd, missing, unexpected);
    }

    public OpenBracketToken(int start, int end, int fullStart, int fullEnd, boolean missing, boolean unexpected) {
        super(TweetQlTokenString.OPEN_BRACKET, TweetQlSyntaxTokenKind.OPEN_BRACKET_TOKEN, start, end, fullStart, fullEnd, missing, unexpected);
    }

    public OpenBracketToken(boolean missing, boolean unexpected, SyntaxNode parent) {
        super(TweetQlTokenString.OPEN_BRACKET, TweetQlSyntaxTokenKind.OPEN_BRACKET_TOKEN, missing, unexpected, parent);
    }

    public OpenBracketToken(int start, boolean missing, boolean unexpected, SyntaxNode parent) {
        super(TweetQlTokenString.OPEN_BRACKET, TweetQlSyntaxTokenKind.OPEN_BRACKET_TOKEN, start, missing, unexpected, parent);
    }

    public OpenBracketToken(int start, int end, boolean missing, boolean unexpected, SyntaxNode parent) {
        super(TweetQlTokenString.OPEN_BRACKET, TweetQlSyntaxTokenKind.OPEN_BRACKET_TOKEN, start, end, missing, unexpected, parent);
    }

    public OpenBracketToken(int start, int end, int fullEnd, boolean missing, boolean unexpected, SyntaxNode parent) {
        super(TweetQlTokenString.OPEN_BRACKET, TweetQlSyntaxTokenKind.OPEN_BRACKET_TOKEN, start, end, fullEnd, missing, unexpected, parent);
    }

    public OpenBracketToken(int start, int end, int fullStart, int fullEnd, boolean missing, boolean unexpected, SyntaxNode parent) {
        super(TweetQlTokenString.OPEN_BRACKET, TweetQlSyntaxTokenKind.OPEN_BRACKET_TOKEN, start, end, fullStart, fullEnd, missing, unexpected, parent);
    }
}
