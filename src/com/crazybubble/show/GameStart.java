package com.crazybubble.show;

import com.crazybubble.controller.GameListener;
import com.crazybubble.controller.GameThread;
import com.crazybubble.manager.GameLoad;

public class GameStart {

    /**
     * �����Ψһ���
     *
     * @param args
     */
    public static void main(String[] args) {
        GameJFrame gj = new GameJFrame();
        //ʵ������壬ע�뵽jFrame��
        GameMainJPanel jp = new GameMainJPanel();
        //ʵ��������
        GameListener listener = new GameListener();
        //ʵ�������߳�
        GameThread th = new GameThread();

        
        gj.setJPanel(jp);
        gj.setKeyListener(listener);
        gj.setThread(th);

        gj.start();
    }

}

/**
 * 1.������Ϸ�������Ϸ�������ļ���ʽ���ļ���ȡ��ʽ��load��ʽ��
 * 2.�����Ϸ��ɫ��������Ϸ���󣨳�����ڻ���ļ̳У�
 * 3.����pojo�ࣨvo��
 * 4.��Ҫ�ķ������ڸ�������д��������಻֧�֣����Բ����޸ĸ��ࣩ
 * 5.������ã���ɶ����load��add��manager
 * 6.�����ײ�ȵ�ϸ�ڿ���
 *
 *
 */
