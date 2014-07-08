import java.io.File;
import java.util.ArrayList;

/**
 * Created by Blake on 2014/7/8.
 */
public class VideosList
{
    private static VideosList instance = null;
    private static ArrayList<String> videosList=new ArrayList<String>();
    public static VideosList getInstance()
    {
        if (instance == null){
            synchronized(VideosList.class)
            {
                if(instance == null)
                {
                    instance = new VideosList();
                }
            }
        }
        return instance;
    }
    public static void loadVideosList()
    {
        videosList.clear();
        File name = new File(Config.videosPath);
        String[] directory = name.list();
        for (String f : directory)
        {
            File check = new File(name.getPath() + "\\" + f);
            if (check.isFile()&&isVideo(f.substring(f.lastIndexOf(".")+1)))
            {
//                System.out.println(f.toString());
                addVideosList(f.toString());
            }
        }
    }
    public static ArrayList<String> findVideosList(String input)
    {
        ArrayList<String> list=new ArrayList<String>();
        File name = new File(Config.videosPath);
        String[] directory = name.list();
        for (String f : directory)
        {
            File check = new File(name.getPath() + "\\" + f);
            if (check.isFile()&&isVideo(f.substring(f.lastIndexOf(".")+1)))
            {
                if(f.length()>=input.length()&&f.substring(0,input.length()).equals(input))
                    list.add(f.toString());
            }
        }
        return list;
    }
    public static boolean isVideo(String extention)
    {
        String ext=extention.toLowerCase();
        if(ext.equals("mp4")||ext.equals("rm")||ext.equals("rmvb")||ext.equals("mkv")||ext.equals("ts"))
            return true;
        else
            return false;
    }
    public static void addVideosList(String input)
    {
        videosList.add(input);
    }
    public static int getVideosListSize()
    {
        return videosList.size();
    }
    public static String getVideoList(int index)
    {
        return videosList.get(index);
    }
}
