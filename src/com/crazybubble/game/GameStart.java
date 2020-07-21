package com.crazybubble.game;

import com.crazybubble.controller.GameListener;
import com.crazybubble.controller.GameThread;
import com.crazybubble.show.GameBeginJPanel;
import com.crazybubble.show.GameJFrame;
import com.crazybubble.show.GameMainJPanel;
import com.crazybubble.show.GameOverJPanel;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GameStart {

    private static GameJFrame frame = new GameJFrame();
    //ʵ��������
    private static GameListener listener = new GameListener();
    //ʵ�������߳�
    private static GameThread thread = new GameThread();

    public static void main(String[] args) {
        frame.changePanel("begin");
        frame.setVisible(true);
    }

    public static void rule(){
        frame.changePanel("rule");
    }

    public static void start() {
        frame.changePanel("main");
        frame.setKeyListener(listener);
        frame.setThread(thread);
        frame.start();
    }

    public static void over() {
        frame.changePanel("over");
    }

    public static void begin() {
        frame.changePanel("begin");
    }
}

/**
 * 1.������Ϸ�������Ϸ�������ļ���ʽ���ļ���ȡ��ʽ��load��ʽ��
 * 2.�����Ϸ��ɫ��������Ϸ���󣨳�����ڻ���ļ̳У�
 * 3.����pojo�ࣨvo��
 * 4.��Ҫ�ķ������ڸ�������д��������಻֧�֣����Բ����޸ĸ��ࣩ
 * 5.������ã���ɶ����load��add��manager
 * 6.�����ײ�ȵ�ϸ�ڿ���
 */
