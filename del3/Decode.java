import java.io.FileInputStream;

public class Decode {
  private int[] byteFreq = new int[256];

  public static void main(String[] args) {
    String compressedFile = args[0];

    try {
      FileInputStream fileStream = new FileInputStream(compressedFile);
    } catch (FileNotFoundExeception e) {
      e.printStackTrace();
    }
  }
}
