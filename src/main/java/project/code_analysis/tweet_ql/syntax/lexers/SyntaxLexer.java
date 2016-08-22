package project.code_analysis.tweet_ql.syntax.lexers;

import project.code_analysis.core.SyntaxToken;
import project.code_analysis.tweet_ql.TweetQlSyntaxTokenKind;
import project.code_analysis.tweet_ql.TweetQlTokenString;
import project.code_analysis.tweet_ql.syntax.TweetQlSyntaxFacts;
import project.code_analysis.tweet_ql.syntax.tokens.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * ProjectSouthernCross
 * <p>
 * Created by Dy.Zhao on 2016/8/13.
 */
public class SyntaxLexer {
    private LexerStates currentState = LexerStates.IDLE;
    private Stack<LexerStates> scopeLexStack = new Stack<>();
    private TweetQlSyntaxFacts syntaxFacts = TweetQlSyntaxFacts.getInstance();

    private SyntaxLexer() {
    }

    public static SyntaxLexer create() {
        return new SyntaxLexer();
    }

    public List<? extends SyntaxToken> transformTokens(List<SyntaxToken> originTokenList) {
        ArrayList<SyntaxToken> result = new ArrayList<>();
        if (originTokenList != null) {
            originTokenList.forEach(t -> {
                switch (t.getRawString()) {
                    case TweetQlTokenString.CREATE_KEYWORD:
                        result.add(new CreateKeywordToken());
                        break;
                    case TweetQlTokenString.SELECT_KEYWORD:
                        result.add(new SelectKeywordToken());
                        break;
                    case TweetQlTokenString.FROM_KEYWORD:
                        result.add(new FromKeywordToken());
                        break;
                    case TweetQlTokenString.WHERE_KEYWORD:
                        result.add(new WhereKeywordToken());
                        break;
                    case TweetQlTokenString.CONTAIN_KEYWORD:
                        result.add(new ContainKeywordToken());
                        break;
                    case TweetQlTokenString.LIKE_KEYWORD:
                        result.add(new LikeKeywordToken());
                        break;
                    case TweetQlTokenString.AND_KEYWORD:
                        result.add(new AndKeywordToken());
                        break;
                    case TweetQlTokenString.OR_KEYWORD:
                        result.add(new OrderKeywordToken());
                        break;
                    case TweetQlTokenString.NOT_KEYWORD:
                        result.add(new NotKeywordToken());
                        break;
                    case TweetQlTokenString.SKIP_KEYWORD:
                        result.add(new SkipKeywordToken());
                        break;
                    case TweetQlTokenString.WINDOW_KEYWORD:
                        result.add(new WindowKeywordToken());
                        break;
                    case TweetQlTokenString.AS_KEYWORD:
                        result.add(new AsKeywordToken());
                        break;
                    case TweetQlTokenString.BETWEEN_KEYWORD:
                        result.add(new BetweenKeywordToken());
                        break;
                    case TweetQlTokenString.ORDER_KEYWORD:
                        result.add(new OrderKeywordToken());
                        break;
                    case TweetQlTokenString.BY_KEYWORD:
                        result.add(new ByKeywordToken());
                        break;
                    case TweetQlTokenString.ASCEND_KEYWORD:
                        result.add(new AscendKeywordToken());
                        break;
                    case TweetQlTokenString.DESCEND_KEYWORD:
                        result.add(new DescendKeywordToken());
                        break;
                    case TweetQlTokenString.OPEN_PARENTHESES:
                        result.add(new OpenParenthesesToken());
                        break;
                    case TweetQlTokenString.CLOSE_PARENTHESES:
                        result.add(new CloseParenthesesToken());
                        break;
                    case TweetQlTokenString.OPEN_BRACKET:
                        result.add(new OpenBracketToken());
                        break;
                    case TweetQlTokenString.CLOSE_BRACKET:
                        result.add(new CloseBracketToken());
                        break;
                    case TweetQlTokenString.OPEN_BRACE:
                        result.add(new OpenBraceToken());
                        break;
                    case TweetQlTokenString.CLOSE_BRACE:
                        result.add(new CloseBraceToken());
                        break;
                    case TweetQlTokenString.COMMA:
                        result.add(new CommaToken());
                        break;
                    case TweetQlTokenString.DOT:
                        result.add(new DotToken());
                        break;
                    case TweetQlTokenString.COLON:
                        result.add(new ColonToken());
                        break;
                    case TweetQlTokenString.SEMICOLON:
                        result.add(new SemicolonToken());
                        break;
                    case TweetQlTokenString.EQUAL:
                        result.add(new EqualToken());
                        break;
                    case TweetQlTokenString.BIGGER:
                        result.add(new BiggerToken());
                        break;
                    case TweetQlTokenString.LESS:
                        result.add(new LessToken());
                        break;
                    case TweetQlTokenString.BIGGER_EQUAL:
                        result.add(new BiggerEqualToken());
                        break;
                    case TweetQlTokenString.LESS_EQUAL:
                        result.add(new LessEqualToken());
                        break;
                    case TweetQlTokenString.CRLF:
                        result.add(new CRLFToken());
                        break;
                    case TweetQlTokenString.LF:
                        result.add(new LFToken());
                        break;
                    case TweetQlTokenString.STAR:
                        result.add(new StarToken());
                        break;
                    default:
                        if (this.syntaxFacts.isLiteralString(t.getRawString())) {
                            result.add(new LiteralStringToken(t.getRawString()));
                        } else if (this.syntaxFacts.isValidIdentifier(t.getRawString())) {
                            result.add(new IdentifierToken(t.getRawString()));
                        } else {
                            result.add(new SyntaxToken(t.getRawString(), t.getKind()));
                        }
                        break;
                }
            });
        }
        return result;
    }

