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
    private int userid;
    private String title;
    private String Scontent;
    private String Ncomment;
    private String Nread;
    private String Lcontent;

    public Article(int id, int userid, String title, String scontent, String ncomment, String nread, String lcontent) {
        this.id = id;
        this.userid = userid;
        this.title = title;
        Scontent = scontent;
        Ncomment = ncomment;
        Nread = nread;
        Lcontent = lcontent;
    }

    public Article() {
    }
}
