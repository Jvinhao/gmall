package org.lf.gmall.manage.utils;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.io.ResolverUtil;
import org.csource.common.MyException;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public class ProImageUtil {


    private static String fileUrl = "http://192.168.183.130";

    public static String upLoadImage(MultipartFile multipartFile) {
        String imgUrl = fileUrl;
        if (multipartFile != null) {
            String filePath = ResolverUtil.Test.class.getResource("/tracker.conf").getPath();
            try {
                ClientGlobal.init(filePath);
                TrackerClient trackerClient = new TrackerClient();
                TrackerServer trackerServer = trackerClient.getConnection();
                StorageClient storageClient = new StorageClient(trackerServer, null);
                String fileName = multipartFile.getOriginalFilename();
                String extName = StringUtils.substringAfterLast(fileName, ".");

                String[] upload_file = storageClient.upload_file(multipartFile.getBytes(), extName, null);
                for (int i = 0; i < upload_file.length; i++) {
                    String path = upload_file[i];
                    imgUrl += "/" + path;
                }

            } catch (IOException e) {
                e.printStackTrace();
            } catch (MyException e) {
                e.printStackTrace();
            }

        }
        return imgUrl;
    }


}
