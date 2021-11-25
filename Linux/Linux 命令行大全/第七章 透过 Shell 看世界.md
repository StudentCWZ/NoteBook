# 第七章 透过 Shell 看世界
## 前言
```
1. 在本章，我们将介绍在按下 Enter 键时，命令行中发生的一些神奇事情。
2. 虽然我们会介绍 shell 的几个有趣而复杂的特性，但是我们只使用一条新命令来处理。
echo: 显示一行文本
```

## 扩展
### 基本介绍
```
1. 每次输入命令行按下 Enter 键时，bash 都会执行之前的对文本进行多重处理。
2. 前面已经见过一个简单的字符序列 (比如 * ) 在 shell 中被识别为多种意思的例子。产生这个结果的处理过程称为扩展 (expansion) 。
3. 有了扩展功能，再输入内容后，这些内容将在 Shell 对其执行之前被扩展成其他内容。
4. echo 是 shell 的一个内置命令，它执行的任务非常简单，即把文本参数内容打印到标准输出。
[me@linuxbox ~]$ echo this is a test
5. 上面例子相当简单，传递 echo 的任何参数都将显示出来。
6. 我们可以看看另一个例子
[me@linux ~]$ echo *
Desktop Documents ls-output.txt Music Pictures Public Templates Videos
7. * 字符意味着匹配文件名中的任意字符，shell 会在执行 echo 命令前把 * 扩展成其他内容 (在这个例子中，扩展为当前工作目录下的所有文件名) 。
8. 在按下 Enter 键的时候，shell 会在执行命令前自动扩展命令行中所有符合条件的字符，因此，echo 命令将不可能看到 * 字符，只能看到 * 字符扩展后的结果。
```

### 路径名拓展
```
1. 通过使用通配符来实现扩展机制称为路径名扩展 (pathname expansion) 。
2. 尝试前面章节中使用过的一些技术，将会发现它们实际上就是扩展。下面给定一个主目录，如下所示：
[me@linuxbox ~]$ ls
Desktop ls-output.txt Pictures Templates
Documents Music Public Videos
[me@linuxbox ~]$ echo D*
Desktop Documents
[me@linuxbox ~]$ echo *s
Documents Pictures Temolates Videos
[me@linuxbox ~]$ echo [[:upper:]]*
Desktop Documents Music Pictures Public Templates Videos
[me@linuxbox ~]$ echo /usr/*/share
/usr/kerberos/share /usr/local/share
```

### 波浪线扩展
```
1. 回顾前面对 cd 命令的介绍，你会发现波浪线字符 ~ 具有特殊的含义。
2. 如果把它用在一个单词的开头，那么它将被扩展为指定用户的主目录名；如果没有指定用户命名，则扩展为当前用户的主目录。
# 没有指定用户
[me@linuxbox ~]$ echo ~
/home/me
[me@linuxbox ~]$ echo ~foo
/home/foo
```

### 算术扩展
- 基本介绍
```
1. shell 支持通过扩展来运行算术表达式。这允许我们把 shell 提示符当做计算器来使用。
[me@linuxbox ~]$ echo $((2+2))
4
2. 算术扩展使用如下格式：
$((expression))     #  其中，expression 是包含数值和算术操作符的算术表达式
```
- 算术扩展操作符

|运算符|描述|
|:--:|:--:|
|`+`|加|
|`-`|减|
|`*`|乘|
|`/`|除 (但是记住，因为扩展只支持整数运算，所以结果也是整数) |
|`%`|取余，即余数|
|`**`|取幂|

- 算术扩展使用细节
```
1. 空格在算术表达式中是没有意义的，而且表达式是可以嵌套的。
[me@linuxbox ~]$ echo $(($((5**2))*3))
2. 你可以使用一对括号来组合多个子表达式。通过该技术，可以把上面的例子重写，用一个扩展来替代两个，可以得到同样的结果：
[me@linuxbox ~]$ echo $(((5**2)*3))
3. 下面的例子使用了除运算符和取余运算符，注意整数相除的结果。
[me@linuxbox ~]$ echo Five divided by two equals $((5/2))
Five divided by two equals 2
[me@linuxbox ~]$ echo with $((5%2)) left over.
with 1 left over.
```

