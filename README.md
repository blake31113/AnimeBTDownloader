# [AnimeBTDownloader](https://github.com/blake31113/AnimeBTDownloader)

Animation BT Downloader: 

1. Download Animation torrent following the rule you defined.
2. Automatically use torrent client to download file (the boolean of use torrent client have to be true)
3. Move Downloaded Animations to Directory following the rule you defined.

Load and Parse Animation List from [Dmhy](http://share.dmhy.org/cms/page/name/programme.html), and Download Torrent from [KXTP](http://bt.ktxp.com/)

## Contents
 - [Get Start](#get-start)
 - [What's included](#whats-included)
 - [How to use](#how-to-use)
 - [Bugs and feature requests](#bugs-and-feature-requests)
 

## Get Start

####1.[install JRE](http://java.com/zh_TW/download/)

####2.Check if Java can run:

    $ java -version
    
If can run like as follows, it works.

    
```
java version "1.7.0_45"
Java(TM) SE Runtime Environment (build 1.7.0_45-b18)
Java HotSpot(TM) 64-Bit Server VM (build 24.45-b08, mixed mode)
```
If Java is acutually installed, but cannot use `$java ` to run, continue to follow the steps 


####3.[Download the latest release Animation BT Downloader](https://github.com/blake31113/AnimeBTDownloader/raw/master/AnimeBTDownloader_ver1.0.0.zip)

####4.Use command line to execute:

    $ java -Dfile.encoding=UTF-8 -jar AnimeBTDownloader.jar

If don't use command line to execute, lots of garbled text will come out!

#####If Java installed but cannot use `$java ` to run, try to find where Java installed and use command line to execute:

    $ "C:\Program Files\Java\jre7\bin\java" -Dfile.encoding=UTF-8 -jar AnimeBTDownloader.jar

p.s. my java is installed in `"C:\Program Files\Java\jre7\bin\"`

### What's included
Within the download you'll find the following directories and files like this:

```
/
├── config/
│   ├── AnimeList.txt       (for recording animation lists captured from dmhy)
│   ├── MissionList.txt     (for recording download missions)
│   ├── MoveRule.txt        (for recording moving videos rule)
│   └── Torrentlog.txt      (for recording downloaded torrents)
│
├── torrent/                (for saving the download torrent)
│   
├── AnimeBTDownloader.jar   (the executable file)
│
└── Config.txt              (for setting)
```
## How to use
After successfully execute AnimeBTDownloader.jar without garbled texts.

### Setting
Set setting first
#####- Use torrent client:
If use torrent client to download file.
#####- Torrent Client path:
If use torrent client is selected, after downloading the torrent will use torrent client to download file.
#####- Torrent path:
The place to store torrent.
#####- Configs path:
The place to store configs. (suggest not to change)
#####- Videos path:
The place to store videos.
#####- Videos Move to path:
The place to store path changed videos.

##### After modifying Settings, Push Save Config Button then Push Apply.
---
### Select BT
##### If left list is empty, push the ReloadList Button.

Select the Animation and Fansub you want to download or Type the keyword into the rightup side Textfield. 
Push add to mission list button will get the value of Keyword Textfield to Download Mission List


##### ReloadList_Web Button : reload the list from Dmhy (may be slower)
---
### Download
Will load Mission List

##### Push Download Button will begin to download Torrents with Keywords from KXTP 

---
### Move To Directory
Left list is your videos in Videos path.

Right list is your Move Rules. You can add yours rule with two Textfields down below and Save it.

Move Button is for moving videos following the Move Rules.

##### The subString of Moved Videos Name must match the Rule
---
## Bugs and feature requests

If any problems or ideas, [please add a new issue](https://github.com/blake31113/AnimeBTDownloader/issues/new).

