## ISpring-cloud

>  本项目整合spring-cloud与k8s（kubernets）实现微服务的架构。并提供两种实现方式
>
> 1. spring-cloud on k8s
>
>    使用spring-cloud的原生云架构，将此架构部署在k8s上。
>
> 2. spring-cloud in k8s
>
>    使用spring-cloud-kubernets组建，实现spring-cloud与k8s的深度整合。



## 准备工作

### 操作系统

> 本项目使用的操作系统为ubuntu20.04，其他的非linux的项目不能保证可以正常使用。
>
> windows下需要解决安装docker的问题

### kubernetes 集群

> 需要一个已经安装好的k8s集群，并且保证当前工作主机可以访问集群（需要配置~./kube/config）
>
> 本项目测试环境的集群地址为bjrdc17:6443

### harbor

> 需要安装一个docker的registies服务，本项目测试使用的harbor，harbor的地址为https://bjrdc206.reg

### docker

> 需要安装docker，并进行相关的配置
>
> ```
> cat > /etc/docker/daemon.json <<EOF
> {
>   "registry-mirrors": [
> 	"https://1nj0zren.mirror.aliyuncs.com",
>         "https://docker.mirrors.ustc.edu.cn",
>         "http://f1361db2.m.daocloud.io",
>         "https://registry.docker-cn.com"],
>   "graph": "/TOOLS/DOCKER_REGISTRY",
>   "exec-opts": ["native.cgroupdriver=systemd"],
>   "log-driver": "json-file",
>   "log-opts": {
>     "max-size": "100m"
>   },
>   "storage-driver": "overlay2",
>   "insecure-registries":["bjrdc206.reg"]
> }
> EOF
> ```



###  使用组件

| 组件         | 版本                     |
| ------------ | ------------------------ |
| eclipse      | 2020.03                  |
| maven        | 3.6.0                    |
| java         | 1.8.0_131                |
| docker       | 19.03.8                  |
| harbor       | Version v1.10.3-6990ccaa |
| kubernetes   | v1.18.5                  |
| spring-boot  |                          |
| spring-cloud |                          |



## 项目结构

├── spring-cloud-common
│   ├── spring-cloud-config		spring-cloud中的配置服务器
│   └── spring-cloud-support	sping-cloud相关的工具类
├── spring-cloud-k8s
│   ├── k8s-consumer
│   └── k8s-provider
├── spring-cloud-kubernetes-examples
│   ├── kubernetes-hello-world-example
│   ├── kubernetes-leader-election-example
│   └── kubernetes-reload-example
├── spring-cloud-sample
│   ├── spring-cloud-consumer
│   ├── spring-cloud-dashboard
│   ├── spring-cloud-eureka
│   ├── spring-cloud-provider
│   ├── spring-cloud-zipkin
│   └── spring-cloud-zuul
└── spring-cloud-version
    ├── spring-cloud-version-k8s
    └── spring-cloud-version-local



## spring-cloud-sample

### 基本思路

将本地可以执行的spring-cloud项目迁移到k8s上，实现spring-cloud的微服务的特性。需要解决的问题有

1. spring-boot项目的docker化
2. docker push到register
3. 生成k8s的yaml文件，并启动service
4. 通过ingress实现service的暴露

### kubernetes-maven-plugin

#### docker



#### k8s



### actuator/health



## spring-cloud-k8s



## spring-cloud-common



## spring-cloud-version

