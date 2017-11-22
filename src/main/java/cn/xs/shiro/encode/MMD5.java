package cn.xs.shiro.encode;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import sun.security.provider.MD5;

/**
 * Created by uwayxs on 2017/11/22.
 * Message-Digest Algorithm 5 信息-摘要算法
 * 单向加密算法,只能根据结果匹配进行暴力破解
 * 1、压缩性：任意长度的数据，算出的MD5值长度都是固定的。
 2、容易计算：从原数据计算出MD5值很容易。
 3、抗修改性：对原数据进行任何改动，哪怕只修改1个字节，所得到的MD5值都有很大区别。
 4、弱抗碰撞：已知原数据和其MD5值，想找到一个具有相同MD5值的数据（即伪造数据）是非常困难的。
 5、强抗碰撞：想找到两个不同的数据，使它们具有相同的MD5值，是非常困难的。
 MD5的作用是让大容量信息在用数字签名软件签署私人密钥前被”压缩”成一种保密的格式（就是把一个任意长度的字节串变换成一定长的十六进制数字串）。除了MD5以外，其中比较有名的还有sha-1、RIPEMD以及Haval等。
 */
public class MMD5 {

    public static byte[] EncryptionStr(String str, String algorithm) throws NoSuchAlgorithmException {
        MessageDigest md5 = MessageDigest.getInstance(algorithm);
        md5.update(str.getBytes());
        return md5.digest();
    }

    public static void main(String[] args) throws NoSuchAlgorithmException {
        byte [] bytes = MMD5.EncryptionStr("def","MD5");
        System.out.println(new BigInteger(bytes).toString(16));
    }

}
