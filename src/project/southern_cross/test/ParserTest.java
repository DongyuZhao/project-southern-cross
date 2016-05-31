package project.southern_cross.test;

import org.junit.Assert;
import org.junit.Test;
import project.southern_cross.code_analysis.tweet_ql.TweetQlSyntaxParser;
import project.southern_cross.code_analysis.tweet_ql.TweetQlSyntaxTree;

/**
 * Project Southern Cross
 * A language parser framework come up with TweetQL parser. Originally designed for R.A.P.I.D
 * <p>
 * Created by Dy.Zhao on 2016/6/1 0001.
 */
public class ParserTest {
    @Test
    public void test(){
        String source = "CREATE a FROM (B);";
        TweetQlSyntaxParser parser = new TweetQlSyntaxParser(source);
        TweetQlSyntaxTree tree = parser.parse();
        Assert.assertNotNull(tree.root().toString());
    }
}