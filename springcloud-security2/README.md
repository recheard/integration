# integration
用于记录springcloud和springsecurity的集成


#运行步骤
    1.先执行security_test.sql文件，修改数据库相应配置
    2.启动项目
#项目介绍
    erueka   服务注册中心
    gateway  网关，有做一个所有服务的一个请求拦截，拦截之后会判断服务是否需要是否需要security验证，
             如果需要则会验证jwt令牌是否合法，如果合法则会解析令牌信息为用户信息，并将用户信息存入requst中
    user-security 认证服务，用户用户登录以及令牌发放
    order    订单服务，里面有两个接口有做权限认证

#测试
    获取令牌 post http://localhost:8101/oauth/token?client_id=order&client_secret=secret&grant_type=password&username=1&password=123456
        该模式是通过用户信息和密码获取token令牌
    验证令牌信息 post http://localhost:8101/security/oauth/check_token  
        参数： body {token : "令牌"} 
    测试访问订单服务 get http://localhost:8103/security/oauth/token?client_id=order&client_secret=secret&grant_type=password&username=1&password=123456