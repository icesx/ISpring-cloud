k8s-consumer
============
> 集成spring-cloud-kubernetes，并调用`spring-cloud-k8s-provider`的相关服务

### 准备工作

> 将`spring-cloud-k8s-provider`部署到k8s集群上。详细见`spring-cloud-k8s-provider/readme.md`

> 当前主机通过vpn链接到k8s集群，并设置了dns为`10.96.0.10`（k8s的cluster.local的域名）

### 启动与测试

直接在eclipse上启动`cn.xportal.sc.consumerK8sConsumerApplication`通过如下的命令访问服务

```
curl localhost:8086/sc-k8s-consumer/feign/list
curl localhost:8086/sc-k8s-consumer/ribbon/list
```

### 注意事项

**namespace**

> 在application.yml文件中，需要设置provider的namespace
>
> ```
> spring-cloud-k8s-provider:
>   ribbon:
>     KubernetesNamespace: bjrdc-dev
> ```
>
> spring-cloud-k8s-provider为provider在k8s集群中的namespace，否则consumer无法找到provider的service