服务发现
=====
2.0已闭源

## on k8s

> 将eureka 部署到k8s，的配置

### 本地安装docker

1. ubuntu

   ```
   sudo apt install docker.io
   cat > /etc/docker/daemon.json <<EOF
   {
     "registry-mirrors": [
   	"https://1nj0zren.mirror.aliyuncs.com",
           "https://docker.mirrors.ustc.edu.cn",
           "http://f1361db2.m.daocloud.io",
           "https://registry.docker-cn.com"],
     "graph": "/TOOLS/DOCKER_REGISTRY",
     "exec-opts": ["native.cgroupdriver=systemd"],
     "log-driver": "json-file",
     "log-opts": {
       "max-size": "100m"
     },
     "storage-driver": "overlay2",
     "insecure-registries":["bjrdc206.reg"]
   }
   EOF
   
   ```

   bjrdc206.reg ：harbor的地址

   graph：本地docker存储目录

2. window

   自行查阅docker官方资料

3. mac

   自行查阅docker官方资料

### 部署到k8s

> 如下命令将执行如下流程
>
> 1. 打包本地的spring-boot项目
> 2. 并将jar包打包到docker中，使用/src/main/docker/Dockerfile
> 3. 将docker image push dao registies
> 4. 生成k8s的yaml文件，一部分使用/src/main/jkuber/*中的模板，一部分按照环境生成
> 5. 使用k8s的客户端程序将，yaml提交到k8s中，创建deployment、service、pod
> 6. 需要提前将master服务器~/.kube/config文件创建到本地的~/.kube/config



> maven 的命令有
>
> ```
> mvn clean package spring-boot:repackage  k8s:build k8s:push k8s:resource k8s:undeploy k8s:deploy
> ```
>
> ​	
>
> | 命令         | 说明                          |
> | ------------ | ----------------------------- |
> | k8s:build    | 创建docker images             |
> | k8s:push     | 推送 image到harbor            |
> | k8s:resource | 创k8s的yaml文件               |
> | k8s:undeploy | 从k8s集群卸载之前的depolyment |
> | k8s:deploy   | 部署yaml文件到k8s集群         |
>
> 



> 或者执行如下命令，前提是需要将命令绑定到maven的phase
>
> ```
> <executions>
>     <execution>
>     	<phase>install</phase>
>     <goals>
>     <goal>resource</goal>
>         <goal>build</goal>
>         <goal>push</goal>
>         <goal>deploy</goal>
>     </goals>
>     </execution>
> </executions>
> ```
>
> 
>
> ```
> mvn clean package install
> ```
>
> 



### k8s访问

通过如下地址请求k8s上的服务，需要提前在本地的hosts文件增加域名

```
172.16.15.17	ingress.bjrdc17
```



1.eureka

```
http://ingress.bjrdc17:30080/sc-eureka/
```

2.consumer

```

```



### 验证

> 部署完成后,可以通过clasterIP访问，也可以使用域名访问，使用域名访问，需要先将cluster的dnsserver增加到主机上，默认为10.96.0.10

```
curl spring-cloud-eureka.bjrdc-dev.svc.cluster.local:8080/actuator/health
```



