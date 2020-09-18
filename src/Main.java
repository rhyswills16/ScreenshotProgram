import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.image.BufferedImage;
import java.io.File;

public class Main {
    private final JFrame MAIN_FRAME;
    private final JLabel STATUS_LABEL;
    private final JPanel CONTROL_PANEL;

    private Main() {
        this.MAIN_FRAME = new JFrame("Screen Capture");
        this.STATUS_LABEL = new JLabel("", JLabel.CENTER);
        this.CONTROL_PANEL = new JPanel();

        this.MAIN_FRAME.setSize(600, 300);
        this.MAIN_FRAME.getContentPane().setBackground(Color.DARK_GRAY); // currently only alters half of the screen for some reason
        this.MAIN_FRAME.setLayout(new GridLayout(2, 1));
        this.MAIN_FRAME.add(this.CONTROL_PANEL);
        this.MAIN_FRAME.add(this.STATUS_LABEL);
        this.MAIN_FRAME.setVisible(true);
        this.MAIN_FRAME.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        System.setProperty("com.apple.mrj.application.apple.menu.about.name", "Name");

        this.CONTROL_PANEL.setLayout(new FlowLayout());
    }

    private void show() {
        final JButton okButton = new JButton("Take Screenshot");

        okButton.addActionListener(e -> {
            Main.this.MAIN_FRAME.setVisible(false);

            try {
                final Dimension screenDimension = Toolkit.getDefaultToolkit().getScreenSize();
                final Robot robot = new Robot();
                final BufferedImage image = robot.createScreenCapture(new Rectangle(screenDimension));

                TransferableImage trans = new TransferableImage(image);
                Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
                clipboard.setContents(trans, null);

                Main.this.STATUS_LABEL.setText("Copied to clipboard.");
                Main.this.MAIN_FRAME.setVisible(true);
            } catch (Exception awtException) {
                awtException.printStackTrace();
            }
        });

        this.CONTROL_PANEL.add(okButton);
        this.MAIN_FRAME.setVisible(true);
    }

    public static void main(String[] args) {
        final Main screenCap = new Main();
        screenCap.show();
    }
}