## zuul
> 提供集群外负载均衡

>路由是微服务架构中必须（integral ）的一部分，比如，“/” 可能映射到你的WEB程序上，”/api/users “可能映射到你的用户服务上，“/api/shop”可能映射到你的商品服务商。（注解：我理解这里的这几个映射就是说通过Zuul这个网关把服务映射到不同的服务商去处理，从而变成了微服务！）

Zuul是Netflix出品的一个基于JVM路由和服务端的负载均衡器.

### loadbanlance 



> 本地访问
> ```
> curl http://localhost:8084/consumer/feign/list
> curl http://localhost:8084/consumer/ribbon/list
> ```
> 

> 本地 网关访问
>
> ```
> curl http://localhost:8084/sc-zuul/consumer/sc-consumer/ribbon/list
> ```
>
> 集群访问
>
> ```
> curl http://ingress.bjrdc17:30080/sc-zuul/consumer/sc-consumer/ribbon/list
> 
> curl http://ingress.bjrdc17:30080/sc-zuul/consumer/sc-consumer/feign/list
> ```
>
> 