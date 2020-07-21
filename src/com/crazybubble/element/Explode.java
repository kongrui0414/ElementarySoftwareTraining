package com.crazybubble.element;

import com.crazybubble.manager.ElementManager;
import com.crazybubble.manager.GameElement;
import com.crazybubble.manager.GameLoad;
import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.Timer;

/**
 * @description ���ݱ�ը
 */
public class Explode extends ElementObj {

    private Bubble bubble;

    private int imgX = 0;
    private int imgY = 0;

    //��ըʱ�����
    private int explodeTime = 0;
    //��ը��Χ
    private int scope;
    //��ը����
    private int power;
    //��ը������obj�б�
    private List<ElementObj> isExplodeObj = new ArrayList<>();

    //ͼƬƫ����
    public static int sx1;
    public static int sy1;
    public static int sx2;
    public static int sy2;
    public static int pixel;


    public Explode(){}

    public Explode(Bubble bubble) {
        this.bubble = bubble;
    }

    @Override
    public void showElement(Graphics g) {
        for (int i = -2; i <= 2; i++) {
            g.drawImage(this.getIcon().getImage(),
                    this.getX() + this.getW() * i, this.getY(),
                    this.getX() + this.getW() * (i + 1), this.getY() + this.getH(),
                    imgX, imgY,
                    32 + imgX, 48 + imgY, Color.blue, null);
        }
        for (int i = -2; i <= 2; i++) {
            g.drawImage(this.getIcon().getImage(),
                    this.getX(), this.getY() + this.getH() * i,
                    this.getX() + this.getW(), this.getY() + this.getH() * (i + 1),
                    imgX, imgY,
                    32 + imgX, 48 + imgY, Color.blue, null);
        }

    }

    @Override
    public ElementObj createElement(String str) {
        this.setX(bubble.getX());
        this.setY(bubble.getY());
        this.setW(bubble.getW());
        this.setH(bubble.getH());
        //ȷ��isExplodeObj������
        this.isExplodeObj.add(new Player());
        ImageIcon icon = GameLoad.imgMap.get("explode");
        this.setIcon(icon);
        return this;
    }

    @Override
    public void destroy() {
        if (explodeTime < 20) {
            explodeTime++;
        } else {
            this.setLive(false);
        }

    }

    @Override
    public void model(long time, ElementObj obj) {
        updateImage(time, obj);
        destroy();
    }

    @Override
    protected void updateImage(long time, ElementObj obj) {
        super.updateImage(time, obj);
    }

    @Override
    public void crashMethod(ElementObj obj) {
        if (obj instanceof Player || obj instanceof MapObj) {
            boolean isExist = false;
            //��֤һ�α�ըֻ��Ѫһ��
            for (ElementObj elementObj :
                    this.isExplodeObj) {
                if (elementObj.equals(obj)) {
                    isExist = true;
                }
            }
            if (!isExist) {
                this.isExplodeObj.add(obj);
                if (obj.getHp() > 0) {
                    obj.setHp(obj.getHp() - bubble.getPower());
                } else {
                    obj.setLive(false);
                }
            }
        }
    }

    public int getScope() {
        return scope;
    }

    public void setScope(int scope) {
        this.scope = scope;
    }
}
