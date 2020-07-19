package com.crazybubble.manager;

import com.crazybubble.element.ElementObj;
import com.crazybubble.manager.GameElement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Magic Gunner
 * @˵�� ������Ԫ�ع�������ר�Ŵ洢����Ԫ�أ�ͬʱ�ṩ����
 * ������ͼ�Ϳ��ƻ�ȡ����
 *
 */
public class ElementManager {


    private static ElementManager EM = null;//����
    private Map<GameElement, List<ElementObj>> gameElements;
    private Map<String, Integer> prioriMap;//��ʼ����ͼͼ��˳��

    //���Ԫ��
    public void addElement(ElementObj obj, GameElement ge) {
        //��Ӷ��󵽼����У���keyֵ���д洢
        gameElements.get(ge).add(obj);
    }

    //��ȡ����Ԫ��
    public Map<GameElement, List<ElementObj>> getGameElements() {
        return gameElements;
    }

    //����key����list���ϣ�ȡ��ĳһ��Ԫ��
    public List<ElementObj> getElementsByKey(GameElement ge) {
        return gameElements.get(ge);
    }


    public static synchronized ElementManager getManager() {
        //synchronized�߳�����֤������ִ����ֻ��һ���߳�
        if (EM == null) {
            EM = new ElementManager();
        }
        return EM;
    }

    public ElementManager() {//���췽��˽�л�
        init();//ʵ��������
    }


    public void init() {//ʵ����
        gameElements = new HashMap<GameElement, List<ElementObj>>();
        //��ÿ��Ԫ�ؼ��϶����뵽map��
        for (GameElement ge :
                GameElement.values()) {
            gameElements.put(ge, new ArrayList<ElementObj>());
        }
        //���ߣ��ӵ�����ըЧ��������Ч��etc
    }
    
    //ͼ�����ȼ�����
    private void initPriorityMap() {
    	
    }
}
