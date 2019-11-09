/**
 * FileName: ArticleDapImpl
 * Author:   hy
 * Date:     2019/11/9 21:17
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package blog.dao;

import blog.entity.Article;
import blog.util.DbUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ArticleDapImpl implements ArticleDao{
    private static Logger logger = LoggerFactory.getLogger(ArticleDapImpl.class);
    @Override
    public int[] batchInsertArticle(List<Article> articlelist) throws SQLException {
        Connection connection = DbUtil.getConnection();
        String sql = " INSERT INTO t_article (title,content,diamond,comment,author,likes) VALUES(?,?,?,?,?,?) ";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        connection.setAutoCommit(false);
        articlelist.forEach(article -> {
            try {
                pstmt.setString(1,article.getTitle());
                pstmt.setString(2,article.getContent());
                pstmt.setDouble(3,article.getDiamond());
                pstmt.setInt(4,article.getComment());
                pstmt.setString(5,article.getAuthor());
                pstmt.setInt(6,article.getLikes());
                pstmt.addBatch();
            } catch (SQLException e) {
               logger.error("批量新增文章失败");
            }
        });
        int [] result = pstmt.executeBatch();
        connection.commit();
        DbUtil.close(null,pstmt,connection);
        return result;
     }

    @Override
    public List<Article> selectAll() throws SQLException {
        List<Article> articleList = new ArrayList<>();
        Connection connection = DbUtil.getConnection();
        String sql = "SELECT * FROM t_article ORDER BY id DESC ";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery();
        while (rs.next()){
            Article article = new Article();
            article.setId(rs.getInt("id"));
            article.setTitle(rs.getString("title"));
            article.setContent(rs.getString("content"));
            article.setComment(rs.getInt("comment"));
            article.setDiamond(rs.getDouble("diamond"));
            article.setLikes(rs.getInt("likes"));
            article.setAuthor(rs.getString("author"));
            articleList.add(article);
        }
        return articleList;
    }
    }

