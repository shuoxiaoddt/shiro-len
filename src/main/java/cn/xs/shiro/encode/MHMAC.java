package cn.xs.shiro.encode;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 * Created by uwayxs on 2017/11/22.
 * Hash Message Authentication Code
 * 散列消息鉴别码
 * 消息鉴别码实现鉴别的原理是，用公开函数和密钥产生一个固定长度的值作为认证标识，用这个 标识鉴别消息的完整性
 * 使用一个密钥生成一个固定大小的小数据块，即MAC，并将其加入到消息中，然后传输。接收方利用与发送方共享的密钥进行鉴别认证
 */
public class MHMAC {
    public static final String KEY_MAC = "HmacMD5";
    /**
     * 初始化HMAC密钥
     *
     * @return
     * @throws Exception
     */
    public static String initMacKey() throws Exception {
        KeyGenerator keyGenerator = KeyGenerator.getInstance(KEY_MAC);
        SecretKey secretKey = keyGenerator.generateKey();
        return MBASE64.encryptBASE64(secretKey.getEncoded());
    }
    /**
     * HMAC加密  ：主要方法
     *
     * @param data
     * @param key
     * @return
     * @throws Exception
     */
    public static String encryptHMAC(byte[] data , String key) throws Exception {
        SecretKey secretKey = new SecretKeySpec(MBASE64.decodeBASE64(key),KEY_MAC);
        Mac mac = Mac.getInstance(secretKey.getAlgorithm());
        mac.init(secretKey);
        return new String(mac.doFinal(data));
    }

    public static  String  getResult1(String inputStr)
    {
        String path=MHMAC.class.getClassLoader().getResource("").getPath();
        String fileSource=path+"file/HMAC_key.txt";
        System.out.println("=======加密前的数据:"+inputStr);
        String  result=null;
        try {
            byte[] inputData = inputStr.getBytes();
            String key = MHMAC.initMacKey(); /*产生密钥*/
            System.out.println("Mac密钥:===" + key);
            /*将密钥写文件*/
            Tools.WriteMyFile(fileSource,key);
            result= MHMAC.encryptHMAC(inputData, key);
            System.out.println("HMAC加密后:===" + result);
        } catch (Exception e) {e.printStackTrace();}
        return result.toString();
    }

    public static  String  getResult2(String inputStr)
    {
        System.out.println("=======加密前的数据:"+inputStr);
        String path=MHMAC.class.getClassLoader().getResource("").getPath();
        String fileSource=path+"file/HMAC_key.txt";
        String key=null;;
        try {
             /*将密钥从文件中读取*/
            key=Tools.ReadMyFile(fileSource);
            System.out.println("getResult2密钥:===" + key);
        } catch (Exception e1) {
            e1.printStackTrace();}
        String  result=null;
        try {
            byte[] inputData = inputStr.getBytes();
            /*对数据进行加密*/
            result= MHMAC.encryptHMAC(inputData, key);
            System.out.println("HMAC加密后:===" + result);
        } catch (Exception e) {e.printStackTrace();}
        return result.toString();
    }
    public static void main(String args[])
    {
        try {
            String inputStr = "简单加密";
             /*使用同一密钥：对数据进行加密：查看两次加密的结果是否一样*/
            String res1 = getResult1(inputStr);
            String res2 = getResult2(inputStr);
            System.out.println(res1.equals(res2));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static class Tools{

        public static void WriteMyFile(String path , String source){
            File file = new File(path) ;
            if(!file.exists()){
                file.getParentFile().mkdirs();
                try {
                    file.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            try (FileOutputStream outputStream = new FileOutputStream(file)) {
                outputStream.write(source.getBytes());
            } catch (Exception e){
                e.printStackTrace();
            }
        }

        public static String ReadMyFile(String path){
            byte[] res = new byte[1024];
            int len = 0;
            try (FileInputStream inputStream = new FileInputStream(new File(path))) {
                len = inputStream.read(res);
            } catch (Exception e){
                e.printStackTrace();
            }
            return new String(res,0,len);
        }
    }

}
