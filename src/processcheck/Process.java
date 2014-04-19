/*
 * 中间过程，树中间节点
 */
package processcheck;

import components.DraggableButton;
import components.ProcessConfDialog;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;
import main.ProcessPanel;

/**
 *
 * @author shuai
 */
public class Process extends DraggableButton {

    private boolean isAotom;
    private int serial;
    private Process preProcess;
    private ArrayList<Process> postProcess;
    private ProcessPanel processPanel;
    private ProcessConfDialog dialog;
    private String pName;

    public Process(String s, Point p, boolean isAotom, Process parent, ProcessPanel pp) {
        super(s, p);
        this.pName = s;
        this.isAotom = isAotom;
        this.serial = -1;
        this.preProcess = parent;
        this.processPanel = pp;
        this.dialog = pp.getConfDialog();
        this.postProcess = new ArrayList<Process>();
        addListener(this);
        if (isAotom) {
            setBorder(new LineBorder(Color.green));
        }
    }

    private void addListener(final Process process) {
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                if (!isAotom && !hasPosts()) {
                    Object input = JOptionPane.showInputDialog("输入子过程数目");
                    if (input != null) {
                        try {
                            int num = Integer.parseInt(input.toString());
                            dialog.setNum(num);
                            dialog.setVisible(true);
                            if (dialog.getChildren() != null) {
                                ArrayList<Object[]> children = dialog.getChildren();
                                Point pSelf = getLocation();
                                Point pChild = null;
                                int len = children.size();
                                for (int i = 0; i < len; i++) {
                                    pChild = new Point(pSelf.x + getWidth() + 50, pSelf.y + (i - len / 2) * 60);
                                    JTextField tf = (JTextField) children.get(i)[0];
                                    JCheckBox cb = (JCheckBox) children.get(i)[1];
                                    Process p = new Process(tf.getText(), pChild, cb.isSelected(), process, processPanel);
                                    postProcess.add(p);
                                    processPanel.addProcess(p);
                                }
                                processPanel.repaint();
                                dialog.reset();
                            }
                        } catch (NumberFormatException ne) {
                        }
                    }
                }
                if (isAotom && !isNumbered()) {
                    Object input = JOptionPane.showInputDialog("输入原子过程序号");
                    if (input != null) {
                        try {
                            int sn = Integer.parseInt(input.toString());
                            if (sn > 0) {
                                setSerial(sn);
                                setBackground(UIManager.getColor("Button.ground"));
                                setpName(getText());
                                String s = "[" + sn + "]" + pName;
                                setText(s);
                                FontMetrics metrics = getFontMetrics(getFont());
                                int width = metrics.stringWidth(s);
                                int height = metrics.getHeight();
                                Dimension newDimension = new Dimension(width + 30, height + 10);
                                setPreferredSize(newDimension);
                                setBounds(new Rectangle(getLocation(), newDimension));
                            }
                        } catch (NumberFormatException ne) {
                        }
                    }
                }
            }
        });
    }

    public boolean isIsAotom() {
        return isAotom;
    }

    public void setIsAotom(boolean isAotom) {
        this.isAotom = isAotom;
    }

    public int getSerial() {
        return serial;
    }

    public void setSerial(int serial) {
        this.serial = serial;
    }

    public Process getPreProcess() {
        return preProcess;
    }

    public void setPreProcess(Process preProcess) {
        this.preProcess = preProcess;
    }

    public ArrayList<Process> getPostProcess() {
        return postProcess;
    }

    public void setPostProcess(ArrayList<Process> postProcess) {
        this.postProcess = postProcess;
    }

    public ProcessPanel getPp() {
        return processPanel;
    }

    public void setPp(ProcessPanel pp) {
        this.processPanel = pp;
    }

    public boolean isNumbered() {
        return serial == -1 ? false : true;
    }

    public boolean hasPosts() {
        return !postProcess.isEmpty();
    }

    public String getpName() {
        return pName;
    }

    public void setpName(String pName) {
        this.pName = pName;
    }
}
