消费者
============
### Config
	config on git
###Hystrix
断路器
###Ribbon
负载均衡 
curl http://localhost:8200/ribbon/hello

### feign to provider 
curl http://localhost:8200/feign/list

Feign是负责服务间内部调用消费，以及负责均衡的组件，但是和Eureka不一样，Feign没有Server这么一说，Feign本身就是个Client，它是可插拔的

### feign vs ribbon
目前，在Spring cloud 中服务之间通过restful方式调用有两种方式 

+ restTemplate+Ribbon 

> 使用reset 请求

+ feign

> 使用注解的方法请求，更加优雅

最重要的是：它俩都支持软负载均衡