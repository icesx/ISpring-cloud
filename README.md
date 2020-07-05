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

## 基本概念

1. enureka

   > spring自带的服务发现，也可以被其他的组件替换。

2. feign

   > 客户端负载均衡，使用注解的方式使用，很优雅

3. ribbon

   > 客户端负载均衡，需要resttemplate配合使用

4. zuul

   > 网管服务，可以将用户请求代理到不同的service上

5. zipkin

   > 服务追踪

   

## 项目结构

├── spring-cloud-common
│   ├── spring-cloud-config
│   └── spring-cloud-support
├── spring-cloud-k8s
│   ├── spring-cloud-k8s-consumer
│   ├── spring-cloud-k8s-gateway
│   ├── spring-cloud-k8s-init-yaml
│   └── spring-cloud-k8s-provider
├── spring-cloud-sample
│   ├── spring-cloud-consumer
│   ├── spring-cloud-dashboard
│   ├── spring-cloud-eureka
│   ├── spring-cloud-provider
│   ├── spring-cloud-zipkin
│   └── spring-cloud-zuul
└── spring-cloud-version
    ├── spring-cloud-version-k8s
    ├── spring-cloud-version-local
    └── spring-cloud-version-public



## spring-cloud-sample

> 使用spring-cloud的原生组件，将整个组件打包成docker，部署到k8s上。

### 基本思路

将本地可以执行的spring-cloud项目迁移到k8s上，实现spring-cloud的微服务的特性。需要解决的问题有

1. spring-boot项目的docker化

2. docker push到register

3. 生成k8s的yaml文件，并启动service

4. 通过ingress实现service的暴露

5. 微服务的健康检查

   

### kubernetes-maven-plugin

> kmp 是一个从maven到k8s的插件，按照官方的说法，其是从著名的fabric8迁移过来的，用的也是fabric8的kubernetes-client相关api。可以使用
>
> 1. 自动打包image（k8s:build）
> 2. image推送到registies服务器(k8s:push)
> 3. 自动生成k8s的yaml文件（自动识别spring-boot的配置文件）(k8s:resource)
> 4. 调用k8s的api推送yaml的文件，并执行(k8s:deploy)



> kmp的配置如下
>
> ```
> 	<properties>
> 		<k8s.plugin.assemble.name>k8s</k8s.plugin.assemble.name>
> 		<harbor.local>bjrdc206.reg</harbor.local>
> 		<k8s.master.url>https://172.16.15.17:6443</k8s.master.url>
> 		<docker.buildArg.JAR_FILE>${k8s.plugin.assemble.name}/${project.build.finalName}.jar</docker.buildArg.JAR_FILE>
> 
> 	</properties>
>     	<build>
> 		<plugins>
> 				<plugin>
> 					<groupId>org.eclipse.jkube</groupId>
> 					<artifactId>kubernetes-maven-plugin</artifactId>
> 					<version>1.0.0-alpha-4</version>
> 					<configuration>
> 						<registry>${harbor.local}</registry>
> 						<authConfig>
> 							<username>admin</username>
> 							<password>Harbor12345</password>
> 						</authConfig>
> 						<namespace>bjrdc-dev</namespace>
> 						<kubernetesManifest>${project.build.directory}/jkube/kubernetes.yml</kubernetesManifest>
> 						<targetDir>${project.build.directory}/jkube</targetDir>
> 						<images>
> 
> 							<image>
> 								<registry>${harbor.local}</registry>
> 								<name>bjrdc-dev/${project.artifactId}:${project.version}</name>
> 								<alias>${project.artifactId}</alias>
> 								<build>
> 									<assembly>
> 										<name>${k8s.plugin.assemble.name}</name>
> 									</assembly>
> 									<contextDir>${project.basedir}</contextDir>
> 									<dockerFile>${project.basedir}/src/main/docker/Dockerfile</dockerFile>
> 									<!-- <cleanup>remove</cleanup> -->
> 								</build>
> 							</image>
> 						</images>
> 						<access>
> 							<masterUrl>${k8s.master.url}</masterUrl>
> 						</access>
> 					</configuration>
> 				</plugin>
> 		</plugins>
> 	</build>
> ```
>
> 

#### docker



#### k8s



### actuator/health



## spring-cloud-k8s



## spring-cloud-common



## spring-cloud-version

