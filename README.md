# hi！BBS简易论坛
  
## 环境及技术
    SSM + shiro + mysql + tomcat
## BBS功能
    前台
        无账号权限
                    访问目录
                    注册及激活
        普通用户权限
                    访问BBS
                    主题
                        列表、搜索、发布、删除、回帖
        管理员权限
                    删主题、删回帖、禁言       
                                                      
    后台
        用户权限管理
        
    PS
        MD5 邮件验证码限时一分钟激活注册
        
    计划功能\优化
        
        
        后台权限管理优化
        图片上传
        
        
        数据库表（user）账户中状态 (State)列类型是int，可读性差 。      
## version日志
        1.03 新增后台权限管理系统。   
        1.2  新增用户和权限相关表增加mybatis二级缓存。    
        1.4  新增注解型ReplyMapperSQL查询总数语句。
        1.5  增加前台admin 删帖、禁言功能。
        1.7  优化注册（AJAX），重新发送验证码倒计时。
        1.8  忘记/修改密码 
        1.9  前台记住登陆 ,增加AddPrincipalToSessionFilter，shiro配置rememberMe
## 已知Bug
    已修复1.前台admin禁言功能，会更改封号等级用户的权限为禁言等级。
    2.前台搜索主题过后，使用排序功能会排序所有主题。
    3.激活时间实际为当前时间分钟改变前（yyyy-MM-dd-mm）。
    
    admin 12345
    li4 abc
    zhang3 123
    wang5 123
    dayo0107 123