import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Main {
    private JFrame frame;
    private JLabel imageLabel;
    private ImageIcon originalImageIcon;
    private int currentSize;
    private int maxSize;
    private boolean isMaxSizeReached;

    public Main() {
        frame = new JFrame("Resizable Image");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLayout(new BorderLayout());

        originalImageIcon = new ImageIcon("C:\\Users\\Acer\\Downloads\\ball.png"); // Замість "path_to_your_image.jpg" вкажіть шлях до вашого зображення у форматі JPEG
        currentSize = originalImageIcon.getIconWidth();
        maxSize = 400; // Максимальний розмір
        isMaxSizeReached = false;

        imageLabel = new JLabel(originalImageIcon);
        frame.add(imageLabel, BorderLayout.CENTER);

        imageLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (!isMaxSizeReached) {
                    if (currentSize < maxSize) {
                        currentSize += 50;
                        if (currentSize >= maxSize) {
                            isMaxSizeReached = true;
                        }
                    }
                } else {
                    if (currentSize > originalImageIcon.getIconWidth()) {
                        currentSize -= 50;
                        if (currentSize <= originalImageIcon.getIconWidth()) {
                            isMaxSizeReached = false;
                        }
                    }
                }
                resizeImage();
            }
        });

        frame.setVisible(true);
    }

    private void resizeImage() {
        ImageIcon resizedImageIcon = new ImageIcon(originalImageIcon.getImage().getScaledInstance(currentSize, -1, Image.SCALE_DEFAULT));
        imageLabel.setIcon(resizedImageIcon);
        frame.revalidate();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Main();
        });
    }
}