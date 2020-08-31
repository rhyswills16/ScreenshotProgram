import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;

public class TransferableImage implements Transferable {
    Image image;

    public TransferableImage(Image image) {
        this.image = image;
    }

    @Override
    public Object getTransferData(DataFlavor flavour) throws UnsupportedFlavorException {
        if (flavour.equals(DataFlavor.imageFlavor) && image != null) {
            return image;
        } else {
            throw new UnsupportedFlavorException(flavour);
        }
    }

    @Override
    public DataFlavor[] getTransferDataFlavors() {
        DataFlavor[] flavours = new DataFlavor[1];
        flavours[0] = DataFlavor.imageFlavor;
        return flavours;
    }

    @Override
    public boolean isDataFlavorSupported(DataFlavor flavour) {
        DataFlavor[] flavours = getTransferDataFlavors();
        for (int i = 0; i < flavours.length; i++) {
            if (flavours.equals(flavours[i])) {
                return true;
            }
        }
        return false;
    }
}