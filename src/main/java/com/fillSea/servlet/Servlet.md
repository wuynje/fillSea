#### servlet执行原理
***
1. web.xml中有<servlet></servlet>和<servlet-mapping></servlet-mapping>的标签来配置请求，其中有自定义的servlet的***全类名***，<font color=red>看到全类名就知道自定义的servlet肯定是通过反射加载的```Class.forName()```方法，然后通过```Class.newInstance()```去实例化一个对象，</font>最后调用其service方法，感觉是通过jdk代理来执行的。

#### servlet生命周期

***

1. ```init()```在对象被创建时执行，单例的只执行一次，一般是第一次被访问时执行

   ***但是，如果配置如下，那么servlet会在tomcat启动的时候被加载***

   ![image-20200423155029143](./Servlet.jpg)

2. ```service()```每次请求都会执行

3. ```destory()```对象销毁时执行，比如tomcat停止运行时（指服务器正常关闭）

#### HTTP 协议数据

***

* 请求消息数据格式

  1. 请求行

     <font color=green>格式：请求方式 请求URL 请求协议/版本</font>

     <font color=green>GET /login.html HTTP/1.1</font>

  2. 请求头

     <font color=green>key:value形式</font>

  * <font color=green>字符串格式</font>：

    <font color=green>GET /login.html HTTP/1.1</font>

    <font color=green>Accept: application/json, text/plain,</font>  ——数据格式
    <font color=green>Accept-Encoding: gzip, deflate</font> ——支持压缩格式
    <font color=green>Accept-Language: zh-CN</font>	——接收语言
    <font color=green>Cache-Control: no-cache</font>	
    <font color=green>Connection: Keep-Alive</font>	——表示连接可以服用
    <font color=green>Content-Length: 18</font>	
    <font color=green>Content-Type: application/json; charset=utf-8</font>	——传输文本数据格式
    <font color=green>Cookie: portalid=wbalone; default_serial=1; u_locale=zh_CN; locale_serial=1;</font> <font color=green>sessionid=c895d7d4-7a67-4a60-a24a-5e1d338bee30; _TH_=primary;</font> <font color=green>token=d2ViLDM2MDAscEo5SDhBSnV0Tk5LWjRFUm1GZ3JTVVVOMXVJak5zZXllTVV2UHAvemNlZTZhK0pKOCtnZm5qb25hSjZTVDJwZGpMbTdkNlF4N0lkMGdsWDdoQ1hMWUE9PQ; u_usercode=43c0b56a01b4479da522a28d428153fb; u_logints=1587632058152; tenantid=tenant; userType=userType; typeAlias=typeAlias; userId=43c0b56a01b4479da522a28d428153fb; _A_P_userId=43c0b56a01b4479da522a28d428153fb; _A_P_userType=userType; _A_P_userLoginName=wuyjn; _A_P_userAvator=images%252Fdot.png; _A_P_userName=wuyjn; _A_P_integration=integration; i18next=zh_CN; vertx-web.session=6d805065-1f09-4034-8610-c719f73fc33e</font>	——携带的token
    <font color=green>Host: 10.16.54.23</font>	——请求地址
    <font color=green>random-num: 0.5832274245947651</font>
    <font color=green>Referer: http://10.16.54.23/ss-pas-fe/paramset/?modulefrom=sidebar</font> ——指明了请求来源，可以做防盗链，统计等
    <font color=green>User-Agent: Mozilla/5.0 (Windows NT 10.0; WOW64; Trident/7.0; rv:11.0) like Gecko</font>	——指明了请求代理的类型，可以用来判断浏览器，进行适配
    <font color=green>X-Requested-With: XMLHttpRequest</font>

  3. 请求空行

     用来分割和请求体的空行，没什么用

  4. 请求体

     用来携带请求参数

* 响应消息数据格式
  1. 响应行
  2. 响应头
  3. 响应空行
  4. 响应体

#### GET和POST

***

1. GET中URL的长度是有限的，受浏览器限制，相对不安全的因为地址栏可见
2. POST中的参数是不受限制的，相对于GET安全一点，