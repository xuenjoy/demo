package com.tilchina.edi.util;

import org.apache.log4j.Logger;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Properties;

public class PropertyUtil extends  Properties {
    private static final long serialVersionUID = 1935981579709590740L;
    private static final Logger logger = LoggerUtil.getLogger(PropertyUtil.class);
    private Charset charset;
    private URL propertiesFileUrl;


    public PropertyUtil(File propertiesFile){
        System.setProperty("log4j.configuration", "log4j.properties");
        this.charset =  Charset.forName("ISO-8859-1");
        try {
            this.load(new FileResource(propertiesFile));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    public void load(FileResource urlResource) {
        this.propertiesFileUrl = urlResource.getUrl();
        if (null == this.propertiesFileUrl) {
            throw new RuntimeException("Can not find properties file: "+urlResource);
        } else {
            logger.debug("Load properties:"+ this.propertiesFileUrl.getPath());

            try {
                BufferedReader reader = urlResource.getReader(this.charset);
                Throwable throwable = null;

                try {
                    super.load(reader);
                } catch (Throwable th) {
                    throwable = th;
                    throw th;
                } finally {
                    if (reader != null) {
                        if (throwable != null) {
                            try {
                                reader.close();
                            } catch (Throwable var12) {
                                throwable.addSuppressed(var12);
                            }
                        } else {
                            reader.close();
                        }
                    }

                }
            } catch (Exception ex) {
                logger.error("Load properties error!");
            }

        }
    }

    public String getStr(String key, String defaultValue) {
        return super.getProperty(key, defaultValue);
    }

    public String getStr(String key) {
        return super.getProperty(key);
    }

    public void setProperty(String key, Object value) {
        super.setProperty(key, value.toString());
    }
    public Integer getInt(String key, Integer defaultValue) {
        try {
            return Integer.valueOf((this.getStr(key)));
        }catch (Exception e){
            return defaultValue;
        }

    }

    public Integer getInt(String key) {
        return this.getInt((String)key, (Integer)null);
    }
    public void store(String absolutePath) throws IOException {
        //
        FileWriter fw=new FileWriter(absolutePath);
        BufferedWriter writer = null;

        try {
            writer = new BufferedWriter(fw);
            super.store(writer, (String)null);
        } catch (IOException ex) {
            throw new IOException( "Store properties  error!"+ absolutePath);
        } finally {
            if(writer!=null){
                writer.close();
            }
        }

    }


}
