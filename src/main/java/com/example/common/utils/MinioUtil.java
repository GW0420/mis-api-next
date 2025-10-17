package com.example.common.utils;

import cn.hutool.core.codec.Base64;
import com.example.common.exception.MisException;
import io.minio.*;
import io.minio.http.Method;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import javax.annotation.PostConstruct;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.concurrent.TimeUnit;


@Component
@Slf4j
public class MinioUtil {
    @Value("${minio.endpoint}")
    private String endpoint;

    @Value("${minio.access-key}")
    private String accessKey;

    @Value("${minio.secret-key}")
    private String secretKey;

    @Value("${minio.bucket}")
    private String bucket;

    private MinioClient minioClient;

    @PostConstruct
    public void init() {
        minioClient = new MinioClient.Builder().endpoint(endpoint)
                .credentials(accessKey, secretKey)
                .build();
    }

    /**
     * 上传图片
     * @param path file
     */
    public void uploadImage(String path, MultipartFile file) {
        try {
            //在Minio中保存图片（文件不能超过5M）
            minioClient.putObject(PutObjectArgs.builder().bucket(bucket).object(path)
                    .stream(file.getInputStream(), -1, 5 * 1024 * 1024)
                    .contentType("image/jpeg").build());
            log.debug("向" + path + "保存了文件");
        } catch (Exception e) {
            log.error("保存文件失败", e);
            throw new MisException("保存文件失败");
        }
    }

    public void uploadImage(String path, String base64Image) {
        try {
            //去掉前缀
            base64Image = base64Image.replace("data:image/jpeg;base64,", "");
            base64Image = base64Image.replace("data:image/png;base64,", "");
            byte[] decode = Base64.decode(base64Image);
            ByteArrayInputStream in = new ByteArrayInputStream(decode);
            //在Minio中保存图片（文件不能超过5M）
            minioClient.putObject(PutObjectArgs.builder().bucket(bucket).object(path).stream(in, -1, 5 * 1024 * 1024).contentType("image/jpeg").build());
            log.debug("向" + path + "保存了文件");
        } catch (Exception e) {
            log.error("保存文件失败", e);
            throw new MisException("保存文件失败");
        }
    }


    /**
     * 删除文件
     * @param path file
     */
    public void deleteFile(String path) {
        try {
            minioClient.removeObject(RemoveObjectArgs.builder()
                    .bucket(bucket)
                    .object(path)
                    .build());
            log.debug("删除了" + path + "路径下的文件");
        } catch (Exception e) {
            log.error("文件删除失败", e);
            throw new MisException("文件删除失败");
        }
    }

    /**
     * 上传word
     * @param path
     * @param in
     */
    public void uploadWord(String path, InputStream in) {
        try {
            String mime = "application/vnd.openxmlformats-officedocument.wordprocessingml.document";
            //在Minio中保存Word文档（文件不能超过50M）
            minioClient.putObject(PutObjectArgs.builder().bucket(bucket)
                    .object(path).stream(in, -1, 50 * 1024 * 1024)
                    .contentType(mime).build());
            log.debug("向" + path + "保存了文件");
            in.close();
        } catch (Exception e) {
            log.error("保存文件失败", e);
            throw new MisException("保存文件失败");
        }
    }

    /**
     * 上传excel
     * @param path file
     */
    public void uploadExcel(String path, MultipartFile file) {
        try {
            //Excel文件的MIME类型
            String mime = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
            //Excel文件不能超过20M
            minioClient.putObject(PutObjectArgs.builder()
                    .bucket(bucket).object(path)
                    .stream(file.getInputStream(), -1, 20 * 1024 * 1024)
                    .contentType(mime).build());
            log.debug("向" + path + "保存了文件");
        } catch (Exception e) {
            log.error("保存文件失败", e);
            throw new MisException("保存文件失败");
        }
    }

//    public String downloadExcel(Integer id) {
//        return "http://localhost:9000/mis/goods/checkup/" + id + ".xlsx";
//    }

    /**
     * 下载excel
     * @param path file
     */
    public InputStream downloadFile(String path) {
        try {
            GetObjectArgs args = GetObjectArgs.builder()
                    .bucket(bucket)
                    .object(path)
                    .build();
            return minioClient.getObject(args);
        } catch (Exception e) {
            log.error("文件下载失败", e);
            throw new MisException("文件下载失败");
        }
    }

    /**
     * 获得上传URL
     * @param path
     * @return
     */
    @SneakyThrows
    public String getDownloadUrl(String key,String path){
        String objectUrl = minioClient.getPresignedObjectUrl(GetPresignedObjectUrlArgs
                .builder()
                .bucket(key)
                .object(path)
                .expiry(10, TimeUnit.MINUTES)
                .method(Method.GET).build());
        return objectUrl;
    }

}
