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

### 打包镜像

