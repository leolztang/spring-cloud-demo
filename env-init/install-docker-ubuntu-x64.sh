#!/bin/bash

#检查系统版本，这份自动安装文件只适用于ubuntu 14/16系统
ver="`cat /etc/issue`"
echo $ver
if [[ $ver == *Ubuntu*14.* ||  $ver == *Ubuntu*16.* ]]
then
    #安装docker, docker不能重复安装，所以已经安装，这不再重复执行安装步骤
    docker_ver="`docker -v`"
    need_logout=0
    if [[ $docker_ver != *Docker*version* ]]
    then
        if [[ $ver == *Ubuntu*14.* ]]
        then
            apt-get update
            apt-get install -y linux-image-extra-$(uname -r) linux-image-extra-virtual
        fi

	# step 1: 安装必要的一些系统工具
	apt-get update
	apt-get -y install apt-transport-https ca-certificates curl software-properties-common
	# step 2: 安装GPG证书
	curl -fsSL http://mirrors.aliyun.com/docker-ce/linux/ubuntu/gpg | apt-key add -
	# Step 3: 写入软件源信息
	add-apt-repository "deb [arch=amd64] http://mirrors.aliyun.com/docker-ce/linux/ubuntu $(lsb_release -cs) stable"
	# Step 4: 更新并安装 Docker-CE
	apt-get -y update
	apt-get -y install docker-ce

        #给docker设置加速器
        mkdir -p /etc/docker
        echo '{"registry-mirrors": ["https://mirror.ccs.tencentyun.com"]}' > /etc/docker/daemon.json
        systemctl daemon-reload
        systemctl restart docker
        need_logout=1
    fi


    echo "开始检查安装结果..."
    docker_ver="`docker -v`"
    if [[ $docker_ver != *Docker*version* ]]
    then
        echo "docker没有安装成功"
        exit -1
    else
        echo $docker_ver
    fi


    if [ $need_logout == 1 ]
    then
        echo "将添加用户$USER到docker组"
        usermod -aG docker $USER
        echo "docker用户配置将在logout之后login生效，系统将立即自动logout，稍后请重新登录"
        echo "如果非登录shell，请手动logout"
	logout
    fi
else
    echo "当前版本不支持，该脚本只支持ubuntu 16和14版本操作系统"
fi
