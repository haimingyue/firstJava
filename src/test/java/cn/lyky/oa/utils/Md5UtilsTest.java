package cn.lyky.oa.utils;

import org.junit.Test;

import static org.junit.Assert.*;

public class Md5UtilsTest {

    @Test
    public void md5Digest() {
        System.out.println(Md5Utils.md5Digest("123456", 12345));
    }
}