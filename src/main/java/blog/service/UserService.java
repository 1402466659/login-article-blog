/**
 * FileName: UserService
 * Author:   hy
 * Date:     2019/11/9 15:30
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package blog.service;

import blog.domain.UserDto;

import java.util.Map;

public interface UserService {
    Map<String,Object> signIn(UserDto userdto);
}
