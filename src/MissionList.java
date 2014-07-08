import java.io.*;
import java.util.ArrayList;

/**
 * Created by Blake on 2014/7/7.
 */
public class MissionList
{
    private static MissionList instance = null;

    public static ArrayList<DownloadList> downList=new ArrayList<DownloadList>();
    public static MissionList getInstance()
    {
        if (instance == null){
            synchronized(MissionList.class)
            {
                if(instance == null)
                {
                    instance = new MissionList();
                }
            }
        }
        return instance;
    }
    public static void addList(String input) throws IOException
    {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(Config.MissionList,true), "UTF-8"));
        bw.append(input + "\r\n");
        bw.close();
    }
    public static void readList() throws IOException
    {
        BufferedReader br=new BufferedReader(new InputStreamReader(new FileInputStream(Config.MissionList),"UTF-8"));
        try
        {
            StringBuilder sb = new StringBuilder();
            while (br.ready())
            {
                String line = br.readLine();
                MissionList.downList.add(new DownloadList(line));
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            br.close();
        }
    }
    public static void rereadList() throws IOException
    {
        BufferedReader br=new BufferedReader(new InputStreamReader(new FileInputStream(Config.MissionList),"UTF-8"));
        try
        {
            StringBuilder sb = new StringBuilder();
            MissionList.downList.clear();
            while (br.ready())
            {
                String line = br.readLine();
                MissionList.downList.add(new DownloadList(line));
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            br.close();
        }
    }
}
