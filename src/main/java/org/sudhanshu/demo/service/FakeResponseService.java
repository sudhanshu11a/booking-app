package org.sudhanshu.demo.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@Service
public class FakeResponseService {

    private static final Logger LOGGER = LoggerFactory.getLogger(FakeResponseService.class);

    public String getFakeResponse() throws IOException{
        String data = null;
        try {
            File file = ResourceUtils.getFile("classpath:fakeResponse.json");
            data = new String(Files.readAllBytes(file.toPath()));
        } catch (IOException e) {
            LOGGER.error("IOException", e);
            throw e;
        }
        return data;
    }
}
