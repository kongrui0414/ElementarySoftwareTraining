package com.crazybubble.element;

import com.crazybubble.manager.ElementManager;
import com.crazybubble.manager.GameElement;
import com.crazybubble.manager.GameLoad;
import jdk.nashorn.api.tree.CaseTree;

import javax.swing.*;
import java.awt.*;

/**
 * @author Magic Gunner
 * @description �����
 */
public class Player extends ElementObj {
    //������ͣ�0�������A��1�������B
    private int playerType;

    //���̼���
    private boolean left = false;
    private boolean up = false;
    private boolean right = false;
    private boolean down = false;

    //�и�ͼƬ����
    private int imgX = 0;
    private int imgY = 0;
    //����ͼƬˢ��ʱ��
    private int imgTime = 0;

    //���﷽��
    private String fx = "";
    //����״̬
    private boolean attackType = false;
    //Ѫ��
    private int hp = 5;
    //�ƶ��ٶ�
    private int speed = 10;
    //������ͷ���������
    private int bubbleNum = 0;
    //���ͷ���������
    private int bubbleTotal = 3;
    //��������
    private int bubblePower = 1;
    //�޵�״̬
    private boolean isSuper = false;
    //��ͣ״̬
    private boolean isStop = false;
    //�ܶ�״̬
    private boolean isRun = false;

    @Override
    public void showElement(Graphics g) {
        g.drawImage(this.getIcon().getImage(), this.getX(), this.getY(),
                this.getX() + this.getW(),
                this.getY() + this.getH(),
                24 + (imgX * 100), 42 + (imgY * 100),
                72 + (imgX * 100), 99 + (imgY * 100), null);
    }

    @Override
    public ElementObj createElement(String str) {
        String[] split = str.split(",");
        this.setX(Integer.parseInt(split[0]));
        this.setY(Integer.parseInt(split[1]));
        ImageIcon icon = GameLoad.imgMap.get("player");
        this.setW(30);
        this.setH(30);
        this.setIcon(icon);
        this.setPlayerType(Integer.parseInt(split[2]));
        return this;
    }


    protected void addBubble() {
        if (bubbleNum <= bubbleTotal)
            if (attackType) {
                ElementObj obj = GameLoad.getObj("bubble");
                Bubble element = (Bubble) obj.createElement(this.toStr());
                element.bubbleCrash();
                if (!element.isCrash()) {
                    ElementManager.getManager().addElement(element, GameElement.BUBBLE);
                    ++bubbleNum;
                    this.attackType = false;
                }

            }
//        try {
//            //�����ļ���������
//            Class<?> forName = Class.forName("com.crazybubble.element");
//            ElementObj element = PlayFile.class.newInstance().createElement("");
//        } catch (InstantiationException e) {
//            e.printStackTrace();
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
    }

    /**
     * @return
     * @description ����ǰ�������Ϣת��Ϊ�ַ���
     */
    @Override
    public String toStr() {
        int x = this.getX();
        int y = this.getY();
        int w = this.getW();
        int h = this.getH();
        int playerType = this.playerType;
        return "x:" + x + ",y:" + y + ",w:" + w + ",h:" + this.getH() + ",playerType:" + playerType;
    }

    /**
     * @param time
     * @description �ܶ�ʱ����ͼƬ
     */
    @Override
    protected void updateImage(long time) {
        if (time - imgTime > 3 && this.isRun) {
            imgTime = (int) time;
            switch (this.fx) {
                case "up":
                    imgY = 3;
                    break;
                case "down":
                    imgY = 0;
                    break;
                case "left":
                    imgY = 1;
                    break;
                case "right":
                    imgY = 2;
                    break;
            }
            imgX++;
            if (imgX > 3) {
                imgX = 0;
            }
        }
    }

    protected void move() {
        if (this.left && this.getX() > 0)
            this.setX(this.getX() - this.speed);
        if (this.up && this.getY() > 0)
            this.setY(this.getY() - this.speed);
        if (this.right && this.getX() < 800 - this.getW())
            this.setX(this.getX() + this.speed);
        if (this.down && this.getY() < 800 - this.getH())
            this.setY(this.getY() + speed);
    }

    /**
     * @description ģ�巽������װ���в���
     */
    @Override
    public final void model(long time) {
        updateImage(time);
        move();
        addBubble();
    }

    /**
     * @param bindType ��������� true������ false�����ɿ�
     * @param key      ���������̵�codeֵ
     * @description ���̼���
     */
    public void keyClick(boolean bindType, int key) {
        if (bindType) {
            switch (key) {
//                case 65:
                case 37:
                    this.left = true;
                    this.right = false;
                    this.up = false;
                    this.down = false;
                    this.fx = "left";
                    break;
//                case 87:
                case 38:
                    this.up = true;
                    this.down = false;
                    this.left = false;
                    this.right = false;
                    this.fx = "up";
                    break;
//                case 68:
                case 39:
                    this.right = true;
                    this.left = false;
                    this.up = false;
                    this.down = false;
                    this.fx = "right";
                    break;
//                case 83:
                case 40:
                    this.down = true;
                    this.up = false;
                    this.left = false;
                    this.right = false;
                    this.fx = "down";
                    break;
                //��������״̬
                case 32:
//                case 108:
                    this.attackType = true;
                    break;
            }
            this.isRun = true;
        } else {
            switch (key) {
                case 37:
                    this.left = false;
                    break;
                case 38:
                    this.up = false;
                    break;
                case 39:
                    this.right = false;
                    break;
                case 40:
                    this.down = false;
                    break;
                //�رչ���״̬
                case 32:
                    this.attackType = false;
                    break;
            }
            this.isRun = false;
        }
    }

