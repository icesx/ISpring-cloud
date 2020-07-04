spring-cloud-consumer
============
### Hystrix
断路器

http://localhost:8086/sc-consumer/actuator/hystrix.stream
### 启动与访问

本地执行`cn.xportal.sc.consumer.ConsumerApplication`

#### Ribbon访问

```
curl http://localhost:8086/sc-consumer/ribbon/list
curl http://ingress.bjrdc17:30080/sc-consumer/ribbon/list
```

#### feign 

```
curl http://localhost:8086/sc-consumer/feign/list
curl http://clusterIp:8086/sc-consumer/feign/list
```
#### 集群访问

> 通过如下命令部署到集群中
>
> ```
> clean package spring-boot:repackage  k8s:build  k8s:resource k8s:push k8s:undeploy k8s:deploy
> ```
>
> 通过如下命令访问
>
> ```
> curl http://ingress.bjrdc17:30080/sc-consumer/feign/list
> ```
>
> 通过zuul访问
>
> ```
> curl ingress.bjrdc17:30080/sc-zuul/consumer/sc-consumer/feign/users
> ```
>
> 

### 知识点

Feign是负责服务间内部调用消费，以及负责均衡的组件，但是和Eureka不一样，Feign没有Server这么一说，Feign本身就是个Client，它是可插拔的

### feign vs ribbon
目前，在Spring cloud 中服务之间通过restful方式调用有两种方式 

> 1. restTemplate+Ribbon 
>
>    使用reset 请求
>
> 2. feign
>
>    使用注解的方法请求，更加优雅

最重要的是：它俩都支持软负载均衡

### bootstrap
> 使用bootstrap的原因是，bootstrap比application优先级高，故类似加载configserver这种事情需要放到bootstrap中做


