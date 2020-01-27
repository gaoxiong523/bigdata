# bigdata
##大数据学习笔记
#centos6.8搭建Hadoop集群
```text
本机192.168.2.126
192.168.150.130 纯净
克隆机器后, 
1
vi /etc/udev/rules.d/70-persistent-net.rules 
删掉原来的网卡.
# PCI device 0x8086:0x100f (e1000)
SUBSYSTEM=="net", ACTION=="add", DRIVERS=="?*", ATTR{address}=="00:0c:29:a0:fe:a2", ATTR{type}=="1", KERNEL=="eth*", NAME="eth0"

# PCI device 0x8086:0x100f (e1000)
SUBSYSTEM=="net", ACTION=="add", DRIVERS=="?*", ATTR{address}=="00:0c:29:0e:00:59", ATTR{type}=="1", KERNEL=="eth*", NAME="eth1"
删掉 eth0 (快捷键 dd)
然后将第二个改为eth0,复制网卡地址 备用00:0c:29:0e:00:59

# PCI device 0x8086:0x100f (e1000)

# PCI device 0x8086:0x100f (e1000)
SUBSYSTEM=="net", ACTION=="add", DRIVERS=="?*", ATTR{address}=="00:0c:29:0e:00:59", ATTR{type}=="1", KERNEL=="eth*", NAME="eth0"
2
修改网卡
vi /etc/sysconfig/network-scripts/ifcfg-eth0

DEVICE="eth0"
BOOTPROTO="dhcp"
HWADDR="00:0c:29:0e:00:59" //上一步复制的网卡地址
IPV6INIT="yes"
NM_CONTROLLED="yes"
ONBOOT="yes"
TYPE="Ethernet"
UUID="43019a58-57b6-4a46-a49c-8395a8a4026c"

IPADDR=192.168.150.131
GATEWAY=192.168.150.255
DNS1=192.168.150.255

3
[root@hadoop130 Desktop]# hostname
hadoop130
[root@hadoop130 Desktop]# vi /etc/sysconfig/network
NETWORKING=yes
HOSTNAME=hadoop131

4,修改HOSTS

[root@hadoop130 Desktop]# vi /etc/hosts

127.0.0.1   localhost localhost.localdomain localhost4 localhost4.localdomain4 hadoop130
::1         localhost localhost.localdomain localhost6 localhost6.localdomain6
192.168.150.131 hadoop131
192.168.150.132 hadoop132
192.168.150.133 hadoop133
192.168.150.134 hadoop134
192.168.150.135 hadoop135
192.168.150.136 hadoop136
192.168.150.137 hadoop137
192.168.150.138 hadoop138
192.168.150.139 hadoop139


```
```text


vi /etc/profile
#JAVAHOME
export JAVA_HOME=/opt/module/jdk1.8.0_211
export PATH=$PATH:$JAVA_HOME/bin
#HADOOPHOME
export HADOOP_HOME=/opt/module/hadoop-2.8.5
export PATH=$PATH:$HADOOP_HOME/bin
export PATH=$PATH:$HADOOP_HOME/sbin

```