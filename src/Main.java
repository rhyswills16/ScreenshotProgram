import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.image.BufferedImage;
import java.io.File;

public class Main {
    private JFrame mainFrame;
    private JLabel statusLabel;
    private JPanel controlPanel;

    private Main() {
        this.mainFrame = new JFrame("Screen Capture");
        this.statusLabel = new JLabel("", JLabel.CENTER);
        this.controlPanel = new JPanel();

        this.mainFrame.setSize(600, 300);
        this.mainFrame.setLayout(new GridLayout(2, 1));
        this.mainFrame.add(this.controlPanel);
        this.mainFrame.add(this.statusLabel);
        this.mainFrame.setVisible(true);
        this.mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        this.controlPanel.setLayout(new FlowLayout());
    }

    private void show() {
        final JButton okButton = new JButton("Take Screenshot");
        final JButton saveButton = new JButton("Click to save your most recent screenshot");

        okButton.addActionListener(e -> {
            Main.this.mainFrame.setVisible(false);

            try {
                final Dimension screenDimension = Toolkit.getDefaultToolkit().getScreenSize();
                final Robot robot = new Robot();
                final BufferedImage image = robot.createScreenCapture(new Rectangle(screenDimension));

                TransferableImage trans = new TransferableImage(image);
                Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
                clipboard.setContents(trans, null);

                /* IF U WANT TO SAVE TO A DIRECTORY
                final File savePath = new File(find out how to find the default images path);
                ImageIO.write(image, "JPG", savePath);
                */

                Main.this.statusLabel.setText("Copied to clipboard.");
                Main.this.mainFrame.setVisible(true);
                } catch (Exception awtException) {
                    awtException.printStackTrace();
                }
        });

        saveButton.addActionListener(e -> {
            final File savePath = new File("Users/rhyswilliams/Desktop");
            // ImageIO.write(image, "JPG", savePath);
        });

        this.controlPanel.add(okButton);
        this.mainFrame.setVisible(true);
        this.mainFrame.add(saveButton);
    }

    public static void main(String[] args) {
        final Main screenCap = new Main();
        screenCap.show();
    }
}