    public List<? extends SyntaxToken> lex(List<? extends SyntaxToken> originTokenList) {
        ArrayList<SyntaxToken> result = new ArrayList<>();
        for (SyntaxToken token : originTokenList) {
            switch (this.currentState) {
                case IDLE:
                    switch ((TweetQlSyntaxTokenKind) token.getKind()) {
                        case CREATE_KEYWORD_TOKEN:
                            this.currentState = LexerStates.AFTER_CREATE;
                            result.add(token);
                            break;
                        case SELECT_KEYWORD_TOKEN:
                            this.currentState = LexerStates.AFTER_SELECT;
                            result.add(token);
                            break;
                        default:
                            token.setError(false, true);
                            result.add(token);
                            break;
                    }
                    continue;
                case AFTER_CREATE:
                    processIdentifierInCreate(result, token);
                    continue;
                case AFTER_COMMA_IN_CREATE:
                    processIdentifierInCreate(result, token);
                    continue;
                case AFTER_SELECT:
                    switch ((TweetQlSyntaxTokenKind) token.getKind()) {
                        case IDENTIFIER_TOKEN:
                            this.currentState = LexerStates.AFTER_IDENTIFIER_IN_SELECT;
                            result.add(token);
                            break;
                        case STAR_TOKEN:
                            this.currentState = LexerStates.AFTER_STAR_IN_SELECT;
                            result.add(token);
                            break;
                        default:
                            token.setError(false, true);
                            result.add(token);
                            break;
                    }
                    continue;
                case AFTER_IDENTIFIER_IN_CREATE:
                    switch ((TweetQlSyntaxTokenKind) token.getKind()) {
                        case COMMA_TOKEN:
                            this.currentState = LexerStates.AFTER_COMMA_IN_CREATE;
                            result.add(token);
                            break;
                        case FROM_KEYWORD_TOKEN:
                            this.currentState = LexerStates.AFTER_FROM;
                            result.add(token);
                            break;
                        default:
                            token.setError(false, true);
                            result.add(token);
                            break;
                    }
                    continue;
                case AFTER_IDENTIFIER_IN_SELECT:
                    switch ((TweetQlSyntaxTokenKind) token.getKind()) {
                        case COMMA_TOKEN:
                            this.currentState = LexerStates.AFTER_COMMA_IN_SELECT;
                            result.add(token);
                            break;
                        case FROM_KEYWORD_TOKEN:
                            this.currentState = LexerStates.AFTER_FROM;
                            result.add(token);
                            break;
                        default:
                            token.setError(false, true);
                            result.add(token);
                            break;
                    }
                    continue;
                case AFTER_STAR_IN_SELECT:
                    switch ((TweetQlSyntaxTokenKind) token.getKind()) {
                        case FROM_KEYWORD_TOKEN:
                            this.currentState = LexerStates.AFTER_FROM;
                            result.add(token);
                            break;
                        default:
                            token.setError(false, true);
                            result.add(token);
                            break;
                    }
                    continue;
                case AFTER_COMMA_IN_SELECT:
                    switch ((TweetQlSyntaxTokenKind) token.getKind()) {
                        case IDENTIFIER_TOKEN:
                            this.currentState = LexerStates.AFTER_IDENTIFIER_IN_SELECT;
                            result.add(token);
                            break;
                        default:
                            token.setError(false, true);
                            result.add(token);
                            break;
                    }
                    continue;
                case AFTER_FROM:
                    switch ((TweetQlSyntaxTokenKind) token.getKind()) {
                        case IDENTIFIER_TOKEN:
                        case STAR_TOKEN:
                            this.currentState = LexerStates.AFTER_IDENTIFIER_IN_FROM;
                            result.add(token);
                            break;
                        default:
                            token.setError(false, true);
                            result.add(token);
                            break;
                    }
                    continue;
                case AFTER_IDENTIFIER_IN_FROM:
                    switch ((TweetQlSyntaxTokenKind) token.getKind()) {
                        case WHERE_KEYWORD_TOKEN:
                            this.currentState = LexerStates.IN_SCOPE;
                            result.add(token);
                            break;
                        case SEMICOLON_TOKEN:
                            this.currentState = LexerStates.IDLE;
                            result.add(token);
                            break;
                        default:
                            token.setError(false, true);
                            result.add(token);
                            break;
                    }
                    continue;
                case IN_SCOPE:
                    switch ((TweetQlSyntaxTokenKind) token.getKind()) {
                        case IDENTIFIER_TOKEN:
                            this.currentState = LexerStates.AFTER_FIRST_IDENTIFIER_IN_BINARY;
                            result.add(token);
                            break;
                        default:
                            token.setError(false, true);
                            result.add(token);
                            break;
                    }
                    continue;
                case AFTER_FIRST_IDENTIFIER_IN_BINARY:
                    if (this.syntaxFacts.isBinaryOperator(token.getRawString())) {
                        this.currentState = LexerStates.AFTER_OPERATOR_IN_BINARY;
                        result.add(token);
                    } else {
                        token.setError(false, true);
                        result.add(token);
                    }
                    continue;
                case AFTER_OPERATOR_IN_BINARY:
                    if (this.syntaxFacts.isLiteralString(token.getRawString()) || this.syntaxFacts.isDigit(token.getRawString())) {
                        this.currentState = LexerStates.AFTER_SECOND_IDENTIFIER_IN_BINARY;
                        result.add(token);
                    } else {
                        token.setError(false, true);
                        result.add(token);
                    }
                    continue;
                case AFTER_SECOND_IDENTIFIER_IN_BINARY:
                    switch ((TweetQlSyntaxTokenKind) token.getKind()) {
                        case SEMICOLON_TOKEN:
                            this.currentState = LexerStates.IDLE;
                            result.add(token);
                            break;
                        case COMMA_TOKEN:
                            this.currentState = LexerStates.AFTER_FROM;
                            result.add(token);
                            break;
                        default:
                            token.setError(false, true);
                            result.add(token);
                            break;
                    }
                    continue;
                case AFTER_UNIVERSE_IN_SELECT:
                    break;
                case AFTER_UNIVERSE_IN_FROM:
                    break;
                case AFTER_UNARY_OPERATOR:
                    break;
                case AFTER_OPEN_PARENTHESES:
                    break;
                case AFTER_CLOSE_PARENTHESES:
                    break;
                default:
                    token.setError(false, true);
                    result.add(token);
                    continue;
            }
        }
        return result;
    }

