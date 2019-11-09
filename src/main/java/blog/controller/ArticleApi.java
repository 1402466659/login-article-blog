/**
 * FileName: ArticleApi
 * Author:   hy
 * Date:     2019/11/10 0:23
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package blog.controller;

import blog.entity.Article;

import java.util.List;

public class ArticleApi {
    private String code;
    private List data;

    public ArticleApi() {
    }

    public ArticleApi(String code, List data) {
        this.code = code;
        this.data = data;
    }
}
