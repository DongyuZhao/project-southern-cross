package project.code_analysis.tweet_ql.syntax.tokens.symbols;

import project.code_analysis.core.SyntaxError;
import project.code_analysis.core.SyntaxNode;
import project.code_analysis.tweet_ql.TweetQlTokenKind;
import project.code_analysis.tweet_ql.TweetQlTokenString;
import project.code_analysis.tweet_ql.syntax.tokens.SymbolToken;

/**
 * ProjectSouthernCross
 * <p>
 * Created by Dy.Zhao on 2016/8/11.
 */

/**
 * A syntax token class holds "("
 */
public class OpenParenthesesToken extends SymbolToken {
    public OpenParenthesesToken() {
        super(TweetQlTokenString.OPEN_PARENTHESES, TweetQlTokenKind.OPEN_PARENTHESES);
    }

    public OpenParenthesesToken(SyntaxError error) {
        super(TweetQlTokenString.OPEN_PARENTHESES, TweetQlTokenKind.OPEN_PARENTHESES, error);
    }

    public OpenParenthesesToken(int start, SyntaxError error) {
        super(TweetQlTokenString.OPEN_PARENTHESES, TweetQlTokenKind.OPEN_PARENTHESES, start, error);
    }

    public OpenParenthesesToken(SyntaxNode parent, SyntaxError error) {
        super(TweetQlTokenString.OPEN_PARENTHESES, TweetQlTokenKind.OPEN_PARENTHESES, parent, error);
    }

    public OpenParenthesesToken(SyntaxNode parent, int start, SyntaxError error) {
        super(TweetQlTokenString.OPEN_PARENTHESES, TweetQlTokenKind.OPEN_PARENTHESES, parent, start, error);
    }
}