    private void processIdentifierInCreate(ArrayList<SyntaxToken> result, SyntaxToken token) {
        switch ((TweetQlSyntaxTokenKind) token.getKind()) {
            case IDENTIFIER_TOKEN:
                this.currentState = LexerStates.AFTER_IDENTIFIER_IN_CREATE;
                result.add(token);
                break;
            default:
                token.setError(false, true);
                result.add(token);
                break;
        }
    }

    private enum LexerStates {
        IDLE,
        AFTER_CREATE,
        AFTER_IDENTIFIER_IN_CREATE,
        AFTER_COMMA_IN_CREATE,

        AFTER_SELECT,
        AFTER_IDENTIFIER_IN_SELECT,
        AFTER_COMMA_IN_SELECT,

        AFTER_FROM,
        AFTER_IDENTIFIER_IN_FROM,

        IN_SCOPE,
        AFTER_FIRST_IDENTIFIER_IN_BINARY,
        AFTER_OPERATOR_IN_BINARY,
        AFTER_SECOND_IDENTIFIER_IN_BINARY,
        AFTER_UNIVERSE_IN_SELECT,
        AFTER_UNIVERSE_IN_FROM,
        AFTER_UNARY_OPERATOR,
        AFTER_OPEN_PARENTHESES,
        AFTER_CLOSE_PARENTHESES,
        AFTER_STAR_IN_SELECT
    }
}