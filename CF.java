package com.islam;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.*;

public class CF extends JFrame implements ActionListener, ChangeListener {
    JTabbedPane tab;
    JPanel simple,develop;
    JTextField text,text1;
    keyAct keyAct = new keyAct();
    String str = "0.=+123-456*789/";
    public CF(){
        start();
        makeJMenu();
        makePanels();
        tabData();
        addKeyListener(keyAct);
        text.addKeyListener(new keyAct());
        text1.addKeyListener(new keyAct());
        add(tab);
        tab.setSelectedIndex(1);
    }

    private void makeJMenu() {
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Options");

        JMenuItem exit = new JMenuItem("Exit");
        exit.setToolTipText("exit the app");
        exit.setMnemonic(KeyEvent.VK_E);
        exit.setActionCommand("exit");
        exit.addActionListener(this);

        JMenuItem copy = new JMenuItem("Copy");
        copy.setToolTipText("copy data to clipboard");
        copy.setMnemonic(KeyEvent.VK_C);
        KeyStroke key = KeyStroke.getKeyStroke("control C");
        copy.setAccelerator(key);
        copy.setActionCommand("copy");
        copy.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                copy();
            }
        });


        menu.add(copy);
        menu.add(exit);

        menuBar.add(menu);

        setJMenuBar(menuBar);
    }

    private void tabData() {
        tab = new JTabbedPane();
        tab.add("simple",simple);
        tab.add("develop",develop);
        tab.addChangeListener(this);
//        tab.addKeyListener(new keyAct());
    }

    private void makePanelDevelop() {
        JPanel input = new JPanel();
        text = new JTextField(15);
        text.setEditable(false);
        JButton ac = new JButton("Ac");
        ac.setActionCommand("Ac");
        ac.addActionListener(this);
        input.add(text);
        input.add(ac);

        JPanel body = new JPanel(new GridLayout(
                4,4,12,12));
        makeButtons(body);
        develop = new JPanel();
        develop.setLayout(new BoxLayout(develop,BoxLayout.Y_AXIS));

        JPanel end = new JPanel(new GridLayout(1,4));
        makeThisButton(end);

        develop.add(input);
        develop.add(body);
        develop.add(end);
    }

    private void makeThisButton(JPanel end) {
        JButton btn = new JButton();
        btn.setText("sin");
        btn.setActionCommand(btn.getText());
        btn.addActionListener(this);
        end.add(btn);

        btn = new JButton();
        btn.setText("tan");
        btn.setActionCommand(btn.getText());
        btn.addActionListener(this);
        end.add(btn);

        btn = new JButton();
        btn.setText("log");
        btn.setActionCommand(btn.getText());
        btn.addActionListener(this);
        end.add(btn);

        btn = new JButton();
        btn.setText("shift");
        btn.setActionCommand(btn.getText());
        btn.addActionListener(this);
        end.add(btn);

    }

    private void makePanels() {
        simple = new JPanel();
        simple.setLayout(new BoxLayout(simple,BoxLayout.Y_AXIS));

        JPanel input = new JPanel();
        text1 = new JTextField(15);
        text1.setEditable(false);
//        text1.addKeyListener(new keyAct());
        JButton ac = new JButton("Ac");
        ac.setActionCommand("Ac");
        ac.addActionListener(this);
        input.add(text1,BorderLayout.WEST);
        input.add(ac);

        JPanel body = new JPanel(new GridLayout(
                4,4,12,12));
        makeButtons(body);

        simple.add(input);
        simple.add(body);

        makePanelDevelop();
    }

    private void makeButtons(JPanel body) {
        JButton btn;
        for (int i=1;i<17;i++){
            btn = new JButton();
            if (Math.floorMod(i,4) != 0){
                switch (i/4){
                    case 0:
                        btn.setText(String.valueOf(i+6));
                        break;
                    case 1:
                        btn.setText(String.valueOf(i-1));
                        break;
                    case 2:
                        btn.setText(String.valueOf(i-8));
                        break;
                }
            }
            switch (i){
                case 4:
                    btn.setText("/");
                    break;
                case 8:
                    btn.setText("*");
                    break;
                case 12:
                    btn.setText("-");
                    break;
                case 16:
                    btn.setText("+");
                    break;
                case 15:
                    btn.setText("=");
                    break;
                case 14:
                    btn.setText(".");
                    break;
                case 13:
                    btn.setText("0");
                    break;
            }
            btn.setActionCommand(btn.getText());
            btn.addActionListener(this);
            body.add(btn);
        }
    }

    private void start(){
        setSize(250,250);
        setLocation(300,100);
        setTitle("Calculate");
        setVisible(true);
        setResizable(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JTextField text = this.text;
        if (tab.getSelectedIndex() == 0)
            text = this.text1;
        if (e.getActionCommand().equals("exit"))
            System.exit(0);
        if (e.getActionCommand().equals("copy"))
            copy();
        else {
            if (e.getActionCommand().equals("=")){
                text.setText(text.getText() + e.getActionCommand());
                calculate();
            }else if (!e.getActionCommand().equals("Ac")){
                text.setText(text.getText() + e.getActionCommand());
            }else{
                text.setText("");
            }

        }
//        switch (tab.getSelectedIndex()){
//            case 1:
//                tab.setSelectedIndex(0);
//                tab.setSelectedIndex(1);
//                break;
//            case 2:
//                tab.setSelectedIndex(1);
//                tab.setSelectedIndex(0);
//                break;
//        }
        tab.updateUI();
    }

    private void copy() {
//        String myString = "This text will be copied into clipboard";
//        StringSelection stringSelection = new StringSelection(myString);
//        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
//        clipboard.setContents(stringSelection, null);
//        System.out.println("h");
        Clipboard cp = Toolkit.getDefaultToolkit().getSystemClipboard();
        JTextField t  = text;
        if (tab.getSelectedIndex() == 0)
            t = text1;
            System.out.println(t.getText());
        StringSelection str = new StringSelection(t.getText());
        cp.setContents(str,null);

    }

    private void calculate() {
        String str;
        JTextField t;
        str = text1.getText().trim();
        t = text1;
        if (tab.getSelectedIndex() == 1){
            str = text.getText().trim();
            t = text;
        }
        float x,y;
        float r=0;
        int a = str.indexOf('*');
        if (a >= 1){
            x = Float.parseFloat((str.substring(0,a)));
            y = Float.parseFloat(str.substring(a+1,str.length()-1));
            r += x*y;
            System.out.println(x + " " + y + " " + r);
        }
        int d = str.indexOf('/');

        if (d >= 1){
            x = Float.parseFloat((str.substring(0,d)));
            y = Float.parseFloat(str.substring(d+1,str.length()-1));
            r += x/y;
        }

        int s = str.indexOf('+');

        if (s >= 1){
            x = Float.parseFloat((str.substring(0,s)));
            y = Float.parseFloat(str.substring(s+1,str.length()-1));
            r += x+y;
        }
        int m = str.indexOf('-');

        if (m >= 1){
            x = Float.parseFloat((str.substring(0,m)));;
            y = Float.parseFloat(str.substring(m+1,str.length()-1));
            r += x-y;
        }

        int tan = str.indexOf("tan");
        if (tan >= 0){
            System.out.println("t");
            r += Math.tan(Float.parseFloat((str.substring(tan+3))));
        }
        int sin = str.indexOf("sin");
        if (sin >= 0){
            System.out.println("s");
            r += Math.sin(Float.parseFloat((str.substring(sin+3))));
        }
        int log = str.indexOf("log");
        if (log >= 0){
            System.out.println("l"
            );
            r += Math.log(Float.parseFloat((str.substring(log+3))));
        }

        t.setText(String.valueOf(r));
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        if (tab.getSelectedIndex() == 1){
            this.setSize(270,300);
        }else{
            this.setSize(250,250);
        }
    }

    class keyAct extends KeyAdapter{
        @Override
        public void keyTyped(KeyEvent e) {
            System.out.println(e.paramString());
            JTextField text = CF.this.text;
            if (tab.getSelectedIndex() == 0)
                text = CF.this.text1;
            if (e.getKeyChar() == '\b')
                text.setText("");
            if (str.indexOf(e.getKeyChar()) >= 0){
                text.setText(text.getText() + e.getKeyChar());
                if (e.getKeyChar() == '=') {
                    calculate();
                }
            }}
    }
}
