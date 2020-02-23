首先要启动
hdfs
[hadoop@hadoop134 hadoop-2.7.2]$ sbin/start-dfs.sh
[hadoop@hadoop134 hadoop-2.7.2]$ sbin/start-yarn.sh



```java
Relative path in absolute URI: ${system:java.io.tmpdir%7D/$%7Bsystem:user.name%7D
```
第一个坑,配置的时候 没有指定创建临时文件,自动创建临时文件失败,
```xml
在hive下创建临时IO的tmp文件夹。然后将路径配置到hive-site.xml的下列参数中


hive.querylog.location 
/usr/local/hive/iotmp 
Location of Hive run time structured log file 

hive.exec.local.scratchdir 
/usr/local/hive/iotmp 
Local scratch space for Hive jobs 

hive.downloaded.resources.dir 
/usr/local/hive/iotmp 
Temporary local directory for added resources in the remote file system. 

保存，重启hive即可。
```
第二个坑,配置mysql为元数据库的时候,一定要仔细检查原来的内存数据库配置文件是否删除了



管理表与外部表的互相转换
```xml
（1）查询表的类型
hive (default)> desc formatted student2;
Table Type:             MANAGED_TABLE
（2）修改内部表student2为外部表
alter table student2 set tblproperties('EXTERNAL'='TRUE');
（3）查询表的类型
hive (default)> desc formatted student2;
Table Type:             EXTERNAL_TABLE
（4）修改外部表student2为内部表
alter table student2 set tblproperties('EXTERNAL'='FALSE');
（5）查询表的类型
hive (default)> desc formatted student2;
Table Type:             MANAGED_TABLE
注意：('EXTERNAL'='TRUE')和('EXTERNAL'='FALSE')为固定写法，区分大小写！

```
数据修复
```xml
执行修复命令
hive> msck repair table dept_partition2;

```

load 数据的时候的区别
```bash
load data [local] 
inpath '/opt/module/datas/student.txt' 
[overwrite] into table student
 [partition (partcol1=val1,…)];
```
```text
（1）load data:表示加载数据
（2）local:表示从本地加载数据到hive表；否则从HDFS加载数据到hive表
（3）inpath:表示加载数据的路径
（4）overwrite:表示覆盖表中已有数据，用新的数据替换原有的数据,否则表示追加
（5）into table:表示加载到哪张表
（6）student:表示具体的表
（7）partition:表示上传到指定分区

load 本地数据是 上传 ,load HDFS 数据则是移动数据
export和import主要用于两个Hadoop平台集群之间Hive表迁移。
```
后台启动hiveserver2
```bash
nohup bin/hiveserver2 > s2.log 2>&1 &
```
坑
```text
1  datagrip 链接 hive数据库 注意事项
        <property>
            <name>hadoop.http.staticuser.user</name>
            <value>root</value>
        </property> 
        下面这两个 hadoop 用户名是什么就填什么,切记
		<property>
            <name>hadoop.proxyuser.hadoop.hosts</name>
            <value>*</value>
        </property> 
		<property>
            <name>hadoop.proxyuser.hadoop.groups</name>
            <value>*</value>
        </property>
        
2  hive 2.x系统 需要初始化元数据库
      ./schematool -initSchema -dbType mysql 

```
```text
1．开启hive中间传输数据压缩功能
hive (default)>set hive.exec.compress.intermediate=true;
2．开启mapreduce中map输出压缩功能
hive (default)>set mapreduce.map.output.compress=true;
3．设置mapreduce中map输出数据的压缩方式
hive (default)>set mapreduce.map.output.compress.codec=
 org.apache.hadoop.io.compress.SnappyCodec;

```
1.格式存储主要 是  orc 和parquet
2.根据引擎选择存储方式,
3.hive 用 orc,spark 用parquet,
4,一般 采用  snappy压缩

##hive优化
```text
1.fetch抓取 ,默认 不用改
2.本地模式 
    set hive.exec.mode.local.auto=true;  //开启本地mr
    //设置local mr的最大输入数据量，当输入数据量小于这个值时采用local  mr的方式，默认为134217728，即128M
    set hive.exec.mode.local.auto.inputbytes.max=50000000;
    //设置local mr的最大输入文件个数，当输入文件个数小于这个值时采用local mr的方式，默认为4
    set hive.exec.mode.local.auto.input.files.max=10;
3.表的优化
    1.小表,大表join
        新版的hive已经对小表JOIN大表和大表JOIN小表进行了优化。小表放在左边和右边已经没有明显区别。
    2.大表join  大表
    3.MapJoin(小表join大表)
        （1）设置自动选择Mapjoin
        set hive.auto.convert.join = true; 默认为true
        （2）大表小表的阈值设置（默认25M一下认为是小表）：
        set hive.mapjoin.smalltable.filesize=25000000;
    4.group by     
        开启Map端聚合参数设置
        	（1）是否在Map端进行聚合，默认为True
        set hive.map.aggr = true
        （2）在Map端进行聚合操作的条目数目
        set hive.groupby.mapaggr.checkinterval = 100000
        （3）有数据倾斜的时候进行负载均衡（默认是false）
        set hive.groupby.skewindata = true
    5.count

```