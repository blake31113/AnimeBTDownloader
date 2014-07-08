import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * Created by Blake on 2014/7/6.
 */
public class UIform extends JFrame
{
    private JTabbedPane         tabPane;
    private JPanel              rootPanel;
    private JList               animeList;
    private JList               fansubList;
    private JButton             addToListButton;
    public  DefaultListModel    animelistModel;
    public  DefaultListModel    fansublistModel;
    private JTextField          changedPathText;
    private JButton             moveVideosButton;
    private JButton             refreshDirectoryButton;
    private JTextField          utorrentPathText;
    private JRadioButton        useUtorrentToDownloadRadioButton;
    private JTextField          torrentPathText;
    private JButton             saveRuleButton;
    private JButton             saveConfigButton;
    private JButton             applyConfigButton;
    private JButton             reloadAnimeListFromWebButton;
    private JButton             reloadAnimeListButton;
    private JTable              progressListTable;
    private JTextField          selectFanSubTextField;
    private JButton             downloadTorrentsButton;
    private JButton             refreshMissionListButton;
    private JTextField          configsPathText;
    private JTextField          videosPathText;
    private JTable              videosTable;
    private JTable              selfDefineRuleTable;
    private JTextField          movedVideoTextField;
    private JButton             refreshRuleButton;
    private JTextField          videosMoveToPathText;
    private String []           downloadListField;
    private String [][]         downloadListData;
    private DefaultTableModel   downloadListModel;

    private String []           videosTableField;
    private String [][]         videosListData;
    private DefaultTableModel   videosTableModel;

    private String []           selfDefineRuleTableField;
    private String [][]         selfDefineRuleListData;
    private DefaultTableModel   selfDefineRuleTableModel;
    public UIform() throws IOException
    {
        super("Download Anime BT");
        setContentPane(rootPanel);
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1300, 600);
        setVisible(true);
        animelistModel =new DefaultListModel();
        fansublistModel=new DefaultListModel();
        downloadListField = new String[]{"名稱","下載種子數"};
        downloadListModel =new DefaultTableModel(downloadListData, downloadListField);
        progressListTable.setModel(downloadListModel);
        progressListTable.getColumnModel().getColumn(0).setPreferredWidth(600);

        videosTableField = new String[]{"名稱"};
        videosTableModel =new DefaultTableModel(videosListData, videosTableField);
        videosTable.setModel(videosTableModel);
        videosTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        ListSelectionModel videosTableSelectionModel = videosTable.getSelectionModel();

        selfDefineRuleTableField = new String[]{"名稱","資料夾名稱"};
        selfDefineRuleTableModel =new DefaultTableModel(selfDefineRuleListData, selfDefineRuleTableField);
        selfDefineRuleTable.setModel(selfDefineRuleTableModel);

