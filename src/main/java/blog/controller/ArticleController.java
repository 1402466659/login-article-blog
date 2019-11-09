/**
 * FileName: ArticleController
 * Author:   hy
 * Date:     2019/11/9 22:33
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package blog.controller;

import blog.domain.UserDto;
import blog.entity.Article;
import blog.factory.DaoFactory;
import blog.util.ResponseObject;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@WebServlet(urlPatterns = {"/article","/article/*"})
public class ArticleController extends HttpServlet {
    private static Logger logger = LoggerFactory.getLogger(UserController.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Article> articleList = null;
        ArticleApi articleApi ;
        try{
            articleList = DaoFactory.getArticleImpl().selectAll();
        }
        catch(SQLException e){
            logger.error("获取文章信息失败");
        }
        PrintWriter out = resp.getWriter();
        Gson gson = new GsonBuilder().create();
        if(articleList!=null){
            articleApi = new ArticleApi("200ok",articleList);
        }else{
            articleApi = new ArticleApi("400ok",articleList);
        }
        out.print(gson.toJson(articleApi));
        out.close();
    }
}
