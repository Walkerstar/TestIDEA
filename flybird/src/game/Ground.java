package game;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * @author mcw 2020\4\24 0024-14:34
 * 地面
 */
public class Ground {

    /**
     * 图片
     */
    public BufferedImage image;

    /**
     * 位置
     */
    public int x, y;

    /**
     * 宽高
     */
    public int width, height;

    /**
     * 初始化地面
     */
    public Ground() throws IOException {
        image = ImageIO.read(getClass().getResource("/resources/ground.png"));
        width = image.getWidth();
        height = image.getHeight();
        x = 0;
        y = 500;
    }

    /**
     * 向左移动一步
     */
    public void step() {
        x--;
        if (x == -109) {
            x = 0;
        }
    }
}
