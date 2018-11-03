package com.tilchina.edi.util;

import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.Color;
public class first extends JFrame{
    private JLabel jLabel;
    private JTextField jTextField;
    private JButton jButton,jButton1,jButton2;
    private JTextArea jtextarea;
    private JScrollPane sp;
    public first()
    {
        super();
        this.setSize(700, 500);
        this.getContentPane().setLayout(null);
//        this.add(getJLabel(), null);
//        this.add(getJTextField(), null);
//        this.add(getJButtonok(), null);
//        this.add(getJButtoncancel(),null);
//        this.add(getJButtonzero(),null);
        this.setBackground(Color.red);
        this.add(getJTextArea(), null);

        this.setTitle("calculator");
    }
    private JScrollPane getJTextArea(){
        if(jtextarea==null){
            jtextarea=new JTextArea();
            //jtextarea.setBounds(5, 45, 650, 400);
        }
        //jtextarea.setLineWrap(true);
        sp=new JScrollPane(jtextarea);
        sp.setBounds(5, 45, 650, 400);
        //sp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        return sp;
    }
    /*private JLabel getJLabel() {
        if(jLabel == null) {
            jLabel = new javax.swing.JLabel();
            jLabel.setBounds(10, 10, 150, 30);
            jLabel.setText("请输入需要输出的位数：");
        }

        return jLabel;
    }
    private JTextField getJTextField() {
        if(jTextField == null) {
            jTextField = new javax.swing.JTextField();
            jTextField.setBounds(150, 10, 160, 30);
        }
        return jTextField;
    }*/
    /*private JButton getJButtonok() {
        if(jButton == null) {
            jButton = new javax.swing.JButton();
            jButton.setBounds(400, 10, 100,30);
            jButton.setText("begin");
        }
        return jButton;
    }
    private JButton getJButtoncancel() {
        if(jButton1 == null) {
            jButton1 = new JButton();
            jButton1.setBounds(530, 10, 100, 30);
            jButton1.setText("stop");
        }
        return jButton1;
    }
    private JButton getJButtonzero() {
        if(jButton2 == null) {
            jButton2 = new JButton();
            jButton2.setBounds(310, 10, 80, 30);
            jButton2.setText("清零");
        }
        return jButton2;
    }*/
    public static void main(String[] args)
    {
        first w = new first();
        w.setVisible(true);
    }
}
