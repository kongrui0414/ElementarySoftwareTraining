package com.crazybubble.element;

import com.crazybubble.element.ElementObj;
import com.crazybubble.manager.GameLoad;

import javax.swing.*;
import java.awt.*;

/**
 * @author Magic Gunner
 * @description ��ͼԪ��
 */
public class MapObj extends ElementObj {
    //��ͼԪ������ֵ
    private int hp = 1;
    //��ͼԪ������
    private String mapType = "";
    //��ͼԪ������ֵ
    private int mapHp;
    //�Ƿ񲻿ɴݻ�
    private boolean isStable;

    private int imgX = 0;
    private int imgY = 0;

    @Override
    public void showElement(Graphics g) {
        g.drawImage(this.getIcon().getImage(),
                this.getX(), this.getY(),
                this.getX() + this.getW(), this.getY() + this.getH(),
                imgX, imgY,
                31 + imgX, 31 + imgY, null);
    }


    @Override
    public ElementObj createElement(String str) {
        //���ط����Ե��������ļ�
        String[] arr = str.split(",");
        ImageIcon icon = GameLoad.imgMap.get("map");
        //������Ϣ��mapObj.pro��ȡ

//        switch (arr[0]) {
//            case "GRASS":
////                icon = new ImageIcon("image/image/wall/grass.png");
//                break;
//            case "BRICK":
////                icon = new ImageIcon("image/image/wall/brick.png");
//                break;
//            case "RIVER":
////                icon = new ImageIcon("image/image/wall/river.png");
//                break;
//            case "IRON":
////                icon = new ImageIcon("image/image/wall/iron.png");
//                this.hp = 4;
//                this.mapType = "IRON";
//                break;
//        }
        this.setStable(false);
        this.setMapType(arr[0]);
        this.setX(Integer.parseInt(arr[1]));
        this.setY(Integer.parseInt(arr[2]));
        this.setW(icon.getIconWidth() / 4);
        this.setH(icon.getIconHeight() / 4);
        this.setIcon(icon);

        return this;
    }

    @Override
    public void crashMethod(ElementObj obj) {
//        this.setLive(false);
//        obj.setLive(false);
    }

    /**
     * @description ���õ�ͼԪ�صĴ���״̬
     */
    @Override
    public void setLive(boolean live) {
//        if ("IRON".equals(this.name)) {
//            this.hp--;
//            if (this.hp >= 0) {
//                super.setLive(live);
//                return;
//            }
//        }
        super.setLive(live);
    }

    @Override
    public void model(long time, ElementObj obj) {
        updateImage(time, obj);
    }

    @Override
    protected void updateImage(long time, ElementObj obj) {
        super.updateImage(time, obj);
    }

    @Override
    public void destroy() {
        super.destroy();
    }

    @Override
    public int getHp() {
        return hp;
    }

    @Override
    public void setHp(int hp) {
        this.hp = hp;
    }

    public String getMapType() {
        return mapType;
    }

    public void setMapType(String mapType) {
        this.mapType = mapType;
    }

    public int getMapHp() {
        return mapHp;
    }

    public void setMapHp(int mapHp) {
        this.mapHp = mapHp;
    }

    public boolean isStable() {
        return isStable;
    }

    public void setStable(boolean stable) {
        isStable = stable;
    }
}
