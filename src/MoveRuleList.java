import java.io.*;
import java.util.ArrayList;

/**
 * Created by Blake on 2014/7/8.
 */
public class MoveRuleList
{
    private static MoveRuleList instance = null;
    public static MoveRuleList getInstance()
    {
        if (instance == null){
            synchronized(MoveRuleList.class)
            {
                if(instance == null)
                {
                    instance = new MoveRuleList();
                }
            }
        }
        return instance;
    }
    public static ArrayList<MoveRule> downList=new ArrayList<MoveRule>();
    public static void readList() throws IOException
    {
        BufferedReader br=new BufferedReader(new InputStreamReader(new FileInputStream(Config.MoveRulePath),"UTF-8"));
        try
        {
            downList.clear();
            while (br.ready())
            {
                String line = br.readLine();
                String[] str=line.split("\\|");
                if(str.length==2)
                    downList.add(new MoveRule(str[0],str[1]));
            }
//            for(int i=0;i<downList.size();i++)
//            {
//                System.out.println(downList.get(i).getGroupName() + " " + downList.get(i).getDirName());
//            }
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
    public static void addList(String filename,String dirPath) throws IOException
    {
        FileWriter fw = new FileWriter(Config.MoveRulePath,true);
        fw.write(filename+"|"+dirPath+"\r\n");
        fw.flush();
        fw.close();
    }
    public static void executeRuleMove() throws IOException
    {
        Config.readConfig();
        MoveRuleList.readList();
        for(int j=0;j<MoveRuleList.downList.size();j++)
        {
            ArrayList<String> list= VideosList.findVideosList(MoveRuleList.downList.get(j).getGroupName());
            for(int i=0;i<list.size();i++)
            {
                executeMove(Config.videosPath,list.get(i),Config.videosMoveToPath +MoveRuleList.downList.get(j).getDirName());
            }
        }
    }

    public static void executeMove(String srcFilePath, String filename, String foldername)
    {
        if(new File(srcFilePath+filename).exists())
        {
            if(!new File(foldername).isDirectory())
            {
                new File(foldername).mkdir();
            }
            new File(srcFilePath+filename).renameTo(new File(foldername+"/"+filename));
            System.out.println("Move " + srcFilePath + filename + " to " + foldername + "/" + filename);
        }
    }
}
class MoveRule
{
    private String groupname;
    private String dirName;
    public MoveRule(String name,String dirname)
    {
        this.groupname=name;
        this.dirName=dirname;
    }
    public void setGroupName(String input)
    {
        groupname=input;
    }
    public void setDirName(String input)
    {
        dirName=input;
    }
    public String getGroupName()
    {
        return groupname;
    }
    public String getDirName()
    {
        return dirName;
    }
}