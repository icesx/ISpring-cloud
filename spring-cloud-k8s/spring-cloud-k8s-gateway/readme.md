spring-cloud-k8s-gateway
=====

### 部署

通过如下命令部署到k8s

```
mvn clean package spring-boot:repackage  k8s:build  k8s:resource k8s:push k8s:undeploy k8s:deploy
```



### 准备

> 开启vpn，将`spring-cloud-k8s-provider，`spring-cloud-k8s-consumer`部署到k8s

### 启动

直接启动`cn.xportal.sc.gateway.GatewayApplication`

### 本地访问

```
curl localhost:8097/provider/index/list
curl localhost:8097/consumer/feign/list
```

### 集群访问

通过clusterIp访问

```
curl spring-cloud-k8s-gateway.bjrdc-dev.svc.cluster.local:8097/consumer/feign/list
```



通过ingress访问

```
curl ingress.bjrdc17:30080/sc-gateway/consumer/feign/list
```

