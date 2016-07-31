## HTTPS SSL 证书获取工具

![工具截图](http://git.oschina.net/uploads/images/2016/0411/084251_9a63e34b_111846.png "工具截图")

> 本工具用于将指定 HTTPS 地址的 SSL 证书加入 JDK 信任列表中，从而直接使用 HTTPS 连接。

### 相关依赖

- [UI元素 beautyeye](http://git.oschina.net/jackjiang/beautyeye)
- [浏览器调用](http://my.oschina.net/u/862250/blog/91777)

### 安装beautyeye到本地Maven仓库
1. 首先下载[beautyeye](https://github.com/JackJiang2011/beautyeye/archive/3.7.zip)
2. 将其解压到任意位置
3. 配置好Maven后执行如下命令

```
mvn install:install-file -Dfile=解压位置/dist/beautyeye_lnf.jar -DgroupId=org.jb2011.lnf.beautyeye -DartifactId=beautyeye_lnf  -Dversion=3.6 -Dpackaging=jar 
```


### 打包可执行jar

```
mvn assembly:assembly
```