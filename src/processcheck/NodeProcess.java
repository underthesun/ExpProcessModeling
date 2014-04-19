/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package processcheck;

import components.DraggableButton;
import java.awt.Color;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;
import main.NodePanel;

/**
 *
 * @author shuai
 */
public class NodeProcess extends DraggableButton {

    private int serial;
    private ArrayList<Node> nodes;
    private NodePanel nodePanel;
    private String pName;

    public NodeProcess(String s, Point p, int serial, NodePanel np) {
        super(s, p);
//        this.pName = s;
        this.serial = serial;
        this.nodePanel = np;
        this.nodes = new ArrayList<Node>();
        setBorder(new LineBorder(Color.green));
        addListener(this);
    }

    public void addListener(final NodeProcess nodeProcess) {
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                if (!hasNodes()) {
                    String input = JOptionPane.showInputDialog("输入基本过程包含的原子过程数");
                    if (input != null) {
                        try {
                            int num = Integer.parseInt(input);
                            Point p = getLocation();
                            for (int i = 0; i < num; i++) {
                                Node node = new Node(getpName() + "(" + i + ")", new Point(p.x, p.y + (i + 1) * 100), serial, nodePanel);
                                nodes.add(node);
                                nodePanel.addNode(node);
                            }
                            setBackground(UIManager.getColor("Button.ground"));
                        } catch (NumberFormatException ne) {
                        }
                    }
                    nodePanel.repaint();
                }
            }
        });
    }

    public int getSerial() {
        return serial;
    }

    public void setSerial(int serial) {
        this.serial = serial;
    }

    public ArrayList<Node> getNodes() {
        return nodes;
    }

    public void setNodes(ArrayList<Node> nodes) {
        this.nodes = nodes;
    }

    public NodePanel getNodePanel() {
        return nodePanel;
    }

    public void setNodePanel(NodePanel nodePanel) {
        this.nodePanel = nodePanel;
    }

    public boolean hasNodes() {
        return !nodes.isEmpty();
    }

    public void reset() {
        setBackground(UIManager.getColor("Button.ground"));
        repaint();
        nodes.clear();
    }

    public NodeProcess getPreNodePreocess() {
        NodeProcess nodeProcess = null;
        if (serial > 1) {
            nodeProcess = nodePanel.getNodeProcess().get(serial - 1);
        }
        return nodeProcess;
    }

    public NodeProcess getPostNodeProcess() {
        NodeProcess nodeProcess = null;
        if (serial < nodePanel.getNodeProcess().size()) {
            nodeProcess = nodePanel.getNodeProcess().get(serial + 1);
        }
        return nodeProcess;
    }

    public String getpName() {
        return pName;
    }

    public void setpName(String pName) {
        this.pName = pName;
    }   
    
}
