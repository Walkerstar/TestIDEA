package game;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

/**
 * @author mcw 2020\4\24 0024-14:34
 * 柱子
 */
public class Column {
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
     * 柱子之间的间隙
     */
    public int gap;

    /**
     * 柱子之间的距离
     */
    public int distance;

    /**
     * 随机数工具
     */
    Random random = new Random();

    /**
     * 初始化第 N 个柱子
     *
     * @param n 柱子数量
     */
    public Column(int n) throws IOException {
        image = ImageIO.read(getClass().getResource("/resources/column.png"));
        width = image.getWidth();
        height = image.getHeight();
        gap = 144;
        distance = 245;
        x = 550 + (n - 1) * distance;
        y = random.nextInt(218) + 132;
    }

    /**
     * 向左移动一步
     */
    public void step() {
        x--;
        if (x == -width / 2) {
            x = distance * 2 - width / 2;
            y = random.nextInt(218) + 132;
        }
    }
}