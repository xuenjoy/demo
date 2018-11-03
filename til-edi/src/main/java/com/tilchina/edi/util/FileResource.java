package com.tilchina.edi.util;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;

public class FileResource {
    private URL url;

    public FileResource(File file) throws MalformedURLException {
    this(file.toURI().toURL());
    }

    public FileResource(URL url){
        this.url=url;
    }

    public URL getUrl() {
        return this.url;
    }

    public BufferedReader getReader(Charset charset) {
        InputStream in= null;
        try {
            in = this.url.openStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (null == in) {
            return null;
        } else {
            InputStreamReader reader = null;
            if (null == charset) {
                reader = new InputStreamReader(in);
            } else {
                reader = new InputStreamReader(in, charset);
            }

            return new BufferedReader(reader);
        }
    }

}
