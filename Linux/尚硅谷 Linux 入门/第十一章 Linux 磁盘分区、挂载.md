# Linux 磁盘分区、挂载
## 分区基本知识
### 分区的方式
1. mbr 分区
    - 最多支持四个主分区
    - 系统只能安装在主分区
    - 扩展分区要占一个主分区
    - MBR 最大只支持 2TB ，但拥有最好的兼容性

2. gtp 分区
    - 支持无线多个主分区(但操作系统可能限制，比如 Windows 下最多 128 个分区)
    - 最大支持 18EB 的大容量(EB=1024PB，PB=1024TB)
    - Windows7 64 位以后支持 gtp

## Linux 分区
### 原理介绍
1. 概述
    - Linux 来说无论有几个分区，分给哪一个目录使用，它归根到底就只有一个根目录，一个独立且唯一的文件结构，Linux 中每个分区都是用来组成整个文件系统的一部分。
    - Linux 采用了一种叫载入的处理方法，它的整个文件系统中包含了一整套的文件目录，且将一个分区和目录联系起来，这时要载入的一个分区将使它的存储空间在一个目录下获得。

### 硬盘说明
1. 概述
    - Linux 硬盘分 IDE 硬盘和 SCCI 硬盘，目前基本上是 SCSI 硬盘。
    - 对于 IDE 硬盘，驱动器表示为 hdx~ ，其中 hd 表明分区所在设备的类型，这里是指 IDE 硬盘了。
    - x 为盘号（a 为基本盘，b 为基本从属盘，c 为辅助主盘，d 为辅助从属盘），~ 代表分区，前四个分区用数字 1 到 4 表示，它们是主分区或扩展分区，从 5 开始就是逻辑分区。
    - 例如，hda3 表示为第一个 IDE 硬盘上的第三个主分区或扩展分区，hdb2 表示为第二个 IDE 硬盘上的第二个主分区或扩展分区。
    - 对于 SCSI 硬盘则标识为 sdx~ ，SCSI 硬盘是用 sd 来表示分区所在设备的类型的，其余和 IDE 硬盘的表示方法一样。

### 查看硬盘挂载情况
1. 查看系统的分区和挂载情况
```
lsblk -f
NAME   FSTYPE LABEL UUID                                 MOUNTPOINT
vda
└─vda1 ext4         9f2d3e15-a78a-4f3d-8385-0165b4b67864 /
```
2. 显示分区大小
```
lsblk -f
NAME   MAJ:MIN RM SIZE RO TYPE MOUNTPOINT
vda    253:0    0  40G  0 disk
└─vda1 253:1    0  40G  0 part /
```

## 挂载的经典案例
1. 虚拟机添加硬盘
```
(1) 在虚拟机菜单中，选择设置，然后设备列表里添加硬盘，然后一路下一步，中间只有选择磁盘大小的地方需要修改，至到完成。然后重启系统。
```

2. 分区
```
(1) 分区命令 fdisk /dev/sdb
(2) 开始对 /sdb 分区
    m 显示命令列表
    p 显示磁盘分区 同 fdisk -l
    n 新增分区
    d 删除分区
    w 写入并退出
(3) 说明：开始分区后输入 n ，新增分区，然后选择 p ，分区类型为主分区。两次回车默认剩余全部空间。最后输入 w 写入分区并退出。若不保存退出输入 q 。
```
3. 格式化
```
mkfs -t ext4 /dev/sdb1  # ext4 是分区类型
```
4. 挂载
```
(1) 挂载：将一个分区与一个目录联系起来，
(2) mount 设备名称 挂载目录
(3) 卸载：umount 设备名称或者挂载目录
(4) 用命令行挂载重启后会失效
```
5. 设置可以自动挂载
```
(1) 永久挂载：通过修改 /etc/fstab 实现挂载
(2) 添加完成后执行 mount -a 即刻生效
```

## 磁盘情况查询
### 查询系统整体磁盘使用情况
1. 基本语法
```
(1) df -lh
```

### 查询指定目录的磁盘占用情况
1. 基本语法
```
du -h /目录
```
2. 查询指定目录的磁盘占用情况
```
-s 指定目录占用大小汇总
-h 带计量单位
-a 含文件
--max-depath=1 子目录深度
-c 列出明细的同时，增加汇总值
```
3. 举例
```
du -ach --max-depth=1 子目录深度
```

## 工作实用指令
1. 统计 /home 文件夹下文件的个数
```
ls -l /home | grep "^-" | wc -l
```
2. 统计 /home 文件夹下目录的个数
```
ls -l /home | grep "^d" | wc -l
```
3. 统计 /home 文件夹下文件的个数，包括子文件夹里的
```
ls -lR /home | grep "^-" | wc -l
```
4. 统计文件夹下目录的个数，包括子文件夹里的
```
ls -lR /home | grep "^d" | wc -l
```
5. 以树状显示目录结构
```
tree # 默认当前路径下的目录结构
```


