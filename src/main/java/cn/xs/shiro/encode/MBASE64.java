package cn.xs.shiro.encode;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * Created by uwayxs on 2017/11/22.
 * BASE64是双向的,即可以解密
 * 所以严格来说BASE64并不是什么加密算法,而是一种编码格式,网络上最常见的用于传输8Bit字节码的编码方式之一
 * Base64就是一种基于64个可打印字符来表示二进制数据的方法
 * 实际上就是将数据转换成二进制,但是这些二进制数是有64个可打印的字符替代显示的(见RFC2045～RFC2049)
 */
public class MBASE64 {

    public static String encryptBASE64(byte[] target) throws Exception{
        return new BASE64Encoder().encodeBuffer(target);
    }

    public static byte[] decodeBASE64(String s) throws Exception{
        return new BASE64Decoder().decodeBuffer(s);
    }

    public static void main(String[] args) throws Exception{

    }

}
