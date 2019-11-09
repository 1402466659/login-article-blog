package blog.util;


import blog.entity.Article;
import blog.entity.User;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


/**
 * @author mq_xu
 * @ClassName JSoupSpider
 * @Description JSoup实现的一个爬虫工具
 * @Date 9:13 2019/11/7
 * @Version 1.0
 **/
public class JSoupSpider {
    private static Logger logger = LoggerFactory.getLogger(JSoupSpider.class);
    public static List<User> getUsers() {
        Document document = null;
        List<User> userList = new ArrayList<>(100);
        for (int i = 1; i <= 3; i++) {
            try {
                document = Jsoup.connect("https://www.jianshu.com/recommendations/users?utm_source=desktop&utm_medium=index-users&page=" + i).get();
            } catch (IOException e) {
                logger.error("连接失败");
            }
            Elements divs = document.getElementsByClass("col-xs-8");
            divs.forEach(div -> {
                Element wrapDiv = div.child(0);
                Element link = wrapDiv.child(0);
                Elements linkChildren = link.children();
                User user = new User();
                user.setMobile(Datautil.getMobile());
                user.setPassword(Datautil.getPassword());
                user.setGender(Datautil.getGender());
                user.setAvatar("https:" + linkChildren.get(0).attr("src"));
                user.setNickname(linkChildren.get(1).text());
                user.setIntroduction(linkChildren.get(2).text());
                user.setBirthday(Datautil.getBirthday());
                user.setCreateTime(LocalDateTime.now());
                userList.add(user);
            });
        }
        return userList;
    }

    public static List<Article> getArticle(){
        List<Article> articleList = new ArrayList<>(100);
        Document document = null;
            try {
                document = Jsoup.connect("https://www.jianshu.com/").get();
            } catch (IOException e) {
                logger.error("爬取文章信息失败");
            }
            Elements divs = document.getElementsByClass("have-img");
            divs.forEach(div->{
                //初始化对象
                Article article = new Article();
                //标题
                article.setTitle(div.child(1).child(0).text());
                //内容
                article.setContent(div.child(1).child(1).text());
                //钻石
                article.setDiamond(Double.parseDouble(div.child(1).child(2).child(0).text()));
                //评论数
                article.setComment(Integer.parseInt(div.child(1).child(2).child(2).text()));
                //喜欢数
                article.setLikes(Integer.parseInt(div.child(1).child(2).child(3).text()));
                //作者
                article.setAuthor(div.child(1).child(2).child(1).text());
                articleList.add(article);
            });
        return articleList;
    }

}
