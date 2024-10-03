import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Image {
  BufferedImage img;
  String fileName = "pixelImage";
  private final int width;
  private final int height;
  private final ArrayList<int[]> fillQueue;

  public Image(int width, int height) {
    this.width = width;
    this.height = height;
    this.fillQueue = new ArrayList<>();

    this.img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
    for (int y = 0; y < height; y++) {
      for (int x = 0; x < width; x++) {
        int randomValue = (int) (Math.random() * 2);
        int p = (randomValue == 0) ? 0xFFFFFFFF : 0xFF000000;

        img.setRGB(x, y, p);
      }
    }

    this.saveImage();
  }

  public void saveImage() {
    try {
      File f = new File("D:/Java Projects/floodfill/" + this.fileName + ".png");
      ImageIO.write(img, "png", f);
    } catch (IOException e) {
      System.out.println("Error: " + e);
    }
  }

  public void getCoordinates(int x, int y) {
    int color = this.getPixelColor(x, y);
    int nextPixelColor;

    this.fillQueue.add(new int[]{x, y});

    while (!this.fillQueue.isEmpty()) {
      int xPosition = this.fillQueue.getFirst()[0];
      int yPosition = this.fillQueue.getFirst()[1];

      for (int i = -1; i < 2; i = i + 2) {
        if ((yPosition + i) < 0 || (yPosition + i) >= width) continue;

        nextPixelColor = getPixelColor(xPosition, yPosition + i);

        if (color == nextPixelColor) this.fillQueue.add(new int[]{xPosition, yPosition + i});
      }

      for (int j = -1; j < 2; j = j + 2) {
        if ((xPosition + j) < 0 || (xPosition + j) >= height) continue;

        nextPixelColor = getPixelColor(xPosition + j, yPosition);

        if (color == nextPixelColor) this.fillQueue.add(new int[]{xPosition + j, yPosition});
      }

      int changeColorTo = (color == 0xFFFFFFFF) ? 0xFF000000 : 0xFFFFFFFF;

      int[] el = this.fillQueue.removeFirst();
      this.updatePixel(el[0], el[1], changeColorTo);
    }
  }

  public int getPixelColor(int x, int y) {
    return img.getRGB(x, y);
  }

  public void updatePixel(int x, int y, int changeColorTo) {
    System.out.println("Current to change color: (" + x + ", " + y + ")");
    try {
      img.setRGB(x, y, changeColorTo);
      saveImage();
    } catch (Exception e) {
      System.out.println(e);
    }
  }
}
