package osproject;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.LinkedList;
import javax.swing.JPanel;

public class GanttChart extends JPanel {
    LinkedList<Process> GProcesses;
    
    GanttChart(LinkedList<Process> p) {
        initComponents();
        
        this.GProcesses = new LinkedList(p);
        
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        int x;
        int y;
        int width;
        int height;
        String text;
        int x1;
        int y1;
        Graphics2D g2 = (Graphics2D) g;
        for (int i = 0; i < GProcesses.size(); i++) {
            x = GProcesses.get(i).start*20+20;
            y = 100;
            width = GProcesses.get(i).finish*20-GProcesses.get(i).start*20;
            height = 50;
            text = GProcesses.get(i).name;
            x1 = x+(width/2);
            y1 = height+40;


            g2.draw(new Rectangle2D.Double(x, y, width,height));

            if(GProcesses.size()-1==i){
                
                g2.drawString(""+GProcesses.get(i).start, GProcesses.get(i).start*20+15, 170);
                g2.drawString(""+GProcesses.get(i).finish, GProcesses.get(i).start*20+15+width, 170);
                g2.drawString(""+GProcesses.get(i).name, x1, y1);
            }
            else{
                g2.drawString(""+GProcesses.get(i).start, GProcesses.get(i).start*20+15, 170);
                g2.drawString(""+GProcesses.get(i).name, x1, y1);
            }
        }
    }

 private void initComponents() {
        setBackground(new java.awt.Color(255, 255, 255));
        setAutoscrolls(true);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }
}
