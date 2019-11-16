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
import blog.util.JSoupSpider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class ArticleDapImpl implements ArticleDao{
    private static Logger logger = LoggerFactory.getLogger(ArticleDapImpl.class);
    @Override
    public int[] batchInsertArticle(List<Article> articlelist) throws SQLException {
        Connection connection = DbUtil.getConnection();
        String sql = " INSERT INTO t_article (title,Scontent,Ncomment,Nread,userid,projectid) VALUES(?,?,?,?,?,?) ";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        connection.setAutoCommit(false);
        articlelist.forEach(article -> {
            try {
                pstmt.setString(1,article.getTitle());
                pstmt.setString(2,article.getScontent());
                pstmt.setString(3,article.getNcomment());
                pstmt.setString(4,article.getNread());
                pstmt.setInt(5,new Random().nextInt(216));
                pstmt.setInt(6,new Random().nextInt(10));
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
        String sql = " SELECT * FROM t_article ";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery();
        while (rs.next()){
            Article article = new Article();
            article.setId(rs.getInt("id"));
            article.setUserid(rs.getInt("userid"));
            article.setTitle(rs.getString("title"));
            article.setScontent(rs.getString("Scontent"));
            article.setNcomment(rs.getString("Ncomment"));
            article.setNread(rs.getString("Nread"));
            article.setLcontent(rs.getString("Lcontent"));
            articleList.add(article);
        }
        connection.commit();
        DbUtil.close(null,pstmt,connection);
        return articleList;
    }
    @Override
    public void insertDetail(List detailmessage) throws SQLException {
        Connection connection = DbUtil.getConnection();
        int num ;
        for (num = 1;num<=detailmessage.size();num++){
            String sql = " UPDATE t_article set Lcontent = ? where id= "+num ;
            PreparedStatement pstmt = connection.prepareStatement(sql);
            try {
                 pstmt.setString(1, detailmessage.get(num-1)+"");
                 pstmt.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


}

