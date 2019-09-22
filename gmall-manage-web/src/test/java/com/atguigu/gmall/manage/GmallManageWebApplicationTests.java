package com.atguigu.gmall.manage;

import org.csource.common.MyException;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GmallManageWebApplicationTests {

	@Test
	public void contextLoads() throws IOException, MyException {
		String fdfsConf =
				GmallManageWebApplicationTests.class.getResource("/fdfs_client.ini").getPath();

		ClientGlobal.init(fdfsConf);
		TrackerClient trackerClient = new TrackerClient();
		TrackerServer trackerServer = trackerClient.getConnection();
		StorageClient storageClient = new StorageClient(trackerServer, null);
		String[] uploadInfos =
				storageClient.upload_file("d:/1.jpg", "jpg", null);

		String svrAddr = "http://192.168.128.109";
		for (String uploadInfo : uploadInfos) {
			svrAddr += "/";
			svrAddr += uploadInfo;
		}

		System.out.println(svrAddr);
	}

}
