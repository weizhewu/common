package com.waltz.utils.file;

import com.waltz.constant.CommonConstant;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Position;
import org.apache.commons.io.FilenameUtils;
import org.springframework.util.CollectionUtils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @version 1.0.0
 * @author: wei-zhe-wu
 * @description: 图片工具
 * @createDate: 2023/5/19 9:22
 **/
public class ImgUtil {

    /**
     * 按照图片质量压缩
     * @param file 图片
     * @param quality 图片质量压缩比例 从0-1，越接近1质量越好
     * @param targetPath 目标地址
     * @return true：压缩成功 false：压缩失败
     */
    public static boolean compressByQuality(File file,Float quality,String targetPath)  {
        if (!ifImg(file)){
            return false;
        }
        targetPath = completeFilePath(file,targetPath);
        try {
            Thumbnails.of(file)
                    //图片大小（长宽）压缩比例 从0-1，1表示原图
                    .scale(1f)
                    .outputQuality(quality)
                    .toOutputStream(Files.newOutputStream(Paths.get(targetPath)));
        } catch (IOException e){
            return false;
        }
        return true;
    }

    /**
     * 按照图片质量以及长宽压缩比列压缩
     * @param file 图片
     * @param scale 图片大小（长宽）压缩比例 从0-1，1表示原图
     * @param quality 图片质量压缩比例 从0-1，越接近1质量越好
     * @param targetPath 目标地址
     * @return true：压缩成功 false：压缩失败
     */
    public static boolean compressByQualityAndScale(File file,Float scale,Float quality,String targetPath)  {
        if (!ifImg(file)){
            return false;
        }
        targetPath = completeFilePath(file,targetPath);
        try {
            Thumbnails.of(file)
                    .scale(scale)
                    .outputQuality(quality)
                    .toOutputStream(Files.newOutputStream(Paths.get(targetPath)));
        } catch (IOException e){
            return false;
        }
        return true;
    }

    /**
     * 按照长宽压缩——不保持图片比例
     * @param file 图片
     * @param width 目标宽度
     * @param height 目标长度
     * @param targetPath 目标地址
     * @return true：压缩成功 false：压缩失败
     */
    public static boolean compressByForceSize(File file,Integer width,Integer height,String targetPath){
        if (!ifImg(file)){
            return false;
        }
        targetPath = completeFilePath(file,targetPath);
        try {
            Thumbnails.of(file)
                    .forceSize(width,height)
                    .toOutputStream(Files.newOutputStream(Paths.get(targetPath)));
        } catch (IOException e){
            return false;
        }
        return true;
    }

    /**
     * 向右旋转
     * @param file 图片
     * @param angle 旋转度数
     * @param targetPath 目标地址
     * @return true：旋转成功 false：旋转失败
     */
    public static boolean rotateToRight(File file,Double angle,String targetPath){
        if (!ifImg(file)){
            return false;
        }
        targetPath = completeFilePath(file,targetPath);
        try {
            Thumbnails.of(file)
                    .rotate(angle)
                    .toOutputStream(Files.newOutputStream(Paths.get(targetPath)));
        } catch (IOException e){
            return false;
        }
        return true;
    }

    /**
     * 添加水印
     * @param file 图片
     * @param waterMarkFile 水印图片
     * @param position 位置 Positions.位置
     * @param transparency 透明度
     * @param targetPath 目标地址
     * @return true：添加成功 false：添加失败
     */
    public static boolean addWaterMark(File file, File waterMarkFile, Position position, Float transparency,String targetPath){
        if (!ifImg(file)){
            return false;
        }
        targetPath = completeFilePath(file,targetPath);
        try {
            Thumbnails.of(file)
                    .watermark(position, ImageIO.read(waterMarkFile),transparency)
                    .toOutputStream(Files.newOutputStream(Paths.get(targetPath)));
        } catch (IOException e){
            return false;
        }
        return true;
    }

    /**
     * 图片裁剪
     * @param file 图片
     * @param position 位置 Positions.位置
     * @param width 裁剪的宽度
     * @param height 裁剪的长度
     * @param keepRatio 是否保持原有图片的长宽比
     * @param targetPath 目标地址
     * @return true：裁剪成功 false：裁剪失败
     */
    public static boolean curRegion(File file,Position position,Integer width,Integer height,boolean keepRatio,String targetPath){
        if (!ifImg(file)){
            return false;
        }
        targetPath = completeFilePath(file,targetPath);
        try {
            Thumbnails.of(file)
                    .sourceRegion(position,width,height)
                    .size(width,height)
                    .keepAspectRatio(keepRatio)
                    .toOutputStream(Files.newOutputStream(Paths.get(targetPath)));
        } catch (IOException e){
            return false;
        }
        return true;
    }

    /**
     * 图片拼接
     * @param files 要拼接的图片
     * @param ifHorizontal 是否水平拼接
     * @param targetPath 目标地址
     * @return true：拼接成功 false：拼接失败
     */
    public static boolean joinImages(List<File> files,boolean ifHorizontal,String targetPath){
        if (CollectionUtils.isEmpty(files)){
            return false;
        }
        for (File file : files) {
            if (!ifImg(file)){
                return false;
            }
        }
        targetPath = completeFilePath(files.get(0),targetPath);
        try {
            List<BufferedImage> imageList = new ArrayList<>();
            for (File file : files) {
                BufferedImage image = ImageIO.read(file);
                imageList.add(image);
            }
            int height = imageList.get(0).getHeight();
            int width = imageList.get(0).getWidth();
            if (ifHorizontal){
                width = imageList.stream().mapToInt(BufferedImage::getWidth).sum();
            } else {
                height = imageList.stream().mapToInt(BufferedImage::getHeight).sum();
            }
            // 创建拼接后的图片画布，参数分别为宽、高、类型，这里我们使用RGB3原色类型
            BufferedImage resultImage = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
            Graphics graphics = resultImage.getGraphics();
            int previousWidth = 0;
            int previousHeight = 0;
            for (BufferedImage image : imageList){
                // 向画布上画图片
                graphics.drawImage(image,previousWidth,previousHeight,null);
                if (ifHorizontal){
                    previousWidth += image.getWidth();
                } else {
                    previousHeight += image.getHeight();
                }
            }
            Thumbnails.of(resultImage)
                    .toOutputStream(Files.newOutputStream(Paths.get(targetPath)));
        } catch (IOException e){
            return false;
        }
        return true;
    }


    /**
     * 判断文件是否是img
     * @param file 文件
     * @return true：是 false：不是
     */
    public static boolean ifImg(File file){
        if (Objects.isNull(file)){
            return false;
        }
        return CommonConstant.File.IMG_TYPE_LIST.contains(FilenameUtils.getExtension(file.getName()));
    }

    /**
     * 补全文件生成路径
     * @param file 图片
     * @param targetPath 目标地址
     * @return 目标地址
     */
    public static String completeFilePath(File file,String targetPath){
        if (Objects.isNull(targetPath)){
            String filename = file.getName();
            targetPath = file.getParent()+FilenameUtils.getBaseName(filename)+"_copy." + FilenameUtils.getExtension(filename);
        }
        return targetPath;
    }

    public static void main(String[] args) {
        System.out.println(compressByQuality(new File("D:/1.jpg"),0.5f,null));
    }
}