    @Override
    public void destroy() {
        ElementManager em = ElementManager.getManager();
        em.addElement(this, GameElement.DIE);
    }

    @Override
    public void crashMethod(ElementObj obj) {
        //���֮����ײ
        if (Player.class.equals(obj.getClass())) {
            //��Ҫȡ���ƶ�
        }
        //��Һ͵���֮����ײ
        else if (Prop.class.equals(obj.getClass())) {
            Prop prop = (Prop) obj;
            this.propCrash(prop.getPropType());
        }
        //��Һ�����֮����ײ
        else if (Bubble.class.equals(obj.getClass())) {
            Bubble bubble = (Bubble) obj;
            this.bubbleCrash(bubble);
        }
        //��Һ͵�ͼ֮����ײ
        else if (MapObj.class.equals(obj.getClass())) {
            MapObj mapObj = (MapObj) obj;
            this.mapCrash();
        }

    }


    /**
     * @description ��Һ͵���֮����ײ
     */
    public void propCrash(String propType) {
        //���ط���ֵҲ�����������ļ����ã���ʱ��д�ɶ�ֵ
        switch (propType) {
            case "superpower":
                this.setBubblePower(2);
                System.out.println("superpower");
                break;
            case "bubbleadd":
                this.setBubbleTotal(4);
                System.out.println("bubbleadd");
                break;
            case "runnningshoes":
                System.out.println("runningshoes");
                break;
            case "crazydiamond":
                this.setHp(5);
                System.out.println("crazydiamond");
                break;
        }
    }

    /**
     * @description ��Һ�����֮����ײ
     */
    public void bubbleCrash(Bubble bubble) {
        //����������������������ϱ�ը�����һ�Ҫ�ж�������ͣ�����ʱ��д
//        bubble.setCrash(true);
//        this.setHp(this.getHp() - 1);
    }

    /**
     * @description ��Һ͵�ͼ֮����ײ
     */
    public void mapCrash() {
        //��Ҫȡ���ƶ�
        if (this.left && this.getX() > 0)
            this.setX(this.getX() + this.speed);
        if (this.up && this.getY() > 0)
            this.setY(this.getY() + this.speed);
        if (this.right && this.getX() < 800 - this.getW())
            this.setX(this.getX() - this.speed);
        if (this.down && this.getY() < 800 - this.getH())
            this.setY(this.getY() - speed);
    }

    /**
     * @param playerType
     * @description BubbleAdd������������Ŀ
     */
    public void propBubbleAdd(int playerType) {
        if (playerType == this.getPlayerType()) {
            this.setBubbleTotal(this.getBubbleTotal() + 1);
        }
    }

    /**
     * @param playerType
     * @description SuperPower����ɫҩˮ���������ݹ�����
     */
    public void propSuperPower(int playerType) {
        if (playerType == this.getPlayerType()) {
            this.setBubblePower(this.getBubblePower() + 1);
        }
    }

    public void propMirror(int playerType) {
        if (playerType == this.getPlayerType()) {
            //��������
        }
    }

    public void propRunningShoes() {
        if (playerType == this.getPlayerType()) {
            this.setSpeed(this.getSpeed() + 1);
        }
    }

    public void propTheWorld() {
        if (playerType == this.getPlayerType()) {
            //զ��³��
        }
    }

    public void propCrazyDiamond() {
        if (playerType == this.getPlayerType()) {
            this.setHp(5);
        }
    }

    public void propWhiteAlbum() {
        if (playerType == this.getPlayerType()) {
            //����Լ�ֹͣ
        }
    }

    public void propGodStatus() {
        if (playerType == this.getPlayerType()) {
            //�޵�
            this.setSuper(true);
        }
    }

    public int getPlayerType() {
        return playerType;
    }

    public void setBubbleNum(int playerType) {
        if (playerType == this.playerType)
            --bubbleNum;
    }

    public void setPlayerType(int playerType) {
        this.playerType = playerType;
    }

    public boolean isLeft() {
        return left;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }

    public boolean isUp() {
        return up;
    }

    public void setUp(boolean up) {
        this.up = up;
    }

    public boolean isRight() {
        return right;
    }

    public void setRight(boolean right) {
        this.right = right;
    }

    public boolean isDown() {
        return down;
    }

    public void setDown(boolean down) {
        this.down = down;
    }

    public int getImgX() {
        return imgX;
    }

    public void setImgX(int imgX) {
        this.imgX = imgX;
    }

    public int getImgY() {
        return imgY;
    }

    public void setImgY(int imgY) {
        this.imgY = imgY;
    }

    public int getImgTime() {
        return imgTime;
    }

    public void setImgTime(int imgTime) {
        this.imgTime = imgTime;
    }

    public String getFx() {
        return fx;
    }

    public void setFx(String fx) {
        this.fx = fx;
    }

    public boolean isAttackType() {
        return attackType;
    }

    public void setAttackType(boolean attackType) {
        this.attackType = attackType;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getBubbleNum() {
        return bubbleNum;
    }

    public int getBubbleTotal() {
        return bubbleTotal;
    }

    public void setBubbleTotal(int bubbleTotal) {
        this.bubbleTotal = bubbleTotal;
    }

    public int getBubblePower() {
        return bubblePower;
    }

    public void setBubblePower(int bubblePower) {
        this.bubblePower = bubblePower;
    }

    public boolean isSuper() {
        return isSuper;
    }

    public void setSuper(boolean aSuper) {
        isSuper = aSuper;
    }

    public boolean isStop() {
        return isStop;
    }

    public void setStop(boolean stop) {
        isStop = stop;
    }

    public boolean isRun() {
        return isRun;
    }

    public void setRun(boolean run) {
        isRun = run;
    }
}
