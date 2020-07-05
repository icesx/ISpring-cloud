spring-cloud-provider on k8s
=====
### 原理

使用spring-cloud-config作为配置中心，不使用k8s的configmap

### 测试

> 查询config
>
> ```
> curl http://localhost:8093/sc-k8s-provider/index/
> ```
>
> 查询list
>
> ```
> curl http://localhost:8093/sc-k8s-provider/index/list
> ```
>
> 