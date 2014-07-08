import java.io.*;

/**
 * Created by Blake on 2014/7/7.
 */
public class Config
{
    private static Config instance = null;
    public static Config getInstance()
    {
        if (instance == null){
            synchronized(Config.class)
            {
                if(instance == null)
                {
                    instance = new Config();
                }
            }
        }
        return instance;
    }

    public static String DownloadTorrentPath ="./torrent/";
    public static String AnimeListFilePath;
    public static String MissionList;
    public static String TorrentlogPath;
    public static String MoveRulePath;
    public static String configPath ="Config.txt";
    public static String configsDirPath;
    public static String utorrentPath;
    public static boolean useutorrentFlag;
    public static String torrentPath;
    public static String videosPath;
    public static String videosMoveToPath;
    public static String settingPath;
    public static void parseConfig(String config, String setting)
    {
        if(config.equals("utorrentPath"))
        {
            utorrentPath=setting;
        }
        else if(config.equals("useutorrent"))
        {
            useutorrentFlag= Boolean.parseBoolean(setting);
        }
        else if(config.equals("torrentPath"))
        {
            torrentPath=setting;
        }
        else if(config.equals("ConfigPath"))
        {
            AnimeListFilePath   =setting+"AnimeList.txt";
            MissionList         =setting+"MissionList.txt";
            TorrentlogPath      =setting+"Torrentlog.txt";
            MoveRulePath        =setting+"MoveRule.txt";
            settingPath         =setting+"Settings.ini";
            configsDirPath      =setting;
//            System.out.println(MoveRulePath);
        }
        else if(config.equals("VideoPath"))
        {
            videosPath =setting;
//            System.out.println(videosPath);
        }
        else if(config.equals("VideoMoveToPath"))
        {
            videosMoveToPath =setting;
//            System.out.print(videosMoveToPath);
        }
    }
    public static void setConfig(String config, String setting) throws IOException
    {
        FileReader fr = new FileReader(Config.configPath);
        BufferedReader br = new BufferedReader(fr);
        String fileString="";
        while (br.ready())
        {
            String temp=br.readLine();
            String[] str=temp.split("\\=");
            if(str[0].equals(config))
            {
                fileString+=str[0]+"="+setting;
            }
            else
            {
                fileString+=temp;
            }
            fileString+="\r\n";
        }
        fr.close();
        FileWriter fw = new FileWriter(Config.configPath);
        fw.write(fileString);
        fw.flush();
        fw.close();
    }
    public static void readConfig() throws IOException
    {
        FileReader fr = new FileReader(Config.configPath);
        BufferedReader br = new BufferedReader(fr);
        while (br.ready())
        {
            String[] str=br.readLine().split("\\=");
            Config.parseConfig(str[0], str[1]);
        }
        fr.close();
    }
}
