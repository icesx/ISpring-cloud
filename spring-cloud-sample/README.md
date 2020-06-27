ISpring-cloud
====

## 准备工作

> 本地执行不需要其他准备，基本的java环境即可
>
> 如果在k8s执行，不论是spring-cloud原生，换是k8s原生都需要k8s的环境，需要安装如下套件
>
> 1. 本地需要安装docker
> 2. 远端需要安装k8s集群
> 3. 需要安装docker的registies,Harbor



## spring-cloud-sample

> 使用spring-cloud的原生微服务，提供两种执行模式
>
> 1. local:本地执行
> 2. k8s:在k8s上执行

#### 打包
1. 本地打包

   ```
   mvn package
   ```

2. 打包到docker

   ```
   mvn package dockerfile:push  
   ```
## 本地执行
> 确保profile=local
```
cat ISpring-cloud/spring-cloud-sample/spring-cloud-enviroment/src/main/java/bootstrap.yml

spring:
  profiles:
    active: local
  cloud:
    config: local
    
```

> 启动顺序

1. eureka
2. spring-cloud-config
3. dashboard consumer provider zipin zuul

#### 访问
1. consumer
> 使用ribbon访问 
> http://localhost:8200/ribbon/hello

> http://localhost:8200/feign/list

## spring-cloud-k8s



## spring-cloud-common