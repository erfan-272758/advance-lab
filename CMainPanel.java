package com.islam;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;


public class CMainPanel extends JPanel {

    private JTabbedPane tabbedPane;
    //private JList<Object> directoryList;
    private Note[] notes;
    private JList<File> directoryListNote;

    public CMainPanel() {

        setLayout(new BorderLayout());

        initDirectoryList(); // add JList to main Panel

        initTabbedPane(); // add TabbedPane to main panel

        addNewTab(); // open new empty tab when user open the application
    }

    private void initTabbedPane() {
        tabbedPane = new JTabbedPane();
        add(tabbedPane, BorderLayout.CENTER);
    }

    private void initDirectoryList() {
        File[] files = FileUtils.getBinsInDirectory();
//        directoryList = new JList<>(files);
        directoryListNote = new JList<>(files);

        directoryListNote.setBackground(new Color(211, 211, 211));
        directoryListNote.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        directoryListNote.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        directoryListNote.setVisibleRowCount(-1);
        directoryListNote.setMinimumSize(new Dimension(130, 100));
        directoryListNote.setMaximumSize(new Dimension(130, 100));
        //directoryListNote.setFixedCellWidth(130);
        directoryListNote.setCellRenderer(new MyCellRenderer());
        directoryListNote.addMouseListener(new MyMouseAdapter());
        add(new JScrollPane(directoryListNote,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED
                ,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS), BorderLayout.WEST);
    }


    public void addNewTab() {
        JTextArea textPanel = createTextPanel();
        textPanel.setText("Write Something here...");
        tabbedPane.addTab("Tab " + (tabbedPane.getTabCount() + 1), textPanel);
    }

    public void openExistingNote(String content) {
        JTextArea existPanel = createTextPanel();
        existPanel.setText(content);

        int tabIndex = tabbedPane.getTabCount() + 1;
        tabbedPane.addTab("Tab " + tabIndex, existPanel);
        tabbedPane.setSelectedIndex(tabIndex - 1);
    }

    //    public void saveNote() {
//        JTextArea textPanel = (JTextArea) tabbedPane.getSelectedComponent();
//        String note = textPanel.getText();
//        if (!note.isEmpty()) {
//            FileUtils.fileWriter(note);
//        }
//        updateListGUI();
//    }
//    public void saveNote(int index) {
//        JTextArea textPanel = (JTextArea) tabbedPane.getComponentAt(index);
//        String note = textPanel.getText();
//        if (!note.isEmpty()) {
//            FileUtils.fileWriter(note);
//        }
//        updateListGUI();
//    }
    public void saveNoteNew() {
        JTextArea textPanel = (JTextArea) tabbedPane.getSelectedComponent();
        String note = textPanel.getText();
        if (!note.isEmpty()) {
            String title = FileUtils.getProperFileName(note);
            Date d = Calendar.getInstance().getTime();
            FileUtils.binWriter(new Note(title,note,d.toString()));
        }
        updateListGUINote();
    }

    public void saveNoteNew(int index) {
        JTextArea textPanel = (JTextArea) tabbedPane.getComponentAt(index);
        String note = textPanel.getText();
        if (!note.isEmpty()) {
            String title = FileUtils.getProperFileName(note);
            Date d = Calendar.getInstance().getTime();
            FileUtils.binWriter(new Note(title,note,d.toString()));
        }
        updateListGUINote();
    }

    private JTextArea createTextPanel() {
        JTextArea textPanel = new JTextArea();
        textPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        return textPanel;
    }
    //
    private void updateListGUI() {
        File[] newFiles = FileUtils.getFilesInDirectory();
//        directoryList.setListData(newFiles);
    }

    private void updateListGUINote() {
        File[] notes = FileUtils.getBinsInDirectory();
        directoryListNote.setListData(notes);
    }


    private class MyMouseAdapter extends MouseAdapter {
        @Override
        public void mouseClicked(MouseEvent eve) {
            // Double-click detected

            if (eve.getClickCount() == 2) {
                int index = directoryListNote.locationToIndex(eve.getPoint());
                System.out.println("Item " + index + " is clicked...");
                //TODO: Phase1: Click on file is handled... Just load content into JTextArea
                File[] curr = FileUtils.getBinsInDirectory();
                String content = FileUtils.binReader(curr[index]).getContent();
                openExistingNote(content);
            }
//            if (eve.getClickCount() == 2) {
//                int index = directoryList.locationToIndex(eve.getPoint());
//                System.out.println("Item " + index + " is clicked...");
//                //TODO: Phase1: Click on file is handled... Just load content into JTextArea
//                File curr[] = FileUtils.getFilesInDirectory();
//                String content = FileUtils.fileReader(curr[index]);
//                openExistingNote(content);
//            }
        }
    }


    private static class MyCellRenderer extends DefaultListCellRenderer {

        @Override
        public Component getListCellRendererComponent(JList list, Object object, int index, boolean isSelected, boolean cellHasFocus) {

            if (object instanceof File) {
                File file = (File) object;
                Note note = FileUtils.changeFileToNote(file);
                if (note!=null)
                    setText(note.toString());
                else
                    setText("Not Fount");
                setIcon(FileSystemView.getFileSystemView().getSystemIcon(file));
                if (isSelected) {
                    setBackground(list.getSelectionBackground());
                    setForeground(list.getSelectionForeground());
                } else {
                    setBackground(list.getBackground());
                    setForeground(list.getForeground());
                }
                setEnabled(list.isEnabled());
            }
            return this;
        }
    }

    public int getTabbedPaneSize() {
        return tabbedPane.getTabCount();
    }
}
