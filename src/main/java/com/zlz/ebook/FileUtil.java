package com.zlz.ebook;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zlz.util.JSONObjectUtil;
import org.apache.commons.io.FileUtils;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.thymeleaf.util.StringUtils;

import java.io.*;
import java.util.Properties;

public class FileUtil {

    private static Logger log = org.slf4j.LoggerFactory.getLogger(FileUtil.class);

    private Properties hosts = new Properties();
    private FileWriter hostsWriter = null;

    private FileUtil() {
        try {
            String value = Constants.propertiesPath;
            File file = new File(value);
            if (!file.exists()) {
                file.createNewFile();
            }
            setHostsWriter(new FileWriter(file, true));
            hosts.load(new FileInputStream(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public UrlSetting getProperties(String host) {
        String value = hosts.getProperty(host);
        if(!StringUtils.isEmptyOrWhitespace(value)){
            UrlSetting setting = JSONObjectUtil.toObject(value,UrlSetting.class);
            return setting;
        }
        return null;
    }

    private static class FileUtilHolder {
        private static FileUtil instance = new FileUtil();
    }

    public static FileUtil instance() {
        return FileUtilHolder.instance;
    }

    /**
     * 写入文件，不存在则创建文件后保存
     */
    public boolean saveValueToFile(File file, String value, boolean append) {
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            FileUtils.write(file, value, "UTF-8", append);
            return true;
        } catch (IOException e) {
            log.error("存储数据出现错误：{}", e);
            return false;
        }
    }

    public boolean saveHost(String host) {
        try {
            if (hosts.getProperty(host) == null) {
                hosts.put(host, "true");
                hostsWriter.write(host + "=true\n");
                hostsWriter.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;

    }

    public FileWriter getHostsWriter() {
        return hostsWriter;
    }

    public void setHostsWriter(FileWriter hostsWriter) {
        this.hostsWriter = hostsWriter;
    }

    public boolean hasHost(String host) {
        if (this.hosts.containsKey(host)) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) throws Exception {
        System.out.println("keep Happy boy");
        String a = FileUtil.instance().hosts.getProperty("www.520xsb.com");
        JSONObject json = new JSONObject(a);
        System.out.println(json.toString());

        // 反序列化
        ObjectMapper objectMapper = new ObjectMapper();
        UrlSetting userRank = objectMapper.readValue(a, UrlSetting.class);
        System.out.println(userRank);

        // 序列化
        String strObj = objectMapper.writeValueAsString(userRank);
        System.out.println(strObj);

    }

}