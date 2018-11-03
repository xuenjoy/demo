package com.tilchina.edi.util;

import com.healthmarketscience.jackcess.CryptCodecProvider;
import com.healthmarketscience.jackcess.Database;
import com.healthmarketscience.jackcess.DatabaseBuilder;
import net.ucanaccess.jdbc.JackcessOpenerInterface;

import java.io.File;
import java.io.IOException;

public class CryptCodecOpener implements JackcessOpenerInterface {
    @Override
    public Database open(File file, String pwd) throws IOException {
        DatabaseBuilder dbd =new DatabaseBuilder(file);

        dbd.setAutoSync(false);
        dbd.setCodecProvider(new CryptCodecProvider(pwd));
        dbd.setReadOnly(false);
        return dbd.open();
    }
}
