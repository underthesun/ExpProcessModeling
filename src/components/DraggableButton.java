/*
 * 每个过程均为一个可移动的DraggableButton
 */
package components;

import java.awt.Container;
import java.awt.Font;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import javax.swing.JButton;

/**
 *
 * @author shuai
 */
public class DraggableButton extends JButton {
    //鼠标的原始点，按按钮坐标算，就是本按钮的左上方为原点

    private Point pLeft = new Point();
    private Point pRight = new Point();
    private Point origin = new Point();
    //本个按钮
    DraggableButton thisButton = this;
    //父容器，如你将按钮放在一个面板上，面板就是按钮的父容器
    Container parent = null;
    //注意, parent不能在构造函数中初始化，因为当按钮创建的时候，他还没有放在一个容器里，
    //这个时候按钮是没有父容器的。

//    public DraggableButton() {
//        setBounds(new Rectangle(new Point(10,10), getPreferredSize()));
//        addListener();
//        setPoints(this.getLocation());
//    }
    public DraggableButton(String text, Point p) {
        super(text);
        super.setFont(new Font("宋体", 1, 16));
//        System.out.println("Size: " + getPreferredSize());
        setBounds(new Rectangle(p, getPreferredSize()));
        addListener();
        setPoints(getLocation());
    }

    private void setPoints(Point location) {
        pLeft.x = location.x;
        pLeft.y = location.y + getBounds().height / 2;
        pRight.x = location.x + getBounds().width;
        pRight.y = location.y + getBounds().height / 2;
    }

    public Point[] getPoints() {
        return new Point[]{pLeft, pRight};
    }

    private void addListener() {
        //一个匿名类，实现mousePressed，当鼠标按下时，得到鼠标的坐标，
        //注意，是按本按钮的坐标系来算
        this.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent event) {
                origin = event.getPoint();
            }
//            public void mouseClicked(MouseEvent event) {
//                System.out.println("Fuck");
//            }
        });

        //匿名类，实现mouseDragged。原理很简单，就是当鼠标在按钮上面拖动时候，
        //改变按钮的位置，让它随着鼠标动
        this.addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent event) {
                /* 得到按钮在父容器中的位置。
                 ** 注意，这个时候得到的位置是按父容器的坐标系来算的，就是父容器的
                 ** 左上角为原点。geLocation, 和setLocation都是按父容器的坐标系来算。
                 ** event.getX(), event.getY()，按本按钮的坐标系来算
                 ** 所以下面的一条代码就将坐标转换，从原来的按钮位置和原来的鼠标位置
                 ** 还有现在的鼠标位置，得到现在的按钮位置 */
//                System.out.println("Event.x：" + event.getX());
                Point p = thisButton.getLocation();
//                System.out.println("button location：" + p.toString());
                Point location = new Point(p.x + event.getX() - origin.x, p.y + event.getY() - origin.y);

                //这个时候可以初始化parent了，当按钮可以拖动的时候
                //当然是已经放在一个容器里面了
                if (parent == null) {
                    parent = thisButton.getParent();
                }

                // 下面的几个if 语句是判断按钮位置，不能让按钮落到父容器外面。
                // 如果在外面，就看不到按钮了
                if (location.x < 0) {
                    location.x = 0;
                } else if (location.x + thisButton.getWidth() > parent.getWidth()) {
                    location.x = parent.getWidth() - thisButton.getWidth();
                }

                if (location.y < 0) {
                    location.y = 0;
                } else if (location.y + thisButton.getHeight() > parent.getHeight()) {
                    location.y = parent.getHeight() - thisButton.getHeight();
                }
                thisButton.setLocation(location);
                setPoints(location);
                getParent().repaint();
            }
        });
    }
};