### 花括号扩展
```
1. 花括号扩展 (brace expansion) 可能算是最奇怪的扩展方式了。有了它，你可以按照花括号里面的模式创建多种文本字符串。实例如下：
[me@linuxbox ~]$ echo Front-{A,B,C}-Back
Front-A-Back Front-B-Back Front-C-Back
2. 用于花括号扩展的模式信息可以包含一个称为前导字符 (preamble) 的开头部分和一个称为附言 (postscript) 的结尾部分。
3. 花括号表达式本身可以包含一系列逗号分隔符的字符串，也可以包含一系列整数或者单个字符。这里的模式信息不能包含内嵌的空白。
[me@linuxbox ~]$ echo Number_{1..5}
Number_1 Number_2 Number_3 Number_4 Number_5
4. 下面输出一系列逆序排列的字母。
[me@linuxbox ~]$ echo {Z..A}
Z Y X W V U T S R Q P O N M L K J I H G F E D C B A
5. 花括号最普遍的应用是创建一系列的文件或者目录。
[me@linuxbox ~]$ mkdir Pics
[me@linuxbox ~]$ cd Pics
[me@linuxbox Pics]$ mkdir {2009..2010}-0{1..9} {2009..2010}-{10..12}
[me@linuxbox Pics]$ ls
2009-01 2009-07 2010-01 2010-07
2009-02 2009-08 2010-02 2010-08
2009-03 2009-09 2010-03 2010-09
2009-04 2009-10 2010-04 2010-10
2009-05 2009-11 2010-05 2010-11
2009-06 2009-12 2010-06 2010-12
```

### 参数扩展
```
1. 参数扩展用在 shell 脚本中比直接用在命令行中更为有用。
2. 它的许多特性与系统存储小块数据以及给每小块数据命令的性能有关。很多这样的小块数据 (称为变量 [variable] 会更合适) 可用于扩展。
3. 例如，命名为 USER 的变量包含你的用户名，为了触发参数扩展，并显示出 USER 的内容，你可以进行如下操作：
[me@linuxbox ~]$ echo $USER
me
4. 想要查看可用的变量列表，试试如下操作：
[me@linuxbox ~]$ printenv | less
5. 对于其他的扩展类型来说，如果你误输入了一个模式，就不会发生扩展，这时 echo 命令将只是显示这些误输入的模式信息。但是对于参数扩展来说，如果变量名拼写错误，仍然会进行扩展，只不过结果是输出一个空字符串而已，如下所示：
[me@linuxbox ~]$ echo $SUER
[me@linuxbox ~]$
```

### 命令替换
```
1. 命令替换可以把一个命令的输出作为一个扩展模式使用，如下所示。
[me@linuxbox ~]$ echo $(ls)
Desktop Documents ls-output.txt Music Pictures Public Templates Videos
2. 另外一种用法：
[me@linuxbox ~]$ ls -l $(which cp)
-rwxr-xr-x 1 root root 71516 2012-12-05 08:58 /bin/cp
3. 这里，把 which cp 命令的运行结果作为 ls 命令的一个参数，因此我们无需知道 cp 程序的完整路径就能获得 cp 程序对应的列表。
4. 这个功能并不只是局限于简单的命令，也可以应用于整个管道中 (只不过只显示部分输出内容) 。
[me@linuxbox ~]$ file $(ls /usr/bin* | grep zip)
/usr/bin/bunzip2: symbolic link to 'bzip2'
/usr/bin/bzip2: ELF 32-bit LSB executable, Intel 80386, version 1 (SYSV), dynamically linked (uses shared libs)，for GNU/Linux 2.69, stripped
5. 在早期的 shell 程序中，存在命令替换的另一种语法，bash 也支持这种格式。它用反引号替代美元符号和括号，具体如下所示。
[me@linuxbox ~]$ ls -l `which cp`
-rwxr-xr-x 1 root root 71516 2012-12-05 08:58 /bin/cp
```

## 引用
### 基本介绍
```
1. shell 有多种方式可以执行扩展，现在我们来学习如何控制扩展。先看下面的例子。
[me@linuxbox ~]$ echo this  is a test
this is test
[me@linuxbox ~]$ echo The total is $100.00
The total is 00.00
2. 在第一个例子，shell 会对 echo 命令的参数列表进行单词分割 (word splitting) ，去除多余的空白。在第二个例子中，因为 $1 是一个未定义的变量，所以参数扩展把 $1 的值替换为空字符串。
3. shell 提供了一种称为引用 (quoting) 的机制，用来选择性地避免不想要的扩展。
```

