/**
 * FileName: Project
 * Author:   hy
 * Date:     2019/11/15 21:17
 * Description: project实体类
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package blog.entity;

import lombok.Data;

@Data
public class Project {
    private int id;
    private String projectname;
    private int projectfollows;
    private int projectarticles;

    public Project() {
    }

    public Project(int id, String projectname, int projectfollows, int projectarticles) {
        this.id = id;
        this.projectname = projectname;
        this.projectfollows = projectfollows;
        this.projectarticles = projectarticles;
    }
}
