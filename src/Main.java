import java.awt.*;
import java.io.IOException;

/**
 * Created by Blake on 2014/7/6.
 */
public class Main
{
    public static void main(String[] args) throws IOException
    {
        Config.readConfig();
        AnimeList.loadAnimeListFromFile();
        MissionList.readList();
        VideosList.loadVideosList();
        UIform GUI =new UIform();
        Dimension screensizeDimension = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension size = GUI.getSize();
        int w = (int) (screensizeDimension.getWidth() / 2 - size.getWidth() / 2);
        int h = (int) (screensizeDimension.getHeight() / 2 - size.getHeight() / 2);
        GUI.setLocation(w,h);
    }
}
