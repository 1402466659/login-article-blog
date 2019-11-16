/**
 * FileName: JsoupDemo
 * Author:   hy
 * Date:     2019/11/7 8:32
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package blog.util;

import blog.entity.Article;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class JsoupDemo {
//    public static void main(String[] args) throws Exception{
//        //声明文档变量
//        Document document;
//        //通过Jsoup连接目标页面
//        document = Jsoup.connect("https://www.mosoteach.cn/web/index.php?c=interaction&m=index&clazz_course_id=70722C73-9395-11E9-A9F1-98039B1848C6").get();
//        //选取所有class为cal-xs-8集合
//        Elements divs = document.getElementsByClass("col-xs-8");
//        //对divs遍历
//        divs.forEach(div->{
//            //取出class为wrap的节点
//            Element wrapDiv = div.child(0);
//            Element link =  wrapDiv.child(0);
//            Elements lingchildren = link.children();
//            System.out.println(lingchildren.get(2).text());
//        });
//    }

/**
 获取文章的简单信息
*/

    public static void main(String[] args) {
        Object[]  url =null;
        for(int num = 1;num<=3 ;num++){
            Document document = null;

            try {
                document = Jsoup.connect("https://www.cnblogs.com/sitehome/p/"+num).get();
            } catch (IOException e) {
                System.out.println("错啦");
            }
            Elements datas =  document.getElementsByClass("post_item_body");
            datas.forEach(data->{
//                System.out.println(data.child(0).text());//标题
//                System.out.println(data.child(1).text());//小内容
//                System.out.println(data.child(2).child(1).text());//评论数
//                System.out.println(data.child(2).child(2).text());//阅读
            });
            Elements detaildivs = document.getElementsByClass("titlelnk");
            Elements dataillinks = detaildivs.select("a");
            //获取超链接
            dataillinks.forEach(detaillink->{
                Document detaildocument = null;
                try {
                    detaildocument = Jsoup.connect(""+detaillink.attr("href")).get();
                } catch (IOException e) {
                    System.out.println("爬取详细信息错啦");
                }
                Elements detailmessage = detaildocument.getElementsByClass("postBody");
                Elements messagelink = detailmessage.select("p");
                messagelink.forEach(message->{
                    System.out.println(message.text());
                });

            });


//            System.out.println(detaildocument);
//            Elements detaildivs = document.getElementsByClass("postBody");
//            Elements detaillink = divs.select("p");
//            detaillink.forEach(div->{
//                System.out.println(div.text());
//            });
        }
    }

/**
 * 获取文章的详细信息
 */

        public  void getMesssge(Object url){
            Document document = null;
            try {
                document = Jsoup.connect(""+url).get();
            } catch (IOException e) {
                System.out.println("错啦");
            }
            Elements divs = document.getElementsByClass("postBody");
            Elements link = divs.select("p");
            link.forEach(div->{
//                System.out.println(div.text());
            });
}

    }

