spring-cloud-k8s
==========

> spring-cloud in k8s。基于云原生的概念将spring-cloud迁移到k8s上

### spring-cloud-k8s-init-yaml

> 需要对k8s集群进行一些面向spring-cloud的初始化工作
>
> 1. bjrdc-cluster-role.yaml
>
>    ```
>    apiVersion: rbac.authorization.k8s.io/v1
>    kind: ClusterRole
>    metadata:
>      name: bjrdc-cr
>      namespace: bjrdc-dev
>    rules:
>    - apiGroups: [""]
>      resources: ["services", "endpoints", "pods","configmaps"]
>      verbs: ["get", "list", "watch"]
>    ```
>
>    创建名称为bjrdc-dev:bjrdc-cr的clusterRole，并分配权限为get、list、watch资源为services、endponits、configmaps
>
> 2. bjrdc-cluster-role-binding.yaml
>
>    ```
>    apiVersion: rbac.authorization.k8s.io/v1
>    kind: ClusterRoleBinding
>    metadata:
>      name: bjrdc-rb
>      namespace: bjrdc-dev
>    roleRef:
>      apiGroup: rbac.authorization.k8s.io
>      kind: ClusterRole
>      name: bjrdc-cr
>    subjects:
>    - kind: ServiceAccount
>      name: default
>      namespace: bjrdc-dev
>    ```
>
>    将bjrdc-dev:default这个serviceaccount绑定到bjrdc-dev:bjrdc-cr上。
>
>    bjrdc-dev:default为默认创建的在bjrdc-dev nampspace下的serviceaccount，用于从pod上获取到cluster上的资源。

### spring-cloud-k8s-gateway

> spring-cloud 自带一个gateway的组件，用于替换zuul2.0。在真实的环境中，k8s集群中会提供ingress网关，那么他们两个的区别和联系是什么呢？
>
> 1. ingress，提供集群级别的网管功能，一般需要集群级权限才能进行设置。而且规则比较简单一般通过路径进行设置。
> 2. spring-cloud-gateway，提供应用级别的路由规则，由应用自行配置，配置规则更加业务化。

### spring-cloud-k8s-provider

> 提供服务给其他的service，是一个普通的spring-boot的应用。

### spring-cloud-k8s-consumer

> 集成spring-cloud-kubernetes，并调用`spring-cloud-k8s-provider`的相关服务