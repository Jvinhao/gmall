package org.lf.gmall.manage.controller;

import org.csource.common.MyException;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;

import java.io.IOException;

public class Test {
    public static void main(String[] args) throws IOException, MyException {
        String path = Test.class.getResource("/tracker.conf").getPath();
        ClientGlobal.init(path);

        TrackerClient trackerClient = new TrackerClient();
        TrackerServer trackerServer = trackerClient.getConnection();

        StorageClient storageClient=new StorageClient(trackerServer,null);
        String orginalFilename="C:/Users/Jvinh/Pictures/Camera Roll/bjtp.jpg";
        String[] jpgs = storageClient.upload_file(orginalFilename, "jpg", null);
        for (int i = 0; i < jpgs.length; i++) {
            String s = jpgs[i];
            System.out.println("s = " + s);

        }
    }
}
