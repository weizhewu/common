package com.waltz.utils.content;

import com.waltz.constant.CommonConstant;
import lombok.extern.slf4j.Slf4j;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Objects;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

/**
 * @version 1.0.0
 * @author: wei-zhe-wu
 * @description: 压缩、解压缩工具类
 * @createDate: 2023/4/27 14:00
 **/
@Slf4j
public class CompressUtil {
    /**
     * 使用gzip对文本进行压缩
     * @param originContent 要压缩的内容
     * @return 压缩后的文本
     */
    public static String compressWithGzip(String originContent){
        if (Objects.isNull(originContent) || CommonConstant.BLANK.equals(originContent.trim())){
            return originContent;
        }
        try {
            ByteArrayInputStream in = new ByteArrayInputStream(originContent.getBytes(StandardCharsets.UTF_8));
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            GZIPOutputStream gzip = new GZIPOutputStream(out);
            int count;
            byte[] data = new byte[CommonConstant.File.BUFFER_SIZE];
            while ((count = in.read(data,0,CommonConstant.File.BUFFER_SIZE)) != -1){
                gzip.write(data,0,count);
            }
            gzip.finish();
            gzip.flush();
            return Base64.getEncoder().encodeToString(out.toByteArray());
        } catch (IOException e){
            log.error("文本内容压缩失败，错误原因："+e.getMessage(),e);
            return null;
        }
    }

    /**
     * 对使用gzip压缩的文本进行解压缩
     * @param compressedContent 使用gzip压缩的文本
     * @return 解压后的内容
     */
    public static String unCompressWithGzip(String compressedContent){
        if (Objects.isNull(compressedContent) || CommonConstant.BLANK.equals(compressedContent.trim())){
            return compressedContent;
        }
        byte[] compressed = Base64.getDecoder().decode(compressedContent);
        try {
            ByteArrayInputStream in = new ByteArrayInputStream(compressed);
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            GZIPInputStream gzip = new GZIPInputStream(in);
            byte[] buffer = new byte[CommonConstant.File.BUFFER_SIZE];
            int offset;
            while ((offset = gzip.read(buffer)) != -1){
                out.write(buffer,0,offset);
            }
            gzip.close();
            return out.toString();
        } catch (IOException e){
            log.error("文本内容解压缩失败，错误原因："+e.getMessage(),e);
            return null;
        }
    }
}
