import java.util.InputMismatchException;
import java.util.Scanner;

public class Init {
  Image pixelImage;

  public Init() {
    Scanner scan = new Scanner(System.in);

    boolean control = true;
    int x, y;

    System.out.println("Digite a largura da imagem a ser criada.");
    int width = scan.nextInt();

    System.out.println("Digite a altura da imagem a ser criada.");
    int height = scan.nextInt();

    pixelImage = new Image(width, height);

    do {
      try {
        System.out.println("1. Alterar pixels. \n2. Sair.");
        int option = scan.nextInt();

        if (option == 1) {
          System.out.println("Selecione a coordenada 'x': ");
          x = scan.nextInt();

          System.out.println("Selecione a coordenada 'y': ");
          y = scan.nextInt();

          if (x < 0 || x >= width || y < 0 || y >= height) {
            System.out.println("Coordenadas não pertencentes a imagem.");
            continue;
          }

          this.pixelImage.getCoordinates(x, y);
        } else {
          control = false;
        }
      } catch (InputMismatchException e) {
        System.out.println("Digite um número natural, por favor.");
        scan.next();
      }
    } while (control);
  }
}

