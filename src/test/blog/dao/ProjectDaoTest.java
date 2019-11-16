package blog.dao;

import blog.entity.Project;
import blog.factory.DaoFactory;
import blog.util.JSoupSpider;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;

import static org.junit.Assert.*;

public class ProjectDaoTest {
    private static Logger logger = LoggerFactory.getLogger(ArticleDapImpl.class);
    private ProjectDao projectDao = DaoFactory.getProjectImpl();
    @Test
    public void batchInsertProject() {
        try {
            int[] result = projectDao.batchInsertProject(JSoupSpider.getProject());
        } catch (SQLException e) {
            logger.error("批量新增文章出现异常");
            e.printStackTrace();
        }
    }
}