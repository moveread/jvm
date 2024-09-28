import com.google.gson.Gson;
import com.moveread.tnmt.Client;

public class Main {
  
  public static void main(String[] args) {
    var client = new Client();
    var images = client.getImages("bamberg24", "a", "1", "1").join();
    System.out.println(images);
  }

}
