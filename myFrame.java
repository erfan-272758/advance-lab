package com.islam;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

public class myFrame extends JFrame implements ChangeListener {
    JPanel whole;
    JPanel numbers;
    JPanel input;
    JPanel develop;
    JTextArea ta;
    JTabbedPane tp;
    public myFrame(){
        setSize(250,230);
        setLocation(500,250);
        develop = new JPanel(new BorderLayout());
        whole = new JPanel(new BorderLayout());
        tp=new JTabbedPane();
        tp.setBounds(50,50,200,200);
        tp.add("simple",whole);
        tp.add("develop",develop);
        tp.addChangeListener(this);
        add(tp);
        inputInit(whole);
        numberInit(whole);
        inputInit(develop);
        numberInit(develop);
        developDo();
        setResizable(false);
        setTitle("Calculate");

    }

    private void developDo() {
        JPanel node = new JPanel(new GridLayout(1,4,5,1));
        for (int i = 0; i < 4; i++) {
            JButton bt = new JButton();
            bt.setSize(20,20);
            switch (i){
                case 0:
                    bt.setText("sin");
                    break;
                case 1:
                    bt.setText("tan");
                    break;
                case 2:
                    bt.setText("log");
                    break;
                case 3:
                    bt.setText("shift");
                    break;
            }
            node.add(bt);
        }
        develop.add(node,BorderLayout.SOUTH);
    }

    private void inputInit(JPanel whole) {
        input = new JPanel(new BorderLayout());
        ta = new JTextArea(1,16);
        ta.setFont(Font.getFont(Font.DIALOG_INPUT));
        ta.setEditable(false);
        ta.setMaximumSize(new Dimension(150,20));
        JButton bt = new JButton("Ac");
        bt.setMaximumSize(new Dimension(20,20));
        input.add(ta,BorderLayout.WEST);
        input.add(bt,BorderLayout.EAST);
        whole.add(input,BorderLayout.NORTH);
    }

    private void numberInit(JPanel whole) {
        numbers = new JPanel(new GridLayout(4,4,12,12));
        for (int i = 0; i < 12; i++) {
            JButton btn = new JButton();
            if (i<3){
                btn.setMaximumSize(new Dimension(20,20));
                btn.setText(String.valueOf(i+7));
                numbers.add(btn);
            }
            if (i==3){
                btn.setMaximumSize(new Dimension(20,20));
                btn.setText("/");
                numbers.add(btn);
            }
            else if (i>3 && i<7){
                btn.setMaximumSize(new Dimension(20,20));
                btn.setText(String.valueOf(i));
                numbers.add(btn);
            }
            if (i==7){
                btn.setMaximumSize(new Dimension(20,20));
                btn.setText("x");
                numbers.add(btn);
            }
            if (i>7 && i<11){
                btn.setMaximumSize(new Dimension(20,20));
                btn.setText(String.valueOf(i-7));
                numbers.add(btn);
            }
            if (i==11){
                btn.setMaximumSize(new Dimension(20,20));
                btn.setText("--");
                numbers.add(btn);
            }
        }
        JButton btn = new JButton();
        btn.setMaximumSize(new Dimension(20,20));
        btn.setText("0");
        numbers.add(btn);
        btn = new JButton();
        btn.setMaximumSize(new Dimension(20,20));
        btn.setText(".");
        numbers.add(btn);
        btn = new JButton();
        btn.setMaximumSize(new Dimension(20,20));
        btn.setText("=");
        numbers.add(btn);
        btn = new JButton();
        btn.setMaximumSize(new Dimension(20,20));
        btn.setText("+");
        numbers.add(btn);
        whole.add(numbers,BorderLayout.CENTER);
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        System.out.println(tp.getSelectedIndex());
        if (tp.getSelectedIndex() == 1){
            this.setSize(270,250);
        }else{
            this.setSize(250,230);
        }
    }
}
