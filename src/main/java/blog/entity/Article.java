/**
 * FileName: Article
 * Author:   hy
 * Date:     2019/11/9 20:12
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package blog.entity;

import lombok.Data;

@Data
public class Article {
    private  int id;
    private String title;
    private String content;
    private  double diamond;
    private int comment;
    private String author;
    private int likes;

    public Article(int id, String title, String content, double diamond, int comment, String author, int likes) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.diamond = diamond;
        this.comment = comment;
        this.author = author;
        this.likes = likes;
    }

    public Article() {
    }
}
