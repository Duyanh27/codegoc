package view;

import model.Place;
import javax.swing.*;
import java.awt.*;
import java.util.List;

public class MapView extends JFrame {
    private List<Place> places;  // Store places to be drawn

    public MapView() {
        setTitle("Map Visualization");
        setSize(800, 600); // Size of the GUI window
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.places = null;

        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (places != null) {
                    drawPlaces(g);
                }
            }
        };
        panel.setLayout(null);
        getContentPane().add(panel, BorderLayout.CENTER);
    }

    public void displayResults(List<Place> places) {
        this.places = places;
        repaint();
    }

    private void drawPlaces(Graphics g) {
        g.setColor(Color.RED);
        int width = getWidth();
        int height = getHeight();

        // Assuming the actual map size is 10,000,000 x 10,000,000
        double scale = Math.min(width, height) / 10_000_000.0;

        for (Place place : places) {
            int x = (int) (place.getX() * scale);
            int y = (int) (place.getY() * scale);
            g.fillOval(x - 5, y - 5, 10, 10);  // Draw a circle for each place
            // Dynamically display the service type
            String services = String.join(", ", place.getServices().stream().map(Enum::name).toArray(String[]::new));
            g.drawString(services, x, y);
        }
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            MapView view = new MapView();
            view.setVisible(true);
        });
    }
}
