package com.tilchina.edi.util.excel;

/**
 * Created by Administrator on 2018/10/23.
 */

import com.tilchina.edi.util.LoggerUtil;
import org.apache.log4j.Logger;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.security.Security;


/***
 * 单例模式(迅速加载)的c# base64/AES解密工具类
 * @author ZHD
 * 2018-2-26
 */
public class DecryptUtil {

    private DecryptUtil(){}

    private static DecryptUtil decTool = new DecryptUtil();
    private static final Logger logger = LoggerUtil.getLogger(DecryptUtil.class);
    public static DecryptUtil getInstance(){
        return decTool;
    }

    //算法名称
    final String KEY_ALGORITHM = "AES";
    // 加解密算法/模式/填充方式
    final String algorithmStr = "AES/CBC/PKCS7Padding";

    final String space = " ";

    /**
     * 解密方法
     * @param encryptedData 要解密的字符串
     * @return base64/aes双重解密后的字符串
     */
    public String decrypt(String encryptedData) {
        if(null == encryptedData)
            return null;
        byte[] decryptedText = null;
        String result ;
        try {
            byte[] buf = new sun.misc.BASE64Decoder().decodeBuffer(encryptedData);
            // 初始化
            Security.addProvider(new BouncyCastleProvider());
            // 转化成JAVA的密钥格式
            Key key = new SecretKeySpec(getLegalKey(), KEY_ALGORITHM);
            // 初始化cipher
            Cipher cipher = Cipher.getInstance(algorithmStr);
            cipher.init(Cipher.DECRYPT_MODE, key, new IvParameterSpec(getLegalIV()));
            decryptedText = cipher.doFinal(buf);
            result = new String(decryptedText);
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("数据:"+encryptedData+",解密失败！"+e.getMessage());
            return  null;
        }
        return result;
    }

    public  void test() {
        String encryptedData = decrypt("07mCsz1rB2xS6VxBOySwtjXxNl1IIIG4wkSoOFo96O/AAp8jEPrUO+2xPmTXnbM+");
        System.out.println(encryptedData);
    }
    /**
     * 加密方法
     * @param content 要加密的字符串
     * @return aes/base64位双重加密后的字符串
     */
    public String encrypt(String content) {
        if(null == content)
            return null;
        byte[] encryptedText = null;
        try {
            // 初始化
            Security.addProvider(new BouncyCastleProvider());
            // 转化成JAVA的密钥格式
            Key key = new SecretKeySpec(getLegalKey(), KEY_ALGORITHM);
            Cipher cipher = Cipher.getInstance(algorithmStr);
            cipher.init(Cipher.ENCRYPT_MODE, key, new IvParameterSpec(getLegalIV()));
            encryptedText = cipher.doFinal(content.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new String(new sun.misc.BASE64Encoder().encode(encryptedText));
    }

    private byte[] getLegalIV()
    {
        String iv = "E4ghj*Ghg7!rNIfb&95GUY86GfghUb#er57HBh(u%g6HJ($jhWk7&!hg4ui%$hjk";
        int length = 16;//c# RijndaelManaged.IV.length
        if (iv.length() > length)
            iv = iv.substring(0, length);
        else if (iv.length() < length)
        {
            int len = iv.length();
            for(int i=0;i<16-len;i++){
                iv += space;
            }
        }
        return iv.getBytes();//iv必须为8的倍数
    }

    private byte[] getLegalKey(){
        String key = "adgaw334^*^&#$#$W2343qwreqwr12";
        int length = 32;//c# RijndaelManaged.KEY.length
        if (key.length() > length)
            key = key.substring(0, length);
        else if (key.length() < length)
        {
            int len = key.length();
            for(int i=0;i<32-len;i++){
                key += space;
            }
        }
        return key.getBytes();//key必须为8的倍数
    }
}
