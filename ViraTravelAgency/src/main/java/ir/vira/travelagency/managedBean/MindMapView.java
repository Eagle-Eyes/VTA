package ir.vira.travelagency.managedBean;

import lombok.Getter;
import lombok.Setter;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.mindmap.DefaultMindmapNode;
import org.primefaces.model.mindmap.MindmapNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.util.UUID;

@ViewScoped
@ManagedBean
@Getter
@Setter
public class MindMapView extends BaseBean {
    
    private static final Logger logger = LoggerFactory.getLogger(MindMapView.class);
    
    private MindmapNode root;
    
    private MindmapNode selectedNode;
    
    public MindMapView() {
        logger.warn("MindMapView Bean");
        
        root = new DefaultMindmapNode("google.com", "Google WebSite", "FFCC00", false);
        
        MindmapNode ips = new DefaultMindmapNode("IPs", "IP Numbers", "6e9ebf", true);
        MindmapNode ns = new DefaultMindmapNode("NS(s)", "Namespaces", "6e9ebf", true);
        MindmapNode malware = new DefaultMindmapNode("Malware", "Malicious Software", "6e9ebf", true);
        
        root.addNode(ips);
        root.addNode(ns);
        root.addNode(malware);
    }
    
    public MindmapNode getRoot() {
        return root;
    }
    
    public MindmapNode getSelectedNode() {
        return selectedNode;
    }
    
    public void setSelectedNode(MindmapNode selectedNode) {
        this.selectedNode = selectedNode;
    }
    
    public void onNodeSelect(SelectEvent event) {
        MindmapNode node = (MindmapNode) event.getObject();
        
        //populate if not already loaded
        if (node.getChildren().isEmpty()) {
            Object label = node.getLabel();
            
            if (label.equals("NS(s)")) {
                for (int i = 0; i < 25; i++) {
                    node.addNode(new DefaultMindmapNode("ns" + i + ".google.com", "Namespace " + i + " of Google", "82c542", false));
                }
            } else if (label.equals("IPs")) {
                for (int i = 0; i < 18; i++) {
                    node.addNode(new DefaultMindmapNode("1.1.1." + i, "IP Number: 1.1.1." + i, "fce24f", false));
                }
            } else if (label.equals("Malware")) {
                for (int i = 0; i < 18; i++) {
                    String random = UUID.randomUUID().toString();
                    node.addNode(new DefaultMindmapNode("Malware-" + random, "Malicious Software: " + random, "3399ff", false));
                }
            }
        }
    }
    
    public void onNodeDblselect(SelectEvent event) {
        this.selectedNode = (MindmapNode) event.getObject();
    }
}
