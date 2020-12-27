package 面试题;

import java.io.*;
import java.util.ArrayList;
import java.util.Collection;

/**
 * @author mcw 2020\8\21 0021-14:28
 */
public class FileList {
    public static void main(String[] args) {
        //列出要遍历的目录
        File dir=new File("d:/");

        //建立一个过滤器对象，根据用户指定的内容过滤
        FilterBySuffix filter = new FilterBySuffix(".java");

        //建立一个容器来装过滤后的文件
        ArrayList<File> list = new ArrayList<>();

        //遍历，过滤功能函数
        getList(dir,filter,list);
        File dest = new File("destFile.txt");

        //遍历，写入功能函数
        getDestFile(list,dest);
    }



    /**
     * 遍历指定目录下的文件，把指定扩展名的文件过滤下来
     * 然后将指定扩展名的文件装进容器中
     */
    private static void getList(File dir, FilenameFilter filter, Collection<File> collection) {
        File[] file = dir.listFiles();
        assert file != null;
        for (File subfile : file) {
            //判断是否是目录，是文件继续调用该函数
            if (subfile.isDirectory()) {
                //递归
                getList(subfile, filter, collection);
            } else if (filter.accept(subfile, subfile.getAbsolutePath())) {
                //不是目录，过滤
                collection.add(subfile);
            }
        }
    }

    /**
     * 对容器进行遍历，将遍历后的文件写入到指定的文本文件里
     */
    private static void getDestFile(ArrayList<File> list, File dest) {
        //建立一个输出字符流缓冲区对象，提高效率
        BufferedWriter bufw = null;

        try {
            bufw = new BufferedWriter(new FileWriter(dest));
            //对该容器进行遍历，然后写入到指定文件夹
            for (File filename : list) {
                bufw.write(filename.getAbsolutePath());
                //换行
                bufw.newLine();
                //将缓冲区的数据刷新到文本文件中
                bufw.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bufw != null) {
                try {
                    bufw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

class FilterBySuffix implements FilenameFilter{
    private  String suffix;

    public FilterBySuffix(String suffix) {
        this.suffix = suffix;
    }

    @Override
    public boolean accept(File dir, String name) {
        return name.endsWith(suffix);
    }
}