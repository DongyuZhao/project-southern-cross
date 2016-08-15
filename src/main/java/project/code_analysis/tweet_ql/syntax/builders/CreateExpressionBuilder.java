package project.code_analysis.tweet_ql.syntax.builders;

import project.code_analysis.core.SyntaxNode;
import project.code_analysis.core.SyntaxToken;
import project.code_analysis.tweet_ql.TweetQlSyntaxTokenKind;
import project.code_analysis.tweet_ql.syntax.nodes.CreateExpressionSyntax;
import project.code_analysis.tweet_ql.syntax.nodes.StreamListSyntax;

import java.util.ArrayList;

/**
 * ProjectSouthernCross
 * <p>
 * Created by Dy.Zhao on 2016/8/14.
 */
public class CreateExpressionBuilder {
    private CreateExpressionSyntax root;
    private ArrayList<SyntaxToken> tokenList = new ArrayList<>();
    public void append(SyntaxToken token) {
        this.tokenList.add(token);
    }

    public void clear() {
        this.tokenList.clear();
    }

    private StreamListBuilder targetListBuilder = new StreamListBuilder();

    private StreamListBuilder sourceListBuilder = new StreamListBuilder();

    private enum ParseStates {
        ROOT,
        AFTER_CREATE,
        AFTER_FROM,
    }

    private ParseStates currentStates = ParseStates.ROOT;

    public CreateExpressionSyntax build() {
        this.root = new CreateExpressionSyntax();
        this.tokenList.forEach(token -> {
            if (this.currentStates == ParseStates.ROOT) {
                if (token.getKind() == TweetQlSyntaxTokenKind.CREATE_KEYWORD_TOKEN) {
                    this.currentStates = ParseStates.AFTER_CREATE;
                    return;
                }
            }
            if (this.currentStates == ParseStates.AFTER_CREATE) {
                if (token.getKind() != TweetQlSyntaxTokenKind.FROM_KEYWORD_TOKEN) {
                    this.targetListBuilder.append(token);
                } else {
                    this.root.addChildNode(this.targetListBuilder.build());
                    this.currentStates = ParseStates.AFTER_FROM;
                }
                return;
            }
            if (this.currentStates == ParseStates.AFTER_FROM) {
                this.sourceListBuilder.append(token);
                if (token.getKind() == TweetQlSyntaxTokenKind.SEMICOLON_TOKEN) {
                    this.root.addChildNode(this.sourceListBuilder.build());
                    this.currentStates = ParseStates.ROOT;
                }
                return;
            }
            return;
        });
        return this.root;
    }
}