        videosTableSelectionModel.addListSelectionListener(new ListSelectionListener()
        {
            public void valueChanged(ListSelectionEvent e)
            {
                if (e.getValueIsAdjusting())
                    return;
                if(videosTable.getSelectedRow()!=-1)
                    movedVideoTextField.setText(VideosList.getVideoList(videosTable.getSelectedRow()));
            }
        });
        animeList.addListSelectionListener(new ListSelectionListener()
        {
            public void valueChanged(ListSelectionEvent evt)
            {
                if (evt.getValueIsAdjusting())
                    return;
                int currentIndex=animeList.getSelectedIndex();
//                System.out.println(AnimeList.fansubList.getFansubData(currentIndex).getAnimeName());
                updateFansubList(currentIndex);
            }
        });
        fansubList.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent evt) {
                if (evt.getValueIsAdjusting())
                    return;
                int currentIndex = fansubList.getSelectedIndex();
                if (animeList.getSelectedIndex() >= 0 && fansubList.getSelectedIndex() >= 0)
                    selectFanSubTextField.setText(AnimeList.fansubList.getFansubData(animeList.getSelectedIndex()).getAnimeName() + " " + AnimeList.fansubList.getFansubData(animeList.getSelectedIndex()).getFansub(currentIndex));
            }
        });
        animeList.setModel(animelistModel);
        fansubList.setModel(fansublistModel);
        addToListButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    System.out.println("Add to Mission List");
                    MissionList.addList(selectFanSubTextField.getText());
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });
        reloadAnimeListFromWebButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Reloading");
                AnimeList.reloadAnimeListFromWeb();
                JOptionPane.showMessageDialog(rootPanel, "Successful Reload\nRe-open the program then Apply");
            }
        });
        reloadAnimeListButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    updateAnimeList();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });
        downloadTorrentsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i < MissionList.downList.size(); i++) {
                    Thread t = new LoadBTList_Thread(i);
                    t.run();
                    try {
                        refreshMissionList();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        });
        refreshMissionListButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    refreshMissionList();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });
        saveRuleButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                if(!movedVideoTextField.getText().equals("")&&!changedPathText.getText().equals(""))
                {
                    try
                    {
                        MoveRuleList.addList(movedVideoTextField.getText(),changedPathText.getText());
                        refreashMoveRuleList();
                    }
                    catch (IOException e1)
                    {
                        e1.printStackTrace();
                    }
                }
                else
                {
                    JOptionPane.showMessageDialog(rootPanel,"Cannot be Blank!!");
                }
            }
        });
        refreshRuleButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                try {
                    refreashMoveRuleList();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });
        refreshDirectoryButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                try
                {
                    refreashVideosList();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });
        moveVideosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    MoveRuleList.executeRuleMove();
                    refreashVideosList();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });
        saveConfigButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                try
                {
                    Config.setConfig("useutorrent", Boolean.toString(useUtorrentToDownloadRadioButton.isSelected()));
                    Config.setConfig("utorrentPath",utorrentPathText.getText());
                    Config.setConfig("torrentPath",torrentPathText.getText());
                    Config.setConfig("ConfigPath",configsPathText.getText());
                    Config.setConfig("VideoPath", videosPathText.getText());
                    JOptionPane.showMessageDialog(rootPanel, "Save the Config Setting!");
                }
                catch (IOException e1)
                {
                    e1.printStackTrace();
                }

            }
        });
        applyConfigButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                try {
                    Config.readConfig();
                    refreshConfigPanel();
                    JOptionPane.showMessageDialog(rootPanel,"Apply the Saved Config Setting!");
                }
                catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });
        initialAnimeList();
        initialMissionList();
        initialVideosList();
        initialMoveRuleList();
        refreshConfigPanel();
    }
    public void initialAnimeList() throws IOException
    {
        AnimeList.loadAnimeListFromFile();
        for(int i=0;i< AnimeList.fansubList.fansubDataSize();i++)
        {
            animelistModel.addElement(AnimeList.fansubList.getFansubData(i).getAnimeName());
        }
    }
    public void initialVideosList()
    {
        VideosList.loadVideosList();
        for(int i=0;i< VideosList.getVideosListSize();i++)
        {
            videosTableModel.addRow(new Object[]{VideosList.getVideoList(i)});
        }
    }
    public void refreashVideosList() throws IOException
    {
        VideosList.loadVideosList();
        int deleteCount=videosTableModel.getRowCount();
        for(int i=0;i<deleteCount;i++)
        {
            videosTableModel.removeRow(0);
        }
        for(int i=0;i< VideosList.getVideosListSize();i++)
        {
            videosTableModel.addRow(new Object[]{VideosList.getVideoList(i)});
        }
    }
    public void initialMoveRuleList() throws IOException
    {
        MoveRuleList.readList();
        for(int i=0;i<MoveRuleList.downList.size();i++)
        {
            selfDefineRuleTableModel.addRow(new Object[]{MoveRuleList.downList.get(i).getGroupName(),MoveRuleList.downList.get(i).getDirName()});
        }
    }
    public void refreashMoveRuleList() throws IOException
    {
        MoveRuleList.readList();
        int deleteCount=selfDefineRuleTableModel.getRowCount();
        for(int i=0;i<deleteCount;i++)
        {
            selfDefineRuleTableModel.removeRow(0);
        }
        for(int i=0;i<MoveRuleList.downList.size();i++)
        {
            selfDefineRuleTableModel.addRow(new Object[]{MoveRuleList.downList.get(i).getGroupName(),MoveRuleList.downList.get(i).getDirName()});
        }
    }
    public void refreshConfigPanel() throws IOException
    {
        useUtorrentToDownloadRadioButton.setSelected(Config.useutorrentFlag);
        utorrentPathText.setText(Config.utorrentPath);
        torrentPathText.setText(Config.torrentPath);
        configsPathText.setText(Config.configsDirPath);
        videosPathText.setText(Config.videosPath);
        videosMoveToPathText.setText(Config.videosMoveToPath);
    }
    public void initialMissionList() throws IOException
    {
        for(int i=0;i<MissionList.downList.size();i++)
        {
            addDownloadList(MissionList.downList.get(i).getURL(),String.valueOf(MissionList.downList.get(i).getNumberOfTorrent()));
//            System.out.println(MissionList.downList.get(i));
        }
    }
    public void refreshMissionList() throws IOException
    {
        int deleteCount=downloadListModel.getRowCount();
        for(int i=0;i<deleteCount;i++)
        {
            downloadListModel.removeRow(0);
        }
        for(int i=0;i<MissionList.downList.size();i++)
        {
            addDownloadList(MissionList.downList.get(i).getURL(),String.valueOf(MissionList.downList.get(i).getNumberOfTorrent()));
        }
    }
    public void updateAnimeList() throws IOException
    {
        AnimeList.loadAnimeListFromFile();
        for(int i=0;i< AnimeList.fansubList.fansubDataSize();i++)
        {
            animelistModel.addElement(AnimeList.fansubList.getFansubData(i).getAnimeName());
        }
    }
    public void updateFansubList(int index)
    {
        selectFanSubTextField.setText(AnimeList.fansubList.getFansubData(index).getAnimeName());
        animeList.setSelectedIndex(-1);
        fansubList.setSelectedIndex(-1);
        fansublistModel.removeAllElements();
        for(int j=0;j< AnimeList.fansubList.getFansubData(index).getFansubSize();j++)
        {
            fansublistModel.addElement(AnimeList.fansubList.getFansubData(index).getFansub(j));
        }
    }
    public void addDownloadList(String cl1,String cl2)
    {
        downloadListModel.addRow(new Object[]{cl1, cl2});
    }
}