1. 导出报错：error while loading shared libraries: libfontconfig.so.1: cannot open shared object file: No such file or directory
    > 解决方案：  
    sudo yum install fontconfig
2. 导出图片没有文字：缺少字体
    > 解决方案：  
    yum -y install bitmap-fonts bitmap-fonts-cjk mkfontscale fontconfig   
    mkdir /usr/share/fonts/win/  
    下载微软雅黑字体:   
    wget https://nipao.googlecode.com/files/msyh.ttf -O /usr/share/fonts/win/msyh.ttf   
    建立字体索引，更新字体缓存:   
    cd /usr/share/fonts/win/   
    mkfontscale  
    mkfontdir  
    fc-cache -fv 
3. 导出excel缺失图片：tomcat缺少temp目录
    > 解决方案：  
    tomcat创建temp目录
4. 邮件推送乱码
    > 解决方案：  
    系统语言设置为：zh_CN.utf8  
    /etc/profile添加：export LC_ALL=zh_CN.utf8  
5. Tomcat启动慢
    > 解决方案：  
    修改catalina.sh  
    JAVA_OPTS="$JAVA_OPTS -Djava.security.egd=file:/dev/./urandom"