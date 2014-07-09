# [動畫 BT 種子下載器](https://github.com/blake31113/AnimeBTDownloader)

動畫 BT 種子下載器: 

1. 按照您所設定的方式,下載動畫的種子
2. 自動搭配 BT 下載工具下載檔案（必須勾選使用 BT 下載工具的選項）
3. 移動下載後的動畫到你指定的資料夾裡

從[動漫花園](http://share.dmhy.org/cms/page/name/programme.html)載入與分析動畫列表，從[極影](http://bt.ktxp.com/)下載種子

## 目錄
 - [開始](#%E9%96%8B%E5%A7%8B)
 - [資料夾中有些什麼？](#%E8%B3%87%E6%96%99%E5%A4%BE%E4%B8%AD%E6%9C%89%E4%BA%9B%E4%BB%80%E9%BA%BC)
 - [如何使用](#%E5%A6%82%E4%BD%95%E4%BD%BF%E7%94%A8)
 - [問題回報與其它功能要求](#%E5%95%8F%E9%A1%8C%E5%9B%9E%E5%A0%B1%E8%88%87%E5%85%B6%E5%AE%83%E5%8A%9F%E8%83%BD%E8%A6%81%E6%B1%82)
 - [許可證](#%E8%A8%B1%E5%8F%AF%E8%AD%89)
 

## 開始

####1.[安裝 JAVA Runtime Environment](http://java.com/zh_TW/download/)

####2.檢查Java是否可以執行
在 Command Line 下執行

    $ java -version
    
如果跑出下面以下的內容，代表成功了

    
```
java version "1.7.0_45"
Java(TM) SE Runtime Environment (build 1.7.0_45-b18)
Java HotSpot(TM) 64-Bit Server VM (build 24.45-b08, mixed mode)
```

如果 Java 確實安裝了，卻無法執行 `$java ` ，繼續以下步驟 

####3.[下載最新版本的動畫 BT 種子下載器](https://github.com/blake31113/AnimeBTDownloader/raw/master/AnimeBTDownloader_ver1.0.1.zip)

####4.使用 Command line 來執行：

    $ java -Dfile.encoding=UTF-8 -jar AnimeBTDownloader.jar

如果不使用 Command line 來執行，會有許多亂碼跑出，如圖所示↓

![selectbt-garbled] [selectbt-garbled-img]

#####如果 Java 安裝了，卻無法執行 `$java ` 嘗試找到 Java 安裝在哪裡然後使用 Command line 來執行:

    $ "C:\Program Files\Java\jre7\bin\java" -Dfile.encoding=UTF-8 -jar AnimeBTDownloader.jar

p.s. 我的 Java 安裝在 `"C:\Program Files\Java\jre7\bin\"`

### 資料夾中有些什麼？
在下載的檔案中，你會找到一些資料夾與檔案，如下

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
## 如何使用
在成功執行動畫 BT 種子下載器，且不會顯示亂碼後後

### 設置（ Setting ）
![setting] [setting-img]

在其它動作之前，設定設置

#####- 是否使用 BT 下載工具（ Use torrent client ）：
是否在下載完種子後，使用 BT 下載工具來下載檔案
#####- BT 下載工具的位置（ Torrent Client path ）：
BT 下載工具的執行檔案位置
#####- 種子存放位置（ Torrent path ）：
種子存放位置
#####- 其它設定檔存放位置（ Configs path ）：
其它設定檔存放位置（建議不要更改）
#####- 影片存放位置（ Videos path ）：
影片存放位置
#####- 移動影片存放的目的地（ Videos Move to path ）：
移動影片存放的目的地
##### 在修改完設定後，按下存取設定（ Save Config ），之後在按應用（ Apply ）
---
### 選擇下載的動畫種子（ Select BT ）

![selectbt] [selectbt-img]

##### 如果左方烈表為空白的話，按下重新載入表單按鍵（ ReloadList Button ）

選擇你想下載的動畫以及字幕組，或是將關鍵字打入右上方的空格內
按下加入下載任務按鈕（ add to mission list ），會將右上方的關鍵字放入任務表單中


##### 從網路重新載入表單按鍵（ ReloadList_Web Button ）：會重新從動漫花園擷取分析動畫表單（ 可能虛要等待一些時間 ），通常從網路重新載入表單用於換季時才需使用
---
### 下載（ Download ）

![download] [download-img]

表列任務表單

##### 按下下載按鈕（ Download Button ）會開始下載在極影關鍵字搜尋到的種子

---
### 移動到目錄（ Move To Directory ）

![movetodirectory] [movetodirectory-img]

左表單是你設定動畫存放的位置裡的動畫

右表單是你的移動規則，你可以自己使用下方兩個輸入增加規則

移動按鍵（ Move Button ）按下後會開始移動動畫依照上方的規則

##### 只會將動畫檔名中前方字串符合規則的移動
---
## 問題回報與其它功能要求

如果有任何問題或想法，[請增加一個新的問題](https://github.com/blake31113/AnimeBTDownloader/issues/new)，或[使用電子郵件聯絡我](mailto:blake31113@gmail.com).

## 許可證
####Copyright 2014 Blake K.C LIN. [Licensed under CC BY-SA 3.0](http://creativecommons.org/licenses/by-sa/3.0/)

[selectbt-img]:https://raw.githubusercontent.com/blake31113/AnimeBTDownloader/master/snapshot/selectbt.PNG
[selectbt-garbled-img]:https://raw.githubusercontent.com/blake31113/AnimeBTDownloader/master/snapshot/selectbt_garbled.PNG
[download-img]:https://raw.githubusercontent.com/blake31113/AnimeBTDownloader/master/snapshot/download.PNG
[movetodirectory-img]:https://raw.githubusercontent.com/blake31113/AnimeBTDownloader/master/snapshot/movetodirectory.PNG
[setting-img]:https://raw.githubusercontent.com/blake31113/AnimeBTDownloader/master/snapshot/setting.PNG
