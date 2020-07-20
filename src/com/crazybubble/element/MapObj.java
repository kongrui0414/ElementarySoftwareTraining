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
        this.setX(Integer.parseInt(arr[1]));
        this.setY(Integer.parseInt(arr[2]));
        this.setW(icon.getIconWidth()/4);
        this.setH(icon.getIconHeight()/4);
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
    public void model(long time) {
        updateImage(time);
    }

    @Override
    protected void updateImage(long time) {
        super.updateImage(time);
    }

    @Override
    public void destroy() {
        super.destroy();
    }
}