### 双引号
```
1. 我们要看的第一种引用类型是双引号 (double quote) 。
2. 如果把文本放在双引号中，那么 shell 使用的所有特殊字符都将失去他们的特殊含义，而被看成普通字符。字符 $(美元符号)、\(反斜杠)、`(反引号) 除外。
3. 这就意味着单词分割、路径名扩展、波浪线扩展和花括号扩展都将失效，但是参数扩展、算术扩展、命令替换仍然生效。
4. 使用双引号能够处理文件名中包含空白的情况。
5. 假设不幸地有一个名为 two words.txt 的文件，如果在命令行中使用该文件名，那么单词分割功能将把它当成两个独立的参数，而不是当成我们希望的单个参数，具体运行结果如下所示。
[me@linuxbox ~]$ ls -l two words.txt
ls: cannot access two: No such file or directory
ls: cannot access words.txt: No such file or directory
6. 使用双引号可以阻止单词分割，得到预期的效果。另外，使用双引号甚至可以修复破损的文件名，参考下面的例子。
[me@linuxbox ~]$ ls -l "two words.txt"
-rw-rw-r-- 1 me me 18 2012-02-20 13:03 two words.txt
[me@linuxbox ~]$ mv "two words.txt" "two_words.txt"
7. 参数扩展、算术扩展和命令替换在双引号中依然生效：
[me@linuxbox ~]$ echo "$USER $((2+2)) $(cal)"
me 4 February 2012
Su  Mo Tu We Th Fr Sa
           1  2  3  4
 5   6  7  8  9 10 11
 12 13 14 15 16 17 18
 19 20 21 22 23 24 25
 26 27 28 29
8. 默认情况下，单词分割会先查找是否存在空格、制表符以及换行 (换行字符) ，然后把它们当作单词见的界定符 (delimiter) 。这就意味着没有用引号包含起来的空格、制表符和换行字符都不会被当成文本的一部分，而只是被当成分割符。
9. 单词分割机制会把换行字符当成界定符，这一点在命令替换将会产生微妙有趣的效果。参考下面例子：
[me@linuxbox ~]$ echo $(cal)
February 2012 Su Mo Tu We Th Fr Sa 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26 27 28 29
[me@linuxbox ~]$ echo "$(cal)"
    February 2012
Su  Mo Tu We Th Fr Sa
           1  2  3  4
 5   6  7  8  9 10 11
 12 13 14 15 16 17 18
 19 20 21 22 23 24 25
 26 27 28 29
10. 在第一个例子中，没有加上引号的命令替换将导致命令行被识别为命令后面跟 38 个参数；而在第二个例子中加了双引号，使得命令行被识别为命令后面只跟一个参数，这个参数包含着嵌入空格和换行字符。
```

### 单引号
```
1. 如果我们希望抑制所有扩展，那么应使用单引号。下面是不使用引号、使用双引号和使用单引号的情况对比。
[me@linuxbox ~]$ echo text ~/*.txt {a,b} $(echo foo) $((2+2)) $USER
text /home/me/ls-output.txt a b foo 4 me
[me@linuxbox ~]$ echo "text ~/*.txt {a,b} $(echo foo) $((2+2)) $USER"
text ~/*.txt {a,b} foo 4 me
[me@linuxbox ~]$ echo 'text ~/*.txt {a,b} $(echo foo) $((2+2)) $USER'
text ~/*.txt {a,b} $(echo foo) $((2+2)) $USER
2. 可以看到，随着引用级别的加强，越来越多扩展将被抑制。
```

### 转义字符
- 基本介绍
```
1. 有时候我们只是想要引用单个字符，这种情况可以通过在该字符前加上反斜杠来实现。这里的反斜杠称为转义字符。
2. 转义字符经常在双引号中用来有选择性地组织扩展。如下所示：
[me@linuxbox ~]$ echo "The balance for user $USER is: \$5.00"
The balance for user $USER is: $5.00
3. 转义字符也常用来消除文件名中某个字符的特殊含义。
4. 比如，文件名中可以使用在 shell 中通常具有特殊含义的字符。这些字符包括 $、!、&、空格等。想要在文件名中包含特殊字符，可执行如下操作。
[me@linuxbox ~]$ mv bad\&filename good_filename
5. 如果想要显示反斜杠字符，可以通过使用两个反斜杠 \\ 来实现。
```
- 反斜杠转义字符序列

|转义字符|含义|
|:--:|:--:|
|`\a`|响铃 (警告声-计算机发出哔哔声音) |
|`\b`|退格|
|`\n`|新的一行 (在类`UNIX`系统中，产生的是换行效果) |
|`\r`|回车|
|`\t`|制表|
