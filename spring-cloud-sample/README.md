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

### maven

> maven的基本配置如下，使用的插件是kubernetes-maven-plugin,这个插件是从fabric8迁移过来的，更简单一点
>
> 1. 工作原理：插件运行期间，在构建docker images的时候，会有一个工作目录，该工作目录默认为`spring-cloud-eureka/target/docker/bjrdc-dev/spring-cloud-eureka/0.0.1-SNAPSHOT/build`  插件会自动将Dockerfile copy到该目录，并使用`docker build `构建镜像，
>
> 2. assemble:在工作目录中的一个装配目录，如下插件配置中，将项目的jar文件copy到该目录下，用于构建docker镜像
>
> 3. docker.buildArg.JAR_FILE 为传给Dockerfile的变量
>
>    ```
>    FROM java:8-jdk
>    ARG JAR_FILE
>    ADD ${JAR_FILE} app.jar
>    ENTRYPOINT ["java", "-jar", "/app.jar"]
>    ```
>
> 4. harbor.local 是harbor地址
>
> 5. k8s.master.url  为k8s的master的地址，可以在master上通过`kubectl cluster-info`查看
>
> 6. 

```
<properties>
		<k8s.plugin.assemble.name>k8s</k8s.plugin.assemble.name>
		<harbor.local>bjrdc206.reg</harbor.local>
		<k8s.master.url>https://172.16.15.17:6443</k8s.master.url>
	<docker.buildArg.JAR_FILE>${k8s.plugin.assemble.name}/${project.build.finalName}.jar</docker.buildArg.JAR_FILE>
</properties>
<plugins>
<plugin>
				<groupId>org.eclipse.jkube</groupId>
				<artifactId>kubernetes-maven-plugin</artifactId>
				<version>1.0.0-alpha-4</version>
				<configuration>
					<registry>${harbor.local}</registry>
					<authConfig>
						<username>admin</username>
						<password>Harbor12345</password>
					</authConfig>
					<namespace>bjrdc-dev</namespace>
					<kubernetesManifest>${project.build.directory}/jkube/kubernetes.yml</kubernetesManifest>
					<targetDir>${project.build.directory}/jkube</targetDir>
					<images>

						<image>
							<registry>${harbor.local}</registry>
							<name>bjrdc-dev/${project.artifactId}:${project.version}</name>
							<alias>${project.artifactId}</alias>
							<build>
								<assembly>
									<name>${k8s.plugin.assemble.name}</name>
								</assembly>
								<contextDir>${project.basedir}</contextDir>
								<dockerFile>${project.basedir}/src/main/docker/Dockerfile</dockerFile>
								<!-- <cleanup>remove</cleanup> -->
							</build>
						</image>
					</images>
					<access>
						<masterUrl>${k8s.master.url}</masterUrl>
					</access>
				</configuration>
				<executions>
					<execution>
						<phase>install</phase>
						<goals>
							<goal>resource</goal>
							<goal>build</goal>
							<goal>push</goal>
							<goal>deploy</goal>
						</goals>
					</execution>
				</executions>
			</plugin>
			</plugins>
```



#### 打包

1. 本地打包

   ```
   mvn package
   ```

2. 打包到docker

   ```
   mvn package dockerfile:push  
   ```
## 测试与部署
### 本地执行

#### 配置

> 在eclipse中或者通过`java -jar xxx.jar`执行的时候，需要将依赖的version修改为
>
> ```
> <dependency>
>     <groupId>cn.xportal.sc</groupId>
>     <artifactId>spring-cloud-version-local</artifactId>
>     <version>0.0.1-SNAPSHOT</version>
> </dependency>
> ```
>
> 该项目中存在一个bootstrap.yaml中定义了profile=local
>
> ```
> spring:
>   profiles:
>     active: local
>   cloud:
>     config: local
> ```
>
> 注：该profile为spring-boot的功能，定义了profile为local的时候，spring-boot在启动的时候，会使用所有的带`-local.`的properties或者yml



> 启动顺序

1. eureka
2. spring-cloud-config
3. dashboard consumer provider zipin zuul

#### 访问
1. consumer
> 使用ribbon访问 
> http://localhost:8200/ribbon/hello

> http://localhost:8200/feign/list

### k8s执行

> 在k8s上执行与local执行的区别为
>
> 1. 需要将version的依赖修改为k8s
>
>    ```
>    <dependency>
>        <groupId>cn.xportal.sc</groupId>
>        <artifactId>spring-cloud-version-k8s</artifactId>
>        <version>0.0.1-SNAPSHOT</version>
>    </dependency>
>    ```
>
> 2. 使用maven命令将所有的微服务部署到k8s
>
>    ```
>    mvn clean package deploy
>    ```
>
> 3. 通过k8s相关命令查看部署的情况
>
>    ```
>    
>    ```
>
>    

## spring-cloud-k8s



## spring-cloud-common

