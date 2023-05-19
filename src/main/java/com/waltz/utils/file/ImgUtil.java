package com.waltz.utils.file;

import com.waltz.constant.CommonConstant;
import net.coobird.thumbnailator.Thumbnails;
import org.apache.commons.io.FilenameUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
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
