import java.io.*;
import java.util.ArrayList;

/**
 * Created by Blake on 2014/7/7.
 */
public class TorrentLog
{
    private static TorrentLog instance = null;
    public static TorrentLog getInstance()
    {
        if (instance == null){
            synchronized(TorrentLog.class)
            {
                if(instance == null)
                {
                    instance = new TorrentLog();
                }
            }
        }
        return instance;
    }

    public static final ArrayList<String> TorrentlogList=new ArrayList<String>();
    public static void readList() throws IOException
    {
        TorrentlogList.clear();
        FileReader fr = new FileReader(Config.TorrentlogPath);
        BufferedReader br = new BufferedReader(fr);
        while (br.ready())
        {
            TorrentlogList.add(br.readLine());
        }
        fr.close();
    }
    public static void addList(String input) throws IOException
    {
        FileWriter fw = new FileWriter(Config.TorrentlogPath,true);
        fw.write(input+"\r\n");
        fw.flush();
        fw.close();
    }
//    public static void main(String argc[]) throws IOException
//    {
//        TorrentLog.readList();
//        TorrentLog.addList("http://bt.ktxp.com/down/1396594126/505ca144faf55e7fb3f5c9d81056791a970279a7.torrent");
//        TorrentLog.readList();
//        for(int i=0;i<TorrentlogList.size();i++)
//        {
//            System.out.println(i+" "+TorrentlogList.get(i));
//        }
//    }
}
