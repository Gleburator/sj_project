/*
package GraphicsComponents;

import graphics.PixelBuffer;

public class ConsoleGraphics {
    public static void draw(PixelBuffer canvas)
    {
        //▀▄█░ ▒ ▓
        System.out.print("\033[2J");
        System.out.print("\033[?25l");
        int height=canvas.height();
        int width=canvas.height();
        StringBuilder s = new StringBuilder(height * width * 15 / 2);
        int R1,G1,B1,R2,G2,B2;
        for (int y = 0; y < height-1; y=y+2)
        {
            for (int x = 0; x < width; x++)
            {
                R1 = canvas.getColor(x, y).red();
                G1 = canvas.getColor(x, y).green();
                B1 = canvas.getColor(x, y).blue();
                R2 = canvas.getColor(x, y+1).red();
                G2 = canvas.getColor(x, y+1).green();
                B2 = canvas.getColor(x, y+1).blue();

                if(!canvas.getColor(x, y).equals(canvas.getColor(x, y+1)))
                {
                    s.append(String.format("\033[48;2;%d;%d;%dm", R2, G2,B2));
                    s.append(String.format("\033[38;2;%d;%d;%dm▀", R1, G1,B1));
                }
                else
                {
                    s.append(String.format("\033[38;2;%d;%d;%dm█", R1, G1,B1));
                }

            }
            if (y != height - 2)
            {
                s.append("\n");
            }

        }
        System.out.print("\033[H");
        System.out.print(s);
    }
}

 */



