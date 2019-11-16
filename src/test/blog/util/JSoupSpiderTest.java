package blog.util;

import blog.dao.ArticleDao;
import blog.dao.ArticleDapImpl;
import blog.factory.DaoFactory;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;

import static org.junit.Assert.*;

public class JSoupSpiderTest {
    private static Logger logger = LoggerFactory.getLogger(ArticleDapImpl.class);
    private ArticleDao articleDao = DaoFactory.getArticleImpl();
    @Test
    public void getArticle() {
        try {
            int[] result = articleDao.batchInsertArticle(JSoupSpider.getArticle());
            //articleDao.insertDetail(JSoupSpider.getArticleDetail());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}