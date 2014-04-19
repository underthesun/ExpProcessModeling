/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.awt.Point;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import main.MainPanel;
import main.NodePanel;
import processcheck.Node;
import processcheck.NodeProcess;

/**
 *
 * @author b1106
 */
public class POJOUtils {

    public static void toFile(ArrayList<Node> nodes, ArrayList<NodeProcess> nodeProcesses, File file) {
        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new FileWriter(file));
            Gson gson = new GsonBuilder().setPrettyPrinting().create();

            ArrayList<NodeProcessPOJO> nodeProcessPOJO = nodeProcessToPOJO(nodeProcesses);
            ArrayList<NodePOJO> nodePOJO = nodeToPOJO(nodes);
            ProcessPOJO pojo = new ProcessPOJO();
            pojo.setNodeProcessPOJO(nodeProcessPOJO);
            pojo.setNodePOJO(nodePOJO);
            gson.toJson(pojo, ProcessPOJO.class, bw);
        } catch (IOException ex) {
            Logger.getLogger(MainPanel.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                bw.close();
            } catch (IOException ex) {
                Logger.getLogger(MainPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public static ArrayList<NodePOJO> nodeToPOJO(ArrayList<Node> nodes) {
        ArrayList<NodePOJO> nodePOJOs = new ArrayList<NodePOJO>();
        for (Node node : nodes) {
            NodePOJO nPOJO = new NodePOJO();
            ArrayList<Node> preNodes = node.getPreNodes();
            ArrayList<Node> postNodes = node.getPostNodes();
            int[] preIds = new int[preNodes.size()];
            int[] postIds = new int[postNodes.size()];

            for (int i = 0; i < preIds.length; i++) {
                preIds[i] = preNodes.get(i).getId();
            }
            for (int i = 0; i < postIds.length; i++) {
                postIds[i] = postNodes.get(i).getId();
            }

            nPOJO.setName(node.getText());
            nPOJO.setDimension(node.getPreferredSize());
            nPOJO.setId(node.getId());
            nPOJO.setPoint(node.getLocation());
            nPOJO.setProId(node.getProID());
            nPOJO.setPostNodes(postIds);
            nPOJO.setPreNodes(preIds);
            nodePOJOs.add(nPOJO);
        }
        return nodePOJOs;
    }

    public static ArrayList<NodeProcessPOJO> nodeProcessToPOJO(ArrayList<NodeProcess> nodeProcesses) {
        ArrayList<NodeProcessPOJO> nodeProcessPOJO = new ArrayList<NodeProcessPOJO>();
        for (NodeProcess np : nodeProcesses) {
            NodeProcessPOJO npPOJO = new NodeProcessPOJO();
            ArrayList<Node> nodes = np.getNodes();
            int[] nodeIds = new int[nodes.size()];
            for (int i = 0; i < nodeIds.length; i++) {
                nodeIds[i] = nodes.get(i).getId();
            }
            npPOJO.setpName(np.getpName());
            npPOJO.setText(np.getText());
            npPOJO.setNodes(nodeIds);
            npPOJO.setDimension(np.getPreferredSize());
            npPOJO.setPoint(np.getLocation());
            npPOJO.setSerial(np.getSerial());
            nodeProcessPOJO.add(npPOJO);
        }
        return nodeProcessPOJO;
    }

    public static void fromFile(File file, NodePanel nodePanel) {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(file));
            Gson gson = new Gson();
            ProcessPOJO pojo = gson.fromJson(br, ProcessPOJO.class);
            ArrayList<NodeProcessPOJO> nodeProcessPOJO = pojo.getNodeProcessPOJO();
            ArrayList<NodePOJO> nodePOJO = pojo.getNodePOJO();

            ArrayList<Node> nodes = new ArrayList<Node>();
            Node.resetCounter();
            for (NodePOJO npojo : nodePOJO) {
                String name = npojo.getName();
                int proId = npojo.getProId();
                Point point = npojo.getPoint();
                Node node = new Node(name, point, proId, nodePanel);
                node.setId(npojo.getId());
                node.setPreferredSize(npojo.getDimension());
                nodePanel.add(node);
                nodes.add(node);
            }
            for (Node node : nodes) {
                int[] preIds = null;
                int[] postIds = null;
                for (NodePOJO npojo : nodePOJO) {
                    if (npojo.getId() == node.getId()) {
                        preIds = npojo.getPreNodes();
                        postIds = npojo.getPostNodes();
                    }
                }
                for (Node n : nodes) {
                    for (int id : preIds) {
                        if (n.getId() == id) {
                            node.getPreNodes().add(n);
                        }
                    }
                    for (int id : postIds) {
                        if (n.getId() == id) {
                            node.getPostNodes().add(n);
                        }
                    }
                }
            }

            ArrayList<NodeProcess> nodeProcess = new ArrayList<NodeProcess>();
            for (NodeProcessPOJO npo : nodeProcessPOJO) {
                String text = npo.getText();
                Point point = npo.getPoint();
                int serial = npo.getSerial();
                int[] nodeIds = npo.getNodes();
                NodeProcess np = new NodeProcess(text, point, serial, nodePanel);
                np.setPreferredSize(npo.getDimension());
                ArrayList<Node> nodesContained = new ArrayList<Node>();
                for (int i = 0; i < nodeIds.length; i++) {
                    for (Node node : nodes) {
                        if (node.getId() == nodeIds[i]) {
                            nodesContained.add(node);
                        }
                    }
                }
                np.setNodes(nodesContained);
                nodeProcess.add(np);
                nodePanel.add(np);
            }
            nodePanel.setNodes(nodes);
            nodePanel.setNodeProcess(nodeProcess);
            nodePanel.revalidate();
            nodePanel.repaint();
        } catch (IOException ex) {
            Logger.getLogger(MainPanel.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                br.close();
            } catch (IOException ex) {
                Logger.getLogger(MainPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
