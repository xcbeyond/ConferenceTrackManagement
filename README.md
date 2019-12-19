# Conference Track Management

## 功能描述
```
You are planning a big programming conference and have received many proposals which have passed the initial screen process but you're having trouble fitting them into the time constraints of the day -- there are so many possibilities! So you write a program to do it for you.
The conference has multiple tracks each of which has a morning and afternoon session.
Each session contains multiple talks.
Morning sessions begin at 9am and must finish before 12 noon, for lunch.
Afternoon sessions begin at 1pm and must finish in time for the networking event.
The networking event can start no earlier than 4:00 and no later than 5:00.
No talk title has numbers in it.
All talk lengths are either in minutes (not hours) or lightning (5 minutes).
Presenters will be very punctual; there needs to be no gap between sessions.

Note that depending on how you choose to complete this problem, your solution may give a different ordering or combination of talks into tracks. This is acceptable; you don’t need to exactly duplicate the sample output given here.

Test input:
Writing Fast Tests Against Enterprise Rails 60min
Overdoing it in Python 45min
Lua for the Masses 30min
Ruby Errors from Mismatched Gem Versions 45min
Common Ruby Errors 45min
Rails for Python Developers lightning
Communicating Over Distance 60min
Accounting-Driven Development 45min
Woah 30min
Sit Down and Write 30min
Pair Programming vs Noise 45min
Rails Magic 60min
Ruby on Rails: Why We Should Move On 60min
Clojure Ate Scala (on my project) 45min
Programming in the Boondocks of Seattle 30min
Ruby vs. Clojure for Back-End Development 30min
Ruby on Rails Legacy App Maintenance 60min
A World Without HackerNews 30min
User Interface CSS in Rails Apps 30min

Test output:
Track 1:
09:00AM Writing Fast Tests Against Enterprise Rails 60min
10:00AM Overdoing it in Python 45min
10:45AM Lua for the Masses 30min
11:15AM Ruby Errors from Mismatched Gem Versions 45min
12:00PM Lunch
01:00PM Ruby on Rails: Why We Should Move On 60min
02:00PM Common Ruby Errors 45min
02:45PM Pair Programming vs Noise 45min
03:30PM Programming in the Boondocks of Seattle 30min
04:00PM Ruby vs. Clojure for Back-End Development 30min
04:30PM User Interface CSS in Rails Apps 30min
05:00PM Networking Event

Track 2:
09:00AM Communicating Over Distance 60min
10:00AM Rails Magic 60min
11:00AM Woah 30min
11:30AM Sit Down and Write 30min
12:00PM Lunch
01:00PM Accounting-Driven Development 45min
01:45PM Clojure Ate Scala (on my project) 45min
02:30PM A World Without HackerNews 30min
03:00PM Ruby on Rails Legacy App Maintenance 60min
04:00PM Rails for Python Developers lightning
05:00PM Networking Event
```
### 需求解读：
* 上午(09:00-12:00)，共180分钟可安排。
* 午饭(12:00/12:30-13:00/13:30)，共60分钟间隔。
* 下午(13:00-16:00/17:00)，最多240分钟可安排。
* 网络会议，为灵活时段，确保最晚17:00开始即可。

### 实现方案
1. 读取解析输入文件的数据，将其存放在Talk实体类中，包括title、duration等属性；
2. 根据持续时长降序排序，放入List集合；
3. 整个日程按照上午、午饭、下午、网络会议四个时段处理，优先将时长大的先安排，为后续留余更多的时间。

## 项目说明
本项目基于Maven搭建，使用JDK1.8以上版本，推荐使用IDEA开发。
### 项目结构
项目名称暂定为ConferenceTrackManagement，项目结构如下：
```
ConferenceTrackManagement
    |- src
        |-- main
            |- java       # Java源码文件存储目录
                |- com
                    |- xcbeyond
                        |- ctm      # 包根路径
                          |- comparator       # 比较器，用于存放对象、集合等的排序
                          |- constant         # 常量包
                          |- entity           # 实体包，存放实体类
                          |- enums            # 枚举包
                          |- exception        # 异常包
                          |- manager          # 核心业务管理层
                          |- parser           # 解析器
                          |- util             # 工具包
                          |- CTMStart.java    # 项目启动类(入口)
            |- resources  # 资源配置目录，用于存放配置文件等
        |- test           # 单元测试目录
        |- pom.xml        # 项目中maven依赖的核心配置文件
        |- README.md      # 简要项目说明文件
```
### 使用说明
1. src/main/resources/input.txt，为输入数据文件。
2. com.xcbeyond.ctm.CTMStart为项目入口，用于启动运行项目。
