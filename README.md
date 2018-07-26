# socket-servers
socketserver(blocking and asynchronous implement)

在开发sharebase项目中，需要用户登录验证功能，需要使用socket进行通信，故实现了两种socket服务器，一种是同步阻塞服务器（blocking）还有一种是
异步非阻塞服务器。
用户验证的流程：
1. 用户登录：用户在某个服务器登录以后，登录服务将用户token写入redis；
2. 其他服务要验证登录时，与用户登录的服务器（服务器上开了单独的socketServer进行验证代理）进行通信（项目中先后使用了阻塞和异步支持并发的socketServer），验证token；

***
## Reference
非阻塞Server的实现参考了这篇文章，由于没有找到原文，暂时贴上这篇转载文[JAVA NIO使用非阻塞模式实现高并发服务器](https://blog.csdn.net/zmx729618/article/details/51860699)

[Java NIO （四） 选择器（Selector）](https://www.cnblogs.com/qq-361807535/p/6670529.html)
