package com.dayo.realm;

import com.dayo.pojo.State;
import com.dayo.pojo.User;
import com.dayo.service.PermissionService;
import com.dayo.service.RoleService;
import com.dayo.service.UserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;

/**
 * @author DayoWong on 2018/9/20
 */
public class DatabaseRealm extends AuthorizingRealm {
    @Autowired
    UserService userService;
    @Autowired
    RoleService roleService;
    @Autowired
    PermissionService permissionService;

    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //能进入到这里，表示账号已经通过验证了
        String username = (String) principalCollection.getPrimaryPrincipal();
        //通过service获取用户拥有的角色和授权
        Set<String> roleNames = roleService.listRoleNames(username);
        Set<String> permissions = permissionService.listPermissions(username);
        //授权对象
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        if (roleNames!=null && permissions !=null) {
            info.setRoles(roleNames);
            info.setStringPermissions(permissions);
        }
        return info;
    }

    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

        //获取当前用户名
        String userName= authenticationToken.getPrincipal().toString();
        //获取数据库中的密码
        User user =userService.get(userName);
        if (user.getState()==State.INACTIVATION)
            return null;
        String passwordInDB = user.getPassword();
        String salt = user.getSalt();
        //认证信息里存放账号密码, getName() 是当前Realm的继承方法,通常返回当前类名 :databaseRealm
        //盐也放进去
        //这样通过applicationContext-shiro.xml里配置的 HashedCredentialsMatcher 进行自动校验
        return new SimpleAuthenticationInfo(userName,passwordInDB,ByteSource.Util.bytes(salt),getName());
    }
}
